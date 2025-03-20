package com.example.smarthome.ui.help

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.fragment.findNavController
import com.example.smarthome.R
import com.example.smarthome.databinding.FragmentHelpBinding
import com.example.smarthome.databinding.FragmentSettingsBinding


class HelpFragment : Fragment() {

    private var _binding: FragmentHelpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        _binding = FragmentHelpBinding.inflate(inflater, container, false)

        binding.btnBackHelpActivity.setOnClickListener {

            findNavController().navigateUp()
        }

        binding.textPhoneHelpActivity.setOnClickListener {
            val phoneNumber = "tel:+48573870821"
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse(phoneNumber)
            }
            startActivity(intent)
        }

        binding.textEmailHelpActivity.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:etiap22@gmail.com")
                putExtra(Intent.EXTRA_SUBJECT, "Subject.")
                putExtra(Intent.EXTRA_TEXT, "Describe a problem.")
            }
            emailIntent.setPackage("com.google.android.gm")

            try {
                startActivity(emailIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(requireContext(), "Gmail not found.", Toast.LENGTH_SHORT).show()
            }
        }


        return binding.root
    }

}