-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema popthemoviedb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `popthemoviedb` DEFAULT CHARACTER SET utf8 ;
USE `popthemoviedb` ;

-- -----------------------------------------------------
-- Table `popthemoviedb`.`film`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popthemoviedb`.`film` (
  `id` VARCHAR(45) NOT NULL,
  `titolo` VARCHAR(45) NOT NULL,
  `genere` VARCHAR(255) NOT NULL,
  `poster` VARCHAR(255) NOT NULL,
  `durata` VARCHAR(45) NOT NULL DEFAULT '0 min',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `popthemoviedb`.`utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popthemoviedb`.`utente` (
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  `cognome` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`email`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `popthemoviedb`.`filmdavedere`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popthemoviedb`.`filmdavedere` (
  `Utente_email` VARCHAR(45) NOT NULL,
  `Film_id` VARCHAR(45) NOT NULL,
  INDEX `fk_FilmDaVedere_Utente1_idx` (`Utente_email` ASC) VISIBLE,
  INDEX `fk_FilmDaVedere_Film1_idx` (`Film_id` ASC) VISIBLE,
  CONSTRAINT `FK5bd9h688hq2fqgob6tow164ie`
    FOREIGN KEY (`Utente_email`)
    REFERENCES `popthemoviedb`.`utente` (`email`),
  CONSTRAINT `FK8oqxygb3wx8igxydj36abavdq`
    FOREIGN KEY (`Film_id`)
    REFERENCES `popthemoviedb`.`film` (`id`),
  CONSTRAINT `fk_FilmDaVedere_Film1`
    FOREIGN KEY (`Film_id`)
    REFERENCES `popthemoviedb`.`film` (`id`),
  CONSTRAINT `fk_FilmDaVedere_Utente1`
    FOREIGN KEY (`Utente_email`)
    REFERENCES `popthemoviedb`.`utente` (`email`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `popthemoviedb`.`filmvisti`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `popthemoviedb`.`filmvisti` (
  `Utente_email` VARCHAR(45) NOT NULL,
  `Film_id` VARCHAR(45) NOT NULL,
  INDEX `fk_filmVisti_Utente_idx` (`Utente_email` ASC) VISIBLE,
  INDEX `fk_FilmVisti_Film1_idx` (`Film_id` ASC) VISIBLE,
  CONSTRAINT `fk_FilmVisti_Film1`
    FOREIGN KEY (`Film_id`)
    REFERENCES `popthemoviedb`.`film` (`id`),
  CONSTRAINT `fk_filmVisti_Utente`
    FOREIGN KEY (`Utente_email`)
    REFERENCES `popthemoviedb`.`utente` (`email`),
  CONSTRAINT `FKm8js4pnng0oh9uv5soc5uytbe`
    FOREIGN KEY (`Utente_email`)
    REFERENCES `popthemoviedb`.`utente` (`email`),
  CONSTRAINT `FKn07e4dicutk9xcyl09awhf7wb`
    FOREIGN KEY (`Film_id`)
    REFERENCES `popthemoviedb`.`film` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `popthemoviedb` ;

-- -----------------------------------------------------
-- procedure ClassificaConFilm
-- -----------------------------------------------------

DELIMITER $$
USE `popthemoviedb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ClassificaConFilm`(
	email varchar(45)
)
BEGIN
SELECT filmMaiVisti.Film_id
FROM
	(SELECT  Utente_email, COUNT(Utente_email) AS numFilm
	FROM filmvisti 
	WHERE
		Utente_email NOT LIKE email
		AND
		Film_id IN (
		SELECT Film_id FROM filmvisti WHERE Utente_email = email)
	GROUP BY Utente_email) AS classificaAffinita
RIGHT JOIN 
	(SELECT Film_id, Utente_email
		FROM filmvisti 
		WHERE 
			Utente_email NOT LIKE email
			AND Film_id NOT IN (SELECT Film_id FROM filmdavedere WHERE Utente_email LIKE email)
			AND Film_id NOT IN (SELECT Film_id FROM filmvisti WHERE Utente_email LIKE email)) AS filmMaiVisti
ON filmMaiVisti.Utente_email = classificaAffinita.Utente_email
ORDER BY classificaAffinita.numFilm DESC;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ClassificaConIdFilm
-- -----------------------------------------------------

DELIMITER $$
USE `popthemoviedb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ClassificaConIdFilm`(
	email varchar(45)
)
BEGIN
SELECT filmMaiVisti.Film_id
FROM
	(SELECT  Utente_email, COUNT(Utente_email) AS numFilm
	FROM filmvisti 
	WHERE
		Utente_email NOT LIKE email
		AND
		Film_id IN (
		SELECT Film_id FROM filmvisti WHERE Utente_email = email)
	GROUP BY Utente_email) AS classificaAffinita
RIGHT JOIN 
	(SELECT Film_id, Utente_email
		FROM filmvisti 
		WHERE 
			Utente_email NOT LIKE email
			AND Film_id NOT IN (SELECT Film_id FROM filmdavedere WHERE Utente_email LIKE email)
			AND Film_id NOT IN (SELECT Film_id FROM filmvisti WHERE Utente_email LIKE email)) AS filmMaiVisti
ON filmMaiVisti.Utente_email = classificaAffinita.Utente_email
ORDER BY classificaAffinita.numFilm DESC;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure classificaAffinita
-- -----------------------------------------------------

DELIMITER $$
USE `popthemoviedb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `classificaAffinita`(
	email varchar(45)
)
BEGIN
SELECT  Utente_email, COUNT(Utente_email) as numFilm
FROM filmvisti 
WHERE
	Utente_email NOT LIKE email
    AND
	Film_id IN (
	SELECT Film_id FROM filmvisti WHERE Utente_email = email)
GROUP BY Utente_email
ORDER BY numFilm DESC ;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure filmInClassifica
-- -----------------------------------------------------

DELIMITER $$
USE `popthemoviedb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `filmInClassifica`(
	email varchar(45)
)
BEGIN
SELECT DISTINCT film.id, film.titolo, film.genere, film.poster, film.durata
FROM
(
	SELECT filmMaiVisti.Film_id AS Film_id, classificaAffinita.numFilm AS numFilm
	FROM
		(SELECT  Utente_email, COUNT(Utente_email) AS numFilm
		FROM filmvisti 
		WHERE
			Utente_email NOT LIKE email
			AND
			Film_id IN (
			SELECT Film_id FROM filmvisti WHERE Utente_email = email)
		GROUP BY Utente_email) AS classificaAffinita
	RIGHT JOIN 
		(SELECT Film_id, Utente_email
			FROM filmvisti 
			WHERE 
				Utente_email NOT LIKE email
				AND Film_id NOT IN (SELECT Film_id FROM filmdavedere WHERE Utente_email LIKE email)
				AND Film_id NOT IN (SELECT Film_id FROM filmvisti WHERE Utente_email LIKE email)) AS filmMaiVisti
	ON filmMaiVisti.Utente_email = classificaAffinita.Utente_email
) AS classificaFilm
INNER JOIN film
ON classificaFilm.Film_id = film.id
ORDER BY classificaFilm.numFilm DESC
LIMIT 100;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure filmInClassificaInversa
-- -----------------------------------------------------

DELIMITER $$
USE `popthemoviedb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `filmInClassificaInversa`(email varchar(45))
BEGIN
SELECT DISTINCT film.id, film.titolo, film.genere, film.poster, film.durata
FROM
(
	SELECT filmMaiVisti.Film_id AS Film_id, classificaAffinita.numFilm AS numFilm
	FROM
		(SELECT  Utente_email, COUNT(Utente_email) AS numFilm
		FROM filmvisti 
		WHERE
			Utente_email NOT LIKE email
			AND
			Film_id IN (
			SELECT Film_id FROM filmvisti WHERE Utente_email = email)
		GROUP BY Utente_email) AS classificaAffinita
	RIGHT JOIN 
		(SELECT Film_id, Utente_email
			FROM filmvisti 
			WHERE 
				Utente_email NOT LIKE email
				AND Film_id NOT IN (SELECT Film_id FROM filmdavedere WHERE Utente_email LIKE email)
				AND Film_id NOT IN (SELECT Film_id FROM filmvisti WHERE Utente_email LIKE email)) AS filmMaiVisti
	ON filmMaiVisti.Utente_email = classificaAffinita.Utente_email
) AS classificaFilm
INNER JOIN film
ON classificaFilm.Film_id = film.id
ORDER BY classificaFilm.numFilm ASC 
LIMIT 100;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure filmVistiDaAltri
-- -----------------------------------------------------

DELIMITER $$
USE `popthemoviedb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `filmVistiDaAltri`(
	email VARCHAR(45)
)
BEGIN
SELECT Utente_email, Film_id FROM filmvisti WHERE 
	Utente_email NOT LIKE email
    AND Film_id NOT IN (SELECT Film_id FROM filmdavedere WHERE Utente_email LIKE email)
    AND Film_id NOT IN (SELECT Film_id FROM filmvisti WHERE Utente_email LIKE email);
END$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
