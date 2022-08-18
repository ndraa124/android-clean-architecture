package com.idn.news.external.recyclerview

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.idn.news.external.Constant

class LoadStateListener(private val loadState: CombinedLoadStates) {
    fun showLoadingState(): Boolean {
        return loadState.source.refresh is LoadState.Loading
    }

    fun showNotLoadingState(): Boolean {
        return loadState.source.refresh is LoadState.NotLoading
    }

    fun showEmptyState(itemCount: Int): Boolean {
        return loadState.refresh is LoadState.NotLoading && itemCount == 0
    }

    fun showErrorState(): Boolean {
        return loadState.source.refresh is LoadState.Error
    }

    fun getCodeErrorState(): Int {
        val errCode = (loadState.source.refresh as LoadState.Error).error.localizedMessage

        return if (errCode?.contains(Constant.ERROR_400) == true) {
            400
        } else if (errCode?.contains(Constant.ERROR_401) == true) {
            401
        } else if (errCode?.contains(Constant.ERROR_402) == true) {
            402
        } else if (errCode?.contains(Constant.ERROR_403) == true) {
            403
        } else if (errCode?.contains(Constant.ERROR_404) == true) {
            404
        } else if (errCode?.contains(Constant.ERROR_406) == true) {
            406
        } else if (errCode?.contains(Constant.ERROR_500) == true) {
            500
        } else if (errCode?.contains(Constant.ERROR_502) == true) {
            502
        } else if (errCode?.contains(Constant.ERROR_503) == true) {
            503
        } else {
            999
        }
    }
}
