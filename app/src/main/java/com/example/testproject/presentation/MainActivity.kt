package com.example.testproject.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.testproject.R
import com.example.testproject.common.Resource
import com.example.testproject.databinding.ActivityMainBinding
import com.example.testproject.presentation.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val postViewModel: PostViewModel by viewModels()

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.viewModel = postViewModel

        postViewModel.getUserData()

        CoroutineScope(Dispatchers.Main).launch {
            postViewModel.userStateFlow.collect {
                when (it) {
                    is Resource.ERROR -> {}
                    is Resource.LOADING -> {}
                    is Resource.SUCCESS -> {
                        it.data?.let { it1 ->
                            Log.e("MainActivity", "onCreate: " + it1)
                            postViewModel.userAdapter.setData(it1)
                        }
                    }

                }
            }
        }

    }
}