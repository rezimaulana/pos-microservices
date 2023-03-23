<a name="readme-top"></a>

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

<br />
<div align="center">

<h3 align="center">Point of Sale(Spring Boot Microservices)</h3>

  <p align="center">
    Aplikasi penjualan atau sistem penjualan yang digunakan di toko atau bisnis lainnya untuk memproses transaksi pembelian oleh pelanggan.
    <br />
    <a href="https://github.com/rezimaulana/pos-microservices/issues">Report Bug</a>
    ·
    <a href="https://github.com/rezimaulana/pos-microservices/issues">Request Feature</a>
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Daftar Isi</summary>
  <ol>
    <li><a href="#deskripsi-project">Deskripsi Project</a></li>
    <li>
      <a href="#spesifikasi-aplikasi">Spesifikasi Aplikasi</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation-with-docker">Installation with Docker</a></li>
        <li><a href="#installation-with-local-ide">Installation with Local IDE</a></li>
      </ul>
    </li>
    <li>
      <a href="#usage">Usage</a>
      <ul>
        <li><a href="#api-user">API User</a></li>
        <li><a href="#api-product">API Product</a></li>
        <li><a href="#api-order">API Order</a></li>
      </ul>
    </li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## Deskripsi Project

<p>Terintegrasi untuk mengelola stok, inventaris, dan pembayaran. Sistem POS sangat penting dalam menjalankan bisnis karena membantu menghemat waktu, mengurangi kesalahan manusia, dan meningkatkan efisiensi proses penjualan.</p>

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->
## Spesifikasi Aplikasi

Aplikasi ini develops menggunakan Framework Spring Boot versi 2.7.4. Spring Boot digunakan agar aplikasi ini akan memiliki performa yang optimal dan mudah digunakan.

### Prerequisites

Berikut adalah daftar tools dan software yang diperlukan untuk menjalankan aplikasi.
* Apache Maven 3.8.6
  ```url
  https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.8.6/apache-maven-3.8.6-bin.zip
  ```
* PostgreSQL 11.18
  ```url
  https://www.enterprisedb.com/downloads/postgres-postgresql-downloads
  ```
* Open JDK 11.0.2
  ```url
  https://jdk.java.net/archive/
  ``` 

### Installation with Docker

1. Build container aplikasi
   ```sh
   sudo docker compose up --build
   ```
2. Jalankan aplikasi
   ```sh
   sudo docker compose up
   ```
3. Mematikan aplikasi
   ```sh
   sudo docker compose down
   ```
4. Build spesifik microservices
   ```sh
   sudo docker compose up --build <nama-service>
   ```


### Installation with Local IDE

1. Clone repository
   ```sh
   git clone https://github.com/rezimaulana/pos-microservices.git
   ```
2. Buat database untuk aplikasi
   ```sql
   CREATE DATABASE ms_users;
   ```
   ```sql
   CREATE DATABASE ms_products;
   ```
   ```sql
   CREATE DATABASE ms_orders;
   ```
3. Modifikasi application.properties dan gunakan #Local
4. Buka project dan gunakan maven untuk update project
5. Run App.java melalui IDE dengan file launch.json
6. Table akan terupdate otomatis pada database
7. Data init akan insert otomatis pada tabel
8. Password default semua user adalah "admin"

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->
## Usage

Berikut ini adalah alur POS aplikasi backend dengan user service, order service, dan product service:

1. User Service:<br />
a. User Service berfungsi untuk mengatur manajemen pengguna seperti login, logout, registrasi, dan manajemen role.<br />
b. User Service memiliki tiga entitas yaitu User, Role, dan UserRole.<br />
c. User memiliki atribut seperti id, username, password, name, dan email.<br />
d. Role memiliki atribut seperti id dan name.<br />
e. UserRole memiliki atribut seperti id, userId, dan roleId.<br />
f. User Service terhubung dengan database untuk menyimpan data pengguna dan manajemen role.<br />

