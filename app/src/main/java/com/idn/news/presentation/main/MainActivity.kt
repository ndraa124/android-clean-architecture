package com.idn.news.presentation.main

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.idn.news.R
import com.idn.news.databinding.ActivityMainBinding
import com.idn.news.external.Alert.showSnackbarAlert
import com.idn.news.external.Constant
import com.idn.news.external.binding.Widget.gone
import com.idn.news.external.binding.Widget.visible
import com.idn.news.external.recyclerview.LoadMoreStateAdapter
import com.idn.news.external.recyclerview.LoadStateListener
import com.idn.news.presentation.base.BaseActivity
import com.idn.news.presentation.main.adapter.MainAdapter
import com.idn.news.viewmodel.NewsViewModel
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var layoutManager: LinearLayoutManager
    private val viewModel: NewsViewModel by viewModel()
    private val mainPagingAdaper: MainAdapter by lazy { MainAdapter() }
    private var textQuery: String? = null

    override fun createBinding(): ActivityMainBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.lifecycleOwner = this
        bind.srNews.setOnRefreshListener(this)

        setMainPagingAdapter()
        getAllNews()
    }

    override fun onRefresh() {
        textQuery = null
        getAllNews()
        bind.srNews.isRefreshing = false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val itemSearch = menu?.findItem(R.id.nav_search)
        val searchView = itemSearch?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    textQuery = query
                    getAllNews()
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    textQuery = null
                    getAllNews()
                }

                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun setMainPagingAdapter() {
        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        bind.rvNews.apply {
            layoutManager = layoutManager
            adapter = mainPagingAdaper.withLoadStateFooter(LoadMoreStateAdapter())
        }

        mainPagingAdaper.addLoadStateListener { loadState ->
            bind.rvNews.isVisible = LoadStateListener(loadState).showNotLoadingState()

            if (LoadStateListener(loadState).showLoadingState()) {
                showLoading()
            } else {
                hideLoading()
                showSuccessLayout()
            }

            if (LoadStateListener(loadState).showEmptyState(mainPagingAdaper.itemCount)) {
                showEmptyLayout()
            } else {
                showSuccessLayout()
            }

            if (LoadStateListener(loadState).showErrorState()) {
                when (LoadStateListener(loadState).getCodeErrorState()) {
                    404 -> showEmptyLayout()
                    else -> {
                        showSnackbarAlert(
                            this,
                            getString(R.string.loading_error),
                            Constant.LENGTH_SHORT
                        )
                    }
                }
            }
        }
    }

    private fun getAllNews() {
        viewModel.getAllNews(textQuery).observe(bind.lifecycleOwner!!) { result ->
            if (result != null) {
                lifecycleScope.launch {
                    mainPagingAdaper.submitData(result)
                }
            }
        }
    }

    private fun showSuccessLayout() {
        bind.rvNews.visible()
        bind.layoutEmpty.clLayout.gone()
    }

    private fun showEmptyLayout() {
        bind.rvNews.gone()
        bind.layoutEmpty.clLayout.visible()
    }
}
