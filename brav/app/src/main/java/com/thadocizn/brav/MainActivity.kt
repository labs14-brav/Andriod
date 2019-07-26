package com.thadocizn.brav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.thadocizn.brav.model.CustomViewModel
import com.thadocizn.brav.services.BravApi
import com.thadocizn.brav.services.RetroInstance
import com.thadocizn.brav.viewModels.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth
    var data: ArrayList<User> = ArrayList()
    private lateinit var viewModel: UserViewModel
//    private var token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.auth = FirebaseAuth.getInstance()
        this.auth.signOut()


            emailSignInButton.setOnClickListener(this)
            emailCreateAccountButton.setOnClickListener(this)
            signOutButton.setOnClickListener(this)
            verifyEmailButton.setOnClickListener(this)
            enter_button.setOnClickListener(this)

    }

    private fun populateUsers() {
        viewModel.userList.observe(this, Observer { users ->
            data = users as ArrayList<User>
            println(data.size)

        })

    }

    private fun registerUser() {

        viewModel.createUser.observe(this, Observer {
        })

    }

    override fun onClick(v: View) {
        val i = v.id
        when (i) {
            R.id.emailCreateAccountButton -> createAccount(fieldEmail.text.toString(), fieldPassword.text.toString())
            R.id.emailSignInButton -> signIn(fieldEmail.text.toString(), fieldPassword.text.toString())
            R.id.signOutButton -> signOut()
            R.id.verifyEmailButton -> sendEmailVerification()
            R.id.enter_button -> loadIntent()


        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = this.auth.currentUser

        updateUI(currentUser)
    }

    private fun createAccount(email: String, password: String) {
        Log.d("Create Account", "createAccount:$email")
        if (!validateForm()) {
            return
        }

        // [START create_user_with_email]
        this.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user!!.getIdToken(true).addOnSuccessListener { result ->
                        val token = result.token.toString()
                        viewModel = ViewModelProviders.of(this, CustomViewModel(token)).get(UserViewModel::class.java)
                        registerUser()}

                        // Sign in success, update UI with the signed-in user's information
                    Log.d("Success", "createUserWithEmail:success")
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Failure", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }

            }
    }

    private fun signIn(email: String, password: String) {
        if (!validateForm()) {
            return
        }

        // START sign_in_with_email
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Success", "signInWithEmail:success")
                    val user = auth.currentUser
                    user!!.getIdToken(true).addOnSuccessListener { result ->
                       val token = result.token.toString()
                        viewModel = ViewModelProviders.of(this, CustomViewModel(token)).get(UserViewModel::class.java)
                        registerUser()
                    }



                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Fail", "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }

                if (!task.isSuccessful) {
                    status.setText(R.string.auth_failed)
                }
            }
    }

    private fun signOut() {
        auth.signOut()
        updateUI(null)
    }

    private fun validateForm(): Boolean {
        var valid = true

        val email = fieldEmail.text.toString()
        if (TextUtils.isEmpty(email)) {
            fieldEmail.error = "Required."
            valid = false
        } else {
            fieldEmail.error = null
        }

        val password = fieldPassword.text.toString()
        if (TextUtils.isEmpty(password)) {
            fieldPassword.error = "Required."
            valid = false
        } else {
            fieldPassword.error = null
        }

        return valid
    }

    private fun sendEmailVerification() {
        // Disable button
        verifyEmailButton.isEnabled = false

        // Send verification email
        val user = auth.currentUser
        user?.sendEmailVerification()
            ?.addOnCompleteListener(this) { task ->

                verifyEmailButton.isEnabled = true

                if (task.isSuccessful) {
                    Toast.makeText(
                        baseContext,
                        "Verification email sent to ${user.email} ",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    // Log.e(TAG, "sendEmailVerification", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Failed to send verification email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            status.text = getString(
                R.string.email_password_status_fmt,
                user.email, user.isEmailVerified
            )
            detail.text = getString(R.string.fire_base_status_fmt, user.uid)
            emailPasswordButtons.visibility = View.GONE
            emailPasswordFields.visibility = View.GONE
            signedInButtons.visibility = View.VISIBLE
            enter_button.visibility = View.VISIBLE
            enter_button.isClickable = true

            verifyEmailButton.isEnabled = !user.isEmailVerified
        } else {
            status.setText(R.string.signed_out)
            detail.text = null

            emailPasswordButtons.visibility = View.VISIBLE
            emailPasswordFields.visibility = View.VISIBLE
            signedInButtons.visibility = View.GONE
            enter_button.isClickable = false
            enter_button.visibility = View.GONE
        }
    }

    private fun loadIntent() {
        val landingIntent = Intent(this@MainActivity, LandingActivity::class.java)
        //adding any credentials needed to the intent to pass, not sure if the authorization carries through the activities

        startActivity(landingIntent)

    }
}
