-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 09 juin 2026 à 12:45
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `unchk_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `budget`
--

CREATE TABLE `budget` (
  `id` bigint(20) NOT NULL,
  `annee` int(11) NOT NULL,
  `chemin_document` varchar(255) DEFAULT NULL,
  `date_approbation` date DEFAULT NULL,
  `date_creation` date NOT NULL,
  `date_modification` date DEFAULT NULL,
  `description` text DEFAULT NULL,
  `designation` varchar(255) NOT NULL,
  `montant` decimal(38,2) NOT NULL,
  `statut` enum('APPROUVE','EN_ATTENTE','EXECUTE','REJETE') NOT NULL,
  `type` enum('NOTE_ORIENTATION','PROJET','REALISE') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `compte_rendu`
--

CREATE TABLE `compte_rendu` (
  `id` bigint(20) NOT NULL,
  `approuve_par` varchar(255) DEFAULT NULL,
  `chemin_document` varchar(255) DEFAULT NULL,
  `contenu` text DEFAULT NULL,
  `date_creation` datetime(6) NOT NULL,
  `date_modification` datetime(6) DEFAULT NULL,
  `date_reunion` date NOT NULL,
  `decisions` text DEFAULT NULL,
  `heure_debut` datetime(6) DEFAULT NULL,
  `heure_fin` datetime(6) DEFAULT NULL,
  `lieu` varchar(255) DEFAULT NULL,
  `notification_envoyee` bit(1) DEFAULT NULL,
  `points_traites` text DEFAULT NULL,
  `recommandations` text DEFAULT NULL,
  `redacteur` varchar(255) NOT NULL,
  `statut` enum('ARCHIVE','BROUILLON','PUBLIE','REJETE') NOT NULL,
  `titre` varchar(255) NOT NULL,
  `type` enum('AUTRE','CONSEIL_UNIVERSITE','RENCONTRE','REUNION','SEMINAIRE','WEBINAIRE') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `compte_rendu`
--

INSERT INTO `compte_rendu` (`id`, `approuve_par`, `chemin_document`, `contenu`, `date_creation`, `date_modification`, `date_reunion`, `decisions`, `heure_debut`, `heure_fin`, `lieu`, `notification_envoyee`, `points_traites`, `recommandations`, `redacteur`, `statut`, `titre`, `type`) VALUES
(1, NULL, NULL, 'e', '2026-06-09 10:23:29.000000', NULL, '2026-06-09', 'eeee', NULL, NULL, 'eee', b'0', NULL, NULL, 'undefined undefined', 'BROUILLON', 'ee', 'REUNION');

-- --------------------------------------------------------

--
-- Structure de la table `courrier`
--

CREATE TABLE `courrier` (
  `id` bigint(20) NOT NULL,
  `chemin_fichier` varchar(255) DEFAULT NULL,
  `contenu` text DEFAULT NULL,
  `date_creation` date DEFAULT NULL,
  `date_modification` date DEFAULT NULL,
  `date_reception` date NOT NULL,
  `date_traitement` date DEFAULT NULL,
  `destinataire` varchar(255) DEFAULT NULL,
  `expediteur` varchar(255) DEFAULT NULL,
  `numero` varchar(255) NOT NULL,
  `objet` varchar(255) NOT NULL,
  `statut` enum('ARCHIVE','EN_ATTENTE','REJETE','TRAITE') NOT NULL,
  `type` enum('ARRIVE','CIRCULAIRE','DEPART','INTERNE','NOTE_ADMINISTRATIVE','NOTE_SERVICE') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `courrier`
--

INSERT INTO `courrier` (`id`, `chemin_fichier`, `contenu`, `date_creation`, `date_modification`, `date_reception`, `date_traitement`, `destinataire`, `expediteur`, `numero`, `objet`, `statut`, `type`) VALUES
(1, NULL, 'ddd', '2026-06-09', NULL, '2026-06-09', NULL, 'ddddd', 'dddd', 'ccccd', 'ddddd', 'EN_ATTENTE', 'ARRIVE');

-- --------------------------------------------------------

--
-- Structure de la table `etudiants`
--

CREATE TABLE `etudiants` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `annee_debut` int(11) DEFAULT NULL,
  `annee_sortie` int(11) DEFAULT NULL,
  `autres_formations` text DEFAULT NULL,
  `date_graduation` date DEFAULT NULL,
  `date_inscription` date DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `diplomes` text DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `formation` varchar(255) DEFAULT NULL,
  `ine` varchar(255) NOT NULL,
  `niveau` int(11) DEFAULT NULL,
  `nom` varchar(255) NOT NULL,
  `numero_matricule` varchar(255) DEFAULT NULL,
  `parcours` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) NOT NULL,
  `promo` varchar(255) DEFAULT NULL,
  `sexe` varchar(255) DEFAULT NULL,
  `statut` enum('ABANDONNE','ACTIF','DIPLOME','INACTIF','SUSPENDU') DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `etudiants`
--

INSERT INTO `etudiants` (`id`, `created_at`, `updated_at`, `adresse`, `annee_debut`, `annee_sortie`, `autres_formations`, `date_graduation`, `date_inscription`, `date_naissance`, `diplomes`, `email`, `formation`, `ine`, `niveau`, `nom`, `numero_matricule`, `parcours`, `prenom`, `promo`, `sexe`, `statut`, `telephone`) VALUES
(1, '2026-06-04 13:16:21.000000', '2026-06-04 13:16:21.000000', NULL, NULL, NULL, NULL, NULL, '2026-06-04', '2004-01-01', NULL, 'djongnabeb@gmail.com', NULL, 'INE01', NULL, 'Beni', 'MAT01', NULL, 'Djongnabe', '2026', NULL, NULL, '+23566022847');

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE `formation` (
  `id` bigint(20) NOT NULL,
  `date_creation` date DEFAULT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `date_modification` date DEFAULT NULL,
  `description` text DEFAULT NULL,
  `lieu` varchar(255) DEFAULT NULL,
  `montant` decimal(38,2) DEFAULT NULL,
  `niveau` enum('AVANCE','EXPERT','INITIATION','INTERMEDIATE') NOT NULL,
  `nombre_femmes` int(11) DEFAULT NULL,
  `nombre_formes` int(11) DEFAULT NULL,
  `nombre_hommes` int(11) DEFAULT NULL,
  `statut` enum('ANNULEE','EN_COURS','PLANIFIEE','SUSPENDUE','TERMINEE') NOT NULL,
  `titre` varchar(255) NOT NULL,
  `type` enum('CERTIFICATION','CONTINUE','PRIVEE','REGULIERE') NOT NULL,
  `type_financement` enum('AUTO_FINANCEMENT','EXTERNE','INTERNE','PARTENARIAT') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `formation`
--

INSERT INTO `formation` (`id`, `date_creation`, `date_debut`, `date_fin`, `date_modification`, `description`, `lieu`, `montant`, `niveau`, `nombre_femmes`, `nombre_formes`, `nombre_hommes`, `statut`, `titre`, `type`, `type_financement`) VALUES
(1, '2026-06-09', '2026-06-12', '2026-06-18', NULL, 'ddd', 'Nouakchott', 120000.00, 'INTERMEDIATE', 15, 45, 30, 'PLANIFIEE', 'web developement', 'REGULIERE', 'INTERNE');

-- --------------------------------------------------------

--
-- Structure de la table `partenaire`
--

CREATE TABLE `partenaire` (
  `id` bigint(20) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `date_creation` date DEFAULT NULL,
  `date_fin_partenariat` date DEFAULT NULL,
  `date_modification` date DEFAULT NULL,
  `date_partenariat` date NOT NULL,
  `description` text DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `fonction_contact` varchar(255) DEFAULT NULL,
  `nom_contact` varchar(255) NOT NULL,
  `nom_entreprise` varchar(255) NOT NULL,
  `observation` text DEFAULT NULL,
  `secteur_activite` varchar(255) NOT NULL,
  `statut` enum('ACTIF','EN_ATTENTE','INACTIF','SUSPENDU') NOT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `type_partenariat` enum('AUTRE','FORMATION','INSERTION','RECRUTEMENT','STAGE') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `partenaire`
--

INSERT INTO `partenaire` (`id`, `adresse`, `date_creation`, `date_fin_partenariat`, `date_modification`, `date_partenariat`, `description`, `email`, `fonction_contact`, `nom_contact`, `nom_entreprise`, `observation`, `secteur_activite`, `statut`, `telephone`, `type_partenariat`) VALUES
(1, NULL, '2026-06-09', NULL, NULL, '2026-06-09', NULL, 'djongnabeb@gmail.com', NULL, 'dddd', 'ddd', NULL, 'dddd', 'EN_ATTENTE', '66022847', 'STAGE');

-- --------------------------------------------------------

--
-- Structure de la table `personnel`
--

CREATE TABLE `personnel` (
  `id` bigint(20) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `categorie` enum('ADMINISTRATIF','ENSEIGNANT','FORMATEUR','TUTEUR') NOT NULL,
  `date_creation` date DEFAULT NULL,
  `date_embauche` date NOT NULL,
  `date_fin_contrat` date DEFAULT NULL,
  `date_modification` date DEFAULT NULL,
  `departement` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `fonction` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `numero_matricule` varchar(255) DEFAULT NULL,
  `observation` text DEFAULT NULL,
  `prenom` varchar(255) NOT NULL,
  `statut` enum('ACTIF','EN_CONGE','INACTIF','SUSPENDU') NOT NULL,
  `telephone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `planning`
