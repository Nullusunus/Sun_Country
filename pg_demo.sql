--CREATE DATABASE [IF NOT EXISTS] pg_testdb
SELECT 'CREATE DATABASE pg_testdb' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'pg_testdb')\gexec

\c pg_testdb

/* airplane table containing the following information on airplanes:

	callsign: Alpha numeric text containing the airplane's callsign
   	aircraft_type: contains the text for the type of airplane
    passenger_capacity: max number of passengers the airplane can seat
    max_altitude: max height the plane can fly in feet
    max_speed: max speed the plane can fly miles per hour
*/
CREATE TABLE IF NOT EXISTS airplane (
	airplaneid INT PRIMARY KEY,
	callsign TEXT,
	aircraft_type TEXT,
	passenger_capacity INT,
	max_altitude INT,
	max_speed INT
);

INSERT INTO airplane (airplaneid, callsign, aircraft_type, passenger_capacity, max_altitude, max_speed) VALUES (12345, 'SCX3006', 'B738', 210, 41000, 530);
INSERT INTO airplane (airplaneid, callsign, aircraft_type, passenger_capacity, max_altitude, max_speed) VALUES (67890, 'SCX1177', 'B711', 211, 41001, 531);
SELECT * FROM airplane;