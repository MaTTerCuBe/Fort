package com.mattercube.touchbase


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_touch.*

/**
 * Created by Roman Khamov
 * Inspired by:
 *      - Atif Pervaiz from https://devofandroid.blogspot.com/2018/11/dialer-intent-android-studio-kotlin.html
 *      - ChiragP Patel from https://medium.com/@chiragpatel_52497/send-sms-from-android-application-a8a9c1ada8b7
 */
class TouchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_touch, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        call_icon1.setOnClickListener {
            makeCall(1)
        }

        text_icon1.setOnClickListener {
            sendSMS(1)
        }
    }

    fun makeCall(toPersonNum: Int?) {

        val numberOfFriend = App.savedData!!.getPersonNumber(toPersonNum)

        val dialInent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(numberOfFriend)))

        val shareIntent = Intent.createChooser(dialInent, null)

        startActivity(shareIntent)
    }

    fun sendSMS(toPersonNum: Int?)
    {
        val numberOfFriend = App.savedData!!.getPersonNumber(toPersonNum)

        val messageIntent = Intent(Intent.ACTION_SENDTO, Uri.parse(numberOfFriend))

        messageIntent.putExtra("sms_body", "We need to touch base.\n")

        val shareIntent = Intent.createChooser(messageIntent, null)

        startActivity(shareIntent)
    }
}
