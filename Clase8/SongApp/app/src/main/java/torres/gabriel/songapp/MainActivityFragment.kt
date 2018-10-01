package torres.gabriel.songapp

import android.content.Context
import android.net.Uri
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*
import torres.gabriel.songapp.adapter.CustomRecyclerAdapter

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment(), CustomRecyclerAdapter.OnItemClicked {

    lateinit var adapter: CustomRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        var layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        //layoutManager = GridLayoutManager(this, 1)
        rvSongs.layoutManager = layoutManager
        adapter = CustomRecyclerAdapter(this)
        rvSongs.adapter = adapter

    }

    override fun onItemClick(position: Int, view: View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLongItemClick(position: Int, view: View): Boolean {

        val builder = AlertDialog.Builder(context!!)

        builder.setTitle("Eliminar")
        builder.setMessage("¿Desea eliminar la broma?")

        builder.setPositiveButton("Confirmar"){dialog, which ->
//            contentResolver.delete( Uri.withAppendedPath(Constantes.JOKE_URI,position.toString()),null,null)
//            App.instance.showToast("Eliminado")
//            loadJokes()
        }

        builder.setNeutralButton("Cancelar"){_,_ ->
            App.instance.showToast("Cancelado")
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()

        return true
    }
}
