package torres.gabriel.songapp;

import android.content.ContentValues
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import kotlinx.android.synthetic.main.fragment_add_edit.*

import torres.gabriel.songapp.model.Song;
import torres.gabriel.songapp.util.Constants

class AddEditFragment : Fragment() {

    companion object {
        val SONG_PARAM = "song"
    }

    private var song:Song? = null

    private var mListener:AddEditInteractionListener?=null

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param song Parameter 1.
     * @return A new instance of fragment AddEditFragment.
     */
    fun newInstance(song:Song):AddEditFragment {
        val fragment = AddEditFragment()
        val args = Bundle()
        args.putSerializable(SONG_PARAM, song)
        fragment.arguments = args
        return fragment
    }

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            song = arguments!!.getSerializable(SONG_PARAM) as Song
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSave.setOnClickListener {
            addEdit()
        }

        if (song != null) {
            btnSave.text = "Actualizar"
            etName.setText(song!!.nombre)
            etAuthor.setText(song!!.autor)
            etAlbum.setText(song!!.album)
        }
    }

    fun addEdit(){
        val name = etName.text.toString()
        val author = etAuthor.text.toString()
        val album = etAlbum.text.toString()
        if(name.isNullOrEmpty()||author.isNullOrEmpty()||album.isNullOrEmpty()){
            App.instance.showToast("Todos los campos son requeridos")
            return
        }

        val cv = ContentValues()
        cv.put("nombre", song!!.nombre)
        cv.put("autor", song!!.autor)
        cv.put("album", song!!.album)

        if(song != null){
            //Edit
            activity!!.contentResolver .update( Uri.withAppendedPath(Constants.SONG_URI,song!!.id.toString()),cv,null,null)
            onEditedSong()
        }
        else{
            //Create
            activity!!.contentResolver .insert( Constants.SONG_URI,cv)
            onAddedSong()
        }
    }

    fun onAddedSong() {
        if(mListener != null)
            mListener!!.onAddedSong()
    }

    fun onEditedSong() {
        if(mListener != null)
            mListener!!.onEditedSong()
    }

    override fun onAttach(context:Context) {
        super.onAttach(context)
        if (context is AddEditInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString()
                    + " must implement AddEditInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface AddEditInteractionListener {
        fun onAddedSong()
        fun onEditedSong()
    }
}
