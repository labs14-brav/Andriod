package com.thadocizn.brav.views

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.thadocizn.brav.R
import com.thadocizn.brav.adapters.CaseAdapter
import com.thadocizn.brav.models.Case
import com.thadocizn.brav.services.RetroInstance
import com.thadocizn.brav.utils.DrawerUtil
import com.thadocizn.brav.utils.SharedPreference
import kotlinx.android.synthetic.main.activity_case.*
import kotlinx.android.synthetic.main.content_case.*
import kotlinx.coroutines.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.startActivity

class CaseActivity : AppCompatActivity() {

    private lateinit var idToken: String
    val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_case)
        setSupportActionBar(tbCase)
        val sharedPreference = SharedPreference(this)

        idToken = sharedPreference.getToken("token").toString()

        getCases()

        DrawerUtil.getDrawer(this, tbCase)
        fab.setOnClickListener {
            alert(getString(R.string.msgChooseTypeOfCase)) {
                positiveButton("Court Case") {
                    startActivity<CourtCaseFormActivity>(getString(R.string.token) to idToken)
                }
                negativeButton("Other") { startActivity<OtherCaseActivity>(getString(R.string.token) to idToken) }
            }.show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

   /* override fun onPostResume() {
        super.onPostResume()
        getCases()
    }

    override fun onResume() {
        super.onResume()

    }*/

    override fun onRestart() {
        super.onRestart()
        getCases()
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
            R.id.settings -> {
                applicationContext.startActivity(intentFor<UserAccountActivity>().newTask())
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getCases() {

        coroutineScope.launch {
            val service = RetroInstance().service(idToken)
            val call = service.getCasesAsync()

            withContext(Dispatchers.Main) {

                val response = call.await()
                val list = response

                getRecycleView(list)
            }
        }
    }

    private fun getRecycleView(list: List<Case>?) {
        val adapter = CaseAdapter(list)
        rvCase.adapter = adapter
        rvCase.layoutManager = GridLayoutManager(this, 1)
    }
}
