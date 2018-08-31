package torres.gabriel.gamesapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import torres.gabriel.gamesapp.Models.AddEditActivity
import torres.gabriel.gamesapp.Models.Game

class MainActivity : AppCompatActivity() {

    var list = ArrayList<Game>()
    var adapter:GamesRecyclerAdapter = GamesRecyclerAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            startActivityForResult(Intent(this,AddEditActivity::class.java),100)
        }

        list = ArrayList<Game>()
        list.add(Game(1,"Crash bandicoot","Naughty Dog","asd"))
        list.add(Game(2,"Crash bandicoot","Naughty Dog","asd"))

        adapter = GamesRecyclerAdapter(list)
        rvGames.layoutManager = LinearLayoutManager(this)
        rvGames.adapter = adapter
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
