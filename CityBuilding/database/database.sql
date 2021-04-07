DROP database IF  exists citybuilding2;
CREATE DATABASE IF NOT EXISTS citybuilding2;
USE citybuilding2;


CREATE TABLE IF NOT EXISTS city
(idCity INT UNIQUE auto_increment PRIMARY KEY,
name VARCHAR(25),
rating numeric,
longitude numeric,
latitude numeric)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS district
(idDistrict INT UNIQUE auto_increment PRIMARY KEY,
name VARCHAR(25),
rating numeric,
xStartCoord INT,
yStartCoord INT,
xEndCoord INT,
yEndCoord INT,
idCity INT,
FOREIGN KEY (idCity) REFERENCES city(idCity))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



CREATE TABLE IF NOT EXISTS layertype
(idLayerType INT UNIQUE auto_increment PRIMARY KEY,
name VARCHAR(20))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS landhydro
(idLandHydro INT UNIQUE auto_increment PRIMARY KEY,
name VARCHAR(20),
xCoord INT,
yCoord INT,
idDistrict INT,
idLayerType INT,
FOREIGN KEY(idDistrict) REFERENCES district(idDistrict),
FOREIGN KEY(idLayerType) REFERENCES layertype(idLayerType))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;




CREATE TABLE IF NOT EXISTS placetype
(idPlaceType INT UNIQUE auto_increment PRIMARY KEY,
placeName VARCHAR(30))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS contact
(idContact INT UNIQUE auto_increment PRIMARY KEY,
address VARCHAR (40),
phoneNo VARCHAR(15),
website VARCHAR(30),
email VARCHAR(30))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



CREATE TABLE IF NOT EXISTS place
(idPlace INT UNIQUE auto_increment PRIMARY KEY,
placeName VARCHAR(20),
capacity INT,
availability VARCHAR(10),
rating numeric,
xCoord INT,
yCoord INT,
idContact INT,
idDistrict INT,
idPlaceType INT,
FOREIGN KEY(idDistrict) REFERENCES district(idDistrict),
FOREIGN KEY(idContact) REFERENCES contact(idContact),
FOREIGN KEY(idPlaceType) REFERENCES placetype(idPlaceType))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



CREATE TABLE IF NOT EXISTS photo
(idPhoto INT UNIQUE auto_increment PRIMARY KEY,
path VARCHAR(100),
idPlace INT,
FOREIGN KEY(idPlace) REFERENCES place(idPlace))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS amenity
(idAmenity INT UNIQUE auto_increment PRIMARY KEY,
name VARCHAR(20),
description VARCHAR(50),
idPlace INT,
FOREIGN KEY(idPlace) REFERENCES place(idPlace))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



CREATE TABLE IF NOT EXISTS bug
(idBug INT UNIQUE auto_increment PRIMARY KEY,
description VARCHAR(255),
idPlace INT,
FOREIGN KEY(idPlace) REFERENCES place(idPlace))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;











