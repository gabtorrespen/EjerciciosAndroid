package com.example.gtorres.chuckapp.Utils

import android.net.Uri
import com.example.gtorres.chuckapp.Database.DBHelper
import com.example.gtorres.chuckapp.Providers.AppProvider

class Constantes {

    companion object {
        //URIS
        val JOKE_URI = Uri.parse("content://${AppProvider.AUTHORITY}/${DBHelper.JOKE_TABLE_NAME}")
    }

}