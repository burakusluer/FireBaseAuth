package com.burakusluer.firebaseauth.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.burakusluer.firebaseauth.R
import com.burakusluer.firebaseauth.activityTraveler
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    //region InitArea
    private lateinit var name: EditText
    private lateinit var pass: EditText
    private lateinit var firebaseAuth: FirebaseAuth
    private fun init() {
        name = findViewById(R.id.editTextRegisterName)
        pass = findViewById(R.id.editTextRegisterPass)
        firebaseAuth = FirebaseAuth.getInstance()
    }

    //endregion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        init()
    }

    fun Register(view: View) {
        when (view.id) {
            R.id.buttonRegister -> firebaseAuth.createUserWithEmailAndPassword(
                name.text.toString(),
                pass.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(
                        this@RegisterActivity,
                        "You Registered SuccessFully",
                        Toast.LENGTH_LONG
                    ).show()
                    activityTraveler(this, Intent(this, MainActivity::class.java))
                }
            }
        }
    }

}