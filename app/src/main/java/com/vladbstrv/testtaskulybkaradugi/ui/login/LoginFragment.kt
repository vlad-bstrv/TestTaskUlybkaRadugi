package com.vladbstrv.testtaskulybkaradugi.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.vladbstrv.testtaskulybkaradugi.R
import com.vladbstrv.testtaskulybkaradugi.data.AppState
import com.vladbstrv.testtaskulybkaradugi.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModel()
    private val controller: Controller
        get() {
            return activity as Controller
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.text

        binding.btnLogin.setOnClickListener {
            requireActivity().getSharedPreferences(getString(R.string.app_name), 0)
                .edit().putString("LOGIN", binding.editTextNameUser.editText?.text.toString())
                .apply()
            requireActivity().getSharedPreferences(getString(R.string.app_name), 0)
                .edit().putString("PASSWORD", binding.editTextPassword.editText?.text.toString())
                .apply()
            viewModel.getData()
        }

        viewModel.data.observe(viewLifecycleOwner) {
            when (it) {
                is AppState.Success -> {
                    controller.openDetailScreen()
                }
                is AppState.Loading -> {
                    binding.loadingFrameLayout.visibility = View.VISIBLE
                }
                is AppState.Error -> {
                    binding.loadingFrameLayout.visibility = View.GONE
                    requireActivity().getSharedPreferences(getString(R.string.app_name), 0)
                        .edit().clear().apply()
                    Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface Controller {
        fun openDetailScreen()
    }

}