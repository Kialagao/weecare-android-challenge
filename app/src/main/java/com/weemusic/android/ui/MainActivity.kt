package com.weemusic.android.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import com.weemusic.android.R
import com.weemusic.android.di.Injector
import com.weemusic.android.domain.GetTopAlbumsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
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

    private lateinit var adapter: AlbumsAdapter
    private lateinit var topAlbumsDisposable: Disposable
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as Injector).createMainSubComponent().inject(this)

        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        val responseLiveData = mainViewModel.getTopAlbums()
        responseLiveData.observe(this, {
            it.forEach { album ->
                Log.i("Result", album.name.toString())
            }
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

    class AlbumsAdapter(val albums: List<JsonObject>) : RecyclerView.Adapter<AlbumsViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
            val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.album_view_holder, parent, false)

            return AlbumsViewHolder(itemView)
        }

        override fun getItemCount(): Int = albums.size

        override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) =
            holder.onBind(albums[position])
    }

    class AlbumsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(album: JsonObject) {
            val coverUrl = album
                .getAsJsonArray("im:image")
                .last()
                .asJsonObject
                .getAsJsonPrimitive("label")
                .asString
            val title = album
                .getAsJsonObject("im:name")
                .getAsJsonPrimitive("label")
                .asString
            val artist = album
                .getAsJsonObject("im:artist")
                .getAsJsonPrimitive("label")
                .asString

            val ivCover: ImageView = itemView.findViewById(R.id.ivCover)
            val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
            val tvArtist: TextView = itemView.findViewById(R.id.tvArtist)

            Picasso.with(itemView.context).load(coverUrl).into(ivCover)
            tvTitle.text = title
            tvArtist.text = artist
        }
    }
}
