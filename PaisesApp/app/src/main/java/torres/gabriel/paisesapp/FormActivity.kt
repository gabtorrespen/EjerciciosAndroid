package torres.gabriel.paisesapp

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View

import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.android.synthetic.main.content_form.*
import torres.gabriel.paisesapp.Models.Country

class FormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        spinner2.adapter = ImageAdapter(this)
    }

    public fun submit(view: View){
        var name = etName.text.toString()
        var city = etCity.text.toString()

        if (!name.isEmpty() && !city.isEmpty()){
            var flag = spinner2.selectedItem as Int
            var intent = Intent()
            intent.putExtra("country", Country(name,city,flag))
            setResult(1,intent)
            finish()
        }
    }

}
