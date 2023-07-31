package com.example.randomuser.userscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.randomuser.R
import com.example.randomuser.data.ApiResponseStatus
import com.example.randomuser.databinding.ActivityMainBinding
import com.example.randomuser.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {


    private val dogListViewModel: UserViewModel by viewModels()
    private lateinit var binding: ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)

        setContentView(binding.root)

        dogListViewModel.userData.observe(this) {
                user ->
                Log.e("Error", user.toString())
            binding.userData.text = user.toString()
        }

        dogListViewModel.status.observe(this) {
                status ->

            when(status) {
                is ApiResponseStatus.Error -> Log.e("Error", "error response")
                is ApiResponseStatus.Loading -> Log.e("Error", "Loading")
                is ApiResponseStatus.Success -> Log.e("Error", "success")
            }
        }
    }
}