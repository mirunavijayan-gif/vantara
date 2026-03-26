package com.example.project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val issueDropdown = findViewById<AutoCompleteTextView>(R.id.issueDropdown)
        val submitButton = findViewById<Button>(R.id.submitButton)
        val notificationBadge = findViewById<TextView>(R.id.notificationBadge)

        // Dropdown setup
        val issues = arrayOf("Breakdown", "Petrol", "Emergency")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, issues)
        issueDropdown.setAdapter(adapter)

        // Update notification badge if there's a pending status
        val sharedPref = getSharedPreferences("VantaraPrefs", Context.MODE_PRIVATE)
        val status = sharedPref.getString("request_status", "None")
        if (status != "None") {
            notificationBadge.visibility = View.VISIBLE
            notificationBadge.text = "1"
        }

        submitButton.setOnClickListener {
            val selectedIssue = issueDropdown.text.toString()
            if (selectedIssue.isEmpty()) {
                Toast.makeText(this, "Please select an issue", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save request for Mechanic
            with(sharedPref.edit()) {
                putString("pending_request", selectedIssue)
                putString("request_status", "Sent")
                apply()
            }

            Toast.makeText(this, "Request Sent Successfully!", Toast.LENGTH_LONG).show()
            
            // Show notification badge locally
            notificationBadge.visibility = View.VISIBLE
            notificationBadge.text = "1"
        }
    }
}