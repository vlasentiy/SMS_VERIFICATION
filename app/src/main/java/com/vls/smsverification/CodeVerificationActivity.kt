package com.vls.smsverification

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.auth.api.phone.SmsRetriever
import kotlinx.android.synthetic.main.activity_code_verification.*


class CodeVerificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_verification)
        startSmsRetriever()
    }

    private fun startSmsRetriever() {
        val client = SmsRetriever.getClient(this)
        val task = client.startSmsRetriever()
        task.addOnSuccessListener {
            Log.d("vls", "Sms listener started!")
            listenSms()
        }
        task.addOnFailureListener { e ->
            Log.e("vls", "Failed to start sms retriever: ${e.message}")
        }
    }

    private fun listenSms() {
        SmsReceiver.bindListener(object : SmsListener {
            override fun onSuccess(code: String) {
                etCode.setText(code)
            }

            override fun onError() {
                Log.e("vls", "Error")
            }
        })
    }
}
