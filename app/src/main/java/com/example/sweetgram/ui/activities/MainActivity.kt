package com.example.sweetgram.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sweetgram.R
import com.example.sweetgram.SweetgramApplication
import com.example.sweetgram.data.DataNode
import com.example.sweetgram.data.entitys.Lover
import com.example.sweetgram.data.entitys.LoverCredentials
import com.example.sweetgram.ui.activities.login.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object{
        lateinit var user: Lover
    }

    @Inject lateinit var dataNode: DataNode
    @Inject lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navView.setupWithNavController(navController)

        SweetgramApplication.instance.injector.inject(this)

        val creds = intent.getSerializableExtra("credentials") as LoverCredentials
        user = dataNode.getLoverByCredentials(creds)!!

        findViewById<ImageView>(R.id.logout_button).setOnClickListener {
            if (sharedPreferences.contains("username")) {
                sharedPreferences.edit().clear().apply()
                val intent = Intent(this, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }


        }

    }


}
