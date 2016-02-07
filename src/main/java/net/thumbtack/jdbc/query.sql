USE `bookstore`;
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `YEAR` INT(11) NOT NULL,
  `pages` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `YEAR` (`YEAR`),
  KEY `pages` (`pages`)
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
INSERT  INTO `books`(`id`,`title`,`YEAR`,`pages`) VALUES (1,'C++',2012,500),(2,'Java',2015,850);


SELECT * FROM books;
SELECT * FROM books LIMIT 5;
SELECT * FROM books WHERE id=2;
SELECT * FROM books WHERE title="Java";
SELECT * FROM books WHERE title LIKE "C%";
SELECT * FROM books WHERE title LIKE "%a";
SELECT * FROM books WHERE YEAR BETWEEN 2000 AND 2014;
SELECT * FROM books WHERE pages BETWEEN 400 AND 600;
SELECT * FROM books WHERE (pages BETWEEN 400 AND 600) AND( YEAR BETWEEN 2000 AND 2014);

UPDATE books SET pages=0;
UPDATE books SET pages=100 WHERE pages<10;

DELETE FROM books WHERE pages>15;
DELETE FROM books WHERE title LIKE "J%";
DELETE FROM books WHERE id BETWEEN 0 AND 2;
DELETE FROM books WHERE title="C++";
DELETE FROM books WHERE title IN ("C++", "Java");
DELETE FROM books;