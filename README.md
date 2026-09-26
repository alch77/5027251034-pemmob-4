<<<<<<< HEAD
# Aplikasi Manajemen Data Mahasiswa (DBSederhana)

Aplikasi Android berbasis Java untuk melakukan pengelolaan data mahasiswa menggunakan basis data lokal **SQLite** (`SQLiteOpenHelper`) dan persistensi data sementara menggunakan **SharedPreferences**. Aplikasi ini mendukung operasi **CRUD (Create, Read, Update, Delete)** lengkap dengan antarmuka modern Material Design 3.

---

## 👤 Identitas Mahasiswa
- **Nama:** Albert Chen
- **NRP:** 5027251034
- **Mata Kuliah:** Pemrograman Mobile
- **Repository:** [5027251034-pemmob-4](https://github.com/alch77/5027251034-pemmob-4)

---

## 🌟 Fitur Utama

1. **Tambah / Simpan Data (Create):**
   - Menginput NRP dan Nama mahasiswa, kemudian menyimpannya ke dalam database SQLite (`mhs.db` pada tabel `mhs`).
   - Validasi duplikasi: Mencegah penyimpanan jika NRP yang sama sudah terdaftar (NRP sebagai Primary Key).

2. **Cari Data Mahasiswa (Read):**
   - Mencari data nama mahasiswa berdasarkan NRP yang dimasukkan.
   - Menggunakan kueri `rawQuery` dengan parameterisasi untuk keamanan data.

3. **Penyimpanan Riwayat Otomatis (SharedPreferences):**
   - NRP pencarian terakhir secara otomatis disimpan ke dalam `SharedPreferences` (`MhsPrefs`).
   - Saat aplikasi pertama kali dibuka (`onCreate`), NRP terakhir akan langsung dimuat ke kolom input dan datanya otomatis dicari serta ditampilkan.

4. **Perbarui Data (Update):**
   - Mengubah nama mahasiswa berdasarkan NRP yang tersimpan di dalam database menggunakan metode `update()`.

5. **Hapus Data (Delete):**
   - Menghapus data mahasiswa dari database SQLite berdasarkan NRP.
   - Sekaligus membersihkan cache riwayat pencarian pada `SharedPreferences`.

6. **Validasi Input Interaktif:**
   - Kolom NRP dan Nama dilengkapi validasi: menampilkan pesan error visual (`setError`) dan fokus kursor jika ada kolom wajib yang kosong.
   - Notifikasi interaktif menggunakan `Toast` untuk setiap status operasi (berhasil/gagal).

7. **Tombol "Tutup" & Manajemen Siklus Hidup (*Lifecycle*):**
   - Menyediakan tombol **"Tutup"** dengan pemanggilan metode `finish()`.
   - Menimpa (*override*) callback `onDestroy()` untuk menampilkan notifikasi penutupan activity secara bersih.

---

## 🛠 Spesifikasi & Teknologi

| Komponen | Spesifikasi / Keterangan |
| :--- | :--- |
| **Platform** | Android |
| **Bahasa Pemrograman** | Java (Java 11 / `VERSION_11`) |
| **Min SDK** | API 24 (Android 7.0 Nougat) |
| **Target & Compile SDK** | API 37 |
| **Database Lokal** | SQLite (melalui `SQLiteOpenHelper`) |
| **Penyimpanan Key-Value** | Android `SharedPreferences` |
| **Build System** | Gradle 9.5.0 |
| **Android Gradle Plugin (AGP)** | 9.3.2 |
| **Komponen UI** | Google Material Design 3 (`com.google.android.material:material:1.14.0`) |
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
│   │   │   │   ├── DatabaseHelper.java      # Pengelola database SQLite (Tabel mhs, CRUD)
│   │   │   │   └── SecondActivity.java      # Activity utama: UI CRUD, SharedPreferences, Lifecycle
│   │   │   ├── res/
│   │   │   │   ├── color/
│   │   │   │   │   ├── box_stroke_color.xml # Selector warna outline form fokus (hitam)
│   │   │   │   │   └── box_hint_color.xml   # Selector warna hint form fokus (hitam)
│   │   │   │   ├── layout/
│   │   │   │   │   └── activity_second.xml  # Antarmuka form input, tombol CRUD, & tombol Tutup
│   │   │   │   └── values/
│   │   │   │       ├── colors.xml           # Definisi palet warna Material
│   │   │   │       ├── strings.xml          # Definisi string teks dan label
│   │   │   │       └── themes.xml           # Tema Material 3 aplikasi
│   │   │   └── AndroidManifest.xml          # Konfigurasi manifest & registrasi SecondActivity
│   │   └── test/
│   │       └── java/com/example/tugassederhana/
│   │           └── ExampleUnitTest.java     # Pengujian unit lokal (Unit Test)
│   └── build.gradle.kts                     # Dependensi modul aplikasi
├── gradle/
│   └── libs.versions.toml                   # Version catalog dependencies
├── build.gradle.kts                         # Konfigurasi root project Gradle
├── gradlew.bat                              # Script Gradle Wrapper (Windows)
└── README.md                                # Dokumentasi lengkap proyek
```

---

## 🗄 Skema Database SQLite

Basis data diberi nama **`mhs.db`** dengan tabel utama **`mhs`**:

```sql
CREATE TABLE mhs (
    nrp TEXT PRIMARY KEY,
    nama TEXT
);
```

### Operasi Database (`DatabaseHelper.java`):
- `insertData(String nrp, String nama)`: Menambahkan data mahasiswa baru.
- `getData(String nrp)`: Mengambil data mahasiswa berdasarkan NRP menggunakan kueri `SELECT * FROM mhs WHERE nrp = ?`.
- `updateData(String nrp, String nama)`: Memperbarui kolom nama mahasiswa berdasarkan NRP.
- `deleteData(String nrp)`: Menghapus baris data mahasiswa berdasarkan NRP.

---

## 🚀 Cara Menjalankan Aplikasi

### Opsi 1: Menggunakan Android Studio (Direkomendasikan)
1. Buka aplikasi **Android Studio**.
2. Pilih menu **File** > **Open**, lalu pilih direktori proyek:
   ```text
   d:\TugasSederhana
   ```
3. Tunggu hingga proses *Gradle Sync* dan indeks selesai.
4. Hubungkan perangkat fisik Android melalui kabel USB (pastikan *USB Debugging* aktif) atau jalankan **Android Emulator (AVD)**.
5. Klik tombol **Run 'app'** (ikon segitiga hijau `▶`) atau gunakan pintasan `Shift + F10`.
6. Aplikasi akan terkompilasi, terpasang, dan langsung terbuka di layar perangkat.

### Opsi 2: Menggunakan Terminal / Command Line
Anda dapat mengompilasi dan membangun file APK langsung melalui Gradle Wrapper:

```powershell
# Atur JAVA_HOME ke JDK Android Studio
$env:JAVA_HOME="C:\Program Files\Android\Android Studio\jbr"

# Build file APK Debug
.\gradlew.bat assembleDebug
```

File APK yang siap diinstal akan terbentuk pada direktori:
```text
app/build/outputs/apk/debug/app-debug.apk
```

Untuk langsung memasang (*install*) APK ke perangkat/emulator yang terhubung:
```powershell
.\gradlew.bat installDebug
```

---

## 🧪 Pengujian Unit (Unit Test)

Pengujian unit dilakukan secara lokal menggunakan **JUnit 4** pada file [`ExampleUnitTest.java`](app/src/test/java/com/example/tugassederhana/ExampleUnitTest.java) tanpa memerlukan emulator/device fisik.

### Skenario Pengujian:
1. `addition_isCorrect`: Memverifikasi lingkungan eksekusi pengujian dasar.
2. `testDatabaseConstants`: Memvalidasi nama tabel (`mhs`), kolom NRP (`nrp`), dan kolom Nama (`nama`) pada `DatabaseHelper`.
3. `testValidasiNRP_TidakBolehKosong`: Memverifikasi validasi input NRP terhadap kondisi string kosong dan panjang karakter.
4. `testValidasiNama_TidakBolehKosong`: Memverifikasi validasi nama tidak boleh hanya berisi spasi kosong (*whitespace*).
5. `testKueriSqliteQueryBuilder`: Memverifikasi sintaks kueri SQL pencarian data mahasiswa.

### Cara Menjalankan Unit Test:

#### Melalui Terminal:
```powershell
$env:JAVA_HOME="C:\Program Files\Android\Android Studio\jbr"
.\gradlew.bat testDebugUnitTest
```

Laporan hasil pengujian dalam format visual HTML interaktif dapat diakses pada:
```text
app/build/reports/tests/testDebugUnitTest/index.html
```

#### Melalui Android Studio:
1. Di panel navigasi *Project*, buka folder `app` > `src` > `test` > `java` > `com.example.tugassederhana`.
2. Klik kanan pada file `ExampleUnitTest.java`.
3. Pilih opsi **Run 'ExampleUnitTest'**.
4. Hasil status pengujian (semua centang hijau *Passed*) akan ditampilkan pada panel *Run* di bagian bawah.

=======
# Aplikasi Manajemen Data Mahasiswa (DBSederhana)

Aplikasi Android berbasis Java untuk melakukan pengelolaan data mahasiswa menggunakan basis data lokal **SQLite** (`SQLiteOpenHelper`) dan persistensi data sementara menggunakan **SharedPreferences**. Aplikasi ini mendukung operasi **CRUD (Create, Read, Update, Delete)** lengkap dengan antarmuka modern Material Design 3.

---

## 👤 Identitas Mahasiswa
- **Nama:** Albert Chen
- **NRP:** 5027251034
- **Mata Kuliah:** Pemrograman Mobile
- **Repository:** [5027251034-pemmob-4](https://github.com/alch77/5027251034-pemmob-4)

---

## 🌟 Fitur Utama

1. **Tambah / Simpan Data (Create):**
   - Menginput NRP dan Nama mahasiswa, kemudian menyimpannya ke dalam database SQLite (`mhs.db` pada tabel `mhs`).
   - Validasi duplikasi: Mencegah penyimpanan jika NRP yang sama sudah terdaftar (NRP sebagai Primary Key).

2. **Cari Data Mahasiswa (Read):**
   - Mencari data nama mahasiswa berdasarkan NRP yang dimasukkan.
   - Menggunakan kueri `rawQuery` dengan parameterisasi untuk keamanan data.

3. **Penyimpanan Riwayat Otomatis (SharedPreferences):**
   - NRP pencarian terakhir secara otomatis disimpan ke dalam `SharedPreferences` (`MhsPrefs`).
   - Saat aplikasi pertama kali dibuka (`onCreate`), NRP terakhir akan langsung dimuat ke kolom input dan datanya otomatis dicari serta ditampilkan.

4. **Perbarui Data (Update):**
   - Mengubah nama mahasiswa berdasarkan NRP yang tersimpan di dalam database menggunakan metode `update()`.

5. **Hapus Data (Delete):**
   - Menghapus data mahasiswa dari database SQLite berdasarkan NRP.
   - Sekaligus membersihkan cache riwayat pencarian pada `SharedPreferences`.

6. **Validasi Input Interaktif:**
   - Kolom NRP dan Nama dilengkapi validasi: menampilkan pesan error visual (`setError`) dan fokus kursor jika ada kolom wajib yang kosong.
   - Notifikasi interaktif menggunakan `Toast` untuk setiap status operasi (berhasil/gagal).

7. **Tombol "Tutup" & Manajemen Siklus Hidup (*Lifecycle*):**
   - Menyediakan tombol **"Tutup"** dengan pemanggilan metode `finish()`.
   - Menimpa (*override*) callback `onDestroy()` untuk menampilkan notifikasi penutupan activity secara bersih.

---

## 🛠 Spesifikasi & Teknologi

| Komponen | Spesifikasi / Keterangan |
| :--- | :--- |
| **Platform** | Android |
| **Bahasa Pemrograman** | Java (Java 11 / `VERSION_11`) |
| **Min SDK** | API 24 (Android 7.0 Nougat) |
| **Target & Compile SDK** | API 37 |
| **Database Lokal** | SQLite (melalui `SQLiteOpenHelper`) |
| **Penyimpanan Key-Value** | Android `SharedPreferences` |
| **Build System** | Gradle 9.5.0 |
| **Android Gradle Plugin (AGP)** | 9.3.2 |
| **Komponen UI** | Google Material Design 3 (`com.google.android.material:material:1.14.0`) |
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
│   │   │   │   ├── DatabaseHelper.java      # Pengelola database SQLite (Tabel mhs, CRUD)
│   │   │   │   └── SecondActivity.java      # Activity utama: UI CRUD, SharedPreferences, Lifecycle
│   │   │   ├── res/
│   │   │   │   ├── color/
│   │   │   │   │   ├── box_stroke_color.xml # Selector warna outline form fokus (hitam)
│   │   │   │   │   └── box_hint_color.xml   # Selector warna hint form fokus (hitam)
│   │   │   │   ├── layout/
│   │   │   │   │   └── activity_second.xml  # Antarmuka form input, tombol CRUD, & tombol Tutup
│   │   │   │   └── values/
│   │   │   │       ├── colors.xml           # Definisi palet warna Material
│   │   │   │       ├── strings.xml          # Definisi string teks dan label
│   │   │   │       └── themes.xml           # Tema Material 3 aplikasi
│   │   │   └── AndroidManifest.xml          # Konfigurasi manifest & registrasi SecondActivity
│   │   └── test/
│   │       └── java/com/example/tugassederhana/
│   │           └── ExampleUnitTest.java     # Pengujian unit lokal (Unit Test)
│   └── build.gradle.kts                     # Dependensi modul aplikasi
├── gradle/
│   └── libs.versions.toml                   # Version catalog dependencies
├── build.gradle.kts                         # Konfigurasi root project Gradle
├── gradlew.bat                              # Script Gradle Wrapper (Windows)
└── README.md                                # Dokumentasi lengkap proyek
```

---

## 🗄 Skema Database SQLite

Basis data diberi nama **`mhs.db`** dengan tabel utama **`mhs`**:

```sql
CREATE TABLE mhs (
    nrp TEXT PRIMARY KEY,
    nama TEXT
);
```

### Operasi Database (`DatabaseHelper.java`):
- `insertData(String nrp, String nama)`: Menambahkan data mahasiswa baru.
- `getData(String nrp)`: Mengambil data mahasiswa berdasarkan NRP menggunakan kueri `SELECT * FROM mhs WHERE nrp = ?`.
- `updateData(String nrp, String nama)`: Memperbarui kolom nama mahasiswa berdasarkan NRP.
- `deleteData(String nrp)`: Menghapus baris data mahasiswa berdasarkan NRP.

---

## 🚀 Cara Menjalankan Aplikasi

### Opsi 1: Menggunakan Android Studio (Direkomendasikan)
1. Buka aplikasi **Android Studio**.
2. Pilih menu **File** > **Open**, lalu pilih direktori proyek:
   ```text
   d:\TugasSederhana
   ```
3. Tunggu hingga proses *Gradle Sync* dan indeks selesai.
4. Hubungkan perangkat fisik Android melalui kabel USB (pastikan *USB Debugging* aktif) atau jalankan **Android Emulator (AVD)**.
5. Klik tombol **Run 'app'** (ikon segitiga hijau `▶`) atau gunakan pintasan `Shift + F10`.
6. Aplikasi akan terkompilasi, terpasang, dan langsung terbuka di layar perangkat.

### Opsi 2: Menggunakan Terminal / Command Line
Anda dapat mengompilasi dan membangun file APK langsung melalui Gradle Wrapper:

```powershell
# Atur JAVA_HOME ke JDK Android Studio
$env:JAVA_HOME="C:\Program Files\Android\Android Studio\jbr"

# Build file APK Debug
.\gradlew.bat assembleDebug
```

File APK yang siap diinstal akan terbentuk pada direktori:
```text
app/build/outputs/apk/debug/app-debug.apk
```

Untuk langsung memasang (*install*) APK ke perangkat/emulator yang terhubung:
```powershell
.\gradlew.bat installDebug
```

---

## 🧪 Pengujian Unit (Unit Test)

Pengujian unit dilakukan secara lokal menggunakan **JUnit 4** pada file [`ExampleUnitTest.java`](app/src/test/java/com/example/tugassederhana/ExampleUnitTest.java) tanpa memerlukan emulator/device fisik.

### Skenario Pengujian:
1. `addition_isCorrect`: Memverifikasi lingkungan eksekusi pengujian dasar.
2. `testDatabaseConstants`: Memvalidasi nama tabel (`mhs`), kolom NRP (`nrp`), dan kolom Nama (`nama`) pada `DatabaseHelper`.
3. `testValidasiNRP_TidakBolehKosong`: Memverifikasi validasi input NRP terhadap kondisi string kosong dan panjang karakter.
4. `testValidasiNama_TidakBolehKosong`: Memverifikasi validasi nama tidak boleh hanya berisi spasi kosong (*whitespace*).
5. `testKueriSqliteQueryBuilder`: Memverifikasi sintaks kueri SQL pencarian data mahasiswa.

### Cara Menjalankan Unit Test:

#### Melalui Terminal:
```powershell
$env:JAVA_HOME="C:\Program Files\Android\Android Studio\jbr"
.\gradlew.bat testDebugUnitTest
```

Laporan hasil pengujian dalam format visual HTML interaktif dapat diakses pada:
```text
app/build/reports/tests/testDebugUnitTest/index.html
```

#### Melalui Android Studio:
1. Di panel navigasi *Project*, buka folder `app` > `src` > `test` > `java` > `com.example.tugassederhana`.
2. Klik kanan pada file `ExampleUnitTest.java`.
3. Pilih opsi **Run 'ExampleUnitTest'**.
4. Hasil status pengujian (semua centang hijau *Passed*) akan ditampilkan pada panel *Run* di bagian bawah.

>>>>>>> 4e17447 (tugas 4)
