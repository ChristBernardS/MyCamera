package com.example.mycamera.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycamera.R
import com.example.mycamera.adapter.StoryAdapter
import com.example.mycamera.database.DatabaseHelper
import androidx.core.net.toUri
import com.example.mycamera.model.Story

class HomeActivity : AppCompatActivity() {

    private lateinit var storyRecyclerView: RecyclerView
    private lateinit var cameraButton: ImageButton
    private lateinit var chatButton: ImageButton
    private lateinit var notificationButton: ImageButton
    private lateinit var profileButton: ImageButton
    private lateinit var storyAdapter: StoryAdapter
    private val stories = mutableListOf<Story>()
    private lateinit var databaseHelper: DatabaseHelper

    private val cameraLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val imageUriString = result.data?.getStringExtra("IMAGE_URI")
                imageUriString?.let { uriString ->
                    val imageUri = uriString.toUri()

                    // Cek apakah gambar sudah ada di database
                    if (!databaseHelper.isImageExists(imageUri.toString())) {
                        databaseHelper.insertImage(imageUri.toString())

                        // Tambahkan ke daftar stories tanpa perlu reload semua
                        val newStory = Story("Captured Image", imageUri.toString())
                        stories.add(newStory)
                        storyAdapter.notifyItemInserted(stories.size - 1) // Perbarui RecyclerView
                    }
                }
            } else {
                Log.e("HomeActivity", "IMAGE_URI tidak ditemukan dalam Intent")
            }
        }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        databaseHelper = DatabaseHelper(this)
        storyRecyclerView = findViewById(R.id.recyclerViewStories)
        cameraButton = findViewById(R.id.btnCamera)
        profileButton = findViewById(R.id.btnProfile)

        chatButton = findViewById(R.id.btnChat)
        notificationButton = findViewById(R.id.btnNotification)

        // Setup RecyclerView dengan onClick listener
        // Pastikan storyAdapter diberikan onItemClick
        storyAdapter = StoryAdapter(stories) { story ->
            Log.d("HomeActivity", "Klik item: ${story.name}, path: ${story.imagePath}")

            val intent = Intent(this, ImageDetailActivity::class.java).apply {
                putExtra("IMAGE_PATH", story.imagePath)
                putExtra("STORY_NAME", story.name)
            }
            startActivity(intent)
        }



        storyRecyclerView.layoutManager = LinearLayoutManager(this)
        storyRecyclerView.adapter = storyAdapter

        loadImagesFromDatabase()

        cameraButton.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            cameraLauncher.launch(intent)
        }

        chatButton.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }

        notificationButton.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }

        profileButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }


    private fun loadImagesFromDatabase() {
        val newImages = databaseHelper.getAllImages() // Ambil semua URI gambar dari database
        stories.clear() // Kosongkan list lama
        stories.addAll(newImages.map { Story("Captured Image", it) }) // Tambahkan gambar baru
        storyAdapter.notifyDataSetChanged() // Perbarui RecyclerView
    }

    override fun onResume() {
        super.onResume()
        loadImagesFromDatabase() // Reload data dari database setiap kali kembali ke HomeActivity
    }

}
