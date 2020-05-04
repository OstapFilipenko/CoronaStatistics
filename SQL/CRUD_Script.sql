INSERT INTO Location (LocationID, Lat, Longtitude, Country, Province) VALUES (1, '234.32423', '432.34234', 'Germany', '');
INSERT INTO Records (DateV, Number, LocationID) VALUES ('02/05/20', 823, 1);

DELETE FROM Location WHERE LocationID = 1;
DELETE FROM Records WHERE LocationID = 2;

SELECT * FROM Location;
SELECT * FROM Records;

UPDATE Location SET Lat='324.3244' WHERE LocationID = 1;
UPDATE Records SET Number=423 WHERE LocationID = 2 AND DateV is '02/05/20';