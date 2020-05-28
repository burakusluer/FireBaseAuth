package com.burakusluer.firebaseauth.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.burakusluer.firebaseauth.R

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
}