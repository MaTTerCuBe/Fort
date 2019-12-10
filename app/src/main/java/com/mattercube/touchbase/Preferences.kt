package com.mattercube.touchbase

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

/**
 * Written by Roman Khamov,
 * inspired by Ben Deitch from https://blog.teamtreehouse.com/making-sharedpreferences-easy-with-kotlin
 */

class Preferences (context: Context) {

    val PREFERENCES_FILENAME = context.resources.getString(R.string.shared_preferences_file_name)
    val preferences: SharedPreferences = context.getSharedPreferences(PREFERENCES_FILENAME, Context.MODE_PRIVATE)

    // Re-Occuring Strings in preference file
    val friend      = "friend_"
    val number      = "_number"
    val entry       = "_entry"
    val last        = "_last"
    val total       = "_total"
    val date        = "_date"
    val description = "_description"

    var key = ""

    // Default values
    val defName =   "A Test"
    val defNumber   = "1234567890"
    val defDate     = "25 Oct 2019"


    /* ----- Retrieving Data Methods ----- */

    fun getPersonName(personNum: Int?): String? {
        key = friend + personNum
        return preferences.getString(key, defName)
    }

    fun getPersonNumber(personNum: Int?): String? {
        key = friend + personNum + number
        return preferences.getString(key, defNumber)
    }

    fun getPersonLastDate (personNum: Int?): String? {
        key = friend + personNum + entry + last + date
        return preferences.getString(key, defDate)
    }

    /* ----- Setting Data Methods ----- */
    fun setPersonName(personNum: Int?, enteredName: String) {
        key = friend + personNum
        preferences.edit().putString(key, enteredName).apply()
    }

    fun setPersonNumber(personNum: Int?, enteredNumber: String) {
        key = friend + personNum + number
        preferences.edit().putString(key, enteredNumber).apply()
    }
}