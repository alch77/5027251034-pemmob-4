# Aplikasi Biodata Mahasiswa (Tugas Sederhana)

Aplikasi Android sederhana berbasis Java untuk menginput dan menampilkan biodata mahasiswa menggunakan konsep **Explicit Intent** untuk perpindahan data antar Activity, serta mendemonstrasikan siklus hidup (*lifecycle*) Android dengan metode **`finish()`** dan **`onDestroy()`**.

---

## 🌟 Fitur Utama

1. **Form Input Biodata Lengkap:**
   - **NRP / NIM:** Input nomor identitas mahasiswa.
   - **Nama Lengkap:** Input nama mahasiswa dengan format kapitalisasi otomatis.
   - **Jurusan / Program Studi:** Input program studi mahasiswa.
   - **Email:** Input alamat email dengan tipe keyboard email.
   - **Nomor WhatsApp / HP:** Input nomor telepon dengan tipe keyboard numerik.
   - **Jenis Kelamin:** Pilihan radio button (Laki-laki / Perempuan).

2. **Pengiriman Data Menggunakan Explicit Intent:**
   - Berpindah dari `MainActivity` ke `SecondActivity` secara eksplisit (`new Intent(MainActivity.this, SecondActivity.class)`).
   - Mengirim seluruh data melalui `putExtra()` dan diterima kembali di `SecondActivity` melalui `getStringExtra()`.

3. **Tampilan Kartu Informasi (Material Card):**
   - Halaman kedua (`SecondActivity`) menampilkan data yang diterima dalam bentuk kartu (*card*) berdesain rapi dan terstruktur.

4. **Validasi Form:**
   - Validasi interaktif yang menampilkan pesan error dan fokus langsung ke kolom yang belum diisi (Nama, NRP, dan Jurusan wajib diisi).

5. **Kenyamanan Input (High Contrast UI):**
   - Teks ketikan, garis outline saat kolom aktif/fokus (*focused state*), serta floating hint dirancang berwarna hitam pekat (`#000000`) agar jelas dan mudah dibaca.

6. **Tombol "Tutup" dengan Siklus Hidup `finish()` & `onDestroy()`:**
   - Menyediakan tombol **"Tutup"** pada `SecondActivity` yang memanggil fungsi `finish()` untuk mengakhiri activity dan kembali ke halaman utama.
   - Menimpa (*override*) method `onDestroy()` untuk menampilkan indikator Toast saat Activity dihancurkan dari memori.
   - Tersedia juga tombol **"Tutup Aplikasi"** pada `MainActivity` dengan mekanisme yang sama untuk menutup aplikasi.

---

## 🛠 Spesifikasi & Teknologi

| Komponen | Spesifikasi / Versi |
| :--- | :--- |
| **Platform** | Android |
| **Bahasa Pemrograman** | Java (Java 11) |
| **Min SDK** | API 24 (Android 7.0 Nougat) |
| **Target & Compile SDK** | API 37 |
| **Build System** | Gradle 9.5.0 |
| **Android Gradle Plugin (AGP)** | 9.3.2 |
| **UI Components** | Google Material Design 3 (`com.google.android.material:material:1.14.0`) |
| **Library Pendukung** | AndroidX AppCompat (`1.8.0`), ConstraintLayout (`2.2.2`), Activity KTX (`1.13.0`) |
| **Unit Testing** | JUnit 4 (`4.13.2`) |

---

## 📂 Struktur Proyek

