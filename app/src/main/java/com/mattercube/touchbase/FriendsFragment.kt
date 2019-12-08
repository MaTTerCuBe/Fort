package com.mattercube.touchbase


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

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


}
