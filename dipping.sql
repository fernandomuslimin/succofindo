-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 09, 2017 at 04:46 PM
-- Server version: 10.1.25-MariaDB
-- PHP Version: 7.1.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dipping`
--

-- --------------------------------------------------------

--
-- Table structure for table `kapal`
--

CREATE TABLE `kapal` (
  `id` int(11) NOT NULL,
  `namakapal` varchar(100) NOT NULL,
  `seacondition` varchar(60) NOT NULL,
  `bl` float NOT NULL,
  `forward` float NOT NULL,
  `after` float NOT NULL,
  `list` float DEFAULT NULL,
  `trim` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kapal`
--

INSERT INTO `kapal` (`id`, `namakapal`, `seacondition`, `bl`, `forward`, `after`, `list`, `trim`) VALUES
(22, 'testing', 'nice', 100, 40, 20, 0, 20),
(23, 'baru', 'bad', 1000, 50, 30, 0, 20),
(24, 'bagus', 'baik', 100, 50, 30, 0, 20),
(25, 'abcdefg', 'baiksekali', 200000000000, 3234, 34, 34, 3200),
(26, 'bni', 'baik', 100, 40, 20, 0, 20);

-- --------------------------------------------------------

--
-- Table structure for table `tank`
--

CREATE TABLE `tank` (
  `id` int(11) NOT NULL,
  `notank` varchar(4) NOT NULL,
  `sounding` float NOT NULL,
  `gov` float DEFAULT NULL,
  `temp` float NOT NULL,
  `density` float NOT NULL,
  `vcf` float DEFAULT NULL,
  `gsv` float DEFAULT NULL,
  `wcf` float DEFAULT NULL,
  `gsw` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tank`
--

INSERT INTO `tank` (`id`, `notank`, `sounding`, `gov`, `temp`, `density`, `vcf`, `gsv`, `wcf`, `gsw`) VALUES
(22, '3R', 23, 532, 23, 14, NULL, NULL, NULL, NULL),
(22, '3U', 30, 532, 32, 34, NULL, NULL, NULL, NULL),
(22, '4p', 30, 532, 12, 32, NULL, NULL, NULL, NULL),
(22, '4s', 40, 532, 10, 34, NULL, NULL, NULL, NULL),
(22, '5p', 50, 532, 10, 10, NULL, NULL, NULL, NULL),
(22, '6p', 10, 532, 32, 12, NULL, NULL, NULL, NULL),
(22, '6s', 30, 532, 19, 12, NULL, NULL, NULL, NULL),
(22, '7p', 10, 532, 30, 50, NULL, NULL, NULL, NULL),
(22, '7s', 20, 532, 30, 23, NULL, NULL, NULL, NULL),
(22, '8p', 30, 532, 124, 4, NULL, NULL, NULL, NULL),
(22, '8s', 50, 532, 23, 56, NULL, NULL, NULL, NULL),
(22, '9p', 10, 532, 23, 21, NULL, NULL, NULL, NULL),
(22, '9s', 20, 532, 9, 8, NULL, NULL, NULL, NULL),
(22, '10p', 50, 532, 9, 8, NULL, NULL, NULL, NULL),
(22, '10s', 50, 532, 3, 2, NULL, NULL, NULL, NULL),
(22, '11p', 10, 532, 30, 20, NULL, NULL, NULL, NULL),
(22, '12p', 10, 532, 30, 40, NULL, NULL, NULL, NULL),
(22, '12s', 20, 532, 30, 40, NULL, NULL, NULL, NULL),
(22, '11s', 20, 532, 0, 0, NULL, NULL, NULL, NULL),
(22, '13p', 30, 532, 0, 0, NULL, NULL, NULL, NULL),
(22, '14p', 50, 532, 32, 345, NULL, NULL, NULL, NULL),
(22, '14s', 40, 532, 22, 13, NULL, NULL, NULL, NULL),
(22, '15p', 40, 532, 0, 0, NULL, NULL, NULL, NULL),
(22, '15s', 40, 532, 0, 0, NULL, NULL, NULL, NULL),
(23, '1p', 10, 532, 30, 23, NULL, NULL, NULL, NULL),
(23, '1s', 20, 532, 0, 0, NULL, NULL, NULL, NULL),
(23, '2p', 10, 532, 0, 0, NULL, NULL, NULL, NULL),
(23, '2s', 10, 532, 0, 0, NULL, NULL, NULL, NULL),
(23, '3p', 10, 532, 0, 0, NULL, NULL, NULL, NULL),
(23, '3p', 10, 532, 0, 0, NULL, NULL, NULL, NULL),
(23, '3p', 20, 532, 0, 0, NULL, NULL, NULL, NULL),
(23, '4p', 20, 532, 0, 0, NULL, NULL, NULL, NULL),
(23, '4s', 10, 532, 0, 0, NULL, NULL, NULL, NULL),
(23, '5p', 10, 532, 0, 0, NULL, NULL, NULL, NULL),
(23, '5s', 10, 532, 0, 0, NULL, NULL, NULL, NULL),
(23, '6p', 30, 532, 0, 0, NULL, NULL, NULL, NULL),
(24, '1p', 10, 532, 0, 0, NULL, NULL, NULL, NULL),
(24, '1s', 30, 532, 0, 0, NULL, NULL, NULL, NULL),
(24, '2p', 50, 1140, 0, 0, NULL, NULL, NULL, NULL),
(24, '2s', 40, 836, 0, 0, NULL, NULL, NULL, NULL),
(24, '3p', 12345700, 0, 23498, 3498, NULL, NULL, NULL, NULL),
(24, '3s', 32000000000, 0, 234, 34, NULL, NULL, NULL, NULL),
(22, '55p', 10, NULL, 12, 12, NULL, NULL, NULL, NULL),
(22, '66p', 10, 0, 32, 23, NULL, NULL, NULL, NULL),
(22, '56p', 10, NULL, 2, 3, NULL, NULL, NULL, NULL),
(22, '57p', 10, 0, 30, 20, NULL, NULL, NULL, NULL),
(22, '58p', 10, NULL, 12, 43, NULL, NULL, NULL, NULL),
(22, '61p', 10, 61, 23, 32, NULL, NULL, NULL, NULL),
(22, '62p', 10, NULL, 3, 1, NULL, NULL, NULL, NULL),
(22, '63p', 10, NULL, 23, 32, NULL, NULL, NULL, NULL),
(22, '64p', 10, NULL, 32, 12, NULL, NULL, NULL, NULL),
(22, '65p', 12, NULL, 34, 34, NULL, NULL, NULL, NULL),
(22, '69p', 15, 152, 31, 12, NULL, NULL, NULL, NULL),
(22, '70p', 14, 134, 23, 30, NULL, NULL, NULL, NULL),
(22, '29p', 16, 171, 34, 44, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tankresults`
--

CREATE TABLE `tankresults` (
  `idkapal` varchar(100) NOT NULL,
  `namakapal` varchar(100) NOT NULL,
  `seacondition` varchar(100) NOT NULL,
  `bl` varchar(100) NOT NULL,
  `notank` varchar(3) DEFAULT NULL,
  `sounding` float DEFAULT NULL,
  `gov` float DEFAULT NULL,
  `temp` float DEFAULT NULL,
  `density` float DEFAULT NULL,
  `vcf` float DEFAULT NULL,
  `gsv` float DEFAULT NULL,
  `wcf` float DEFAULT NULL,
  `gsw` float DEFAULT NULL,
  `forward` float DEFAULT NULL,
  `after` float DEFAULT NULL,
  `list` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `trim20`
--

CREATE TABLE `trim20` (
  `sounding` float(10,0) NOT NULL,
  `volume` float(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `trim20`
--

INSERT INTO `trim20` (`sounding`, `volume`) VALUES
(10, 61),
(20, 243),
(30, 532),
(40, 836),
(50, 1140);

-- --------------------------------------------------------

--
-- Table structure for table `trim40`
--

CREATE TABLE `trim40` (
  `sounding` decimal(10,0) NOT NULL,
  `volume` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `trim40`
--

INSERT INTO `trim40` (`sounding`, `volume`) VALUES
('10', '100'),
('20', '200');

-- --------------------------------------------------------

--
-- Table structure for table `trim60`
--

CREATE TABLE `trim60` (
  `height` decimal(10,0) NOT NULL,
  `volume` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `trim80`
--

CREATE TABLE `trim80` (
  `height` decimal(10,0) NOT NULL,
  `volume` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `trim100`
--

CREATE TABLE `trim100` (
  `height` decimal(10,0) NOT NULL,
  `volume` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `userid`
--

CREATE TABLE `userid` (
  `npp` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `username` varchar(60) NOT NULL,
  `password` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userid`
--

INSERT INTO `userid` (`npp`, `name`, `username`, `password`) VALUES
(1, 'administrator', 'admin', 'admin'),
(12359, 'Ryan', 'ryanrahmadyan', 'bandung'),
(12360, 'Fernando M', 'fernando', 'bandung'),
(12366, 'Leo M', 'leomuttaqin', 'palembang'),
(12370, 'Nisa Ainun', 'ainunisa', 'nisabandung');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kapal`
--
ALTER TABLE `kapal`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tank`
--
ALTER TABLE `tank`
  ADD KEY `id` (`id`);

--
-- Indexes for table `tankresults`
--
ALTER TABLE `tankresults`
  ADD PRIMARY KEY (`idkapal`);

--
-- Indexes for table `trim20`
--
ALTER TABLE `trim20`
  ADD PRIMARY KEY (`sounding`);

--
-- Indexes for table `trim40`
--
ALTER TABLE `trim40`
  ADD PRIMARY KEY (`sounding`);

--
-- Indexes for table `trim60`
--
ALTER TABLE `trim60`
  ADD PRIMARY KEY (`height`);

--
-- Indexes for table `trim80`
--
ALTER TABLE `trim80`
  ADD PRIMARY KEY (`height`);

--
-- Indexes for table `trim100`
--
ALTER TABLE `trim100`
  ADD PRIMARY KEY (`height`);

--
-- Indexes for table `userid`
--
ALTER TABLE `userid`
  ADD PRIMARY KEY (`npp`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kapal`
--
ALTER TABLE `kapal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `tank`
--
ALTER TABLE `tank`
  ADD CONSTRAINT `tank_ibfk_1` FOREIGN KEY (`id`) REFERENCES `kapal` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
