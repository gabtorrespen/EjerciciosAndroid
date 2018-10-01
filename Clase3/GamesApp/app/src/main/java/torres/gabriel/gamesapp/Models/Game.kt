package torres.gabriel.gamesapp.Models

import java.io.Serializable

class Game(id:Int,name: String, developer: String, description: String) : Serializable  {

    var id:Int? = id
    var name:String?= name
    var developer:String? = developer
    var description:String? = description
    var image:ByteArray? =null
    var idImage:Int?=null

    constructor(id:Int,name: String, developer: String, description: String,idImage:Int)
            : this(id,name,developer,description) {
        this.idImage = idImage
    }
}