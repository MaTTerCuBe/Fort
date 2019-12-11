package com.mattercube.touchbase


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_touch.*
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.*

/**
 * Created by Roman Khamov
 * Inspired by:
 *      - Atif Pervaiz from https://devofandroid.blogspot.com/2018/11/dialer-intent-android-studio-kotlin.html
 *      - ChiragP Patel from https://medium.com/@chiragpatel_52497/send-sms-from-android-application-a8a9c1ada8b7
 *      - Soleil from https://stackoverflow.com/questions/47006254/how-to-get-current-local-date-and-time-in-kotlin
 */
class TouchFragment : Fragment() {

    private lateinit var buttonPressed: touchFragmentButtons

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_touch, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        buttonPressed = context as touchFragmentButtons

        val today = getCurrentDate()

        friend_name1.text = App.savedData!!.getPersonName(1)
        friend_name2.text = App.savedData!!.getPersonName(2)

        touch_counter1.text = checkTimeSinceLastTouchBase(1, today).toString()
        touch_counter2.text = checkTimeSinceLastTouchBase(2, today).toString()

        call_icon1.setOnClickListener {
            makeCall(1)
        }

        text_icon1.setOnClickListener {
            sendSMS(1)
        }

        log_event1.setOnClickListener {
            buttonPressed.tappedLogEvent()
        }

        call_icon2.setOnClickListener {
            makeCall(2)
        }

        text_icon2.setOnClickListener {
            sendSMS(2)
        }

        log_event2.setOnClickListener {
            buttonPressed.tappedLogEvent()
        }
    }

    fun getCurrentDate(): String? {
        val simplifiedFormat = SimpleDateFormat("yyyy-dd-M", Locale.US)
        val currentDate = simplifiedFormat.format(Date())

        //Log.i("PLEASE!!!!", "C = $currentDate ")
        return currentDate
    }

    // TO DO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    fun setFriendsInNeedOfTouchBase() {

        val hardCodedFriendsPersonNumbers = arrayOf(1, 2, 3, 4, 5)


    }

    fun checkTimeSinceLastTouchBase(personNum: Int?, currentDate: String?): Long? {

        val lastTouchDate = App.savedData!!.getPersonLastDateFormatted(personNum)

        val lastTouchDateInDays = convertToDaysOnly(lastTouchDate)
        val currentDateInDays = convertToDaysOnly(currentDate)

        val differenceInDays = currentDateInDays - lastTouchDateInDays

        Log.i("Oh boy do I hope this works", "difference in days: $differenceInDays")
        return differenceInDays
    }

    fun convertToDaysOnly(enteredDate: String?): Long {

        val logPoint = "Logging in convertToDaysOnly"
        Log.i(logPoint, "enteredDate: $enteredDate")

        var totalDays: Long = 0
        var daysSoFar: Long
        var years: Long
        var months: Long
        var days: Long
        var leapyears: Long
        var isLeapYear = false

        // These are based off of the format "YYYY-MM-dd"
        val yearSubStringStart = 0
        val yearSubStringEnd = 4
        val monthSubStringStart = 5
        val monthSubStringEnd = 7
        val daySubStringStart = 8

        val enteredYear = enteredDate!!.substring(yearSubStringStart, yearSubStringEnd)
        Log.i(logPoint, "enteredYear = $enteredYear")

        years = enteredYear.toLong()

        daysSoFar = years * 365

        if ((years % 4) == 0.toLong())   isLeapYear = true

        leapyears = years / 4

        daysSoFar += leapyears

        totalDays += daysSoFar

        val enteredMonths = enteredDate!!.substring(monthSubStringStart, monthSubStringEnd)
        Log.i(logPoint, "enteredMonths: $enteredMonths")

        months = enteredMonths.toLong()

        when (months) {
            0.toLong()   ->  daysSoFar = 0
            1.toLong()   ->  daysSoFar = 31
            2.toLong()   ->  daysSoFar = 59
            3.toLong()   ->  daysSoFar = 90
            4.toLong()   ->  daysSoFar = 120
            5.toLong()   ->  daysSoFar = 151
            6.toLong()   ->  daysSoFar = 182
            7.toLong()   ->  daysSoFar = 213
            8.toLong()   ->  daysSoFar = 243
            9.toLong()   ->  daysSoFar = 274
            10.toLong()   ->  daysSoFar = 304
            11.toLong()   ->  daysSoFar = 335
        }

        if (isLeapYear) ++daysSoFar

        totalDays += daysSoFar

        val enteredDays = enteredDate!!.substring(daySubStringStart)
        Log.i(logPoint, "enteredDays: $enteredDays")

        days = enteredDays.toLong()

        totalDays += days

        return totalDays
    }

    fun makeCall(toPersonNum: Int?) {

        val numberOfFriend = App.savedData!!.getPersonNumber(toPersonNum)

        val dialInent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(numberOfFriend)))

        val shareIntent = Intent.createChooser(dialInent, null)

        startActivity(shareIntent)
    }

    fun sendSMS(toPersonNum: Int?)
    {
        val numberOfFriend = App.savedData!!.getPersonNumber(toPersonNum)

        val messageIntent = Intent(Intent.ACTION_SENDTO, Uri.parse(numberOfFriend))

        messageIntent.putExtra("sms_body", "We need to touch base.\n")

        val shareIntent = Intent.createChooser(messageIntent, null)

        startActivity(shareIntent)
    }

    interface touchFragmentButtons {
        fun tappedLogEvent()
    }
}
