package com.thadocizn.brav

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.stripe.android.ApiResultCallback
import com.stripe.android.Stripe
import com.stripe.android.TokenCallback
import com.stripe.android.model.Token
import com.stripe.android.view.CardMultilineWidget
import java.lang.Exception

class PaymentActivity : AppCompatActivity() {
    private lateinit var cardInputWidget: CardMultilineWidget

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        cardInputWidget = findViewById(R.id.card_input_widget)
        val card = cardInputWidget.card

    }

    fun getstripeToken(context: Context, key: String): Unit {

        val stripe = Stripe(context, key)
        stripe.createToken(cardInputWidget.card!!, object : ApiResultCallback<Token> {
            override fun onSuccess(result: Token) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                //create a method to send token to backend to complete payment
            }

            override fun onError(e: Exception) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
}