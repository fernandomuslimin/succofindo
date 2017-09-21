-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 21, 2017 at 10:43 AM
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
  `bl` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kapal`
--

INSERT INTO `kapal` (`id`, `namakapal`, `seacondition`, `bl`) VALUES
(5, 'Dewa19', 'Fine', 1000),
(6, 'Dewa Dewi', 'Nice', 1092),
(7, 'HEWAN', 'BAIK', 123),
(8, 'tes123', 'baiksekali', 123);

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
  `gsw` float DEFAULT NULL,
  `forward` float NOT NULL,
  `after` float NOT NULL,
  `list` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tank`
--

INSERT INTO `tank` (`id`, `notank`, `sounding`, `gov`, `temp`, `density`, `vcf`, `gsv`, `wcf`, `gsw`, `forward`, `after`, `list`) VALUES
(5, '9p', 673, 89, 6834, 667, 8776, 45, 76, 546, 6457, 3489, 43),
(5, '8p', 8767, 6566, 4545, 23, 6556, 46, 565665, 564, 5, 445, 675),
(5, '7p', 78, NULL, 895, 38798, NULL, NULL, NULL, NULL, 34, 347, 834),
(5, '1p', 2234, NULL, 546, 56, NULL, NULL, NULL, NULL, 3422, 34534, 64),
(5, '10P', 8935, NULL, 776, 6732, NULL, NULL, NULL, NULL, 123, 346, 745),
(5, '11p', 345, NULL, 9834, 7834, NULL, NULL, NULL, NULL, 1232, 657, 345),
(5, '12p', 786, NULL, 798, 65, NULL, NULL, NULL, NULL, 675, 987, 65),
(6, '1p', 123, NULL, 65, 98, NULL, NULL, NULL, NULL, 23, 2345, 87);

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
-- Table structure for table `trim2`
--

CREATE TABLE `trim2` (
  `sounding` float(10,0) NOT NULL,
  `volume` float(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `trim2`
--

INSERT INTO `trim2` (`sounding`, `volume`) VALUES
(10, 61),
(20, 243),
(30, 532),
(40, 836),
(50, 1140);

-- --------------------------------------------------------

--
-- Table structure for table `trim4`
--

CREATE TABLE `trim4` (
  `height` decimal(10,0) NOT NULL,
  `volume` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `trim6`
--

CREATE TABLE `trim6` (
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
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `username` varchar(60) NOT NULL,
  `password` varchar(40) NOT NULL,
  `position` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userid`
--

INSERT INTO `userid` (`id`, `name`, `username`, `password`, `position`) VALUES
(1, 'administrator', 'admin', 'admin', 'manager');

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
-- Indexes for table `trim2`
--
ALTER TABLE `trim2`
  ADD PRIMARY KEY (`sounding`);

--
-- Indexes for table `trim4`
--
ALTER TABLE `trim4`
  ADD PRIMARY KEY (`height`);

--
-- Indexes for table `trim6`
--
ALTER TABLE `trim6`
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
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kapal`
--
ALTER TABLE `kapal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `userid`
--
ALTER TABLE `userid`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
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
