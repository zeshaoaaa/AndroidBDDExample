package org.jay.example

import android.widget.Toast
import org.jay.example.MyApplication.Companion.application

private var toast: Toast? = null

fun String?.showToast() {
    this ?: return
    if (toast == null) {
        toast = Toast.makeText(application, this, Toast.LENGTH_SHORT)
    } else {
        toast?.setText(this)
    }
    toast?.show()
}