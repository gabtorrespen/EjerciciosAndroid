package com.example.gtorres.chuckapp.Providers

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import com.example.gtorres.chuckapp.Database.DBHelper

class AppProvider: ContentProvider()  {

    private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    private var myDB: DBHelper? = null

    companion object {
        val AUTHORITY = "com.example.gtorres.chuckapp.Providers.AppProvider"
        val JOKE = 1
        val JOKE_ID = 2
    }

    init {
        sUriMatcher.addURI(AUTHORITY, DBHelper.JOKE_TABLE_NAME, JOKE)
        sUriMatcher.addURI(AUTHORITY, "${DBHelper.JOKE_TABLE_NAME}/#", JOKE_ID)
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
            JOKE_ID -> {
                queryBuilder.tables = DBHelper.JOKE_TABLE_NAME
                queryBuilder.appendWhere(DBHelper.JOKE_COLUMN_ID + "="
                        + uri!!.lastPathSegment)
            }
            JOKE -> {
                queryBuilder.tables = DBHelper.JOKE_TABLE_NAME
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
            JOKE -> {
                table = DBHelper.JOKE_TABLE_NAME
            }
            else -> throw IllegalArgumentException("Unknown URI")
        }
        val id = sqlDB.insert(table, null, cv)
        context.contentResolver.notifyChange(uri, null)
        return Uri.parse("$table/$id")
    }

    override fun update(p0: Uri?, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        throw IllegalArgumentException("Unknown URI")
    }

    override fun delete(uri: Uri?, where: String?, whereArgs: Array<out String>?): Int {
        val uriType = sUriMatcher.match(uri)
        val sqlDB = myDB!!.writableDatabase
        var selection = where
        val table: String
        when (uriType) {
            JOKE -> {
                table = DBHelper.JOKE_TABLE_NAME
            }
            JOKE_ID -> {
                table = DBHelper.JOKE_TABLE_NAME
                selection = DBHelper.JOKE_COLUMN_ID + "=" + uri!!.lastPathSegment
            }
            else -> throw IllegalArgumentException("Unknown URI")
        }
        return sqlDB.delete(table, selection, whereArgs)
    }

    override fun getType(p0: Uri?): String {
        throw IllegalArgumentException("Unknown URI")
    }

}