```text
TugasSederhana/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/tugassederhana/
│   │   │   │   ├── MainActivity.java        # Activity utama: form input & Explicit Intent
│   │   │   │   └── SecondActivity.java      # Activity kedua: penampil data & tombol Tutup
│   │   │   ├── res/
│   │   │   │   ├── color/
│   │   │   │   │   ├── box_stroke_color.xml # Selector warna border saat fokus (hitam)
│   │   │   │   │   └── box_hint_color.xml   # Selector warna hint saat fokus (hitam)
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml    # Layout form input biodata
│   │   │   │   │   └── activity_second.xml  # Layout tampilan biodata & tombol Tutup
│   │   │   │   └── values/
│   │   │   │       ├── colors.xml           # Definisi palet warna
│   │   │   │       ├── strings.xml          # Definisi teks & label
│   │   │   │       └── themes.xml           # Tema Material3 aplikasi
│   │   │   └── AndroidManifest.xml          # Pendaftaran MainActivity & SecondActivity
│   │   └── test/
│   │       └── java/com/example/tugassederhana/
│   │           └── ExampleUnitTest.java     # Pengujian unit (Unit Test)
│   └── build.gradle.kts                     # Konfigurasi dependensi modul app
├── gradle/
│   └── libs.versions.toml                   # Version catalog Gradle
├── build.gradle.kts                         # Konfigurasi root project
├── gradlew.bat                              # Script Gradle wrapper (Windows)
└── README.md                                # Dokumentasi proyek
```

---

## 🚀 Cara Menjalankan Aplikasi

### Opsi 1: Menggunakan Android Studio (Direkomendasikan)
1. Buka aplikasi **Android Studio**.
2. Pilih menu **File** > **Open**, lalu arahkan ke direktori proyek:
   ```text
   d:\TugasSederhana
   ```
3. Tunggu proses *Gradle Sync* selesai.
4. Hubungkan perangkat fisik Android melalui USB (aktifkan *USB Debugging*) atau jalankan **Android Emulator (AVD)**.
5. Klik tombol **Run 'app'** (ikon segitiga hijau `▶`) atau tekan pintasan `Shift + F10`.
6. Aplikasi akan terpasang dan terbuka di perangkat Anda.

### Opsi 2: Menggunakan Terminal / Command Line
Anda dapat mengompilasi dan menghasilkan file APK menggunakan Gradle Wrapper:

```powershell
# Pastikan JAVA_HOME telah terpasang (misal menggunakan JDK bawaan Android Studio)
$env:JAVA_HOME="C:\Program Files\Android\Android Studio\jbr"

# Build file APK Debug
.\gradlew.bat assembleDebug
```

Setelah proses selesai, file APK siap diinstal dan berlokasi di:
```text
app/build/outputs/apk/debug/app-debug.apk
```

Untuk langsung memasang (*install*) ke perangkat/emulator yang sedang terhubung:
```powershell
.\gradlew.bat installDebug
```

---

## 🧪 Pengujian Unit (Unit Test)

Proyek ini dilengkapi dengan unit test berbasis **JUnit 4** di file [`ExampleUnitTest.java`](app/src/test/java/com/example/tugassederhana/ExampleUnitTest.java) untuk memvalidasi logika aplikasi tanpa memerlukan perangkat emulator.

### Skenario Pengujian yang Diuji:
- `testValidasiNama_TidakBolehKosong`: Memastikan input nama yang kosong terdeteksi dan input yang valid diterima.
- `testValidasiNRP_HarusSesuai`: Memastikan input NRP memiliki nilai dan panjang yang sesuai.
- `testValidasiEmail_Format`: Menguji pola ekspresi reguler (*regex*) format alamat email.
- `testNilaiDefault_FieldKosong`: Memastikan nilai default `"-"` terpasang saat field opsional kosong.

### Cara Menjalankan Unit Test:

#### Melalui Terminal:
Jalankan perintah berikut di folder proyek:
```powershell
$env:JAVA_HOME="C:\Program Files\Android\Android Studio\jbr"
.\gradlew.bat testDebugUnitTest
```

Laporan hasil pengujian dalam format HTML interaktif dapat dilihat di:
```text
app/build/reports/tests/testDebugUnitTest/index.html
```

#### Melalui Android Studio:
1. Di panel *Project*, buka folder `app` > `src` > `test` > `java` > `com.example.tugassederhana`.
2. Klik kanan pada file `ExampleUnitTest.java`.
3. Pilih menu **Run 'ExampleUnitTest'**.
4. Hasil pengujian (*Passed / Failed*) akan muncul di panel *Run* di bagian bawah.

