package torres.gabriel.songapp.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import torres.gabriel.songapp.database.DBHelper

class AppProvider: ContentProvider()  {

    private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    private var myDB: DBHelper? = null

    companion object {
        val AUTHORITY = "torres.gabriel.songapp.provider.AppProvider"
        val SONG = 1
        val SONG_ID = 2
    }

    init {
        sUriMatcher.addURI(AUTHORITY, DBHelper.SONG_TABLE_NAME, SONG)
        sUriMatcher.addURI(AUTHORITY, "${DBHelper.SONG_TABLE_NAME}/#", SONG_ID)
    }

    override fun onCreate(): Boolean {
        myDB = DBHelper()
        return true
    }

    override fun query(uri: Uri?,
                       columns: Array<out String>?,
                       selection: String?,
                       selectionArgs: Array<out String>?,
                       orderBy: String?): Cursor {

        val queryBuilder = SQLiteQueryBuilder()

        val uriType = sUriMatcher.match(uri)

        when (uriType) {
            SONG_ID -> {
                queryBuilder.tables = DBHelper.SONG_TABLE_NAME
                queryBuilder.appendWhere(DBHelper.SONG_COLUMN_ID + "="
                        + uri!!.lastPathSegment)
            }
            SONG -> {
                queryBuilder.tables = DBHelper.SONG_TABLE_NAME
            }
            else -> throw IllegalArgumentException("Unknown URI")
        }

        val cursor = queryBuilder.query(myDB?.readableDatabase,
                columns, selection, selectionArgs, null, null,
                orderBy)
        cursor.setNotificationUri(context.contentResolver,
                uri)
        return cursor
    }

    override fun insert(uri: Uri?, cv: ContentValues?): Uri {

        val table: String
        val uriType = sUriMatcher.match(uri)
        val sqlDB = myDB!!.writableDatabase

        when (uriType) {
            SONG -> {
                table = DBHelper.SONG_TABLE_NAME
            }
            else -> throw IllegalArgumentException("Unknown URI")
        }
        val id = sqlDB.insert(table, null, cv)
        context.contentResolver.notifyChange(uri, null)
        return Uri.parse("$table/$id")
    }

    override fun bulkInsert(uri: Uri?, values: Array<out ContentValues>?): Int {

        val table: String
        val uriType = sUriMatcher.match(uri)
        val sqlDB = myDB!!.writableDatabase

        when (uriType) {
            SONG -> {
                table = DBHelper.SONG_TABLE_NAME
            }
            else -> throw IllegalArgumentException("Unknown URI")
        }

        sqlDB.beginTransaction()

        try{
            for (cv in values!!){
                val newId = sqlDB.insert(table, null, cv)
            }
            context.contentResolver.notifyChange(uri, null)
            sqlDB.setTransactionSuccessful()
        }finally {
            sqlDB.endTransaction()
        }

        return values!!.size
    }

    override fun update(uri: Uri?, values: ContentValues?, where: String?, whereArgs: Array<out String>?): Int {
        val table: String
        val uriType = sUriMatcher.match(uri)
        val sqlDB = myDB!!.writableDatabase
        var selection = where

        when (uriType) {
            SONG_ID -> {
                table = DBHelper.SONG_TABLE_NAME
                selection = DBHelper.SONG_COLUMN_ID + "=" + uri!!.lastPathSegment
            }
            else -> throw IllegalArgumentException("Unknown URI")
        }
        val count = sqlDB.update(table, values, selection,whereArgs)
        context.contentResolver.notifyChange(uri, null)
        return count
    }

    override fun delete(uri: Uri?, where: String?, whereArgs: Array<out String>?): Int {
        val uriType = sUriMatcher.match(uri)
        val sqlDB = myDB!!.writableDatabase
        var selection = where
        val table: String
        when (uriType) {
            SONG -> {
                table = DBHelper.SONG_TABLE_NAME
            }
            SONG_ID -> {
                table = DBHelper.SONG_TABLE_NAME
                selection = DBHelper.SONG_COLUMN_ID + "=" + uri!!.lastPathSegment
            }
            else -> throw IllegalArgumentException("Unknown URI")
        }
        return sqlDB.delete(table, selection, whereArgs)
    }

    override fun getType(p0: Uri?): String {
        throw IllegalArgumentException("Unknown URI")
    }

}