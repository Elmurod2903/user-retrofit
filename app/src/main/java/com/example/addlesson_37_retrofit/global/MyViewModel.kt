package com.example.addlesson_37_retrofit.global

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.addlesson_37_retrofit.model.Albums
import com.example.addlesson_37_retrofit.model.Photos
import com.example.addlesson_37_retrofit.model.Post
import com.example.addlesson_37_retrofit.model.User

class MyViewModel : ViewModel() {
    val liveDataUser: MutableLiveData<User> by lazy { MutableLiveData() }
    val liveDataPOst: MutableLiveData<Post> by lazy { MutableLiveData() }
    val liveDataAlbumsId: MutableLiveData<Albums> by lazy { MutableLiveData() }


}