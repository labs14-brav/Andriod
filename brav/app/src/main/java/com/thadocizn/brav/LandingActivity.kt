package com.thadocizn.brav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.thadocizn.brav.models.User
import com.thadocizn.brav.services.APIService
import com.thadocizn.brav.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_landing.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LandingActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)


        val mUser = FirebaseAuth.getInstance().currentUser
        mUser!!.getIdToken(true)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val idToken = task.result!!.token
                    loadUsers()
//                    textView.text = userList.toString()
                    // Send token to your backend via HTTPS
                    // ...
                } else {
                    // Handle error -> task.getException();

                }
            }


    }

private fun loadUsers(){
   val apiService =  ServiceBuilder.buildService(APIService::class.java)
    val requestCall = apiService.getUserList()
    requestCall.enqueue(object: Callback<List<User>>{
        override fun onFailure(call: Call<List<User>>, t: Throwable) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onResponse(call: Call<List<User>>, response: Response<List<User>>){
            if(response.isSuccessful){
               val userList= response.body()!!

            }
        }


    })
}
}
