-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 22, 2022 at 02:16 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `volcanofootball`
--

-- --------------------------------------------------------

--
-- Table structure for table `agence`
--

CREATE TABLE `agence` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `adresse` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `num_tel` int(11) NOT NULL,
  `photo_a` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nb_etoiles` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `agence`
--

INSERT INTO `agence` (`id`, `nom`, `adresse`, `num_tel`, `photo_a`, `nb_etoiles`) VALUES
(1, 'New Look Travel', '31 Avenue Alain Savary', 72653726, NULL, 3),
(2, 'Ezy Travel', 'Al Rabban Bldg., 27 Umm Ghuwailina B', 7382933, NULL, 3),
(3, 'travell Todo', 'alan savary', 72657556, NULL, 3);

-- --------------------------------------------------------

--
-- Table structure for table `artiste`
--

CREATE TABLE `artiste` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `age` int(11) NOT NULL,
  `evenement_id` int(11) DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `artiste`
--

INSERT INTO `artiste` (`id`, `nom`, `age`, `evenement_id`, `type`) VALUES
(2, 'maitre gims', 21, 2, 'Chanteur');

-- --------------------------------------------------------

--
-- Table structure for table `billet`
--

CREATE TABLE `billet` (
  `id` int(11) NOT NULL,
  `prix` double NOT NULL,
  `categorie` int(11) NOT NULL,
  `matche_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `billet`
--

INSERT INTO `billet` (`id`, `prix`, `categorie`, `matche_id`) VALUES
(2, 200, 1, 2),
(3, 150, 2, 2),
(4, 100, 3, 2),
(5, 50, 4, 2),
(6, 200, 1, 3),
(7, 150, 2, 3),
(8, 100, 3, 3),
(9, 50, 4, 3),
(10, 200, 1, 4),
(11, 150, 2, 4),
(12, 100, 3, 4),
(13, 50, 4, 4),
(14, 200, 1, 5),
(15, 150, 2, 5),
(16, 100, 3, 5),
(17, 50, 4, 5),
(18, 200, 1, 6),
(19, 150, 2, 6),
(20, 100, 3, 6),
(21, 50, 4, 6);

-- --------------------------------------------------------

--
-- Table structure for table `commande`
--

CREATE TABLE `commande` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `produit_id` int(11) DEFAULT NULL,
  `quantite` int(11) NOT NULL,
  `date_ajout` datetime NOT NULL,
  `adresse` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `commande`
--

INSERT INTO `commande` (`id`, `user_id`, `produit_id`, `quantite`, `date_ajout`, `adresse`) VALUES
(5, 1, 2, 7, '2022-03-18 04:13:11', 'ariana'),
(13, 1, 1, 5, '2022-03-21 10:32:10', 'tunis'),
(15, 1, 1, 5, '2022-03-22 13:00:47', 'test');

-- --------------------------------------------------------

--
-- Table structure for table `contact_joueur`
--

CREATE TABLE `contact_joueur` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `message` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `objet` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `doctrine_migration_versions`
--

