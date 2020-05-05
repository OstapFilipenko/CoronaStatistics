package DB;

public class CRUD_Operations {
/*
    Before Writing into the table, first check which elements should be posted in the table, (dont write object twice into table)
    How to do that:
        Location Table:
            loop through the list<Location> try to select that from table if everything is ok then do nothing, otherwise in the catch block INSERT INTO with values of that object
        Records:
            the same principle like Location, but create select where number = object number, date = object date, country = object country

    !!!!!ATTENTION!!!!!
    First try to Insert Dates with described principle above, then can be read, deleted, adn updated.

 */





}
