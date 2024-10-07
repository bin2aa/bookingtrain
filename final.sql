-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 02, 2024 at 07:47 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `final`
--

-- --------------------------------------------------------

--
-- Table structure for table `carriages`
--

CREATE TABLE `carriages` (
  `carriageId` int(11) NOT NULL,
  `carriageTypeId` int(11) DEFAULT NULL,
  `carriageCode` varchar(255) DEFAULT NULL,
  `carriageName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `carriagetype`
--

CREATE TABLE `carriagetype` (
  `carriageTypeId` int(11) NOT NULL,
  `carriageTypeName` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `detailschedule`
--

CREATE TABLE `detailschedule` (
  `scheduleId` int(11) NOT NULL,
  `seatId` int(11) NOT NULL,
  `statusSeatId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `employeeId` int(11) NOT NULL,
  `fullName` varchar(255) DEFAULT NULL,
  `dateOfBirth` date DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `objects`
--

CREATE TABLE `objects` (
  `objectId` int(11) NOT NULL,
  `objectName` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `operations`
--

CREATE TABLE `operations` (
  `operationId` int(11) NOT NULL,
  `operationName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `orderId` int(11) NOT NULL,
  `passengerId` int(11) DEFAULT NULL,
  `employeeId` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `timeToOrder` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `passengers`
--

CREATE TABLE `passengers` (
  `passengerId` int(11) NOT NULL,
  `fullName` varchar(255) DEFAULT NULL,
  `dateOfBirth` date DEFAULT NULL,
  `identityId` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `objectId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `permissions`
--

CREATE TABLE `permissions` (
  `permissionId` int(11) NOT NULL,
  `permissionName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `roleoperation`
--

CREATE TABLE `roleoperation` (
  `roleId` int(11) NOT NULL,
  `permissionId` int(11) NOT NULL,
  `operationId` int(11) DEFAULT NULL,
  `statusId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `roleId` int(11) NOT NULL,
  `roleName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `schedules`
--

CREATE TABLE `schedules` (
  `scheduleId` int(11) NOT NULL,
  `stationDepartmentId` int(11) DEFAULT NULL,
  `stationsArrivalId` int(11) DEFAULT NULL,
  `trainId` int(11) DEFAULT NULL,
  `timeToDepartment` datetime DEFAULT NULL,
  `timeToArrival` datetime DEFAULT NULL,
  `timeToCreateSchedule` datetime DEFAULT NULL,
  `employeeId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `seats`
--

CREATE TABLE `seats` (
  `seatId` int(11) NOT NULL,
  `seatCode` varchar(255) DEFAULT NULL,
  `seatTypeId` int(11) DEFAULT NULL,
  `carriageId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `seattype`
--

CREATE TABLE `seattype` (
  `seatTypeId` int(11) NOT NULL,
  `seatTypeName` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `stations`
--

CREATE TABLE `stations` (
  `stationId` int(11) NOT NULL,
  `stationCode` varchar(10) DEFAULT NULL,
  `stationName` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `statusroleoperation`
--

CREATE TABLE `statusroleoperation` (
  `statusId` int(11) NOT NULL,
  `statusName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `statusseat`
--

CREATE TABLE `statusseat` (
  `statusSeatId` int(11) NOT NULL,
  `statusSeatName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tickets`
--

CREATE TABLE `tickets` (
  `orderId` int(11) NOT NULL,
  `scheduleId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `trains`
--

CREATE TABLE `trains` (
  `trainId` int(11) NOT NULL,
  `trainCode` varchar(10) DEFAULT NULL,
  `trainName` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `carriages`
--
ALTER TABLE `carriages`
  ADD PRIMARY KEY (`carriageId`),
  ADD KEY `carriageTypeId` (`carriageTypeId`);

--
-- Indexes for table `carriagetype`
--
ALTER TABLE `carriagetype`
  ADD PRIMARY KEY (`carriageTypeId`);

--
-- Indexes for table `detailschedule`
--
ALTER TABLE `detailschedule`
  ADD PRIMARY KEY (`scheduleId`,`seatId`),
  ADD KEY `seatId` (`seatId`),
  ADD KEY `statusSeatId` (`statusSeatId`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`employeeId`),
  ADD KEY `roleId` (`roleId`);

--
-- Indexes for table `objects`
--
ALTER TABLE `objects`
  ADD PRIMARY KEY (`objectId`);

--
-- Indexes for table `operations`
--
ALTER TABLE `operations`
  ADD PRIMARY KEY (`operationId`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`orderId`),
  ADD KEY `passengerId` (`passengerId`),
  ADD KEY `employeeId` (`employeeId`);

--
-- Indexes for table `passengers`
--
ALTER TABLE `passengers`
  ADD PRIMARY KEY (`passengerId`),
  ADD KEY `objectId` (`objectId`);

--
-- Indexes for table `permissions`
--
ALTER TABLE `permissions`
  ADD PRIMARY KEY (`permissionId`);

--
-- Indexes for table `roleoperation`
--
ALTER TABLE `roleoperation`
  ADD PRIMARY KEY (`roleId`,`permissionId`),
  ADD KEY `permissionId` (`permissionId`),
  ADD KEY `operationId` (`operationId`),
  ADD KEY `statusId` (`statusId`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`roleId`);

--
-- Indexes for table `schedules`
--
ALTER TABLE `schedules`
  ADD PRIMARY KEY (`scheduleId`),
  ADD KEY `stationDepartmentId` (`stationDepartmentId`),
  ADD KEY `stationsArrivalId` (`stationsArrivalId`),
  ADD KEY `trainId` (`trainId`),
  ADD KEY `employeeId` (`employeeId`);

--
-- Indexes for table `seats`
--
ALTER TABLE `seats`
  ADD PRIMARY KEY (`seatId`),
  ADD KEY `seatTypeId` (`seatTypeId`),
  ADD KEY `carriageId` (`carriageId`);

--
-- Indexes for table `seattype`
--
ALTER TABLE `seattype`
  ADD PRIMARY KEY (`seatTypeId`);

--
-- Indexes for table `stations`
--
ALTER TABLE `stations`
  ADD PRIMARY KEY (`stationId`);

--
-- Indexes for table `statusroleoperation`
--
ALTER TABLE `statusroleoperation`
  ADD PRIMARY KEY (`statusId`);

--
-- Indexes for table `statusseat`
--
ALTER TABLE `statusseat`
  ADD PRIMARY KEY (`statusSeatId`);

--
-- Indexes for table `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`orderId`,`scheduleId`),
  ADD KEY `scheduleId` (`scheduleId`);

--
-- Indexes for table `trains`
--
ALTER TABLE `trains`
  ADD PRIMARY KEY (`trainId`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `carriages`
--
ALTER TABLE `carriages`
  ADD CONSTRAINT `carriages_ibfk_1` FOREIGN KEY (`carriageTypeId`) REFERENCES `carriagetype` (`carriageTypeId`);

--
-- Constraints for table `detailschedule`
--
ALTER TABLE `detailschedule`
  ADD CONSTRAINT `detailschedule_ibfk_1` FOREIGN KEY (`scheduleId`) REFERENCES `schedules` (`scheduleId`),
  ADD CONSTRAINT `detailschedule_ibfk_2` FOREIGN KEY (`seatId`) REFERENCES `seats` (`seatId`),
  ADD CONSTRAINT `detailschedule_ibfk_3` FOREIGN KEY (`statusSeatId`) REFERENCES `statusseat` (`statusSeatId`);

--
-- Constraints for table `employees`
--
ALTER TABLE `employees`
  ADD CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `roles` (`roleId`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`passengerId`) REFERENCES `passengers` (`passengerId`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`employeeId`) REFERENCES `employees` (`employeeId`);

--
-- Constraints for table `passengers`
--
ALTER TABLE `passengers`
  ADD CONSTRAINT `passengers_ibfk_1` FOREIGN KEY (`objectId`) REFERENCES `objects` (`objectId`);

--
-- Constraints for table `roleoperation`
--
ALTER TABLE `roleoperation`
  ADD CONSTRAINT `roleoperation_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `roles` (`roleId`),
  ADD CONSTRAINT `roleoperation_ibfk_2` FOREIGN KEY (`permissionId`) REFERENCES `permissions` (`permissionId`),
  ADD CONSTRAINT `roleoperation_ibfk_3` FOREIGN KEY (`operationId`) REFERENCES `operations` (`operationId`),
  ADD CONSTRAINT `roleoperation_ibfk_4` FOREIGN KEY (`statusId`) REFERENCES `statusroleoperation` (`statusId`);

--
-- Constraints for table `schedules`
--
ALTER TABLE `schedules`
  ADD CONSTRAINT `schedules_ibfk_1` FOREIGN KEY (`stationDepartmentId`) REFERENCES `stations` (`stationId`),
  ADD CONSTRAINT `schedules_ibfk_2` FOREIGN KEY (`stationsArrivalId`) REFERENCES `stations` (`stationId`),
  ADD CONSTRAINT `schedules_ibfk_3` FOREIGN KEY (`trainId`) REFERENCES `trains` (`trainId`),
  ADD CONSTRAINT `schedules_ibfk_4` FOREIGN KEY (`employeeId`) REFERENCES `employees` (`employeeId`);

--
-- Constraints for table `seats`
--
ALTER TABLE `seats`
  ADD CONSTRAINT `seats_ibfk_1` FOREIGN KEY (`seatTypeId`) REFERENCES `seattype` (`seatTypeId`),
  ADD CONSTRAINT `seats_ibfk_2` FOREIGN KEY (`carriageId`) REFERENCES `carriages` (`carriageId`);

--
-- Constraints for table `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `orders` (`orderId`),
  ADD CONSTRAINT `tickets_ibfk_2` FOREIGN KEY (`scheduleId`) REFERENCES `detailschedule` (`scheduleId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
