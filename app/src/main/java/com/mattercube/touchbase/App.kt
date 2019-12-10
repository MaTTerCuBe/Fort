package com.mattercube.touchbase

import android.app.Application

/**
 *  Written by Roman Khamov
 *  Inspired by Ben Deitch from https://blog.teamtreehouse.com/making-sharedpreferences-easy-with-kotlin
 **/

val savedData: Preferences by lazy { App.savedData!! }

class App : Application() {

    companion object {
        var savedData: Preferences? = null
    }

    override fun onCreate() {
        savedData = Preferences(applicationContext)
        super.onCreate()
    }
}