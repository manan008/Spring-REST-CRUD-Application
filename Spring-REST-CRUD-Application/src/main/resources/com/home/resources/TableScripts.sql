DROP TABLE UserCredentials;
DROP TABLE BookDetails;

CREATE TABLE UserCredentials
(
  username VARCHAR(40) PRIMARY KEY,
  password VARCHAR(40)
);

INSERT INTO UserCredentials VALUES('root','root');

CREATE TABLE BookDetails
(
  bookId int PRIMARY KEY,
  bookName VARCHAR(40) UNIQUE,
  publisherName VARCHAR(20),
  publishedDate DATE,
  cost NUMBER(6,2)
);


INSERT INTO BookDetails VALUES(101,'A Modern Introduction to Programming','Marijn Haverbeke',DATE '2018-11-02','472.50');
INSERT INTO BookDetails VALUES(102,'Learning JavaScript Design Patterns','Addy Osmani',DATE '2018-07-12','580.30');
INSERT INTO BookDetails VALUES(103,'Speaking JavaScript','Axel Rauschmayer',DATE '2014-02-01','460.80');
INSERT INTO BookDetails VALUES(104,'Understanding ECMAScript 6','Nicholas C. Zakas',DATE '2016-09-03','1060.20');
INSERT INTO BookDetails VALUES(105,'You Don''t Know JS','Kyle Simpson',DATE '2015-12-27','272.80');

COMMIT;

SELECT * FROM BookDetails;
SELECT * FROM UserCredentials;