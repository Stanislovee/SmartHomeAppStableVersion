package com.example.smarthome.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.smarthome.MainActivity
import com.example.smarthome.R
import com.example.smarthome.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userName = sharedPref.getString("user_name", "User") ?: "User"
        binding.textViewCardName.text = userName

        binding.imageButtonNotificationUpper.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_navigation_notifications)
        }

        binding.btnLogout.setOnClickListener {
            with(sharedPref.edit()) {
                remove("user_name")
                remove("user_email")
                putBoolean("is_logged_in", false)
                apply()
            }

            (activity as? MainActivity)?.hideBottomNavigation()
            findNavController().popBackStack(R.id.authFragment, false)
            findNavController().navigate(R.id.authFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}