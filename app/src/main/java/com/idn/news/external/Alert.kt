package com.idn.news.external

import android.app.Activity
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.idn.news.R

object Alert {
    fun showSnackbarAlert(activity: Activity, txt: String, length: String? = null) {
        val snackbar: Snackbar = when (length) {
            Constant.LENGTH_LONG -> {
                Snackbar.make(
                    activity.findViewById(android.R.id.content),
                    txt,
                    Snackbar.LENGTH_LONG
                )
            }
            Constant.LENGTH_INDEFINITE -> {
                Snackbar.make(
                    activity.findViewById(android.R.id.content),
                    txt,
                    Snackbar.LENGTH_INDEFINITE
                )
            }
            else -> {
                Snackbar.make(
                    activity.findViewById(android.R.id.content),
                    txt,
                    Snackbar.LENGTH_SHORT
                )
            }
        }

        snackbar.setActionTextColor(
            ContextCompat.getColor(
                activity,
                R.color.colorSecondary
            )
        )
        snackbar.setAction("X") {
            snackbar.dismiss()
        }

        snackbar.show()
    }

    fun showToastAlert(activity: Activity, txt: String, length: String? = null) {
        if (length == Constant.LENGTH_LONG) {
            Toast.makeText(activity, txt, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(activity, txt, Toast.LENGTH_SHORT).show()
        }
    }
}
