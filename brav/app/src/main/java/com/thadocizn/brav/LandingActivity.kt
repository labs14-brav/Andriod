package com.thadocizn.brav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_landing.*



class LandingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        val mUser = FirebaseAuth.getInstance().currentUser
        mUser!!.getIdToken(true)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val idToken = task.result!!.token
                    textView.text = idToken
                    // Send token to your backend via HTTPS
                    // ...
                } else {
                    // Handle error -> task.getException();

                }
            }


    }


}
