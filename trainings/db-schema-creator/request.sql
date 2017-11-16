CREATE TABLE employees(
table_number serial PRIMARY KEY NOT NULL,
id_division serial  NOT NULL,
id_position serial  NOT NULL,
surname varchar(50)  NOT NULL,
middle_name varchar(50)  NOT NULL,
first_name varchar(50)  NOT NULL
);CREATE TABLE positions(
id_position serial PRIMARY KEY NOT NULL,
name varchar(50)  NOT NULL
);CREATE TABLE divisions(
id_division serial PRIMARY KEY NOT NULL,
name varchar(50)  NOT NULL
);