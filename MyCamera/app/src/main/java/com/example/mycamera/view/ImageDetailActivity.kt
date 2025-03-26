package com.example.mycamera.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mycamera.R
import com.example.mycamera.database.DatabaseHelper

class ImageDetailActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var btnDelete: ImageView
    private var imagePath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_detail)

        databaseHelper = DatabaseHelper(this)

        imageView = findViewById(R.id.imageViewDetail)
        textView = findViewById(R.id.imageName)
        btnDelete = findViewById(R.id.btnDelete)

        // Ambil data dari intent
        imagePath = intent.getStringExtra("IMAGE_PATH")
        val storyName = intent.getStringExtra("STORY_NAME")

        // Tampilkan data
        textView.text = storyName
        imagePath?.let {
            Glide.with(this).load(it).into(imageView)
        }

        // Tombol hapus (opsional)
        btnDelete.setOnClickListener {
            imagePath?.let { path ->
                val isDeleted = databaseHelper.deleteImage(path)

                if (isDeleted) {
                    Log.d("ImageDetailActivity", "Gambar berhasil dihapus: $path")

                    // Kembali ke HomeActivity dan refresh daftar gambar
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    finish()
                } else {
                    Log.e("ImageDetailActivity", "Gagal menghapus gambar: $path")
                }
            }
        }
    }
}
