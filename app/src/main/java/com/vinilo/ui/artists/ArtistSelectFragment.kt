package com.vinilo.ui.artists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinilo.view.databinding.FragmentArtistSelectBinding
import com.vinilo.viewmodel.PerformerViewModel
import androidx.navigation.fragment.findNavController
import com.vinilo.domain.model.Performer

class ArtistSelectFragment : Fragment() {

    private var _binding: FragmentArtistSelectBinding? = null
    private val binding get() = _binding!!

    private lateinit var performerViewModel: PerformerViewModel
    private lateinit var adapter: ArtistSelectAdapter
    private var awardId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtistSelectBinding.inflate(inflater, container, false)
        awardId = arguments?.getInt("awardId") ?: 0
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        performerViewModel = PerformerViewModel() // Instancia manual (sin Hilt)

        binding.recyclerArtistSelect.layoutManager = LinearLayoutManager(requireContext())

        performerViewModel.performers.observe(viewLifecycleOwner) { performers ->
            adapter = ArtistSelectAdapter(performers) { selectedArtist: Performer ->
                performerViewModel.addWinnerToPrize(
                    awardId,
                    selectedArtist.id,
                    onSuccess = {
                        Toast.makeText(requireContext(), "Artista agregado como ganador", Toast.LENGTH_SHORT).show()
                        requireActivity().onBackPressedDispatcher.onBackPressed()
                    },
                    onError = { msg ->
                        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
                    }
                )
            }
            binding.recyclerArtistSelect.adapter = adapter
        }

        performerViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        binding.backBtnDetailPrize.setOnClickListener {
            findNavController().popBackStack()
        }

        performerViewModel.fetchPerformersNotInPrize(awardId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
