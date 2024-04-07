CREATE TABLE airplanes(
id INT PRIMARY KEY AUTO_INCREMENT,
model varchar(255) NOT NULL, 
capacity INT NOT NULL
);

CREATE TABLE passengers(
id INT PRIMARY KEY AUTO_INCREMENT,
name_passenger varchar(255) NOT NULL,
lastname varchar(255) NOT NULL, 
identity_card varchar(255) NOT NULL
);

CREATE TABLE flights(
id INT PRIMARY KEY AUTO_INCREMENT,
destination varchar(255) NOT NULL,
departure_date DATE NOT NULL,
departure_time TIME NOT NULL,
id_airplane INT,
CONSTRAINT fk_id_airplane FOREIGN KEY(id_airplane) REFERENCES airplanes(id) ON DELETE CASCADE
);

CREATE TABLE seats(
id INT PRIMARY KEY AUTO_INCREMENT,
seat_code varchar(255) NOT NULL,
availability BOOLEAN NOT NULL,
id_flight INT,
CONSTRAINT fk_id_flight1 FOREIGN KEY(id_flight) REFERENCES flights(id) ON DELETE CASCADE
);

CREATE TABLE reservations(
id INT PRIMARY KEY AUTO_INCREMENT,
reservation_date DATE NOT NULL,
reservation_time TIME NOT NULL,
seat varchar(255) NOT NULL,
id_passengers INT,
CONSTRAINT fk_id_passengers FOREIGN KEY(id_passengers) REFERENCES passengers(id) ON DELETE CASCADE,
id_flight INT,
CONSTRAINT fk_id_flight FOREIGN KEY(id_flight) REFERENCES flights(id) ON DELETE CASCADE
);

SELECT * FROM seats;
SELECT * FROM airplanes;
SELECT * FROM flights;
SELECT * FROM reservations;
SELECT * FROM passengers;

INSERT INTO passengers (name_passenger, lastname, identity_card) VALUES
('Keity', 'Ortega', '1017249128A'),
('Dania', 'Ortega', '1017249127B'),
('Margarita', 'Muñoz', '43547206C'),
('Mery', 'Muñoz', '43547207D'),
('Andrés', 'Péres', '1017249128E');