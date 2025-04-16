package com.vinilo.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.vinilo.util.BottomNavManager

class AlbumDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_album_detail)

        val bottomNavView = findViewById<View>(R.id.custom_bottom_nav)
        BottomNavManager(bottomNavView, this)
    }
}
