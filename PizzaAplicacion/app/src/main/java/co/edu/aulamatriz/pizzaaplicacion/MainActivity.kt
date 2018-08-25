package co.edu.aulamatriz.pizzaaplicacion

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            if(validar())
                mostrarDialogo()
        }
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

    fun validar():Boolean{
        if(rgTamano.checkedRadioButtonId==-1) {
            Toast.makeText(this, "Debe seleccionar un tamaño", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun obtenerMensajePedido():String{

        var valor = 0.0
        when (rgTamano.checkedRadioButtonId){
            radioButton1.id-> valor += 15000.0
            radioButton2.id-> valor += 25000.0
            radioButton3.id-> valor += 35000.0
        }

        when{
            checkBox.isChecked-> valor += 1000
            checkBox2.isChecked-> valor += 2000
            checkBox3.isChecked-> valor += 3000
        }

        return "El valor del pedido es de $valor"
    }

    fun mostrarDialogo(){
        val builder = AlertDialog.Builder(this@MainActivity)

        builder.setTitle("Ordenar pizza")

        builder.setMessage(obtenerMensajePedido())

        builder.setPositiveButton("Confirmar"){dialog, which ->
            Toast.makeText(applicationContext,"Pedido confirmado",Toast.LENGTH_SHORT).show()
        }

        builder.setNeutralButton("Cancelar"){_,_ ->
            Toast.makeText(applicationContext,"Pedido cancelado",Toast.LENGTH_SHORT).show()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
