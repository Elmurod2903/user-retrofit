package com.example.addlesson_37_retrofit.second_activity.ui.album.photos_albumsId

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.addlesson_37_retrofit.R
import com.example.addlesson_37_retrofit.model.Comments
import com.example.addlesson_37_retrofit.model.Photos
import com.example.addlesson_37_retrofit.model.PhotosAlbumId
import com.squareup.picasso.Picasso

import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_comment.*
import kotlinx.android.synthetic.main.fragment_photos_albums.*
import org.w3c.dom.Comment


class AlbumsPhotosAdapter(context: Context) :
    RecyclerView.Adapter<AlbumsPhotosAdapter.ViewHolder>() {

    private val ALbumsIdPhotosList: ArrayList<PhotosAlbumId> = arrayListOf()
    private val inflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater
            .inflate(R.layout.fragment_photos_albums, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = ALbumsIdPhotosList[position]
        holder.onBind(item)

    }

    fun updateDataAlbumsPhoto(post: Array<PhotosAlbumId>) {
    //    ALbumsIdPhotosList.clear()
        ALbumsIdPhotosList.addAll(post)
        notifyItemRangeInserted(0, post.size)
    }

    override fun getItemCount(): Int = ALbumsIdPhotosList.size

    class ViewHolder(override val containerView: View) :

        RecyclerView.ViewHolder(containerView), LayoutContainer {


        fun onBind(item: PhotosAlbumId) {
            photos_albums_title.text = item.title
            Picasso.get().load(item.url).into(item_photos_albums)
        }
    }
}