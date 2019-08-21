package com.thadocizn.brav.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.thadocizn.brav.R
import com.thadocizn.brav.models.Case
import com.thadocizn.brav.models.CaseOut
import com.thadocizn.brav.services.RetroInstance
import com.thadocizn.brav.utils.SharedPreference
import kotlinx.android.synthetic.main.activity_user_account.*
import kotlinx.coroutines.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class UserAccountActivity : AppCompatActivity() {
    private lateinit var idToken: String
    private lateinit var auth: FirebaseAuth
    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_account)
        val mediator: Int = intent.getIntExtra("mediatorId", 0)
        val caseId: Int = intent.getIntExtra("caseId", 0)
        this.auth = FirebaseAuth.getInstance()

        val sharedPreference = SharedPreference(this)
        idToken = sharedPreference.getToken("token").toString()

        val userEmail = sharedPreference.getUserEmail("userEmail")
        tvUserEmail.text = userEmail

            if (mediator != 0 && caseId != 0){
            connect(mediator, caseId)
        }

        btnSignOut.setOnClickListener {
            auth.signOut()
            startActivity<MainActivity>()
             }

        btnDeactivateAccount.setOnClickListener {

            alert("Are you sure?"){
                positiveButton("Yes"){
                    deactivate()
                    auth.signOut()
                    startActivity<MainActivity>()
                }
                negativeButton("Cancel"){}
            }.show()

        }

    }


        private fun deactivate() {
            coroutineScope.launch {
                val service = RetroInstance().service(idToken)
                val call = service.deactivateAsync()

                withContext(Dispatchers.Main){
                    try {
                        call.await()
                    } catch (e: HttpException) {
                    }
                }
            }
        }



    private fun connect(mediatorId: Int, caseId: Int) {
        val service = RetroInstance().service(idToken)
        val call = service.connect(mediatorId, CaseOut(caseId))
        call.enqueue(object : Callback<Case> {
            override fun onFailure(call: Call<Case>, t: Throwable) {
                println(t.message)
            }

            override fun onResponse(call: Call<Case>, response: Response<Case>) {

                toast("Email sent")
            }

        })
    }
}