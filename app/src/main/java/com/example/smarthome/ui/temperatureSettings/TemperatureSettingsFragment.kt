package com.example.smarthome.ui.temperatureSettings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.smarthome.R
import com.example.smarthome.databinding.FragmentTemperatureSettingsBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.android.material.slider.Slider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.coroutines.launch

class TemperatureSettingsFragment : Fragment() {

    private var _binding: FragmentTemperatureSettingsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTemperatureSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupUI()
        fetchTemperatureFromFirebase()
        setupSetTemperatureButton()

        return root
    }

    private fun setupUI() {
        val btnHot = binding.btnHot
        val btnCold = binding.btnCold
        val btnNormal = binding.btnNormal
        val slider = binding.sliderTempSettings
        val textInsideCircle = binding.textInsideCircle

        val buttons = listOf(btnHot, btnCold, btnNormal)

        btnHot.setOnClickListener {
            updateButtonState(buttons, btnHot)
            updateSliderValue(slider, textInsideCircle, 22f)
        }

        btnCold.setOnClickListener {
            updateButtonState(buttons, btnCold)
            updateSliderValue(slider, textInsideCircle, 5f)
        }

        btnNormal.setOnClickListener {
            updateButtonState(buttons, btnNormal)
            updateSliderValue(slider, textInsideCircle, 16f)
        }

        buttons.forEach { it.background = requireContext().getDrawable(R.drawable.custom_segment_buttons) }

        slider.value = 30f
        slider.valueTo = 35f
        slider.valueFrom = -5f
        slider.stepSize = 1f
        textInsideCircle.text = slider.value.toInt().toString()

        slider.addOnChangeListener { _, value, _ ->
            textInsideCircle.text = value.toInt().toString()
        }
    }

    private fun fetchTemperatureFromFirebase() {
        val textInsideCircle = binding.textInsideCircle

        val database = FirebaseDatabase.getInstance("https://smarthome-5d99c-default-rtdb.europe-west1.firebasedatabase.app/")
            .reference

        database.child("temperature").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val temperature = snapshot.getValue(String::class.java)
                temperature?.let {
                    textInsideCircle.text = it
                }
            }

            override fun onCancelled(error: DatabaseError) {
                textInsideCircle.text = "Error"
            }
        })
    }

    private fun setupSetTemperatureButton() {
        val btnSetTemp = binding.btnSetTemp
        val textInsideCircle = binding.textInsideCircle

        btnSetTemp.setOnClickListener {
            val setTemp = textInsideCircle.text.toString()
            val database = FirebaseDatabase.getInstance("https://smarthome-5d99c-default-rtdb.europe-west1.firebasedatabase.app/")
                .reference
            database.child("set_temperature").setValue(setTemp)
        }
    }

    private fun updateButtonState(buttons: List<Button>, activeButton: Button) {
        buttons.forEach { button ->
            if (button == activeButton) {
                button.background = requireContext().getDrawable(R.drawable.custom_segment_buttons_pressed)
            } else {
                button.background = requireContext().getDrawable(R.drawable.custom_segment_buttons)
            }
        }
    }

    private fun updateSliderValue(slider: Slider, textView: TextView, value: Float) {
        slider.value = value
        textView.text = value.toInt().toString()
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
