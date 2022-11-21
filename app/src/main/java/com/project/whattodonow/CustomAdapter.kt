package com.project.whatdonow

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.project.whattodonow.R


class CustomAdapter(private val mList: List<ItemsViewModel>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        holder.textView.text = ItemsViewModel.text
        if(position ==1){
            holder.checkBox.isChecked =true
        }
        if (holder.checkBox.isChecked) {
            holder.cardView.setBackgroundColor(Color.MAGENTA)
        }else{
            holder.cardView.setBackgroundColor(Color.WHITE)
        }

        holder.checkBox.setOnCheckedChangeListener { View, isChecked ->
            if (isChecked) {
                holder.cardView.setBackgroundColor(Color.MAGENTA)
            }else{
                holder.cardView.setBackgroundColor(Color.WHITE)
            }
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val checkBox: CheckBox = itemView.findViewById(R.id.cb_task)
        val cardView: CardView = itemView.findViewById(R.id.cv_task)
    }
}
