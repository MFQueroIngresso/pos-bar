package com.barqueroingresso.util

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.GONE
}