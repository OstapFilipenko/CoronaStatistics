CREATE TABLE Location(
	LocationID int NOT NULL PRIMARY KEY,
	Lat varchar(100) NOT NULL,
	Longtitude varchar(100) NOT NULL,
	Country varchar(100) NOT NULL,
	Province varchar(100)
);

CREATE TABLE Records(
	DateV varchar(10) NOT NULL,
	Number varchar NOT NULL,
	LocationID int NOT NULL,
	CONSTRAINT PK_Record PRIMARY KEY(DateV, LocationID),
	CONSTRAINT FK_Location FOREIGN KEY(LocationID) REFERENCES Location(LocationID)
);
