package torres.gabriel.gamesapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import torres.gabriel.gamesapp.Models.Game

class MainActivity : AppCompatActivity(),GamesRecyclerAdapter.OnItemClicked {

    val CREATE_FLAG:Int = 10
    val EDIT_FLAG:Int = 20

    var list = ArrayList<Game>()
    var adapter:GamesRecyclerAdapter = GamesRecyclerAdapter(list,this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val intent = Intent(this, AddEditActivity::class.java)
            intent.putExtra("IdNewGame",adapter.itemCount)
            startActivityForResult(intent,CREATE_FLAG)
        }

        initData()

        adapter = GamesRecyclerAdapter(list, this)
        rvGames.layoutManager = LinearLayoutManager(this)
        rvGames.adapter = adapter
    }

    fun initData(){
        list = ArrayList<Game>()
        list.add(Game(1,"Crash bandicoot","Naughty Dog","asd"))
        list.add(Game(2,"Crash bandicoot","Naughty Dog","asd"))
    }

    override fun onItemClick(position: Int, view: View) {
        when (view.id){
            R.id.ivGame ->{
                val intent = Intent(this,DetailActivity::class.java)
                intent.putExtra("Game",list.get(position))
                startActivity(intent)
            }
            R.id.btn_delete -> showDeleteDialog(position)
            R.id.btn_edit -> {
                val intent = Intent(this,AddEditActivity::class.java)
                intent.putExtra("Game",list.get(position))
                startActivityForResult(intent,EDIT_FLAG)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            var game:Game? = null
            when(requestCode){
                CREATE_FLAG->{
                    game = data!!.getSerializableExtra("game") as Game
                    list.add(game)
                }
                EDIT_FLAG->{
                    game = data!!.getSerializableExtra("game") as Game

                    val index = list.indexOfFirst{it.id == game!!.id}
                    if(index != -1){
                        list.set(index,game)
                    }
                }
            }
            adapter.notifyDataSetChanged()
        }
    }

    fun showDeleteDialog(position: Int){
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Eliminar")
        builder.setMessage("¿Desea eliminar el juego?")

        builder.setPositiveButton("Confirmar"){dialog, which ->
            list.removeAt(position)
            adapter.notifyDataSetChanged()
            showToast("Juego eliminado")
        }

        builder.setNeutralButton("Cancelar"){_,_ ->
            showToast("Cancelado")
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun showToast(message:String){
        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
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
