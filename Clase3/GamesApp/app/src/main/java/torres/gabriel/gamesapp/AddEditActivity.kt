package torres.gabriel.gamesapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import torres.gabriel.gamesapp.R

import kotlinx.android.synthetic.main.activity_add_edit.*
import kotlinx.android.synthetic.main.content_add_edit.*
import torres.gabriel.gamesapp.Models.Game

class AddEditActivity : AppCompatActivity() {

    val CREATE_FLAG:Int = 10
    val EDIT_FLAG:Int = 20

    var game: Game? = null
    var newId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        newId = intent.getIntExtra("IdNewGame",0)

        if (newId == 0) {
            game = intent.getSerializableExtra("Game") as Game
            initEdit()
        }
    }

    fun initEdit(){
        etName.setText(game!!.name)
        etDeveloper.setText(game!!.developer)
        etDescription.setText(game!!.description)
    }

    fun submit(view: View){
        var name = etName.text.toString()
        var developer = etDeveloper.text.toString()
        var description = etDescription.text.toString()

        if (!name.isEmpty() && !developer.isEmpty() && !description.isEmpty()){
            var intent = Intent()

            if(newId != 0){
                //Create
                intent.putExtra("game", Game(newId+1,name,developer,description))

            }else{
                //Edit
                game!!.name = name
                game!!.developer = developer
                game!!.description = description

                intent.putExtra("game", game)
            }

            setResult(Activity.RESULT_OK,intent)
            finish()
        }
        else
            showToast("Uno o más campos están vacíos")
    }

    fun cancel(view: View) {
        setResult(Activity.RESULT_CANCELED,Intent())
        finish()
    }

    fun showToast(message:String){
        Toast.makeText(applicationContext,message, Toast.LENGTH_SHORT).show()
    }
}
