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

    /*  For now, this is unecessary, using one big ConstraintLayout instead of multiple mini-fragments
    // hard-coding in our friend entry fragments
    private val friend1 = FriendListEntryFragment()
    private val friend2 = FriendListEntryFragment()
    private val friend3 = FriendListEntryFragment()
    private val friend4 = FriendListEntryFragment()
    private val friend5 = FriendListEntryFragment()
    private val friend6 = FriendListEntryFragment()

    private val manager = fragmentManager*/

    private lateinit var optionSelected: FriendOptions

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends, container, false)

        /*manager?.beginTransaction()   See above comment about using ConstraintLayout
            ?.add(R.id.friend_entry1, friend1)
            ?.add(R.id.friend_entry2, friend2)
            ?.add(R.id.friend_entry3, friend3)
            ?.add(R.id.friend_entry4, friend4)
            ?.add(R.id.friend_entry5, friend5)
            ?.add(R.id.friend_entry6, friend6)
            ?.commit()*/
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        optionSelected =context as FriendOptions

        options_1.setOnClickListener {
            optionSelected.option1Tapped()
        }

        options_2.setOnClickListener {
            optionSelected.option2Tapped()
        }

        options_3.setOnClickListener {
            optionSelected.option3Tapped()
        }

        options_4.setOnClickListener {
            optionSelected.option4Tapped()
        }

        options_5.setOnClickListener {
            optionSelected.option5Tapped()
        }
    }
    interface FriendOptions {
        fun option1Tapped()
        fun option2Tapped()
        fun option3Tapped()
        fun option4Tapped()
        fun option5Tapped()
    }
}
