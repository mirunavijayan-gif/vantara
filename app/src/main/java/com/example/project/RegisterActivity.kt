package com.example.project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val nameInput = findViewById<EditText>(R.id.regName)
        val ageInput = findViewById<EditText>(R.id.regAge)
        val emailInput = findViewById<EditText>(R.id.regEmail)
        val passwordInput = findViewById<EditText>(R.id.regPassword)
        val mobileInput = findViewById<EditText>(R.id.regMobile)
        val vehicleTypeInput = findViewById<EditText>(R.id.regVehicleType)
        val vehicleNumberInput = findViewById<EditText>(R.id.regVehicleNumber)
        val roleDropdown = findViewById<AutoCompleteTextView>(R.id.regRoleDropdown)
        val registerBtn = findViewById<Button>(R.id.registerBtn)
        val goToLogin = findViewById<TextView>(R.id.goToLogin)

        val roles = arrayOf("Customer", "Mechanic")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, roles)
        roleDropdown.setAdapter(adapter)

        registerBtn.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            val role = roleDropdown.text.toString()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || role.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (email.endsWith("@vantara.com")) {
                // Save role for this email in SharedPreferences
                val sharedPref = getSharedPreferences("VantaraPrefs", Context.MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putString(email, role)
                    apply()
                }

                Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Registration allowed only with @vantara.com", Toast.LENGTH_LONG).show()
            }
        }

        goToLogin.setOnClickListener {
            finish()
        }
    }
}