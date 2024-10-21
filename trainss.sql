-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 13, 2024 at 12:04 PM
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
-- Database: `trainss`
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

--
-- Dumping data for table `carriages`
--

INSERT INTO `carriages` (`carriageId`, `carriageTypeId`, `carriageCode`, `carriageName`) VALUES
(1, 1, 'C1', 'Carriage 1'),
(2, 1, 'C2', 'Carriage 2'),
(3, 2, 'C3', 'Carriage 3'),
(4, 2, 'C4', 'Carriage 4'),
(5, 1, 'C5', 'Carriage 5');

-- --------------------------------------------------------

--
-- Table structure for table `carriagetype`
--

CREATE TABLE `carriagetype` (
  `carriageTypeId` int(11) NOT NULL,
  `carriageTypeName` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `carriagetype`
--

INSERT INTO `carriagetype` (`carriageTypeId`, `carriageTypeName`, `description`) VALUES
(1, 'Sleeper', 'Carriage with sleeping facilities.'),
(2, 'Seater', 'Carriage with seating arrangements.'),
(3, 'Luxury', 'High-end carriage with premium amenities.'),
(4, 'Economy', 'Affordable carriage for budget travelers.'),
(5, 'First Class', 'Comfortable seating with extra space and services.');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `customerId` int(11) NOT NULL,
  `customerName` varchar(255) DEFAULT NULL,
  `customerEmail` varchar(255) DEFAULT NULL,
  `customerPhone` varchar(20) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL
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

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`employeeId`, `fullName`, `dateOfBirth`, `address`, `phone`, `roleId`) VALUES
(1, 'Nguyễn Văn A', '1990-01-01', '123 Đường ABC, TP.HCM', '0901234567', 1),
(2, 'Trần Thị B', '1992-02-02', '456 Đường DEF, Hà Nội', '0902345678', 2),
(3, 'Lê Văn C', '1988-03-03', '789 Đường GHI, Đà Nẵng', '0903456789', 3),
(4, 'Phạm Thị D', '1995-04-04', '321 Đường JKL, Cần Thơ', '0904567890', 1),
(5, 'Nguyễn Văn E', '1985-05-05', '654 Đường MNO, Hải Phòng', '0905678901', 2),
(6, 'Trần Thị F', '1993-06-06', '987 Đường PQR, Nha Trang', '0906789012', 3),
(7, 'Lê Văn G', '1980-07-07', '159 Đường STU, Vũng Tàu', '0907890123', 1),
(8, 'Phạm Thị H', '1991-08-08', '753 Đường VWX, Huế', '0908901234', 2),
(9, 'Nguyễn Văn I', '1987-09-09', '852 Đường YZ, Đà Lạt', '0909012345', 3),
(10, 'Trần Thị J', '1994-10-10', '369 Đường ABC, Quy Nhơn', '0900123456', 1);

-- --------------------------------------------------------

--
-- Table structure for table `legs`
--

