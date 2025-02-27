package com.thadocizn.brav.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stripe.android.ApiResultCallback
import com.stripe.android.Stripe
import com.stripe.android.model.Card
import com.stripe.android.model.Token
import com.stripe.android.view.CardMultilineWidget
import com.thadocizn.brav.R
import com.thadocizn.brav.models.StripeToken
import com.thadocizn.brav.services.RetroInstance
import com.thadocizn.brav.utils.SharedPreference
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.coroutines.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.longToast
import org.jetbrains.anko.newTask

class PaymentActivity : CoroutineScopeActivity() {
    private lateinit var cardInputWidget: CardMultilineWidget
    private lateinit var idToken: String
    var caseId: Int? = 0
    var invoiceId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        caseId = intent.getIntExtra("caseId", 0)
        invoiceId = intent.getIntExtra("invoiceId", 0)
        val sharedPreference = SharedPreference(this)

        idToken = sharedPreference.getToken("token").toString()


        btnSubmit.setOnClickListener {
            val card = getCard()

            if (card != null) {
                getstripeToken(card)
            }

        }

    }

    private fun getCard(): Card? {
        cardInputWidget = findViewById(R.id.card_input_widget)
        return cardInputWidget.card
    }

    private fun getstripeToken(card: Card): Unit {

        val stripe = Stripe(this, "pk_test_jvHFK1rRq0u4Bj2BejCL7ngi")
        stripe.createToken(card, object : ApiResultCallback<Token> {
            override fun onSuccess(result: Token) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                //create a method to send token to backend to complete payment
                val response = result.id
                sendToken(response)
            }

            override fun onError(e: Exception) {
              //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    private fun sendToken(token:String):Job =
        launch {
            val service = RetroInstance().service(idToken)
            val call = service.sendTokenAsync(1, StripeToken(token))

            call.await()
            if (call.isCompleted) {
                getPaymentMade()

            }
            applicationContext.startActivity(intentFor<CaseActivity>().newTask())

        }

    private fun getPaymentMade():Job =
        launch {

            val service = RetroInstance().service(idToken)
            val call = service.invoicePaid(1)

            call.await()
            applicationContext.longToast("Payment Sent")

        }
}