--

CREATE TABLE `planning` (
  `id` bigint(20) NOT NULL,
  `date` date NOT NULL,
  `date_creation` date DEFAULT NULL,
  `date_modification` date DEFAULT NULL,
  `description` text DEFAULT NULL,
  `document_preparation` varchar(255) DEFAULT NULL,
  `formateur` varchar(255) DEFAULT NULL,
  `heure_debut` time(6) NOT NULL,
  `heure_fin` time(6) NOT NULL,
  `nombre_participants` int(11) DEFAULT NULL,
  `salle` varchar(255) DEFAULT NULL,
  `sujet` varchar(255) NOT NULL,
  `type_activite` enum('COURS','EVALUATION','PROJET','REUNION_EVALUATION','REUNION_PREPARATION','REUNION_TUTELLE','TP') NOT NULL,
  `formation_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `description` text DEFAULT NULL,
  `actif` tinyint(1) NOT NULL DEFAULT 1,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `nom`, `description`, `actif`, `created_at`, `updated_at`) VALUES
(1, 'ROLE_ADMIN', 'Administrateur avec accès complet', 1, '2026-06-03 10:53:38', '2026-06-03 10:53:38'),
(2, 'ROLE_GESTIONNAIRE', 'Gestionnaire des formations et budgets', 1, '2026-06-03 10:53:38', '2026-06-03 10:53:38'),
(3, 'ROLE_ENSEIGNANT', 'Enseignant responsable des formations', 1, '2026-06-03 10:53:38', '2026-06-03 10:53:38'),
(4, 'ROLE_PERSONNEL', 'Personnel administratif', 1, '2026-06-03 10:53:38', '2026-06-03 10:53:38'),
(5, 'ROLE_ETUDIANT', 'Utilisateur étudiant', 1, '2026-06-03 10:53:38', '2026-06-03 10:53:38'),
(6, 'ROLE_RESPONSABLE_STAGE', 'Responsable des stages', 1, '2026-06-03 10:53:38', '2026-06-03 10:53:38');

-- --------------------------------------------------------

--
-- Structure de la table `stage`
--

CREATE TABLE `stage` (
  `id` bigint(20) NOT NULL,
  `bilan_stage` text DEFAULT NULL,
  `chemin_rapport` varchar(255) DEFAULT NULL,
  `date_creation` date DEFAULT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `date_modification` date DEFAULT NULL,
  `descriptif_taches` text DEFAULT NULL,
  `entreprise` varchar(255) DEFAULT NULL,
  `etudiant_id` bigint(20) NOT NULL,
  `evaluateur_ecole` varchar(255) DEFAULT NULL,
  `evaluateur_entreprise` varchar(255) DEFAULT NULL,
  `note_evaluation` double DEFAULT NULL,
  `poste_occupe` varchar(255) DEFAULT NULL,
  `statut` enum('ANNULE','EN_ATTENTE','EN_COURS','TERMINE') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `stage`
--

INSERT INTO `stage` (`id`, `bilan_stage`, `chemin_rapport`, `date_creation`, `date_debut`, `date_fin`, `date_modification`, `descriptif_taches`, `entreprise`, `etudiant_id`, `evaluateur_ecole`, `evaluateur_entreprise`, `note_evaluation`, `poste_occupe`, `statut`) VALUES
(1, NULL, NULL, '2026-06-09', '2026-06-09', '2026-06-09', NULL, 'sssssssssssssss', 'vvvvs', 1, NULL, NULL, NULL, 'ssssssssssss', 'EN_COURS');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` bigint(20) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `actif` tinyint(1) NOT NULL DEFAULT 1,
  `date_creation` date DEFAULT NULL,
  `derniere_connexion` date DEFAULT NULL,
  `observation` text DEFAULT NULL,
  `date_modification` date DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `username`, `password`, `nom`, `prenom`, `email`, `telephone`, `actif`, `date_creation`, `derniere_connexion`, `observation`, `date_modification`, `created_at`, `updated_at`) VALUES
