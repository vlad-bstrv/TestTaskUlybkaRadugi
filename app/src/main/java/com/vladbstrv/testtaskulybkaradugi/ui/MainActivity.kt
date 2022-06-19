package com.vladbstrv.testtaskulybkaradugi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.vladbstrv.testtaskulybkaradugi.R
import com.vladbstrv.testtaskulybkaradugi.databinding.ActivityMainBinding
import okhttp3.Credentials
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getData()

        val a = Credentials.basic("l12345678", "p12345678")
        Log.d("TAG", "onCreate() called with: savedInstanceState = $a")
//        bDEyMzQ1Njc4OnAxMjM0NTY3OA==
        viewModel.data.observe(this) {
            Log.d("TAG", it.toString())
        }
    }
}