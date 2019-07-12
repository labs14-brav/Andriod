package com.thadocizn.brav.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.thadocizn.brav.R
import kotlinx.android.synthetic.main.activity_user_registration.*
import org.jetbrains.anko.design.snackbar

class UserRegistration : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_registration)

        btnGo.setOnClickListener{
            val user = etUserName.text ?: "Welcome Aboard"
            it.snackbar("Welcome aboard $user")
        }
    }
}
