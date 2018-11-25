package com.camehereforstickers.luckypoint

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (getSharedPreferences("luckypoint", Context.MODE_PRIVATE).contains("number")) {
            startMainActivity()

        } else {
            setContentView(R.layout.activity_login)

            sign_in_button.setOnClickListener {

                val number = number_text.text.toString().toLong()
                val username = name_text.text.toString()

                getSharedPreferences("luckypoint", Context.MODE_PRIVATE).edit()
                    .putLong("number", number)
                    .putString("name", username)
                    .apply()

                startMainActivity()
            }
        }
    }

    private fun startMainActivity() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        this.finish()
        startActivity(intent)
    }
}
