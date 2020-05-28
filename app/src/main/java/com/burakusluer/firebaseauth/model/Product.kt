package com.burakusluer.firebaseauth.model

import java.net.URL

class Product(id: Int, ad: String, aciklama: String, resimUrl: URL) {

    var id: Int = id
    var ad: String = ad
    var aciklama: String = aciklama
    var resimUrl: URL = resimUrl
}