(1, 'string', '$2a$10$sUUsHx2JueJO2uIVTo8u5uLgcWaoOFQcwCtszaznHXPZnmRxmt2Sy', 'string', 'string', 'string', 'string', 1, '2026-06-04', '2026-06-09', 'string', '2026-06-04', '2026-06-03 10:53:38', '2026-06-09 09:53:47'),
(2, 'gestionnaire', '$2a$10$slYQmyNdGzin7olVN3p5OPST9/PgBkqquzi8Ayla3MUhUuzretrip', 'Belaid', 'Hassan', 'gestionnaire@unchk.edu.dz', '+213 21 34 56 78', 1, '2024-01-15', NULL, NULL, NULL, '2026-06-03 10:53:38', '2026-06-03 10:53:38'),
(3, 'enseignant', '$2a$10$slYQmyNdGzin7olVN3p5OPST9/PgBkqquzi8Ayla3MUhUuzretrip', 'Ahmed', 'Mohamed', 'enseignant@unchk.edu.dz', '+213 21 45 67 89', 1, '2024-01-15', NULL, NULL, NULL, '2026-06-03 10:53:38', '2026-06-03 10:53:38'),
(4, 'hassan', '$2a$10$slYQmyNdGzin7olVN3p5OPST9/PgBkqquzi8Ayla3MUhUuzretrip', 'Hassan', 'Karim', 'hassan.karim@unchk.edu.dz', '+213 21 56 78 90', 1, '2024-01-16', NULL, NULL, NULL, '2026-06-03 10:53:38', '2026-06-03 10:53:38'),
(5, 'fatima', '$2a$10$slYQmyNdGzin7olVN3p5OPST9/PgBkqquzi8Ayla3MUhUuzretrip', 'Zahra', 'Fatima', 'fatima.zahra@unchk.edu.dz', '+213 21 67 89 01', 1, '2024-01-16', NULL, NULL, NULL, '2026-06-03 10:53:38', '2026-06-03 10:53:38'),
(6, 'ali', '$2a$10$slYQmyNdGzin7olVN3p5OPST9/PgBkqquzi8Ayla3MUhUuzretrip', 'Benali', 'Ali', 'ali.benali@unchk.edu.dz', '+213 21 78 90 12', 1, '2024-01-16', NULL, NULL, NULL, '2026-06-03 10:53:38', '2026-06-03 10:53:38'),
(7, 'beni.dev', '$2a$10$idFS3QFa6F0lTtLb5RkVPuZVgCQNh5mxd2l2HAjyq6QktiHxUCTXi', 'Beni', 'Djongnabe', 'beni.dev@unchk.edu.dz', '+213770112233', 1, '2026-06-03', '2026-06-03', 'Compte de test développeur', '2026-06-03', '2026-06-03 12:15:50', '2026-06-03 12:15:50');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur_role`
--

CREATE TABLE `utilisateur_role` (
  `utilisateur_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `utilisateur_role`
