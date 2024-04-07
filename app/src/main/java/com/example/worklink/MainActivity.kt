package com.example.worklink

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.example.worklink.utils.TokenManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text
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

        var tabNames:List<String>
        val pp = findViewById<ImageView>(R.id.profile);
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        val role = tokenManager.getRole().toString()
        if(role == "Worker"){
            fab.isVisible = false
        }
        pp.setOnClickListener {
            if(role == "Worker"){
                findViewById<TextView>(R.id.greet).setText("Hi, Ayush")
                startActivity(Intent(this, WorkerProfile::class.java))
            }else if(role == "Factory"){
                findViewById<TextView>(R.id.greet).setText("Hi, JK Moulding")
                startActivity(Intent(this, FactoryOwnerProfile::class.java))
            }else{
                startActivity(Intent(this, StartupProfile::class.java))
            }
        }

        fab.setOnClickListener {
            startActivity(Intent(this, CreateGigActivity::class.java))
        }


        val viewpager = findViewById<ViewPager2>(R.id.viewpager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)


        val adapter = FragmentAdapterMain(this)
        viewpager.adapter = adapter


        TabLayoutMediator(tabLayout, viewpager, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            val role = tokenManager.getRole().toString()
            if(role=="Worker"){
                tabNames=listOf("All","StartUp","Factory","Liked Gigs")
            }else if(role=="Factory") {
                tabNames = listOf("All", "StartUps", "Workers", "Your Gigs")
            }else{
                tabNames= listOf("All","Machines","Workers","Liked Gigs")
            }
            //val tabNames = listOf("All", "NearYou", "Startup", "Workers")
            tab.text = tabNames[position]
        }).attach()
    }
}