CREATE TABLE `doctrine_migration_versions` (
  `version` varchar(191) COLLATE utf8_unicode_ci NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `execution_time` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `doctrine_migration_versions`
--

INSERT INTO `doctrine_migration_versions` (`version`, `executed_at`, `execution_time`) VALUES
('DoctrineMigrations\\Version20220203194414', '2022-03-08 01:29:53', 418),
('DoctrineMigrations\\Version20220203195826', '2022-03-08 01:29:54', 375),
('DoctrineMigrations\\Version20220204134336', '2022-03-08 01:29:54', 635),
('DoctrineMigrations\\Version20220209130902', '2022-03-08 01:29:55', 791),
('DoctrineMigrations\\Version20220209132119', '2022-03-08 01:29:56', 1060),
('DoctrineMigrations\\Version20220209134103', '2022-03-08 01:29:57', 752),
('DoctrineMigrations\\Version20220209134517', '2022-03-08 01:29:58', 3014),
('DoctrineMigrations\\Version20220219132907', '2022-03-08 01:30:01', 6124),
('DoctrineMigrations\\Version20220226131041', '2022-03-08 01:30:07', 123),
('DoctrineMigrations\\Version20220226160121', '2022-03-08 01:30:07', 1103),
('DoctrineMigrations\\Version20220228200246', '2022-03-08 01:30:08', 0),
('DoctrineMigrations\\Version20220303223117', '2022-03-08 01:30:08', 4394),
('DoctrineMigrations\\Version20220308003739', '2022-03-08 01:37:48', 23793),
('DoctrineMigrations\\Version20220308015411', '2022-03-08 02:54:21', 3917),
('DoctrineMigrations\\Version20220308032134', '2022-03-08 04:21:41', 2265),
('DoctrineMigrations\\Version20220308032935', '2022-03-08 04:29:40', 1961),
('DoctrineMigrations\\Version20220308042329', '2022-03-08 05:23:34', 156),
('DoctrineMigrations\\Version20220321222917', '2022-03-21 23:29:31', 3871),
('DoctrineMigrations\\Version20220321224954', '2022-03-21 23:49:58', 1617),
('DoctrineMigrations\\Version20220322081258', '2022-03-22 09:13:08', 2272),
('DoctrineMigrations\\Version20220322095507', '2022-03-22 10:55:11', 838);

-- --------------------------------------------------------

--
-- Table structure for table `equipe`
--

CREATE TABLE `equipe` (
  `id` int(11) NOT NULL,
  `nom_equipe` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_creation` date NOT NULL,
  `drapeau_equipe` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nom_entreneur` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `equipe`
--

INSERT INTO `equipe` (`id`, `nom_equipe`, `date_creation`, `drapeau_equipe`, `nom_entreneur`) VALUES
(1, 'tunisie', '2017-01-01', 'f4641538db3e064c7036aa43cc9d447a.png', 'aaaaaaaaaaaaaa'),
(2, 'FRANCE', '2017-01-01', '53011cfa78c7eabdf8472b1f0b8726c3.png', 'Al Janoub Stadium');

-- --------------------------------------------------------

--
-- Table structure for table `evenement`
--

CREATE TABLE `evenement` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `stade_id` int(11) DEFAULT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `evenement`
--

INSERT INTO `evenement` (`id`, `nom`, `stade_id`, `date`) VALUES
(2, 'bbb', 1, '2022-01-01');

-- --------------------------------------------------------

--
-- Table structure for table `facture`
--

CREATE TABLE `facture` (
  `id` int(11) NOT NULL,
  `reservation_id` int(11) DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `prix` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `facture`
--

INSERT INTO `facture` (`id`, `reservation_id`, `type`, `created_at`, `prix`) VALUES
(1, 1, 'Paiement Par Carte', '2022-03-08 02:25:47', 80),
(2, 2, 'Paiement Par Carte', '2022-03-08 09:48:01', 80);

-- --------------------------------------------------------

--
-- Table structure for table `favori`
--

CREATE TABLE `favori` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `produit_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `favori`
--

INSERT INTO `favori` (`id`, `user_id`, `produit_id`) VALUES
(5, 9, 1),
(6, 9, 2);

-- --------------------------------------------------------

--
-- Table structure for table `hebergement`
--

CREATE TABLE `hebergement` (
  `id` int(11) NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `adresse` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `agence_id` int(11) DEFAULT NULL,
  `nom_h` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `photo_h` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `hebergement`
--

INSERT INTO `hebergement` (`id`, `type`, `adresse`, `agence_id`, `nom_h`, `photo_h`) VALUES
(3, 'hotels', 'Al daouha Qatar', 1, 'Farah hotel', '5d2d6e732c1c64dad9bf0f72bbe68550.png'),
(4, 'apartments & villas', 'Umm Salal , Qatar', 1, 'Croisant Hotel & Spa', 'f7b1e039ac3be0b1862631de4fe1fdbf.png'),
(5, 'cruise ship hotels', 'qatar', 2, 'Al Janoub Stadium', 'e3e5d3682ba444d0b55686ecdb11cd92.png'),
(6, 'hotels', 'al daouha Qatar', 2, 'jumeirah hotel', '9f991172718921118035265e4cd6c3bc.png'),
(7, 'hotels', 'AlRiyadh Qatar', 2, 'Al Mouradi', '1523cf7e759eb9be8924dbd352437c6c.png');

-- --------------------------------------------------------

--
-- Table structure for table `joueur`
--

CREATE TABLE `joueur` (
  `id` int(11) NOT NULL,
  `nom_joueur` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prenom_joueur` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `age` int(11) NOT NULL,
  `equipe_id` int(11) DEFAULT NULL,
  `position` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `photo` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `joueur`
--

INSERT INTO `joueur` (`id`, `nom_joueur`, `prenom_joueur`, `age`, `equipe_id`, `position`, `photo`, `description`) VALUES
(1, 'slimani', 'khairi', 23, 1, 'defense', 'a37ec835d4a90f1fbd077cc688cce4f7.jpeg', 'aaaaaaaaaaaaaaaaaaaaaaaa');

-- --------------------------------------------------------

--
-- Table structure for table `kiosque`
--

CREATE TABLE `kiosque` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `stade_id` int(11) DEFAULT NULL,
  `photo` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `kiosque`
--

INSERT INTO `kiosque` (`id`, `nom`, `type`, `stade_id`, `photo`) VALUES
(1, 'rest', 'Marocain', 1, 'd9cb89bac1c0347c602a802021595a7a.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `matche`
--

CREATE TABLE `matche` (
  `id` int(11) NOT NULL,
  `nom_arbitre` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tour` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `stade_id` int(11) DEFAULT NULL,
  `date` date NOT NULL,
  `nom_matche` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `time` time NOT NULL,
  `image1` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image2` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `matche`
--

INSERT INTO `matche` (`id`, `nom_arbitre`, `tour`, `stade_id`, `date`, `nom_matche`, `time`, `image1`, `image2`) VALUES
(2, 'Marc batta', 'Demi final', 1, '2023-01-01', 'Tun vs Alg', '00:00:00', 'tun-6222a3f860854-622685ee7f215-6226c6348c51f.png', 'alge-6222a3f862179-622685ee9ba4b-6226c6348dc87.png'),
(3, 'Mikael Lesage', 'Final', 1, '2023-01-01', 'Qatar vs Egybt', '00:00:00', 'qatar-6222a3812d9f4-62268654f3b37-6226c66fa9c90.png', 'egypt-6222a3812f377-6226875eae2bf-6226c66faafa1.png'),
(4, 'François Letexier', 'Demi final', 1, '2023-01-01', 'Maroc vs Nigeria', '00:00:00', 'maroc-6222a8d89a1b8-622686c89d015-6226c6a0eb446.png', 'Nigeria-6222a802790f3-622686c89e544-6226c6a0ec96a.png'),
(5, 'Mikael Lesage', 'Demi final', 1, '2023-01-01', 'Malysia vs Oman', '00:00:00', 'Malysia-622337bde2200-62268719d189c-6226c6c9d027c.png', 'oman-6222a923969b9-62268719d2ab8-6226c6c9d17f2.png'),
(6, 'Marc batta', 'Demi final', 1, '2023-01-01', 'Egybt vs Sweden', '00:00:00', 'egypt-6222a3812f377-6226875eae2bf-6226c6ebd7d92.png', 'Sweden-6222a9fc6adcf-6226875eaf943-6226c6ebd90bf.png');

-- --------------------------------------------------------

--
-- Table structure for table `produit`
--

CREATE TABLE `produit` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `taille` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `couleur` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nbr_etoiles` int(11) NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prix` double NOT NULL,
  `photo` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `produit`
--

INSERT INTO `produit` (`id`, `nom`, `type`, `taille`, `couleur`, `nbr_etoiles`, `description`, `prix`, `photo`) VALUES
(1, 'Produit  1', 'Jaquette', 'M', 'Noir', 4, 'test test test', 100, '1416e85f2673bea3d5e983782dd99f17.jpeg'),
(2, 'Produit 2', 'Maillot', 'XL', 'Violet', 3, 'test', 100, '07c0c6ee2e3d45c78eaba45e3298bc6b.jpeg'),
(6, 'Produit 3', 'Maillot', 'XL', 'Violet', 3, 'test test', 100, '4b3d162208cbaede297d5da83c4aeb25.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `id` int(11) NOT NULL,
  `hebergement_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `date_debut` datetime NOT NULL,
  `date_fin` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`id`, `hebergement_id`, `user_id`, `date_debut`, `date_fin`) VALUES
(4, 5, 1, '2022-02-27 04:03:00', '2022-03-06 00:00:00'),
(6, 3, 1, '2022-03-18 00:00:00', '2022-04-03 00:00:00'),
(7, 3, 1, '2022-03-09 11:00:00', '2022-03-12 12:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `reservation_kiosque`
--

CREATE TABLE `reservation_kiosque` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `kiosque_id` int(11) DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `reservation_kiosque`
--

INSERT INTO `reservation_kiosque` (`id`, `user_id`, `kiosque_id`, `type`, `created_at`) VALUES
(1, 1, 1, 'STANDARD', '2022-03-08 02:25:47'),
(2, 1, 1, 'STANDARD', '2022-03-08 09:48:01');

-- --------------------------------------------------------

--
-- Table structure for table `reset_password_request`
--

CREATE TABLE `reset_password_request` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `selector` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `hashed_token` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `requested_at` datetime NOT NULL COMMENT '(DC2Type:datetime_immutable)',
  `expires_at` datetime NOT NULL COMMENT '(DC2Type:datetime_immutable)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `stade`
--

CREATE TABLE `stade` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `adresse` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `capacite` int(11) NOT NULL,
  `dateouverture` date NOT NULL,
  `photo` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `stade`
--

INSERT INTO `stade` (`id`, `nom`, `adresse`, `capacite`, `dateouverture`, `photo`) VALUES
(1, 'Al Janoub Stadium', 'qatar', 40000, '2020-01-01', 'e12b73f0f17ba708ec569b199aec1663.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `roles` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '(DC2Type:json)',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `enabled` tinyint(4) NOT NULL,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `username` varchar(180) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `roles`, `password`, `nom`, `prenom`, `email`, `token`, `enabled`, `code`, `username`) VALUES
(1, '[\"ROLE_USER\",\"ROLE_ADMIN\"]', '$argon2id$v=19$m=65536,t=4,p=1$VHg1a2c5OFlzSnVVcXNYdQ$j1xwz/Ob0iH/nCjbumElbQUS6Ao1ihWkVIDACFM6TqQ', 'khairi', 'khairi', 'khairi@esprit.tn', NULL, 1, '6414', 'khairi'),
(9, '[\"ROLE_USER\"]', '$argon2id$v=19$m=65536,t=4,p=1$RU01VVFJWHJadmpIZnZ1eQ$PNvA0x4Pvh5xgIj5t82kNm9pvcai1RkR61nMC0is4sw', 'test', 'test', 'khairi.slimani@esprit.tn', NULL, 1, NULL, 'test'),
(11, '[\"ROLE_ADMIN\"]', '$argon2id$v=19$m=65536,t=4,p=1$SDY5by54Q0hQekpNOHlMeg$6lS9xzEcLrnK0Nm32JPMVL92OObnCFDWfBFotQ8unK8', 'mobile', 'mobile', 'ds@dssd.ca', NULL, 1, NULL, 'mobile');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `agence`
--
ALTER TABLE `agence`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `artiste`
--
ALTER TABLE `artiste`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_9C07354FFD02F13` (`evenement_id`);

--
-- Indexes for table `billet`
--
ALTER TABLE `billet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_1F034AF6FD997C2B` (`matche_id`);

--
-- Indexes for table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_6EEAA67DA76ED395` (`user_id`),
  ADD KEY `IDX_6EEAA67DF347EFB` (`produit_id`);

--
-- Indexes for table `contact_joueur`
--
ALTER TABLE `contact_joueur`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `doctrine_migration_versions`
--
ALTER TABLE `doctrine_migration_versions`
  ADD PRIMARY KEY (`version`);

--
-- Indexes for table `equipe`
--
ALTER TABLE `equipe`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_B26681E6538AB43` (`stade_id`);

--
-- Indexes for table `facture`
--
ALTER TABLE `facture`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQ_FE866410B83297E7` (`reservation_id`);

--
-- Indexes for table `favori`
--
ALTER TABLE `favori`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_EF85A2CCA76ED395` (`user_id`),
  ADD KEY `IDX_EF85A2CCF347EFB` (`produit_id`);

--
-- Indexes for table `hebergement`
--
ALTER TABLE `hebergement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_4852DD9CD725330D` (`agence_id`);

--
-- Indexes for table `joueur`
--
ALTER TABLE `joueur`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_FD71A9C56D861B89` (`equipe_id`);

--
-- Indexes for table `kiosque`
--
ALTER TABLE `kiosque`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_636D6C1E6538AB43` (`stade_id`);

--
-- Indexes for table `matche`
--
ALTER TABLE `matche`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_9FCAD5106538AB43` (`stade_id`);

--
-- Indexes for table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_42C8495523BB0F66` (`hebergement_id`),
  ADD KEY `IDX_42C84955A76ED395` (`user_id`);

--
-- Indexes for table `reservation_kiosque`
--
ALTER TABLE `reservation_kiosque`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_4F8D79BA76ED395` (`user_id`),
  ADD KEY `IDX_4F8D79B8AB27267` (`kiosque_id`);

--
-- Indexes for table `reset_password_request`
--
ALTER TABLE `reset_password_request`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_7CE748AA76ED395` (`user_id`);

--
-- Indexes for table `stade`
--
ALTER TABLE `stade`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `agence`
--
ALTER TABLE `agence`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `artiste`
--
ALTER TABLE `artiste`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `billet`
--
ALTER TABLE `billet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `commande`
--
ALTER TABLE `commande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `contact_joueur`
--
ALTER TABLE `contact_joueur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `equipe`
--
ALTER TABLE `equipe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `facture`
--
ALTER TABLE `facture`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `favori`
--
ALTER TABLE `favori`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `hebergement`
--
ALTER TABLE `hebergement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `joueur`
--
ALTER TABLE `joueur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `kiosque`
--
ALTER TABLE `kiosque`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `matche`
--
ALTER TABLE `matche`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `produit`
--
ALTER TABLE `produit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `reservation_kiosque`
--
ALTER TABLE `reservation_kiosque`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `reset_password_request`
--
ALTER TABLE `reset_password_request`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `stade`
--
ALTER TABLE `stade`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `artiste`
--
ALTER TABLE `artiste`
  ADD CONSTRAINT `FK_9C07354FFD02F13` FOREIGN KEY (`evenement_id`) REFERENCES `evenement` (`id`);

--
-- Constraints for table `billet`
--
ALTER TABLE `billet`
  ADD CONSTRAINT `FK_1F034AF6FD997C2B` FOREIGN KEY (`matche_id`) REFERENCES `matche` (`id`);

--
-- Constraints for table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `FK_6EEAA67DA76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_6EEAA67DF347EFB` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`id`);

--
-- Constraints for table `evenement`
--
ALTER TABLE `evenement`
  ADD CONSTRAINT `FK_B26681E6538AB43` FOREIGN KEY (`stade_id`) REFERENCES `stade` (`id`);

--
-- Constraints for table `facture`
--
ALTER TABLE `facture`
  ADD CONSTRAINT `FK_FE866410B83297E7` FOREIGN KEY (`reservation_id`) REFERENCES `reservation_kiosque` (`id`);

--
-- Constraints for table `favori`
--
ALTER TABLE `favori`
  ADD CONSTRAINT `FK_EF85A2CCA76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_EF85A2CCF347EFB` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`id`);

--
-- Constraints for table `hebergement`
--
ALTER TABLE `hebergement`
  ADD CONSTRAINT `FK_4852DD9CD725330D` FOREIGN KEY (`agence_id`) REFERENCES `agence` (`id`);

--
-- Constraints for table `joueur`
--
ALTER TABLE `joueur`
  ADD CONSTRAINT `FK_FD71A9C56D861B89` FOREIGN KEY (`equipe_id`) REFERENCES `equipe` (`id`);

--
-- Constraints for table `kiosque`
--
ALTER TABLE `kiosque`
  ADD CONSTRAINT `FK_636D6C1E6538AB43` FOREIGN KEY (`stade_id`) REFERENCES `stade` (`id`);

--
-- Constraints for table `matche`
--
ALTER TABLE `matche`
  ADD CONSTRAINT `FK_9FCAD5106538AB43` FOREIGN KEY (`stade_id`) REFERENCES `stade` (`id`);

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FK_42C8495523BB0F66` FOREIGN KEY (`hebergement_id`) REFERENCES `hebergement` (`id`),
  ADD CONSTRAINT `FK_42C84955A76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `reservation_kiosque`
--
ALTER TABLE `reservation_kiosque`
  ADD CONSTRAINT `FK_4F8D79B8AB27267` FOREIGN KEY (`kiosque_id`) REFERENCES `kiosque` (`id`),
  ADD CONSTRAINT `FK_4F8D79BA76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `reset_password_request`
--
ALTER TABLE `reset_password_request`
  ADD CONSTRAINT `FK_7CE748AA76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
