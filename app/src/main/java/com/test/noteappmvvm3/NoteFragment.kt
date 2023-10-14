package com.test.noteappmvvm3

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.noteappmvvm3.adapter.NoteAdapter
import com.test.noteappmvvm3.databinding.FragmentNoteBinding
import com.test.noteappmvvm3.db.NoteDataBase
import com.test.noteappmvvm3.note.Note
import com.test.noteappmvvm3.repository.NoteRealisation
import com.test.noteappmvvm3.repository.NoteRepository
import com.test.noteappmvvm3.viewModel.NoteViewModel
import com.test.noteappmvvm3.viewModel.NoteViewModelFactory


class NoteFragment : Fragment() {
    lateinit var bindingClass: FragmentNoteBinding
    lateinit var db: NoteDataBase
    lateinit var repository: NoteRepository
    lateinit var viewModel: NoteViewModel
    lateinit var factory: NoteViewModelFactory
    lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingClass = FragmentNoteBinding.inflate(inflater)
        return bindingClass.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NoteAdapter()

        bindingClass.rvNote.layoutManager = LinearLayoutManager(requireActivity())
        bindingClass.rvNote.adapter = adapter


        db = NoteDataBase.getDb(requireActivity())
        repository = NoteRealisation(db)
        factory = NoteViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(NoteViewModel::class.java)


        viewModel.getAllNotes().observe(viewLifecycleOwner){
            adapter.noteList = it
            adapter.notifyDataSetChanged()
        }

        adapter.onActionDelete {
            viewModel.deleteNote(it)
            adapter.notifyDataSetChanged()
        }

        bindingClass.ivAdd.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_addNoteFragment)
        }

        bindingClass.ivDeleteAll.setOnClickListener {
            viewModel.deleteAllNotes()
            adapter.notifyDataSetChanged()
        }

        adapter.onActionAddToFavorite {
            it.favorite = 1
            viewModel.updateNote(it)
            adapter.notifyDataSetChanged()
        }

        adapter.onActionUpdate {
            val action = NoteFragmentDirections.actionNoteFragmentToUpdateNoteFragment(it)
            findNavController().navigate(action)
            adapter.notifyDataSetChanged()
        }

    }


}