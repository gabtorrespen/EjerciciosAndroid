package torres.gabriel.fragmentapp.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import torres.gabriel.fragmentapp.R
import java.lang.Exception

/**
 * Created by Gabriel on 29/09/2018.
 */
class MainFragment: Fragment() {

    var listener: OnMainFragmentListener? = null

    companion object {
        fun createInstance(param1: String): MainFragment {
            val fragment = MainFragment()
            val bundle = Bundle()
            bundle.putString("param1", param1)

            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.main_fragment,container,false)


        if(listener != null){
            listener!!.onClick("dato desde el fragmento")
        }

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if(context is OnMainFragmentListener){
            listener = context
        }
        else{
            throw MainFragmentException()
        }

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun fromActivity(param:String){
        Toast.makeText(activity,param,Toast.LENGTH_LONG).show()
    }

    interface OnMainFragmentListener{
        fun onClick(param: String)
    }

    class MainFragmentException:Exception("Por favor implementar OnMainFragmentListener")
}