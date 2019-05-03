package com.vls.smsverification

interface SmsListener {
    fun onSuccess(code: String)
    fun onError()
}