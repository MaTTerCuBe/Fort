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

        val emptyFieldsMessage  = resources.getString(R.string.empty_fields_warning)
        val entryUpdated        = resources.getString(R.string.entry_saved)
        var dateSelected = ""

        checkIfOptionAlreadySelected()

        clear_icon.setOnClickListener {
            App.savedData!!.clearTempData()
            optionSelected.leaveEventFragmentGoBackToMainMenu()
        }

        select_plus_icon.setOnClickListener { // This is the select TextView from the XML layout
            optionSelected.selectTapped()
        }

        date_plus_icon.setOnClickListener {
            showCalendar()
        }

        calendarView.setOnDateChangeListener { calendarView, year, month, day ->
            dateSelected = day.toString() + " " + changeMonthNumberToString(month) +
                            " " + year.toString()

            App.savedData!!.setTempDate(dateSelected)

            Toast.makeText(context, dateSelected, Toast.LENGTH_SHORT).show()

            hideCalendar()
            changeDateToTempDate()

        }

        description_plus_icon.setOnClickListener {
            var aFriendWasAlreadySelected = false
            var selectViewDefaultValue = resources.getString(R.string.select)
            var theSelectedFriend = arguments!!.getInt("selected_friend")

            if (select.text != selectViewDefaultValue) {
                aFriendWasAlreadySelected =  true
            }

            optionSelected.descriptionTapped()
        }

        save.setOnClickListener {
            val saveButtonCurrentStatus = save.currentTextColor

            when (saveButtonCurrentStatus) {

                resources.getColor(R.color.greyed)  -> {
                    Toast.makeText(
                        context, emptyFieldsMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                resources.getColor(R.color.white)   ->  {
                    App.savedData!!.saveTempData()
                    Toast.makeText(context, entryUpdated, Toast.LENGTH_SHORT).show()

                    // Testing
                    var testDate = App.savedData!!.getPersonLastDate(1)
                    var testDescription = App.savedData!!.getPersonLastDescription(1)

                    Log.i("NOTICE ME SENPAI!!!", "Date: $testDate, Description: $testDescription#")

                    optionSelected.leaveEventFragmentGoBackToMainMenu()
                }
            }
        }
    }

    private fun checkIfOptionAlreadySelected() {

        var isNameSet           = false
        var isDateSet           = false
        var isDescriptionSet    = false

        // Check if friend has been set
        val tempName = App.savedData!!.getTempPerson()

        if (tempName != "default") { // TO DO: Change all of the default values in Preferences.kt to string resources
            changeSelectToName(tempName!!.toInt())
            isNameSet = true
        }

        // Check if Date has been set
        val tempDate = App.savedData!!.getTempDate()

        if (tempDate != "default") { // TO DO: Change all of the default values in Preferences.kt to string resources
            changeDateToTempDate()
            isDateSet = true
        }

        // Check if Description has been set
        val tempEntry = App.savedData!!.getTempDescription()

        if (tempEntry != "default") { // TO DO: Change all of the default values in Preferences.kt to string resources
            changeDescriptionStatus()
            isDescriptionSet = true
        }

        if (isNameSet && isDateSet && isDescriptionSet) {
            save.setTextColor(resources.getColor(R.color.white))
        }
    }

    private fun changeSelectToName(personNum: Int?){
        select.text = App.savedData!!.getPersonName(personNum)
        select.setTextColor(resources.getColor(R.color.greyed))
        select_plus_icon.visibility = View.INVISIBLE
    }

    private fun changeDateToTempDate() {
        date_plus_icon.visibility = View.INVISIBLE
        date.setTextColor(resources.getColor(R.color.greyed))
        date.text = App.savedData!!.getTempDate()
    }

    private fun changeDescriptionStatus() {
        description_plus_icon.visibility = View.INVISIBLE
        description.setTextColor(resources.getColor(R.color.greyed))
    }

    private fun showCalendar() {

        Log.i(resources.getString(R.string.notice_me), "Changing visibility, was: ${calendarView.visibility}")
        calendarView.visibility = View.VISIBLE
        Log.i(resources.getString(R.string.notice_me), "Now is: ${calendarView.visibility}")


        val descriptionParams = description.layoutParams as ConstraintLayout.LayoutParams
        descriptionParams.topToBottom = calendarView.id
    }

    private fun hideCalendar() {

        Log.i(resources.getString(R.string.notice_me), "Changing visibility, should be GONE")
        calendarView.visibility = View.GONE
        Log.i(resources.getString(R.string.notice_me), "Is acturllY ${calendarView.visibility}")

        val descriptionParams = description.layoutParams as ConstraintLayout.LayoutParams
        descriptionParams.topToBottom = date.id

    }

    private fun changeMonthNumberToString(monthNumber: Int?): String {

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
        fun descriptionTapped()
        fun leaveEventFragmentGoBackToMainMenu()
    }
}
