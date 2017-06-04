-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 04, 2017 at 04:07 PM
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
-- Table structure for table `exercise`
--

CREATE TABLE `exercise` (
  `exercise_id` int(11) NOT NULL,
  `day` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  `vdo_id` text NOT NULL,
  `calorieInDay` int(11) NOT NULL,
  `calorieTotal` int(11) NOT NULL,
  `note` text NOT NULL,
  `time` text NOT NULL,
  `check_state_workout` int(11) NOT NULL,
  `member_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `exercise`
--

INSERT INTO `exercise` (`exercise_id`, `day`, `month`, `year`, `vdo_id`, `calorieInDay`, `calorieTotal`, `note`, `time`, `check_state_workout`, `member_id`) VALUES
(7, 6, 5, 2017, 'ยังไม่ได้เลือกท่าออกกำลังกาย', 0, 256, 'Not add note', '-', 0, 3),
(8, 7, 5, 2017, 'ยังไม่ได้เลือกท่าออกกำลังกาย', 0, 256, 'Not add note', '-', 0, 3),
(9, 8, 5, 2017, 'ยังไม่ได้เลือกท่าออกกำลังกาย', 0, 256, 'Not add note', '-', 0, 3),
(10, 9, 5, 2017, 'ยังไม่ได้เลือกท่าออกกำลังกาย', 0, 256, 'Not add note', '-', 0, 3),
(11, 10, 5, 2017, 'ยังไม่ได้เลือกท่าออกกำลังกาย', 0, 256, 'Not add note', '-', 0, 3),
(12, 11, 5, 2017, 'ยังไม่ได้เลือกท่าออกกำลังกาย', 0, 256, 'Not add note', '-', 0, 3);

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `member_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `type` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`member_id`, `username`, `password`, `type`) VALUES
(1, 'siriwan@hotmail.com', 'siriwan', ''),
(2, 'papang@hotmail.com', 'papang', ''),
(3, 'user003', 'user003', 'ลดน้ำหนัก'),
(4, 'user004', 'user004', 'ลดน้ำหนัก'),
(5, 'user005', 'user005', 'ลดน้ำหนัก'),
(6, 'user006', 'user006', 'เสริมสร้างกล้ามเนื้อ'),
(7, 'user007', 'user007', 'เสริมสร้างกล้ามเนื้อ'),
(8, 'ss2@ddsasa.com', 'ss2@ddsasa.com', ''),
(9, 'dwdwa@dawd', '1234', ''),
(10, 'ewq@dwad', '1234', ''),
(11, '123@dwa', 'dawd', ''),
(12, 'assd', 'asdsad', ''),
(13, 'assd', 'asdsad', ''),
(14, 'assd', 'asdsad', ''),
(15, 'e21e1@e1e1', 'e12e', ''),
(16, 'assd', '231', ''),
(17, 'assd', '2111', ''),
(18, 'assd11', '211122222', ''),
(19, 'assd11', '211122222aaa', ''),
(20, 'gghh@jjj', 'aaaa', ''),
(21, 'ja@ddd.com', '1234', '');

-- --------------------------------------------------------

--
-- Table structure for table `personal`
--

