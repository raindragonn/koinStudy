package com.raindragonn.koinstudy.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.raindragonn.koinstudy.data.SampleRepository
import com.raindragonn.koinstudy.databinding.ActivityMainBinding
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    // viewModel 주입
//    private val viewModel: MainViewModel by viewModel { parametersOf(repo) }
    private val viewModel: MainViewModel by viewModel()

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    // SampleRepository 주입
    private val repo: SampleRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        observe()
    }

    private fun showToast() {
        repo.getPerson("a", 10).let {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun showToast2() {
        // SampleRepository 가져오기
        val repo2: SampleRepository = get()
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