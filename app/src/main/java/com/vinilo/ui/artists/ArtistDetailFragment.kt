package com.vinilo.ui.artists

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
import com.vinilo.view.databinding.FragmentPerformerDetailBinding
import com.vinilo.viewmodel.PerformerDetailViewModel

class ArtistDetailFragment : Fragment() {
    private var _binding: FragmentPerformerDetailBinding? = null
    private val binding get() = _binding!!

    private val performerViewModel: PerformerDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPerformerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val performerId = arguments?.getInt("performerId") ?: -1

        binding.backBtnAlbum.setOnClickListener{
            findNavController().navigate(R.id.artistsFragment)
        }
        //binding.trackRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        Glide.with(requireContext())
            .load(R.drawable.bg_gradient)
            .circleCrop()
            .into(binding.imgArtistPhoto)

        performerViewModel.performer.observe(viewLifecycleOwner) { performer ->
            Glide.with(requireContext()).load(performer.image).circleCrop().into(binding.imgArtistPhoto)
            binding.tvArtistName.text = performer.name
            binding.tvDescription.text = performer.description
            binding.rvAlbums.layoutManager = LinearLayoutManager(requireContext())
            binding.rvAlbums.adapter = AlbumsAdapter(performer.albums)
        }

        performerViewModel.error.observe(viewLifecycleOwner) {
            // Toast de error
        }

        performerViewModel.fetchPerformer(performerId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}