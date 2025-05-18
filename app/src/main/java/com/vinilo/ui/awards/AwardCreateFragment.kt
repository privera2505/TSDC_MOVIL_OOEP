package com.vinilo.ui.awards

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vinilo.view.databinding.FragmentPrizeCreateBinding
import com.vinilo.view.R
import com.vinilo.viewmodel.AwardCreateViewModel

class AwardCreateFragment: Fragment() {
    private var _binding: FragmentPrizeCreateBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AwardCreateViewModel
    private lateinit var prizeNombre: EditText
    private lateinit var prizeDescripcion: EditText
    private lateinit var prizeOrganization: EditText
    private lateinit var btnEnviar: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrizeCreateBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backBtnPrize.setOnClickListener{
            findNavController().navigate(R.id.awardsFragment)
        }

        viewModel = ViewModelProvider(this)[AwardCreateViewModel::class.java]

        prizeNombre = view.findViewById(R.id.inputPrizeName)
        prizeDescripcion = view.findViewById(R.id.inputPrizeDescription)
        prizeOrganization = view.findViewById(R.id.inputPrizeOrganization)
        btnEnviar = view.findViewById(R.id.btnCreatePrize)

        viewModel.isFormularioValido.observe(viewLifecycleOwner) { esValido ->
            if (esValido){
                changeButtonProperties(true,"#1ED760")
            }else{
                changeButtonProperties(false,"#B0B0B0")
            }
        }

        prizeNombre.doAfterTextChanged {
            viewModel.actualizarNombre(it.toString())
        }
        prizeDescripcion.doAfterTextChanged {
            viewModel.actualizarDescripcion(it.toString())
        }
        prizeOrganization.doAfterTextChanged {
            viewModel.actualizarOrganization(it.toString())
        }

    }

    fun changeButtonProperties(bool: Boolean, str: String){
        btnEnviar.isEnabled = bool
        btnEnviar.setBackgroundColor(Color.parseColor(   str))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}