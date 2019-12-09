package com.mattercube.touchbase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
//Made by Roman Khamov
class MainActivity : AppCompatActivity(), MainMenuFragment.mainMenuOptions,
                        FriendsFragment.FriendOptions {

    // Fragments
    private val mainMenuFragment = MainMenuFragment()
    private val friendsFragment = FriendsFragment()
    private val eventFragment = EventFragment()
    private val touchFragment = TouchFragment()
    private val contactInfoFragment = ContactInfoFragment()


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
        manager.beginTransaction()
            .replace(R.id.main_screen, friendsFragment)
            .addToBackStack(null)
            .commit()
    }

    /* ----- Interfaces for FriendsFragment ----- */

    override fun option1Tapped() {
        manager.beginTransaction()
            .replace(R.id.main_screen, contactInfoFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun option2Tapped() {
        manager.beginTransaction()
            .replace(R.id.main_screen, contactInfoFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun option3Tapped() {
        manager.beginTransaction()
            .replace(R.id.main_screen, contactInfoFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun option4Tapped() {
        manager.beginTransaction()
            .replace(R.id.main_screen, contactInfoFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun option5Tapped() {
        manager.beginTransaction()
            .replace(R.id.main_screen, contactInfoFragment)
            .addToBackStack(null)
            .commit()
    }
}
