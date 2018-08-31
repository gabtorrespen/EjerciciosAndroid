package torres.gabriel.gamesapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import torres.gabriel.gamesapp.Models.Game

class DetailActivity : AppCompatActivity() {

    var game: Game? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intentExtra = intent.getSerializableExtra("Game")
        game = if (intentExtra != null) intentExtra as Game else null
        
        if (game != null)
            showGame()
        else
            hideElements()
    }

    fun showGame(){
        txtDetailName.text = game!!.name
        txtDetailDeveloper.text = game!!.developer
        txtDetailDescription.text = game!!.description
    }

    fun hideElements(){
        txtDetailName.text = "Sin información"
        txtDetailDeveloper.visibility = View.INVISIBLE
        txtDetailDescription.visibility = View.INVISIBLE
    }
}
