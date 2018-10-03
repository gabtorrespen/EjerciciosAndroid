package torres.gabriel.songapp

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_main.*
import torres.gabriel.songapp.adapter.CustomRecyclerAdapter
import torres.gabriel.songapp.model.Song
import torres.gabriel.songapp.network.song.SongInteractor
import torres.gabriel.songapp.util.Constants

class ListActivityFragment : Fragment(), CustomRecyclerAdapter.OnItemClicked, SongInteractor.SongCallback {

    lateinit var adapter: CustomRecyclerAdapter

    private var mListener: ListInteractionListener?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvSongs.layoutManager = layoutManager
        adapter = CustomRecyclerAdapter(this)
        rvSongs.adapter = adapter

        SongInteractor(this).getSongs()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is ListInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString()
                    + " must implement ListInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    fun onShowEditSong(song:Song) {
        if(mListener != null)
            mListener!!.onShowEditSong(song)
    }

    fun loadSongs(){
        adapter.swap(activity!!.contentResolver.query(Constants.SONG_URI, null,
                null, null, null))
    }

    override fun onSongsObtained(songs: List<Song>) {
        activity!!.contentResolver.delete(Constants.SONG_URI,null,null)

        val values:ArrayList<android.content.ContentValues> = ArrayList()

        songs.forEach {
            val cv = ContentValues()
            cv.put("id",it.id)
            cv.put("nombre", it.nombre)
            cv.put("autor", it.autor)
            cv.put("album", it.album)
            values.add(cv)
        }

        val a = arrayOfNulls<ContentValues>(values.size)

        activity!!.contentResolver.bulkInsert(Constants.SONG_URI, values.toArray(a))
        loadSongs()
    }

    override fun onError(error: Throwable) {
        Toast.makeText(activity,error.message,Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(position: Int, view: View) {
    }

    override fun onLongItemClick(position: Int, view: View): Boolean {

        val builder = AlertDialog.Builder(context!!)

        builder.setTitle("Seleccione")
        builder.setMessage("¿Qué desea hacer?")

        builder.setPositiveButton("Editar"){dialog, which ->

            val cursor = activity!!.contentResolver.
                    query( Uri.withAppendedPath(Constants.SONG_URI,position.toString()),null,null,null,null)

            cursor.moveToFirst()
            val song = Song()
            song.id = cursor.getInt(cursor.getColumnIndex("id"))
            song.nombre = cursor.getString(cursor.getColumnIndex("nombre"))
            song.autor = cursor.getString(cursor.getColumnIndex("autor"))
            song.album = cursor.getString(cursor.getColumnIndex("album"))

            onShowEditSong(song)
        }

        builder.setNegativeButton("Eliminar"){_,_ ->
            activity!!.contentResolver .delete( Uri.withAppendedPath(Constants.SONG_URI,position.toString()),null,null)
            App.instance.showToast("Eliminado")
            loadSongs()
        }

        builder.setNeutralButton("Cancelar"){_,_ ->
            App.instance.showToast("Cancelado")
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()

        return true
    }

    interface ListInteractionListener {
        fun onShowEditSong(song:Song)
    }
}
