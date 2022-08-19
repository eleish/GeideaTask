package com.eleish.geideatask.app.core.extensions

import android.view.View

fun View.setGone(gone: Boolean) {
    visibility = if (gone)
        View.GONE
    else
        View.VISIBLE
}