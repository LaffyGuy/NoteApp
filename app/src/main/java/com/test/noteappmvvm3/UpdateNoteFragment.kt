package com.test.noteappmvvm3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.test.noteappmvvm3.databinding.FragmentUpdateNoteBinding
import com.test.noteappmvvm3.db.NoteDataBase
import com.test.noteappmvvm3.note.Note
import com.test.noteappmvvm3.repository.NoteRealisation
import com.test.noteappmvvm3.repository.NoteRepository
import com.test.noteappmvvm3.viewModel.NoteViewModel
import com.test.noteappmvvm3.viewModel.NoteViewModelFactory


class UpdateNoteFragment : Fragment() {
    lateinit var bindingClass: FragmentUpdateNoteBinding
    private val args by navArgs<UpdateNoteFragmentArgs>()
    lateinit var db: NoteDataBase
    lateinit var repository: NoteRepository
    lateinit var viewModel: NoteViewModel
    lateinit var factory: NoteViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingClass = FragmentUpdateNoteBinding.inflate(inflater)
        return bindingClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      db = NoteDataBase.getDb(requireActivity())
      repository = NoteRealisation(db)
      factory = NoteViewModelFactory(repository)
      viewModel = ViewModelProvider(requireActivity(), factory).get(NoteViewModel::class.java)

      bindingClass.etTitle.setText(args.current.title)

      bindingClass.btnAddTitle.setOnClickListener {
          val updateNote = Note(args.current.id, bindingClass.etTitle.text.toString(),args.current.favorite)
          viewModel.updateNote(updateNote)
          findNavController().navigate(R.id.action_updateNoteFragment_to_noteFragment)
      }
    }

}