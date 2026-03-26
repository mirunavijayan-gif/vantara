package com.example.project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

class MechanicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mechanic)

        val sharedPref = getSharedPreferences("VantaraPrefs", Context.MODE_PRIVATE)
        val pendingRequest = sharedPref.getString("pending_request", null)
        val status = sharedPref.getString("request_status", "None")

        val requestCard = findViewById<MaterialCardView>(R.id.requestCard)
        val requestDetails = findViewById<TextView>(R.id.requestDetails)
        val acceptButton = findViewById<Button>(R.id.acceptButton)
        val denyButton = findViewById<Button>(R.id.denyButton)
        val mechNotificationBadge = findViewById<TextView>(R.id.mechNotificationBadge)

        // Check if there is a pending request to display
        if (pendingRequest != null && status == "Sent") {
            requestCard.visibility = View.VISIBLE
            requestDetails.text = "Issue: $pendingRequest"
            mechNotificationBadge.visibility = View.VISIBLE
            mechNotificationBadge.text = "1"
        }

        acceptButton.setOnClickListener {
            with(sharedPref.edit()) {
                putString("request_status", "Accepted")
                apply()
            }
            requestCard.visibility = View.GONE
            mechNotificationBadge.visibility = View.GONE
            Toast.makeText(this, "Request Accepted!", Toast.LENGTH_SHORT).show()
        }

        denyButton.setOnClickListener {
            with(sharedPref.edit()) {
                putString("request_status", "Denied")
                // We keep the request in prefs but mark it as denied
                apply()
            }
            requestCard.visibility = View.GONE
            mechNotificationBadge.visibility = View.GONE
            Toast.makeText(this, "Request Denied. You are now available.", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.logoutButton).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        
        findViewById<Button>(R.id.setAvailabilityButton).setOnClickListener {
            Toast.makeText(this, "Availability Updated", Toast.LENGTH_SHORT).show()
        }
    }
}