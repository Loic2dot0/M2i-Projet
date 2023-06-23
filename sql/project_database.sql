/* Erase database */
DROP DATABASE IF EXISTS m2i_project;

/* Creation database if not exist */
CREATE DATABASE IF NOT EXISTS m2i_project;
USE m2i_project;

/* Creation table clients */
CREATE TABLE IF NOT EXISTS clients(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    company_name VARCHAR(255) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(255),
    phone VARCHAR(50),
    address VARCHAR(255),
    zip_code VARCHAR(50),
    city VARCHAR(50),
    country VARCHAR(50),
    state TINYINT
);

/* creation table orders */
CREATE TABLE IF NOT EXISTS orders(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type_presta VARCHAR(255) NOT NULL,
    designation	VARCHAR(255) NOT NULL,
    client_id INT NOT NULL,
    nb_days INT DEFAULT NULL,
    unit_price FLOAT DEFAULT NULL,
    state TINYINT DEFAULT NULL,
    FOREIGN KEY(client_id) REFERENCES clients(id)
);

/* insert clients */
INSERT INTO clients (company_name, first_name, last_name, email, phone, address, zip_code, city, country, state)
    VALUES
        ('Capgemini', 'Fabrice', 'Martin', 'martin@mail.com', '06 56 85 84 33', 'abc', 'xyz', 'Nantes', 'France', 0),
        ('M2I Formation', 'Julien', 'Lamard', 'lamard@mail.com', '06 11 22 33 44', 'abc', 'xyz', 'Paris', 'France', 1),
        ('Capgemini', 'Jean Claude', 'Convenant', 'convenant@mail.com', '06 01 02 03 04', 'abc', 'xyz', 'Nantes', 'France', 1),
        ('M2I Formation', 'Bernard', 'Minet', 'minet@mail.com', '06 60 10 12 30', 'abc', 'xyz', 'Paris', 'France', 0);


/* insert data */
INSERT INTO orders (type_presta, designation, client_id, nb_days, unit_price, state)
    VALUES
        ('Formation', 'Angular init', 2, 3, 1200, 0),
        ('Formation', 'React avancé', 2, 3, 1000, 2),
        ('Coaching', 'React Techlead', 1, 20, 900, 2),
        ('Coaching', 'Nest.js Techlead', 1, 50, 800, 1);

INSERT INTO orders (type_presta, designation, client_id)
    VALUES
        ('Coaching', 'React Techlead', 3),
        ('Coaching', 'Jakarta EE', 3),
        ('Coaching', 'Angular Teachlead', 4);