package com.mattercube.touchbase


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_friends.*

/**
 * A simple [Fragment] subclass.
 */
class FriendsFragment : Fragment() {

    private lateinit var optionSelected: FriendOptions
    var previousFragment: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends, container, false)

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        previousFragment = arguments?.getString("request_from_fragment")

        populateNamesAndDates()

        optionSelected = context as FriendOptions

        options_1.setOnClickListener {
            optionSelected.optionTapped(1, previousFragment)
        }

        options_2.setOnClickListener {
            optionSelected.optionTapped(2, previousFragment)
        }

        options_3.setOnClickListener {
            optionSelected.optionTapped(3, previousFragment)
        }

        options_4.setOnClickListener {
            optionSelected.optionTapped(4, previousFragment)
        }

        options_5.setOnClickListener {
            optionSelected.optionTapped(5, previousFragment)
        }
    }
    interface FriendOptions {
        fun optionTapped(friendSelected: Int, requestMadeFromFragment: String?)
    }

    fun populateNamesAndDates() {

        user_name_1.text = App.savedData!!.getPersonName(1)
        last_touch_1.text = App.savedData!!.getPersonLastDate(1)

        user_name_2.text = App.savedData!!.getPersonName(2)
        last_touch_2.text = App.savedData!!.getPersonLastDate(2)

        user_name_3.text = App.savedData!!.getPersonName(3)
        last_touch_3.text = App.savedData!!.getPersonLastDate(3)

        user_name_4.text = App.savedData!!.getPersonName(4)
        last_touch_4.text = App.savedData!!.getPersonLastDate(4)

        user_name_5.text = App.savedData!!.getPersonName(5)
        last_touch_5.text = App.savedData!!.getPersonLastDate(5)
    }
}
