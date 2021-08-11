package com.example.addlesson_37_retrofit.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.lifecycle.ViewModelProvider
import com.example.addlesson_37_retrofit.R
import com.example.addlesson_37_retrofit.common.USER_ID
import com.example.addlesson_37_retrofit.global.MyViewModel
import com.example.addlesson_37_retrofit.model.Albums
import com.example.addlesson_37_retrofit.model.Photos

class AlbumActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        val myViewModelAlbums = ViewModelProvider(this).get(MyViewModel::class.java)
        myViewModelAlbums.liveDataAlbumsId.observe(this,{
            startActivities(it)

        })
    }

    private fun startActivities(it: Albums?) {

        if (it ==null) return
        val intent = Intent(this, AlbumPhotosActivity::class.java)
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit().putInt(USER_ID, it.id).apply()
        startActivity(intent)

    }


}