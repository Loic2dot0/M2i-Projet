/* Creation database if not exist */
CREATE DATABASE IF NOT EXISTS m2i_project;
USE m2i_project;

/* Creation table clients */
CREATE TABLE IF NOT EXISTS clients(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    company_name VARCHAR(255),
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
    type_presta VARCHAR(255),
    designation	VARCHAR(255),
    client_id INT,
    nb_days INT,
    unit_price FLOAT,
    state TINYINT,
    FOREIGN KEY(client_id) REFERENCES clients(id)
);