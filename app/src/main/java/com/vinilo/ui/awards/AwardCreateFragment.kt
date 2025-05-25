package com.vinilo.ui.awards

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.vinilo.view.databinding.FragmentPrizeCreateBinding
import com.vinilo.view.R
import com.vinilo.viewmodel.AwardCreateViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AwardCreateFragment: Fragment() {
    private var _binding: FragmentPrizeCreateBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AwardCreateViewModel
    private lateinit var prizeNombre: EditText
    private lateinit var prizeDescripcion: EditText
    private lateinit var prizeOrganization: EditText
    private lateinit var textCreated: TextView
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
        textCreated = view.findViewById(R.id.CreateSuccessAward)

        val textWatcher = object :  TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val nombreNoVacio = prizeNombre.text.toString().isNotBlank()
                val descNoVacia = prizeDescripcion.text.toString().isNotBlank()
                val orgNoVacia = prizeOrganization.text.toString().isNotBlank()

                if (nombreNoVacio && descNoVacia && orgNoVacia) {
                    changeButtonProperties(true, "#1ED760")

                }else{
                    changeButtonProperties(false,"#B0B0B0")

                }

            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        prizeNombre.addTextChangedListener(textWatcher)
        prizeDescripcion.addTextChangedListener(textWatcher)
        prizeOrganization.addTextChangedListener(textWatcher)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.createAwardResponse.collect{ response ->
                    response?.let {
                        if (it.startsWith("Éxito:")) {
                            println(it)
                            prizeNombre.setText("")
                            prizeDescripcion.setText("")
                            prizeOrganization.setText("")
                            textCreated.visibility = View.VISIBLE

                            //Ocultarlo despues de 4 segundos
                            Handler(Looper.getMainLooper()).postDelayed({
                                textCreated.visibility = View.INVISIBLE
                            }, 4000)
                        }
                    }
                }
            }
        }

        btnEnviar.setOnClickListener{
            viewModel.createAward(prizeNombre.text.toString(),prizeDescripcion.text.toString(),prizeOrganization.text.toString())
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
