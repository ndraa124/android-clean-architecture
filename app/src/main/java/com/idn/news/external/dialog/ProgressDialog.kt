package com.idn.news.external.dialog

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.idn.news.R

object ProgressDialog {
    @SuppressLint("InflateParams")
    fun setProgressDialog(activity: Activity): AlertDialog.Builder {
        val inflater = activity.layoutInflater
        val dialogView = inflater.inflate(R.layout.layout_progress_dialog, null)

        return AlertDialog.Builder(activity)
            .setView(dialogView)
            .setCancelable(false)
    }
}
