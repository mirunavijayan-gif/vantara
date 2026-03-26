package com.example.project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AdministratorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrator)

        findViewById<Button>(R.id.manageUsersButton).setOnClickListener {
            startActivity(Intent(this, ManageUsersActivity::class.java))
        }

        findViewById<Button>(R.id.manageMechanicsButton).setOnClickListener {
            startActivity(Intent(this, ManageMechanicsActivity::class.java))
        }

        findViewById<Button>(R.id.handleComplaintsButton).setOnClickListener {
            startActivity(Intent(this, HandleComplaintsActivity::class.java))
        }

        findViewById<Button>(R.id.managePaymentsButton).setOnClickListener {
            startActivity(Intent(this, ManagePaymentsActivity::class.java))
        }

        findViewById<Button>(R.id.generateReportsButton).setOnClickListener {
            Toast.makeText(this, "Report Generated", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.adminLogoutButton).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}