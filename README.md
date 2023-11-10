# tienda-informatica-AmpliacionCrud1
Base de datos usada

-- MySQL dump 10.13  Distrib 8.0.34, for macos13 (x86_64)
--
-- Host: localhost    Database: tienda
-- ------------------------------------------------------
-- Server version	8.1.0

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
-- Table structure for table `FABRICANTES`
--

DROP TABLE IF EXISTS `FABRICANTES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `FABRICANTES` (
  `idFabricante` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idFabricante`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FABRICANTES`
--

LOCK TABLES `FABRICANTES` WRITE;
/*!40000 ALTER TABLE `FABRICANTES` DISABLE KEYS */;
INSERT INTO `FABRICANTES` VALUES (1,'Asus'),(2,'Lenovo'),(3,'Hewlett-Packard'),(4,'Samsung'),(5,'Seagate'),(6,'Crucial'),(7,'Gigabyte'),(8,'Huawei'),(9,'Xiaomi');
/*!40000 ALTER TABLE `FABRICANTES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRODUCTOS`
--

DROP TABLE IF EXISTS `PRODUCTOS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PRODUCTOS` (
  `idProducto` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` double NOT NULL,
  `idFabricante` int NOT NULL,
  PRIMARY KEY (`idProducto`),
  KEY `FKc70aquo0w04g2asco4da9yoe0` (`idFabricante`),
  CONSTRAINT `FKc70aquo0w04g2asco4da9yoe0` FOREIGN KEY (`idFabricante`) REFERENCES `FABRICANTES` (`idFabricante`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRODUCTOS`
--

LOCK TABLES `PRODUCTOS` WRITE;
/*!40000 ALTER TABLE `PRODUCTOS` DISABLE KEYS */;
INSERT INTO `PRODUCTOS` VALUES (1,'Monitor 27 LED Full HD',199.25,1),(2,'Monitor 24 LED Full HD',119.99,1),(3,'Portátil IdeaPad 320',359.65,2),(4,'Portátil Yoga 520',452.79,2),(5,'Impresora HP Deskjet 3720',59.99,3),(6,'Impresora HP Laserjet Pro M26nw',111.86,3),(7,'Disco SSD 1 TB',72.99,4),(8,'Disco duro SATA3 1TB',38.49,5),(9,'GeForce GTX 1080 Xtreme',611.55,6),(10,'Memoria RAM DDR4 8GB',24.19,6),(11,'GeForce GTX 1050Ti',319.19,7);
/*!40000 ALTER TABLE `PRODUCTOS` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-30 11:11:06
