package torres.gabriel.temperatureapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    //0 -> Celsius
    //1 -> Fahrenheit
    //2 -> Kelvin
    var actualUnidad = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        btnCelsius.setOnClickListener(this)
        btnFahrenheit.setOnClickListener(this)
        btnKelvin.setOnClickListener(this)
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

        var strNumber = ""

        when (v!!.id){
            R.id.btnCelsius-> {
                actualUnidad = 0
                strNumber = etCelsius.text.toString()
            }
            R.id.btnFahrenheit->{
                actualUnidad = 1
                strNumber = etFahrenheit.text.toString()
            }
            R.id.btnKelvin->{
                actualUnidad = 2
                strNumber = etKelvin.text.toString()
            }
        }

        if(validate(strNumber)){
            val number = strNumber.toDouble()

            when(actualUnidad){
                0 -> {
                    etFahrenheit.setText(Conversion.CelsiusToFahrenheit(number).toString())
                    etKelvin.setText(Conversion.CelsiusToKelvin(number).toString())
                }
                1 -> {
                    etCelsius.setText(Conversion.FahrenheitToCelsius(number).toString())
                    etKelvin.setText(Conversion.FahrenheitToKelvin(number).toString())
                }
                2 -> {
                    etCelsius.setText(Conversion.KelvinToCelsius(number).toString())
                    etFahrenheit.setText(Conversion.KelvinToFahrenheit(number).toString())
                }
            }
        }
    }

    private fun validate(str:String):Boolean{

        if(!str.isEmpty())
            return true

        when(actualUnidad){
            0 -> {
                etCelsius.error = getString(R.string.required_field)
                etCelsius.requestFocus()
            }
            1 -> {
                etFahrenheit.error = getString(R.string.required_field)
                etFahrenheit.requestFocus()
            }
            2 -> {
                etKelvin.error = getString(R.string.required_field)
                etKelvin.requestFocus()
            }
        }
        return false
    }

}
