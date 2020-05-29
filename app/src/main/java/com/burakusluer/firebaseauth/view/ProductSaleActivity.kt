package com.burakusluer.firebaseauth.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.burakusluer.firebaseauth.R
import com.burakusluer.firebaseauth.activityTraveler

class ProductSaleActivity : AppCompatActivity() {
    //region Inıt Area
    fun init() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@ProductSaleActivity)
    }

    private lateinit var recyclerView: RecyclerView

    //endregion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_sale)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.product_sale_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemAddProductForSale -> activityTraveler(
                this,
                Intent(this, AddProductActivity::class.java)
            )
        }
        return super.onOptionsItemSelected(item)
    }
}