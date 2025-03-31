# MyCamera

MyCamera adalah aplikasi Android yang memungkinkan pengguna untuk mengambil gambar menggunakan kamera dan menyimpannya ke dalam database lokal. Aplikasi ini juga menyediakan fitur untuk melihat daftar gambar yang telah diambil serta menghapus gambar yang tidak diperlukan.

### Download file apk:
- [Download zip disini](https://drive.google.com/file/d/1ctgMsj6FfU-ROgotSI-WrqrTOCZLMG8-/view?usp=sharing)

### Fitur Utama:
- Ambil Gambar: Menggunakan kamera perangkat untuk mengambil foto.
- Simpan Gambar: Gambar yang diambil akan disimpan dalam database SQLite.
- Tampilkan Daftar Gambar: Gambar yang telah disimpan akan ditampilkan dalam RecyclerView.
- Lihat Detail Gambar: Pengguna dapat melihat gambar dalam tampilan lebih besar.
- Hapus Gambar: Gambar yang tidak diperlukan dapat dihapus dari database.

### Struktur Aplikasi:
- MainActivity: Halaman utama dengan tombol untuk masuk ke HomeActivity.
- HomeActivity: Menampilkan daftar gambar yang telah diambil dan tombol untuk membuka kamera.
- CameraActivity: Menangani pengambilan gambar menggunakan kamera perangkat.
- ImageDetailActivity: Menampilkan gambar secara penuh dan memberikan opsi untuk menghapus gambar.
- DatabaseHelper: Mengelola penyimpanan dan pengambilan data gambar dari database SQLite.
- StoryAdapter: Adapter untuk menampilkan daftar gambar dalam RecyclerView.

### Cara Menggunakan Aplikasi:
- Buka aplikasi MyCamera.
- Tekan tombol kamera untuk mengambil gambar.
- Gambar akan disimpan ke dalam database dan muncul di daftar gambar.
- Klik gambar dalam daftar untuk melihat detailnya.
- Tekan ikon delete untuk menghapus gambar dari database.

### Kontributor
- @ChristBernardS - Christopher Bernard
- @Ricardoaugusto31 - Ricardo Yan Augusto
- @Fiegoldes - Fiegoldes Shindy
