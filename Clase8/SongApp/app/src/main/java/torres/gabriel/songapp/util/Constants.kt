package torres.gabriel.songapp.util

import android.net.Uri
import torres.gabriel.songapp.database.DBHelper

class Constants {

    companion object {
        //URIS
        val JOKE_URI = Uri.parse("content://${AppProvider.AUTHORITY}/${DBHelper.SONG_TABLE_NAME}")
    }

}