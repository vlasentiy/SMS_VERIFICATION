package com.vls.smsverification

import android.app.Activity
import com.google.android.gms.auth.api.phone.SmsRetriever

class OtpAutoFillReceiver(private var activity: Activity, private var otpListener: OtpListener, private var retrieverListener: OnStartSmsRetriverListener? = null) {

    constructor(activity: Activity, listener: OtpListener) : this(activity, listener, null)

    fun start() {
        val client = SmsRetriever.getClient(activity)
        val task = client.startSmsRetriever()

        task.addOnSuccessListener {
            retrieverListener?.onSuccess()
            OtpReceiver.bindOtpListener(otpListener)
        }
        task.addOnFailureListener { e ->
            retrieverListener?.onFail(e)
        }
    }
}