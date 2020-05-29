package com.burakusluer.firebaseauth.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.burakusluer.firebaseauth.R
import com.burakusluer.firebaseauth.activityTraveler
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    //region Init Area
    fun init() {
        fireaseAuth = FirebaseAuth.getInstance()
        editName = findViewById(R.id.editTextLoginName)
        editPass = findViewById(R.id.editTextLoginPass)
    }

    private lateinit var fireaseAuth: FirebaseAuth
    private lateinit var editName: EditText
    private lateinit var editPass: EditText

    //endregion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        if (fireaseAuth.currentUser != null) {
            activityTraveler(
                this@MainActivity,
                Intent(this@MainActivity, ProductSaleActivity::class.java)
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(this).inflate(R.menu.login_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_login_register -> activityTraveler(
                this,
                Intent(this, RegisterActivity::class.java)
            )
        }
        return super.onOptionsItemSelected(item)
    }

    fun loginClick(x: View) {
        when (x.id) {
            R.id.buttonLogin -> fireaseAuth.signInWithEmailAndPassword(
                editName.text.toString(),
                editPass.text.toString()
            )
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        activityTraveler(
                            this@MainActivity,
                            Intent(this@MainActivity, ProductSaleActivity::class.java)
                        )
                    } else {
                        it.exception!!.printStackTrace()
                        Toast.makeText(this, it.exception!!.localizedMessage, Toast.LENGTH_LONG)
                            .show()
                    }
                }
        }
    }
}