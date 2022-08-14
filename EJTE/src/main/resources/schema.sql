DROP TABLE IF EXISTS Persons;

CREATE TABLE Persons (
    id INTEGER NOT NULL UNIQUE AUTO_INCREMENT,
    username VARCHAR(50),
    password VARCHAR(50),
    name VARCHAR(50),
    surname VARCHAR(100),
    age INTEGER,
    PRIMARY KEY (id)
);
