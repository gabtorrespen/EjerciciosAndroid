package torres.gabriel.paisesapp

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.ImageView.ScaleType


/**
 * Created by Gabriel on 25/08/2018.
 */
class ImageAdapter(mContext: Context) : BaseAdapter() {

    var mContext: Context? = mContext

    private val images = intArrayOf(R.drawable.ic_argentina, R.drawable.ic_bolivia,R.drawable.ic_colombia, R.drawable.ic_costa_rica
            , R.drawable.ic_cuba, R.drawable.ic_costa_rica, R.drawable.ic_chile, R.drawable.ic_mexico, R.drawable.ic_el_salvador)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView = ImageView(mContext)
        imageView.setImageResource(images[position])
        imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
        imageView.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200)
        return imageView
    }

    override fun getItem(position: Int): Any {
        return images[position];
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return images.size;
    }
}