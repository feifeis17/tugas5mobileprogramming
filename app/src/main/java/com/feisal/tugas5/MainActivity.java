package com.feisal.tugas5;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout tilName, tilEmail, tilPassword, tilConfirm, tilOtherHobi;
    private TextInputEditText etName, etEmail, etPassword, etConfirm, etOtherHobi;
    private CheckBox cbCoding, cbOtomotif, cbStreaming, cbOlahraga, cbReading, cbTraveling;
    private RadioGroup rgGender;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisialisasi Checkbox
        cbCoding = findViewById(R.id.cb_coding);
        cbOtomotif = findViewById(R.id.cb_otomotif);
        cbStreaming = findViewById(R.id.cb_Streaming);
        cbOlahraga = findViewById(R.id.cb_olahraga);
        cbReading = findViewById(R.id.cb_reading);
        cbTraveling = findViewById(R.id.cb_traveling);

        //inisialisasi Input
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

        rgGender = findViewById(R.id.rg_gender);
        btnRegister = findViewById(R.id.btn_register);

        setupRealTimeValidation();

        //Click Biasa untuk Validasi & Dialog
        btnRegister.setOnClickListener(v -> validateForm());

        //Long Interaction (Long Press) untuk Reset Form
        btnRegister.setOnLongClickListener(v -> {
            resetForm();
            return true;
        });
    }

    private void setupRealTimeValidation() {
        etConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pass = etPassword.getText().toString();
                String confirm = s.toString();
                if (!confirm.equals(pass)) {
                    tilConfirm.setError("Sorry Bro Password Ente tidak cocok!");
                } else {
                    tilConfirm.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void validateForm() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String pass = etPassword.getText().toString().trim();
        String confirm = etConfirm.getText().toString().trim();
        String otherHobi = etOtherHobi.getText().toString().trim();

        boolean isValid = true;

        if (name.isEmpty()) {
            tilName.setError("Ha Asli Ente Ga Punya Nama?");
            isValid = false;
        } else tilName.setError(null);

        if (email.isEmpty()) {
            tilEmail.setError("Email Ente Isi Bro");
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.setError("Kurang Apa Coba?");
            isValid = false;
        } else tilEmail.setError(null);

        if (pass.length() < 6) {
            tilPassword.setError("Password minimal 6 karakter Beb");
            isValid = false;
        } else tilPassword.setError(null);

        if (!confirm.equals(pass)) {
            tilConfirm.setError("Password harus sama Maimunahhh");
            isValid = false;
        } else tilConfirm.setError(null);

        if (rgGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Jenis Kelamin Ente Apa nichh (aw)", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        boolean hobiUtamaTerisi = cbCoding.isChecked() || cbOtomotif.isChecked() ||
                cbStreaming.isChecked() || cbOlahraga.isChecked() ||
                cbReading.isChecked() || cbTraveling.isChecked();

        if (!hobiUtamaTerisi && otherHobi.isEmpty()) {
            tilOtherHobi.setError("Pilih minimal satu hobi atau tulis di sini");
            isValid = false;
        } else tilOtherHobi.setError(null);

        if (isValid) {
            showConfirmDialog();
        }
    }

    private void showConfirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Konfirmasi Registrasi");
        builder.setMessage("Apakah Ente yakin data yang dimasukkan sudah benar?");

        builder.setPositiveButton("Gass, Kirim", (dialog, which) ->
                Toast.makeText(MainActivity.this, "Data Berhasil Dikirim!", Toast.LENGTH_LONG).show());

        builder.setNegativeButton("Cek Lagi", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }

    //Fungsi Tambahan untuk Long Press
    private void resetForm() {
        etName.setText("");
        etEmail.setText("");
        etPassword.setText("");
        etConfirm.setText("");
        etOtherHobi.setText("");
        rgGender.clearCheck();
        cbCoding.setChecked(false);
        cbOtomotif.setChecked(false);
        cbStreaming.setChecked(false);
        cbOlahraga.setChecked(false);
        cbReading.setChecked(false);
        cbTraveling.setChecked(false);
        tilName.setError(null);
        tilEmail.setError(null);
        tilPassword.setError(null);
        tilConfirm.setError(null);
        tilOtherHobi.setError(null);

        Toast.makeText(this, "Form telah di-reset!", Toast.LENGTH_SHORT).show();
    }
}