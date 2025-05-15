package com.vinilo.ui.collectors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vinilo.viewmodel.CollectorDetailviewModel
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinilo.view.R
import com.vinilo.view.databinding.FragmentCollectorDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CollectorDetailFragment: Fragment() {
    private var _binding: FragmentCollectorDetailBinding? = null
    private val binding get() = _binding!!

    private val collectorViewModel: CollectorDetailviewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectorDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Obtener el ID del Coleccionista
        val collectorId = arguments?.getInt("collectorId") ?: -1

        //Volver a listado de Colleccionistas
        binding.backBtnCollectors.setOnClickListener{
            findNavController().navigate(R.id.collectorsFragment)
        }

        binding.recyclerCollectorComments.layoutManager = LinearLayoutManager(requireContext())

        //Traer información de coleccionista
        collectorViewModel.collectors.observe(viewLifecycleOwner) { collector ->
            //Nombre Coleccionista
            binding.detailCollectorName.text = collector.name

            //Año
            binding.detailCollectorEmail.text = collector.email

            //Comments del coleccionista
            val commentsCollectorList = collector.comments
            val commentsAdapter = CollectorCommentsAdapter(commentsCollectorList)


            binding.recyclerCollectorComments.adapter = commentsAdapter

        }

        collectorViewModel.fetchCollector(collectorId)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}