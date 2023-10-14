package com.test.noteappmvvm3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.noteappmvvm3.R
import com.test.noteappmvvm3.databinding.NoteItemBinding
import com.test.noteappmvvm3.note.Note

class NoteAdapter:RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    var noteList = emptyList<Note>()
    private var actionDelete: ((Note) -> Unit)? = null
    private var actionUpdate: ((Note) -> Unit)? = null
    private var actionAddToFavorite: ( (Note) -> Unit)? = null
    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
      val bindingClass = NoteItemBinding.bind(itemView)
        fun bind(note: Note){
            bindingClass.tvTitle.text = note.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
         val currentNote = noteList[position]
        holder.bind(currentNote)

        holder.bindingClass.ivDelete.setOnClickListener {
            actionDelete?.let {
               it(currentNote)
            }
        }

        holder.bindingClass.clNote.setOnClickListener{
       actionUpdate?.let {callback ->
           callback(currentNote)
       }
        }

        holder.bindingClass.ivFavorite.setOnClickListener {
            actionAddToFavorite?.let { callback ->
                callback(currentNote)
            }
        }

    }

    fun onActionDelete(callback: (Note) -> Unit){
        actionDelete = callback
    }

    fun onActionUpdate(callback: (Note) -> Unit){
        actionUpdate = callback
    }

    fun onActionAddToFavorite(callback: (Note) -> Unit){
        actionAddToFavorite = callback
    }


    override fun getItemCount(): Int {
        return noteList.size
    }
}