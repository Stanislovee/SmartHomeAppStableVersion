package com.example.smarthome.ui.deviceManager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.smarthome.databinding.FragmentDeviceManagerBinding
import com.google.firebase.database.*

class DeviceManagerFragment : Fragment() {

    private var _binding: FragmentDeviceManagerBinding? = null
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeviceManagerBinding.inflate(inflater, container, false)
        val root: View = binding.root

        database = FirebaseDatabase.getInstance("https://smarthome-5d99c-default-rtdb.europe-west1.firebasedatabase.app/").reference

        // Climate control visibility toggle
        binding.switchClimate.setOnCheckedChangeListener { _, isChecked ->
            binding.textViewOnClimate.visibility = if (isChecked) View.VISIBLE else View.GONE
            binding.textViewOffClimate.visibility = if (isChecked) View.GONE else View.VISIBLE
        }

        // Door control visibility toggle
        binding.switchDoor.setOnCheckedChangeListener { _, isChecked ->
            binding.textViewOnDoor.visibility = if (isChecked) View.VISIBLE else View.GONE
            binding.textViewOffDoor.visibility = if (isChecked) View.GONE else View.VISIBLE
        }

        // Motion control visibility toggle
        binding.switchMotion.setOnCheckedChangeListener { _, isChecked ->
            binding.textViewOnMotion.visibility = if (isChecked) View.VISIBLE else View.GONE
            binding.textViewOffMotion.visibility = if (isChecked) View.GONE else View.VISIBLE
        }

        // Existing Firebase listeners
        database.child("door_lock").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val doorLock = snapshot.getValue(String::class.java)
                if (doorLock == "true") {
                    binding.textViewInsideCardViewDoorTrue.visibility = View.VISIBLE
                    binding.textViewInsideCardViewDoorFalse.visibility = View.GONE
                } else {
                    binding.textViewInsideCardViewDoorTrue.visibility = View.GONE
                    binding.textViewInsideCardViewDoorFalse.visibility = View.VISIBLE
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })

        database.child("motion").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val motion = snapshot.getValue(String::class.java)
                if (motion == "false") {
                    binding.textViewInsideCardViewFootTrue.visibility = View.VISIBLE
                    binding.textViewInsideCardViewFootFalse.visibility = View.GONE
                } else {
                    binding.textViewInsideCardViewFootTrue.visibility = View.GONE
                    binding.textViewInsideCardViewFootFalse.visibility = View.VISIBLE
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })

        database.child("set_temperature").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val setTemperature = snapshot.getValue(String::class.java)
                setTemperature?.let {
                    binding.textViewInsideCardViewValueLower.text = it
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}