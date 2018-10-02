package torres.gabriel.songapp

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import torres.gabriel.songapp.model.Song

class MainActivity : AppCompatActivity(),AddEditFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        replaceFragment(ListActivityFragment())

        fab.setOnClickListener { view ->
            val frag = AddEditFragment.newInstance(Song())
            replaceFragment(frag)
            fab.visibility = View.INVISIBLE
        }
    }

    private fun replaceFragment(frag: Fragment) {
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

    override fun onFragmentInteraction(uri: Uri?) {
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
