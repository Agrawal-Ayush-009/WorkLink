package com.example.worklink

import android.location.Location
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.worklink.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import java.util.Objects
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    public var role : String = ""
    public var email: String = ""
    public var password: String = ""
    public var factoryName:String=""
    public var factoryWork:String=""
    public var factoryCity:String=""
    public var factoryState:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
    }
}