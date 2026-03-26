package com.example.project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailInput = findViewById<EditText>(R.id.loginEmail)
        val passwordInput = findViewById<EditText>(R.id.loginPassword)
        val loginBtn = findViewById<Button>(R.id.loginBtn)
        val goToRegister = findViewById<TextView>(R.id.goToRegister)

        loginBtn.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!email.endsWith("@vantara.com")) {
                Toast.makeText(this, "Login allowed only with @vantara.com", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // Admin hardcoded
            if (email == "admin@vantara.com" && password == "admin123") {
                startActivity(Intent(this, AdministratorActivity::class.java))
                finish()
                return@setOnClickListener
            }

            // Retrieve role from SharedPreferences
            val sharedPref = getSharedPreferences("VantaraPrefs", Context.MODE_PRIVATE)
            val role = sharedPref.getString(email, null)

            if (role != null) {
                when (role) {
                    "Customer" -> startActivity(Intent(this, UserActivity::class.java))
                    "Mechanic" -> startActivity(Intent(this, MechanicActivity::class.java))
                }
                finish()
            } else {
                Toast.makeText(this, "User not found. Please register.", Toast.LENGTH_SHORT).show()
            }
        }

        goToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}