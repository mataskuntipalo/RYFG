-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 08, 2017 at 06:16 PM
-- Server version: 10.1.10-MariaDB
-- PHP Version: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ryfg`
--

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `member_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`member_id`, `username`, `password`) VALUES
(1, 'user001', 'user001'),
(2, 'user002', 'user002'),
(3, 'user003', 'user003'),
(4, 'user004', 'user004'),
(5, 'user005', 'user005'),
(6, 'user006', 'user006');

-- --------------------------------------------------------

--
-- Table structure for table `vdo`
--

CREATE TABLE `vdo` (
  `vdo_id` int(11) NOT NULL,
  `name` text NOT NULL,
  `type` text NOT NULL,
  `position` text NOT NULL,
  `duration` text NOT NULL,
  `calorie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `vdo`
--

INSERT INTO `vdo` (`vdo_id`, `name`, `type`, `position`, `duration`, `calorie`) VALUES
(1, 'Cat stretch', 'Stretching/Cool Down', 'Body', '5 รอบ', 7),
(2, 'Camel', 'Stretching/Cool Down', 'Body', '5 วินาที', 0),
(3, 'Pigeon', 'Stretching/Cool Down', 'Body', 'ข้างละ 5 วินาที', 5),
(4, 'Child', 'Stretching/Cool Down', 'Body', '5 วินาที', 3),
(5, 'Cobra', 'Stretching/Cool Down', 'Body', '5 วินาที', 3),
(6, 'Tricep Stretch', 'Stretching/Cool Down', 'Body', '5 วินาที', 3),
(7, 'Butterfly Stretch', 'Stretching/Cool Down', 'Body', '5 วินาที', 3),
(8, 'Dancer''s Stretch', 'Stretching/Cool Down', 'Body', '5 วินาที', 3),
(9, 'Dynamic Chest Stretch', 'Stretching/Cool Down', 'Body', '', 5),
(10, 'Elbow Circles', 'Stretching/Cool Down', 'Body', '10 รอบ', 5),
(11, '90/90 Hamstring', 'Stretching/Cool Down', 'Body', '5 รอบ', 0),
(12, 'Mountain Climber', 'Warm Up/Cardio', 'Body', '5 รอบ', 0),
(13, 'Spider-Man', 'Warm Up/Cardio', 'Body', '5 รอบ', 0),
(14, 'Jumping Jack', 'Warm Up/Cardio', 'Body', '10 รอบ', 3),
(15, 'Marching', 'Warm Up/Cardio', 'Body', '10 รอบ', 3),
(16, 'High Knees', 'Warm Up/Cardio', 'Body', '10 รอบ', 4),
(17, 'Rotating Toe Touches', 'Warm Up/Cardio', 'Body', '5 รอบ', 5),
(18, 'Walk', 'Warm Up/Cardio', 'Body', '1 นาที', 0),
(19, 'Run', 'Warm Up/Cardio', 'Body', '1 นาที', 0),
(20, 'Half Jacks', 'Warm Up/Cardio', 'Body', '10 ที', 4),
(21, 'Side-to-Side Hops', 'Warm Up/Cardio', 'Body', '10 รอบ', 3),
(22, 'Hops On The Spot', 'Warm Up/Cardio', 'Body', '10 รอบ', 11),
(23, 'Squat', 'Strength', 'Leg', '5 รอบ', 3),
(24, 'Leg Lunge', 'Strength', 'Leg', '3 รอบ', 5),
(25, 'Leg Lifts Side', 'Strength', 'Leg', '5 ที', 0),
(26, 'Squat Jumps', 'Strength', 'Leg', '5 รอบ', 5),
(27, 'Bicycle Crunch', 'Strength', 'Leg', '5 รอบ', 0),
(28, 'Crunches', 'Strength', 'Abs', '5 ที', 0),
(29, 'Sit Up', 'Strength', 'Abs', '5 ที', 0),
(30, 'Leg Raise', 'Strength', 'Abs', '5 ที', 0),
(31, 'Russian Twist', 'Strength', 'Abs', '5 รอบ', 0),
(32, 'Criss Cross', 'Strength', 'Abs', '5 รอบ', 0),
(33, 'Deadbugs', 'Strength', 'Abs', '5 ที', 0),
(34, 'Knee Tuck  ', 'Strength', 'Abs', '5 รอบ', 0),
(35, 'Scissor Kicks', 'Strength', 'Abs', '5 รอบ', 0),
(36, 'Side Leg Drop', 'Strength', 'Abs', '5 รอบ', 0),
(37, 'Back Extension Prone', 'Strength', 'Back', '5 ที', 0),
(38, 'Plank With Alternative Hip Extension', 'Strength', 'Back', '5 รอบ', 0),
(39, 'Push Up', 'Strength', 'Chest', '5 ที', 0),
(40, 'Diamond Push Up', 'Strength', 'Chest', '5 ที', 0),
(41, 'Modified Push Up', 'Strength', 'Chest', '5 ที', 0),
(42, 'Glute Bridge', 'Strength', 'Glute', '5 ที', 0),
(43, 'Fire Hydrants', 'Strength', 'Glute', '5 ที', 0),
(44, 'Straight Leg Kickbacks', 'Strength', 'Glute', '5 รอบ', 0),
(45, 'Skater Hop', 'Strength', 'Glute', '5 รอบ', 10);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`member_id`);

--
-- Indexes for table `vdo`
--
ALTER TABLE `vdo`
  ADD PRIMARY KEY (`vdo_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `member_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `vdo`
--
ALTER TABLE `vdo`
  MODIFY `vdo_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
