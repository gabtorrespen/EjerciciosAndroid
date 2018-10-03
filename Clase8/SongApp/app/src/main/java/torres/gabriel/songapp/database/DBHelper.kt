package torres.gabriel.songapp.database

import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import torres.gabriel.songapp.App
import torres.gabriel.songapp.model.Song

class DBHelper  : OrmLiteSqliteOpenHelper(App.instance,
        DBNAME, null, DBVERSION) {

    companion object {
        var DBVERSION = 1
        var DBNAME = "song.db"

        //Song
        val SONG_TABLE_NAME = "Song"
        val SONG_COLUMN_ID = "id"
    }

    override fun onCreate(database: SQLiteDatabase?,
                          connectionSource: ConnectionSource?) {
        TableUtils.createTableIfNotExists(connectionSource, Song::class.java)
    }

    override fun onUpgrade(database: SQLiteDatabase?,
                           connectionSource: ConnectionSource?,
                           oldVersion: Int, newVersion: Int) {
        TableUtils.dropTable<Song, Any>(connectionSource, Song::class.java, true)
        onCreate(database, connectionSource)
    }

}