CREATE TABLE `personal` (
  `personal_id` int(11) NOT NULL,
  `f_name` varchar(30) NOT NULL,
  `l_name` varchar(30) NOT NULL,
  `age` int(11) NOT NULL,
  `gender` int(11) NOT NULL,
  `weight` double NOT NULL,
  `height` double NOT NULL,
  `member_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `personal`
--

INSERT INTO `personal` (`personal_id`, `f_name`, `l_name`, `age`, `gender`, `weight`, `height`, `member_id`) VALUES
(6, 'aa', 'aa', 12, 1, 11, 11, 3),
(7, 'aa', 'aa', 12, 1, 11, 11, 19),
(8, 'ไไไ', 'ไไไไ', 1, 1, 4, 1, 20),
(9, 'sss', 'sss', 12, 0, 123, 21, 0),
(10, 'aa', 'bb', 12, 1, 2, 123, 4),
(11, 'เมธัส', 'ขันติพะโฃ', 21, 0, 65, 178, 9);

-- --------------------------------------------------------

--
-- Table structure for table `program`
--

CREATE TABLE `program` (
  `program_id` int(11) NOT NULL,
  `typeGoal` int(11) NOT NULL,
  `weightGoal` int(11) NOT NULL,
  `totalCalorie` int(11) NOT NULL,
  `kgPerWeek` int(11) NOT NULL,
  `year_date_begin` int(11) NOT NULL,
  `month_date_begin` int(11) NOT NULL,
  `day_date_begin` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `percentFat` int(11) NOT NULL,
  `programName` varchar(50) NOT NULL,
  `member_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `program`
--

INSERT INTO `program` (`program_id`, `typeGoal`, `weightGoal`, `totalCalorie`, `kgPerWeek`, `year_date_begin`, `month_date_begin`, `day_date_begin`, `status`, `percentFat`, `programName`, `member_id`) VALUES
(2, 1, 22, 11, 1, 1, 1, 1, 1, 20, 'dddd', 4),
(38, 0, 5, 4000, 1540, 0, 1, 0, 1, 0, 'อยากผอม', 3);

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
  `calorie` int(11) NOT NULL,
  `img` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `vdo`
--

INSERT INTO `vdo` (`vdo_id`, `name`, `type`, `position`, `duration`, `calorie`, `img`) VALUES
(1, 'Cat stretch', 'Stretching/Cool Down', 'Body', '5 รอบ', 7, 'image/Cat stretch.jpg'),
(2, 'Camel', 'Stretching/Cool Down', 'Body', '5 วินาที', 8, 'image/Camel.jpg'),
(3, 'Pigeon', 'Stretching/Cool Down', 'Body', 'ข้างละ 5 วินาที', 5, ''),
(4, 'Child', 'Stretching/Cool Down', 'Body', '5 วินาที', 3, ''),
(5, 'Cobra', 'Stretching/Cool Down', 'Body', '5 วินาที', 3, ''),
(6, 'Tricep Stretch', 'Stretching/Cool Down', 'Body', '5 วินาที', 3, ''),
(7, 'Butterfly Stretch', 'Stretching/Cool Down', 'Body', '5 วินาที', 3, ''),
(8, 'Dancer''s Stretch', 'Stretching/Cool Down', 'Body', '5 วินาที', 3, ''),
(9, 'Dynamic Chest Stretch', 'Stretching/Cool Down', 'Body', '5 ที', 5, ''),
(10, 'Elbow Circles', 'Stretching/Cool Down', 'Body', '10 รอบ', 5, ''),
(11, '90-90 Hamstring', 'Stretching/Cool Down', 'Body', '5 รอบ', 10, ''),
(13, 'Spider-Man', 'Warm Up/Cardio', 'Body', '5 รอบ', 10, ''),
(14, 'Jumping Jack', 'Warm Up/Cardio', 'Body', '10 รอบ', 6, ''),
(15, 'Marching', 'Warm Up/Cardio', 'Body', '10 รอบ', 3, ''),
(16, 'High Knees', 'Warm Up/Cardio', 'Body', '10 รอบ', 4, ''),
(17, 'Rotating Toe Touches', 'Warm Up/Cardio', 'Body', '5 รอบ', 5, ''),
(18, 'Walk', 'Warm Up/Cardio', 'Body', '1 นาที', 10, ''),
(19, 'Run', 'Warm Up/Cardio', 'Body', '1 นาที', 15, ''),
(20, 'Half Jacks', 'Warm Up/Cardio', 'Body', '10 ที', 4, ''),
(21, 'Side-to-Side Hops', 'Warm Up/Cardio', 'Body', '10 รอบ', 8, ''),
(23, 'Squat', 'Strength', 'Leg', '5 รอบ', 5, ''),
(24, 'Leg Lunge', 'Strength', 'Leg', '3 รอบ', 5, ''),
(25, 'Leg Lifts Side', 'Strength', 'Leg', '5 ที', 10, ''),
(26, 'Squat Jumps', 'Strength', 'Leg', '5 รอบ', 8, ''),
(27, 'Bicycle Crunch', 'Strength', 'Leg', '5 รอบ', 12, ''),
(28, 'Crunches', 'Strength', 'Abs', '5 ที', 8, ''),
(29, 'Sit Up', 'Strength', 'Abs', '5 ที', 10, ''),
(30, 'Leg Raise', 'Strength', 'Abs', '5 ที', 10, ''),
(31, 'Russian Twist', 'Strength', 'Abs', '5 รอบ', 8, ''),
(32, 'Criss Cross', 'Strength', 'Abs', '5 รอบ', 13, ''),
(33, 'Deadbugs', 'Strength', 'Abs', '5 ที', 10, ''),
(34, 'Knee Tuck', 'Strength', 'Abs', '5 รอบ', 8, ''),
(35, 'Scissor Kicks', 'Strength', 'Abs', '5 รอบ', 14, ''),
(36, 'Side Leg Drop', 'Strength', 'Abs', '5 รอบ', 8, ''),
(37, 'Back Extension Prone', 'Strength', 'Back', '5 ที', 8, ''),
(38, 'Plank With Alternative Hip Extension', 'Strength', 'Back', '5 รอบ', 10, ''),
(39, 'Push Up', 'Strength', 'Chest', '5 ที', 15, ''),
(40, 'Diamond Push Up', 'Strength', 'Chest', '5 ที', 13, ''),
(41, 'Modified Push Up', 'Strength', 'Chest', '5 ที', 15, ''),
(42, 'Glute Bridge', 'Strength', 'Glute', '5 ที', 10, ''),
(43, 'Fire Hydrants', 'Strength', 'Glute', '5 ที', 10, ''),
(44, 'Straight Leg Kickbacks', 'Strength', 'Glute', '5 รอบ', 10, ''),
(45, 'Skater Hop', 'Strength', 'Glute', '5 รอบ', 10, '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `exercise`
--
ALTER TABLE `exercise`
  ADD PRIMARY KEY (`exercise_id`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`member_id`);

--
-- Indexes for table `personal`
--
ALTER TABLE `personal`
  ADD PRIMARY KEY (`personal_id`);

--
-- Indexes for table `program`
--
ALTER TABLE `program`
  ADD PRIMARY KEY (`program_id`);

--
-- Indexes for table `vdo`
--
ALTER TABLE `vdo`
  ADD PRIMARY KEY (`vdo_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `exercise`
--
ALTER TABLE `exercise`
  MODIFY `exercise_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `member_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `personal`
--
ALTER TABLE `personal`
  MODIFY `personal_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `program`
--
ALTER TABLE `program`
  MODIFY `program_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;
--
-- AUTO_INCREMENT for table `vdo`
--
ALTER TABLE `vdo`
  MODIFY `vdo_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
