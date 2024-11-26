package com.purity.memomate10.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.purity.memomate10.data.note
import com.purity.memomate10.databinding.ItemNoteBinding


class NoteAdapter : ListAdapter<note, NoteAdapter.NoteViewHolder>(NoteDiffCallBack()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteAdapter.NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NoteViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(note: note){
            binding.titleText.text= note.title
            binding.contentText.text = note.content
//            binding.createdAt.text = note.createdAt
        }
    }

    class NoteDiffCallBack : DiffUtil.ItemCallback<note>(){
        override fun areItemsTheSame(oldItem: note, newItem: note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: note, newItem: note): Boolean {
            return oldItem == newItem
        }
    }
}