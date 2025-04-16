package com.vinilo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.vinilo.util.BottomNavManager
import com.vinilo.view.databinding.ActivityMainBinding
import com.vinilo.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observar LiveData del ViewModel
        viewModel.user.observe(this) { user ->
            binding.textViewUserName.text = "Nombre:" + user.name
        }

        // Cargar datos
        viewModel.loadUser()

        binding.buttonOpenAlbum.setOnClickListener {
            val intent = Intent(this, AlbumDetailActivity::class.java)
            startActivity(intent)
        }
    }
}