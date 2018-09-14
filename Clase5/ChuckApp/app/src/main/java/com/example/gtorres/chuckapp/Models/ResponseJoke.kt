package com.example.gtorres.chuckapp.Models

class ResponseJoke {

    var type:String? = null
    var value:ArrayList<ResponseIndividualJoke>? = null

    class ResponseIndividualJoke{
        var joke:String? = null
        var categories:ArrayList<String>? = null
    }

}