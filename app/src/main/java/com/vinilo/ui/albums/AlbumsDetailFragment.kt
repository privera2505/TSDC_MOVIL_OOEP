package com.vinilo.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.vinilo.view.R
import com.vinilo.view.databinding.FragmentAlbumDetailBinding
import com.vinilo.viewmodel.AlbumsDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Locale

@AndroidEntryPoint
class AlbumsDetailFragment : Fragment() {
    private var _binding: FragmentAlbumDetailBinding? = null
    private val binding get() = _binding!!

    private val albumViewModel: AlbumsDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val albumId = arguments?.getInt("albumId") ?: -1

        binding.backBtnAlbum.setOnClickListener{
            findNavController().navigate(R.id.albumsFragment)
        }
        binding.trackRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        albumViewModel.albums.observe(viewLifecycleOwner) { album ->
            println("Tracks recibidos: ${album.tracks.size}")
            val adapter = TrackAdapter(album.tracks)
            val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            val formattedDate = formatter.format(album.releaseDate)

            Glide.with(requireContext()).load(album.cover).into(binding.imageAlbumCover)
            Glide.with(requireContext()).load(R.drawable.album_cover).into(binding.imageAlbumCoverDescription)

            binding.trackRecyclerView.adapter = adapter
            binding.nameAlbumName.text = album.name
            binding.AlbumNameDescription.text = album.name
            binding.releaseDate.text = formattedDate
            binding.tracksAlbumDescription.text = album.description
            binding.genraText.text = album.genre
        }

        albumViewModel.error.observe(viewLifecycleOwner) {
            // Toast de error
        }

        albumViewModel.fetchAlbums(albumId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}