package com.example.addlesson_37_retrofit.second_activity.ui.home_user.post_list

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.addlesson_37_retrofit.R

import com.example.addlesson_37_retrofit.model.Post
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_post.*


class PostAdapter(context: Context, val selectAction: (Post) -> Unit) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private val postList: ArrayList<Post> = arrayListOf()
    private val inflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater
            .inflate(R.layout.fragment_post, parent, false)
        return ViewHolder(view, selectAction)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = postList[position]
        holder.onBind(item)

    }

    fun updateDataPost(post: Array<Post>) {
        postList.clear()
        postList.addAll(post)
        notifyItemRangeInserted(0, post.size)
    }

    override fun getItemCount(): Int = postList.size

    class ViewHolder(override val containerView: View, selectAction: (Post) -> Unit) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        private var selesctedPOst: Post? = null

        init {
            tv_comment.setOnClickListener {
                selesctedPOst?.let { selectAction(it) }
            }
        }

        @SuppressLint("SetTextI18n")
        fun onBind(item: Post) {
            this.selesctedPOst=item
            item_title.text = item.title
            item_body.text = item.body
        }
    }
}