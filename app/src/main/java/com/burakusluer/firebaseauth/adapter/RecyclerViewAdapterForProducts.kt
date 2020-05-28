package com.burakusluer.firebaseauth.adapter

import android.app.Activity
import android.graphics.ImageDecoder
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.burakusluer.firebaseauth.R
import com.burakusluer.firebaseauth.model.Product

class RecyclerViewAdapterForProducts(
    var activity: Activity? = null,
    var array: ArrayList<Product>? = ArrayList<Product>()
) :
    RecyclerView.Adapter<RecyclerViewAdapterForProducts.RecyclerViewHolderForProducts>() {


    inner class RecyclerViewHolderForProducts(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewProductDesc: TextView = itemView.findViewById(R.id.textViewProductDesc)
        var textViewProductName: TextView = itemView.findViewById(R.id.textViewProductName)
        var imageViewProduct: ImageView = itemView.findViewById(R.id.imageViewProduct)
    }

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
        holder.textViewProductName.text = array!![position].ad
        holder.textViewProductDesc.text = array!![position].aciklama
        if (Build.VERSION.SDK_INT >= 28 && activity != null)
            holder.imageViewProduct.setImageBitmap(
                ImageDecoder.decodeBitmap(
                    ImageDecoder.createSource(
                        activity!!.contentResolver, array!![position].resimUrl
                    )
                )
            )
    }

}