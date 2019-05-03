package com.vls.smsverification

interface OtpListener {
    fun onSuccess(code: String)
    fun onError(errorMessage:String?)
    fun onEmptyMatchedResult(message:String)
    fun onTimeout()
}