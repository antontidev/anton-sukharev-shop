package android.example.shop.utils

import android.widget.TextView

fun TextView.setHeader(id: Int) {
    text = resources.getString(id)
}