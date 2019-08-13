package com.thadocizn.brav.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thadocizn.brav.R
import kotlinx.android.synthetic.main.activity_mediator_detail.*

class MediatorDetailActivity : AppCompatActivity() {

    companion object{

        const val TYPE = "type"
        const val LICENSE = "license"
        const val EXPERIENCE = "experience"
        const val SPECIALIZATION = "specialization"
        const val LANGUAGE = "language"
        const val PROFESSIONAL_BIO = "professional_bio"
        const val NAME = "name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mediator_detail)

        val getMediator = intent.extras
        if (getMediator != null){

            val type = getMediator.getString(TYPE)
            val license = getMediator.getString(LICENSE)
            val experience = getMediator.getString(EXPERIENCE)
            val specialization = getMediator.getString(SPECIALIZATION)
            val language = getMediator.getString(LANGUAGE)
            val bio = getMediator.getString(PROFESSIONAL_BIO)
            val name = getMediator.getString(NAME)

            tvMediatorDetType.text = type
            tvMediatorDetLicense.text = license
            tvMediatorDetExperience.text = experience
            tvMediatorDetSpecialization.text = specialization
            tvMediatorDetLanguage.text = language
            tvMediatorDetBio.text = bio
            tvMediatorDetName.text = name
        }
    }
}
