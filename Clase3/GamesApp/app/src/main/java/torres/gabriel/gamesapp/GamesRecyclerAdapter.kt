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
class GamesRecyclerAdapter(private val games: ArrayList<Game>, private val listener: OnItemClicked) :
        RecyclerView.Adapter<GamesRecyclerAdapter.GameViewHolder>() {

    class GameViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    interface OnItemClicked {
        fun onItemClick(position: Int,view: View)
    }

    private val images = intArrayOf(R.drawable.controller, R.drawable.logo_gears)

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
        holder.view.ivGame.setImageResource(games[position].idImage!!)

        holder.view.ivGame.setOnClickListener { view ->
            listener.onItemClick(position,view)
        }

        holder.view.btn_delete.setOnClickListener { view ->
            listener.onItemClick(position,view)
        }

        holder.view.btn_edit.setOnClickListener { view ->
            listener.onItemClick(position,view)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = games.size

}