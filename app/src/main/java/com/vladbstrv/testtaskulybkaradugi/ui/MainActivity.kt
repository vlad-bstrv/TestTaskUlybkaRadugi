package com.vladbstrv.testtaskulybkaradugi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.vladbstrv.testtaskulybkaradugi.R
import com.vladbstrv.testtaskulybkaradugi.databinding.ActivityMainBinding
import com.vladbstrv.testtaskulybkaradugi.ui.login.LoginFragment
import com.vladbstrv.testtaskulybkaradugi.ui.order.OrderFragment

class MainActivity : AppCompatActivity(), LoginFragment.Controller {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
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
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_exit -> {
                getSharedPreferences(getString(R.string.app_name), 0)
                    .edit().clear().apply()
                openScreen(LoginFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
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