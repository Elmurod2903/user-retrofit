package com.example.addlesson_37_retrofit.second_activity.ui.photos

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.addlesson_37_retrofit.R
import com.example.addlesson_37_retrofit.global.BaseFragment
import com.example.addlesson_37_retrofit.model.Photos
import com.example.addlesson_37_retrofit.network.RestApiImpl
import kotlinx.android.synthetic.main.fragment_photos_list.*
import retrofit2.Call
import retrofit2.Response

class PhotosFragment : BaseFragment() {
    private var adapter: PhotosAdapter? = null
    private val columnCount = 3

    override val layoutRes: Int = R.layout.fragment_photos_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_gallery.layoutManager = GridLayoutManager(activity, columnCount)
        this.adapter = PhotosAdapter(requireActivity())
        rv_gallery.adapter = adapter

        loadPhotos()
    }

    private fun loadPhotos() {

        RestApiImpl.restApi.loadPhotos()
            .enqueue(object : retrofit2.Callback<Array<Photos>> {
                override fun onResponse(
                    call: Call<Array<Photos>>,
                    response: Response<Array<Photos>>
                ) {
                    val photos = response.body()
                    if (photos != null) {
                        adapter?.upDateDataPhotos(photos)
                    }
                }

                override fun onFailure(call: Call<Array<Photos>>, t: Throwable) {

                }

            })
    }


}