package com.example.gtorres.chuckapp.Adapters

import android.database.Cursor
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gtorres.chuckapp.R

class CustomRecyclerAdapter(private val listener: OnItemClicked): RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>()  {

    private var myDataset: Cursor? = null

    interface OnItemClicked {
        fun onItemClick(position: Int,view: View)
        fun onLongItemClick(position: Int,view: View):Boolean
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CustomRecyclerAdapter.ViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_joke, parent, false) as View
        // set the view's size, margins, paddings and layout parameters

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        myDataset!!.moveToPosition(position)
        holder.jokeTextView!!.text = myDataset?.getString(myDataset!!.getColumnIndex("joke"))
        holder.categoriesTextView!!.text = myDataset?.getString(myDataset!!.getColumnIndex("categories"))

        holder.mView.setOnLongClickListener {view ->
            val id =  myDataset?.getString(myDataset!!.getColumnIndex("id"))
            listener.onLongItemClick(id?.toInt() ?: 0,view)
        }
    }

    fun swap(cur: Cursor?) {
        if (cur != null)
            myDataset = cur
        notifyDataSetChanged()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        if (myDataset == null) return 0
        return myDataset!!.count
    }

    class ViewHolder(view: View)
        : RecyclerView.ViewHolder(view) {

        var mView = view
        var jokeTextView: TextView? = null
        var categoriesTextView: TextView? = null

        init {
            jokeTextView = view.findViewById(R.id.txtJoke)
            categoriesTextView = view.findViewById(R.id.txtCategories)
        }

    }

}