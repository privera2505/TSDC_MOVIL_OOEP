package com.vinilo.util

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.ImageButton
import com.vinilo.view.R
import com.vinilo.view.MainActivity
import com.vinilo.view.AlbumDetailActivity
// Importa otras actividades si las tenés

class BottomNavManager(
    private val rootView: View,
    private val activity: Activity
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
            activity.startActivity(Intent(activity, MainActivity::class.java))
        }

        btnAlbums.setOnClickListener {
            selectButton(btnAlbums)
            println("Click en Álbumes")
            activity.startActivity(Intent(activity, AlbumDetailActivity::class.java))
        }

        btnPerformers.setOnClickListener {
            selectButton(btnPerformers)
            println("Click en Performers")
            // TODO: Agrega el intent si tenés la Activity creada
        }

        btnAwards.setOnClickListener {
            selectButton(btnAwards)
            println("Click en Awards")
            // TODO: Agrega el intent si tenés la Activity creada
        }
    }

    private fun selectButton(selected: ImageButton) {
        val allButtons = listOf(btnHome, btnAlbums, btnPerformers, btnAwards)
        allButtons.forEach {
            it.isSelected = (it == selected)
        }
    }

    private fun setInitialState() {
        // Marca uno por defecto si querés (opcional)
        btnAlbums.isSelected = true
    }
}
