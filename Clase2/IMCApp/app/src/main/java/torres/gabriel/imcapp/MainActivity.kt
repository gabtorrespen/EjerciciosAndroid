package torres.gabriel.imcapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        btnCalcular.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(v: View?) {
        val strPeso = etPeso.text.toString()
        val strTalla = etTalla.text.toString()

        if(validate(strPeso,strTalla)){
            val numPeso = strPeso.toDouble()
            val numTalla = strTalla.toDouble()/100.0

            val res = numPeso/(numTalla*numTalla)
            var eval = when{
                res<18.5-> "BAJO PESO"
                res<24.9-> "PESO SALUDABLE"
                res<29.9-> "SOBREPESO"
                else-> "OBESIDAD"
            }

            txtIMC.text = eval
        }
    }

    private fun validate(strPeso: String, strTalla: String):Boolean {
        if(strPeso.isNullOrEmpty()){
            etPeso.error = getText(R.string.required_field)
            etPeso.requestFocus()
            return false
        }
        if(strTalla.isNullOrEmpty()){
            etTalla.error = getText(R.string.required_field)
            etTalla.requestFocus()
            return false
        }
        txtIMC.text = ""
        return true
    }
}
