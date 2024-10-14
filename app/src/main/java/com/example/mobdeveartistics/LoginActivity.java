package com.example.mobdeveartistics;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        btnLogin = findViewById(R.id.btnlogin);
        btnSignUp = findViewById(R.id.btnSignUp);
    }
    public void btnLoginClicked (View v) {
        Toast toast = Toast.makeText(LoginActivity.this, "Login button clicked!", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void btnSignUpClicked (View v) {
        Toast toast = Toast.makeText(LoginActivity.this, "Sign Up button clicked!", Toast.LENGTH_SHORT);
        toast.show();
    }
}