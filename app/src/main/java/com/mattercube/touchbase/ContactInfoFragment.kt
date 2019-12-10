package com.mattercube.touchbase


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_contact_info.*

/**
 * A simple [Fragment] subclass.
 */
class ContactInfoFragment : Fragment() {

    private lateinit var buttonPressed: saveButton


    var friendSelected: Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        buttonPressed = context as saveButton

        val emptyFieldWarning = context?.resources?.getString(R.string.empty_fields_warning)
        val infoUpdated       = context?.resources?.getString(R.string.contact_updated)

        friendSelected = arguments?.getInt("selected_friend")

        button_save.setOnClickListener {

            if (input_contact_name.text.toString().isBlank() ||
                input_contact_phone_number.text.toString().isBlank()) {

                Toast.makeText(context, emptyFieldWarning, Toast.LENGTH_LONG).show()
            }
            else {
                var inputedName = input_contact_name.text.toString()
                var inputedNumber = input_contact_phone_number.text.toString()

                Log.i("NOTICE ME SENPAI!!!!!", inputedName)
                Toast.makeText(context, infoUpdated, Toast.LENGTH_LONG).show()

                App.savedData!!.setPersonName(friendSelected, inputedName)
                App.savedData!!.setPersonNumber(friendSelected, inputedNumber)

                buttonPressed.saveButtonPressed()
            }
        }

    }

    interface saveButton {
        fun saveButtonPressed()
    }
}