2. Order Service:<br />
a. Order Service berfungsi untuk mengatur manajemen pesanan seperti membuat pesanan, mengubah status pesanan, dan melihat riwayat pesanan.<br />
b. Order Service memiliki dua entitas yaitu OrderHeader dan OrderDetail.<br />
c. OrderHeader memiliki atribut seperti id, userId, status, dan totalAmount.<br />
d. OrderDetail memiliki atribut seperti id, orderId, productId, quantity, dan price.<br />
e. Order Service terhubung dengan database untuk menyimpan data pesanan.<br />

3. Product Service:<br />
a. Product Service berfungsi untuk mengatur manajemen produk seperti menambahkan produk, mengubah produk, dan melihat daftar produk.<br />
b. Product Service memiliki satu entitas yaitu Product.<br />
c. Product memiliki atribut seperti id, name, description, price, dan stock.<br />
d. Product Service terhubung dengan database untuk menyimpan data produk.<br />

Alur aplikasi akan terjadi seperti berikut:

1. Pengguna akan login ke aplikasi dengan mengirimkan permintaan login ke User Service.
2. User Service akan memverifikasi kredensial pengguna dan memberikan token akses.
3. Pengguna dapat mengakses layanan Order Service dan Product Service dengan menyertakan token akses dalam permintaan.
4. Pengguna dapat membuat pesanan baru dengan mengirimkan permintaan pembuatan pesanan ke Order Service.
5. Order Service akan membuat entitas OrderHeader baru dan menyimpannya ke database.
6. Pengguna dapat menambahkan produk ke dalam pesanan dengan mengirimkan permintaan penambahan produk ke Order Service.
7. Order Service akan membuat entitas OrderDetail baru dan menyimpannya ke database.
8. Order Service akan mengambil entitas OrderHeader dan OrderDetail dari database dan mengembalikannya ke pengguna.

### API User
API User memungkinkan Anda untuk input data karyawan yang disimpan dalam sistem. Endpoint berikut tersedia:
```
GET /users
```
```
POST /register
```
```
POST /login
```
```
GET /users/data
```
### API Product
API Product memungkinkan Anda untuk melakukan input data dan modifikasi stok produk yang disimpan dalam sistem. Endpoint berikut tersedia:
```
GET /products
```
```
PUT /products
```
```
POST /products
```
```
GET /products/data
```

### API Order
API Order memungkinkan Anda untuk input data transaksi untuk disimpan dalam sistem. Endpoint berikut tersedia:

```
GET /orders
```
```
POST /orders
Menggunakan insertMany untuk Order Detail.
```
```
GET /orders/data
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- LICENSE -->
## License

Didistribusikan di bawah Lisensi GPL-3.0. Lihat `LICENSE.txt` untuk informasi lebih lanjut.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTACT -->
## Contact

Maulana Rezi Rosadi - [@rezi_maulana](https://twitter.com/rezi_maulana) - rsazrm@gmail.com

Project Link: [https://github.com/rezimaulana/pos-microservices](https://github.com/rezimaulana/pos-microservices)

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- MARKDOWN LINKS & IMAGES -->
[contributors-shield]: https://img.shields.io/github/contributors/rezimaulana/pos-microservices.svg?style=for-the-badge
[contributors-url]: https://github.com/rezimaulana/pos-microservices/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/rezimaulana/pos-microservices.svg?style=for-the-badge
[forks-url]: https://github.com/rezimaulana/pos-microservices/network/members
[stars-shield]: https://img.shields.io/github/stars/rezimaulana/pos-microservices.svg?style=for-the-badge
[stars-url]: https://github.com/rezimaulana/pos-microservices/stargazers
[issues-shield]: https://img.shields.io/github/issues/rezimaulana/pos-microservices.svg?style=for-the-badge
[issues-url]: https://github.com/rezimaulana/pos-microservices/issues
[license-shield]: https://img.shields.io/github/license/rezimaulana/pos-microservices.svg?style=for-the-badge
[license-url]: https://github.com/rezimaulana/pos-microservices/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/rezimaulana