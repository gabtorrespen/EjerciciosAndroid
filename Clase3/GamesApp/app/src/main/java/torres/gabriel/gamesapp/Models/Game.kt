package torres.gabriel.gamesapp.Models

import java.io.Serializable

class Game(id:Int,name: String, developer: String, description: String) : Serializable  {

    var id:Int? = id
    var name:String?= name
    var developer:String? = developer
    var description:String? = description
    var image:ByteArray? =null

//    constructor(name: String, city: String, flag: Int) : this(name,city,"") {
//        //this.flag = flag
//    }
}