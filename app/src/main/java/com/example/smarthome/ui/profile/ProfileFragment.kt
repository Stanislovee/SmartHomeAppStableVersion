package com.example.smarthome.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.smarthome.AuthRepositoryImpl
import com.example.smarthome.MainActivity
import com.example.smarthome.R
import com.example.smarthome.databinding.FragmentProfileBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val authRepository by lazy { AuthRepositoryImpl() }

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
        val email = sharedPref.getString("user_email", null)

        binding.textViewCardName.text = "Loading..."

        if (email != null) {
            lifecycleScope.launch {
                try {
                    val userName = authRepository.getUserName(email)
                    if (userName != null) {
                        binding.textViewCardName.text = userName
                        with(sharedPref.edit()) {
                            putString("user_name", userName)
                            apply()
                        }
                    } else {
                        binding.textViewCardName.text = "User"
                    }
                } catch (e: Exception) {
                    binding.textViewCardName.text = sharedPref.getString("user_name", "User") ?: "User"
                }
            }
        } else {
            binding.textViewCardName.text = "User"
        }

        binding.btnNotifications.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_navigation_notifications)

        }

        binding.btnDeviceManager.setOnClickListener {
            val bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
            bottomNav.selectedItemId = R.id.navigation_device_manager
        }

        binding.btnSettings.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_profile_to_navigation_settings)
        }

        binding.btnHelp.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_navigation_help)
        }



        binding.btnLogout.setOnClickListener {
            with(sharedPref.edit()) {
                remove("user_name")
                remove("user_email")
                remove("jwt_token")
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