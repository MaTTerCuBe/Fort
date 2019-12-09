package com.mattercube.touchbase


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main_menu.*

/**
 * A simple [Fragment] subclass.
 */
class MainMenuFragment : Fragment() {

    private lateinit var optionSelected: mainMenuOptions

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        optionSelected =context as mainMenuOptions

        main_screen.setOnClickListener {
            optionSelected.logEventTapped()
        }

        middle_panel.setOnClickListener {
            optionSelected.touchBaseTapped()
        }

        bottom_panel.setOnClickListener {
            optionSelected.friendsTapped()
        }
    }

    interface mainMenuOptions {
        fun logEventTapped()
        fun touchBaseTapped()
        fun friendsTapped()
    }
}
