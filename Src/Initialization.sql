DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS application;
DROP TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS department;
CREATE TABLE department(
	ID int primary key,
	NAME text
);
CREATE TABLE student(
	ID CHAR(10) primary key,
	DEPARTMENT int,
	password CHAR(20),
	FOREIGN KEY (DEPARTMENT) REFERENCES department ON UPDATE CASCADE
);
CREATE TABLE teacher(
	ID CHAR(10) primary key,
	DEPARTMENT int,
	password CHAR(20),
	FOREIGN KEY (DEPARTMENT) REFERENCES department ON UPDATE CASCADE
);
CREATE TABLE application(
	ID INT primary key,
	STAT timestamp,
	ENDTIME timestamp,
	DURATION int,
	status TEXT,
	apply_reason TEXT,
	initiator CHAR(10),
	processor CHAR(10),
	week int,
	FOREIGN KEY (initiator) REFERENCES student ON UPDATE CASCADE,
	FOREIGN KEY (processor) REFERENCES teacher ON UPDATE CASCADE
);
CREATE TABLE comment(
	ID INT primary key,
	TEA_ID CHAR(10),
	APP_ID INT,
	content TEXT,
	FOREIGN KEY (TEA_ID) REFERENCES teacher ON UPDATE CASCADE,
	FOREIGN KEY (APP_ID) REFERENCES application ON UPDATE CASCADE
);
INSERT INTO department VALUES(0,'Industrial Engineering'),
                             (1,'Department of Automation'),
                             (2,'School of Software'),
                             (3,'Computer Science'),
                             (4,'School of Economics and Management'),
                             (5,'Chemistry'),
                             (6,'XinYa College');
INSERT INTO student VALUES('2019011404',0,'0000'),
                          ('2022035384',1,'0000'),
                          ('2077013250',2,'0000'),
                          ('1908022333',3,'0000'),
                          ('2050012345',4,'0000'),
                          ('2016011404',0,'0000'),
                          ('2042035384',1,'0000'),
                          ('2077018250',2,'0000'),
                          ('1909021333',3,'0000'),
                          ('2058012345',4,'0000');
INSERT INTO teacher VALUES('2019011404',0,'0000'),
                          ('2038035384',1,'0000'),
                          ('2045013250',2,'0000'),
                          ('1909022333',3,'0000'),
                          ('2066012345',4,'0000'),
                          ('2029011404',0,'0000'),
                          ('2098035384',1,'0000'),
                          ('2015013250',2,'0000'),
                          ('1989022333',3,'0000'),
                          ('2086012345',4,'0000');
INSERT INTO application VALUES
(0,'2021-6-12 04:00:00','2021-6-13 21:00:00',41,'passed','Go home.','2019011404','2019011404',24),
(1,'2021-6-14 12:00:00','2021-6-14 23:00:00',11,'pending','Date.','2058012345','2066012345',25),
(2,'2021-7-14 12:00:00','2021-7-16 00:00:00',36,'cancelled','Try to use the system.','2058012345','2086012345',29),
(3,'2021-5-14 12:00:00','2021-5-16 00:00:00',36,'refused','Buy bombs.','1909021333','1909022333',20),
(4,'2021-8-1 00:00:00','2021-8-2 23:00:00',47,'passed','Save the world.','2019011404','2029011404',31);
INSERT INTO comment VALUES
(0,'2019011404',0,'OK'),
(1,'2029011404',0,'Wish you a nice trip.'),
(2,'2029011404',4,'Faith on you.'),
(3,'1909022333',3,'How dare you?');