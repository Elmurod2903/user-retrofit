package com.example.addlesson_37_retrofit.second_activity.ui.home_user.post_list

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.addlesson_37_retrofit.R
import com.example.addlesson_37_retrofit.common.USER_ID
import com.example.addlesson_37_retrofit.global.BaseFragment
import com.example.addlesson_37_retrofit.global.MyViewModel
import com.example.addlesson_37_retrofit.model.Post
import com.example.addlesson_37_retrofit.network.RestApiImpl
import kotlinx.android.synthetic.main.fragment_post_item_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostFragment : BaseFragment() {
    private var adapter: PostAdapter? = null
    override val layoutRes: Int = R.layout.fragment_post_item_list
    private val call: MutableList<Call<*>> = mutableListOf()
    private val myViewModel by lazy { ViewModelProvider(requireActivity()).get(MyViewModel::class.java) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_list_post.layoutManager = LinearLayoutManager(context)
        this.adapter = PostAdapter(requireActivity(), this::onselectedPost)
        rv_list_post.adapter = adapter
        loadPosts()
    }

    private fun onselectedPost(post: Post) {
        myViewModel.liveDataPOst.value = post

    }

    private fun loadPosts() {
        val pref = PreferenceManager.getDefaultSharedPreferences(activity)
        val userId = pref.getInt(USER_ID, 0)
        if (userId == 0) return
        RestApiImpl.restApi.loadPostUserId(userId)
            .enqueue(object : Callback<Array<Post>> {
                override fun onResponse(
                    call: Call<Array<Post>>,
                    response: Response<Array<Post>>
                ) {
                    val pos = response.body()
                    if (pos != null) {
                        adapter?.updateDataPost(pos)
                    }
                }

                override fun onFailure(call: Call<Array<Post>>, t: Throwable) {
                    Log.d("error", t.message)

                }

            })
    }

    override fun onDestroy() {
        super.onDestroy()
        for (call in call) {
            call.cancel()
        }
    }

}