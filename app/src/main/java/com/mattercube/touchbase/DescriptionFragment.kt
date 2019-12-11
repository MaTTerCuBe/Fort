package com.mattercube.touchbase


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_description.*

/**
 * Made by Roman Khamov
 */
class DescriptionFragment : Fragment() {

    private lateinit var buttonPressedDescription: DescriptionSaveButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_description, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        buttonPressedDescription = context as DescriptionSaveButton

        val emptyFieldWarning = context?.resources?.getString(R.string.empty_fields_warning)


        button_save.setOnClickListener {
            val inputedEntry = input_entry.text.toString()

            if (inputedEntry.isBlank()) {

                Toast.makeText(context, emptyFieldWarning, Toast.LENGTH_LONG).show()
            }
            else {
                App.savedData!!.setTempEntry(inputedEntry)

                buttonPressedDescription.descriptionSaveButtonPressed()
            }
        }

    }

    interface DescriptionSaveButton {
        fun descriptionSaveButtonPressed()
    }
}
