-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Aug 14, 2018 at 01:19 PM
-- Server version: 10.1.34-MariaDB-0ubuntu0.18.04.1
-- PHP Version: 7.2.7-0ubuntu0.18.04.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `futurama_tetris`
--

-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE `answer` (
  `id` int(11) NOT NULL,
  `team` text COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `answer` text COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `correct` tinyint(4) NOT NULL,
  `timestamp` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;


--
-- Table structure for table `correct_answers`
--

CREATE TABLE `correct_answers` (
  `id` int(11) NOT NULL,
  `answer` text COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `tetramino` char(1) COLLATE utf8mb4_unicode_520_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Dumping data for table `correct_answers`
--

INSERT INTO `correct_answers` (`id`, `answer`, `tetramino`) VALUES
(1, '422901', 'Z'),
(2, '903157', 'Z'),
(3, '544249', 'Z'),
(4, '741363', 'Z'),
(5, '999200', 'Z'),
(6, '691542', 'Z'),
(7, '552698', 'Z'),
(8, '703844', 'Z'),
(9, '213938', 'Z'),
(10, '361382', 'Z'),
(11, '321501', 'Z'),
(12, '248480', 'Z'),
(13, '496641', 'Z'),
(14, '546882', 'Z'),
(15, '738816', 'Z'),
(16, '779942', 'Z'),
(17, '962937', 'Z'),
(18, '367304', 'Z'),
(19, '154698', 'Z'),
(20, '763213', 'Z'),
(21, '447202', 'S'),
(22, '540066', 'S'),
(23, '798432', 'S'),
(24, '811284', 'S'),
(25, '372707', 'S'),
(26, '443510', 'S'),
(27, '569651', 'S'),
(28, '623846', 'S'),
(29, '435441', 'S'),
(30, '249634', 'S'),
(31, '637447', 'S'),
(32, '889022', 'S'),
(33, '920159', 'S'),
(34, '406628', 'S'),
(35, '815424', 'S'),
(36, '203032', 'S'),
(37, '598342', 'S'),
(38, '208975', 'S'),
(39, '836568', 'S'),
(40, '965514', 'S'),
(41, '353206', 'T'),
(42, '215393', 'T'),
(43, '947521', 'T'),
(44, '506791', 'T'),
(45, '545732', 'T'),
(46, '936935', 'T'),
(47, '894529', 'T'),
(48, '123034', 'T'),
(49, '970507', 'T'),
(50, '584954', 'T'),
(51, '729314', 'T'),
(52, '838113', 'T'),
(53, '245463', 'T'),
(54, '677265', 'T'),
(55, '123125', 'T'),
(56, '321022', 'T'),
(57, '538191', 'T'),
(58, '839727', 'T'),
(59, '314587', 'T'),
(60, '351095', 'T'),
(61, '906058', 'L'),
(62, '423589', 'L'),
(63, '972761', 'L'),
(64, '110167', 'L'),
(65, '374231', 'L'),
(66, '160160', 'L'),
(67, '618017', 'L'),
(68, '238093', 'L'),
(69, '500028', 'L'),
(70, '387441', 'L'),
(71, '281495', 'L'),
(72, '574946', 'L'),
(73, '112668', 'L'),
(74, '913576', 'L'),
(75, '257009', 'L'),
(76, '856091', 'L'),
(77, '824371', 'L'),
(78, '592516', 'L'),
(79, '304254', 'L'),
(80, '435391', 'L'),
(81, '802102', 'J'),
(82, '291859', 'J'),
(83, '471476', 'J'),
(84, '898562', 'J'),
(85, '817229', 'J'),
(86, '201895', 'J'),
(87, '233294', 'J'),
(88, '123422', 'J'),
(89, '651855', 'J'),
(90, '149967', 'J'),
(91, '385574', 'J'),
(92, '897092', 'J'),
(93, '826521', 'J'),
(94, '747710', 'J'),
(95, '921641', 'J'),
(96, '636851', 'J'),
(97, '725930', 'J'),
(98, '884019', 'J'),
(99, '771117', 'J'),
(100, '889581', 'J'),
(101, '368654', 'O'),
(102, '243384', 'O'),
(103, '348601', 'O'),
(104, '423128', 'O'),
(105, '453827', 'O'),
(106, '464680', 'O'),
(107, '841105', 'O'),
(108, '824065', 'O'),
(109, '320833', 'O'),
(110, '474521', 'O'),
(111, '519068', 'O'),
(112, '770430', 'O'),
(113, '691221', 'O'),
(114, '311982', 'O'),
(115, '651208', 'O'),
(116, '714177', 'O'),
(117, '971003', 'O'),
(118, '194679', 'O'),
(119, '270622', 'O'),
(120, '738015', 'O'),
(121, '403081', 'l'),
(122, '187123', 'l'),
(123, '715168', 'l'),
(124, '256249', 'l'),
(125, '967119', 'l'),
(126, '668294', 'l'),
(127, '340707', 'l'),
(128, '965033', 'l'),
(129, '685817', 'l'),
(130, '414204', 'l'),
(131, '464037', 'l'),
(132, '255529', 'l'),
(133, '638465', 'l'),
(134, '985210', 'l'),
(135, '402909', 'l'),
(136, '548997', 'l'),
(137, '465290', 'l'),
(138, '799749', 'l'),
(139, '279756', 'l'),
(140, '994313', 'l');

-- --------------------------------------------------------

--
-- Table structure for table `lines_to_win`
--

CREATE TABLE `lines_to_win` (
  `id` int(11) NOT NULL,
  `lines_to_win` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Dumping data for table `lines_to_win`
--

INSERT INTO `lines_to_win` (`id`, `lines_to_win`) VALUES
(1, 15);

-- --------------------------------------------------------

--
-- Table structure for table `logs`
--

CREATE TABLE `logs` (
  `id` int(11) UNSIGNED NOT NULL,
  `tag` text COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `type` enum('INFO','ERROR') COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `message` longtext COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `timestamp` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

-- --------------------------------------------------------

--
-- Table structure for table `monitor`
--

CREATE TABLE `monitor` (
  `id` int(11) NOT NULL,
  `team` text COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `game_id` int(11) NOT NULL,
  `board` text COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `answers` text COLLATE utf8mb4_unicode_520_ci NOT NULL,
  `removed_lines` int(11) NOT NULL,
  `win` tinyint(4) NOT NULL,
  `timestamp` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answer`
--
ALTER TABLE `answer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `correct_answers`
--
ALTER TABLE `correct_answers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `lines_to_win`
--
ALTER TABLE `lines_to_win`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `monitor`
--
ALTER TABLE `monitor`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answer`
--
ALTER TABLE `answer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3985;

--
-- AUTO_INCREMENT for table `correct_answers`
--
ALTER TABLE `correct_answers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=141;

--
-- AUTO_INCREMENT for table `lines_to_win`
--
ALTER TABLE `lines_to_win`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `logs`
--
ALTER TABLE `logs`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `monitor`
--
ALTER TABLE `monitor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3411;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
