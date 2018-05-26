-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2018 at 01:45 PM
-- Server version: 10.1.22-MariaDB
-- PHP Version: 7.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_bmd`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `kodeBooking` int(20) NOT NULL,
  `tanggal` date NOT NULL,
  `username` varchar(50) NOT NULL,
  `kodeDokter` varchar(20) NOT NULL,
  `kodePraktek` varchar(20) NOT NULL,
  `tanggalBooking` date NOT NULL,
  `nomorAntrian` int(50) NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `dokter`
--

CREATE TABLE `dokter` (
  `kodeDokter` varchar(20) NOT NULL,
  `username` varchar(50) NOT NULL,
  `namaDokter` varchar(50) NOT NULL,
  `alamatPraktek` varchar(50) NOT NULL,
  `kotaPraktek` varchar(20) NOT NULL,
  `kodeSpesialis` varchar(20) NOT NULL,
  `telp1` int(20) NOT NULL,
  `telp2` int(20) NOT NULL,
  `nomerSiup` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `jenis` varchar(20) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `alamat` varchar(200) NOT NULL,
  `kota` varchar(50) NOT NULL,
  `telp` int(20) NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `praktek`
--

CREATE TABLE `praktek` (
  `kodePraktek` varchar(20) NOT NULL,
  `kodeDokter` varchar(20) NOT NULL,
  `hari` varchar(10) NOT NULL,
  `jamAwal` int(10) NOT NULL,
  `jamAkhir` int(10) NOT NULL,
  `maxAntrian` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `spesialis`
--

CREATE TABLE `spesialis` (
  `kodeSpesialis` varchar(20) NOT NULL,
  `namaSpesialis` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`kodeBooking`),
  ADD KEY `cusername` (`username`),
  ADD KEY `ckodeDokter` (`kodeDokter`),
  ADD KEY `ckodePraktek` (`kodePraktek`);

--
-- Indexes for table `dokter`
--
ALTER TABLE `dokter`
  ADD PRIMARY KEY (`kodeDokter`),
  ADD KEY `ckodeSpesialis` (`kodeSpesialis`) USING BTREE;

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `praktek`
--
ALTER TABLE `praktek`
  ADD PRIMARY KEY (`kodePraktek`),
  ADD KEY `ckodeDokter` (`kodeDokter`) USING BTREE;

--
-- Indexes for table `spesialis`
--
ALTER TABLE `spesialis`
  ADD PRIMARY KEY (`kodeSpesialis`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `kodeBooking` int(20) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `ckodeDokter` FOREIGN KEY (`kodeDokter`) REFERENCES `dokter` (`kodeDokter`),
  ADD CONSTRAINT `ckodePraktek` FOREIGN KEY (`kodePraktek`) REFERENCES `praktek` (`kodePraktek`),
  ADD CONSTRAINT `cusername` FOREIGN KEY (`username`) REFERENCES `member` (`username`);

--
-- Constraints for table `dokter`
--
ALTER TABLE `dokter`
  ADD CONSTRAINT `kodeSpesialis` FOREIGN KEY (`kodeSpesialis`) REFERENCES `spesialis` (`kodeSpesialis`);

--
-- Constraints for table `praktek`
--
ALTER TABLE `praktek`
  ADD CONSTRAINT `kodeDokter` FOREIGN KEY (`kodeDokter`) REFERENCES `dokter` (`kodeDokter`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
