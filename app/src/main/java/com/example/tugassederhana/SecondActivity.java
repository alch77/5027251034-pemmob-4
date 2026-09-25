package com.example.tugassederhana;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    EditText edtNrp, edtNama;
    Button btnSimpan, btnCari, btnUpdate, btnHapus, btnTutup;
    DatabaseHelper dbHelper;
    SharedPreferences sharedPreferences;

    private static final String PREF_NAME = "MhsPrefs";
    private static final String KEY_LAST_NRP = "last_searched_nrp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Inisialisasi DatabaseHelper dan SharedPreferences
        dbHelper = new DatabaseHelper(this);
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // Inisialisasi View
        edtNrp = findViewById(R.id.edtNrp);
        edtNama = findViewById(R.id.edtNama);
        btnSimpan = findViewById(R.id.btnSimpan);
        btnCari = findViewById(R.id.btnCari);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnHapus = findViewById(R.id.btnHapus);
        btnTutup = findViewById(R.id.btnTutup);

        // Memuat NRP terakhir yang dicari secara otomatis dari SharedPreferences ke EditText nrp saat onCreate()
        String lastNrp = sharedPreferences.getString(KEY_LAST_NRP, "");
        if (!lastNrp.isEmpty()) {
            edtNrp.setText(lastNrp);
            // Cari otomatis data jika NRP terakhir ada
            loadDataByNrp(lastNrp, false);
        }

        // Operasi 1: SIMPAN (Insert)
        btnSimpan.setOnClickListener(v -> {
            String nrp = edtNrp.getText().toString().trim();
            String nama = edtNama.getText().toString().trim();

            if (nrp.isEmpty()) {
                edtNrp.setError("NRP tidak boleh kosong");
                edtNrp.requestFocus();
                return;
            }
            if (nama.isEmpty()) {
                edtNama.setError("Nama tidak boleh kosong");
                edtNama.requestFocus();
                return;
            }

            boolean isInserted = dbHelper.insertData(nrp, nama);
            if (isInserted) {
                Toast.makeText(this, "Data mahasiswa berhasil disimpan", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Gagal menyimpan: NRP sudah terdaftar atau terjadi kesalahan", Toast.LENGTH_SHORT).show();
            }
        });

        // Operasi 2: CARI (Query) & Simpan ke SharedPreferences
        btnCari.setOnClickListener(v -> {
            String nrp = edtNrp.getText().toString().trim();
            if (nrp.isEmpty()) {
                edtNrp.setError("Masukkan NRP yang ingin dicari");
                edtNrp.requestFocus();
                Toast.makeText(this, "NRP tidak boleh kosong untuk pencarian", Toast.LENGTH_SHORT).show();
                return;
            }

            // Simpan NRP terakhir yang dicari ke SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_LAST_NRP, nrp);
            editor.apply();

            loadDataByNrp(nrp, true);
        });

        // Operasi 3: UPDATE (Update)
        btnUpdate.setOnClickListener(v -> {
            String nrp = edtNrp.getText().toString().trim();
            String nama = edtNama.getText().toString().trim();

            if (nrp.isEmpty()) {
                edtNrp.setError("NRP wajib diisi untuk update");
                edtNrp.requestFocus();
                return;
            }
            if (nama.isEmpty()) {
                edtNama.setError("Nama baru wajib diisi");
                edtNama.requestFocus();
                return;
            }

            boolean isUpdated = dbHelper.updateData(nrp, nama);
            if (isUpdated) {
                Toast.makeText(this, "Data mahasiswa berhasil diupdate", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Gagal update: NRP tidak ditemukan di database", Toast.LENGTH_SHORT).show();
            }
        });

        // Operasi 4: HAPUS (Delete)
        btnHapus.setOnClickListener(v -> {
            String nrp = edtNrp.getText().toString().trim();

            if (nrp.isEmpty()) {
                edtNrp.setError("Masukkan NRP yang ingin dihapus");
                edtNrp.requestFocus();
                Toast.makeText(this, "NRP tidak boleh kosong untuk penghapusan", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean isDeleted = dbHelper.deleteData(nrp);
            if (isDeleted) {
                Toast.makeText(this, "Data mahasiswa berhasil dihapus", Toast.LENGTH_SHORT).show();
                edtNrp.setText("");
                edtNama.setText("");
                // Hapus juga dari SharedPreferences jika perlu atau biarkan
                sharedPreferences.edit().remove(KEY_LAST_NRP).apply();
            } else {
                Toast.makeText(this, "Gagal menghapus: NRP tidak ditemukan", Toast.LENGTH_SHORT).show();
            }
        });

        // Tombol Tutup / Kembali
        btnTutup.setOnClickListener(v -> finish());
    }

    private void loadDataByNrp(String nrp, boolean showToast) {
        Cursor cursor = dbHelper.getData(nrp);
        if (cursor != null && cursor.moveToFirst()) {
            int namaIndex = cursor.getColumnIndex(DatabaseHelper.COL_NAMA);
            String nama = namaIndex != -1 ? cursor.getString(namaIndex) : "";
            edtNama.setText(nama);
            cursor.close();
            if (showToast) {
                Toast.makeText(this, "Data ditemukan: " + nama, Toast.LENGTH_SHORT).show();
            }
        } else {
            if (cursor != null) {
                cursor.close();
            }
            if (showToast) {
                Toast.makeText(this, "Data tidak ditemukan untuk NRP: " + nrp, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "SecondActivity ditutup", Toast.LENGTH_SHORT).show();
    }
}
