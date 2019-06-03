package com.samoana.minimalistyoutube.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.samoana.minimalistyoutube.R
import com.samoana.minimalistyoutube.data.Item
import com.samoana.minimalistyoutube.utils.ResultsDiffUtil
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.result_item.view.*



class ResultsAdapter(private val context: Context): RecyclerView.Adapter<ResultsAdapter.ResultViewHolder>() {

    private var items : List<Item> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val eventItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.result_item, parent, false) as View
        return ResultViewHolder(eventItem)
    }

    override fun getItemCount(): Int {
      return items.size
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
      val item = items[position]
        Picasso.get()
            .load(item.snippet.thumbnails.default.url)
            .placeholder(R.drawable.default_image_thumbnail)
            .resize(128, 72)
            .centerCrop()
            .into(holder.thumbnail)
        holder.title.text = item.snippet.title
        holder.resultItem.setOnClickListener {
            val intent = Intent(context, VideoPlayerActivity::class.java)
            intent.putExtra("video_id", item.id.videoId)
            intent.putExtra("title", item.snippet.title)
            intent.putExtra("description", item.snippet.description)
            context.startActivity(intent)
        }
    }

    fun updateEvents(items: List<Item>) {
        val diffResult = DiffUtil.calculateDiff(ResultsDiffUtil(this.items, items), true)
        this.items = items
        diffResult.dispatchUpdatesTo(this)
    }

    class ResultViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val view = view
        val thumbnail = view.thumbnail!!
        val title = view.title!!
        val resultItem = view.result_item!!
    }

}