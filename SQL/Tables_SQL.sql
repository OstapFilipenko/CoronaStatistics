CREATE TABLE Locations(
	Lat float NOT NULL,
	Longtitude float NOT NULL,
	Country varchar(100)  NOT NULL,
	Province varchar(100) NOT NULL,
	CONSTRAINT PK_Location PRIMARY KEY (Country, Province),
);

CREATE TABLE Records(
	DateV Date NOT NULL,
	Infections int NOT NULL,
	Country varchar(100) NOT NULL,
	Province varchar(100) NOT NULL,
	CONSTRAINT PK_Records PRIMARY KEY(DateV, Country, Province),
	CONSTRAINT FK_Locations FOREIGN KEY(Country, Province) REFERENCES Locations(Country, Province),
);
