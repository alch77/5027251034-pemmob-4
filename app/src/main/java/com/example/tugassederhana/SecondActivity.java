package com.example.tugassederhana;
// java second
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    TextView txtNama, txtNrp, txtJurusan, txtEmail, txtNoHp, txtJenisKelamin;
    Button btnTutup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Inisialisasi View
        txtNama = findViewById(R.id.txtNama);
        txtNrp = findViewById(R.id.txtNrp);
        txtJurusan = findViewById(R.id.txtJurusan);
        txtEmail = findViewById(R.id.txtEmail);
        txtNoHp = findViewById(R.id.txtNoHp);
        txtJenisKelamin = findViewById(R.id.txtJenisKelamin);
        btnTutup = findViewById(R.id.btnTutup);

        // Menerima data yang dikirim melalui Explicit Intent dari MainActivity
        String nrp = getIntent().getStringExtra("nrp");
        String nama = getIntent().getStringExtra("nama");
        String jurusan = getIntent().getStringExtra("jurusan");
        String email = getIntent().getStringExtra("email");
        String noHp = getIntent().getStringExtra("nohp");
        String jenisKelamin = getIntent().getStringExtra("jenisKelamin");

        // Menampilkan data ke masing-masing TextView
        txtNrp.setText(nrp != null && !nrp.isEmpty() ? nrp : "-");
        txtNama.setText(nama != null && !nama.isEmpty() ? nama : "-");
        txtJurusan.setText(jurusan != null && !jurusan.isEmpty() ? jurusan : "-");
        txtEmail.setText(email != null && !email.isEmpty() ? email : "-");
        txtNoHp.setText(noHp != null && !noHp.isEmpty() ? noHp : "-");
        txtJenisKelamin.setText(jenisKelamin != null && !jenisKelamin.isEmpty() ? jenisKelamin : "-");

        // Penerapan tombol "Tutup" dengan memanggil finish()
        btnTutup.setOnClickListener(v -> {
            finish(); // Mengakhiri activity saat ini dan kembali ke activity sebelumnya
        });
    }

    // Penerapan method onDestroy() ketika Activity dihancurkan dari memori
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "SecondActivity ditutup (onDestroy dipanggil)", Toast.LENGTH_SHORT).show();
    }
}
