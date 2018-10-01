package torres.gabriel.songapp.model

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable
class Song {
    @DatabaseField(generatedId = true)
    var _id:Int? = null
    @DatabaseField
    var id: Int? = null
    @DatabaseField
    var nombre:String? = null
    @DatabaseField
    var autor:String? = null
    @DatabaseField
    var album:String? = null
}