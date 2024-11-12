-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 12, 2024 at 11:55 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bookingtrain1`
--

-- --------------------------------------------------------

--
-- Table structure for table `bookings`
--

CREATE TABLE `bookings` (
  `bookingId` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `employeeId` int(11) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `dateBooking` datetime DEFAULT NULL,
  `scheduleId` int(11) DEFAULT NULL,
  `statusBooking` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `coaches`
--

CREATE TABLE `coaches` (
  `coacheId` int(11) NOT NULL,
  `coacheName` varchar(255) DEFAULT NULL,
  `statusCoache` int(11) DEFAULT NULL,
  `trainId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `coaches`
--

INSERT INTO `coaches` (`coacheId`, `coacheName`, `statusCoache`, `trainId`) VALUES
(1, 'Toa C+', 0, 1),
(2, 'Toa B\r\n', 1, 2),
(3, 'Toa C', 1, 18);

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `employeeId` int(11) NOT NULL,
  `employeeName` varchar(255) DEFAULT NULL,
  `dateOfBirth` date DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`employeeId`, `employeeName`, `dateOfBirth`, `address`, `phone`, `userId`) VALUES
(1, '1', '2024-11-16', '1', '1', 1),
(2, '2', '2024-11-13', '2', '2', 1),
(3, '1', '2024-11-24', '1', '1', 1),
(4, '1', '2024-11-21', '123', '123', 7),
(5, '141414141414', '2024-11-24', '14141', '14141414', 5),
(6, '141414141414', '2024-11-24', '14141', '14141414', 11),
(7, '141414141414', '2024-11-24', '14141', '14141414', 1),
(8, '141414141414', '2024-11-24', '14141', '14141414', 7);

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

