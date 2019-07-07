DROP TABLE USERCREDENTIALS;
DROP TABLE AUTHOR_BOOK;
DROP TABLE AUTHOR;
DROP TABLE BOOK;

CREATE TABLE USERCREDENTIALS
(
  username VARCHAR(40) PRIMARY KEY,
  password VARCHAR(40)
);

CREATE TABLE AUTHOR
(
  authorid INTEGER PRIMARY KEY,
  authorname VARCHAR(40),
  emailid VARCHAR(40),
  qualification VARCHAR(20)
);

CREATE TABLE BOOK
(
  bookid VARCHAR(40) PRIMARY KEY,
  bookname VARCHAR(40),
  language VARCHAR(20),
  publisheddate DATE,
  cost NUMBER(6,2)
);

CREATE TABLE AUTHOR_BOOK
(
  author_id INTEGER NOT NULL,
  book_id VARCHAR(40) NOT NULL,
  FOREIGN KEY (author_id) REFERENCES AUTHOR(authorid),
  FOREIGN KEY (book_id) REFERENCES BOOK(bookid)
);


INSERT INTO USERCREDENTIALS VALUES('root','root');

INSERT INTO AUTHOR VALUES(1001,'Marijn Haverbeke','marjin@gmail.com','MBA');
INSERT INTO AUTHOR VALUES(1002,'Addy Osmani','addy@gmail.com','B.Tech');
INSERT INTO AUTHOR VALUES(1003,'Eric Elliott','eric@gmail.com','Ph.D');
INSERT INTO AUTHOR VALUES(1004,'Kyle Simpson','kyle@gmail.com','BBA');

INSERT INTO BOOK VALUES('6001','A Modern Introduction to Programming','English',DATE '2018-11-02','472.50');
INSERT INTO BOOK VALUES('6002','Learning JavaScript Design Patterns','English',DATE '2018-07-12','580.30');
INSERT INTO BOOK VALUES('6003','Speaking JavaScript','English',DATE '2014-02-01','460.80');
INSERT INTO BOOK VALUES('6004','Understanding ECMAScript 6','English',DATE '2016-09-03','1060.20');
INSERT INTO BOOK VALUES('6005','You Don''t Know JS','English',DATE '2015-12-27','272.80');

INSERT INTO AUTHOR_BOOK VALUES(1001,'6001');
INSERT INTO AUTHOR_BOOK VALUES(1002,'6002');
INSERT INTO AUTHOR_BOOK VALUES(1003,'6003');
INSERT INTO AUTHOR_BOOK VALUES(1004,'6004');
INSERT INTO AUTHOR_BOOK VALUES(1004,'6005');

SELECT * FROM USERCREDENTIALS;
SELECT * FROM AUTHOR;
SELECT * FROM BOOK;
SELECT * FROM AUTHOR_BOOK;