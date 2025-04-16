package com.vinilo.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vinilo.viewmodel.AlbumsViewModel
import com.vinilo.view.R

class AlbumsFragment : Fragment() {

    private lateinit var albumViewModel: AlbumsViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var albumAdapter: AlbumAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflamos el layout
        val binding = inflater.inflate(R.layout.fragment_albums, container, false)

        // Aquí puedes obtener la referencia al RecyclerView
        recyclerView = binding.findViewById(R.id.recycler_albums)

        val layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.layoutManager = layoutManager

        // Inicializamos el ViewModel y observamos los cambios en los datos
        albumViewModel = ViewModelProvider(this).get(AlbumsViewModel::class.java)

        albumViewModel.albums.observe(viewLifecycleOwner, Observer { albums ->
            // Cuando recibimos los datos, actualizamos el adapter con la nueva lista
            albumAdapter = AlbumAdapter(albums)
            recyclerView.adapter = albumAdapter
        })

        albumViewModel.error.observe(viewLifecycleOwner) { errorMsg ->
            Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_LONG).show()
        }

        // Disparar la carga de datos
        albumViewModel.fetchAlbums()
        return binding
    }
}

