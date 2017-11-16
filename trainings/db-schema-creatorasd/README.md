# JDBC tasks

* create console app for getting structure of PostgreSQL database and
  creating .sql file of SQL-requests for creating the database with 
  this structure;
* write the test for checking dump of WorldDB and dump of own created
  database.
 
## Results
*Console app and test works right.*
But I don't know how to get this line from sql-database:

```
CONSTRAINT country_continent_check CHECK ((((((((continent = 'Asia'::text) OR (continent = 'Europe'::text)) OR (continent = 'North America'::text)) OR (continent = 'Africa'::text)) OR (continent = 'Oceania'::text)) OR (continent = 'Antarctica'::text)) OR (continent = 'South America'::text)))
```