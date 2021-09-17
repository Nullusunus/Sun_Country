USE master;  
GO
IF DB_ID ( N'Music' ) IS NOT NULL
DROP DATABASE sql_demo;
GO
CREATE DATABASE sql_demo;  
GO


USE sql_demo
IF OBJECT_ID ('dbo.airplane', 'U') IS NOT NULL  
   DROP TABLE airplane;  
GO  
CREATE TABLE airplane  
(  
   airplaneid  int,  
   callsign  varchar (50),  
   aircraft_type varchar(50),  
   passenger_capacity int,
   max_altitude int,
   max_speed int
);  
GO  

INSERT airplane  
   (airplaneid, callsign, aircraft_type,  passenger_capacity, max_altitude, max_speed)  
VALUES  
   (12345678, 'SC737', '737', 354, 40000, 540);  
  
INSERT airplane  
   (airplaneid, callsign, aircraft_type,  passenger_capacity, max_altitude, max_speed)  
VALUES  
   (12345679, 'SC747', '747', 600, 47000, 640);  
GO
