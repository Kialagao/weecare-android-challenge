package com.weemusic.android.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.weemusic.android.R
import com.weemusic.android.di.Injector
import com.weemusic.android.domain.usecases.GetTopAlbumsUseCase
import com.weemusic.android.ui.adapter.AlbumsAdapter
import com.weemusic.android.ui.viewmodel.MainViewModel
import com.weemusic.android.ui.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var getTopAlbumsUseCase: GetTopAlbumsUseCase

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    @Inject
    lateinit var adapter: AlbumsAdapter

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as Injector).createMainSubComponent().inject(this)

        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        setUpToolbar()
        subscribeToLiveData()
        setRefreshLayout()
    }

    override fun onStart() {
        super.onStart()

        rvFeed.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            R.id.action_price ->  {
                mainViewModel.getSortedAlbums("price")
                true
            }
            R.id.action_title -> {
                mainViewModel.getSortedAlbums("title")
                true
            }
            R.id.action_albums -> {
                if (mainViewModel.unsortedAlbums.value != null) {
                    adapter.submitList(mainViewModel.unsortedAlbums.value!!)
                } else {
                    mainViewModel.getTopAlbums()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setUpToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun subscribeToLiveData() {
        mainViewModel.unsortedAlbums.observe(this, {
            adapter.submitList(it)
        })

        mainViewModel.sortedAlbums.observe(this, {
            adapter.submitList(it)
        })

        // Observer for fetching error.
        mainViewModel.onError.observe(this, { isNetworkError ->
            if (isNetworkError) onFetchError()
        })

        mainViewModel.isRefreshing.observe(this, { isRefreshing ->
            if (!isRefreshing) rvFeed.visibility = View.VISIBLE
            swipeRefreshLayout.isRefreshing = isRefreshing
        })
    }

    private fun setRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener {
            rvFeed.visibility = View.INVISIBLE
            mainViewModel.updateTopAlbums()
        }
    }

    private fun onFetchError() {
        Toast.makeText(this, "Something went wrong...", Toast.LENGTH_LONG).show()
    }
}
