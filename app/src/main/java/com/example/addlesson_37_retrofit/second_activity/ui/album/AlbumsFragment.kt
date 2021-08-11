package com.example.addlesson_37_retrofit.second_activity.ui.album

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.addlesson_37_retrofit.R
import com.example.addlesson_37_retrofit.global.BaseFragment
import com.example.addlesson_37_retrofit.global.MyViewModel
import com.example.addlesson_37_retrofit.model.Albums
import com.example.addlesson_37_retrofit.model.Photos
import com.example.addlesson_37_retrofit.network.RestApiImpl
import kotlinx.android.synthetic.main.fragment_albums_list.*
import retrofit2.Call
import retrofit2.Response

class AlbumsFragment : BaseFragment() {
    private var adapter: AlbumsAdapter? = null

    override val layoutRes: Int = R.layout.fragment_albums_list
    private val myViewModel by lazy { ViewModelProvider(requireActivity()).get(MyViewModel::class.java) }
    private val listCall: MutableList<Call<*>> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_albums.layoutManager = LinearLayoutManager(activity)
        this.adapter = AlbumsAdapter(requireActivity(), ::onSelectedAlbumsId)
        rv_albums.adapter = adapter

        loadAlbums()
    }

    private fun onSelectedAlbumsId(albums: Albums) {
        myViewModel.liveDataAlbumsId.postValue(albums)
    }

    private fun loadAlbums() {

        RestApiImpl.restApi.loadAlbums()
            .enqueue(object : retrofit2.Callback<Array<Albums>> {
                override fun onResponse(
                    call: Call<Array<Albums>>,
                    response: Response<Array<Albums>>
                ) {
                    val albums = response.body()
                    if (albums != null) {
                        adapter?.upDateDataAlbums(albums)
                    }
                }

                override fun onFailure(call: Call<Array<Albums>>, t: Throwable) {

                }

            })
    }

    override fun onDestroy() {
        super.onDestroy()
        for (it in listCall) {
            it.cancel()
        }
    }


}