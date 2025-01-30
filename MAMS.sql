-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : jeu. 30 jan. 2025 à 15:54
-- Version du serveur : 10.11.10-MariaDB
-- Version de PHP : 8.3.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `MAMS`
--

-- --------------------------------------------------------

--
-- Structure de la table `administrator`
--

CREATE TABLE `administrator` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `administrator`
--

INSERT INTO `administrator` (`id`, `password`, `email`, `firstName`, `lastName`) VALUES
(1, 'adminpassword', 'admin@example.com', 'Admin', 'User');

-- --------------------------------------------------------

--
-- Structure de la table `appointment`
--

CREATE TABLE `appointment` (
  `id` bigint(20) NOT NULL,
  `dateTime` datetime NOT NULL,
  `status` varchar(50) DEFAULT NULL,
  `doctor_id` bigint(20) DEFAULT NULL,
  `patient_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `appointment`
--

INSERT INTO `appointment` (`id`, `dateTime`, `status`, `doctor_id`, `patient_id`) VALUES
(10, '2025-01-30 15:21:34', 'SCHEDULED', 19, 4),
(11, '2025-01-30 15:23:40', 'SCHEDULED', 19, 4),
(12, '2025-01-30 15:28:43', 'SCHEDULED', 19, 4),
(13, '2025-01-30 15:31:15', 'SCHEDULED', 19, 4),
(14, '2025-01-30 15:32:31', 'SCHEDULED', 19, 5),
(15, '2025-01-30 16:13:04', 'SCHEDULED', 19, 5),
(16, '2025-01-30 16:16:58', 'SCHEDULED', 19, 5),
(17, '2025-01-30 16:18:17', 'SCHEDULED', 19, 5),
(18, '2025-01-30 16:23:47', 'SCHEDULED', 19, 5),
(19, '2025-01-30 16:24:09', 'SCHEDULED', 19, 5);

-- --------------------------------------------------------

--
-- Structure de la table `department`
--

CREATE TABLE `department` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `department`
--

INSERT INTO `department` (`id`, `name`, `description`) VALUES
(1, 'Cardiology', 'Heart related medical treatments and procedures.'),
(2, 'Neurology', 'Focuses on disorders of the nervous system.'),
(3, 'Pediatrics', 'Provides medical care for children.'),
(4, 'Orthopedics', 'Deals with bones, joints, and muscles.'),
(5, 'Dermatology', 'Specializes in skin diseases and treatments.');

-- --------------------------------------------------------

--
-- Structure de la table `doctor`
--

CREATE TABLE `doctor` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `specialization` varchar(255) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `doctor`
--

INSERT INTO `doctor` (`id`, `password`, `email`, `firstName`, `lastName`, `specialization`, `department_id`) VALUES
(2, 'password123', 'dr.jane@example.com', 'Jane', 'Smith', 'Neurologist', 2),
(3, 'password123', 'dr.jane@example.com', 'Jane', 'Smith', 'Neurologist', 2),
(10, 'doctor123', 'doctor@example.com', 'John', 'Doe', 'Cardiology', 1),
(11, 'doctor123', 'doctor@example.com', 'John', 'Doe', 'Cardiology', 1),
(12, 'doctor123', 'doctor@example.com', 'John', 'Doe', 'Cardiology', 1),
(13, 'doctor123', 'doctor@example.com', 'John', 'Doe', 'Cardiology', 1),
(14, 'doctor123', 'doctor@example.com', 'John', 'Doe', 'Cardiology', 1),
(15, 'doctor123', 'doctor@example.com', 'John', 'Doe', 'Cardiology', 1),
(16, 'doctor123', 'doctor@example.com', 'John', 'Doe', 'Cardiology', 1),
(17, 'doctor123', 'doctor@example.com', 'John', 'Doe', 'Cardiology', 1),
(18, 'doctor123', 'doctor@example.com', 'John', 'Doe', 'Cardiology', 1),
(19, 'doctor123', 'doctor@example.com', 'John', 'Doe', 'Cardiology', 1),
(20, 'doctor123', 'doctor@example.com', 'John', 'Doe', 'Cardiology', 1),
(21, 'doctor123', 'doctor@example.com', 'John', 'Doe', 'Cardiology', 1),
(22, 'doctor123', 'doctor@example.com', 'John', 'Doe', 'Cardiology', 1),
(23, 'doctor123', 'doctor@example.com', 'John', 'Doe', 'Cardiology', 1),
(24, 'doctor123', 'doctor@example.com', 'John', 'Doe', 'Cardiology', 1),
(25, 'doctor123', 'doctor@example.com', 'John', 'Doe', 'Cardiology', 1),
(26, 'doctor123', 'doctor@example.com', 'John', 'Doe', 'Cardiology', 1),
(27, 'doctor123', 'doctor@example.com', 'John', 'Doe', 'Cardiology', 1),
(28, 'doctor123', 'doctor@example.com', 'John', 'Doe', 'Cardiology', 1),
(29, 'doctor123', 'doctor@example.com', 'John', 'Doe', 'Cardiology', 1);

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

CREATE TABLE `patient` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(15) DEFAULT NULL,
  `address` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `patient`
--

INSERT INTO `patient` (`id`, `password`, `email`, `firstName`, `lastName`, `phoneNumber`, `address`) VALUES
(1, 'password123', 'patient1@example.com', 'Jane', 'Doe', '123-456-7890', '456 Elm St'),
(2, 'mypassword', 'mike@example.com', 'Mike', 'Smith', NULL, 'Roued'),
(3, 'mypassword', 'youssef@example.com', 'Mike', 'Smith', '123456789', 'Roued'),
(4, 'mypassword', 'youssef@example.com', 'Mike', 'Smith', '123456789', 'Roued'),
(5, 'mypassword', 'youssef@example.com', 'Mike', 'Smith', '123456789', 'Roued'),
(6, 'mypassword', 'youssef@example.com', 'Mike', 'Smith', '123456789', 'Roued'),
(7, 'mypassword', 'youssef@example.com', 'Mike', 'Smith', '123456789', 'Roued'),
(8, 'mypassword', 'youssef@example.com', 'Mike', 'Smith', '123456789', 'Roued'),
(9, 'mypassword', 'youssef@example.com', 'Mike', 'Smith', '123456789', 'Roued'),
(10, 'mypassword', 'youssef@example.com', 'Mike', 'Smith', '123456789', 'Roued'),
(11, 'mypassword', 'youssef@example.com', 'Mike', 'Smith', '123456789', 'Roued'),
(12, 'mypassword', 'youssef@example.com', 'Mike', 'Smith', '123456789', 'Roued'),
(13, 'mypassword', 'youssef@example.com', 'Mike', 'Smith', '123456789', 'Roued'),
(14, 'mypassword', 'youssef@example.com', 'Mike', 'Smith', '123456789', 'Roued');

-- --------------------------------------------------------

--
-- Structure de la table `timeslot`
--

CREATE TABLE `timeslot` (
  `id` bigint(20) NOT NULL,
  `startTime` datetime NOT NULL,
  `endTime` datetime NOT NULL,
  `isAvailable` tinyint(1) DEFAULT 1,
  `doctor_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `administrator`
--
ALTER TABLE `administrator`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_appointment_doctor` (`doctor_id`),
  ADD KEY `fk_appointment_patient` (`patient_id`);

--
-- Index pour la table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_department_name` (`name`);

--
-- Index pour la table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_doctor_department` (`department_id`);

--
-- Index pour la table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `timeslot`
--
ALTER TABLE `timeslot`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_timeslot_doctor` (`doctor_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `administrator`
--
ALTER TABLE `administrator`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `appointment`
--
ALTER TABLE `appointment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT pour la table `department`
--
ALTER TABLE `department`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT pour la table `patient`
--
ALTER TABLE `patient`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `timeslot`
--
ALTER TABLE `timeslot`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `fk_appointment_doctor` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`),
  ADD CONSTRAINT `fk_appointment_patient` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`);

--
-- Contraintes pour la table `doctor`
--
ALTER TABLE `doctor`
  ADD CONSTRAINT `fk_doctor_department` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`);

--
-- Contraintes pour la table `timeslot`
--
ALTER TABLE `timeslot`
  ADD CONSTRAINT `fk_timeslot_doctor` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
