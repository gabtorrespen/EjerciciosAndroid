package torres.gabriel.consumerapp

import android.database.Cursor
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SimpleCursorAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        consumeProvider()
    }

    private fun consumeProvider() {

        val AUTHORITY = "com.example.gtorres.chuckapp.Providers.AppProvider"
        val JOKE_TABLE_NAME = "Joke"

        val projections = arrayOf( "id _id","id", "joke")

        val uri = Uri.parse("content://${AUTHORITY}/${JOKE_TABLE_NAME}")
        val cursor = contentResolver.query(uri, projections,
                null, null, null)

        updateList(cursor)
    }

    fun updateList(cursor: Cursor){
        val JOKE_COLUMN_ID = "id"
        val JOKE_TABLE_NAME = "joke"
        val listAdapter = SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                cursor,
                arrayOf(JOKE_COLUMN_ID, JOKE_TABLE_NAME),
                intArrayOf(android.R.id.text1, android.R.id.text2),
                0
        )
        listView.adapter = listAdapter
    }


}
