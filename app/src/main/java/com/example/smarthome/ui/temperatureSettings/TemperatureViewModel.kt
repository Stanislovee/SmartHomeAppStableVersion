package com.example.smarthome.ui.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TemperatureViewModel : ViewModel() {
    private val _temperature = MutableLiveData<String>()
    val temperature: LiveData<String> get() = _temperature

    fun setTemperature(temp: String) {
        _temperature.value = temp
    }
}
