package com.example.addlesson_37_retrofit.second_activity.ui.photos

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.addlesson_37_retrofit.R
import com.example.addlesson_37_retrofit.common.BASE_URL
import com.example.addlesson_37_retrofit.common.PHOTOS
import com.example.addlesson_37_retrofit.model.Comments
import com.example.addlesson_37_retrofit.model.Photos
import com.squareup.picasso.Picasso

import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_comment.*
import kotlinx.android.synthetic.main.fragment_photos.*


class PhotosAdapter(context: Context) :
    RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    private val gallaryList: ArrayList<Photos> = arrayListOf()
    private val inflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater
            .inflate(R.layout.fragment_photos, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = gallaryList[position]
        holder.onBind(item)

    }


    override fun getItemCount(): Int = gallaryList.size

    fun upDateDataPhotos(photos: Array<Photos>) {
        gallaryList.addAll(photos)
        notifyItemRangeInserted(0, photos.size)
    }

    class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {


        fun onBind(item: Photos) {
            photos_title.text = item.title
            Picasso.get().load(item.url).into(item_photos)

        }
    }
}