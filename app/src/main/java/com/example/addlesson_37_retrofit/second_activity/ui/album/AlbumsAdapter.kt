package com.example.addlesson_37_retrofit.second_activity.ui.album

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.addlesson_37_retrofit.R
import com.example.addlesson_37_retrofit.model.Albums

import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_albums.*


class AlbumsAdapter(
    context: Context,
    private val onSelectedAlbumsId: (Albums) -> Unit
) :
    RecyclerView.Adapter<AlbumsAdapter.ViewHolder>() {

    private val albumsList: ArrayList<Albums> = arrayListOf()
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater
            .inflate(R.layout.fragment_albums, parent, false)
        return ViewHolder(view, onSelectedAlbumsId)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = albumsList[position]
        holder.onBind(item)

    }


    override fun getItemCount(): Int = albumsList.size

    fun upDateDataAlbums(photos: Array<Albums>) {
        albumsList.addAll(photos)
        notifyItemRangeInserted(0, photos.size)
    }

    class ViewHolder(
        override val containerView: View,
        onSelectedAlbumsId: (Albums) -> Unit
    ) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        private var selectAlbums: Albums? = null

        init {
            tv_albums.setOnClickListener {
                selectAlbums?.let { onSelectedAlbumsId(it) }
            }
        }

        fun onBind(item: Albums) {
            this.selectAlbums = item
            tv_albums.text = item.title

        }
    }
}