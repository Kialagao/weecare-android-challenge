package com.weemusic.android.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.weemusic.android.R
import com.weemusic.android.di.Injector
import com.weemusic.android.domain.usecases.GetTopAlbumsUseCase
import com.weemusic.android.ui.adapter.AlbumsAdapter
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var getTopAlbumsUseCase: GetTopAlbumsUseCase

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    @Inject
    lateinit var adapter: AlbumsAdapter
    private lateinit var topAlbumsDisposable: Disposable
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as Injector).createMainSubComponent().inject(this)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        mainViewModel.unsortedAlbums.observe(this, {
            adapter.submitList(it)
            //sortAlbumsBy("Hello")
        })

        mainViewModel.sortedAlbums.observe(this, {
            it.forEach { album ->
                Log.i("Result", album.price)
            }
            adapter.submitList(it)
        })

        // Observer for the network error.
        mainViewModel.onError.observe(this, { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
        /*
        val networkComponent = DaggerNetworkComponent.create()
        val domainComponent = DaggerDomainComponent
            .builder()
            .networkComponent(networkComponent)
            .build()

        DaggerAppComponent
            .builder()
            .domainComponent(domainComponent)
            .build()
            .inject(this)*/
    }

    override fun onStart() {
        super.onStart()

        rvFeed.adapter = adapter
        /*
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)*/
        CoroutineScope(Dispatchers.IO).launch {
            try {
                //getTopAlbumsUseCase.fetch()


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        /*
        topAlbumsDisposable = getTopAlbumsUseCase
            .perform()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response ->
                response.getAsJsonObject("feed")
                    .getAsJsonArray("entry")
                    .map { it.asJsonObject }
            }
            .subscribe(Consumer {
                adapter = AlbumsAdapter(it)
                rvFeed.adapter = adapter
                rvFeed.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            })*/
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
    private fun onNetworkError() {
        if(!mainViewModel.isErrorShown.value!!) {
            Toast.makeText(this, "Something went wrong...", Toast.LENGTH_LONG).show()
            mainViewModel.onErrorShown()
        }
    }
}
