package com.example.mobdeveartistics.activities.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.example.mobdeveartistics.activities.feed.MainActivity

import com.example.mobdeveartistics.network.RetrofitApiService
import com.example.mobdeveartistics.network.ApiService
import com.example.mobdeveartistics.network.login.LoginRequest
import com.example.mobdeveartistics.network.login.LoginResponse
import com.example.mobdeveartistics.network.register.RegisterRequest
import com.example.mobdeveartistics.network.register.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

        btnLogin?.setOnClickListener { btnLoginClicked() }
    }

    fun btnLoginClicked() {
        try {
            val email = etEmail?.text.toString()
            val password = etPassword?.text.toString()

            val loginRequest = LoginRequest(email, password)

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
                return
            }

            RetrofitApiService().getRetrofitInstance().create(ApiService::class.java)
                .login(loginRequest).enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        val loginResponse = response.body()
                        if (response.isSuccessful) {
                            Toast.makeText(this@LoginActivity, "Welcome, ${loginResponse?.message}", Toast.LENGTH_SHORT).show()

                            // User Data
                            val accessToken = loginResponse?.accessToken
                            val userID = loginResponse?.user?.id

                            // Redirect back to feed
                            val i = Intent(applicationContext, MainActivity::class.java)
                            i.putExtra("accessToken", accessToken)
                            i.putExtra("userID", userID)
                            startActivity(i)
                        } else {
                            Toast.makeText(this@LoginActivity, "There was an issue with your sign-in. Please check your credentials and try again.", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
        } catch (e: Exception) {
            Log.e("LoginActivity", "Exception occurred: ${e.message}")
            Toast.makeText(this, "An error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    fun btnSignUpClicked(v: View?) {
        try {
            val email = etEmail?.text.toString()
            val password = etPassword?.text.toString()

            val registerRequest = RegisterRequest(email, password)

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
                return
            }

            RetrofitApiService().getRetrofitInstance().create(ApiService::class.java)
                .register(registerRequest).enqueue(object : Callback<RegisterResponse> {
                    override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                        val registerResponse = response.body()
                        if (response.isSuccessful) {
                            Toast.makeText(this@LoginActivity, "Welcome, ${registerResponse?.message}", Toast.LENGTH_SHORT).show()


                        } else {
                            Toast.makeText(this@LoginActivity, "There was an issue with your sign-up.", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
        } catch (e: Exception) {
            Log.e("SignUpActivity", "Exception occurred: ${e.message}")
            Toast.makeText(this, "An error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}