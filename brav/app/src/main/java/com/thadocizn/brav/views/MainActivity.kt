package com.thadocizn.brav.views

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.thadocizn.brav.R
import com.thadocizn.brav.models.User
import com.thadocizn.brav.services.BravApi
import com.thadocizn.brav.services.RetroInstance
import com.thadocizn.brav.utils.DrawerUtil
import com.thadocizn.brav.utils.SharedPreference
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.yesButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth
    lateinit var token: String
    lateinit var sharedPreference: SharedPreference
    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.auth = FirebaseAuth.getInstance()
        this.auth.signOut()

        sharedPreference = SharedPreference(this)

        setSupportActionBar(tbMain)

        emailSignInButton.setOnClickListener(this)
        emailCreateAccountButton.setOnClickListener(this)
        signOutButton.setOnClickListener(this)
        verifyEmailButton.setOnClickListener(this)
        enter_button.setOnClickListener(this)

    }


   /* override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.settings -> {
                baseContext.startActivity<UserAccountActivity>()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }*/

    private fun registerUser() {

        coroutineScope.launch {
            val service: BravApi = RetroInstance().service(sharedPreference.getToken("token"))
            val call = service.loginUserAsync()

            withContext(Dispatchers.Main){
                val response = call.await()
                val userEmail = response.email
                val userId = response.id

                sharedPreference.saveUserEmail("userEmail", userEmail)
                sharedPreference.saveUserId("userId", userId)

            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
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
        if (!validateForm()) {
            return
        }

        // [START create_user_with_email]
        this.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    
                    val user = auth.currentUser
                    user!!.getIdToken(true).addOnSuccessListener { result ->
                        token = result.token.toString()
                        sharedPreference.saveToken("token", token)
                        registerUser()
                    }

                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Success", "createUserWithEmail:success")
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
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
                    val user = auth.currentUser
                    user!!.getIdToken(true).addOnSuccessListener { result ->
                        token = result.token.toString()
                        sharedPreference.saveToken("token", token)
                        registerUser()

                    }


                    DrawerUtil.getDrawer(this, tbMain)

                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }

                if (!task.isSuccessful) {
                    //status.setText(R.string.auth_failed)
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

            emailPasswordButtons.visibility = View.GONE
            emailPasswordFields.visibility = View.GONE
            signedInButtons.visibility = View.VISIBLE
            enter_button.visibility = View.VISIBLE
            enter_button.isClickable = true

            verifyEmailButton.isEnabled = !user.isEmailVerified
        } else {


            emailPasswordButtons.visibility = View.VISIBLE
            emailPasswordFields.visibility = View.VISIBLE
            signedInButtons.visibility = View.GONE
            enter_button.isClickable = false
            enter_button.visibility = View.GONE
        }
    }

    private fun loadIntent() {

        //startActivity(landingIntent)
        startActivity<CaseActivity>()

    }
}
