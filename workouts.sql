-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 10, 2021 at 09:33 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `workout_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `workouts`
--

CREATE TABLE `workouts` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `duration` text NOT NULL,
  `views` text NOT NULL,
  `tags` text NOT NULL,
  `description` text NOT NULL,
  `video_url` text NOT NULL,
  `category` text NOT NULL,
  `type` text NOT NULL,
  `trainer` text NOT NULL,
  `difficulty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `workouts`
--

INSERT INTO `workouts` (`id`, `name`, `duration`, `views`, `tags`, `description`, `video_url`, `category`, `type`, `trainer`, `difficulty`) VALUES
(1, 'Cardio: Knock Out', '30', '14.5k', 'Boxing,Cardio,Olivia Corney,Intermediate,0-10 minutes,Easy Video', 'In this course i will take you to through the process of becoming fitness expert step by step. you will learn everything you need to know about the fundamentals of a good training programs and how to design your very own for you and your clients.', 'https://drive.google.com/file/d/1GZxjroAvR-3NPKoK1OrKLkWhRZuJ7p5T/view', 'Cardio', 'Boxing', 'Taylor', 6),
(2, 'Cardio: Knock Out', '30', '25.6k', 'Boxing,Cardio,Olivia Corney,Intermediate,0-10 minutes,Easy Video', 'In this course i will take you to through the process of becoming fitness expert step by step. you will learn everything you need to know about the fundamentals of a good training programs and how to design your very own for you and your clients.', 'https://drive.google.com/file/d/1wI6E78_5otytN_nc7Isi7BFhSroTf6k9/view?usp=sharing', 'Cardio', 'Boxing', 'Kettelbel Taylor', 8),
(3, 'Cardio: Knock Out', '30', '28.5k', 'Boxing,Cardio,Olivia Corney,Intermediate,0-10 minutes,Easy Video', 'In this course i will take you to through the process of becoming fitness expert step by step. you will learn everything you need to know about the fundamentals of a good training programs and how to design your very own for you and your clients.', 'https://drive.google.com/file/d/1D3XBx1lQngprfb48D3bGNDndFELqju_p/view?usp=sharing', 'Cardio', 'Boxing', 'Juli', 8),
(4, 'Cardio: Knock Out', '30', '30.4k', 'Boxing,Cardio,Olivia Corney,Intermediate,0-10 minutes,Easy Video', 'In this course i will take you to through the process of becoming fitness expert step by step. you will learn everything you need to know about the fundamentals of a good training programs and how to design your very own for you and your clients.', 'https://drive.google.com/file/d/13PjDhFlQInfUafEqVWreKHjWZvy3Ighe/view?usp=sharing', 'Cardio', 'Boxing', 'Taylor', 7),
(5, 'Cardio: Knock Out', '30', '35.6k', 'Boxing,Cardio,Olivia Corney,Intermediate,0-10 minutes,Easy Video', 'In this course i will take you to through the process of becoming fitness expert step by step. you will learn everything you need to know about the fundamentals of a good training programs and how to design your very own for you and your clients.', 'https://drive.google.com/file/d/1XIWvhnaXmZ9hpiICcUsOKotOOKJcrjl1/view?usp=sharing', 'Cardio', 'Boxing', 'Anna', 8);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `workouts`
--
ALTER TABLE `workouts`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `workouts`
--
ALTER TABLE `workouts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
