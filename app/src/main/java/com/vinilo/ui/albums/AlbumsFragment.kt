package com.vinilo.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vinilo.domain.model.Album
import com.vinilo.viewmodel.AlbumsViewModel
import com.vinilo.view.R
import com.vinilo.view.databinding.FragmentAlbumsBinding

class AlbumsFragment : Fragment() {

    private lateinit var albumViewModel: AlbumsViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var albumAdapter: AlbumAdapter
    private var _binding: FragmentAlbumsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumsBinding.inflate(inflater, container, false)

        recyclerView = binding.recyclerAlbums
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        albumViewModel = ViewModelProvider(this)[AlbumsViewModel::class.java]

        albumViewModel.albums.observe(viewLifecycleOwner) { albums ->
            albumAdapter = AlbumAdapter(albums, ::onAlbumClick)
            recyclerView.adapter = albumAdapter
        }

        albumViewModel.error.observe(viewLifecycleOwner) { errorMsg ->
            Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_LONG).show()
        }

        albumViewModel.fetchAlbums()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.albumListTitle.setOnClickListener {
            println("click en titulo")
            findNavController().navigate(R.id.AlbumDetailFragment)
        }
    }

    private fun onAlbumClick(album: Album) {
        val bundle = Bundle().apply {
            putInt("albumId", album.id)
        }

        findNavController().navigate(R.id.AlbumDetailFragment, bundle)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

