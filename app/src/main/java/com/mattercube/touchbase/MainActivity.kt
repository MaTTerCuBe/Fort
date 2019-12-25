package com.mattercube.touchbase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController

//Made by Roman Khamov
class MainActivity : AppCompatActivity(), MainMenuFragment.mainMenuOptions,
                        FriendsFragment.FriendOptions, ContactInfoFragment.ContactSaveButton,
                        EventFragment.EventOptions, DescriptionFragment.DescriptionSaveButton,
                        TouchFragment.touchFragmentButtons {

    // Fragments
    private val mainMenuFragment = MainMenuFragment()
    private val friendsFragment = FriendsFragment()
    private val eventFragment = EventFragment()
    private val touchFragment = TouchFragment()
    private val contactInfoFragment = ContactInfoFragment()
    private val descriptionFragment = DescriptionFragment()

    // String Names for fragments
    val mainMenuFragmentTag = "MainMenu"
    val eventFragmentTag    = "EventFragment"

    private val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        /*  Implementing NavFrag, temporarily suspended.

        Log.i("activity_main", "About to set content view")
        setContentView(R.layout.activity_main)

        Log.i("activity_main", "About begin manager shinanigins")
        manager.beginTransaction()
            .replace(R.id.main_screen, mainMenuFragment)
            .commit()
        */
    }

    /* ----- Interfaces for MainMenuFragment ----- */
    override fun logEventTapped() {
        val bundle = Bundle()
        val aFriendwasSelected = false

        bundle.putBoolean("a_friend_was_selected", aFriendwasSelected)

        eventFragment.arguments = bundle

        /*  Implementing NavFrag, temporarily suspended.

        manager.beginTransaction()
            .replace(R.id.main_screen, eventFragment)
            .addToBackStack(null)
            .commit()
        */
    }

    override fun touchBaseTapped() {

        /*  Implementing NavFrag, temporarily suspended.
        manager.beginTransaction()
            .replace(R.id.main_screen, touchFragment)
            .addToBackStack(null)
            .commit()
        */
    }


    override fun friendsTapped() {
        val bundle = Bundle()
        bundle.putString("request_from_fragment", mainMenuFragmentTag)

        friendsFragment.arguments = bundle

        /*  Implementing NavFrag, temporarily suspended.
        manager.beginTransaction()
            .replace(R.id.main_screen, friendsFragment)
            .addToBackStack(null)
            .commit()
        */
    }

    /* ----- Interfaces for FriendsFragment ----- */
    override fun optionTapped(friendSelected: Int, requestMadeFromFragment: String?) {
        val bundle = Bundle()
        bundle.putInt("selected_friend", friendSelected)

        /*  Implementing NavFrag, temporarily suspended.
        if (requestMadeFromFragment == mainMenuFragmentTag) {
            contactInfoFragment.arguments = bundle

            manager.beginTransaction()
                .replace(R.id.main_screen, contactInfoFragment)
                .commit()
        }

        if (requestMadeFromFragment == eventFragmentTag) {
            App.savedData!!.setTempName(friendSelected.toString())

            manager.beginTransaction()
                .replace(R.id.main_screen, eventFragment)
                .commit()
        }
        */
    }

    /* ----- Interface for ContactInfoFragment ----- */
    override fun contactInfoSaveButtonPressed() {

        /*  Implementing NavFrag, temporarily suspended.
        manager.beginTransaction()
            .remove(contactInfoFragment)
            .replace(R.id.main_screen, friendsFragment)
            .commit()
        */
    }

    /* ----- Interface EventFragment ----- */
    override fun leaveEventFragmentGoBackToMainMenu() {

        /*  Implementing NavFrag, temporarily suspended.
        manager.beginTransaction()
            .remove(eventFragment)
            .replace(R.id.main_screen, mainMenuFragment)
            .commit()
        */
    }

    override fun selectTapped() {
        val bundle = Bundle()
        bundle.putString("request_from_fragment", eventFragmentTag)

        friendsFragment.arguments = bundle

        /*  Implementing NavFrag, temporarily suspended.
        manager.beginTransaction()
            .replace(R.id.main_screen, friendsFragment)
            .addToBackStack(null)
            .commit()
        */
    }

    override fun descriptionTapped() {

        /*  Implementing NavFrag, temporarily suspended.
        manager.beginTransaction()
            .replace(R.id.main_screen, descriptionFragment)
            .commit()
        */
    }

    override fun descriptionSaveButtonPressed() {

        /*  Implementing NavFrag, temporarily suspended.
        manager.beginTransaction()
            .remove(descriptionFragment)
            .replace(R.id.main_screen, eventFragment)
            .commit()
        */
    }

    override fun tappedLogEvent() {

        /*  Implementing NavFrag, temporarily suspended.
        manager.beginTransaction()
            .replace(R.id.main_screen, eventFragment)
            .addToBackStack(null)
            .commit()
            */
    }

}
