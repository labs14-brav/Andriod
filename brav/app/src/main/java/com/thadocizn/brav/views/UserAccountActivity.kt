package com.thadocizn.brav.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thadocizn.brav.R
import com.thadocizn.brav.models.Case
import com.thadocizn.brav.models.CaseOut
import com.thadocizn.brav.services.RetroInstance
import com.thadocizn.brav.utils.SharedPreference
import kotlinx.android.synthetic.main.activity_user_account.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserAccountActivity : AppCompatActivity() {
    private lateinit var idToken: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_account)
        val mediator: Int = intent.getIntExtra("mediatorId", 0)
        val caseId: Int = intent.getIntExtra("caseId", 0)


        val sharedPreference = SharedPreference(this)
        idToken = sharedPreference.getToken("token").toString()

        val userEmail = sharedPreference.getUserEmail("userEmail")
        tvUserEmail.text = userEmail

            if (mediator != 0 && caseId != 0){
            connect(mediator, caseId)
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