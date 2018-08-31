package torres.gabriel.paisesapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.recycler_country.view.*
import torres.gabriel.paisesapp.Models.Country

/**
 * Created by Gabriel on 25/08/2018.
 */
class CountryRecyclerAdapter(private val countries: ArrayList<Country>) :
        RecyclerView.Adapter<CountryRecyclerAdapter.CountryViewHolder>() {

    class CountryViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    private val images = intArrayOf(R.drawable.ic_argentina, R.drawable.ic_bolivia,R.drawable.ic_colombia, R.drawable.ic_costa_rica
            , R.drawable.ic_cuba, R.drawable.ic_costa_rica, R.drawable.ic_chile, R.drawable.ic_mexico, R.drawable.ic_el_salvador)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CountryRecyclerAdapter.CountryViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_country, parent, false) as View
        // set the view's size, margins, paddings and layout parameters
        return CountryViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.view.txtCountry.text = countries[position].name
        holder.view.txtCity.text = countries[position].city
        //holder.view.imagen.drawable = images[countries[position]!!.flag]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = countries.size
}