package com.mattercube.touchbase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
//Made by Roman Khamov
class MainActivity : AppCompatActivity(), MainMenuFragment.mainMenuOptions,
                        FriendsFragment.FriendOptions, ContactInfoFragment.saveButton,
                        EventFragment.EventOptions  {

    // Fragments
    private val mainMenuFragment = MainMenuFragment()
    private val friendsFragment = FriendsFragment()
    private val eventFragment = EventFragment()
    private val touchFragment = TouchFragment()
    private val contactInfoFragment = ContactInfoFragment()

    // String Names for fragments
    val mainMenuFragmentTag = "MainMenu"
    val eventFragmentTag    = "EventFragment"

    private val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        Log.i("activity_main", "About to set content view")
        setContentView(R.layout.activity_main)

        Log.i("activity_main", "About begin manager shinanigins")
        manager.beginTransaction()
            .replace(R.id.main_screen, mainMenuFragment)
            .commit()

    }

    /* ----- Interfaces for MainMenuFragment ----- */
    override fun logEventTapped() {
        var bundle = Bundle()
        val aFriendwasSelected = false

        bundle.putBoolean("a_friend_was_selected", aFriendwasSelected)

        eventFragment.arguments = bundle

        manager.beginTransaction()
            .replace(R.id.main_screen, eventFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun touchBaseTapped() {
        manager.beginTransaction()
            .replace(R.id.main_screen, touchFragment)
            .addToBackStack(null)
            .commit()    }

    override fun friendsTapped() {
        val bundle = Bundle()
        bundle.putString("request_from_fragment", mainMenuFragmentTag)

        friendsFragment.arguments = bundle

        manager.beginTransaction()
            .replace(R.id.main_screen, friendsFragment)
            .addToBackStack(null)
            .commit()
    }

    /* ----- Interfaces for FriendsFragment ----- */
    override fun optionTapped(friendSelected: Int, requestMadeFromFragment: String?) {
        val bundle = Bundle()
        bundle.putInt("selected_friend", friendSelected)

        if (requestMadeFromFragment == mainMenuFragmentTag) {
            contactInfoFragment.arguments = bundle

            manager.beginTransaction()
                .replace(R.id.main_screen, contactInfoFragment)
                .commit()
        }

        if (requestMadeFromFragment == eventFragmentTag) {
            val aFriendwasSelected = true
            bundle.putBoolean("a_friend_was_selected", aFriendwasSelected)

            eventFragment.arguments = bundle

            manager.beginTransaction()
                .replace(R.id.main_screen, eventFragment)
                .commit()
        }
    }

    /* ----- Interface for ContactInfoFragment ----- */
    override fun saveButtonPressed() {
        manager.beginTransaction()
            .remove(contactInfoFragment)
            .replace(R.id.main_screen, friendsFragment)
            .commit()
    }

    /* ----- Interface EventFragment ----- */
    override fun selectTapped() {
        val bundle = Bundle()
        bundle.putString("request_from_fragment", eventFragmentTag)

        friendsFragment.arguments = bundle

        manager.beginTransaction()
            .replace(R.id.main_screen, friendsFragment)
            .addToBackStack(null)
            .commit()
    }
}
