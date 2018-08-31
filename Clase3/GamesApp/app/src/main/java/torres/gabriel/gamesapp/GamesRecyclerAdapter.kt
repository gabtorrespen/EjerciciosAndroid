package torres.gabriel.gamesapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.recycler_game.view.*
import torres.gabriel.gamesapp.Models.Game

/**
 * Created by Gabriel on 25/08/2018.
 */
class GamesRecyclerAdapter(private val games: ArrayList<Game>) :
        RecyclerView.Adapter<GamesRecyclerAdapter.GameViewHolder>() {

//    private val images = intArrayOf(R.drawable.ic_argentina, R.drawable.ic_bolivia,R.drawable.ic_colombia, R.drawable.ic_costa_rica
//            , R.drawable.ic_cuba, R.drawable.ic_costa_rica, R.drawable.ic_chile, R.drawable.ic_mexico, R.drawable.ic_el_salvador)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): GamesRecyclerAdapter.GameViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_game, parent, false) as View
        return GameViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.view.txtName.text = games[position].name
        holder.view.txtDeveloper.text = games[position].developer
        //holder.view.imagen.drawable = images[countries[position]!!.flag]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = games.size

    class GameViewHolder(val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{

        init {
            this.view.ivGame.setOnClickListener(this)
            this.view.btn_delete.setOnClickListener(this)
            this.view.btn_edit.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            adapterPosition
            when(v!!.id){
                R.id.ivGame->Toast.makeText(view.context,"DETAIL",Toast.LENGTH_LONG).show()
                R.id.btn_delete->Toast.makeText(view.context,"DELETE",Toast.LENGTH_LONG).show()
                R.id.btn_edit->Toast.makeText(view.context,"EDIT",Toast.LENGTH_LONG).show()
            }
        }
    }

}