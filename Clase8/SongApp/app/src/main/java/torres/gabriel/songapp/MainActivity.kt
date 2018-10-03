package torres.gabriel.songapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*
import torres.gabriel.songapp.model.Song

class MainActivity : AppCompatActivity()
                    ,AddEditFragment.AddEditInteractionListener
                    ,ListActivityFragment.ListInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        replaceFragment(ListActivityFragment())

        fab.setOnClickListener { view ->
            replaceFragment(AddEditFragment())
            fab.visibility = View.INVISIBLE
        }
    }

    fun replaceFragment(frag: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, frag)
                .commit()
    }

    override fun onBackPressed() {

        val actualFrag:Fragment = supportFragmentManager.findFragmentById(R.id.fragment_container) 

        if(actualFrag !is ListActivityFragment) {
            fab.visibility = View.VISIBLE
            replaceFragment(ListActivityFragment())
        }
        else
            super.onBackPressed()
    }

    override fun onSongsChanged() {
        replaceFragment(ListActivityFragment())
        fab.visibility = View.VISIBLE
    }

    override fun onShowEditSong(song: Song) {
        replaceFragment(AddEditFragment().newInstance(song))
        fab.visibility = View.INVISIBLE
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
}
