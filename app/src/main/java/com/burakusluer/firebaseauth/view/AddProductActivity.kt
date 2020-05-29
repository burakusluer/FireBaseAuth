package com.burakusluer.firebaseauth.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.burakusluer.firebaseauth.R
import com.burakusluer.firebaseauth.model.Product
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException
import java.util.*
import kotlin.collections.HashMap

class AddProductActivity : AppCompatActivity() {
    //region Init Area
    private lateinit var editTextAddProduct: EditText
    private lateinit var editTextProductName: EditText
    private lateinit var imageViewAddProduct: ImageView
    private lateinit var imageUri: Uri
    fun init() {
        editTextAddProduct = findViewById(R.id.editTextAddProduct)
        imageViewAddProduct = findViewById(R.id.imageViewAddProduct)
        editTextProductName = findViewById(R.id.editTextAddProductName)
    }

    //endregion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        init()
        if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                0
            )
        }

        imageViewAddProduct.setOnLongClickListener {


            if (ActivityCompat.checkSelfPermission(
                    this@AddProductActivity,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@AddProductActivity,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    0
                )

            } else {

                startActivityForResult(
                    Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    ), 1
                )
            }
            return@setOnLongClickListener false
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        startActivityForResult(
            Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            ), 1
        )

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            try {
                imageUri = data.data!!
                if (Build.VERSION.SDK_INT >= 28) {
                    imageViewAddProduct.setImageBitmap(
                        ImageDecoder.decodeBitmap(
                            ImageDecoder.createSource(
                                contentResolver,
                                imageUri
                            )
                        )
                    )
                } else {
                    imageViewAddProduct.setImageBitmap(
                        MediaStore.Images.Media.getBitmap(
                            contentResolver,
                            imageUri
                        )
                    )
                }
            } catch (ex: IOException) {
                ex.printStackTrace()
                Toast.makeText(this@AddProductActivity, ex.localizedMessage, Toast.LENGTH_LONG)
                    .show()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    fun addProduct(view: View) {
        val storageReference: StorageReference = FirebaseStorage.getInstance().reference
        val rand: UUID = UUID.randomUUID()
        storageReference.child("${rand}.jpg").putFile(imageUri).addOnCompleteListener { it2 ->
            if (it2.isSuccessful) {
                val firabaseDatabase =
                    FirebaseFirestore.getInstance()
                val firebaseAuthUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
                if (firebaseAuthUser != null) {
                    val map: HashMap<String, Any> = HashMap<String, Any>()
                    FirebaseStorage.getInstance().reference
                        .child("${rand}.jpg").downloadUrl.addOnCompleteListener { it1 ->
                            if (it1.isSuccessful) {

                                firabaseDatabase.collection("products").add(
                                    Product(
                                        editTextProductName.text.toString(),
                                        editTextAddProduct.text.toString(),
                                        it1.result!!.toString()
                                    )
                                ).addOnCompleteListener {
                                    if (it.isComplete) {
                                        Toast.makeText(
                                            this@AddProductActivity,
                                            "success",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                        }


                }
            } else if (!it2.isCanceled) {
                it2.exception!!.printStackTrace()
            }
        }
    }
}
