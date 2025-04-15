package com.vinilo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vinilo.view.databinding.ActivityAlbumDetailBinding

class AlbumDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAlbumDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ejemplo de uso:
        //binding.textViewAlbumTitle.text = "Pantalla de Álbum"
    }
}