CREATE TABLE `legs` (
  `legId` int(11) NOT NULL,
  `routeId` int(11) DEFAULT NULL,
  `statusSeatId` int(11) DEFAULT NULL,
  `stationDepartureId` int(11) DEFAULT NULL,
  `stationArrivalId` int(11) DEFAULT NULL,
  `timeDeparture` datetime DEFAULT NULL,
  `timeArrival` datetime DEFAULT NULL,
  `seatId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `legs`
--

INSERT INTO `legs` (`legId`, `routeId`, `statusSeatId`, `stationDepartureId`, `stationArrivalId`, `timeDeparture`, `timeArrival`, `seatId`) VALUES
(1, 1, 1, 1, 2, '2024-10-15 08:00:00', '2024-10-15 09:00:00', 101),
(2, 1, 1, 2, 3, '2024-10-15 09:30:00', '2024-10-15 10:30:00', 102),
(3, 1, 1, 3, 4, '2024-10-15 11:00:00', '2024-10-15 12:00:00', 103),
(4, 2, 1, 1, 2, '2024-10-15 07:00:00', '2024-10-15 08:00:00', 104),
(5, 2, 1, 2, 3, '2024-10-15 08:30:00', '2024-10-15 09:30:00', 105),
(6, 2, 1, 3, 4, '2024-10-15 10:00:00', '2024-10-15 11:00:00', 106),
(7, 3, 1, 1, 3, '2024-10-15 09:00:00', '2024-10-15 10:00:00', 107),
(8, 3, 1, 2, 4, '2024-10-15 10:30:00', '2024-10-15 11:30:00', 108),
(9, 3, 1, 1, 4, '2024-10-15 11:00:00', '2024-10-15 12:30:00', 109),
(10, 4, 1, 1, 2, '2024-10-15 06:00:00', '2024-10-15 07:00:00', 110),
(11, 4, 1, 2, 3, '2024-10-15 07:30:00', '2024-10-15 08:30:00', 111),
(12, 4, 1, 3, 4, '2024-10-15 09:00:00', '2024-10-15 10:00:00', 112),
(13, 5, 1, 1, 2, '2024-10-15 07:15:00', '2024-10-15 08:15:00', 113),
(14, 5, 1, 2, 3, '2024-10-15 08:45:00', '2024-10-15 09:45:00', 114),
(15, 5, 1, 3, 4, '2024-10-15 10:15:00', '2024-10-15 11:15:00', 115),
(16, 6, 1, 1, 3, '2024-10-15 08:10:00', '2024-10-15 09:10:00', 116),
(17, 6, 1, 2, 4, '2024-10-15 09:40:00', '2024-10-15 10:40:00', 117),
(18, 7, 1, 1, 4, '2024-10-15 10:20:00', '2024-10-15 11:20:00', 118),
(19, 7, 1, 1, 2, '2024-10-15 08:30:00', '2024-10-15 09:30:00', 119),
(20, 8, 1, 2, 3, '2024-10-15 09:50:00', '2024-10-15 10:50:00', 120),
(21, 8, 1, 3, 4, '2024-10-15 11:20:00', '2024-10-15 12:20:00', 121),
(22, 9, 1, 1, 2, '2024-10-15 06:45:00', '2024-10-15 07:45:00', 122),
(23, 9, 1, 2, 3, '2024-10-15 08:15:00', '2024-10-15 09:15:00', 123),
(24, 10, 1, 1, 3, '2024-10-15 07:50:00', '2024-10-15 08:50:00', 124),
(25, 10, 1, 2, 4, '2024-10-15 09:20:00', '2024-10-15 10:20:00', 125),
(26, 11, 1, 1, 2, '2024-10-15 06:30:00', '2024-10-15 07:30:00', 126),
(27, 11, 1, 2, 3, '2024-10-15 08:00:00', '2024-10-15 09:00:00', 127),
(28, 12, 1, 1, 4, '2024-10-15 09:10:00', '2024-10-15 10:10:00', 128),
(29, 12, 1, 3, 4, '2024-10-15 11:30:00', '2024-10-15 12:30:00', 129),
(30, 13, 1, 1, 2, '2024-10-15 06:00:00', '2024-10-15 07:00:00', 130),
(31, 13, 1, 2, 3, '2024-10-15 08:45:00', '2024-10-15 09:45:00', 131),
(32, 14, 1, 1, 3, '2024-10-15 09:30:00', '2024-10-15 10:30:00', 132),
(33, 14, 1, 2, 4, '2024-10-15 11:00:00', '2024-10-15 12:00:00', 133),
(34, 15, 1, 1, 4, '2024-10-15 08:10:00', '2024-10-15 09:10:00', 134),
(35, 15, 1, 2, 3, '2024-10-15 10:30:00', '2024-10-15 11:30:00', 135),
(36, 16, 1, 1, 2, '2024-10-15 07:45:00', '2024-10-15 08:45:00', 136),
(37, 16, 1, 2, 4, '2024-10-15 09:15:00', '2024-10-15 10:15:00', 137),
(38, 17, 1, 1, 2, '2024-10-15 06:30:00', '2024-10-15 07:30:00', 138),
(39, 17, 1, 3, 4, '2024-10-15 10:20:00', '2024-10-15 11:20:00', 139),
(40, 18, 1, 1, 3, '2024-10-15 08:50:00', '2024-10-15 09:50:00', 140);

-- --------------------------------------------------------

--
-- Table structure for table `objects`
--

CREATE TABLE `objects` (
  `objectId` int(11) NOT NULL,
  `objectName` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `objects`
--

INSERT INTO `objects` (`objectId`, `objectName`, `price`) VALUES
(1, 'Người lớn', 100000),
(2, 'Trẻ em', 50000),
(3, 'Người cao tuổi', 70000),
(4, 'Sinh viên', 60000),
(5, 'Người khuyết tật', 30000),
(6, 'Thành viên gia đình', 80000),
(7, 'Khách đoàn', 90000),
(8, 'Người đi lại đặc biệt', 120000),
(9, 'Người dùng thẻ thành viên', 85000),
(10, 'Người đi du lịch', 95000);

-- --------------------------------------------------------

--
-- Table structure for table `operations`
--

CREATE TABLE `operations` (
  `operationId` int(11) NOT NULL,
  `operationName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `operations`
--

INSERT INTO `operations` (`operationId`, `operationName`) VALUES
(1, 'Create User'),
(2, 'Delete User'),
(3, 'Edit User'),
(4, 'View User'),
(5, 'Create Post'),
(6, 'Edit Post'),
(7, 'Delete Post'),
(8, 'View Post'),
(9, 'Generate Report'),
(10, 'Access Admin Panel');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `orderId` int(11) NOT NULL,
  `customerId` int(11) DEFAULT NULL,
  `employeeId` int(11) DEFAULT NULL,
  `orderDate` datetime DEFAULT NULL,
  `totalPrice` decimal(10,2) DEFAULT NULL
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

--
-- Dumping data for table `permissions`
--

INSERT INTO `permissions` (`permissionId`, `permissionName`) VALUES
(1, 'View'),
(2, 'Edit'),
(3, 'Delete'),
(4, 'Create'),
(5, 'Manage Users'),
(6, 'View Reports'),
(7, 'Edit Settings'),
(8, 'Access Dashboard'),
(9, 'Moderate Content'),
(10, 'Manage Roles');

-- --------------------------------------------------------

--
-- Table structure for table `roleoperation`
--

CREATE TABLE `roleoperation` (
  `roleId` int(11) NOT NULL,
  `permissionId` int(11) NOT NULL,
  `operationId` int(11) NOT NULL,
  `statusId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roleoperation`
--

INSERT INTO `roleoperation` (`roleId`, `permissionId`, `operationId`, `statusId`) VALUES
(1, 1, 1, 1),
(1, 1, 2, 1),
(1, 1, 3, 1),
(1, 1, 4, 1),
(1, 1, 8, 1),
(1, 2, 5, 1),
(1, 2, 6, 1),
(1, 2, 7, 1),
(1, 2, 9, 1),
(1, 2, 10, 1),
(2, 1, 8, 1),
(3, 1, 1, 1),
(3, 1, 2, 1),
(3, 1, 3, 1),
(3, 1, 4, 1),
(3, 1, 8, 1),
(3, 2, 5, 1),
(3, 2, 6, 1),
(3, 2, 7, 1),
(3, 2, 9, 1),
(3, 2, 10, 1);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `roleId` int(11) NOT NULL,
  `roleName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`roleId`, `roleName`) VALUES
(1, 'Admin'),
(2, 'User'),
(3, 'Manager'),
(4, 'Guest'),
(5, 'Moderator');

-- --------------------------------------------------------

--
-- Table structure for table `routes`
--

CREATE TABLE `routes` (
  `routeId` int(11) NOT NULL,
  `stationDepartmentId` int(11) DEFAULT NULL,
  `stationArrivalId` int(11) DEFAULT NULL,
  `trainId` int(11) DEFAULT NULL,
  `timeToDepartment` datetime DEFAULT NULL,
  `timeToArrival` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `routes`
--

INSERT INTO `routes` (`routeId`, `stationDepartmentId`, `stationArrivalId`, `trainId`, `timeToDepartment`, `timeToArrival`) VALUES
(1, 1, 2, 1, '2024-10-14 08:00:00', '2024-10-14 10:00:00'),
(2, 2, 3, 1, '2024-10-14 10:30:00', '2024-10-14 12:00:00'),
(3, 3, 4, 1, '2024-10-14 12:30:00', '2024-10-14 14:00:00'),
(4, 1, 3, 2, '2024-10-15 09:00:00', '2024-10-15 11:30:00'),
(5, 3, 4, 2, '2024-10-15 12:00:00', '2024-10-15 13:30:00'),
(6, 1, 4, 3, '2024-10-16 07:00:00', '2024-10-16 13:00:00'),
(7, 2, 4, 4, '2024-10-16 09:30:00', '2024-10-16 12:30:00'),
(8, 4, 5, 4, '2024-10-16 13:00:00', '2024-10-16 15:30:00'),
(9, 1, 5, 5, '2024-10-17 06:00:00', '2024-10-17 16:00:00'),
(10, 2, 5, 6, '2024-10-17 08:00:00', '2024-10-17 16:00:00'),
(11, 5, 6, 7, '2024-10-18 10:00:00', '2024-10-18 14:00:00'),
(12, 1, 6, 7, '2024-10-18 06:00:00', '2024-10-18 14:00:00'),
(13, 2, 6, 8, '2024-10-19 08:00:00', '2024-10-19 14:00:00'),
(14, 3, 6, 9, '2024-10-19 09:00:00', '2024-10-19 13:30:00'),
(15, 6, 7, 10, '2024-10-19 14:30:00', '2024-10-19 16:30:00'),
(16, 1, 7, 1, '2024-10-20 05:30:00', '2024-10-20 16:00:00'),
(17, 2, 7, 2, '2024-10-20 06:30:00', '2024-10-20 16:30:00'),
(18, 3, 7, 3, '2024-10-20 07:30:00', '2024-10-20 15:30:00'),
(19, 4, 7, 4, '2024-10-20 09:00:00', '2024-10-20 14:00:00'),
(20, 5, 7, 5, '2024-10-21 08:30:00', '2024-10-21 16:00:00'),
(21, 1, 2, 1, '2024-10-14 08:00:00', '2024-10-14 10:00:00'),
(22, 2, 3, 1, '2024-10-14 10:30:00', '2024-10-14 12:00:00'),
(23, 3, 4, 1, '2024-10-14 12:30:00', '2024-10-14 14:00:00'),
(24, 1, 3, 2, '2024-10-15 09:00:00', '2024-10-15 11:30:00'),
(25, 3, 4, 2, '2024-10-15 12:00:00', '2024-10-15 13:30:00'),
(26, 1, 4, 3, '2024-10-16 07:00:00', '2024-10-16 13:00:00'),
(27, 2, 4, 4, '2024-10-16 09:30:00', '2024-10-16 12:30:00'),
(28, 4, 5, 4, '2024-10-16 13:00:00', '2024-10-16 15:30:00'),
(29, 1, 5, 5, '2024-10-17 06:00:00', '2024-10-17 16:00:00'),
(30, 2, 5, 6, '2024-10-17 08:00:00', '2024-10-17 16:00:00'),
(31, 5, 6, 7, '2024-10-18 10:00:00', '2024-10-18 14:00:00'),
(32, 1, 6, 7, '2024-10-18 06:00:00', '2024-10-18 14:00:00'),
(33, 2, 6, 8, '2024-10-19 08:00:00', '2024-10-19 14:00:00'),
(34, 3, 6, 9, '2024-10-19 09:00:00', '2024-10-19 13:30:00'),
(35, 6, 7, 10, '2024-10-19 14:30:00', '2024-10-19 16:30:00'),
(36, 1, 7, 1, '2024-10-20 05:30:00', '2024-10-20 16:00:00'),
(37, 2, 7, 2, '2024-10-20 06:30:00', '2024-10-20 16:30:00'),
(38, 3, 7, 3, '2024-10-20 07:30:00', '2024-10-20 15:30:00'),
(39, 4, 7, 4, '2024-10-20 09:00:00', '2024-10-20 14:00:00'),
(40, 5, 7, 5, '2024-10-21 08:30:00', '2024-10-21 16:00:00'),
(41, 1, 2, 1, '2024-10-14 08:00:00', '2024-10-14 10:00:00'),
(42, 2, 3, 1, '2024-10-14 10:30:00', '2024-10-14 12:00:00'),
(43, 3, 4, 1, '2024-10-14 12:30:00', '2024-10-14 14:00:00'),
(44, 1, 3, 2, '2024-10-15 09:00:00', '2024-10-15 11:30:00'),
(45, 3, 4, 2, '2024-10-15 12:00:00', '2024-10-15 13:30:00'),
(46, 1, 4, 3, '2024-10-16 07:00:00', '2024-10-16 13:00:00'),
(47, 2, 4, 4, '2024-10-16 09:30:00', '2024-10-16 12:30:00'),
(48, 4, 5, 4, '2024-10-16 13:00:00', '2024-10-16 15:30:00'),
(49, 1, 5, 5, '2024-10-17 06:00:00', '2024-10-17 16:00:00'),
(50, 2, 5, 6, '2024-10-17 08:00:00', '2024-10-17 16:00:00'),
(51, 5, 6, 7, '2024-10-18 10:00:00', '2024-10-18 14:00:00'),
(52, 1, 6, 7, '2024-10-18 06:00:00', '2024-10-18 14:00:00'),
(53, 2, 6, 8, '2024-10-19 08:00:00', '2024-10-19 14:00:00'),
(54, 3, 6, 9, '2024-10-19 09:00:00', '2024-10-19 13:30:00'),
(55, 6, 7, 10, '2024-10-19 14:30:00', '2024-10-19 16:30:00'),
(56, 1, 7, 1, '2024-10-20 05:30:00', '2024-10-20 16:00:00'),
(57, 2, 7, 2, '2024-10-20 06:30:00', '2024-10-20 16:30:00'),
(58, 3, 7, 3, '2024-10-20 07:30:00', '2024-10-20 15:30:00'),
(59, 4, 7, 4, '2024-10-20 09:00:00', '2024-10-20 14:00:00'),
(60, 5, 7, 5, '2024-10-21 08:30:00', '2024-10-21 16:00:00'),
(61, 1, 2, 1, '2024-10-14 08:00:00', '2024-10-14 10:00:00'),
(62, 2, 3, 1, '2024-10-14 10:30:00', '2024-10-14 12:00:00'),
(63, 3, 4, 1, '2024-10-14 12:30:00', '2024-10-14 14:00:00'),
(64, 1, 3, 2, '2024-10-15 09:00:00', '2024-10-15 11:30:00'),
(65, 3, 4, 2, '2024-10-15 12:00:00', '2024-10-15 13:30:00'),
(66, 1, 4, 3, '2024-10-16 07:00:00', '2024-10-16 13:00:00'),
(67, 2, 4, 4, '2024-10-16 09:30:00', '2024-10-16 12:30:00'),
(68, 4, 5, 4, '2024-10-16 13:00:00', '2024-10-16 15:30:00'),
(69, 1, 5, 5, '2024-10-17 06:00:00', '2024-10-17 16:00:00'),
(70, 2, 5, 6, '2024-10-17 08:00:00', '2024-10-17 16:00:00'),
(71, 5, 6, 7, '2024-10-18 10:00:00', '2024-10-18 14:00:00'),
(72, 1, 6, 7, '2024-10-18 06:00:00', '2024-10-18 14:00:00'),
(73, 2, 6, 8, '2024-10-19 08:00:00', '2024-10-19 14:00:00'),
(74, 3, 6, 9, '2024-10-19 09:00:00', '2024-10-19 13:30:00'),
(75, 6, 7, 10, '2024-10-19 14:30:00', '2024-10-19 16:30:00'),
(76, 1, 7, 1, '2024-10-20 05:30:00', '2024-10-20 16:00:00'),
(77, 2, 7, 2, '2024-10-20 06:30:00', '2024-10-20 16:30:00'),
(78, 3, 7, 3, '2024-10-20 07:30:00', '2024-10-20 15:30:00'),
(79, 4, 7, 4, '2024-10-20 09:00:00', '2024-10-20 14:00:00'),
(80, 5, 7, 5, '2024-10-21 08:30:00', '2024-10-21 16:00:00');

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

--
-- Dumping data for table `seats`
--

INSERT INTO `seats` (`seatId`, `seatCode`, `seatTypeId`, `carriageId`) VALUES
(101, 'S01', 1, 1),
(102, 'S02', 1, 1),
(103, 'S03', 1, 1),
(104, 'S04', 1, 1),
(105, 'S05', 1, 1),
(106, 'S06', 1, 1),
(107, 'S07', 1, 1),
(108, 'S08', 1, 1),
(109, 'S09', 1, 1),
(110, 'S10', 1, 1),
(111, 'S11', 1, 1),
(112, 'S12', 1, 1),
(113, 'S13', 1, 1),
(114, 'S14', 1, 1),
(115, 'S15', 1, 1),
(116, 'S16', 1, 1),
(117, 'S17', 1, 1),
(118, 'S18', 1, 1),
(119, 'S19', 1, 1),
(120, 'S20', 1, 1),
(121, 'S01', 1, 2),
(122, 'S02', 1, 2),
(123, 'S03', 1, 2),
(124, 'S04', 1, 2),
(125, 'S05', 1, 2),
(126, 'S06', 1, 2),
(127, 'S07', 1, 2),
(128, 'S08', 1, 2),
(129, 'S09', 1, 2),
(130, 'S10', 1, 2),
(131, 'S11', 1, 2),
(132, 'S12', 1, 2),
(133, 'S13', 1, 2),
(134, 'S14', 1, 2),
(135, 'S15', 1, 2),
(136, 'S16', 1, 2),
(137, 'S17', 1, 2),
(138, 'S18', 1, 2),
(139, 'S19', 1, 2),
(140, 'S20', 1, 2),
(141, 'S01', 1, 3),
(142, 'S02', 1, 3),
(143, 'S03', 1, 3),
(144, 'S04', 1, 3),
(145, 'S05', 1, 3),
(146, 'S06', 1, 3),
(147, 'S07', 1, 3),
(148, 'S08', 1, 3),
(149, 'S09', 1, 3),
(150, 'S10', 1, 3),
(151, 'S11', 1, 3),
(152, 'S12', 1, 3),
(153, 'S13', 1, 3),
(154, 'S14', 1, 3),
(155, 'S15', 1, 3),
(156, 'S16', 1, 3),
(157, 'S17', 1, 3),
(158, 'S18', 1, 3),
(159, 'S19', 1, 3),
(160, 'S20', 1, 3),
(161, 'S01', 1, 4),
(162, 'S02', 1, 4),
(163, 'S03', 1, 4),
(164, 'S04', 1, 4),
(165, 'S05', 1, 4),
(166, 'S06', 1, 4),
(167, 'S07', 1, 4),
(168, 'S08', 1, 4),
(169, 'S09', 1, 4),
(170, 'S10', 1, 4),
(171, 'S11', 1, 4),
(172, 'S12', 1, 4),
(173, 'S13', 1, 4),
(174, 'S14', 1, 4),
(175, 'S15', 1, 4),
(176, 'S16', 1, 4),
(177, 'S17', 1, 4),
(178, 'S18', 1, 4),
(179, 'S19', 1, 4),
(180, 'S20', 1, 4),
(181, 'S01', 1, 5),
(182, 'S02', 1, 5),
(183, 'S03', 1, 5),
(184, 'S04', 1, 5),
(185, 'S05', 1, 5),
(186, 'S06', 1, 5),
(187, 'S07', 1, 5),
(188, 'S08', 1, 5),
(189, 'S09', 1, 5),
(190, 'S10', 1, 5),
(191, 'S11', 1, 5),
(192, 'S12', 1, 5),
(193, 'S13', 1, 5),
(194, 'S14', 1, 5),
(195, 'S15', 1, 5),
(196, 'S16', 1, 5),
(197, 'S17', 1, 5),
(198, 'S18', 1, 5),
(199, 'S19', 1, 5),
(200, 'S20', 1, 5);

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

--
-- Dumping data for table `seattype`
--

INSERT INTO `seattype` (`seatTypeId`, `seatTypeName`, `description`, `price`) VALUES
(1, 'Regular', 'Standard seating with basic comfort.', 200000),
(2, 'Business', 'Comfortable seating with more legroom.', 400000),
(3, 'First Class', 'Premium seating with additional amenities.', 600000),
(4, 'Sleeper', 'Bunk beds for overnight journeys.', 800000),
(5, 'Luxury', 'Exclusive seating with top-notch services.', 1000000),
(6, 'Regular', 'Standard seating with basic comfort.', 200000),
(7, 'Business', 'Comfortable seating with more legroom.', 400000),
(8, 'First Class', 'Premium seating with additional amenities.', 600000),
(9, 'Sleeper', 'Bunk beds for overnight journeys.', 800000),
(10, 'Luxury', 'Exclusive seating with top-notch services.', 1000000);

-- --------------------------------------------------------

--
-- Table structure for table `stations`
--

CREATE TABLE `stations` (
  `stationId` int(11) NOT NULL,
  `stationCode` varchar(10) DEFAULT NULL,
  `stationName` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `statusStation` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `stations`
--

INSERT INTO `stations` (`stationId`, `stationCode`, `stationName`, `image`, `description`, `statusStation`) VALUES
(1, 'SG', 'Saigon Station', 'saigon.jpg', 'Main station in Saigon', 1),
(2, 'DN', 'Danang Station', 'danang.jpg', 'Main station in Danang', 1),
(3, 'HN', 'Hanoi Station', 'hanoi.jpg', 'Main station in Hanoi', 1),
(4, 'NT', 'Nha Trang Station', 'nhatrang.jpg', 'Main station in Nha Trang', 1),
(5, 'QB', 'Quang Binh Station', 'quangbinh.jpg', 'Main station in Quang Binh', 1),
(6, 'HP', 'Hai Phong Station', 'haiphong.jpg', 'Main station in Hai Phong', 1),
(7, 'VL', 'Vinh Long Station', 'vinhlong.jpg', 'Main station in Vinh Long', 1),
(8, 'CT', 'Can Tho Station', 'cantho.jpg', 'Main station in Can Tho', 1),
(9, 'BD', 'Binh Duong Station', 'binhduong.jpg', 'Main station in Binh Duong', 1),
(10, 'CM', 'Ca Mau Station', 'camau.jpg', 'Main station in Ca Mau', 1),
(11, 'SG', 'Saigon Station', 'saigon.jpg', 'Main station in Saigon', 1),
(12, 'DN', 'Danang Station', 'danang.jpg', 'Main station in Danang', 1),
(13, 'HN', 'Hanoi Station', 'hanoi.jpg', 'Main station in Hanoi', 1),
(14, 'NT', 'Nha Trang Station', 'nhatrang.jpg', 'Main station in Nha Trang', 1),
(15, 'QB', 'Quang Binh Station', 'quangbinh.jpg', 'Main station in Quang Binh', 1),
(16, 'HP', 'Hai Phong Station', 'haiphong.jpg', 'Main station in Hai Phong', 1),
(17, 'VL', 'Vinh Long Station', 'vinhlong.jpg', 'Main station in Vinh Long', 1),
(18, 'CT', 'Can Tho Station', 'cantho.jpg', 'Main station in Can Tho', 1),
(19, 'BD', 'Binh Duong Station', 'binhduong.jpg', 'Main station in Binh Duong', 1),
(20, 'CM', 'Ca Mau Station', 'camau.jpg', 'Main station in Ca Mau', 1);

-- --------------------------------------------------------

--
-- Table structure for table `statusroleoperation`
--

CREATE TABLE `statusroleoperation` (
  `statusId` int(11) NOT NULL,
  `statusName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `statusroleoperation`
--

INSERT INTO `statusroleoperation` (`statusId`, `statusName`) VALUES
(1, 'Active'),
(2, 'Inactive'),
(3, 'Pending'),
(4, 'Suspended'),
(5, 'Revoked');

-- --------------------------------------------------------

--
-- Table structure for table `statusseat`
--

CREATE TABLE `statusseat` (
  `statusSeatId` int(11) NOT NULL,
  `statusSeatName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `statusseat`
--

INSERT INTO `statusseat` (`statusSeatId`, `statusSeatName`) VALUES
(1, 'Available'),
(2, 'Reserved'),
(3, 'Booked'),
(4, 'Occupied'),
(5, 'Maintenance'),
(6, 'Cleaning'),
(7, 'Blocked'),
(8, 'VIP'),
(9, 'Special Needs'),
(10, 'Out of Service');

-- --------------------------------------------------------

--
-- Table structure for table `ticketroute`
--

CREATE TABLE `ticketroute` (
  `ticketRouteId` int(11) NOT NULL,
  `ticketId` int(11) DEFAULT NULL,
  `legId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tickets`
--

CREATE TABLE `tickets` (
  `ticketId` int(11) NOT NULL,
  `passengerId` int(11) DEFAULT NULL,
  `trainId` int(11) DEFAULT NULL,
  `stationDepartureId` int(11) DEFAULT NULL,
  `stationArrivalId` int(11) DEFAULT NULL,
  `timeOfBooking` datetime DEFAULT NULL,
  `seatId` int(11) DEFAULT NULL
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
  `description` varchar(255) DEFAULT NULL,
  `statusTrain` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `trains`
--

INSERT INTO `trains` (`trainId`, `trainCode`, `trainName`, `image`, `description`, `statusTrain`) VALUES
(1, 'TN01', 'Train 1', 'train1.jpg', 'High-speed train 1', 1),
(2, 'TN02', 'Train 2', 'train2.jpg', 'High-speed train 2', 1),
(3, 'TN03', 'Train 3', 'train3.jpg', 'High-speed train 3', 1),
(4, 'TN04', 'Train 4', 'train4.jpg', 'High-speed train 4', 1),
(5, 'TN05', 'Train 5', 'train5.jpg', 'High-speed train 5', 1),
(6, 'TN06', 'Train 6', 'train6.jpg', 'High-speed train 6', 1),
(7, 'TN07', 'Train 7', 'train7.jpg', 'High-speed train 7', 1),
(8, 'TN08', 'Train 8', 'train8.jpg', 'High-speed train 8', 1),
(9, 'TN09', 'Train 9', 'train9.jpg', 'High-speed train 9', 1),
(10, 'TN10', 'Train 10', 'train10.jpg', 'High-speed train 10', 1),
(11, 'TN01', 'Train 1', 'train1.jpg', 'High-speed train 1', 1),
(12, 'TN02', 'Train 2', 'train2.jpg', 'High-speed train 2', 1),
(13, 'TN03', 'Train 3', 'train3.jpg', 'High-speed train 3', 1),
(14, 'TN04', 'Train 4', 'train4.jpg', 'High-speed train 4', 1),
(15, 'TN05', 'Train 5', 'train5.jpg', 'High-speed train 5', 1),
(16, 'TN06', 'Train 6', 'train6.jpg', 'High-speed train 6', 1),
(17, 'TN07', 'Train 7', 'train7.jpg', 'High-speed train 7', 1),
(18, 'TN08', 'Train 8', 'train8.jpg', 'High-speed train 8', 1),
(19, 'TN09', 'Train 9', 'train9.jpg', 'High-speed train 9', 1),
(20, 'TN10', 'Train 10', 'train10.jpg', 'High-speed train 10', 1);

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
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`customerId`),
  ADD KEY `roleId` (`roleId`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`employeeId`),
  ADD KEY `roleId` (`roleId`);

--
-- Indexes for table `legs`
--
ALTER TABLE `legs`
  ADD PRIMARY KEY (`legId`),
  ADD KEY `routeId` (`routeId`),
  ADD KEY `stationDepartureId` (`stationDepartureId`),
  ADD KEY `stationArrivalId` (`stationArrivalId`),
  ADD KEY `statusSeatId` (`statusSeatId`),
  ADD KEY `seatId` (`seatId`);

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
  ADD KEY `customerId` (`customerId`),
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
  ADD PRIMARY KEY (`roleId`,`permissionId`,`operationId`),
  ADD KEY `permissionId` (`permissionId`),
  ADD KEY `operationId` (`operationId`),
  ADD KEY `statusId` (`statusId`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`roleId`);

--
-- Indexes for table `routes`
--
ALTER TABLE `routes`
  ADD PRIMARY KEY (`routeId`),
  ADD KEY `stationDepartmentId` (`stationDepartmentId`),
  ADD KEY `stationArrivalId` (`stationArrivalId`),
  ADD KEY `trainId` (`trainId`);

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
-- Indexes for table `ticketroute`
--
ALTER TABLE `ticketroute`
  ADD PRIMARY KEY (`ticketRouteId`),
  ADD KEY `ticketId` (`ticketId`),
  ADD KEY `legId` (`legId`);

--
-- Indexes for table `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`ticketId`),
  ADD KEY `passengerId` (`passengerId`),
  ADD KEY `trainId` (`trainId`),
  ADD KEY `stationDepartureId` (`stationDepartureId`),
  ADD KEY `stationArrivalId` (`stationArrivalId`),
  ADD KEY `seatId` (`seatId`);

--
-- Indexes for table `trains`
--
ALTER TABLE `trains`
  ADD PRIMARY KEY (`trainId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `carriages`
--
ALTER TABLE `carriages`
  MODIFY `carriageId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `carriagetype`
--
ALTER TABLE `carriagetype`
  MODIFY `carriageTypeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `customerId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `employeeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `legs`
--
ALTER TABLE `legs`
  MODIFY `legId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT for table `objects`
--
ALTER TABLE `objects`
  MODIFY `objectId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `operations`
--
ALTER TABLE `operations`
  MODIFY `operationId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `orderId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `passengers`
--
ALTER TABLE `passengers`
  MODIFY `passengerId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `permissions`
--
ALTER TABLE `permissions`
  MODIFY `permissionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `roleId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `routes`
--
ALTER TABLE `routes`
  MODIFY `routeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- AUTO_INCREMENT for table `seats`
--
ALTER TABLE `seats`
  MODIFY `seatId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=201;

--
-- AUTO_INCREMENT for table `seattype`
--
ALTER TABLE `seattype`
  MODIFY `seatTypeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `stations`
--
ALTER TABLE `stations`
  MODIFY `stationId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `statusroleoperation`
--
ALTER TABLE `statusroleoperation`
  MODIFY `statusId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `statusseat`
--
ALTER TABLE `statusseat`
  MODIFY `statusSeatId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `ticketroute`
--
ALTER TABLE `ticketroute`
  MODIFY `ticketRouteId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tickets`
--
ALTER TABLE `tickets`
  MODIFY `ticketId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `trains`
--
ALTER TABLE `trains`
  MODIFY `trainId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `carriages`
--
ALTER TABLE `carriages`
  ADD CONSTRAINT `carriages_ibfk_1` FOREIGN KEY (`carriageTypeId`) REFERENCES `carriagetype` (`carriageTypeId`);

--
-- Constraints for table `customers`
--
ALTER TABLE `customers`
  ADD CONSTRAINT `customers_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `roles` (`roleId`);

--
-- Constraints for table `employees`
--
ALTER TABLE `employees`
  ADD CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `roles` (`roleId`);

--
-- Constraints for table `legs`
--
ALTER TABLE `legs`
  ADD CONSTRAINT `legs_ibfk_1` FOREIGN KEY (`routeId`) REFERENCES `routes` (`routeId`),
  ADD CONSTRAINT `legs_ibfk_2` FOREIGN KEY (`stationDepartureId`) REFERENCES `stations` (`stationId`),
  ADD CONSTRAINT `legs_ibfk_3` FOREIGN KEY (`stationArrivalId`) REFERENCES `stations` (`stationId`),
  ADD CONSTRAINT `legs_ibfk_4` FOREIGN KEY (`statusSeatId`) REFERENCES `statusseat` (`statusSeatId`),
  ADD CONSTRAINT `legs_ibfk_5` FOREIGN KEY (`seatId`) REFERENCES `seats` (`seatId`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customerId`) REFERENCES `customers` (`customerId`),
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
-- Constraints for table `routes`
--
ALTER TABLE `routes`
  ADD CONSTRAINT `routes_ibfk_1` FOREIGN KEY (`stationDepartmentId`) REFERENCES `stations` (`stationId`),
  ADD CONSTRAINT `routes_ibfk_2` FOREIGN KEY (`stationArrivalId`) REFERENCES `stations` (`stationId`),
  ADD CONSTRAINT `routes_ibfk_3` FOREIGN KEY (`trainId`) REFERENCES `trains` (`trainId`);

--
-- Constraints for table `seats`
--
ALTER TABLE `seats`
  ADD CONSTRAINT `seats_ibfk_1` FOREIGN KEY (`seatTypeId`) REFERENCES `seattype` (`seatTypeId`),
  ADD CONSTRAINT `seats_ibfk_2` FOREIGN KEY (`carriageId`) REFERENCES `carriages` (`carriageId`);

--
-- Constraints for table `ticketroute`
--
ALTER TABLE `ticketroute`
  ADD CONSTRAINT `ticketroute_ibfk_1` FOREIGN KEY (`ticketId`) REFERENCES `tickets` (`ticketId`),
  ADD CONSTRAINT `ticketroute_ibfk_2` FOREIGN KEY (`legId`) REFERENCES `legs` (`legId`);

--
-- Constraints for table `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`passengerId`) REFERENCES `passengers` (`passengerId`),
  ADD CONSTRAINT `tickets_ibfk_2` FOREIGN KEY (`trainId`) REFERENCES `trains` (`trainId`),
  ADD CONSTRAINT `tickets_ibfk_3` FOREIGN KEY (`stationDepartureId`) REFERENCES `stations` (`stationId`),
  ADD CONSTRAINT `tickets_ibfk_4` FOREIGN KEY (`stationArrivalId`) REFERENCES `stations` (`stationId`),
  ADD CONSTRAINT `tickets_ibfk_5` FOREIGN KEY (`seatId`) REFERENCES `seats` (`seatId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
