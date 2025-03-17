package com.example.smarthome.ui.help

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.smarthome.R
import com.example.smarthome.databinding.FragmentHelpBinding
import com.example.smarthome.databinding.FragmentSettingsBinding


class HelpFragment : Fragment() {

    private var _binding: FragmentHelpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHelpBinding.inflate(inflater, container, false)


        binding.btnBackHelp.setOnClickListener {

            findNavController().navigateUp()
        }

        return binding.root
    }

}