package torres.gabriel.permission

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import android.provider.ContactsContract
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    val REQUEST_CODE_CONTACT = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        if(ContextCompat.checkSelfPermission(this,"android.permission.READ_CONTACTS")!=
                PackageManager.PERMISSION_GRANTED) {
            val permissions = arrayOf(Manifest.permission.READ_CONTACTS)
            ActivityCompat.requestPermissions(this,permissions,REQUEST_CODE_CONTACT)
        }else{
            getContacts()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_CODE_CONTACT){
            var index = 0
            for (permission in permissions){
                if(permission.equals(Manifest.permission.READ_CONTACTS)){
                    if(grantResults[index]== PackageManager.PERMISSION_GRANTED){
                        getContacts()
                        break
                    }
                    else{
                        Toast.makeText(this,"Permisos no concedidos",Toast.LENGTH_LONG).show()
                        finish()
                    }
                }
                index++
            }
        }
    }

    private fun getContacts() {

        val projection = arrayOf(ContactsContract.Data._ID
                ,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                ,ContactsContract.CommonDataKinds.Phone.NUMBER)

        val phones = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection, null, null, null)

        val listAdapter = SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                phones,
                arrayOf(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                        ,ContactsContract.CommonDataKinds.Phone.NUMBER),
                intArrayOf(android.R.id.text1, android.R.id.text2),
                0
        )
        listView.adapter = listAdapter

        R.string.share_data
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
