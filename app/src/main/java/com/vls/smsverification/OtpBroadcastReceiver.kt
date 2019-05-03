package com.vls.smsverification

import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.auth.api.phone.SmsRetriever
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import com.google.android.gms.common.api.Status

class OtpReceiver : BroadcastReceiver() {
    private val codePattern = "(\\d{6})".toRegex()

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
                    // Get SMS message contents
                    val message = extras.get(SmsRetriever.EXTRA_SMS_MESSAGE) as String
                    val code: MatchResult? = codePattern.find(message)
                    if (code?.value != null) {
                        otpListener.onSuccess(code.value)
                    } else {
                        otpListener.onStatusCodeError()
                    }
                }
                CommonStatusCodes.ERROR -> otpListener.onError()
                CommonStatusCodes.TIMEOUT -> otpListener.onTimeout()
            }
        }
    }
}