package com.example.smarthome.ui.loginRegister

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.DigitsKeyListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.smarthome.AuthRepository
import com.example.smarthome.AuthRepositoryImpl
import com.example.smarthome.MainActivity
import com.example.smarthome.R
import com.example.smarthome.databinding.FragmentAuthBinding
import kotlinx.coroutines.launch

class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!
    private lateinit var authRepository: AuthRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authRepository = AuthRepositoryImpl()

        // Verification Code logic
        binding.InputNumber1ForgotPasswordCodeActivity.inputType = InputType.TYPE_CLASS_NUMBER
        binding.InputNumber1ForgotPasswordCodeActivity.keyListener =
            DigitsKeyListener.getInstance("0123456789")
        binding.InputNumber1ForgotPasswordCodeActivity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 1) {
                    binding.InputNumber2ForgotPasswordCodeActivity.requestFocus()
                }
            }
        })

        binding.InputNumber2ForgotPasswordCodeActivity.inputType = InputType.TYPE_CLASS_NUMBER
        binding.InputNumber2ForgotPasswordCodeActivity.keyListener =
            DigitsKeyListener.getInstance("0123456789")
        binding.InputNumber2ForgotPasswordCodeActivity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 1) {
                    binding.InputNumber3ForgotPasswordCodeActivity.requestFocus()
                }
            }
        })

        binding.InputNumber3ForgotPasswordCodeActivity.inputType = InputType.TYPE_CLASS_NUMBER
        binding.InputNumber3ForgotPasswordCodeActivity.keyListener =
            DigitsKeyListener.getInstance("0123456789")
        binding.InputNumber3ForgotPasswordCodeActivity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 1) {
                    binding.InputNumber4ForgotPasswordCodeActivity.requestFocus()
                }
            }
        })

        binding.InputNumber4ForgotPasswordCodeActivity.inputType = InputType.TYPE_CLASS_NUMBER
        binding.InputNumber4ForgotPasswordCodeActivity.keyListener =
            DigitsKeyListener.getInstance("0123456789")
        binding.InputNumber4ForgotPasswordCodeActivity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 1) {
                    binding.InputNumber4ForgotPasswordCodeActivity.requestFocus()
                }
            }
        })


        val imageStartActivity = binding.imageStartActivity
        val imageStartText = binding.imageStartText
        val btnStart = binding.btnStart
        val imageUpperLoginRegisterActivity = binding.imageUpperLoginRegisterActivity
        val imageLowerLoginRegisterActivity = binding.imageLowerLoginRegisterActivity
        val btnSignUpLoginRegisterActivity = binding.btnSignUpLoginRegisterActivity
        val btnLoginLoginRegisterActivity = binding.btnLoginLoginRegisterActivity
        val btnBackLoginActivity = binding.btnBackLoginActivity
        val textSignUpLoginActivity = binding.textSignUpLoginActivity
        val imageLoginActivity = binding.imageLoginActivity
        val textViewEmailLoginActivity = binding.textViewEmailLoginActivity
        val editTextTextEmailLoginActivity = binding.editTextTextEmailLoginActivity
        val textViewSetPasswordLoginActivity = binding.textViewSetPasswordLoginActivity
        val editTextTextPasswordRegisterActivity = binding.editTextTextPasswordRegisterActivity
        val btnForgotPassword = binding.btnForgotPassword
        val btnLoginToTempSettLoginActivity = binding.btnLoginToTempSettLoginActivity
        val textViewConstLoginActivity = binding.textViewConstLoginActivity
        val btnSignUpLoginActivity = binding.btnSignUpLoginActivity
        val btnBackRegisterActivity = binding.btnBackRegisterActivity
        val textViewSignUpRegisterActivity = binding.textViewSignUpRegisterActivity
        val textViewRegisterUpperRegisterActivity = binding.textViewRegisterUpperRegisterActivity
        val textViewFullNameRegisterActivity = binding.textViewFullNameRegisterActivity
        val editTextTextNameRegisterActivity = binding.editTextTextNameRegisterActivity
        val textViewEmailRegisterActivity = binding.textViewEmailRegisterActivity
        val editTextEmailRegisterActivity = binding.editTextEmailRegisterActivity
        val textViewSetPasswordRegisterActivity = binding.textViewSetPasswordRegisterActivity
        val editTextPasswordRegisterActivity = binding.editTextPasswordRegisterActivity
        val textViewConfirmPasswordRegisterActivity = binding.textViewConfirmPasswordRegisterActivity
        val editTextConfirmPasswordRegisterActivity = binding.editTextConfirmPasswordRegisterActivity
        val btnRegisterRegisterActivity = binding.btnRegisterRegisterActivity
        val btnBackForgotPasswordEmailActivity = binding.btnBackForgotPasswordEmailActivity
        val textForgotPasswordEmailActivity = binding.textForgotPasswordEmailActivity
        val imageUpperForgotPasswordEmailActivity = binding.imageUpperForgotPasswordEmailActivity
        val imageLowerForgotPasswordEmailActivity = binding.imageLowerForgotPasswordEmailActivity
        val inputEditTextForgotPasswordEmailActivity = binding.InputEditTextForgotPasswordEmailActivity
        val btnSendCodeForgotPasswordEmailActivity = binding.btnSendCodeForgotPasswordEmailActivity
        val btnBackForgotPasswordCodeActivity = binding.btnBackForgotPasswordCodeActivity
        val textForgotPasswordCodeActivity = binding.textForgotPasswordCodeActivity
        val imageForgotPasswordCodeActivity = binding.imageForgotPasswordCodeActivity
        val textViewForgotPasswordCodeActivity = binding.textViewForgotPasswordCodeActivity
        val textViewEmailForgotPasswordCodeActivity = binding.textViewEmailForgotPasswordCodeActivity
        val textViewUserEmailForgotPasswordCodeActivity = binding.textViewUserEmailForgotPasswordCodeActivity
        val btnSubmitForgotPasswordCodeActivity = binding.btnSubmitForgotPasswordCodeActivity
        val inputNumber1ForgotPasswordCodeActivity = binding.InputNumber1ForgotPasswordCodeActivity
        val inputNumber2ForgotPasswordCodeActivity = binding.InputNumber2ForgotPasswordCodeActivity
        val inputNumber3ForgotPasswordCodeActivity = binding.InputNumber3ForgotPasswordCodeActivity
        val inputNumber4ForgotPasswordCodeActivity = binding.InputNumber4ForgotPasswordCodeActivity
        val textReceiveCodeForgotPasswordCodeActivity = binding.textReceiveCodeForgotPasswordCodeActivity
        val btnResentCodeForgotPasswordCodeActivity = binding.btnResentCodeForgotPasswordCodeActivity
        val imageInfoCreateAccActivity = binding.imageInfoCreateAccActivity
        val textUpperInfoCreateAccActivity = binding.textUpperInfoCreateAccActivity
        val textLowerInfoCreateAccActivity = binding.textLowerInfoCreateAccActivity
        val btnOkayInfoCreateAccActivity = binding.btnOkayInfoCreateAccActivity
        val btnBackChangePasswordActivity = binding.btnBackChangePasswordActivity
        val textChangePasswordActivity = binding.textChangePasswordActivity
        val imageviewChangePasswordActivity = binding.imageviewChangePasswordActivity
        val textUpperChangePasswordActivity = binding.textUpperChangePasswordActivity
        val editTextChangePasswordActivity = binding.editTextChangePasswordActivity
        val textLowerChangePasswordActivity = binding.textLowerChangePasswordActivity
        val editTextConfirmChangePasswordActivity = binding.editTextConfirmChangePasswordActivity
        val btnContinueChangePasswordCodeActivity = binding.btnContinueChangePasswordCodeActivity
        val imageInfoChangedPasswordActivity = binding.imageInfoChangedPasswordActivity
        val textUpperInfoChangedPasswordActivity = binding.textUpperInfoChangedPasswordActivity
        val textLowerInfoChangedPasswordActivity = binding.textLowerInfoChangedPasswordActivity
        val btnContinueInfoChangedPasswordActivity = binding.btnContinueInfoChangedPasswordActivity

        // Початковий стан UI
        imageStartActivity.visibility = View.VISIBLE
        imageStartText.visibility = View.VISIBLE
        btnStart.visibility = View.VISIBLE
        imageUpperLoginRegisterActivity.visibility = View.GONE
        imageLowerLoginRegisterActivity.visibility = View.GONE
        btnSignUpLoginRegisterActivity.visibility = View.GONE
        btnLoginLoginRegisterActivity.visibility = View.GONE
        btnBackLoginActivity.visibility = View.GONE
        textSignUpLoginActivity.visibility = View.GONE
        imageLoginActivity.visibility = View.GONE
        textViewEmailLoginActivity.visibility = View.GONE
        editTextTextEmailLoginActivity.visibility = View.GONE
        textViewSetPasswordLoginActivity.visibility = View.GONE
        editTextTextPasswordRegisterActivity.visibility = View.GONE
        btnForgotPassword.visibility = View.GONE
        btnLoginToTempSettLoginActivity.visibility = View.GONE
        textViewConstLoginActivity.visibility = View.GONE
        btnSignUpLoginActivity.visibility = View.GONE
        btnBackRegisterActivity.visibility = View.GONE
        textViewSignUpRegisterActivity.visibility = View.GONE
        textViewRegisterUpperRegisterActivity.visibility = View.GONE
        textViewFullNameRegisterActivity.visibility = View.GONE
        editTextTextNameRegisterActivity.visibility = View.GONE
        textViewEmailRegisterActivity.visibility = View.GONE
        editTextEmailRegisterActivity.visibility = View.GONE
        textViewSetPasswordRegisterActivity.visibility = View.GONE
        editTextPasswordRegisterActivity.visibility = View.GONE
        textViewConfirmPasswordRegisterActivity.visibility = View.GONE
        editTextConfirmPasswordRegisterActivity.visibility = View.GONE
        btnRegisterRegisterActivity.visibility = View.GONE
        btnBackForgotPasswordEmailActivity.visibility = View.GONE
        textForgotPasswordEmailActivity.visibility = View.GONE
        imageUpperForgotPasswordEmailActivity.visibility = View.GONE
        imageLowerForgotPasswordEmailActivity.visibility = View.GONE
        inputEditTextForgotPasswordEmailActivity.visibility = View.GONE
        btnSendCodeForgotPasswordEmailActivity.visibility = View.GONE
        btnBackForgotPasswordCodeActivity.visibility = View.GONE
        textForgotPasswordCodeActivity.visibility = View.GONE
        imageForgotPasswordCodeActivity.visibility = View.GONE
        textViewForgotPasswordCodeActivity.visibility = View.GONE
        textViewEmailForgotPasswordCodeActivity.visibility = View.GONE
        textViewUserEmailForgotPasswordCodeActivity.visibility = View.GONE
        btnSubmitForgotPasswordCodeActivity.visibility = View.GONE
        inputNumber1ForgotPasswordCodeActivity.visibility = View.GONE
        inputNumber2ForgotPasswordCodeActivity.visibility = View.GONE
        inputNumber3ForgotPasswordCodeActivity.visibility = View.GONE
        inputNumber4ForgotPasswordCodeActivity.visibility = View.GONE
        textReceiveCodeForgotPasswordCodeActivity.visibility = View.GONE
        btnResentCodeForgotPasswordCodeActivity.visibility = View.GONE
        imageInfoCreateAccActivity.visibility = View.GONE
        textUpperInfoCreateAccActivity.visibility = View.GONE
        textLowerInfoCreateAccActivity.visibility = View.GONE
        btnOkayInfoCreateAccActivity.visibility = View.GONE
        btnBackChangePasswordActivity.visibility = View.GONE
        textChangePasswordActivity.visibility = View.GONE
        imageviewChangePasswordActivity.visibility = View.GONE
        textUpperChangePasswordActivity.visibility = View.GONE
        editTextChangePasswordActivity.visibility = View.GONE
        textLowerChangePasswordActivity.visibility = View.GONE
        editTextConfirmChangePasswordActivity.visibility = View.GONE
        btnContinueChangePasswordCodeActivity.visibility = View.GONE
        imageInfoChangedPasswordActivity.visibility = View.GONE
        textUpperInfoChangedPasswordActivity.visibility = View.GONE
        textLowerInfoChangedPasswordActivity.visibility = View.GONE
        btnContinueInfoChangedPasswordActivity.visibility = View.GONE

        // Обробники кнопок
        btnStart.setOnClickListener {
            imageStartActivity.visibility = View.GONE
            imageStartText.visibility = View.GONE
            btnStart.visibility = View.GONE
            imageUpperLoginRegisterActivity.visibility = View.VISIBLE
            imageLowerLoginRegisterActivity.visibility = View.VISIBLE
            btnSignUpLoginRegisterActivity.visibility = View.VISIBLE
            btnLoginLoginRegisterActivity.visibility = View.VISIBLE
        }

        btnSignUpLoginRegisterActivity.setOnClickListener {
            imageUpperLoginRegisterActivity.visibility = View.GONE
            imageLowerLoginRegisterActivity.visibility = View.GONE
            btnSignUpLoginRegisterActivity.visibility = View.GONE
            btnLoginLoginRegisterActivity.visibility = View.GONE
            btnBackRegisterActivity.visibility = View.VISIBLE
            textViewSignUpRegisterActivity.visibility = View.VISIBLE
            textViewRegisterUpperRegisterActivity.visibility = View.VISIBLE
            textViewFullNameRegisterActivity.visibility = View.VISIBLE
            editTextTextNameRegisterActivity.visibility = View.VISIBLE
            textViewEmailRegisterActivity.visibility = View.VISIBLE
            editTextEmailRegisterActivity.visibility = View.VISIBLE
            textViewSetPasswordRegisterActivity.visibility = View.VISIBLE
            editTextPasswordRegisterActivity.visibility = View.VISIBLE
            textViewConfirmPasswordRegisterActivity.visibility = View.VISIBLE
            editTextConfirmPasswordRegisterActivity.visibility = View.VISIBLE
            btnRegisterRegisterActivity.visibility = View.VISIBLE
        }

        btnSignUpLoginActivity.setOnClickListener {
            hideAllViews()
            btnBackRegisterActivity.visibility = View.VISIBLE
            textViewSignUpRegisterActivity.visibility = View.VISIBLE
            textViewRegisterUpperRegisterActivity.visibility = View.VISIBLE
            textViewFullNameRegisterActivity.visibility = View.VISIBLE
            editTextTextNameRegisterActivity.visibility = View.VISIBLE
            textViewEmailRegisterActivity.visibility = View.VISIBLE
            editTextEmailRegisterActivity.visibility = View.VISIBLE
            textViewSetPasswordRegisterActivity.visibility = View.VISIBLE
            editTextPasswordRegisterActivity.visibility = View.VISIBLE
            textViewConfirmPasswordRegisterActivity.visibility = View.VISIBLE
            editTextConfirmPasswordRegisterActivity.visibility = View.VISIBLE
            btnRegisterRegisterActivity.visibility = View.VISIBLE
        }

        btnLoginLoginRegisterActivity.setOnClickListener {
            imageUpperLoginRegisterActivity.visibility = View.GONE
            imageLowerLoginRegisterActivity.visibility = View.GONE
            btnSignUpLoginRegisterActivity.visibility = View.GONE
            btnLoginLoginRegisterActivity.visibility = View.GONE
            btnBackLoginActivity.visibility = View.VISIBLE
            textSignUpLoginActivity.visibility = View.VISIBLE
            imageLoginActivity.visibility = View.VISIBLE
            textViewEmailLoginActivity.visibility = View.VISIBLE
            editTextTextEmailLoginActivity.visibility = View.VISIBLE
            textViewSetPasswordLoginActivity.visibility = View.VISIBLE
            editTextTextPasswordRegisterActivity.visibility = View.VISIBLE
            btnForgotPassword.visibility = View.VISIBLE
            btnLoginToTempSettLoginActivity.visibility = View.VISIBLE
            textViewConstLoginActivity.visibility = View.VISIBLE
            btnSignUpLoginActivity.visibility = View.VISIBLE
        }

        btnBackLoginActivity.setOnClickListener {
            btnBackLoginActivity.visibility = View.GONE
            textSignUpLoginActivity.visibility = View.GONE
            imageLoginActivity.visibility = View.GONE
            textViewEmailLoginActivity.visibility = View.GONE
            editTextTextEmailLoginActivity.visibility = View.GONE
            textViewSetPasswordLoginActivity.visibility = View.GONE
            editTextTextPasswordRegisterActivity.visibility = View.GONE
            btnForgotPassword.visibility = View.GONE
            btnLoginToTempSettLoginActivity.visibility = View.GONE
            textViewConstLoginActivity.visibility = View.GONE
            btnSignUpLoginActivity.visibility = View.GONE
            imageUpperLoginRegisterActivity.visibility = View.VISIBLE
            imageLowerLoginRegisterActivity.visibility = View.VISIBLE
            btnSignUpLoginRegisterActivity.visibility = View.VISIBLE
            btnLoginLoginRegisterActivity.visibility = View.VISIBLE
        }

        btnBackRegisterActivity.setOnClickListener {
            btnBackRegisterActivity.visibility = View.GONE
            textViewSignUpRegisterActivity.visibility = View.GONE
            textViewRegisterUpperRegisterActivity.visibility = View.GONE
            textViewFullNameRegisterActivity.visibility = View.GONE
            editTextTextNameRegisterActivity.visibility = View.GONE
            textViewEmailRegisterActivity.visibility = View.GONE
            editTextEmailRegisterActivity.visibility = View.GONE
            textViewSetPasswordRegisterActivity.visibility = View.GONE
            editTextPasswordRegisterActivity.visibility = View.GONE
            textViewConfirmPasswordRegisterActivity.visibility = View.GONE
            editTextConfirmPasswordRegisterActivity.visibility = View.GONE
            btnRegisterRegisterActivity.visibility = View.GONE
            imageUpperLoginRegisterActivity.visibility = View.VISIBLE
            imageLowerLoginRegisterActivity.visibility = View.VISIBLE
            btnSignUpLoginRegisterActivity.visibility = View.VISIBLE
            btnLoginLoginRegisterActivity.visibility = View.VISIBLE
        }

        btnForgotPassword.setOnClickListener {
            hideAllViews()
            btnBackForgotPasswordEmailActivity.visibility = View.VISIBLE
            textForgotPasswordEmailActivity.visibility = View.VISIBLE
            imageUpperForgotPasswordEmailActivity.visibility = View.VISIBLE
            imageLowerForgotPasswordEmailActivity.visibility = View.VISIBLE
            inputEditTextForgotPasswordEmailActivity.visibility = View.VISIBLE
            btnSendCodeForgotPasswordEmailActivity.visibility = View.VISIBLE
        }

        btnBackForgotPasswordEmailActivity.setOnClickListener {
            hideAllViews()
            btnBackLoginActivity.visibility = View.VISIBLE
            textSignUpLoginActivity.visibility = View.VISIBLE
            imageLoginActivity.visibility = View.VISIBLE
            textViewEmailLoginActivity.visibility = View.VISIBLE
            editTextTextEmailLoginActivity.visibility = View.VISIBLE
            textViewSetPasswordLoginActivity.visibility = View.VISIBLE
            editTextTextPasswordRegisterActivity.visibility = View.VISIBLE
            btnForgotPassword.visibility = View.VISIBLE
            btnLoginToTempSettLoginActivity.visibility = View.VISIBLE
            textViewConstLoginActivity.visibility = View.VISIBLE
            btnSignUpLoginActivity.visibility = View.VISIBLE
        }

        btnSendCodeForgotPasswordEmailActivity.setOnClickListener {
            val email = inputEditTextForgotPasswordEmailActivity.text.toString()
            if (email.isEmpty() || !email.contains("@")) {
                Toast.makeText(requireContext(), "Invalid email", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    try {
                        val (status, response) = authRepository.requestPasswordReset(email)
                        println("Reset request status: $status, response: ${response.message}")
                        if (response.success) {
                            hideAllViews()
                            btnBackForgotPasswordCodeActivity.visibility = View.VISIBLE
                            textForgotPasswordCodeActivity.visibility = View.VISIBLE
                            imageForgotPasswordCodeActivity.visibility = View.VISIBLE
                            textViewForgotPasswordCodeActivity.visibility = View.VISIBLE
                            textViewEmailForgotPasswordCodeActivity.visibility = View.VISIBLE
                            textViewUserEmailForgotPasswordCodeActivity.visibility = View.VISIBLE
                            btnSubmitForgotPasswordCodeActivity.visibility = View.VISIBLE
                            inputNumber1ForgotPasswordCodeActivity.visibility = View.VISIBLE
                            inputNumber2ForgotPasswordCodeActivity.visibility = View.VISIBLE
                            inputNumber3ForgotPasswordCodeActivity.visibility = View.VISIBLE
                            inputNumber4ForgotPasswordCodeActivity.visibility = View.VISIBLE
                            textReceiveCodeForgotPasswordCodeActivity.visibility = View.VISIBLE
                            btnResentCodeForgotPasswordCodeActivity.visibility = View.VISIBLE
                            textViewUserEmailForgotPasswordCodeActivity.text = email
                            Toast.makeText(requireContext(), "Check your email!", Toast.LENGTH_SHORT).show()
                        } else {
                            when (status) {
                                404 -> Toast.makeText(requireContext(), "User not found", Toast.LENGTH_SHORT).show()
                                500 -> Toast.makeText(requireContext(), "Server error: ${response.message}", Toast.LENGTH_LONG).show()
                                else -> Toast.makeText(requireContext(), response.message ?: "Unknown error", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } catch (e: Exception) {
                        Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        btnBackForgotPasswordCodeActivity.setOnClickListener {
            hideAllViews()
            btnBackForgotPasswordEmailActivity.visibility = View.VISIBLE
            textForgotPasswordEmailActivity.visibility = View.VISIBLE
            imageUpperForgotPasswordEmailActivity.visibility = View.VISIBLE
            imageLowerForgotPasswordEmailActivity.visibility = View.VISIBLE
            inputEditTextForgotPasswordEmailActivity.visibility = View.VISIBLE
            btnSendCodeForgotPasswordEmailActivity.visibility = View.VISIBLE
            inputEditTextForgotPasswordEmailActivity.setText(textViewUserEmailForgotPasswordCodeActivity.text)
        }

        btnSubmitForgotPasswordCodeActivity.setOnClickListener {
            val email = textViewUserEmailForgotPasswordCodeActivity.text.toString()
            val code = "${inputNumber1ForgotPasswordCodeActivity.text}${inputNumber2ForgotPasswordCodeActivity.text}${inputNumber3ForgotPasswordCodeActivity.text}${inputNumber4ForgotPasswordCodeActivity.text}"

            if (code.length == 4) {
                lifecycleScope.launch {
                    try {
                        val (status, response) = authRepository.verifyResetCode(email, code)
                        println("Verify code status: $status, response: $response")
                        if (response.success) {
                            hideAllViews()
                            btnBackChangePasswordActivity.visibility = View.VISIBLE
                            textChangePasswordActivity.visibility = View.VISIBLE
                            imageviewChangePasswordActivity.visibility = View.VISIBLE
                            textUpperChangePasswordActivity.visibility = View.VISIBLE
                            editTextChangePasswordActivity.visibility = View.VISIBLE
                            textLowerChangePasswordActivity.visibility = View.VISIBLE
                            editTextConfirmChangePasswordActivity.visibility = View.VISIBLE
                            btnContinueChangePasswordCodeActivity.visibility = View.VISIBLE
                            Toast.makeText(requireContext(), "Code verified!", Toast.LENGTH_SHORT).show()
                        } else {
                            when (status) {
                                400 -> Toast.makeText(requireContext(), "Invalid code", Toast.LENGTH_SHORT).show()
                                else -> Toast.makeText(requireContext(), response.message ?: "Unknown error", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } catch (e: Exception) {
                        Toast.makeText(requireContext(), "Error verifying code: ${e.message}", Toast.LENGTH_LONG).show()
                        println("Exception in verifyResetCode: ${e.stackTraceToString()}")
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Please enter 4-digit code", Toast.LENGTH_SHORT).show()
            }
        }

        btnContinueChangePasswordCodeActivity.setOnClickListener {
            val email = textViewUserEmailForgotPasswordCodeActivity.text.toString()
            val code = "${inputNumber1ForgotPasswordCodeActivity.text}${inputNumber2ForgotPasswordCodeActivity.text}${inputNumber3ForgotPasswordCodeActivity.text}${inputNumber4ForgotPasswordCodeActivity.text}"
            val password = editTextChangePasswordActivity.text.toString()
            val confirmPassword = editTextConfirmChangePasswordActivity.text.toString()

            if (password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.length < 8) {
                Toast.makeText(requireContext(), "Password must be at least 8 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                try {
                    val (status, response) = authRepository.changePassword(email, code, password)
                    println("Change password status: $status, response: $response")
                    if (response.success) {
                        val sharedPref = activity?.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                        sharedPref?.let {
                            with(it.edit()) {
                                putString("user_email", email)
                                putBoolean("is_logged_in", true)
                                apply()
                            }

                            val userName = authRepository.getUserName(email)
                            if (userName != null) {
                                with(it.edit()) {
                                    putString("user_name", userName)
                                    apply()
                                }
                                Toast.makeText(requireContext(), "Password changed! Welcome, $userName", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(requireContext(), "Password changed, but failed to fetch name", Toast.LENGTH_SHORT).show()
                            }
                        }
                        hideAllViews()
                        imageInfoChangedPasswordActivity.visibility = View.VISIBLE
                        textUpperInfoChangedPasswordActivity.visibility = View.VISIBLE
                        textLowerInfoChangedPasswordActivity.visibility = View.VISIBLE
                        btnContinueInfoChangedPasswordActivity.visibility = View.VISIBLE
                    } else {
                        when (status) {
                            400 -> Toast.makeText(requireContext(), "Invalid code or data", Toast.LENGTH_SHORT).show()
                            500 -> Toast.makeText(requireContext(), "Server error", Toast.LENGTH_SHORT).show()
                            else -> Toast.makeText(requireContext(), response.message ?: "Unknown error", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "Error changing password: ${e.message}", Toast.LENGTH_LONG).show()
                    println("Exception in changePassword: ${e.stackTraceToString()}")
                }
            }
        }

        btnContinueInfoChangedPasswordActivity.setOnClickListener {
            hideAllViews()
            (activity as? MainActivity)?.showBottomNavigation()
            findNavController().navigate(R.id.navigation_temperature_settings)
        }

        btnLoginToTempSettLoginActivity.setOnClickListener {
            val email = editTextTextEmailLoginActivity.text.toString()
            val password = editTextTextPasswordRegisterActivity.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else if (isAdded) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        btnRegisterRegisterActivity.setOnClickListener {
            val name = editTextTextNameRegisterActivity.text.toString()
            val email = editTextEmailRegisterActivity.text.toString()
            val password = editTextPasswordRegisterActivity.text.toString()
            val confirmPassword = editTextConfirmPasswordRegisterActivity.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {
                    registerUser(name, email, password)
                } else if (isAdded) {
                    Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            } else if (isAdded) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        btnOkayInfoCreateAccActivity.setOnClickListener {
            hideAllViews()
            (activity as? MainActivity)?.showBottomNavigation()
            findNavController().navigate(R.id.navigation_temperature_settings)
        }
    }

    private fun hideAllViews() {
        binding.imageStartActivity.visibility = View.GONE
        binding.imageStartText.visibility = View.GONE
        binding.btnStart.visibility = View.GONE
        binding.imageUpperLoginRegisterActivity.visibility = View.GONE
        binding.imageLowerLoginRegisterActivity.visibility = View.GONE
        binding.btnSignUpLoginRegisterActivity.visibility = View.GONE
        binding.btnLoginLoginRegisterActivity.visibility = View.GONE
        binding.btnBackLoginActivity.visibility = View.GONE
        binding.textSignUpLoginActivity.visibility = View.GONE
        binding.imageLoginActivity.visibility = View.GONE
        binding.textViewEmailLoginActivity.visibility = View.GONE
        binding.editTextTextEmailLoginActivity.visibility = View.GONE
        binding.textViewSetPasswordLoginActivity.visibility = View.GONE
        binding.editTextTextPasswordRegisterActivity.visibility = View.GONE
        binding.btnForgotPassword.visibility = View.GONE
        binding.btnLoginToTempSettLoginActivity.visibility = View.GONE
        binding.textViewConstLoginActivity.visibility = View.GONE
        binding.btnSignUpLoginActivity.visibility = View.GONE
        binding.btnBackRegisterActivity.visibility = View.GONE
        binding.textViewSignUpRegisterActivity.visibility = View.GONE
        binding.textViewRegisterUpperRegisterActivity.visibility = View.GONE
        binding.textViewFullNameRegisterActivity.visibility = View.GONE
        binding.editTextTextNameRegisterActivity.visibility = View.GONE
        binding.textViewEmailRegisterActivity.visibility = View.GONE
        binding.editTextEmailRegisterActivity.visibility = View.GONE
        binding.textViewSetPasswordRegisterActivity.visibility = View.GONE
        binding.editTextPasswordRegisterActivity.visibility = View.GONE
        binding.textViewConfirmPasswordRegisterActivity.visibility = View.GONE
        binding.editTextConfirmPasswordRegisterActivity.visibility = View.GONE
        binding.btnRegisterRegisterActivity.visibility = View.GONE
        binding.btnBackForgotPasswordEmailActivity.visibility = View.GONE
        binding.textForgotPasswordEmailActivity.visibility = View.GONE
        binding.imageUpperForgotPasswordEmailActivity.visibility = View.GONE
        binding.imageLowerForgotPasswordEmailActivity.visibility = View.GONE
        binding.InputEditTextForgotPasswordEmailActivity.visibility = View.GONE
        binding.btnSendCodeForgotPasswordEmailActivity.visibility = View.GONE
        binding.btnBackForgotPasswordCodeActivity.visibility = View.GONE
        binding.textForgotPasswordCodeActivity.visibility = View.GONE
        binding.imageForgotPasswordCodeActivity.visibility = View.GONE
        binding.textViewForgotPasswordCodeActivity.visibility = View.GONE
        binding.textViewEmailForgotPasswordCodeActivity.visibility = View.GONE
        binding.textViewUserEmailForgotPasswordCodeActivity.visibility = View.GONE
        binding.btnSubmitForgotPasswordCodeActivity.visibility = View.GONE
        binding.InputNumber1ForgotPasswordCodeActivity.visibility = View.GONE
        binding.InputNumber2ForgotPasswordCodeActivity.visibility = View.GONE
        binding.InputNumber3ForgotPasswordCodeActivity.visibility = View.GONE
        binding.InputNumber4ForgotPasswordCodeActivity.visibility = View.GONE
        binding.textReceiveCodeForgotPasswordCodeActivity.visibility = View.GONE
        binding.btnResentCodeForgotPasswordCodeActivity.visibility = View.GONE
        binding.imageInfoCreateAccActivity.visibility = View.GONE
        binding.textUpperInfoCreateAccActivity.visibility = View.GONE
        binding.textLowerInfoCreateAccActivity.visibility = View.GONE
        binding.btnOkayInfoCreateAccActivity.visibility = View.GONE
        binding.btnBackChangePasswordActivity.visibility = View.GONE
        binding.textChangePasswordActivity.visibility = View.GONE
        binding.imageviewChangePasswordActivity.visibility = View.GONE
        binding.textUpperChangePasswordActivity.visibility = View.GONE
        binding.editTextChangePasswordActivity.visibility = View.GONE
        binding.textLowerChangePasswordActivity.visibility = View.GONE
        binding.editTextConfirmChangePasswordActivity.visibility = View.GONE
        binding.btnContinueChangePasswordCodeActivity.visibility = View.GONE
        binding.imageInfoChangedPasswordActivity.visibility = View.GONE
        binding.textUpperInfoChangedPasswordActivity.visibility = View.GONE
        binding.textLowerInfoChangedPasswordActivity.visibility = View.GONE
        binding.btnContinueInfoChangedPasswordActivity.visibility = View.GONE
    }

    private fun loginUser(email: String, password: String) {
        lifecycleScope.launch {
            try {
                val context = requireContext()
                val (status, response) = authRepository.login(email, password, context)
                if (isAdded) {
                    when (status) {
                        200 -> {
                            val sharedPref = activity?.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                            sharedPref?.let {
                                with(it.edit()) {
                                    putString("user_email", email)
                                    putBoolean("is_logged_in", true)
                                    apply()
                                }
                                val userName = authRepository.getUserName(email)
                                if (userName != null) {
                                    with(it.edit()) {
                                        putString("user_name", userName)
                                        apply()
                                    }
                                    Toast.makeText(requireContext(), "Welcome, $userName!", Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(requireContext(), "Failed to fetch name", Toast.LENGTH_SHORT).show()
                                }
                            }
                            (activity as? MainActivity)?.showBottomNavigation()
                            findNavController().navigate(R.id.navigation_temperature_settings)
                        }
                        400 -> Toast.makeText(requireContext(), "All fields must be filled in", Toast.LENGTH_SHORT).show()
                        401 -> Toast.makeText(requireContext(), "Incorrect password", Toast.LENGTH_SHORT).show()
                        404 -> Toast.makeText(requireContext(), "User with this email not found", Toast.LENGTH_SHORT).show()
                        else -> Toast.makeText(requireContext(), "Unknown error: ${response.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                if (isAdded) {
                    Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    println("Login exception: ${e.stackTraceToString()}")
                }
            }
        }
    }

    private fun registerUser(name: String, email: String, password: String) {
        if (email.isEmpty() || !email.contains("@gmail.com")) {
            Toast.makeText(requireContext(), "Invalid email", Toast.LENGTH_SHORT).show()
            return
        }

        if (name.isEmpty()) {
            Toast.makeText(requireContext(), "Name cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }
        if (name.length < 4 || !name.all { it.isLetter() }) {
            Toast.makeText(requireContext(), "Name must have at least 4 letters", Toast.LENGTH_SHORT).show()
            return
        }

        if (name.length > 15) {
            Toast.makeText(requireContext(), "Name must be at most 15 characters", Toast.LENGTH_SHORT).show()
            return
        }

        if (name.any { it.isDigit() }) {
            Toast.makeText(requireContext(), "Name cannot contain numbers", Toast.LENGTH_SHORT).show()
            return
        }

        if (password.isEmpty()) {
            Toast.makeText(requireContext(), "Password cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        if (password.length < 8) {
            Toast.makeText(requireContext(), "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            try {
                val (status, response) = authRepository.register(name, email, password)
                if (isAdded) {
                    when (status) {
                        201 -> {
                            val sharedPref = activity?.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                            sharedPref?.let {
                                with(it.edit()) {
                                    putString("user_email", email)
                                    putString("user_name", name)
                                    putBoolean("is_logged_in", true)
                                    apply()
                                }
                                val fetchedName = authRepository.getUserName(email)
                                if (fetchedName != null && fetchedName != name) {
                                    with(it.edit()) {
                                        putString("user_name", fetchedName)
                                        apply()
                                    }
                                    Toast.makeText(requireContext(), "Welcome, $fetchedName!", Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(requireContext(), "Welcome, $name!", Toast.LENGTH_SHORT).show()
                                }
                            }
                            hideAllViews()
                            binding.imageInfoCreateAccActivity.visibility = View.VISIBLE
                            binding.textUpperInfoCreateAccActivity.visibility = View.VISIBLE
                            binding.textLowerInfoCreateAccActivity.visibility = View.VISIBLE
                            binding.btnOkayInfoCreateAccActivity.visibility = View.VISIBLE
                        }
                        400 -> Toast.makeText(requireContext(), "All fields must be filled in", Toast.LENGTH_SHORT).show()
                        409 -> Toast.makeText(requireContext(), "A user with this email already exists", Toast.LENGTH_SHORT).show()
                        else -> Toast.makeText(requireContext(), "Unknown error: ${response.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                if (isAdded) {
                    Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    println("Register exception: ${e.stackTraceToString()}")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}