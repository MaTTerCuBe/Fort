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
    val total       = "_total"
    val date        = "_date"
    val description = "_description"
    val temporary   = "_temporary"

    var key = ""

    // Default values
    val defaultTotal    =   "0"
    val defaultName     =   "default"
    val defaultNumber   =   "default"
    val defaultDate     =   "default"
    val defaultDescription    =   "default"

    val senpai = "Preference-Senpai!!"

    /* ----- Retrieving Data Methods ----- */

    fun getPersonName(personNum: Int?): String? {
        key = friend + personNum
        return preferences.getString(key, defaultName)
    }

    fun getPersonNumber(personNum: Int?): String? {
        key = friend + personNum + number
        return preferences.getString(key, defaultNumber)
    }

    fun getPersonTotalEntries(personNum: Int?): String? {
        key = friend + personNum + total
        return preferences.getString(key, defaultTotal)
    }

    fun getPersonLastDate(personNum: Int?): String? {
        val currentTotal = getPersonTotalEntries(personNum)

        key = friend + personNum + entry + currentTotal + date
        return preferences.getString(key, defaultDate)
    }

    fun getPersonLastDescription(personNum: Int?): String? {
        val currentTotal = getPersonTotalEntries(personNum)

        key = friend + personNum + entry + currentTotal + description
        return preferences.getString(key, defaultDescription)
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

    fun incrementPersonTotalEntries(personNum: Int?) {
        val newTotal: Int = getPersonTotalEntries(personNum)!!.toInt() + 1

        Log.i(senpai, "chcking newTotal in incrementTotal: $newTotal")

        key = friend + personNum + total

        Log.i(senpai, "chcking key in incrementTotal: $key")

        preferences.edit().putString(key, newTotal.toString()).apply()
    }

    fun setPersonEntryDate(personNum: Int?, enteredEntry: Int?, enteredDate: String?) {

        Log.i(senpai, "chcking personNum in setDate: $personNum")
        Log.i(senpai, "chcking enteredEntry in setDate: $enteredEntry")
        Log.i(senpai, "chcking enteredDate in setDate: $enteredDate")

        key = friend + personNum + entry + enteredEntry + date

        Log.i(senpai, "chcking key in setDate: $key")

        preferences.edit().putString(key, enteredDate).apply()
    }

    fun setPersonEntryDescription(personNum: Int?, enteredEntry: Int?, enteredDescription: String?) {

        Log.i(senpai, "chcking personNum in setDescrpt: $personNum")
        Log.i(senpai, "chcking enteredEntry in setDescrpt: $enteredEntry")
        Log.i(senpai, "chcking enteredDescription in setDescrpt: $enteredDescription")

        key = friend + personNum + entry + enteredEntry + description

        Log.i(senpai, "chcking key in setDescrpt: $key")

        preferences.edit().putString(key, enteredDescription).apply()
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

    fun getTempDescription(): String? {
        key = friend + temporary + description
        return preferences.getString(key, defaultDescription)
    }


    /*************************** TEMPORARY DATA FUNCTIONS ***************************/

    /* ----- Setting Temp Data ----- */
    fun setTempName(enteredName: String) {
        key = friend + temporary
        preferences.edit().putString(key, enteredName).apply()
    }

    fun setTempDate(enteredDate: String) {
        key = friend + temporary + date
        preferences.edit().putString(key, enteredDate).apply()
    }

    fun setTempDescription(enteredDescription: String) {
        key = friend + temporary + description
        preferences.edit().putString(key, enteredDescription).apply()
    }

    // Saving Temp Data to a Permanent Person
    fun saveTempData() {
        val personNum = getTempPerson()!!.toInt()

        Log.i(senpai, "chcking personNum in saveTemp: $personNum")

        incrementPersonTotalEntries(personNum)
        val currentEntry: Int = getPersonTotalEntries(personNum)!!.toInt()

        Log.i(senpai, "chcking currentEntry in saveTemp: $currentEntry")

        setPersonEntryDate(personNum, currentEntry, getTempDate())
        setPersonEntryDescription(personNum, currentEntry, getTempDescription())
        clearTempData()
    }

    // Clearing Temp Data
    fun clearTempData() {
        setTempName(defaultName)
        setTempDate(defaultDate)
        setTempDescription(defaultDescription)
    }
}