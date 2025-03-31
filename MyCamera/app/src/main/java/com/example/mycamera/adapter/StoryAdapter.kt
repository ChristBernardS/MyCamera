package com.example.mycamera.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mycamera.R
import com.example.mycamera.model.Story
import com.example.mycamera.view.ImageDetailActivity

class StoryAdapter(
    private val stories: List<Story>,
    private val onItemClick: (Story) -> Unit
) : RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

    inner class StoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val storyImage: ImageView = itemView.findViewById(R.id.imageStory)
        val storyName: TextView = itemView.findViewById(R.id.textStoryName)

        fun bind(story: Story) {
            storyName.text = story.name
            Glide.with(itemView.context)
                .load(story.imagePath)
                .into(storyImage)

            itemView.setOnClickListener {
                Log.d("StoryAdapter", "Item diklik: ${story.name}, path: ${story.imagePath}")
                onItemClick(story) // Panggil fungsi klik
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return StoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(stories[position])
    }

    override fun getItemCount(): Int = stories.size
}
