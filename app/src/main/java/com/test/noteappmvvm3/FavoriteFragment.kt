package com.test.noteappmvvm3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.noteappmvvm3.adapter.NoteAdapter
import com.test.noteappmvvm3.adapter.NoteFavoritesAdapter
import com.test.noteappmvvm3.databinding.FragmentFavoriteBinding
import com.test.noteappmvvm3.db.NoteDataBase
import com.test.noteappmvvm3.repository.NoteRealisation
import com.test.noteappmvvm3.repository.NoteRepository
import com.test.noteappmvvm3.viewModel.NoteViewModel
import com.test.noteappmvvm3.viewModel.NoteViewModelFactory


class FavoriteFragment : Fragment() {
    lateinit var bindingClass: FragmentFavoriteBinding
    lateinit var db: NoteDataBase
    lateinit var repository: NoteRepository
    lateinit var viewModel: NoteViewModel
    lateinit var factory: NoteViewModelFactory
    lateinit var adapter: NoteFavoritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingClass = FragmentFavoriteBinding.inflate(inflater)
        return bindingClass.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NoteFavoritesAdapter()

        bindingClass.rvFavoritNotes.layoutManager = LinearLayoutManager(requireActivity())
        bindingClass.rvFavoritNotes.adapter = adapter

        db = NoteDataBase.getDb(requireActivity())
        repository = NoteRealisation(db)
        factory = NoteViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(NoteViewModel::class.java)

        viewModel.favorites().observe(viewLifecycleOwner){
            adapter.listFavorites = it
            adapter.notifyDataSetChanged()
        }

        adapter.onActionDeleteFromFavorites {
            it.favorite = 0
            viewModel.updateNote(it)
            adapter.notifyDataSetChanged()
        }

    }

}