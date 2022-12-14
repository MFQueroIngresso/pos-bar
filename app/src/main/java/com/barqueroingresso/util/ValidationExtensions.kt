package com.barqueroingresso.util

import androidx.core.util.PatternsCompat

fun String?.isValidEmail(): Boolean {
    if(this.isNullOrEmpty()){
        return false
    }
    return PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()
}