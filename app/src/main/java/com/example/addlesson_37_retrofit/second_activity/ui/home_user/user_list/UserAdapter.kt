package com.example.addlesson_37_retrofit.second_activity.ui.home_user.user_list

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.addlesson_37_retrofit.R

import com.example.addlesson_37_retrofit.model.User
import com.example.addlesson_37_retrofit.ui.AlbumActivity
import com.example.addlesson_37_retrofit.ui.SecondActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_user.*


class UserAdapter(
    context: Context,
    private val select: (User) -> Unit
) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private val userList: MutableList<User> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater
            .inflate(R.layout.fragment_user, parent, false)
        return ViewHolder(view, select)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = userList[position]
        holder.onBind(item)

    }

    fun updateDataUser(user: Array<User>) {
        userList.clear()
        userList.addAll(user)
        notifyItemRangeInserted(0, user.size)
    }

    override fun getItemCount(): Int = userList.size

    class ViewHolder(
        override val containerView: View,
        private val select: (User) -> Unit
    ) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        private var selectedUser: User? = null

        init {
            post_item.setOnClickListener {
                selectedUser?.let { select(it) }

            }
            albums_item.setOnClickListener {
                
            }
        }

        @SuppressLint("SetTextI18n")
        fun onBind(item: User) {
            this.selectedUser = item
            item_user_name.text = item.name
            item_user_email.text = "Email : ${item.email}"
            item_user_address.text = item.address.toString()
            item_user_phone.text = "Phone : ${item.phone}"
        }
    }
}