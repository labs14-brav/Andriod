package com.thadocizn.brav.views

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.provider.AuthCallback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.thadocizn.brav.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnEnter.setOnClickListener{
            val clientId = getString(R.string.com_auth0_client_id)
            val domain = "brav.auth0.com"
            val context = this

            val account = Auth0(clientId,domain)
            account.isOIDCConformant = true

            WebAuthProvider.login(account)
                .withAudience("https://$domain/userinfo")
                .start(this,object: AuthCallback {
                    override fun onFailure(dialog: Dialog) {
                        //show error dialog
                        runOnUiThread { dialog.show() }
                    }
                    override fun onFailure(exception: AuthenticationException){
                        runOnUiThread{
                            Toast.makeText(
                                this@MainActivity, "Oops, something went wrong!", Toast.LENGTH_SHORT).show()

                        }
                        //show error to user
                    }
                    override fun onSuccess(credentials: Credentials){
                        //store credentials
                        //Navigate to main activity (logged in)

                        //not sure if this is the correct way to do it, but I don't have errors so far
                        val mainPageIntent = Intent(context, Main2Activity::class.java)

                        val aToken = credentials.accessToken

                        mainPageIntent.putExtra("credentials", aToken)
                        startActivity<Main2Activity>()
                    }
                })
        }
    }
}
