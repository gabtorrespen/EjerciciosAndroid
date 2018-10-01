package torres.gabriel.songapp.adapter

import android.database.Cursor
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import torres.gabriel.songapp.R

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
                .inflate(R.layout.recycler_song, parent, false) as View
        // set the view's size, margins, paddings and layout parameters

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        myDataset!!.moveToPosition(position)
        holder.nameTextView!!.text = myDataset?.getString(myDataset!!.getColumnIndex("nombre"))
        holder.authorTextView!!.text = myDataset?.getString(myDataset!!.getColumnIndex("autor"))
        holder.albumTextView!!.text = myDataset?.getString(myDataset!!.getColumnIndex("album"))

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
        var nameTextView: TextView? = null
        var authorTextView: TextView? = null
        var albumTextView: TextView? = null

        init {
            nameTextView = view.findViewById(R.id.txtName)
            authorTextView = view.findViewById(R.id.txtAuthor)
            albumTextView = view.findViewById(R.id.txtAlbum)
        }

    }

}