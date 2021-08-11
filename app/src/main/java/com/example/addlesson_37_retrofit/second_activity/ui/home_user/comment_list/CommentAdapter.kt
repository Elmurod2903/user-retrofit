package com.example.addlesson_37_retrofit.second_activity.ui.home_user.comment_list

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.addlesson_37_retrofit.R
import com.example.addlesson_37_retrofit.model.Comments

import com.example.addlesson_37_retrofit.model.Post
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_comment.*
import kotlinx.android.synthetic.main.fragment_post.*
import org.w3c.dom.Comment


class CommentAdapter(context: Context) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    private val commentList: ArrayList<Comments> = arrayListOf()
    private val inflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater
            .inflate(R.layout.fragment_comment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = commentList[position]
        holder.onBind(item)

    }

    fun updateDataPost(post: Array<Comments>) {
        commentList.clear()
        commentList.addAll(post)
        notifyItemRangeInserted(0, post.size)
    }

    override fun getItemCount(): Int = commentList.size

    class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {


        fun onBind(item: Comments) {
          item_comment_name.text=item.name
          item_comment_email.text=item.email
          item_comment_body.text=item.body


        }
    }
}