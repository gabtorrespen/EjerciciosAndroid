package torres.gabriel.saveddataapp

import android.content.Context
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.lang.Exception

class FileExternalActivity : AppCompatActivity() {

    var externalFile: File? = null
    val TEMP_FILE_NAME = "test.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_external)

        externalFile = File(Environment.getExternalStorageDirectory().path+"/"+TEMP_FILE_NAME)

        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                ==PackageManager.PERMISSION_GRANTED){
            guardar()
            leer()
        }
        else{
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),0)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(permissions[0].equals(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            guardar()
            leer()
        }
        else{
            Toast.makeText(this,"No concedió el permiso",Toast.LENGTH_LONG).show()
            finish()
        }

    }

    private fun guardar() {

        GuardarAsyncTask(this).execute(TEMP_FILE_NAME)

//        val writer: FileWriter
//        writer = FileWriter(externalFile)
//        writer.write("texto de prueba")
//        writer.close()
//        Toast.makeText(baseContext,"Creado", Toast.LENGTH_LONG).show()
    }

    private fun leer() {
        val feReader = FileReader(externalFile)
        val breadCrumbClickListener = BufferedReader(feReader)
        val text = breadCrumbClickListener.use(BufferedReader::readText)
        Toast.makeText(baseContext,"Leido: $text", Toast.LENGTH_LONG).show()

    }


    class GuardarAsyncTask:AsyncTask<String,Void,Boolean>{

        val mContext: Context

        constructor(context: Context){
            this.mContext = context
        }

        override fun doInBackground(vararg params: String?): Boolean {
            return guardar(File(Environment.getExternalStorageDirectory().path+"/"+params[0]))
        }

        override fun onPostExecute(result: Boolean?) {
            super.onPostExecute(result)

            if(result==true)
                Toast.makeText(mContext,"Creado", Toast.LENGTH_LONG).show()
            else {
                Toast.makeText(mContext, "No se pudo crear", Toast.LENGTH_LONG).show()
            }
        }

        private fun guardar(file:File):Boolean {
            try {
                val writer: FileWriter
                writer = FileWriter(file)
                writer.write("texto de prueba")
                writer.close()
                return true
            }
            catch (e: Exception){
                return false
            }
        }
    }
}
