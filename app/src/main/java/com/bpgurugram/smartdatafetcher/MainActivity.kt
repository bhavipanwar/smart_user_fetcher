package com.bpgurugram.smartdatafetcher

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bpgurugram.smartdatafetcher.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.loading.visibility = View.GONE

    }

    fun showLoading()
    {
        binding.loading.visibility = View.VISIBLE
    }

    fun hideLoading()
    {
        binding.loading.visibility = View.GONE
    }
}


