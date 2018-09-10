package com.example.gtorres.chuckapp.Database

import android.database.sqlite.SQLiteDatabase
import com.example.gtorres.chuckapp.App
import com.example.gtorres.chuckapp.Models.Joke
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils

class DBHelper  : OrmLiteSqliteOpenHelper(App.instance,
        DBNAME, null, DBVERSION) {

    companion object {
        var DBVERSION = 1
        var DBNAME = "joke.db"

        //Joke
        val JOKE_TABLE_NAME = "Joke"
        val JOKE_COLUMN_ID = "id"

    }

    override fun onCreate(database: SQLiteDatabase?,
                          connectionSource: ConnectionSource?) {
        TableUtils.createTableIfNotExists(connectionSource, Joke::class.java)
    }

    override fun onUpgrade(database: SQLiteDatabase?,
                           connectionSource: ConnectionSource?,
                           oldVersion: Int, newVersion: Int) {
        TableUtils.dropTable<Joke, Any>(connectionSource, Joke::class.java, true)
        onCreate(database, connectionSource)
    }

}