package com.vinilo.ui.awards

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
import com.vinilo.view.databinding.FragmentPrizeBinding
import com.vinilo.view.R
import com.vinilo.viewmodel.AwardsViewModel

class AwardsFragment: Fragment() {

    private lateinit var awardsViewModel: AwardsViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var awardsAdapter: AwardsAdapter
    private var _binding: FragmentPrizeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrizeBinding.inflate(inflater,container,false)

        recyclerView = binding.recyclerAwards

        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)

        awardsViewModel = ViewModelProvider(this)[AwardsViewModel::class.java]

        awardsViewModel.awards.observe(viewLifecycleOwner) {awards ->
            awardsAdapter = AwardsAdapter(awards)
            recyclerView.adapter = awardsAdapter
        }

        awardsViewModel.error.observe(viewLifecycleOwner) {errors ->
            Toast.makeText(requireContext(),errors, Toast.LENGTH_SHORT).show()
        }

        awardsViewModel.fetchAwards()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addButtonPrize.setOnClickListener{
            findNavController().navigate(R.id.prizeCreateLayout)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}