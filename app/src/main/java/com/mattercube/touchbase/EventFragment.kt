package com.mattercube.touchbase


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        checkIfFriendAlreadySelected()

        select.setOnClickListener { // This is the select TextView from the XML layout
            optionSelected.selectTapped()
        }
    }

    fun checkIfFriendAlreadySelected() {
        if (arguments!!.getBoolean("a_friend_was_selected")) {
            changeSelectToName(arguments!!.getInt("selected_friend"))
        }
    }

    fun changeSelectToName(personNum: Int?){
        select.text = App.savedData!!.getPersonName(personNum)
    }

    interface EventOptions {
        fun selectTapped()
    }
}
