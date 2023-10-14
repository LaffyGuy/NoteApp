package com.test.noteappmvvm3

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.test.noteappmvvm3.databinding.FragmentAddNoteBinding
import com.test.noteappmvvm3.db.NoteDataBase
import com.test.noteappmvvm3.repository.NoteRealisation
import com.test.noteappmvvm3.repository.NoteRepository
import com.test.noteappmvvm3.viewModel.NoteViewModel
import com.test.noteappmvvm3.viewModel.NoteViewModelFactory


class AddNoteFragment : Fragment() {
  lateinit var bindingClass: FragmentAddNoteBinding
  lateinit var db: NoteDataBase
  lateinit var repository: NoteRepository
  lateinit var viewModel: NoteViewModel
  lateinit var factory: NoteViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingClass = FragmentAddNoteBinding.inflate(inflater)
        return bindingClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = NoteDataBase.getDb(requireActivity())
        repository = NoteRealisation(db)
        factory = NoteViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(NoteViewModel::class.java)

        bindingClass.btnAddTitle.setOnClickListener {
            val note = com.test.noteappmvvm3.note.Note(null, bindingClass.etTitle.text.toString())
            viewModel.addNote(note)
            findNavController().navigate(R.id.action_addNoteFragment_to_noteFragment)

        }
    }


}