package com.thadocizn.brav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.thadocizn.brav.models.Case
import kotlinx.android.synthetic.main.activity_create_case.*

class CreateCaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_case)

        button_save_case.setOnClickListener{
            val title = title_edit_text.text.toString()
            val description = info_edit_text.text.toString()
            val category = category_edit_text.text.toString()
            val createdCase = Case(title,description,category)
        }
    }
}
