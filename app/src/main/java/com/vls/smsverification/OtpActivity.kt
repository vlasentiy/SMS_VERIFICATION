package com.vls.smsverification

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_otp.*


class OtpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        OtpAutoFillReceiver(this, object : OtpListener {
            override fun onSuccess(code: String) {
                etCode.setText(code)
            }

            override fun onError(errorMessage: String?) {
            }

            override fun onEmptyMatchedResult(message: String) {
            }

            override fun onTimeout() {
            }
        }).start()
    }
}
