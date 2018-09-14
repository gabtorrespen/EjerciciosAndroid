package com.example.gtorres.chuckapp

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.gtorres.chuckapp.Adapters.CustomRecyclerAdapter
import com.example.gtorres.chuckapp.Models.Joke
import com.example.gtorres.chuckapp.Models.ResponseJoke
import com.example.gtorres.chuckapp.Utils.Constantes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), CustomRecyclerAdapter.OnItemClicked{

    companion object {
        var REQUEST_ADD = 100
    }

    lateinit var data: ArrayList<Joke>
    lateinit var adapter: CustomRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //layoutManager = GridLayoutManager(this, 1)
        rvJokes.layoutManager = layoutManager
        adapter = CustomRecyclerAdapter(this)
        rvJokes.adapter = adapter

        //Eliminar todas las bromas y cargar de la página
        contentResolver.delete(Constantes.JOKE_URI,null,null)
        getJokesFromAPI()
        //loadJokes()

        fab.setOnClickListener { view ->
            startActivityForResult(Intent(this,AddActivity::class.java),REQUEST_ADD)
        }
    }

    fun loadJokes(){
        adapter.swap(contentResolver.query(Constantes.JOKE_URI, null,
                null, null, null))
    }

    fun getJokesFromAPI(){
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "http://api.icndb.com/jokes/random/50/"

        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->

                    val listType = object : TypeToken<ResponseJoke>() {}.type
                    val jokeResponse = Gson().fromJson<ResponseJoke>(response, listType)

                    jokeResponse.value!!.forEach {
                        val cv = ContentValues()
                        cv.put("joke", it.joke)
                        cv.put("categories", it.categories.toString())
                        contentResolver.insert(Constantes.JOKE_URI, cv)
                    }
                    loadJokes()
                },
                Response.ErrorListener {

                })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_ADD){
            loadJokes()
        }
    }

    override fun onItemClick(position: Int, view: View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLongItemClick(position: Int, view: View): Boolean {

        val builder = AlertDialog.Builder(this)

        builder.setTitle("Eliminar")
        builder.setMessage("¿Desea eliminar la broma?")

        builder.setPositiveButton("Confirmar"){dialog, which ->
            contentResolver.delete( Uri.withAppendedPath(Constantes.JOKE_URI,position.toString()),null,null)
            App.instance.showToast("Eliminado")
            loadJokes()
        }

        builder.setNeutralButton("Cancelar"){_,_ ->
            App.instance.showToast("Cancelado")
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()

        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_reload -> loadJokes()
            else -> return super.onOptionsItemSelected(item)
        }

        return true
    }
}
