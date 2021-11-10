package com.mutualmobile.mmnotes.android.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.showToast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, duration).show()
}

fun View.showSnackbar(
    msg: String,
    actionLabel: String,
    action: () -> Unit = {},
    duration: Int = Snackbar.LENGTH_SHORT
) {
    Snackbar.make(this.context, this, msg, duration).setAction(actionLabel) { action() }.show()
}
