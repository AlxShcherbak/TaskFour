-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.5.45 - MySQL Community Server (GPL)
-- ОС Сервера:                   Win32
-- HeidiSQL Версия:              9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных taskFour
CREATE DATABASE IF NOT EXISTS `taskfour` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `taskFour`;


-- Дамп структуры для таблица taskFour.ACCOUNT
CREATE TABLE IF NOT EXISTS `ACCOUNT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(128) DEFAULT NULL,
  `LOGIN` varchar(128) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL DEFAULT '0',
  `ROLE` varchar(128) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `LOGIN` (`LOGIN`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы taskFour.ACCOUNT: ~7 rows (приблизительно)
/*!40000 ALTER TABLE `ACCOUNT` DISABLE KEYS */;
INSERT INTO `ACCOUNT` (`ID`, `NAME`, `LOGIN`, `PASSWORD`, `ROLE`) VALUES
	(1, 'admin', 'admin', 'pass', 'admin'),
	(4, 'teacher 1', 'teacher1', 'pass', 'TEACHER'),
	(5, 'teacher 2 ', 'teacher2', 'pass', 'teacher'),
	(6, 'student 1', 'student1', 'pass', 'student'),
	(7, 'student 2', 'student2', 'pass', 'STUDENT'),
	(8, 'nameStudent', 'studentlogin', 'pass', 'student'),
	(15, '123', 'logintest', '123', 'STUDENT');
/*!40000 ALTER TABLE `ACCOUNT` ENABLE KEYS */;


-- Дамп структуры для таблица taskFour.COURSE
CREATE TABLE IF NOT EXISTS `COURSE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(50) NOT NULL DEFAULT '0',
  `KAFEDRA` varchar(50) DEFAULT '0',
  `DESCRIPTION` varchar(128) DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы taskFour.COURSE: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `COURSE` DISABLE KEYS */;
INSERT INTO `COURSE` (`ID`, `TITLE`, `KAFEDRA`, `DESCRIPTION`) VALUES
	(1, 'course 1', 'TEST KAF', 'desc course'),
	(2, 'course 2', 'test kaf', 'desc course 2');
/*!40000 ALTER TABLE `COURSE` ENABLE KEYS */;


-- Дамп структуры для таблица taskFour.FACULTY
CREATE TABLE IF NOT EXISTS `FACULTY` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COURSE` int(11) NOT NULL DEFAULT '0',
  `STATUS` varchar(50) NOT NULL DEFAULT '0',
  `TEACHER` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `COURSE` (`COURSE`),
  KEY `TEACHER` (`TEACHER`),
  CONSTRAINT `FK_FACULTY_ACCOUNT` FOREIGN KEY (`TEACHER`) REFERENCES `ACCOUNT` (`ID`),
  CONSTRAINT `FK_FACULTY_COURSE` FOREIGN KEY (`COURSE`) REFERENCES `COURSE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы taskFour.FACULTY: ~4 rows (приблизительно)
/*!40000 ALTER TABLE `FACULTY` DISABLE KEYS */;
INSERT INTO `FACULTY` (`ID`, `COURSE`, `STATUS`, `TEACHER`) VALUES
	(1, 1, 'STARTED', 4),
	(2, 2, 'STARTED', 5),
	(3, 1, 'ended', 4),
	(4, 2, 'ENDED', 4);
/*!40000 ALTER TABLE `FACULTY` ENABLE KEYS */;


-- Дамп структуры для таблица taskFour.FACULTY-STUDENT
CREATE TABLE IF NOT EXISTS `FACULTY-STUDENT` (
  `FACULTY` int(11) NOT NULL,
  `STUDENT` int(11) NOT NULL,
  KEY `FACULTY` (`FACULTY`),
  KEY `STUDENT` (`STUDENT`),
  CONSTRAINT `FK_FACULTY-STUDENT_ACCOUNT` FOREIGN KEY (`STUDENT`) REFERENCES `ACCOUNT` (`ID`),
  CONSTRAINT `FK_FACULTY-STUDENT_FACULTY` FOREIGN KEY (`FACULTY`) REFERENCES `FACULTY` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы taskFour.FACULTY-STUDENT: ~4 rows (приблизительно)
/*!40000 ALTER TABLE `FACULTY-STUDENT` DISABLE KEYS */;
INSERT INTO `FACULTY-STUDENT` (`FACULTY`, `STUDENT`) VALUES
	(3, 6),
	(3, 7),
	(2, 6),
	(2, 7);
/*!40000 ALTER TABLE `FACULTY-STUDENT` ENABLE KEYS */;


-- Дамп структуры для таблица taskFour.MARK
CREATE TABLE IF NOT EXISTS `MARK` (
  `STUDENT` int(11) NOT NULL,
  `FACULTY` int(11) NOT NULL,
  `MARK` varchar(10) NOT NULL,
  KEY `STUDENT` (`STUDENT`),
  KEY `FACULTY` (`FACULTY`),
  CONSTRAINT `FK_MARK_ACCOUNT` FOREIGN KEY (`STUDENT`) REFERENCES `ACCOUNT` (`ID`),
  CONSTRAINT `FK_MARK_FACULTY` FOREIGN KEY (`FACULTY`) REFERENCES `FACULTY` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы taskFour.MARK: ~3 rows (приблизительно)
/*!40000 ALTER TABLE `MARK` DISABLE KEYS */;
INSERT INTO `MARK` (`STUDENT`, `FACULTY`, `MARK`) VALUES
	(6, 3, 'A'),
	(7, 3, 'C'),
	(6, 4, 'B');
/*!40000 ALTER TABLE `MARK` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
