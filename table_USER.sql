/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER` (
  `id` varchar(36) NOT NULL,
  `firstname` varchar(35) NOT NULL,
  `lastname` varchar(35) NOT NULL,
  `birthday` date DEFAULT NULL,
  `position` point NOT NULL,
  PRIMARY KEY (`id`),
  SPATIAL KEY `position_spatial` (`position`),
  FULLTEXT KEY `firstname_fulltext` (`firstname`),
  FULLTEXT KEY `lastname_fulltext` (`lastname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
