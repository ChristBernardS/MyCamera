package com.example.mycamera.view

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.mycamera.R

class ProfileActivity:AppCompatActivity() {
    private lateinit var cameraButton: ImageButton
    private lateinit var homeButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        cameraButton = findViewById(R.id.btnCamera)
        homeButton = findViewById(R.id.btnHome)

        cameraButton.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

        homeButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

    }

}