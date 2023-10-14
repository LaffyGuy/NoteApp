package com.test.noteappmvvm3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.noteappmvvm3.R
import com.test.noteappmvvm3.databinding.ItemFavoriteBinding
import com.test.noteappmvvm3.note.Note

class NoteFavoritesAdapter: RecyclerView.Adapter<NoteFavoritesAdapter.FavoritesViewHolder>() {
    var listFavorites = emptyList<Note>()
    private var actionDeleteFromFavorite: ((Note) -> Unit)? = null

    class FavoritesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
       val bindingClass = ItemFavoriteBinding.bind(itemView)
        fun bind( note: Note){
            bindingClass.tvTitle.text = note.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return FavoritesViewHolder(view)

    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val currentFavorites = listFavorites[position]
        holder.bind(currentFavorites)

        holder.bindingClass.ivDeleteFavorite.setOnClickListener {
            actionDeleteFromFavorite?.let {callback ->
                callback(currentFavorites)
            }
        }


    }

    fun onActionDeleteFromFavorites(callback: (Note) -> Unit){
        actionDeleteFromFavorite = callback
    }

    override fun getItemCount(): Int {
        return  listFavorites.size
    }
}