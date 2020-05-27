package com.burakusluer.firebaseauth

import android.content.Context
import android.content.Intent

fun activityTraveler(context: Context, intent: Intent) {
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    context.startActivity(intent)
}