CREATE DATABASE  IF NOT EXISTS `reto5` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `reto5`;

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

-- Table structure for table `usuario`

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `cedula` int NOT NULL,
  `nombres` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `nickname` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`cedula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='para lamacenar usuarios';

DROP TABLE IF EXISTS `partes_operaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partes_operaciones` (
  `id_parte_operacion` int NOT NULL AUTO_INCREMENT,
  `signo` varchar(4) NOT NULL,
  `operador1` varchar(15) NOT NULL,
  `operador2` varchar(15) NOT NULL,
  `resultado` varchar(15) NOT NULL,
  PRIMARY KEY (`id_parte_operacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `sistema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sistema` (
  `idSistema` int NOT NULL AUTO_INCREMENT,
  `desc_sistema` varchar(45) NOT NULL,
  PRIMARY KEY (`idSistema`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `tipo_registro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reto5`.`tipo_registro` (
  `idTipo_registro` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTipo_registro`));

DROP TABLE IF EXISTS `registro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reto5`.`registro` (
  `id_registro` INT NOT NULL AUTO_INCREMENT,
  `cedula_usuario` INT NOT NULL,
  `id_tipo_trabajo` INT NOT NULL,
  `fecha` DATE NOT NULL,
  `hora` TIME NOT NULL,
  PRIMARY KEY (`id_registro`),
  INDEX `ced_usuario_idx` (`cedula_usuario` ASC) VISIBLE,
  INDEX `id_tipo_registro_idx` (`id_tipo_trabajo` ASC) VISIBLE,
  CONSTRAINT `ced_usuario`
    FOREIGN KEY (`cedula_usuario`)
    REFERENCES `reto5`.`usuario` (`cedula`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `id_tipo_registro`
    FOREIGN KEY (`id_tipo_trabajo`)
    REFERENCES `reto5`.`tipo_registro` (`idTipo_registro`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

DROP TABLE IF EXISTS `operaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operaciones` (
  `id_operaciones` int NOT NULL AUTO_INCREMENT ,
  `operador1` varchar(45) NOT NULL,
  `operador2` varchar(45) NOT NULL,
  `resultado` varchar(45) NOT NULL,
  PRIMARY KEY (`id_operaciones`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `partes_operaciones` VALUES (0,'?','NUMERO 1','NUMERO 2','RESULTADO'),(1,'+','SUMANDO1','SUMANDO2','TOTAL'),(2,'-','MINUENDO','SUSTRAENDO','DIFERENCIA'),(3,'x','MULTIPLICANDO','MULTIPLICADOR','PRODUCTO'),(4,'÷','DIVIDENDO','DIVISOR','COCIENTE'),(5,'^','BASE','EXPONENTE','RESULTADO'),(6,'√','RADICAL','RADICANDO','RAIZ');
INSERT INTO `sistema` VALUES (0,'SELECCIONAR'),(1,'DECIMAL'),(2,'BINARIO'),(3,'HEXADECIMAL');
INSERT INTO `tipo_registro` VALUES ('0', 'Inicio de sesion'),('1', 'SUMA'),('2', 'RESTA'),('3', 'MULTIPLICACIÓN'),('4', 'DIVISIÓN'),('5', 'POTENCIA'),('6', 'RADICACIÓN');



