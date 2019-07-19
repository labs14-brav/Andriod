package com.thadocizn.brav.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.thadocizn.brav.R
import kotlinx.android.synthetic.main.landing_activity.*

class LandingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_activity)

        val auth: FirebaseAuth = FirebaseAuth.getInstance()

        val user = auth.currentUser

        if (user != null) {

            textView.text = user.email
        }
    }
}