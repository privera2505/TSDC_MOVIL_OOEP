package com.vinilo.ui.awards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.vinilo.view.R
import com.vinilo.view.databinding.FragmentAwardDetailBinding
import com.vinilo.viewmodel.AwardDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AwardDetailFragment : Fragment() {

    private var _binding: FragmentAwardDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AwardDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAwardDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val awardId = arguments?.getInt("awardId") ?: return
        viewModel.loadAward(awardId)

        viewModel.award.observe(viewLifecycleOwner) { award ->
            binding.awardName.text = award.name
            binding.awardDescription.text = award.description
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }

        binding.addWinnerButton.setOnClickListener {
            val bundle = Bundle().apply { putInt("awardId", awardId) }
            findNavController().navigate(R.id.action_awardDetailFragment_to_artistSelectFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}