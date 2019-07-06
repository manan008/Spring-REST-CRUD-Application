DROP TABLE usercredentials;
DROP TABLE author_book;
DROP TABLE author;
DROP TABLE book;

CREATE TABLE usercredentials
(
  username VARCHAR(40) PRIMARY KEY,
  password VARCHAR(40)
);

CREATE TABLE author
(
  authorid INTEGER PRIMARY KEY,
  authorname VARCHAR(40),
  emailid VARCHAR(40),
  qualification VARCHAR(20)
);

CREATE TABLE book
(
  bookid VARCHAR(40) PRIMARY KEY,
  bookname VARCHAR(40),
  language VARCHAR(20),
  publisheddate DATE,
  cost NUMBER(6,2)
);

CREATE TABLE author_book
(
  author_id INTEGER NOT NULL,
  book_id VARCHAR(40) NOT NULL,
  FOREIGN KEY (author_id) REFERENCES author(authorid),
  FOREIGN KEY (book_id) REFERENCES book(bookid)
);


INSERT INTO usercredentials VALUES('root','root');

INSERT INTO author VALUES(1001,'Marijn Haverbeke','marjin@gmail.com','MBA');
INSERT INTO author VALUES(1002,'Addy Osmani','addy@gmail.com','B.Tech');
INSERT INTO author VALUES(1003,'Eric Elliott','eric@gmail.com','Ph.D');
INSERT INTO author VALUES(1004,'Kyle Simpson','kyle@gmail.com','BBA');

INSERT INTO book VALUES('B1001','A Modern Introduction to Programming','English',DATE '2018-11-02','472.50');
INSERT INTO book VALUES('B1002','Learning JavaScript Design Patterns','English',DATE '2018-07-12','580.30');
INSERT INTO book VALUES('B1003','Speaking JavaScript','English',DATE '2014-02-01','460.80');
INSERT INTO book VALUES('B1004','Understanding ECMAScript 6','English',DATE '2016-09-03','1060.20');
INSERT INTO book VALUES('B1005','You Don''t Know JS','English',DATE '2015-12-27','272.80');

INSERT INTO author_book VALUES(1001,'B1001');
INSERT INTO author_book VALUES(1002,'B1002');
INSERT INTO author_book VALUES(1003,'B1003');
INSERT INTO author_book VALUES(1004,'B1004');
INSERT INTO author_book VALUES(1004,'B1005');

SELECT * FROM usercredentials;
SELECT * FROM author;
SELECT * FROM book;
SELECT * FROM author_book;