package com.thadocizn.brav.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.thadocizn.brav.R
import com.thadocizn.brav.models.Case
import com.thadocizn.brav.services.RetroInstance
import com.thadocizn.brav.utils.SharedPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompletedCasesActivity : AppCompatActivity() {

    private lateinit var idToken: String
    lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completed_cases)

        sharedPreference = SharedPreference(this)

        val mUser = FirebaseAuth.getInstance().currentUser
        mUser!!.getIdToken(true)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    idToken = task.result!!.token.toString()
                    println(idToken)
                    getCompletedCases(idToken)


                } else {

                    // Handle error -> task.getException();

                }
            }
    }

    private fun getCompletedCases(token: String) {

        val service = RetroInstance().service(token)
        val call = service.getCompletedCases(sharedPreference.getUserId("userId"))

        call.enqueue(object : Callback<List<Case>> {
            override fun onFailure(call: Call<List<Case>>, t: Throwable) {
                //
            }

            override fun onResponse(call: Call<List<Case>>, response: Response<List<Case>>) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                println(response.body())
            }
        })
    }
}
