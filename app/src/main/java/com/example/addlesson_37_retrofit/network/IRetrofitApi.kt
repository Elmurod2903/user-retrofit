package com.example.addlesson_37_retrofit.network

import com.example.addlesson_37_retrofit.common.*
import com.example.addlesson_37_retrofit.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofitApi {

    @GET(POST)
    fun loadPost(): Call<Array<Post>>

    @GET(USERS)
    fun loadUsers(): Call<Array<User>>

    @GET(POST)
    fun loadPostUserId(@Query("userId") id: Int): Call<Array<Post>>

    @GET(COMMENTS)
    fun loadUserPostComments(@Query("postId") id: Int): Call<Array<Comments>>

    @GET(PHOTOS)
    fun loadPhotos(): Call<Array<Photos>>

    @GET(ALBUMS)
    fun loadAlbums(): Call<Array<Albums>>
    @GET(PHOTOS)
    fun  loadPhotosByAlbums(@Query("albumId")id: Int):Call<Array<PhotosAlbumId>>
    @GET(ALBUMS)
    fun  loadUserIdAlbums(@Query("userId")id:Int):Call<Array<Albums>>
}