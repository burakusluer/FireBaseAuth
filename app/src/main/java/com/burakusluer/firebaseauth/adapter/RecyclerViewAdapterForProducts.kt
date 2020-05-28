package com.burakusluer.firebaseauth.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.burakusluer.firebaseauth.R
import com.burakusluer.firebaseauth.model.Product

class RecyclerViewAdapterForProducts(
    activity: Activity,
    var array: ArrayList<Product>? = ArrayList<Product>()
) :
    RecyclerView.Adapter<RecyclerViewAdapterForProducts.RecyclerViewHolderForProducts>() {


    inner class RecyclerViewHolderForProducts(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolderForProducts {
        return RecyclerViewHolderForProducts(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.products_rcycler_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return array!!.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolderForProducts, position: Int) {
        TODO("Not yet implemented")
    }

}