--

INSERT INTO `utilisateur_role` (`utilisateur_id`, `role_id`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 5),
(7, 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `budget`
--
ALTER TABLE `budget`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `compte_rendu`
--
ALTER TABLE `compte_rendu`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `courrier`
--
ALTER TABLE `courrier`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `etudiants`
--
ALTER TABLE `etudiants`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK9cvt2silxq8jtv5ecrvr4g0ua` (`ine`),
  ADD UNIQUE KEY `UK28gbsstpf8tpc298hhmpffi99` (`email`),
  ADD UNIQUE KEY `UK51pl66qici7uddhg3av8raq9q` (`numero_matricule`);

--
-- Index pour la table `formation`
--
ALTER TABLE `formation`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `partenaire`
--
ALTER TABLE `partenaire`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK2y49uli6lyu3o9j61st08ksc2` (`email`);

--
-- Index pour la table `personnel`
--
ALTER TABLE `personnel`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKspw3be4srpjk4419oma5k7uki` (`email`);

--
-- Index pour la table `planning`
--
ALTER TABLE `planning`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK74vjwmu5gwwdyf3k6snkv8xah` (`formation_id`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nom` (`nom`),
  ADD KEY `idx_nom` (`nom`);

--
-- Index pour la table `stage`
--
ALTER TABLE `stage`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `idx_username` (`username`),
  ADD KEY `idx_email` (`email`),
  ADD KEY `idx_actif` (`actif`);

--
-- Index pour la table `utilisateur_role`
--
ALTER TABLE `utilisateur_role`
  ADD PRIMARY KEY (`utilisateur_id`,`role_id`),
  ADD KEY `idx_role_id` (`role_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `budget`
--
ALTER TABLE `budget`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `compte_rendu`
--
ALTER TABLE `compte_rendu`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `courrier`
--
ALTER TABLE `courrier`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `etudiants`
--
ALTER TABLE `etudiants`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `formation`
--
ALTER TABLE `formation`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `partenaire`
--
ALTER TABLE `partenaire`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `personnel`
--
ALTER TABLE `personnel`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `planning`
--
ALTER TABLE `planning`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `stage`
--
ALTER TABLE `stage`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `planning`
--
ALTER TABLE `planning`
  ADD CONSTRAINT `FK74vjwmu5gwwdyf3k6snkv8xah` FOREIGN KEY (`formation_id`) REFERENCES `formation` (`id`);

--
-- Contraintes pour la table `utilisateur_role`
--
ALTER TABLE `utilisateur_role`
  ADD CONSTRAINT `utilisateur_role_ibfk_1` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `utilisateur_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
