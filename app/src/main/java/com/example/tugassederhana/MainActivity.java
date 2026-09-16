package com.example.tugassederhana;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // java utama
    EditText edtNama, edtNrp, edtJurusan, edtEmail, edtNoHp;
    RadioGroup rgJenisKelamin;
    RadioButton rbLaki, rbPerempuan;
    Button btnPindah, btnTutup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNama = findViewById(R.id.edtNama);
        edtNrp = findViewById(R.id.edtNrp);
        edtJurusan = findViewById(R.id.edtJurusan);
        edtEmail = findViewById(R.id.edtEmail);
        edtNoHp = findViewById(R.id.edtNoHp);
        rgJenisKelamin = findViewById(R.id.rgJenisKelamin);
        rbLaki = findViewById(R.id.rbLaki);
        rbPerempuan = findViewById(R.id.rbPerempuan);
        btnPindah = findViewById(R.id.btnPindah);
        btnTutup = findViewById(R.id.btnTutup);

        // Tombol Kirim Data menggunakan Explicit Intent
        btnPindah.setOnClickListener(v -> {
            String nama = edtNama.getText().toString().trim();
            String nrp = edtNrp.getText().toString().trim();
            String jurusan = edtJurusan.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String noHp = edtNoHp.getText().toString().trim();

            if (nama.isEmpty()) {
                edtNama.setError("Nama tidak boleh kosong");
                edtNama.requestFocus();
                return;
            }

            if (nrp.isEmpty()) {
                edtNrp.setError("NRP tidak boleh kosong");
                edtNrp.requestFocus();
                return;
            }

            if (jurusan.isEmpty()) {
                edtJurusan.setError("Jurusan tidak boleh kosong");
                edtJurusan.requestFocus();
                return;
            }

            String jenisKelamin = rbLaki.isChecked() ? "Laki-laki" : "Perempuan";

            // Penerapan Explicit Intent: secara eksplisit menunjuk SecondActivity.class
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);

            // Mengirimkan data NRP, Nama, jurusan, email, nomor hp, dan jenis kelamin
            intent.putExtra("nrp", nrp);
            intent.putExtra("nama", nama);
            intent.putExtra("jurusan", jurusan);
            intent.putExtra("email", email.isEmpty() ? "-" : email);
            intent.putExtra("nohp", noHp.isEmpty() ? "-" : noHp);
            intent.putExtra("jenisKelamin", jenisKelamin);

            startActivity(intent);
        });

        // Penerapan tombol "Tutup" dengan pemanggilan finish()
        btnTutup.setOnClickListener(v -> {
            finish(); // Mengakhiri activity utama / menutup aplikasi
        });
    }

    // Penerapan method onDestroy() ketika Activity dihancurkan
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Aplikasi ditutup (onDestroy MainActivity dipanggil)", Toast.LENGTH_SHORT).show();
    }
}
