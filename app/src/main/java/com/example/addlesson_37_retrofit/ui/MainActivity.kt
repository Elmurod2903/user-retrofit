package com.example.addlesson_37_retrofit.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.lifecycle.ViewModelProvider
import com.example.addlesson_37_retrofit.R
import com.example.addlesson_37_retrofit.common.USER_ID
import com.example.addlesson_37_retrofit.global.MyViewModel
import com.example.addlesson_37_retrofit.model.Photos
import com.example.addlesson_37_retrofit.model.Post
import com.example.addlesson_37_retrofit.model.User

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        myViewModel.liveDataPOst.observe(this, {
           startActivities(it)
        })

    }


    private fun startActivities(it: Post?) {
        if (it == null) return
        val intent = Intent(this, CommentActivity::class.java)
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit().putInt(USER_ID, it.id).apply()
        startActivity(intent)
    }


}