package com.feisal.tugas5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    private TextView tvRegister;
    private Button btnLogin;
    private TextInputEditText etEmail, etPassword;
    private ProgressBar loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImageView ivBackground = findViewById(R.id.iv_background_kampus);
        Animation fadeInBack = AnimationUtils.loadAnimation(this, R.anim.fade_in_background);
        ivBackground.startAnimation(fadeInBack);

        tvRegister = findViewById(R.id.tv_go_to_register);
        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        btnLogin = findViewById(R.id.btn_login);
        etEmail = findViewById(R.id.et_login_email);
        etPassword = findViewById(R.id.et_login_password);
        loader = findViewById(R.id.loading_bar); // Pastikan di XML ID-nya @+id/loading_bar

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString();
            String pass = etPassword.getText().toString();

            btnLogin.setVisibility(View.GONE);
            loader.setVisibility(View.VISIBLE);

            new android.os.Handler().postDelayed(() -> {
                loader.setVisibility(View.GONE);
                btnLogin.setVisibility(View.VISIBLE);

                if (email.contains("@") && pass.length() >= 6) {
                    Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Gagal! Cek email/password", Toast.LENGTH_SHORT).show();
                }
            }, 3000);
        });
    }
}