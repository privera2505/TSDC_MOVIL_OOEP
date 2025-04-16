package com.vinilo.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vinilo.view.databinding.FragmentAlbumsBinding

class AlbumDetailActivity : AppCompatActivity() {

    private lateinit var binding: FragmentAlbumsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAlbumsBinding.inflate(layoutInflater)
        setContentView(R.layout.fragment_albums)

        binding.buttonOpenAlbum.setOnClickListener {
            val intent = Intent(this, AlbumDetailActivity::class.java)
            startActivity(intent)
            println("Desde Albumes")
        }
    }
}
