package torres.gabriel.fragmentapp

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import torres.gabriel.fragmentapp.fragments.BlankFragment
import torres.gabriel.fragmentapp.fragments.ItemFragment
import torres.gabriel.fragmentapp.fragments.MainFragment
import torres.gabriel.fragmentapp.fragments.dummy.DummyContent

class MainActivity : AppCompatActivity(),MainFragment.OnMainFragmentListener,
        BlankFragment.OnFragmentInteractionListener ,
        ItemFragment.OnListFragmentInteractionListener{


    var fragment: MainFragment? = null

    var onChanged: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            if(onChanged)
                replaceFragment(R.id.container2, ItemFragment())
            else
                replaceFragment(R.id.container2, BlankFragment())
        }

        fragment = MainFragment.createInstance("dato1")
        replaceFragment(R.id.container, fragment!!)
        replaceFragment(R.id.container2, BlankFragment())
    }

    private fun replaceFragment(id:Int,fragment: Fragment) {

        supportFragmentManager.beginTransaction()
                .replace(id,fragment)
                .commit()
    }

    override fun onClick(param: String) {
        Toast.makeText(this,param,Toast.LENGTH_LONG).show()
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
