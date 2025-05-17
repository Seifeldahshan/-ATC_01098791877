CREATE DATABASE  IF NOT EXISTS `booking_sys` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `booking_sys`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: booking_sys
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `event_id` bigint DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `booking_date` time(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiy2tdi4vrw2mljj6rqwmd698q` (`event_id`),
  KEY `FKkgseyy7t56x7lkjgu3wah5s3t` (`user_id`),
  CONSTRAINT `FKiy2tdi4vrw2mljj6rqwmd698q` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`),
  CONSTRAINT `FKkgseyy7t56x7lkjgu3wah5s3t` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (57,144,1,'15:00:00.000000'),(60,168,1,'05:15:00.000000'),(61,166,1,'12:31:00.000000'),(62,31,1,'12:31:00.000000'),(63,167,1,'12:31:00.000000'),(66,166,2,'12:31:00.000000'),(67,31,2,'12:31:00.000000'),(68,30,1,'12:31:00.000000');
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (13,'Music'),(14,'Tech'),(15,'Sports');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` bigint DEFAULT NULL,
  `venue` varchar(255) DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  `start_time` time(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK751x8cp2x1h1fay38u2p5gpkr` (`category_id`),
  CONSTRAINT `FK751x8cp2x1h1fay38u2p5gpkr` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (30,'2025-12-31 00:00:00.000000','Join thousands of runners in this exciting city marathon through scenic parks and urban','b43f867d-0081-4648-96d9-14b81b0252cb.jpg','City Marathon 2025',350,'Central Park',15,'12:31:00.000000'),(31,'2025-05-31 00:00:00.000000','Watch the top teams battle it out for the championship trophy in a thrilling football final.','0025431a-235f-4411-873b-91d030dfc82c.jpg','Championship Football Finals',400,'National Stadium',15,'12:31:00.000000'),(32,'2025-05-29 00:00:00.000000','A prestigious tennis tournament featuring national and international players competing for the title.','f6c690cf-cd5d-408f-853d-4cb50c5fe241.jpg','Summer Tennis Open',450,'City Tennis Club',15,'17:15:00.000000'),(33,'2025-05-27 00:00:00.000000','Explore the latest advancements in AI and machine learning with keynote speakers, workshops, and networking opportunities.','7754888e-cfd0-4051-b759-3e5fb685019a.jpg','AI & Machine Learning Conference',250,'Tech Convention Center',14,'15:12:00.000000'),(34,'2025-05-30 00:00:00.000000','Hands-on training and discussions on the latest cybersecurity threats and defense techniques.','043dbca4-63ca-4c5d-a1b2-a400ca55e735.jpg','Cybersecurity Workshop',300,'Venue: Innovation Hub',14,'12:41:00.000000'),(35,'2025-05-29 00:00:00.000000','Discover the future of blockchain technology with exhibits, talks, and demonstrations from industry leaders.','d42af37c-8a5e-4bb2-a359-3d7cf4da4769.jpg','Blockchain Expo',299,'City Expo Hall',14,'15:00:00.000000'),(136,'2025-06-01 00:00:00.000000','Deep dive into quantum computing principles and applications with leading researchers.','c2b638b6-3291-4bc8-bdd8-aa87d63d50fc.jpg','Quantum Computing Summit',320,'Tech Convention Center',14,'09:00:00.000000'),(138,'2025-06-03 00:00:00.000000','Explore augmented reality trends with practical demos and networking sessions.','524185fd-3dfc-4713-bd6d-500abc3d2632.jpg','AR & VR Expo',310,'City Expo Hall',14,'10:30:00.000000'),(139,'2025-06-04 00:00:00.000000','Panel discussion on ethical AI and its impact on society, featuring international experts.','69aa963f-0fda-4d13-b378-447678de9242.jpg','Ethical AI Symposium',290,'Tech Convention Center',14,'14:00:00.000000'),(140,'2025-06-05 00:00:00.000000','Learn cybersecurity best practices for small businesses in this interactive workshop.','165c21f4-65f8-418a-9a9a-e30cedb6c265.jpg','Cybersecurity for SMBs',260,'Innovation Hub',14,'12:00:00.000000'),(142,'2025-06-07 00:00:00.000000','Workshop on building scalable backend systems with microservices architecture.','aecb6c5b-1167-412d-a0b3-dee2aa33c5ae.jpg','Microservices Workshops',339,'Innovation Hubs',14,'13:00:00.000000'),(143,'2025-06-08 00:00:00.000000','Analyzing big data using modern tools and frameworks in this hands-on session.','1ff87c34-ac1e-4f52-b453-4a75c93c20e1.jpg','Big Data Analytics',300,'City Expo Hall',14,'10:00:00.000000'),(144,'2025-06-09 00:00:00.000000','Discover emerging blockchain applications beyond cryptocurrency at this expert-led event.','b393212a-db13-4107-9254-56d9a885d454.jpg','Blockchain Innovations',270,'Tech Convention Center',14,'15:00:00.000000'),(145,'2025-06-10 00:00:00.000000','Engage with AI-powered automation tools to improve business productivity in this training session.','c1d80205-a00b-4e0e-af05-9f5e6ca31c00.jpg','AI Automation Training',340,'Innovation Hub',14,'11:30:00.000000'),(146,'2025-06-11 00:00:00.000000','Annual city cycling race through scenic neighborhoods with professional and amateur racers.','33ba96c2-7c9a-49c5-85bd-7e1497d85a60.jpg','City Cycling Race',200,'Central Park',15,'08:00:00.000000'),(147,'2025-06-12 00:00:00.000000','Experience the excitement of a national basketball championship game live at the stadium.','95b99514-c1fd-4f92-99ab-2b7e38c3dc95.jpg','National Basketball Finals',450,'National Stadium',15,'19:00:00.000000'),(148,'2025-06-13 00:00:00.000000','Join a fun 5k charity run promoting health and wellness for all ages.','bdf23ecb-4047-4764-aabd-fc39bf8c2acd.jpg','Charity 5K Run',150,'City Park',15,'07:30:00.000000'),(149,'2025-06-14 00:00:00.000000','International swimming competition featuring elite athletes.','931e3c6e-e720-4973-aad7-895c213f5a8f.jpg','International Swimming Meet',380,'Aquatic Center',15,'09:00:00.000000'),(150,'2025-06-15 00:00:00.000000','Local soccer tournament with teams from the surrounding region.','d50c256e-803b-488c-beca-90704ab31ccc.jpg','Regional Soccer Tournament',220,'Community Sports Field',15,'16:00:00.000000'),(151,'2025-06-16 00:00:00.000000','Weekend yoga retreat with expert instructors and wellness workshops.','cc3bf7ac-82f7-4e1c-adcf-a91f85f91b70.jpg','Yoga & Wellness Retreat',300,'Greenwood Park',15,'08:30:00.000000'),(152,'2025-06-17 00:00:00.000000','Mountain biking challenge over rugged trails and scenic routes.','c55eb4ed-ad61-4243-99c1-5cc924a69bd2.jpg','Mountain Biking Challenge',250,'Hillside Trails',15,'10:00:00.000000'),(153,'2025-06-18 00:00:00.000000','Annual marathon for professional and amateur runners alike.','effefe08-66f1-4832-9998-c8e94688ce67.jpg','City Marathon',351,'Downtown',15,'07:00:00.000000'),(154,'2025-06-19 00:00:00.000000','Open-air rock climbing competition in a natural park setting.','00da6fe3-ef22-490a-9810-6e7540256390.jpg','Rock Climbing Open',270,'Rocky Ridge Park',15,'11:00:00.000000'),(155,'2025-06-20 00:00:00.000000','Ultimate frisbee championship featuring top local teams.','a7e99299-29cc-4b20-a8a2-d44410154e5e.jpg','Ultimate Frisbee Championship',230,'City Sports Complex',15,'14:00:00.000000'),(166,'2025-05-30 00:00:00.000000','Enjoy an electrifying night with top rock bands performing live.','3f76a448-c910-4bb0-aa62-e23f79d69346.jpg','Rock Night Festival',299,'Open Air Stage',13,'12:31:00.000000'),(167,'2025-06-07 00:00:00.000000','A soothing evening of classical music by renowned orchestras.','0dd7952d-ab77-4c2b-8f48-cde6c1f24ef9.jpg','Classical Music Gala',300,'Classical Music Gala',13,'12:31:00.000000'),(168,'2025-06-03 00:00:00.000000','Dance to the latest pop hits with chart-topping artists.','9439a2b9-ce20-426f-9196-8053cfde8134.jpg','Pop Extravaganza',450,'City Arena',13,'05:15:00.000000'),(169,'2025-05-24 00:00:00.000000','An unforgettable night with electronic dance music DJs.','0dc2456d-a789-4eba-aff2-f6a7387a443b.jpg','EDM Festival',299,'Festival Grounds',13,'14:14:00.000000'),(170,'2025-05-24 00:00:00.000000','Celebrate summer with reggae rhythms and good vibes.','b9791579-0255-44b4-a304-8ae069a41f02.jpg','Celebrate summer',222,'Beachside Stag',13,'23:24:00.000000');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'saifsayed819@gmail.com','$2a$12$anCVKIH3gFgdJNP5H/EDMub5bDnfi.sqolrgqeiZajb9.JJV9uT4y','ADMIN','seifsayed'),(2,'saifsayed714@gmail.com','$2a$12$3a3wXjIIPeTEXTlgdQ40/OOFbcYdNwe4Z3CTjxfh9ID5BxU9AKJIq','USER','saifsayed'),(14,'saifahmed@gmail.com','$2a$12$mUsGnEJz6nohuKM97Ap5DeY7wBMaDGIfgc/K5Aw3nW6U4mDC4Iua6','USER','ahmedmohamed'),(16,'saifsayed715@gmail.com','$2a$12$OpD8ytC2n6uKa8OgiRCAsuMp7F4mZE3z.ui8Q93wb757GicFMTdZ2','USER','saifsayed123'),(17,'zahmed1@apa.gov.eg','$2a$12$lEV5gwjwo0ZvK2bQc8tgcuuzKWwkXvIPHzySdRd3OMwDPKs9oFUky','USER','harry_Maguir'),(18,'saifsayed8191@gmail.com','$2a$12$tBDcicwvW2u4jGoIyiv.muW./t6NyJIt4ihA3QhcoXoNntut.zzz.','USER','harry_Maguirs'),(19,'zahmedasd@apa.gov.eg','$2a$12$xAoqCIb/7Ti7Etfqo1ZtBeEQNMu2cMkqv3SiMngyVOvI1VakCIXvi','USER','harry_Maguirasda'),(20,'saifsayed8111239@gmail.com','$2a$12$x4J5cOU.Z3dqikXwhuNqLe6Z5xoGw/7GfvQKYl1P.rnEAK5GLYQbq','USER','saifsasasasas'),(21,'zahmeagasdadd@apa.gov.eg','$2a$12$GneVzduBLmyckJeqwItbXOyoJlG3MWzTY/sALPFPSmLF8IHOQ6wXu','USER','asfasfasdasd'),(22,'saifsayed814@gmail.com','$2a$12$GrJjlmEmdxAv.R9vD.69QuBQWiEJdXB/qjWaNJWskYUjGJYm94Xqa','USER','ahmed khaled'),(26,'ahmedkhaled123@gmail.com','$2a$12$7SSMWVn0F4CrwFVCcsUtI.nnOqJuaAJSYx.0wv9D6/UwQ5XuyyTsC','USER','ahmed khaled eid'),(27,'saif.sayed12@yahoo.com','$2a$12$6Ht5/T7XPOnHWPaoZ4Y0y.3JGR2vXTxclW9DXXqsdxLWrq7/BClhu','USER','wasadminasdasd'),(28,'saifsayed8199@gmail.com','$2a$12$MsYA6ptyUG/OoE1o1dlWJudNGKwMyufFRw59RkLO6xM1k5EyMubZS','USER','sasfasfasf'),(29,'zahmed@apa.gov.eg','$2a$12$zSd.UwtwxgE3obvkO4wRSu9FnPE5EMOAvUxQoRwVAFS0QUwH0lnsC','USER','saif sayed ahmed ');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verification_token`
--

DROP TABLE IF EXISTS `verification_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `verification_token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_timestamp` datetime(6) NOT NULL,
  `token` tinytext NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrdn0mss276m9jdobfhhn2qogw` (`user_id`),
  CONSTRAINT `FKrdn0mss276m9jdobfhhn2qogw` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verification_token`
