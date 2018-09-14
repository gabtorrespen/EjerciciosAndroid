package com.example.gtorres.chuckapp

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_add.*
import android.widget.CheckBox
import com.example.gtorres.chuckapp.Utils.Constantes


class AddActivity : AppCompatActivity() {

    lateinit var categories: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        categories = ArrayList()
    }

    fun onCheckboxClicked(view:View){
        val checked = (view as CheckBox).isChecked
        val categoryName = view.text.toString()

        if(checked)
            addCategory(categoryName)
        else
            removeCategory(categoryName)

    }

    fun addCategory(category:String){
        if(categories.indexOf(category)<0)
            categories.add(category)
    }

    fun removeCategory(category: String){
        val index = categories.indexOf(category)
        if(index>0)
            categories.removeAt(index)
    }

    fun submit(view: View){
        val jokeText = etJoke.text.toString()

        if (!jokeText.isEmpty()){

            val cv = ContentValues()
            cv.put("joke", jokeText)
            cv.put("categories", categories.toString())
            contentResolver.insert(Constantes.JOKE_URI, cv)

            setResult(Activity.RESULT_OK,Intent())
            finish()
        }
        else
            showToast("Debe escribir un texto")
    }

    fun cancel(view: View) {
        setResult(Activity.RESULT_CANCELED,Intent())
        finish()
    }

    fun showToast(message:String){
        Toast.makeText(applicationContext,message, Toast.LENGTH_SHORT).show()
    }
}
