package com.vinilo.util

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.vinilo.view.R
import com.vinilo.view.MainActivity
import com.vinilo.view.Album_Detail
import com.vinilo.view.Artistas
import com.vinilo.view.Home
import com.vinilo.view.Premios

// Importa otras actividades si las tenés

class BottomNavManager(
    private val rootView: View,
    private val fragment: FragmentActivity,
    private val containerId: Int
) {

    private val btnHome: ImageButton = rootView.findViewById(R.id.btnHome)
    private val btnAlbums: ImageButton = rootView.findViewById(R.id.btnAlbums)
    private val btnPerformers: ImageButton = rootView.findViewById(R.id.btnPerformers)
    private val btnAwards: ImageButton = rootView.findViewById(R.id.btnAwards)

    init {
        setupListeners()
        setInitialState()
    }

    private fun setupListeners() {
        btnHome.setOnClickListener {
            selectButton(btnHome)
            println("Click en Home")
            replaceFragment(Home())

        }

        btnAlbums.setOnClickListener {
            selectButton(btnAlbums)
            println("Click en Álbumes")
            replaceFragment(Album_Detail())
        }

        btnPerformers.setOnClickListener {
            selectButton(btnPerformers)
            println("Click en Performers")
            replaceFragment(Artistas())
        }

        btnAwards.setOnClickListener {
            selectButton(btnAwards)
            println("Click en Awards")
            replaceFragment(Premios())
        }
    }

    private fun selectButton(selected: ImageButton) {
        val allButtons = listOf(btnHome, btnAlbums, btnPerformers, btnAwards)
        allButtons.forEach {
            it.isSelected = (it == selected)
        }
    }

    private fun replaceFragment(fragmet: Fragment){
        fragment.supportFragmentManager.beginTransaction()
            .replace(containerId,fragmet)
            .commit()
    }

    private fun setInitialState() {
        btnHome.isSelected = true
        replaceFragment(Home())
    }
}
