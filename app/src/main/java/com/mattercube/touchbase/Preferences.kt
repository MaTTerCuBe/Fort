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
    val temporary   = "_temporary"

    var key = ""

    // Default values
    val defaultName     =   "default"
    val defaultNumber   =   "default"
    val defaultDate     =   "default"
    val defaultEntry    =   "default"


    /* ----- Retrieving Data Methods ----- */

    fun getPersonName(personNum: Int?): String? {
        key = friend + personNum
        return preferences.getString(key, defaultName)
    }

    fun getPersonNumber(personNum: Int?): String? {
        key = friend + personNum + number
        return preferences.getString(key, defaultNumber)
    }

    fun getPersonLastDate(personNum: Int?): String? {
        key = friend + personNum + entry + last + date
        return preferences.getString(key, defaultDate)
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

    /* ----- Retrieving Temp Data ----- */
    fun getTempPerson(): String? {
        key = friend + temporary
        return preferences.getString(key, defaultName)
    }

    fun getTempDate(): String? {
        key = friend + temporary + date
        return preferences.getString(key, defaultDate)
    }

    fun getTempEntry(): String? {
        key = friend + temporary + entry
        return preferences.getString(key, defaultEntry)
    }

    /* ----- Setting Temp Data ----- */
    fun setTempName(enteredName: String) {
        key = friend + temporary
        preferences.edit().putString(key, enteredName).apply()
    }

    fun setTempDate(enteredDate: String) {
        key = friend + temporary + date
        preferences.edit().putString(key, enteredDate).apply()
    }

    fun setTempEntry(enteredEntry: String) {
        key = friend + temporary + entry
        preferences.edit().putString(key, enteredEntry).apply()
    }

    // Clearing Temp Data
    fun clearTempData() {
        setTempName(defaultName)
        setTempDate(defaultDate)
        setTempEntry(defaultEntry)
    }
}