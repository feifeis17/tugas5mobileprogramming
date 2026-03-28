package com.feisal.tugas5;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    //UI
    private TextInputLayout tilName, tilEmail, tilPassword, tilConfirm, tilOtherHobi;
    private TextInputEditText etName, etEmail, etPassword, etConfirm, etOtherHobi;
    private CheckBox cbCoding, cbOtomotif, cbStreaming, cbOlahraga, cbReading, cbTraveling;
    private RadioGroup rgGender;
    private Button btnRegister;
    private ScrollView mainScrollView;

    //Layout Steps & Anim
    private LinearLayout layoutStep1, layoutStep2, layoutStep3;
    private Animation fadeSlideUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView ivBackgroundReg = findViewById(R.id.iv_background_register);
        Animation fadeInBackReg = AnimationUtils.loadAnimation(this, R.anim.fade_in_background);
        ivBackgroundReg.startAnimation(fadeInBackReg);

        // 1Inisialisasi Semua View
        initViews();
        // 2Load Animasi
        fadeSlideUp = AnimationUtils.loadAnimation(this, R.anim.fade_slide_up);
        // 3Setup Transitions
        setupTransitions();
        // 4Tombol Register (Click & Long Click)
        btnRegister.setOnClickListener(v -> validateForm());
        btnRegister.setOnLongClickListener(v -> {
            resetForm();
            return true;
        });
    }

    private void initViews() {
        mainScrollView = findViewById(R.id.main_scroll_view);
        layoutStep1 = findViewById(R.id.layout_step_1);
        layoutStep2 = findViewById(R.id.layout_step_2);
        layoutStep3 = findViewById(R.id.layout_step_3);

        tilName = findViewById(R.id.til_name);
        tilEmail = findViewById(R.id.til_email);
        tilPassword = findViewById(R.id.til_password);
        tilConfirm = findViewById(R.id.til_confirm_password);
        tilOtherHobi = findViewById(R.id.til_other_hobi);

        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etConfirm = findViewById(R.id.et_confirm_password);
        etOtherHobi = findViewById(R.id.et_other_hobi);

        cbCoding = findViewById(R.id.cb_coding);
        cbOtomotif = findViewById(R.id.cb_otomotif);
        cbStreaming = findViewById(R.id.cb_Streaming);
        cbOlahraga = findViewById(R.id.cb_olahraga);
        cbReading = findViewById(R.id.cb_reading);
        cbTraveling = findViewById(R.id.cb_traveling);

        rgGender = findViewById(R.id.rg_gender);
        btnRegister = findViewById(R.id.btn_register);
    }

    private void setupTransitions() {
        etConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pass = etPassword.getText().toString();
                if (s.toString().equals(pass) && s.length() >= 6) {
                    if (layoutStep2.getVisibility() == View.GONE) {
                        layoutStep2.setVisibility(View.VISIBLE);
                        layoutStep2.startAnimation(fadeSlideUp);
                    }
                    tilConfirm.setError(null);
                }
            }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        });
        rgGender.setOnCheckedChangeListener((group, checkedId) -> {
            if (layoutStep3.getVisibility() == View.GONE) {
                layoutStep3.setVisibility(View.VISIBLE);
                layoutStep3.startAnimation(fadeSlideUp);
                // Auto scroll ke bawah
                mainScrollView.post(() -> mainScrollView.fullScroll(View.FOCUS_DOWN));
            }
        });
    }

    private void validateForm() {
        // Logika validasi
        boolean isValid = true;
        if (etName.getText().toString().isEmpty()) { tilName.setError("Isi nama!"); isValid = false; } else tilName.setError(null);
        if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) { tilEmail.setError("Email salah!"); isValid = false; } else tilEmail.setError(null);

        if (isValid) showConfirmDialog();
    }

    private void showConfirmDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Yakin data sudah benar?")
                .setPositiveButton("Ya, Kirim", (d, w) -> {
                    Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show();

                    // Lempar balik ke Login
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Biar nggak numpuk activity-nya
                    startActivity(intent);

                    // Animasi: Kiri ke Kanan (seolah balik)
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    finish();
                })
                .setNegativeButton("Cek Lagi", null)
                .show();
    }

    private void resetForm() {
        etName.setText(""); etEmail.setText(""); etPassword.setText(""); etConfirm.setText("");
        rgGender.clearCheck();
        layoutStep2.setVisibility(View.GONE);
        layoutStep3.setVisibility(View.GONE);
        Toast.makeText(this, "Form di-reset!", Toast.LENGTH_SHORT).show();
    }
}