package com.example.addlesson_37_retrofit.second_activity.ui.home_user.comment_list

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import com.example.addlesson_37_retrofit.R
import com.example.addlesson_37_retrofit.common.USER_ID
import com.example.addlesson_37_retrofit.global.BaseFragment
import com.example.addlesson_37_retrofit.model.Comments
import com.example.addlesson_37_retrofit.network.RestApiImpl
import kotlinx.android.synthetic.main.fragment_comment_item_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentFragment : BaseFragment() {
    private var adapter: CommentAdapter? = null
    override val layoutRes: Int = R.layout.fragment_comment_item_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_list_comment.layoutManager = LinearLayoutManager(context)
        this.adapter = CommentAdapter(requireActivity())
        rv_list_comment.adapter = adapter
        loadPosts()
    }

    private fun loadPosts() {
        val pref = PreferenceManager.getDefaultSharedPreferences(activity)
        val postId = pref.getInt(USER_ID, 0)
        if (postId == 0) return
        RestApiImpl.restApi.loadUserPostComments(postId).enqueue(object : Callback<Array<Comments>> {
            override fun onResponse(call: Call<Array<Comments>>, response: Response<Array<Comments>>) {
                val pos = response.body()
                if (pos != null) {
                    adapter?.updateDataPost(pos)
                }
            }

            override fun onFailure(call: Call<Array<Comments>>, t: Throwable) {
                Log.d("error", t.message)

            }

        })
    }

}