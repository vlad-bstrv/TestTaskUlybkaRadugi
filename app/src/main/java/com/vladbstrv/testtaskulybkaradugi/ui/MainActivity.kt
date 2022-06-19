package com.vladbstrv.testtaskulybkaradugi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.vladbstrv.testtaskulybkaradugi.R
import com.vladbstrv.testtaskulybkaradugi.databinding.ActivityMainBinding
import com.vladbstrv.testtaskulybkaradugi.ui.login.LoginFragment
import com.vladbstrv.testtaskulybkaradugi.ui.order.OrderFragment
import okhttp3.Credentials
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), LoginFragment.Controller {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (
            getSharedPreferences(getString(R.string.app_name), 0).getString("LOGIN", "")
                .isNullOrBlank() &&
            getSharedPreferences(getString(R.string.app_name), 0).getString("PASSWORD", "")
                .isNullOrBlank()
        ) {
            openScreen(LoginFragment())
        } else {
            openScreen(OrderFragment())
        }
    }

    private fun openScreen(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.container.id, fragment)
            .commit()
    }

    override fun openDetailScreen() {
        openScreen(OrderFragment())
    }
}