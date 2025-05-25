package com.vinilo.ui.collectors

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
import com.vinilo.domain.model.Collector
import com.vinilo.view.R
import com.vinilo.view.databinding.FragmentCollectorBinding
import com.vinilo.viewmodel.CollectorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectorsFragment: Fragment()  {

    private lateinit var collectorViewModel: CollectorViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var collectorAdapter: CollectorAdapter
    private var _binding: FragmentCollectorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCollectorBinding.inflate(inflater, container,false)

        recyclerView = binding.recyclerCollectors

        recyclerView.layoutManager = GridLayoutManager(requireContext(),1)

        collectorViewModel = ViewModelProvider(this)[CollectorViewModel::class.java]

        collectorViewModel.collectors.observe(viewLifecycleOwner) { collectors ->
            collectorAdapter = CollectorAdapter(collectors, ::onCollectorlick)
            recyclerView.adapter = collectorAdapter
        }

        collectorViewModel.error.observe(viewLifecycleOwner) { errorMsg ->
            Toast.makeText(requireContext(),errorMsg, Toast.LENGTH_SHORT).show()
        }

        collectorViewModel.fetchCollectors()

        return binding.root

    }

    private fun onCollectorlick(collector: Collector) {
        println("ColectorID" + collector.id)
        val bundle = Bundle().apply {
            putInt("collectorId", collector.id)
        }

        findNavController().navigate(R.id.collectorDetailFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}