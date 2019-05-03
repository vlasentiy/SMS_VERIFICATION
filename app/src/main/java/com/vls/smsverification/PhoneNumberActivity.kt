package com.vls.smsverification

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class PhoneNumberActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_number)
    }

    fun verifyPhoneClick(view: View) {
        //Mock Send the phone number to the backend service

        //val appSignatures = AppSignatureHelper(this@PhoneNumberActivity).appSignatures
        //Log.d("vls",appSignatures.toString())

        startActivity(Intent(this, CodeVerificationActivity::class.java))
    }

}
