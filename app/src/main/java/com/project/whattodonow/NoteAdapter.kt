package com.project.roomdbkotlin

import androidx.recyclerview.widget.DiffUtil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.project.roomdbkotlin.db.Task
import com.project.whattodonow.R

class NoteAdapter(private val onItemClickListener: (Task) -> Unit)
    : ListAdapter<Task, NoteAdapter.NoteHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent,
            false)
        return NoteHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        with(getItem(position)) {
            holder.tvTitle.text = title
            holder.tvDescription.text = description
//            holder.tvPriority.text = priority
//            holder.cbDoneOrNot.isChecked = stat
        }
    }

    fun getNoteAt(position: Int) = getItem(position)


    inner class NoteHolder(iv: View) : RecyclerView.ViewHolder(iv) {

        val tvTitle: TextView = itemView.findViewById(R.id.title)
        val tvDescription: TextView = itemView.findViewById(R.id.textViewSubtext)
        val tvPriority: TextView = itemView.findViewById(R.id.textViewDesc)
        val cbDoneOrNot: CheckBox = itemView.findViewById(R.id.cb_task)

        init {
            itemView.setOnClickListener {
                if(adapterPosition != NO_POSITION)
                    onItemClickListener(getItem(adapterPosition))
            }
        }

    }
}

private val diffCallback = object : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Task, newItem: Task) =
        oldItem.title == newItem.title
}