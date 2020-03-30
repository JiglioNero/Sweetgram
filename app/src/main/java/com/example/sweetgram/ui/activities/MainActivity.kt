package com.example.sweetgram.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sweetgram.R
import com.example.sweetgram.SweetgramApplication
import com.example.sweetgram.data.DataNode
import com.example.sweetgram.data.entitys.Lover
import com.example.sweetgram.data.entitys.LoverCredentials
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object{
        lateinit var user: Lover
    }

    @Inject lateinit var dataNode: DataNode

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

    }


}
