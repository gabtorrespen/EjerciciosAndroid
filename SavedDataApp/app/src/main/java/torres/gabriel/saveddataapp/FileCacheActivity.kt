package torres.gabriel.saveddataapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class FileCacheActivity : AppCompatActivity() {


    var tempFile: File? = null
    val TEMP_FILE_NAME = "test.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_cache)

        tempFile = File(cacheDir.path+"/"+TEMP_FILE_NAME)

        guardar()
        leer()
    }

    private fun guardar() {
        val writer: FileWriter
        writer = FileWriter(tempFile)
        writer.write("texto de prueba")
        writer.close()
        Toast.makeText(baseContext,"Creado",Toast.LENGTH_LONG).show()
    }

    private fun leer() {
        val feReader = FileReader(tempFile)
        val breadCrumbClickListener = BufferedReader(feReader)
        val text = breadCrumbClickListener.use(BufferedReader::readText)
        Toast.makeText(baseContext,"Leido: $text",Toast.LENGTH_LONG).show()

    }
}
