CREATE DATABASE  IF NOT EXISTS `db_pousada_system` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_pousada_system`;
-- MySQL dump 10.13  Distrib 5.6.24, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: db_pousada_system
-- ------------------------------------------------------
-- Server version	5.6.24-0ubuntu2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Administrador`
--

DROP TABLE IF EXISTS `Administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Administrador` (
  `idAdministrador` int(11) NOT NULL AUTO_INCREMENT,
  `Pessoa_idPessoa` int(11) NOT NULL,
  PRIMARY KEY (`idAdministrador`),
  KEY `fk_Administrador_Pessoa1_idx` (`Pessoa_idPessoa`),
  CONSTRAINT `fk_Administrador_Pessoa1` FOREIGN KEY (`Pessoa_idPessoa`) REFERENCES `Pessoa` (`idPessoa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Administrador`
--

LOCK TABLES `Administrador` WRITE;
/*!40000 ALTER TABLE `Administrador` DISABLE KEYS */;
/*!40000 ALTER TABLE `Administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Chale`
--

DROP TABLE IF EXISTS `Chale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Chale` (
  `idChale` int(11) NOT NULL AUTO_INCREMENT,
  `numero` int(11) NOT NULL,
  `diaria` double NOT NULL,
  PRIMARY KEY (`idChale`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Chale`
--

LOCK TABLES `Chale` WRITE;
/*!40000 ALTER TABLE `Chale` DISABLE KEYS */;
/*!40000 ALTER TABLE `Chale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ChaleEquipamento`
--

DROP TABLE IF EXISTS `ChaleEquipamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ChaleEquipamento` (
  `Chale_idChale` int(11) NOT NULL,
  `Equipamento_idEquipamento` int(11) NOT NULL,
  KEY `fk_ChaleEquipamento_Chale1_idx` (`Chale_idChale`),
  KEY `fk_ChaleEquipamento_Equipamento1_idx` (`Equipamento_idEquipamento`),
  CONSTRAINT `fk_ChaleEquipamento_Chale1` FOREIGN KEY (`Chale_idChale`) REFERENCES `Chale` (`idChale`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ChaleEquipamento_Equipamento1` FOREIGN KEY (`Equipamento_idEquipamento`) REFERENCES `Equipamento` (`idEquipamento`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ChaleEquipamento`
--

LOCK TABLES `ChaleEquipamento` WRITE;
/*!40000 ALTER TABLE `ChaleEquipamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `ChaleEquipamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cliente`
--

DROP TABLE IF EXISTS `Cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cliente` (
  `idCliente` int(11) NOT NULL AUTO_INCREMENT,
  `Pessoa_idPessoa` int(11) NOT NULL,
  PRIMARY KEY (`idCliente`),
  KEY `fk_Cliente_Pessoa_idx` (`Pessoa_idPessoa`),
  CONSTRAINT `fk_Cliente_Pessoa` FOREIGN KEY (`Pessoa_idPessoa`) REFERENCES `Pessoa` (`idPessoa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cliente`
--

LOCK TABLES `Cliente` WRITE;
/*!40000 ALTER TABLE `Cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `Cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Equipamento`
--

DROP TABLE IF EXISTS `Equipamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Equipamento` (
  `idEquipamento` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) NOT NULL,
  PRIMARY KEY (`idEquipamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Equipamento`
--

LOCK TABLES `Equipamento` WRITE;
/*!40000 ALTER TABLE `Equipamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `Equipamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Hospedagem`
--

DROP TABLE IF EXISTS `Hospedagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Hospedagem` (
  `idHospedagem` int(11) NOT NULL AUTO_INCREMENT,
  `quantAcomp` int(11) NOT NULL,
  `dataInicio` date NOT NULL,
  `previsao` int(11) NOT NULL,
  `dataSaida` date DEFAULT NULL,
  `Cliente_idCliente` int(11) NOT NULL,
  `Chale_idChale` int(11) NOT NULL,
  PRIMARY KEY (`idHospedagem`),
  KEY `fk_Hospedagem_Cliente1_idx` (`Cliente_idCliente`),
  KEY `fk_Hospedagem_Chale1_idx` (`Chale_idChale`),
  CONSTRAINT `fk_Hospedagem_Chale1` FOREIGN KEY (`Chale_idChale`) REFERENCES `Chale` (`idChale`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Hospedagem_Cliente1` FOREIGN KEY (`Cliente_idCliente`) REFERENCES `Cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Hospedagem`
--

LOCK TABLES `Hospedagem` WRITE;
/*!40000 ALTER TABLE `Hospedagem` DISABLE KEYS */;
/*!40000 ALTER TABLE `Hospedagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pessoa`
--

DROP TABLE IF EXISTS `Pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Pessoa` (
  `idPessoa` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `dataNascimento` date NOT NULL,
  `login` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `privilegio` int(11) NOT NULL,
  `endereco` varchar(120) NOT NULL,
  PRIMARY KEY (`idPessoa`,`login`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pessoa`
--

LOCK TABLES `Pessoa` WRITE;
/*!40000 ALTER TABLE `Pessoa` DISABLE KEYS */;
INSERT INTO `Pessoa` VALUES (1,'Jorge Benito Manda Chuva','1972-10-12','jorjao','jorjao123',0,'\n      Av. Cabrito Seco, 222, Bairro Aluvião Rosa, Cidade de Rio Vermelho');
/*!40000 ALTER TABLE `Pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Proprietario`
--

DROP TABLE IF EXISTS `Proprietario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Proprietario` (
  `idProprietario` int(11) NOT NULL AUTO_INCREMENT,
  `Pessoa_idPessoa` int(11) NOT NULL,
  PRIMARY KEY (`idProprietario`),
  KEY `fk_Proprietario_Pessoa1_idx` (`Pessoa_idPessoa`),
  CONSTRAINT `fk_Proprietario_Pessoa1` FOREIGN KEY (`Pessoa_idPessoa`) REFERENCES `Pessoa` (`idPessoa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Proprietario`
--

LOCK TABLES `Proprietario` WRITE;
/*!40000 ALTER TABLE `Proprietario` DISABLE KEYS */;
/*!40000 ALTER TABLE `Proprietario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reserva`
--

DROP TABLE IF EXISTS `Reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reserva` (
  `idReserva` int(11) NOT NULL AUTO_INCREMENT,
  `dataInicio` date NOT NULL,
  `dataFim` date NOT NULL,
  `Cliente_idCliente` int(11) NOT NULL,
  `Chale_idChale` int(11) NOT NULL,
  PRIMARY KEY (`idReserva`),
  KEY `fk_Reserva_Cliente1_idx` (`Cliente_idCliente`),
  KEY `fk_Reserva_Chale1_idx` (`Chale_idChale`),
  CONSTRAINT `fk_Reserva_Chale1` FOREIGN KEY (`Chale_idChale`) REFERENCES `Chale` (`idChale`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reserva_Cliente1` FOREIGN KEY (`Cliente_idCliente`) REFERENCES `Cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reserva`
--

LOCK TABLES `Reserva` WRITE;
/*!40000 ALTER TABLE `Reserva` DISABLE KEYS */;
/*!40000 ALTER TABLE `Reserva` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-29 18:23:17
