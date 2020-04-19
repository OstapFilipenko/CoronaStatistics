CREATE TABLE Location{
	Lat varchar(100) NOT NULL,
	Longtitude varchar(100) NOT NULL,
	Country varchar(100) NOT NULL,
	Province varchar(100),
	CONSTRAINT PK_Location PRIMARY KEY(Country, Province)
}

CREATE TABLE Records{
	RId varchar NOT NULL PRIMARY KEY,
	Date varchar(10) NOT NULL,
	Number varchar NOT NULL,
	LocationID varchar NOT NULL,
	CONSTRAINT FK_Records FOREIGN KEY(PK_Location) REFERENCES Location(PK_Location)
}