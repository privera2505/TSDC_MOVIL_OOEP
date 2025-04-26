package com.vinilo.ui.artists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vinilo.view.databinding.FragmentPerformersBinding
import com.vinilo.viewmodel.PerformerViewModel
import com.vinilo.view.R

class ArtistsFragment : Fragment()  {

    private lateinit var performerViewModel: PerformerViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var performerAdapter: PerformerAdapter
    private var _binding: FragmentPerformersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPerformersBinding.inflate(inflater, container, false)

        recyclerView = binding.recyclerPerformers


        recyclerView.layoutManager = GridLayoutManager(requireContext(),3)

        performerViewModel = ViewModelProvider(this)[PerformerViewModel::class.java]

        performerViewModel.performers.observe(viewLifecycleOwner) { performers ->
            performerAdapter = PerformerAdapter(performers)
            recyclerView.adapter = performerAdapter
        }

        performerViewModel.error.observe(viewLifecycleOwner) { errorMsg ->
            Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_LONG).show()
        }

        performerViewModel.fetchPerformers()


        return binding.root
    }

}