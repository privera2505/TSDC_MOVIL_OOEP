package com.vinilo.ui.awards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.vinilo.view.R
import com.vinilo.view.databinding.FragmentPrizeDetailBinding
import com.vinilo.viewmodel.AwardDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AwardDetailFragment : Fragment() {

    private var _binding: FragmentPrizeDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AwardDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrizeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val awardId = arguments?.getInt("awardId") ?: return
        viewModel.loadAward(awardId)

        viewModel.award.observe(viewLifecycleOwner) { award ->
            Glide.with(requireContext())
                .load(R.drawable.award_ico)
                .into(binding.imagePrize)

            binding.awardName.setText(award.name)
            binding.awardDescription.setText(award.description)
            binding.awardOrganization.setText(award.organization)
        }

        viewModel.winners.observe(viewLifecycleOwner) { winners ->
            val adapter = AwardWinnersAdapter(winners)
            binding.recyclerWinners.adapter = adapter
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }

        binding.btnCreatePrize.setOnClickListener {
            val bundle = Bundle().apply { putInt("awardId", awardId) }
            findNavController().navigate(R.id.action_awardDetailFragment_to_artistSelectFragment, bundle)
        }

        binding.backBtnPrize.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}