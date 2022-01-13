# Project Overview

This project is a course project focusing on constructing an information management system of university entrance and exit. The basic assumptions of system are:

+ Users can be divided into students and teachers.
+ Application records may be marked with 4 states: pending, passed, refused and canclled.
+ For a student with 2 or more applications, the time range may not overlap.
+ For a student, he or she can only ask for a leave of no more than 48 hours.
+ For a student, he or she can only leave the school 3 times per week.

For students, the system needs to achieve functions:

+ Display the students' applications under each state separately
+ Revise the application. The application can only be revised after it is passed or refused. Applications being revised may be no longer valid, need to be processed again.
+ Cancel applications.
+ Apply for a new application. 
+ Sort and filter the applications.

For teachers, the system needs to achieve functions:

+ Pass or refuse the applications belonging to the teacher's department.
+ Write comments for each application.
+ Display the applications under each state separately.
+ Sort and filter the applications.

A demo programm is needed.

# Environment Description

The demo program is developed with Java and PostgreSQL. The GUI part of system is developed with JavaFX. The detailed environment information are listed below.

+ Java version: Oracle JDK 1.8.261
+ Built-in javaFX of JDK8
+ PostgreSQL version: 13.2
+ PG Driver version: 42.2.20

The demo program needs JRE environment and SQL driver to be set up on cilent machine.

# Demo Evaluation Requirements

1.  All of the widgets are set as 1280x720 initially. Please make sure cilent machine has enough resolution. All of the widgets can be dragged for size change but I would never recommend you to do due to possible display issue.
2. The initialization of local or server end database is not included in the program. The initialization SQL code is stored in Src/Initialization.sql. Please create a database named ***proDb***, and run the sql script to finish initialization. 
3. The default configuration of database is: user name ***postgres***, password ***0000***, port ***5433***. This can be manually reset in the corresponding panel.
4. The demo program entrance class is class ***Configurepane***.

# ER Diagram of System Design

![](ERD/ERD.jpg)

# Folder Structure Description

Root/

+ Src: code file of demo and initialization code.
+ ERD: the ER diagram of system description.
+ Documentation: the word/pdf documentation of system. (Simplified Chinese only)
+ Jar: the jar package of demo
+ Screenshots: the running screenshots of system. 

# Code File Description

See in folder documentation.
