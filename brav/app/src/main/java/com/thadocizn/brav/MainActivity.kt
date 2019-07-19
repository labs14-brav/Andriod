package com.thadocizn.brav

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.thadocizn.brav.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.design.snackbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnEnter.setOnClickListener{
            it.snackbar("Come on In")
        }
    }
}
