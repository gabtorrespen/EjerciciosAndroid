package torres.gabriel.security

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contextShare = createPackageContext("torres.gabriel.permission", Context.CONTEXT_IGNORE_SECURITY)
        val id = 0x7f0d0028
        var stringShare = contextShare.resources.getString(id)
        Toast.makeText(this,"SHARE: $id",Toast.LENGTH_LONG).show()
    }
}
