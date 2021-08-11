package com.example.addlesson_37_retrofit.network

import com.example.addlesson_37_retrofit.common.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestApiImpl {
    val restApi: IRetrofitApi by lazy {
        val retrofit = initRetrofit()
        retrofit.create(IRetrofitApi::class.java)
    }

    private fun initRetrofit(): Retrofit {
      return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}