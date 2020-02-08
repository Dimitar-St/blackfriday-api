-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sap_black_friday
-- ------------------------------------------------------
-- Server version	5.7.29-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bought_products`
--

DROP TABLE IF EXISTS `bought_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bought_products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_on` datetime(6) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmyr5y6o1iit0tgu9teagk6ko2` (`product_id`),
  KEY `FK1e5dt8993w4cnwbuoi0tel8es` (`user_id`),
  CONSTRAINT `FK1e5dt8993w4cnwbuoi0tel8es` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKmyr5y6o1iit0tgu9teagk6ko2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bought_products`
--

LOCK TABLES `bought_products` WRITE;
/*!40000 ALTER TABLE `bought_products` DISABLE KEYS */;
/*!40000 ALTER TABLE `bought_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `campaigns`
--

DROP TABLE IF EXISTS `campaigns`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `campaigns` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `isActive` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campaigns`
--

LOCK TABLES `campaigns` WRITE;
/*!40000 ALTER TABLE `campaigns` DISABLE KEYS */;
INSERT INTO `campaigns` VALUES (2,'no desc',_binary '\0','Black Friday 2020'),(3,'no desc',_binary '\0','Black friday 2019'),(4,'description',_binary '\0','Black friday 2018'),(5,'description',_binary '','Black friday 2017');
/*!40000 ALTER TABLE `campaigns` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `campaigns_products`
--

DROP TABLE IF EXISTS `campaigns_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `campaigns_products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `discount_persentage` double DEFAULT NULL,
  `campaign_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5v9rafren065lx0qmdstd0oyh` (`campaign_id`),
  KEY `FKetf09ciyw9fsmpiqesqolkqp3` (`user_id`),
  CONSTRAINT `FK5v9rafren065lx0qmdstd0oyh` FOREIGN KEY (`campaign_id`) REFERENCES `campaigns` (`id`),
  CONSTRAINT `FKetf09ciyw9fsmpiqesqolkqp3` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campaigns_products`
--

LOCK TABLES `campaigns_products` WRITE;
/*!40000 ALTER TABLE `campaigns_products` DISABLE KEYS */;
/*!40000 ALTER TABLE `campaigns_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (4);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `isDeleted` bit(1) NOT NULL,
  `manufacturer` varchar(255) DEFAULT NULL,
  `minPrice` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `promotion` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `regularPrice` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'no desciprtion','no image',_binary '','Nike',20,'Super cool shoes',0,20,25),(2,'no desciprtion','no image',_binary '\0','Nike',20,'Super shoes',0,20,25),(3,'no desciprtion',NULL,_binary '\0','Addidas',20,'V Super Light',0,20,25);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `token` varchar(1000) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,0,NULL,NULL,'uuSnOplnPkGpTRbSHgdNUQ==','employee','eyJraWQiOiJrMSIsImFsZyI6IlJTMjU2In0.eyJpc3MiOiJ0dXRvcmlhbC1hY2FkZW15LmNvbSIsImV4cCI6MTU4MTE3Njg1NiwianRpIjoiSEphRWt3ajgxS0FYdVREbUFEM1o1dyIsImlhdCI6MTU4MTE3NTA1NiwibmJmIjoxNTgxMTc0NDU2LCJpZCI6IjEifQ.UKShJzcVbpSgYtnyIASStf9_49G63Ttacwhog3KCYto9eo-SMQNjNJzOYymqa3Keh6kQ5SbzElxrwdTnHksbT1dc6qcu9cc5mrqIfm5bssTwQGbWxIgTC8JZdwF7YrMil-lFx1PyjMSlrnF0tPYO_HiH6qbBLHJyzVZzfkiLK_mZoQtHUk3HZC08cG8LnbSgb5XzDysHQP1RKDHg09hQYZaU5Mi-GihMZZzfHOgiTC-rJZ-tHZhUanGREDX_Zm2rHR9ILh0nffxPiaxqlkvfLiKA0Ls-3blkjs6Tj-Oioy6GdZ8N1TbEj2ySu2G_pAf0zpm5azvwg5D_ocV3dXLoUw','dimitar'),(2,0,NULL,NULL,'aB5JX4p17izjlOtFhAGtxQ==','client',NULL,'mitko'),(3,0,NULL,NULL,'aB5JX4p17izjlOtFhAGtxQ==','client',NULL,'maria');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-08 17:34:11
