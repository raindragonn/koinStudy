package com.raindragonn.koinstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.raindragonn.koinstudy.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        observe()
    }

    private fun initViews() = with(binding) {
        btnShowLoading.setOnClickListener { viewModel.startLoading() }
        btnDismissLoading.setOnClickListener { viewModel.stopLoading() }
        btnPerson.setOnClickListener { viewModel.getPerson() }
    }

    private fun observe() = with(viewModel) {
        isLoading.observe(this@MainActivity) {
            binding.pbLoading.isVisible = it
        }

        text.observe(this@MainActivity) {
            binding.tvPerson.text = it
        }
    }
}