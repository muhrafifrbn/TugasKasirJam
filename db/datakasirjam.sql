-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 09 Mar 2024 pada 07.39
-- Versi server: 10.4.21-MariaDB
-- Versi PHP: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `datakasirjam`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `barangjam`
--

CREATE TABLE `barangjam` (
  `kode_jam` varchar(50) NOT NULL,
  `merek_jam` varchar(100) NOT NULL,
  `jenis_jam` varchar(20) NOT NULL,
  `harga` int(225) NOT NULL,
  `stok` int(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `barangjam`
--

INSERT INTO `barangjam` (`kode_jam`, `merek_jam`, `jenis_jam`, `harga`, `stok`) VALUES
('KTGJM00001', 'GShock', 'Perempuan', 25000, 1),
('KTGJM00002', 'Rolex ', 'Laki-laki', 20000, 3),
('KTGJM00004', 'rolex g56', 'Perempuan', 5000, 3),
('KTGJM00006', 'apex', 'Perempuan', 10000, 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `datakasir`
--

CREATE TABLE `datakasir` (
  `no_transaksi` varchar(225) NOT NULL,
  `nama_customer` varchar(100) NOT NULL,
  `merek_jam` varchar(225) NOT NULL,
  `jenis_jam` varchar(50) NOT NULL,
  `diameter_jam` int(50) NOT NULL,
  `aksesoris` varchar(50) NOT NULL,
  `jumlah` int(50) NOT NULL,
  `total` int(100) NOT NULL,
  `pembayaran` int(100) NOT NULL,
  `kembalian` int(100) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `barangjam`
--
ALTER TABLE `barangjam`
  ADD PRIMARY KEY (`kode_jam`);

--
-- Indeks untuk tabel `datakasir`
--
ALTER TABLE `datakasir`
  ADD PRIMARY KEY (`no_transaksi`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
