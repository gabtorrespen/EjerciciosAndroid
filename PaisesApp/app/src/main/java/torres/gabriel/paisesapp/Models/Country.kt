package torres.gabriel.paisesapp.Models

import java.io.Serializable

class Country(name: String, city: String, description: String) : Serializable  {

    var name:String?= name
    var city:String? = city
    var description:String? = null
    var flag:Int? =null

    constructor(name: String, city: String, flag: Int) : this(name,city,"") {
        this.flag = flag
    }
}