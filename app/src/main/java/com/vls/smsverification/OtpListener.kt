package com.vls.smsverification

interface OtpListener {
    fun onSuccess(code: String)
    fun onError()
    fun onStatusCodeError()
    fun onTimeout()
}