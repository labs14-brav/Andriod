package com.thadocizn.brav.views

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.thadocizn.brav.DrawerUtil
import com.thadocizn.brav.R
import com.thadocizn.brav.adapters.CaseAdapter
import com.thadocizn.brav.adapters.MediatorAdapter
import com.thadocizn.brav.models.Case
import com.thadocizn.brav.models.Mediator
import com.thadocizn.brav.services.BravApi
import com.thadocizn.brav.services.RetroInstance

import kotlinx.android.synthetic.main.activity_case.*
import kotlinx.android.synthetic.main.activity_mediator.*
import kotlinx.android.synthetic.main.content_case.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CaseActivity : AppCompatActivity() {
    var cases: ArrayList<Case>? = null
    private lateinit var idToken: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_case)
        setSupportActionBar(tbCase)

        val mUser = FirebaseAuth.getInstance().currentUser
        mUser!!.getIdToken(true)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    idToken = task.result!!.token.toString()
                    println(idToken.toString())
                    getCases(idToken)


                } else {

                    // Handle error -> task.getException();

                }
            }


        DrawerUtil.getDrawer(this, tbCase)
        fab.setOnClickListener { view ->
           alert("Creating a court case. Press ok, otherwise press cancel") {
               yesButton {
                   startActivity<CourtCaseFormActivity>(getString(R.string.token) to idToken)
               }
               noButton {startActivity<OtherCaseActivity>(getString(R.string.token) to idToken)  }
           }.show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
            R.id.deactivate_account -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getCases(token: String){
        val service: BravApi = RetroInstance().service(token)
        val call = service.getCases()

        call.enqueue(object : Callback<List<Case>> {
            override fun onFailure(call: Call<List<Case>>, t: Throwable) {
                // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<List<Case>>, response: Response<List<Case>>) {
                // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                cases = response.body() as ArrayList<Case>?
                println(cases)
                getRecycleView(cases)
            }

        })
    }
    private fun getRecycleView(list: ArrayList<Case>?) {
        val adapter = CaseAdapter(list)
        rvCase.adapter = adapter
        rvCase.layoutManager = LinearLayoutManager(this)
    }
}