CREATE TABLE `images` (
  `Id` int(11) NOT NULL,
  `filePath` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `objects`
--

CREATE TABLE `objects` (
  `objectId` int(11) NOT NULL,
  `objectName` varchar(25) DEFAULT NULL,
  `price` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `objects`
--

INSERT INTO `objects` (`objectId`, `objectName`, `price`) VALUES
(1, 'Nguoi gia', 70),
(2, 'Nguoi tre', 40);

-- --------------------------------------------------------

--
-- Table structure for table `operations`
--

CREATE TABLE `operations` (
  `operationId` int(11) NOT NULL,
  `operationName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `operations`
--

INSERT INTO `operations` (`operationId`, `operationName`) VALUES
(1, 'Xem'),
(2, 'Thêm'),
(3, 'Xóa'),
(4, 'Sửa');

-- --------------------------------------------------------

--
-- Table structure for table `passengers`
--

CREATE TABLE `passengers` (
  `passengerId` int(11) NOT NULL,
  `passengerName` varchar(255) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `dateOfBirth` datetime DEFAULT NULL,
  `identityId` int(11) DEFAULT NULL,
  `objectId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `passengers`
--

INSERT INTO `passengers` (`passengerId`, `passengerName`, `phone`, `dateOfBirth`, `identityId`, `objectId`) VALUES
(2, '1', '1', '2024-11-27 11:24:21', 1, 1),
(3, '1', '2', '2024-11-03 11:41:56', 3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `permissions`
--

CREATE TABLE `permissions` (
  `permissionId` int(11) NOT NULL,
  `permissionName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `permissions`
--

INSERT INTO `permissions` (`permissionId`, `permissionName`) VALUES
(1, 'Quản lý vée'),
(2, 'Quản lý ghế'),
(3, 'Quản lý người dùng');

-- --------------------------------------------------------

--
-- Table structure for table `roleoperation`
--

CREATE TABLE `roleoperation` (
  `roleId` int(11) NOT NULL,
  `permissionId` int(11) NOT NULL,
  `operationId` int(11) NOT NULL,
  `statusId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `roleoperation`
--

INSERT INTO `roleoperation` (`roleId`, `permissionId`, `operationId`, `statusId`) VALUES
(1, 1, 1, 1),
(2, 1, 2, 1),
(3, 1, 2, 1),
(5, 2, 1, 1),
(1, 1, 2, 2),
(1, 2, 2, 2),
(3, 2, 1, 2),
(6, 3, 4, 2);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `roleId` int(11) NOT NULL,
  `roleName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`roleId`, `roleName`) VALUES
(1, 'User'),
(2, '1'),
(3, '2'),
(4, '3'),
(5, '1123'),
(6, '123');

-- --------------------------------------------------------

--
-- Table structure for table `routes`
--

CREATE TABLE `routes` (
  `routeId` int(11) NOT NULL,
  `trainId` int(11) DEFAULT NULL,
  `routeName` varchar(255) DEFAULT NULL,
  `stationDepartureId` int(11) DEFAULT NULL,
  `stationArrivalId` int(11) DEFAULT NULL,
  `statusRoute` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `schedules`
--

CREATE TABLE `schedules` (
  `scheduleId` int(11) NOT NULL,
  `trainId` int(11) DEFAULT NULL,
  `routeId` int(11) DEFAULT NULL,
  `arrivalTime` datetime DEFAULT NULL,
  `departureTime` datetime DEFAULT NULL,
  `statusSchedule` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `seats`
--

CREATE TABLE `seats` (
  `seatId` int(11) NOT NULL,
  `coacheId` int(11) DEFAULT NULL,
  `seatTypeId` int(11) DEFAULT NULL,
  `seatNumber` varchar(255) DEFAULT NULL,
  `coachId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `seats`
--

INSERT INTO `seats` (`seatId`, `coacheId`, `seatTypeId`, `seatNumber`, `coachId`) VALUES
(1, 1, 3, '1', NULL),
(2, 2, 2, '1123123', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `seattypes`
--

CREATE TABLE `seattypes` (
  `seatTypeId` int(11) NOT NULL,
  `price` double DEFAULT NULL,
  `seatTypeName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `seattypes`
--

INSERT INTO `seattypes` (`seatTypeId`, `price`, `seatTypeName`) VALUES
(1, 10, '334'),
(2, 50, 'Vip'),
(3, 10, '33'),
(6, 10, '3'),
(13, 1, 'Ghế thường');

-- --------------------------------------------------------

--
-- Table structure for table `stations`
--

CREATE TABLE `stations` (
  `stationId` int(11) NOT NULL,
  `stationName` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) NOT NULL,
  `stationCode` varchar(255) NOT NULL,
  `statusStation` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `stations`
--

INSERT INTO `stations` (`stationId`, `stationName`, `description`, `image`, `stationCode`, `statusStation`) VALUES
(1, '1', '1', 'HCM_wallpaper1.jpg', '1', 1),
(2, '1233333333333333', '1231231', 'HCM_wallpaper1.jpg', '1', 1),
(3, '1313123123123213', NULL, 'HCM_wallpaper1.jpg', '1', 1),
(4, '1313', NULL, 'HCM_wallpaper1.jpg', '1313', 1),
(5, '444', '444', 'HCM_wallpaper1.jpg', '4444', 0);

-- --------------------------------------------------------

--
-- Table structure for table `statusroleoperation`
--

CREATE TABLE `statusroleoperation` (
  `statusId` int(11) NOT NULL,
  `statusName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `statusroleoperation`
--

INSERT INTO `statusroleoperation` (`statusId`, `statusName`) VALUES
(1, 'On'),
(2, 'Off');

-- --------------------------------------------------------

--
-- Table structure for table `tickets`
--

CREATE TABLE `tickets` (
  `ticketId` int(11) NOT NULL,
  `bookingId` int(11) DEFAULT NULL,
  `passengerId` int(11) DEFAULT NULL,
  `seatId` int(11) DEFAULT NULL,
  `isActive` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `trains`
--

CREATE TABLE `trains` (
  `trainId` int(11) NOT NULL,
  `trainName` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) NOT NULL,
  `statusTrain` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `trains`
--

INSERT INTO `trains` (`trainId`, `trainName`, `description`, `image`, `statusTrain`) VALUES
(1, '1', 'hâha', 'HCM_wallpaper1.jpg', 1),
(2, 'Thịnh', 'Tàu provip số 1 thế giới. đi với 2000km/s', 'HCM_wallpaper1.jpg', 0),
(14, '1', '1', 'HCM_wallpaper1.jpg', 1),
(15, '1', '1', 'HCM_wallpaper1.jpg', 1),
(16, '1', '1', 'HCM_wallpaper1.jpg', 1),
(17, '1', '1', 'HCM_wallpaper1.jpg', 1),
(18, '1123123', '11123123123', 'HCM_wallpaper1.jpg', 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userId` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `roleID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userId`, `email`, `password`, `username`, `roleID`) VALUES
(1, '123@gmail.com', '123', '123', 3),
(2, '1@gmail.com', '1', '1', 4),
(4, '2@gmail.com', '2', '2', 1),
(5, '3@gmail.com', '3', '3', 1),
(6, '4@gmail.com', '4', '4', 1),
(7, '5@gmail.com', '5', '5', 1),
(9, '12322@gmail.com', '123', '1233', 3),
(10, '1414@gmail.com', '123', '4141', 1),
(11, '1231313@gmail.com', '1', '1', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bookings`
--
ALTER TABLE `bookings`
  ADD PRIMARY KEY (`bookingId`),
  ADD KEY `userId` (`userId`),
  ADD KEY `employeeId` (`employeeId`),
  ADD KEY `scheduleId` (`scheduleId`);

--
-- Indexes for table `coaches`
--
ALTER TABLE `coaches`
  ADD PRIMARY KEY (`coacheId`),
  ADD KEY `trainId` (`trainId`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`employeeId`),
  ADD KEY `roleId` (`userId`);

--
-- Indexes for table `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`Id`);

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
-- Indexes for table `passengers`
--
ALTER TABLE `passengers`
  ADD PRIMARY KEY (`passengerId`),
  ADD KEY `FK8fke2l0oradn5yyqrq5uxpdpd` (`objectId`);

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
  ADD KEY `roleoperation_ibfk_2` (`permissionId`),
  ADD KEY `roleoperation_ibfk_3` (`operationId`),
  ADD KEY `roleoperation_ibfk_4` (`statusId`);

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
  ADD KEY `trainId` (`trainId`),
  ADD KEY `FK98e52pe83jl269upp8qy3eyw7` (`stationArrivalId`),
  ADD KEY `FKk12tea84w96jkgbba6yye1nbe` (`stationDepartureId`);

--
-- Indexes for table `schedules`
--
ALTER TABLE `schedules`
  ADD PRIMARY KEY (`scheduleId`),
  ADD KEY `trainId` (`trainId`),
  ADD KEY `FK7o59nkqglakwmt6dyl8fk3ktl` (`routeId`);

--
-- Indexes for table `seats`
--
ALTER TABLE `seats`
  ADD PRIMARY KEY (`seatId`),
  ADD UNIQUE KEY `coachId` (`coacheId`),
  ADD KEY `seats_ibfk_1` (`seatTypeId`);

--
-- Indexes for table `seattypes`
--
ALTER TABLE `seattypes`
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
-- Indexes for table `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`ticketId`),
  ADD KEY `bookingId` (`bookingId`),
  ADD KEY `passengerId` (`passengerId`);

--
-- Indexes for table `trains`
--
ALTER TABLE `trains`
  ADD PRIMARY KEY (`trainId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userId`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `roleId` (`roleID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bookings`
--
ALTER TABLE `bookings`
  MODIFY `bookingId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `coaches`
--
ALTER TABLE `coaches`
  MODIFY `coacheId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `employeeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `images`
--
ALTER TABLE `images`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `objects`
--
ALTER TABLE `objects`
  MODIFY `objectId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `operations`
--
ALTER TABLE `operations`
  MODIFY `operationId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `passengers`
--
ALTER TABLE `passengers`
  MODIFY `passengerId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `permissions`
--
ALTER TABLE `permissions`
  MODIFY `permissionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `roleId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `routes`
--
ALTER TABLE `routes`
  MODIFY `routeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `schedules`
--
ALTER TABLE `schedules`
  MODIFY `scheduleId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `seats`
--
ALTER TABLE `seats`
  MODIFY `seatId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `seattypes`
--
ALTER TABLE `seattypes`
  MODIFY `seatTypeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `stations`
--
ALTER TABLE `stations`
  MODIFY `stationId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `statusroleoperation`
--
ALTER TABLE `statusroleoperation`
  MODIFY `statusId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tickets`
--
ALTER TABLE `tickets`
  MODIFY `ticketId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `trains`
--
ALTER TABLE `trains`
  MODIFY `trainId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bookings`
--
ALTER TABLE `bookings`
  ADD CONSTRAINT `bookings_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`),
  ADD CONSTRAINT `bookings_ibfk_2` FOREIGN KEY (`employeeId`) REFERENCES `employees` (`employeeId`),
  ADD CONSTRAINT `bookings_ibfk_3` FOREIGN KEY (`scheduleId`) REFERENCES `schedules` (`scheduleId`);

--
-- Constraints for table `coaches`
--
ALTER TABLE `coaches`
  ADD CONSTRAINT `coaches_ibfk_1` FOREIGN KEY (`trainId`) REFERENCES `trains` (`trainId`);

--
-- Constraints for table `employees`
--
ALTER TABLE `employees`
  ADD CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `passengers`
--
ALTER TABLE `passengers`
  ADD CONSTRAINT `FK8fke2l0oradn5yyqrq5uxpdpd` FOREIGN KEY (`objectId`) REFERENCES `objects` (`objectId`),
  ADD CONSTRAINT `passengers_ibfk_1` FOREIGN KEY (`objectId`) REFERENCES `operations` (`operationId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `roleoperation`
--
ALTER TABLE `roleoperation`
  ADD CONSTRAINT `roleoperation_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `roles` (`roleID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `roleoperation_ibfk_2` FOREIGN KEY (`permissionId`) REFERENCES `permissions` (`permissionId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `roleoperation_ibfk_3` FOREIGN KEY (`operationId`) REFERENCES `operations` (`operationId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `roleoperation_ibfk_4` FOREIGN KEY (`statusId`) REFERENCES `statusroleoperation` (`statusId`);

--
-- Constraints for table `routes`
--
ALTER TABLE `routes`
  ADD CONSTRAINT `FK98e52pe83jl269upp8qy3eyw7` FOREIGN KEY (`stationArrivalId`) REFERENCES `stations` (`stationId`),
  ADD CONSTRAINT `FKk12tea84w96jkgbba6yye1nbe` FOREIGN KEY (`stationDepartureId`) REFERENCES `stations` (`stationId`),
  ADD CONSTRAINT `routes_ibfk_1` FOREIGN KEY (`trainId`) REFERENCES `trains` (`trainId`);

--
-- Constraints for table `schedules`
--
ALTER TABLE `schedules`
  ADD CONSTRAINT `FK7o59nkqglakwmt6dyl8fk3ktl` FOREIGN KEY (`routeId`) REFERENCES `routes` (`routeId`),
  ADD CONSTRAINT `schedules_ibfk_1` FOREIGN KEY (`trainId`) REFERENCES `trains` (`trainId`);

--
-- Constraints for table `seats`
--
ALTER TABLE `seats`
  ADD CONSTRAINT `seats_ibfk_1` FOREIGN KEY (`seatTypeId`) REFERENCES `seattypes` (`seatTypeId`),
  ADD CONSTRAINT `seats_ibfk_2` FOREIGN KEY (`coacheId`) REFERENCES `coaches` (`coacheId`);

--
-- Constraints for table `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`bookingId`) REFERENCES `bookings` (`bookingId`),
  ADD CONSTRAINT `tickets_ibfk_2` FOREIGN KEY (`passengerId`) REFERENCES `passengers` (`passengerId`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`roleID`) REFERENCES `roles` (`roleID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
