package com.example.addlesson_37_retrofit.second_activity.ui.home_user.user_list

import  android.annotation.SuppressLint
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.addlesson_37_retrofit.R
import com.example.addlesson_37_retrofit.global.BaseFragment
import com.example.addlesson_37_retrofit.global.MyViewModel
import com.example.addlesson_37_retrofit.model.User
import com.example.addlesson_37_retrofit.network.RestApiImpl
import kotlinx.android.synthetic.main.fragment_item_user_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("UseRequireInsteadOfGet")
class UserFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {
    private var adapter: UserAdapter? = null
    override val layoutRes: Int = R.layout.fragment_item_user_list
    private val listCall: MutableList<Call<*>> = mutableListOf()
    private val myViewModel by lazy {
        ViewModelProvider(activity!!).get(MyViewModel::class.java)
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_list_user.layoutManager = LinearLayoutManager(context)
        this.adapter = UserAdapter(context!!, this::onSelectedUser)
        rv_list_user.adapter = adapter
        loadUsers()
        swipe_refresh.isRefreshing = true
        swipe_refresh.setOnRefreshListener(this)
    }

    private fun onSelectedUser(user: User) {
        myViewModel.liveDataUser.value = user
    }

    private fun loadUsers() {

        val call = RestApiImpl.restApi.loadUsers()
        call.enqueue(object : Callback<Array<User>> {
            override fun onResponse(call: Call<Array<User>>, response: Response<Array<User>>) {
                val user = response.body()
                if (user != null) {
                    adapter?.updateDataUser(user)

                }
                swipe_refresh.isRefreshing = false
            }

            override fun onFailure(call: Call<Array<User>>, t: Throwable) {
                //todo show snackbar
                //  Log.d("error", t.message)
                swipe_refresh.isRefreshing = false

            }

        })
        listCall += call
//        listCall.add(call)
    }

    override fun onDestroy() {
        super.onDestroy()
        for (it in listCall) {
            it.cancel()

        }
    }

    override fun onRefresh() {
        swipe_refresh.isRefreshing = false


    }

}