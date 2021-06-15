-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 15, 2021 at 06:12 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `uppuyuh`
--

-- --------------------------------------------------------

--
-- Table structure for table `cek_ternak`
--

CREATE TABLE `cek_ternak` (
  `id_cek` varchar(15) NOT NULL,
  `nama_kandang` varchar(25) NOT NULL,
  `id_pakan` varchar(15) NOT NULL,
  `id_pegawai` varchar(15) NOT NULL,
  `jml_telur` int(11) NOT NULL,
  `kebersihan` varchar(25) NOT NULL,
  `tgl_cek` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cek_ternak`
--

INSERT INTO `cek_ternak` (`id_cek`, `nama_kandang`, `id_pakan`, `id_pegawai`, `jml_telur`, `kebersihan`, `tgl_cek`) VALUES
('CT001', 'Kandang 1', '0001 ', '0002 ', 500, 'Bersih', '2021-06-15');

-- --------------------------------------------------------

--
-- Table structure for table `kandang`
--

CREATE TABLE `kandang` (
  `nama_kandang` varchar(25) NOT NULL,
  `jml_ternak` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kandang`
--

INSERT INTO `kandang` (`nama_kandang`, `jml_ternak`) VALUES
('Kandang 1', 500),
('Kandang 2', 800),
('Kandang 3', 750);

--
-- Triggers `kandang`
--
DELIMITER $$
CREATE TRIGGER `TRIGGER_UPDATE_KESEHATAN` AFTER UPDATE ON `kandang` FOR EACH ROW UPDATE kandang 
SET kandang.nama_kandang = NEW.nama_kandang,
kandang.jml_ternak = NEW.jml_ternak,
total = kandang.jml_ternak - kesehatan.jml_mati
WHERE kandang.nama_kandang
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `kesehatan`
--

CREATE TABLE `kesehatan` (
  `id_kesehatan` varchar(15) NOT NULL,
  `nama_kandang` varchar(25) NOT NULL,
  `nama_penyakit` varchar(30) NOT NULL,
  `id_pegawai` varchar(15) NOT NULL,
  `jml_sakit` int(11) NOT NULL,
  `jml_mati` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `pakan`
--

CREATE TABLE `pakan` (
  `id_pakan` varchar(15) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `harga` double NOT NULL,
  `stok` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pakan`
--

INSERT INTO `pakan` (`id_pakan`, `nama`, `harga`, `stok`) VALUES
('0001', 'Dadak Padi', 50000, 500),
('0002', 'Jagung', 400000, 300);

-- --------------------------------------------------------

--
-- Table structure for table `pegawai`
--

CREATE TABLE `pegawai` (
  `id_pegawai` varchar(15) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `asal` varchar(35) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `jekel` varchar(10) NOT NULL,
  `no_telp` text NOT NULL,
  `alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pegawai`
--

INSERT INTO `pegawai` (`id_pegawai`, `nama`, `asal`, `tgl_lahir`, `jekel`, `no_telp`, `alamat`) VALUES
('0001', 'Budi', 'Bukittinggi', '2021-06-11', 'Laki-laki', '083754546343', 'Pasar Baru, Kecamatan Pauh, Kota Padang'),
('0002', 'Ahmad', 'Padang', '1999-09-08', 'Laki-Laki', '0839578463', 'Padang Barat'),
('0003', 'Anggi', 'Payakumbuh', '1987-08-27', 'Perempuan', '08746574657', 'Payakumbuh Barat'),
('0004', 'Anton', 'Jambi', '1970-08-04', 'Laki-Laki', '089758645', 'Padang Utara');

-- --------------------------------------------------------

--
-- Table structure for table `penyakit`
--

CREATE TABLE `penyakit` (
  `nama_penyakit` varchar(30) NOT NULL,
  `gejala` text NOT NULL,
  `penularan` text NOT NULL,
  `pencegahan` text NOT NULL,
  `pengobatan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `penyakit`
--

INSERT INTO `penyakit` (`nama_penyakit`, `gejala`, `penularan`, `pencegahan`, `pengobatan`) VALUES
('Pullorum (berak putih)', '1. kotoran berwarna putih, nafsu makan hilang, \n2. sesak nafas, \n3. bulu-bulu mengkerut  \n4. sayap lemah menggantung. ', '1. Melalui pakan dan minum \n2. peralatan kandang yang kurang bersih', 'Menjaga kebersihan kandang dan peralatan kandang yang digunakan', 'Ampicilin, colistin,Enrofloxasin. Dan untuk meningkatkan kondisi diberikan vitamin.'),
('Radang Usus', '1. puyuh tampak lesu \n2. nafsu makan menurun\n3. bulu kelihatan kusam\n4. diare encer', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `akses` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `username`, `password`, `nama`, `akses`) VALUES
(0, 'Admin', '4e7afebcfbae000b22c7c85e5560f89a2a0280b4', 'Admin UpPuyuh', 'Administrator'),
(1, 'syarif', '29fe2954c7ec4a74f6f410e45e2a6e99bc5ad1de', 'Syarif Hidayat', 'Administrator'),
(2, 'Alvin', 'f7c3bc1d808e04732adf679965ccc34ca7ae3441', 'Alvin Faiz', 'Administrator'),
(3, 'tiara', 'c35bf6e2b2bc7397ff9f2321c39ecabd9043f671', 'Tiara Harmila', 'Pegawai'),
(4, 'Syarif', 'ff6cbb3e0c97b3e406587727abf48f91acf5962f', 'Syarif Hidayat', 'Pegawai');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cek_ternak`
--
ALTER TABLE `cek_ternak`
  ADD PRIMARY KEY (`id_cek`),
  ADD KEY `id_kandang` (`nama_kandang`),
  ADD KEY `id_pegawai` (`id_pegawai`),
  ADD KEY `id_pakan` (`id_pakan`);

--
-- Indexes for table `kandang`
--
ALTER TABLE `kandang`
  ADD PRIMARY KEY (`nama_kandang`);

--
-- Indexes for table `kesehatan`
--
ALTER TABLE `kesehatan`
  ADD PRIMARY KEY (`id_kesehatan`),
  ADD KEY `nama_kandang` (`nama_kandang`),
  ADD KEY `id_pegawai` (`id_pegawai`),
  ADD KEY `id_sakit` (`nama_penyakit`);

--
-- Indexes for table `pakan`
--
ALTER TABLE `pakan`
  ADD PRIMARY KEY (`id_pakan`);

--
-- Indexes for table `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`id_pegawai`);

--
-- Indexes for table `penyakit`
--
ALTER TABLE `penyakit`
  ADD PRIMARY KEY (`nama_penyakit`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cek_ternak`
--
ALTER TABLE `cek_ternak`
  ADD CONSTRAINT `cek_ternak_ibfk_1` FOREIGN KEY (`nama_kandang`) REFERENCES `kandang` (`nama_kandang`),
  ADD CONSTRAINT `cek_ternak_ibfk_2` FOREIGN KEY (`id_pegawai`) REFERENCES `pegawai` (`id_pegawai`),
  ADD CONSTRAINT `cek_ternak_ibfk_3` FOREIGN KEY (`id_pakan`) REFERENCES `pakan` (`id_pakan`);

--
-- Constraints for table `kesehatan`
--
ALTER TABLE `kesehatan`
  ADD CONSTRAINT `kesehatan_ibfk_1` FOREIGN KEY (`nama_kandang`) REFERENCES `kandang` (`nama_kandang`),
  ADD CONSTRAINT `kesehatan_ibfk_2` FOREIGN KEY (`id_pegawai`) REFERENCES `pegawai` (`id_pegawai`),
  ADD CONSTRAINT `kesehatan_ibfk_3` FOREIGN KEY (`nama_penyakit`) REFERENCES `penyakit` (`nama_penyakit`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
