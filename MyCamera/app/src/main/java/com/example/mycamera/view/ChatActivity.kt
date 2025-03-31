package com.example.mycamera.view

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.mycamera.R

class ChatActivity:AppCompatActivity() {
    private lateinit var backButton: ImageButton
    private lateinit var addFriendButton: ImageButton
    private lateinit var chatUser: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        backButton = findViewById(R.id.back)
        addFriendButton = findViewById(R.id.addFriendButton)
        chatUser = findViewById(R.id.chatUser)

        backButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        addFriendButton.setOnClickListener {
            val intent = Intent(this, AddFriendActivity::class.java)
            startActivity(intent)
        }

        chatUser.setOnClickListener {
            val intent = Intent(this, MessageActivity::class.java)
            startActivity(intent)
        }

    }
}