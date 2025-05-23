package com.vinilo.ui.awards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinilo.view.databinding.FragmentArtistSelectBinding
import com.vinilo.viewmodel.AwardDetailViewModel
import com.vinilo.viewmodel.PerformerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistSelectFragment : Fragment() {

    private var _binding: FragmentArtistSelectBinding? = null
    private val binding get() = _binding!!

    private val args: ArtistSelectFragmentArgs by navArgs()
    private val awardViewModel: AwardDetailViewModel by viewModels()
    private val performerViewModel: PerformerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtistSelectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerArtistSelect.layoutManager = LinearLayoutManager(requireContext())

        performerViewModel.performers.observe(viewLifecycleOwner) { performers ->
            val adapter = ArtistSelectAdapter(performers) { artist ->
                awardViewModel.addWinner(args.awardId, artist.id,
                    onSuccess = {
                        Toast.makeText(requireContext(), "Ganador agregado", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    },
                    onError = {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                    }
                )
            }
            binding.recyclerArtistSelect.adapter = adapter
        }

        //performerViewModel.fetchPerformers()
        performerViewModel.fetchPerformersNotInPrize(args.awardId)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