--

LOCK TABLES `verification_token` WRITE;
/*!40000 ALTER TABLE `verification_token` DISABLE KEYS */;
INSERT INTO `verification_token` VALUES (1,'2025-05-06 12:58:19.319000','eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6InNlaWZzYXllZCIsInJvbGUiOiJVU0VSIiwiZXhwIjoxNzQ3MTQxMDk5LCJpc3MiOiJlQ29tbWVyY2UifQ.WEtKiBotu-es5vBlU9P4QI-Qsn9EQ_Nc0qcVtaGUhWc',1),(2,'2025-05-14 17:46:05.257000','eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6InNhaWZzYXllZCIsInJvbGUiOiJVU0VSIiwiZXhwIjoxNzQ3ODQ5NTY1LCJpc3MiOiJlQ29tbWVyY2UifQ.oRtmzFfd-6SvozYYRYihUvrf0Awn64bNF-pSHvaxqPY',2),(3,'2025-05-14 18:20:39.456000','eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImFobWVkbW9oYW1lZCIsInJvbGUiOiJVU0VSIiwiZXhwIjoxNzQ3ODUxNjM5LCJpc3MiOiJlQ29tbWVyY2UifQ.iPDb2szlYXqbcN9jOtt4896mOBzoGQLlZeMfiax6bxM',14),(4,'2025-05-14 19:05:45.450000','eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6InNhaWZzYXllZDEyMyIsInJvbGUiOiJVU0VSIiwiZXhwIjoxNzQ3ODU0MzQ1LCJpc3MiOiJlQ29tbWVyY2UifQ.r7iSARTshmwsAUucoIRJcViFJEy3SgUHMXhnzRKSMuQ',16),(5,'2025-05-14 19:08:33.625000','eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImhhcnJ5X01hZ3VpciIsInJvbGUiOiJVU0VSIiwiZXhwIjoxNzQ3ODU0NTEzLCJpc3MiOiJlQ29tbWVyY2UifQ.VdOPfQoD9SLYsH4lEBspUzF8deg2NavF5tMFOeDWdL8',17),(6,'2025-05-14 19:10:35.430000','eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImhhcnJ5X01hZ3VpcnMiLCJyb2xlIjoiVVNFUiIsImV4cCI6MTc0Nzg1NDYzNSwiaXNzIjoiZUNvbW1lcmNlIn0.-w__rYqhpSuwBKM8Dhh4p2pRZOwWvt7IP7Pn7pUisyg',18),(7,'2025-05-14 19:14:43.138000','eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImhhcnJ5X01hZ3VpcmFzZGEiLCJyb2xlIjoiVVNFUiIsImV4cCI6MTc0Nzg1NDg4MywiaXNzIjoiZUNvbW1lcmNlIn0.um3TYkJWQQ_DyFcyivsiDrMBz8uznQE3lciDI7sptUA',19),(8,'2025-05-14 19:16:59.555000','eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6InNhaWZzYXNhc2FzYXMiLCJyb2xlIjoiVVNFUiIsImV4cCI6MTc0Nzg1NTAxOSwiaXNzIjoiZUNvbW1lcmNlIn0.n7KvSr-M02WhS7v_5RWoAK8P6AcywRsrF059mK9SoR4',20),(9,'2025-05-14 19:17:31.890000','eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImFzZmFzZmFzZGFzZCIsInJvbGUiOiJVU0VSIiwiZXhwIjoxNzQ3ODU1MDUxLCJpc3MiOiJlQ29tbWVyY2UifQ.TWTRMFBEizKnjK5bVNKLS0sCUWI4upjpIn4K23qD6PA',21),(10,'2025-05-15 22:32:24.277000','eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImFobWVkIGtoYWxlZCIsInJvbGUiOiJVU0VSIiwiZXhwIjoxNzQ3OTUzMTQ0LCJpc3MiOiJlQ29tbWVyY2UifQ.6LRjvHg6Baf5EGwKmUzNylk_LkYLdQ9bpxBnXWi7t3w',22),(11,'2025-05-16 19:26:26.842000','eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImFobWVkIGtoYWxlZCBlaWQiLCJyb2xlIjoiVVNFUiIsImV4cCI6MTc0ODAyODM4NiwiaXNzIjoiZUNvbW1lcmNlIn0.xQMxW0CFSl1-jS1KGdyYq8wyjwAajuu8GsIV_l782Vs',26),(12,'2025-05-17 11:03:30.474000','eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6Indhc2FkbWluYXNkYXNkIiwicm9sZSI6IlVTRVIiLCJleHAiOjE3NDgwODQ2MTAsImlzcyI6ImVDb21tZXJjZSJ9.2ppSCCy440-EjLxhZOlNDtd15EKmrgiIqOZIvecS0cM',27),(13,'2025-05-17 16:29:34.560000','eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6InNhc2Zhc2Zhc2YiLCJyb2xlIjoiVVNFUiIsImV4cCI6MTc0ODEwNDE3NCwiaXNzIjoiZUNvbW1lcmNlIn0.ZNDgm5JDP0HQoj3-MaAJAyUWCf0kS9QicXCKiphmBYs',28),(14,'2025-05-17 18:58:34.564000','eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6InNhaWYgc2F5ZWQgYWhtZWQgIiwicm9sZSI6IlVTRVIiLCJleHAiOjE3NDgxMTMxMTQsImlzcyI6ImVDb21tZXJjZSJ9.FVSGjm5sj0HEAp2B6ur8jWunX3LyNgSS6J7lBwrVvLk',29);
/*!40000 ALTER TABLE `verification_token` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-17 23:12:03
