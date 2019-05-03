package com.vls.smsverification

import java.lang.Exception


interface OnStartSmsRetrieverListener {
    fun onSuccess()
    fun onFail(e: Exception)
}