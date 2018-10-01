package torres.gabriel.security2

import android.app.backup.BackupManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.IOException
import java.io.RandomAccessFile

class MainActivity : AppCompatActivity() {

    object sDataLock{

    }

    private val TOP_SCORES = "scores"

    private var backupManager: BackupManager? = null
    private var prefs: SharedPreferences? = null
    private var edit: SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = getSharedPreferences(BackupData.PREFS_TEST, Context.MODE_PRIVATE)
        edit = prefs?.edit()
        backupManager = BackupManager(this)

        if (showData().isNotEmpty()) {
            show.isEnabled = true
        }
        save.setOnClickListener({
            saveData("save", enter_data.text.toString())
            show.isEnabled = true


            try {
                synchronized(MainActivity.sDataLock) {
                    val dataFile = File(filesDir, TOP_SCORES)
                    val raFile = RandomAccessFile(dataFile, "rw")
                    raFile.writeChars(enter_data.text.toString())
                }
            } catch (e: IOException) {
                Log.e("ERROR", "Unable to write to file")
            }
        })
        show.setOnClickListener({
            textView.setText(showData())
        })
        //show.isEnabled = true




    }


    fun openOther(v: View){
        val intent = Intent()
        intent.action = "torres.gabriel.security.SECURITY"
        intent.addCategory("android.intent.category.DEFAULT")
        startActivity(intent)
    }

    private fun saveData(key: String, value: String) {
        edit?.putString(key, value)
        edit?.commit()
        Log.d("Test", "Calling backup...")
        backupManager?.dataChanged()
    }

    private fun showData(): String {
        return prefs?.getString("save", "").toString()
    }
}
