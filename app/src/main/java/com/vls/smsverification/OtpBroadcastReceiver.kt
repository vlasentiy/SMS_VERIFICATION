package com.vls.smsverification

import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.auth.api.phone.SmsRetriever
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import com.google.android.gms.common.api.Status

class OtpReceiver : BroadcastReceiver() {
    // find just 4 digits in string
    //private val codePattern = "(\\d{6})".toRegex()

    /*
    <#> Parol:123456. Ne peredavayte ego nikomy
    d3cIygVb7HX
    */
    private val codePattern = "(?<=:)(.*?)(?=\\.)".toRegex()

    /*
    <#> Your code is:123456
    d3cIygVb7HX
    */
    //private val codePattern = "(?<=:).*".toRegex()

    companion object {
        private lateinit var otpListener: OtpListener
        fun bindOtpListener(otpListener: OtpListener) {
            Companion.otpListener = otpListener
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION == intent.action) {
            val extras = intent.extras
            val status = extras?.get(SmsRetriever.EXTRA_STATUS) as Status

            when (status.statusCode) {
                CommonStatusCodes.SUCCESS -> {
                    val message = extras.get(SmsRetriever.EXTRA_SMS_MESSAGE) as String
                    val code: MatchResult? = codePattern.find(message)
                    if (code?.value != null) {
                        otpListener.onSuccess(code.value)
                    } else {
                        otpListener.onEmptyMatchedResult(message)
                    }
                }
                CommonStatusCodes.ERROR -> otpListener.onError(status.statusMessage)
                CommonStatusCodes.TIMEOUT -> otpListener.onTimeout()
            }
        }
    }
}