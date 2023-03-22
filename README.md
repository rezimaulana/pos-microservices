<a name="readme-top"></a>

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

<br />
<div align="center">

<h3 align="center">Parcel Handover</h3>

  <p align="center">
    Serah terima barang dari satu driver ke driver lainnya di gudang.
    <br />
    <a href="https://github.com/rezimaulana/parcelhandover/issues">Report Bug</a>
    ·
    <a href="https://github.com/rezimaulana/parcelhandover/issues">Request Feature</a>
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
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li>
      <a href="#usage">Usage</a>
      <ul>
        <li><a href="#api-warehouse">API Warehouse</a></li>
        <li><a href="#api-vehicle">API Vehicle</a></li>
        <li><a href="#api-user">API User</a></li>
        <li><a href="#api-user-vehicle">API User Vehicle</a></li>
        <li><a href="#api-handover">API Handover</a></li>
        <li><a href="#api-report">API Report</a></li>
      </ul>
    </li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## Deskripsi Project

<p>Projek ini memiliki tujuan untuk memantau dan mencatat jumlah barang masuk dan keluar pada gudang logistik.</p>
<p>Sistem ini akan mengumpulkan data yang akan digunakan untuk tiga hal utama:<br> 
1. Melacak kehadiran driver gudang, meliputi waktu kedatangan dan waktu keberangkatan<br>
2. Analisis jumlah paket masuk dan keluar pada gudang<br>
3. Menilai performa sorting berdasarkan tujuan</p>
<p>Informasi ini biasanya dicatat secara manual melalui Excel atau melalui query database yang rumit, dan sulit didapatkan terutama pada industri ekspedisi.</p>

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

### Installation

1. Clone repository
   ```sh
   git clone https://github.com/rezimaulana/parcelhandover.git
   ```
2. Buat database untuk aplikasi
   ```sql
   CREATE DATABASE parcelhandover;
   ```
3. Modifikasi application.properties jika perlu
4. Buka project dan gunakan maven untuk update project
5. Run App.java
6. Table akan terupdate otomatis pada database
7. Data init akan insert otomatis pada tabel
8. http://localhost:5003/swagger-ui/index.html#/

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->
## Usage

Alur Proses Sistem adalah sebagai berikut:
1. Kedatangan driver ke gudang
2. Pembongkaran muatan barang
3. Input Data Handover Incoming oleh staff
4. Sorting muatan barang berdasarkan tujuan(sorting-hub to other hub / sorting-hub to delivery)
5. Input Data Handover Outgoing oleh staff
6. Loading muatan barang ke driver baru
7. Keberangkatan driver

### API Warehouse
API Warehouse memungkinkan Anda untuk input data warehouse yang disimpan dalam sistem. Endpoint berikut tersedia:
```
GET /warehouses
```
```
PUT /warehouses
```
```
POST /warehouses
```
```
GET /warehouses/data
```
### API Vehicle
API Vehicle memungkinkan Anda untuk melakukan input data vehicle yang disimpan dalam sistem. Endpoint berikut tersedia:
```
GET /vehicles
```
```
PUT /vehicles
```
```
POST /vehicles
```
```
GET /vehicles/data
```
### API User
API User memungkinkan Anda untuk melakukan input data user yang disimpan dalam sistem. Endpoint berikut tersedia:

```
GET /users
```
```
PUT /users
```
```
POST /users
```
```
GET /users/data
```

### API User Vehicle
API User Vehicle memungkinkan Anda untuk input data tabel bridge antara user dan kendaraan yang disimpan dalam sistem. Endpoint berikut tersedia:

```
GET /user-vehicle
```
```
PUT /user-vehicle
```
```
POST /user-vehicle
Menggunakan insertMany.
```
```
GET /user-vehicle/data
```

### API Handover
API Handover memungkinkan Anda untuk melakukan input data handover yang disimpan dalam sistem. Endpoint berikut tersedia:

```
GET /handover
```
```
PUT /handover
```
```
POST /handover
```
```
DELETE /handover
```
```
GET /handover/data
```

### API Report
API Report memungkinkan Anda untuk melihat ringkasan report dari sistem. Endpoint berikut tersedia:

```
GET /reports/summary
Mengambil ringkasan laporan secara keseluruhan.
```
```
GET /reports/summary/warehouse
Mengambil ringkasan laporan berdasarkan warehouse.
```
```
GET /reports/summary/driver
Mengambil ringkasan laporan berdasarkan driver.
```
```
GET /reports/sorting
Mengambil laporan rata-rata waktu bongkar(unloading), sorting, dan muat(loading).
```
```
GET /reports/sorting/warehouse
Mengambil laporan rata-rata waktu bongkar(unloading), sorting, dan muat(loading) berdasarkan warehouse.
```
```
GET /reports/sorting/driver
Mengambil laporan rata-rata waktu bongkar(unloading), sorting, dan muat(loading) berdasarkan driver.
```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- LICENSE -->
## License

Didistribusikan di bawah Lisensi GPL-3.0. Lihat `LICENSE.txt` untuk informasi lebih lanjut.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTACT -->
## Contact

Maulana Rezi Rosadi - [@rezi_maulana](https://twitter.com/rezi_maulana) - rsazrm@gmail.com

Project Link: [https://github.com/rezimaulana/parcelhandover](https://github.com/rezimaulana/parcelhandover)

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- MARKDOWN LINKS & IMAGES -->
[contributors-shield]: https://img.shields.io/github/contributors/rezimaulana/parcelhandover.svg?style=for-the-badge
[contributors-url]: https://github.com/rezimaulana/parcelhandover/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/rezimaulana/parcelhandover.svg?style=for-the-badge
[forks-url]: https://github.com/rezimaulana/parcelhandover/network/members
[stars-shield]: https://img.shields.io/github/stars/rezimaulana/parcelhandover.svg?style=for-the-badge
[stars-url]: https://github.com/rezimaulana/parcelhandover/stargazers
[issues-shield]: https://img.shields.io/github/issues/rezimaulana/parcelhandover.svg?style=for-the-badge
[issues-url]: https://github.com/rezimaulana/parcelhandover/issues
[license-shield]: https://img.shields.io/github/license/rezimaulana/parcelhandover.svg?style=for-the-badge
[license-url]: https://github.com/rezimaulana/parcelhandover/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/rezimaulana