package com.mattercube.touchbase


import android.os.Bundle
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

    val emptyFieldWarning = context?.resources?.getString(R.string.empty_fields_warning)
    val infoUpdated       = context?.resources?.getString(R.string.contact_updated)
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

        friendSelected = arguments?.getInt("selectedFriend")

        buttonPressed = context as saveButton

        button_save.setOnClickListener {

            if (input_contact_name.toString().isBlank() ||
                input_contact_phone_number.toString().isBlank()) {

                Toast.makeText(context, emptyFieldWarning, Toast.LENGTH_LONG).show()
            }
            else {
                var inputedName = input_contact_name.toString()
                var inputedNumber = input_contact_phone_number.toString()

                Toast.makeText(context, infoUpdated, Toast.LENGTH_LONG).show()

                App.savedData!!.setPersonName(friendSelected, inputedNumber)
                App.savedData!!.setPersonNumber(friendSelected, inputedNumber)

                buttonPressed.saveButtonPressed()
            }
        }

    }

    interface saveButton {
        fun saveButtonPressed()
    }
}
