package com.thadocizn.brav.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.thadocizn.brav.R
import com.thadocizn.brav.models.Case
import com.thadocizn.brav.services.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserAccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_account)
        val mediator:Int = intent.getIntExtra("mediatorId",0)
        val caseId:Int = intent.getIntExtra("caseId", 0)

        val mUser = FirebaseAuth.getInstance().currentUser
        mUser!!.getIdToken(true)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                  val  idToken = task.result!!.token.toString()

                    connect(idToken, mediator, caseId)
                } else {

                    // Handle error -> task.getException();

                }
            }

    }

    private fun connect(token: String, mediatorId:Int, caseId:Int) {
        val service = RetroInstance().service(token)
        val call = service.connect(mediatorId, Case(id = caseId))
        call.enqueue(object : Callback<Case> {
            override fun onFailure(call: Call<Case>, t: Throwable) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                println(t.message)
            }

            override fun onResponse(call: Call<Case>, response: Response<Case>) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                println(response.body())
            }

        })
    }
}