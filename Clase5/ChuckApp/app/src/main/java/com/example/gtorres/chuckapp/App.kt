package com.example.gtorres.chuckapp

import android.app.Application
import android.widget.Toast

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    init {
        instance = this
    }

    fun showToast(message:String){
        Toast.makeText(applicationContext,message, Toast.LENGTH_SHORT).show()
    }
}