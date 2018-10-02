package torres.gabriel.songapp.util

import android.net.Uri
import torres.gabriel.songapp.database.DBHelper
import torres.gabriel.songapp.provider.AppProvider

class Constants {

    companion object {

        //API
        const val BASE_URL = "http://plataforma.visionsatelital.co:9080/"
        const val API_SONG = BASE_URL+"song/"

        //URIS
        val SONG_URI = Uri.parse("content://${AppProvider.AUTHORITY}/${DBHelper.SONG_TABLE_NAME}")
    }

}