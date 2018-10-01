package torres.gabriel.saveddataapp

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_file_internal.*
import java.io.*

class FileInternalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_internal)

        crearArchivo()

        leerArchivo()

    }



    private fun crearArchivo() {
        var oStream: FileOutputStream = openFileOutput("Text.txt", Context.MODE_PRIVATE)

        var writer:OutputStreamWriter = OutputStreamWriter(oStream)
        writer.write("Esto es una prueba xD")
        writer.flush()
        writer.close()
    }

    private fun leerArchivo() {
        val iStream: FileInputStream = openFileInput("Text.txt")

        if(iStream != null) {
            val stringBuffer = iStream.bufferedReader().use(BufferedReader::readText)
            textViewFile.text = stringBuffer
        }
    }
}
