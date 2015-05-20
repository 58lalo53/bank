DROP TABLE TRANSACTIONS;
DROP TABLE ACCOUNT;
DROP TABLE CUSTOMER;


CREATE TABLE BANKING.CUSTOMER(
fname VARCHAR(50) NOT NULL,
lname VARCHAR(50) NOT NULL,
mname VARCHAR(50),
street VARCHAR(150) NOT NULL,
city VARCHAR (100) NOT NULL,
state VARCHAR (100) NOT NULL,
zip VARCHAR(5) NOT NULL,
phone VARCHAR(10) NOT NULL,
email VARCHAR(150) NOT NULL,
username VARCHAR(10) NOT NULL UNIQUE,
password VARCHAR(15) NOT NULL,
role VARCHAR(15) NOT NULL,
picture BLOB(200K),
pictype VARCHAR(30),
id int GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY
);

CREATE TABLE BANKING.ACCOUNT(
type VARCHAR(30) NOT NULL,
description VARCHAR(100),
balance DEC(13,2) DEFAULT 0,
begin_Bal DEC(13,2),
cust_Id int NOT NULL,
time_Stamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
acc_Num int NOT NULL UNIQUE,
status VARCHAR(10) NOT NULL,
id int GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY
);

CREATE TABLE BANKING.TRANSACTIONS(
time_Stamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
type VARCHAR(10) NOT NULL,
amount DEC(13,2) NOT NULL,
balance DEC(13,2) NOT NULL,
description VARCHAR(100),
id int GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
acc_Id int NOT NULL
);


--ALTER TABLE CUSTOMER ON DELETE CASCADE;
ALTER TABLE BANKING.ACCOUNT ADD FOREIGN KEY (cust_Id) REFERENCES BANKING.CUSTOMER (id);
ALTER TABLE BANKING.TRANSACTIONS ADD FOREIGN KEY (acc_Id) REFERENCES BANKING.ACCOUNT (id) ON DELETE CASCADE;

INSERT INTO BANKING.CUSTOMER (fname, lname, street, city, state, zip, phone, email, username, password, role) VALUES
    ('Eduardo', 'Lopez', '3210 Caleb DR', 'Austin', 'TX', '78725', '5129144856', 'lpz_lalo@yahoo.com', 'admin', 'admin', 'admin');
INSERT INTO BANKING.CUSTOMER (fname, mname, lname, street, city, state, zip, phone, email, username, password, role) VALUES
    ('Ashley', 'Ann', 'Lopez', '3210 Caleb DR', 'Austin', 'TX', '78725', '5125696006', 'ahernandez0203@gmail.com', 'alopez', 'Ah1260418', 'customer');
INSERT INTO BANKING.CUSTOMER (fname, lname, street, city, state, zip, phone, email, username, password, role) VALUES
    ('Alpha', 'Alpine', '3210 Caleb DR', 'Austin', 'TX', '78725', '5129144856', 'lpz_lalo@yahoo.com', '58lalo53', 'lalo53', 'customer');
INSERT INTO BANKING.CUSTOMER (fname, mname, lname, street, city, state, zip, phone, email, username, password, role) VALUES
    ('Bravo', 'A', 'Bates', '3210 Caleb DR', 'Austin', 'TX', '78725', '5125696006', 'ahernandez0203@gmail.com', '59lalo54', 'lalo54', 'customer');
INSERT INTO BANKING.CUSTOMER (fname, lname, street, city, state, zip, phone, email, username, password, role) VALUES
    ('Charlie', 'Charles', '3210 Caleb DR', 'Austin', 'TX', '78725', '5129144856', 'lpz_lalo@yahoo.com', '60lalo55', 'lalo55', 'customer');
INSERT INTO BANKING.CUSTOMER (fname, mname, lname, street, city, state, zip, phone, email, username, password, role) VALUES
    ('Delta', 'D', 'Dell', '3210 Caleb DR', 'Austin', 'TX', '78725', '5125696006', 'ahernandez0203@gmail.com', '61lalo56', 'lalo56', 'customer');
INSERT INTO BANKING.CUSTOMER (fname, lname, street, city, state, zip, phone, email, username, password, role) VALUES
    ('Echo', 'Eclipse', '3210 Caleb DR', 'Austin', 'TX', '78725', '5129144856', 'lpz_lalo@yahoo.com', '63lalo59', 'lalo57', 'customer');
INSERT INTO BANKING.CUSTOMER (fname, mname, lname, street, city, state, zip, phone, email, username, password, role) VALUES
    ('Foxtrot', 'Fox', 'Fisher', '3210 Caleb DR', 'Austin', 'TX', '78725', '5125696006', 'ahernandez0203@gmail.com', '62lalo58', 'lalo58', 'customer');
INSERT INTO BANKING.CUSTOMER (fname, lname, street, city, state, zip, phone, email, username, password, role) VALUES
    ('Golf', 'Lopez', '3210 Caleb DR', 'Austin', 'TX', '78725', '5129144856', 'lpz_lalo@yahoo.com', '64lalo59', 'lalo59', 'admin');
INSERT INTO BANKING.CUSTOMER (fname, mname, lname, street, city, state, zip, phone, email, username, password, role) VALUES
    ('Hotel', 'Ann', 'Lopez', '3210 Caleb DR', 'Austin', 'TX', '78725', '5125696006', 'ahernandez0203@gmail.com', '65lalo60', 'Ah1260418', 'customer');
INSERT INTO BANKING.CUSTOMER (fname, lname, street, city, state, zip, phone, email, username, password, role) VALUES
    ('Indea', 'Alpine', '3210 Caleb DR', 'Austin', 'TX', '78725', '5129144856', 'lpz_lalo@yahoo.com', '66lalo61', 'lalo53', 'customer');
INSERT INTO BANKING.CUSTOMER (fname, mname, lname, street, city, state, zip, phone, email, username, password, role) VALUES
    ('Juliet', 'A', 'Bates', '3210 Caleb DR', 'Austin', 'TX', '78725', '5125696006', 'ahernandez0203@gmail.com', '67lalo62', 'lalo54', 'customer');
INSERT INTO BANKING.CUSTOMER (fname, lname, street, city, state, zip, phone, email, username, password, role) VALUES
    ('Kilo', 'Charles', '3210 Caleb DR', 'Austin', 'TX', '78725', '5129144856', 'lpz_lalo@yahoo.com', '68lalo63', 'lalo55', 'customer');
INSERT INTO BANKING.CUSTOMER (fname, mname, lname, street, city, state, zip, phone, email, username, password, role) VALUES
    ('Lima', 'D', 'Dell', '3210 Caleb DR', 'Austin', 'TX', '78725', '5125696006', 'ahernandez0203@gmail.com', '69lalo64', 'lalo56', 'customer');
INSERT INTO BANKING.CUSTOMER (fname, lname, street, city, state, zip, phone, email, username, password, role) VALUES
    ('Mike', 'Eclipse', '3210 Caleb DR', 'Austin', 'TX', '78725', '5129144856', 'lpz_lalo@yahoo.com', '70lalo65', 'lalo57', 'customer');
INSERT INTO BANKING.CUSTOMER (fname, mname, lname, street, city, state, zip, phone, email, username, password, role) VALUES
    ('November', 'Fox', 'Fisher', '3210 Caleb DR', 'Austin', 'TX', '78725', '5125696006', 'ahernandez0203@gmail.com', '71lalo66', 'lalo58', 'customer');