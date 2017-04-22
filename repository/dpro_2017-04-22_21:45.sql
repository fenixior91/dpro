-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP DATABASE IF EXISTS `dpro`;

CREATE DATABASE IF NOT EXISTS `dpro` DEFAULT CHARACTER SET utf8;

-- -----------------------------------------------------
-- Schema dpro
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dpro` DEFAULT CHARACTER SET utf8 ;
USE `dpro` ;

-- -----------------------------------------------------
-- Table `dpro`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dpro`.`roles` (
  `user_name` VARCHAR(255) NOT NULL,
  `role` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`user_name`),
  UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC, `role` ASC))
ENGINE = InnoDB;

INSERT INTO `dpro`.`roles` (`user_name`, `role`) VALUES ('instructor', 'ROLE_INSTRUCTOR');
INSERT INTO `dpro`.`roles` (`user_name`, `role`) VALUES ('student', 'ROLE_STUDENT');


-- -----------------------------------------------------
-- Table `dpro`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dpro`.`users` (
  `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(255) NOT NULL,
  `user_password` VARCHAR(255) NOT NULL,
  `user_first_name` VARCHAR(255) NOT NULL,
  `user_last_name` VARCHAR(255) NOT NULL,
  `user_enabled` TINYINT(1) NOT NULL,
  `user_email` VARCHAR(255) NOT NULL,
  `user_date_of_birth` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `user_pesel` VARCHAR(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_name_UNIQUE` (`user_name` ASC),
  UNIQUE INDEX `user_email_UNIQUE` (`user_email` ASC),
  CONSTRAINT `fk_user_roles`
    FOREIGN KEY (`user_name`)
    REFERENCES `dpro`.`roles` (`user_name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `dpro`.`users` (`user_name`, `user_password`, `user_first_name`, `user_last_name`, `user_enabled`, `user_email`, `user_date_of_birth`, `user_pesel`) VALUES ('instructor', '123', 'Stachu', 'Jones', true, 'instructor@gmail.com', '1955-11-23', '55112323476');

INSERT INTO `dpro`.`users` (`user_name`, `user_password`, `user_first_name`, `user_last_name`, `user_enabled`, `user_email`, `user_date_of_birth`, `user_pesel`) VALUES ('student', '123', 'Tomasz', 'Truszkowski', true, 'student@gmail.com', '1991-11-01', '91011102519');

-- -----------------------------------------------------
-- Table `dpro`.`students`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dpro`.`students` (
  `student_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `student_album` VARCHAR(255) NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE INDEX `student_album_UNIQUE` (`student_album` ASC),
  INDEX `fk_student_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_student_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `dpro`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `dpro`.`students` (`student_album`, `user_id`) VALUES ('81915', 2);


-- -----------------------------------------------------
-- Table `dpro`.`instructors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dpro`.`instructors` (
  `instructor_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT UNSIGNED NOT NULL,
  `instructor_science_degree` VARCHAR(255) NULL,
  PRIMARY KEY (`instructor_id`),
  INDEX `fk_instructor_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_instructor_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `dpro`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `dpro`.`instructors` (`instructor_science_degree`, `user_id`) VALUES ('Doktor', 1);


-- -----------------------------------------------------
-- Table `dpro`.`subject_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dpro`.`subject_types` (
  `subject_type_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `subject_type_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`subject_type_id`))
ENGINE = InnoDB;

INSERT INTO `dpro`.`subject_types` (`subject_type_name`) VALUES ('Wykład');
INSERT INTO `dpro`.`subject_types` (`subject_type_name`) VALUES ('Ćwiczenia');
INSERT INTO `dpro`.`subject_types` (`subject_type_name`) VALUES ('Laboratorium');

-- -----------------------------------------------------
-- Table `dpro`.`subjects`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dpro`.`subjects` (
  `subject_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `subject_name` VARCHAR(255) NOT NULL,
  `subject_ects` INT UNSIGNED NOT NULL,
  `subject_hours` INT UNSIGNED NOT NULL,
  `subject_type_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`subject_id`),
  INDEX `fk_subject_subject_type_idx` (`subject_type_id` ASC),
  CONSTRAINT `fk_subject_subject_type`
    FOREIGN KEY (`subject_type_id`)
    REFERENCES `dpro`.`subject_types` (`subject_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `dpro`.`subjects` (`subject_name`, `subject_ects`, `subject_hours`, `subject_type_id`) VALUES ('Matematyka Dyskretna', 5, 30, 1);
INSERT INTO `dpro`.`subjects` (`subject_name`, `subject_ects`, `subject_hours`, `subject_type_id`) VALUES ('Matematyka Dyskretna', 5, 35, 2);


-- -----------------------------------------------------
-- Table `dpro`.`user_subjects`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dpro`.`user_subjects` (
  `user_id` INT UNSIGNED NOT NULL,
  `subject_id` INT UNSIGNED NOT NULL,
  INDEX `fk_user_subject_user1_idx` (`user_id` ASC),
  INDEX `fk_user_subject_subject1_idx` (`subject_id` ASC),
  CONSTRAINT `fk_user_subject_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `dpro`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_subject_subject1`
    FOREIGN KEY (`subject_id`)
    REFERENCES `dpro`.`subjects` (`subject_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

INSERT INTO `dpro`.`user_subjects` (`user_id`, `subject_id`) VALUES (1, 1);
INSERT INTO `dpro`.`user_subjects` (`user_id`, `subject_id`) VALUES (1, 2);
INSERT INTO `dpro`.`user_subjects` (`user_id`, `subject_id`) VALUES (2, 1);
INSERT INTO `dpro`.`user_subjects` (`user_id`, `subject_id`) VALUES (2, 2);


-- -----------------------------------------------------
-- Table `dpro`.`marks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dpro`.`marks` (
  `mark_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `mark_value` FLOAT(3,2) UNSIGNED NOT NULL,
  `mark_desc` VARCHAR(255) NOT NULL,
  `mark_is_pass` TINYINT(1) NOT NULL,
  PRIMARY KEY (`mark_id`),
  UNIQUE INDEX `mark_unique` (`mark_value` ASC, `mark_desc` ASC, `mark_is_pass` ASC))
ENGINE = InnoDB;

INSERT INTO `marks` (`mark_value`, `mark_desc`, `mark_is_pass`) VALUES (2.0, 'Niedostateczny', '0');
INSERT INTO `marks` (`mark_value`, `mark_desc`, `mark_is_pass`) VALUES (3.0, 'Dostateczny', '1');
INSERT INTO `marks` (`mark_value`, `mark_desc`, `mark_is_pass`) VALUES (3.5, 'Dostateczny Plus', '1');
INSERT INTO `marks` (`mark_value`, `mark_desc`, `mark_is_pass`) VALUES (4.0, 'Dobry', '1');
INSERT INTO `marks` (`mark_value`, `mark_desc`, `mark_is_pass`) VALUES (4.5, 'Dobry Plus', '1');
INSERT INTO `marks` (`mark_value`, `mark_desc`, `mark_is_pass`) VALUES (5.0, 'Bardzo Dobry', '1');

-- -----------------------------------------------------
-- Table `dpro`.`student_marks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dpro`.`student_marks` (
  `subject_id` INT UNSIGNED NOT NULL,
  `mark_id` INT UNSIGNED NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  INDEX `fk_student_marks_subject1_idx` (`subject_id` ASC),
  INDEX `fk_student_marks_mark1_idx` (`mark_id` ASC),
  INDEX `fk_student_marks_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_student_marks_subject1`
    FOREIGN KEY (`subject_id`)
    REFERENCES `dpro`.`subjects` (`subject_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_student_marks_mark1`
    FOREIGN KEY (`mark_id`)
    REFERENCES `dpro`.`marks` (`mark_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_student_marks_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `dpro`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

