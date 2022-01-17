package com.example.homeciti.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ethanhua.skeleton.SkeletonScreen
import com.example.homeciti.R
import com.example.homeciti.data.model.Service
import com.example.homeciti.databinding.ActivityMainBinding
import com.example.homeciti.ui.adapters.ServiceAdapter
import com.example.homeciti.ui.viewmodel.ServiceViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import com.faltenreich.skeletonlayout.Skeleton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}