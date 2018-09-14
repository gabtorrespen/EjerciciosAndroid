package com.example.gtorres.chuckapp.Models

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.io.Serializable
import java.util.ArrayList

@DatabaseTable
class Joke:Serializable  {
    @DatabaseField (generatedId = true)
    var id:Int? = null
    @DatabaseField
    var joke: String? = null
    @DatabaseField
    var categories:String? = null
}