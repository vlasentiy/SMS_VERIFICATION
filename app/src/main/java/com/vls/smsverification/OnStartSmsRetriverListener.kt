package com.vls.smsverification

import java.lang.Exception


interface OnStartSmsRetriverListener {
    fun onSuccess()
    fun onFail(e: Exception)
}