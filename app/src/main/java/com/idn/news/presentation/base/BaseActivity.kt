package com.idn.news.presentation.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.idn.news.external.Constant.PAGE_START
import com.idn.news.external.dialog.ProgressDialog.setProgressDialog

abstract class BaseActivity<DataBinding : ViewDataBinding> : AppCompatActivity() {

    private lateinit var progressDialog: AlertDialog
    protected lateinit var bind: DataBinding

    protected var currentPage = PAGE_START
    protected var totalPage = 1
    protected var isLastPage = false
    protected var isLoading = false

    protected abstract fun createBinding(): DataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = createBinding()
        progressDialog = setProgressDialog(this@BaseActivity).create()
    }

    override fun onDestroy() {
        super.onDestroy()
        hideLoading()
    }

    protected inline fun <reified ClassActivity> intents(context: Context) {
        context.startActivity(Intent(context, ClassActivity::class.java))
    }

    protected fun showLoading() {
        progressDialog.show()
    }

    protected fun hideLoading() {
        progressDialog.dismiss()
    }
}
