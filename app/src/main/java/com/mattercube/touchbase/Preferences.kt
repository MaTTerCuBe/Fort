package com.mattercube.touchbase

import android.content.Context
import android.content.SharedPreferences

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

    // Default values
    val defName =   "A Test"
    val defNumber   = "1234567890"
    val defDate     = "25 Oct 2019"


    /* ----- Retrieving Data Methods ----- */

    fun getPersonName(personNum: Int): String? {
        return preferences.getString(friend + personNum, defName)
    }

    fun getPersonNumber(personNum: Int): String? {
        return preferences.getString(friend + personNum + number, defNumber)
    }

    fun getPersonLastDate (personNum: Int): String? {
        return preferences.getString(friend + personNum + entry + last + date, defDate)
    }

    /* ----- Setting Data Methods ----- */
    fun setPersonName(personNum: Int, enteredName: String) {
        preferences.edit().putString(friend + personNum, enteredName).apply()
    }

    fun setPersonNumber(personNum: Int, enteredNumber: String) {
        preferences.edit().putString(friend + personNum, enteredNumber).apply()
    }
}