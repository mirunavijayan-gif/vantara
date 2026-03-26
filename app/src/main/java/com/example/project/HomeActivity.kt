package com.example.project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        findViewById<Button>(R.id.userButton).setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }

        findViewById<Button>(R.id.mechanicButton).setOnClickListener {
            // Navigate directly to Mechanic Dashboard
            startActivity(Intent(this, MechanicActivity::class.java))
        }

        findViewById<Button>(R.id.administratorButton).setOnClickListener {
            startActivity(Intent(this, AdministratorActivity::class.java))
        }
    }
}