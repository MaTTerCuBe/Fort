package com.mattercube.touchbase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
//Made by Roman Khamov
class MainActivity : AppCompatActivity() {

    private val mainMenuFragment = MainMenuFragment()
    private val friendsFragment = FriendsFragment()
    private val eventFragment = EventFragment()
    private val touchFragment = TouchFragment()
    private val holder3 = BlankFragment()
    private val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        Log.i("activity_main", "About to set content view")
        setContentView(R.layout.activity_main)

        Log.i("activity_main", "About begin manager shinanigins")
        manager.beginTransaction()
            .replace(R.id.top_panel, mainMenuFragment)
            .commit()

    }
}
