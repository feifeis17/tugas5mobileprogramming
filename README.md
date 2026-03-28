Tugas 5 - Pemrograman Mobile
Repositori ini berisi proyek Android Studio untuk tugas pertemuan 5(e-learning), yang mengimplementasikan form registrasi lengkap dengan validasi dan interaksi pengguna.

##Fitur Utama
Sesuai dengan instruksi tugas, aplikasi ini mencakup:
1.Complete Form: Input Nama, Email, Password, dan Konfirmasi Password menggunakan TextInputLayout.
2.Advanced Validation: 
   - Validasi kolom tidak boleh kosong.
   - Validasi format email yang benar.
   - Validasi *real-time* untuk kecocokan password.
3.Selection Controls: Pemilihan jenis kelamin (RadioGroup) dengan ikon dan hobi (Checkbox) dalam format grid.
4.Dialog Confirmation: Munculnya AlertDialog konfirmasi saat menekan tombol kirim.
5.Gesture Interaction: Fitur **Long Press** pada tombol untuk melakukan *reset* seluruh isi form.

##Tech Stack
- Language: Java
- UI Framework: XML

##Cara Menjalankan Program
Untuk menjalankan proyek ini di perangkat lokal Anda, ikuti langkah-langkah berikut:
1. Clone Repositori:
   Clone repositori ini menggunakan Git atau unduh sebagai file ZIP.
   ```bash
   git clone [https://github.com/feifeis17/tugas5mobileprogramming.git](https://github.com/feifeis17/tugas5mobileprogramming.git)
2. Buka di Android Studio:
Jalankan Android Studio.
Pilih File > Open atau Import Project.
Arahkan ke folder hasil clone/ekstrak tadi.
3. Sinkronisasi Gradle:
Tunggu hingga Android Studio selesai melakukan Gradle Sync. Pastikan Anda memiliki koneksi internet yang stabil.
4. Jalankan Aplikasi:
Hubungkan HP Android fisik (dengan USB Debugging aktif) atau gunakan Emulator (AVD).
Klik tombol Run (ikon segitiga hijau) di toolbar atas.
Aplikasi akan terinstal dan siap digunakan!

##DEMO
Berikut adalah simulasi alur aplikasi (Login -> Register -> Loading State):


https://github.com/user-attachments/assets/d6d8b395-a726-4f2b-96e8-7cbe0461197c



##Author
- Nama: Feisal Ramdhani Riyadi
- NIM: 24552011317
- Mata Kuliah: Pemrograman Mobile 1 TIF RP 24D CNS
