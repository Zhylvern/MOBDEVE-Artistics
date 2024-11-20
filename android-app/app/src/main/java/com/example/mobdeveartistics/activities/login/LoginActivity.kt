package com.example.mobdeveartistics.activities.login

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobdeveartistics.R

class LoginActivity : AppCompatActivity() {
    private var btnLogin: Button? = null
    private var btnSignUp: TextView? = null
    private var etEmail: EditText? = null
    private var etPassword: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById<View?>(R.id.main),
            OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                val systemBars = insets!!.getInsets(WindowInsetsCompat.Type.systemBars())
                v!!.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            })

        btnLogin = findViewById<Button?>(R.id.btnLogin)
        btnSignUp = findViewById<TextView?>(R.id.btnSignUp)

        etEmail = findViewById<EditText>(R.id.etEmail) // Initialize EditText for email
        etPassword = findViewById<EditText>(R.id.etPassword) // Initialize EditText for password
    }

    fun btnLoginClicked(v: View?) {
        // Retrieve the text from EditText fields
        val email = etEmail?.text.toString()
        val password = etPassword?.text.toString()

        // Display the email and password in a Toast
        Toast.makeText(this@LoginActivity, "Email: $email\nPassword: $password", Toast.LENGTH_SHORT).show()
    }

    fun btnSignUpClicked(v: View?) {
        Toast.makeText(this@LoginActivity, "Sign Up button clicked!", Toast.LENGTH_SHORT).show()
    }
}