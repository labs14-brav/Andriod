package com.thadocizn.brav.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.thadocizn.brav.DrawerUtil
import com.thadocizn.brav.R
import com.thadocizn.brav.models.Case
import com.thadocizn.brav.models.Mediator
import com.thadocizn.brav.models.User
import com.thadocizn.brav.services.BravApi
import com.thadocizn.brav.services.RetroInstance
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth
    lateinit var bravUser: User
     var mediator: ArrayList<Mediator>? = null
     var cases: ArrayList<Case>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.auth = FirebaseAuth.getInstance()
        this.auth.signOut()

        setSupportActionBar(tbMain)
        DrawerUtil.getDrawer(this, tbMain)

        emailSignInButton.setOnClickListener(this)
        emailCreateAccountButton.setOnClickListener(this)
        signOutButton.setOnClickListener(this)
        verifyEmailButton.setOnClickListener(this)
        enter_button.setOnClickListener(this)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun registerUser(token: String) {
        val service: BravApi = RetroInstance().service(token)
        val call = service.loginUser()

        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
               // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                println(t.message)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                bravUser = response.body()!!
                println(bravUser.email)
                getCases(token)

            }
        })

    }

    private fun getMediators(token: String){
        val service: BravApi = RetroInstance().service(token)
        val call = service.getMediators()

        call.enqueue(object : Callback<List<Mediator>> {
            override fun onFailure(call: Call<List<Mediator>>, t: Throwable) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                println(t.message)
            }

            override fun onResponse(call: Call<List<Mediator>>, response: Response<List<Mediator>>) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                mediator = response.body() as ArrayList<Mediator>?
                println(mediator?.size)

            }

        })
    }

    private fun getCases(token: String){
        val service:BravApi = RetroInstance().service(token)
        val call = service.getCases("1")

        call.enqueue(object : Callback<List<Case>>{
            override fun onFailure(call: Call<List<Case>>, t: Throwable) {
               // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<List<Case>>, response: Response<List<Case>>) {
               // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                response.body()?.let { cases?.addAll(it) }
                println(cases)
            }

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
                        registerUser(token)
                    }

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
                        registerUser(token)
                        getMediators(token)

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
        val landingIntent = Intent(this@MainActivity, CaseActivity::class.java)
        //adding any credentials needed to the intent to pass, not sure if the authorization carries through the activities

        startActivity(landingIntent)

    }
}
