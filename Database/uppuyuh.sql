-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 11, 2021 at 06:35 PM
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
  `jml_pakan` int(11) NOT NULL,
  `id_pegawai` varchar(15) NOT NULL,
  `jml_telur` int(11) NOT NULL,
  `kebersihan` varchar(25) NOT NULL,
  `tgl_cek` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
('Kandang 1', 1000),
('Kandang 2', 500);

-- --------------------------------------------------------

--
-- Table structure for table `kesehatan`
--

CREATE TABLE `kesehatan` (
  `id_kesehatan` varchar(10) NOT NULL,
  `nama_kandang` varchar(25) NOT NULL,
  `nama_penyakit` varchar(30) NOT NULL,
  `id_pegawai` varchar(15) NOT NULL,
  `jml_sakit` int(11) DEFAULT NULL,
  `jml_mati` int(11) DEFAULT NULL,
  `tgl_cek` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `notif`
--

CREATE TABLE `notif` (
  `tgl` date NOT NULL,
  `notif1` int(11) NOT NULL,
  `notif2` int(11) NOT NULL,
  `notif3` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `notif`
--

INSERT INTO `notif` (`tgl`, `notif1`, `notif2`, `notif3`) VALUES
('2021-07-11', 1, 1, 1);

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
('2001', 'Jagung', 10000000, 3500),
('2002', 'BP', 25000000, 5000);

-- --------------------------------------------------------

--
-- Table structure for table `pegawai`
--

CREATE TABLE `pegawai` (
  `id_pegawai` varchar(15) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `asal` varchar(35) NOT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `jekel` varchar(10) NOT NULL,
  `no_telp` text DEFAULT NULL,
  `alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pegawai`
--

INSERT INTO `pegawai` (`id_pegawai`, `nama`, `asal`, `tgl_lahir`, `jekel`, `no_telp`, `alamat`) VALUES
('1001', 'Anton Putra', 'Padang', '1980-03-13', 'Laki-Laki', '082345767312', 'Padang Utara, Padang'),
('1002', 'Budi', 'Payakumbuh', '1975-02-05', 'Laki-Laki', '0895746475123', 'Kec Pauh, Padang'),
('1003', 'Budi Hartono', 'Padang Panjang', '1975-02-13', 'Laki-Laki', '089574647513', 'Kec Pauh, Padang');

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
('Radang usus (Quail enteritis)', '1. Lemas\n2. Nafsu Makan Menurun\n3. Bulu Kusam\n4. Diare Encer', 'Melalui Pakan dan Minuman Terkontaminasi feses', '1. Memperbaiki tata laksana pemelihara\n2. Memisahkan Burung Yang Sehat dan Sakit\n3. Menjaga Kebersihan Kandang', '1. Melalui Pakan / air minum dengan mencampur basitrasi, \n2. Klotetrasiklin, eritromisin, \ndoksisiklin, ampisilin, tilosin, \ndan linkomisin,\n\nDosis Basitrasi : 100g/ton');

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
(0, 'Admin', '12345678', 'Admin', 'Administrator'),
(4, 'Pegawai', 'Pegawai', 'Pegawai Up Puyuh', 'Pegawai');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cek_ternak`
--
ALTER TABLE `cek_ternak`
  ADD PRIMARY KEY (`nama_kandang`,`tgl_cek`) USING BTREE,
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
  ADD PRIMARY KEY (`nama_kandang`,`nama_penyakit`,`tgl_cek`) USING BTREE,
  ADD KEY `id_pegawai` (`id_pegawai`),
  ADD KEY `id_sakit` (`nama_penyakit`);

--
-- Indexes for table `notif`
--
ALTER TABLE `notif`
  ADD PRIMARY KEY (`tgl`);

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
  ADD PRIMARY KEY (`id_user`,`username`);

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
  ADD CONSTRAINT `kesehatan_ibfk_2` FOREIGN KEY (`id_pegawai`) REFERENCES `pegawai` (`id_pegawai`),
  ADD CONSTRAINT `kesehatan_ibfk_3` FOREIGN KEY (`nama_penyakit`) REFERENCES `penyakit` (`nama_penyakit`),
  ADD CONSTRAINT `kesehatan_ibfk_4` FOREIGN KEY (`nama_kandang`) REFERENCES `kandang` (`nama_kandang`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
