package com.example.addlesson_37_retrofit.second_activity.ui.album.photos_albumsId

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.addlesson_37_retrofit.R
import com.example.addlesson_37_retrofit.common.USER_ID
import com.example.addlesson_37_retrofit.global.BaseFragment
import com.example.addlesson_37_retrofit.model.Photos
import com.example.addlesson_37_retrofit.model.PhotosAlbumId
import com.example.addlesson_37_retrofit.network.RestApiImpl
import kotlinx.android.synthetic.main.fragment_albums_photos_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumsPhotosFragment : BaseFragment() {
    private var adapter: AlbumsPhotosAdapter? = null
    private val columnCount = 3
    override val layoutRes: Int = R.layout.fragment_albums_photos_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_photos_albums.layoutManager = GridLayoutManager(context, columnCount)
        this.adapter = AlbumsPhotosAdapter(requireActivity())
        rv_photos_albums.adapter = adapter
        loadAlbumsId()
    }

    private fun loadAlbumsId() {
        val pref = PreferenceManager.getDefaultSharedPreferences(activity)
        val albumsId = pref.getInt(USER_ID, 0)
        if (albumsId == 0) return
        RestApiImpl.restApi.loadPhotosByAlbums(albumsId)
            .enqueue(object : Callback<Array<PhotosAlbumId>> {
                override fun onResponse(
                    call: Call<Array<PhotosAlbumId>>,
                    response: Response<Array<PhotosAlbumId>>
                ) {
                    val pos = response.body()
                    if (pos != null) {
                        adapter?.updateDataAlbumsPhoto(pos)
                    }
                }

                override fun onFailure(call: Call<Array<PhotosAlbumId>>, t: Throwable) {
                    Log.d("error", t.message)

                }

            })
    }

}