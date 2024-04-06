package com.example.worklink

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.worklink.utils.TokenManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var tokenManager: TokenManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val pp = findViewById<ImageView>(R.id.profile);
        pp.setOnClickListener {
            val role = tokenManager.getRole().toString()
            if(role == "Worker"){
                startActivity(Intent(this, WorkerProfile::class.java))
            }else if(role == "Factory"){
                startActivity(Intent(this, FactoryFragment::class.java))
            }else{
                startActivity(Intent(this, StartupFragment::class.java))
            }
        }

        val viewpager = findViewById<ViewPager2>(R.id.viewpager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        val adapter = FragmentAdapterMain(this)
        viewpager.adapter = adapter


        TabLayoutMediator(tabLayout, viewpager, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            val tabNames = listOf("All", "NearYou", "Startup", "Workers")
            tab.text = tabNames[position]
        }).attach()
    }
}