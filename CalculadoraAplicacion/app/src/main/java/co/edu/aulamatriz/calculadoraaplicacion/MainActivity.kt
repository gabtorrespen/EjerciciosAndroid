package co.edu.aulamatriz.calculadoraaplicacion

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {

    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

//        btn_sum.setOnClickListener{
//            Log.d(TAG,"setOnClickListener btn sum")
//        }

        //btn_sum.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        Log.d(TAG,"setOnClickListener btn sum")
    }

    fun procesar(view:View){

        val strNumber1 = etNumber1.text.toString()
        val strNumber2 = etNumber2.text.toString()

        if(validate(strNumber1,strNumber2)){
            var num1 = strNumber1.toDouble()
            var num2 = strNumber2.toDouble()

            var res = 0.0
            when(view.id){
                R.id.btn_sum->res = num1+num2
                R.id.btn_sub->res = num1-num2
                R.id.btn_pro->res = num1*num2
                R.id.btn_div->res = num1/num2
            }

            txt_res.text = res.toString()
            Log.d(TAG,"res :$res")
        }

    }

    private fun validate(strNumber1: String, strNumber2: String):Boolean {
        if(strNumber1.isNullOrEmpty()){
            etNumber1.error = getText(R.string.required_field)
            etNumber1.requestFocus()
            return false
        }
        if(strNumber2.isNullOrEmpty()){
            etNumber2.error = getText(R.string.required_field)
            etNumber2.requestFocus()
            return false
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
