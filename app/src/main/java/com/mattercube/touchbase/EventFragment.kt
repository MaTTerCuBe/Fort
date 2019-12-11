package com.mattercube.touchbase


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.fragment_event.*

/**
 * Made by Roman Khamov
 */
class EventFragment : Fragment() {

    private lateinit var optionSelected: EventOptions

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        optionSelected = context as EventOptions

        calendarView.visibility = View.GONE

        var dateSelected = ""

        checkIfFriendAlreadySelected()

        select_plus_icon.setOnClickListener { // This is the select TextView from the XML layout
            optionSelected.selectTapped()
        }

        date_plus_icon.setOnClickListener {
            showCalendar()
        }

        calendarView.setOnDateChangeListener { calendarView, year, month, day ->
            dateSelected = day.toString() + " " + changeMonthNumberToString(month) +
                            " " + year.toString()

            Toast.makeText(context, dateSelected, Toast.LENGTH_SHORT).show()

            hideCalendar()
            date_plus_icon.visibility = View.INVISIBLE
            date.setTextColor(resources.getColor(R.color.greyed))
            date.text = dateSelected

        }
    }

    fun checkIfFriendAlreadySelected() {
        if (arguments!!.getBoolean("a_friend_was_selected")) {
            changeSelectToName(arguments!!.getInt("selected_friend"))
        }
    }

    fun changeSelectToName(personNum: Int?){
        select.text = App.savedData!!.getPersonName(personNum)
        select.setTextColor(resources.getColor(R.color.greyed))
        select_plus_icon.visibility = View.INVISIBLE
    }

    fun showCalendar() {

        Log.i(resources.getString(R.string.notice_me), "Changing visibility, was: ${calendarView.visibility}")
        calendarView.visibility = View.VISIBLE
        Log.i(resources.getString(R.string.notice_me), "Now is: ${calendarView.visibility}")


        val descriptionParams = description.layoutParams as ConstraintLayout.LayoutParams
        descriptionParams.topToBottom = calendarView.id
    }

    fun hideCalendar() {

        Log.i(resources.getString(R.string.notice_me), "Changing visibility, should be GONE")
        calendarView.visibility = View.GONE
        Log.i(resources.getString(R.string.notice_me), "Is acturllY ${calendarView.visibility}")

        val descriptionParams = description.layoutParams as ConstraintLayout.LayoutParams
        descriptionParams.topToBottom = date.id

    }

    fun changeMonthNumberToString(monthNumber: Int?): String {

        var monthString = ""

        when (monthNumber) {
            0   ->    monthString = "Jan"
            1   ->    monthString = "Feb"
            2   ->    monthString = "Mar"
            3   ->    monthString = "Apr"
            4   ->    monthString = "May"
            5   ->    monthString = "Jun"
            6   ->    monthString = "Jul"
            7   ->    monthString = "Aug"
            8   ->    monthString = "Sep"
            9   ->    monthString = "Oct"
            10  ->    monthString = "Nov"
            11  ->    monthString = "Dec"
        }

        return monthString
    }

    interface EventOptions {
        fun selectTapped()
    }
}
