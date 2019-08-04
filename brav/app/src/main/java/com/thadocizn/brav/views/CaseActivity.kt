package com.thadocizn.brav.views

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity;
import com.thadocizn.brav.DrawerUtil
import com.thadocizn.brav.R

import kotlinx.android.synthetic.main.activity_case.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton
import org.jetbrains.anko.startActivity

class CaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_case)
        setSupportActionBar(tbCase)
        
        val token = intent.extras

        DrawerUtil.getDrawer(this, tbCase)
        fab.setOnClickListener { view ->
           alert("Creating a court case. Press ok, otherwise press cancel") {
               yesButton {
                   startActivity<CourtCaseFormActivity>("token" to token)
               }
               noButton {startActivity<OtherCaseActivity>("token" to token)  }
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
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}
