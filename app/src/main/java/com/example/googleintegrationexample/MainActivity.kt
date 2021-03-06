package com.example.googleintegrationexample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
//    lateinit var googleSignInClient:GoogleSignInClient
    var RC_SIGN_IN :Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gso:GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this@MainActivity,gso)
        signIn.setOnClickListener {
//            signIntoGoogleaccount()
            val signinIntent: Intent = googleSignInClient.signInIntent
            startActivityForResult(signinIntent,RC_SIGN_IN)
        }





    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) { // The Task returned from this call is always completed, no need to attach
// a listener.
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun signIntoGoogleaccount() {

    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account =
                completedTask.getResult(ApiException::class.java)
            // Signed in successfully, show authenticated UI.
            startActivity(Intent(this@MainActivity,SecondActivity::class.java))
        } catch (e: ApiException) { // The ApiException status code indicates the detailed failure reason.
// Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("onError", "signInResult:failed code=" + e.statusCode)

        }
    }

//    override fun onStart() {
//        super.onStart()
//        val account  : GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this@MainActivity)
//    }
}
