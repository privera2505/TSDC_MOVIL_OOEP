package com.vinilo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vinilo.util.BottomNavManager
import com.vinilo.view.databinding.ActivityMainBinding
import com.vinilo.viewmodel.MainViewModel

class MainActivity : FragmentActivity() {

    //private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val bottomNavManager = BottomNavManager(
            findViewById(android.R.id.content),
            this,
            R.id.frameContainer
        )

//        // Observar LiveData del ViewModel
//        viewModel.user.observe(this) { user ->
//            binding.textViewUserName.text = "Nombre:" + user.name
//        }
//
//        // Cargar datos
//        viewModel.loadUser()
//
//        binding.buttonOpenAlbum.setOnClickListener {
//            val intent = Intent(this, AlbumDetailActivity::class.java)
//            startActivity(intent)
//        }

    }
}