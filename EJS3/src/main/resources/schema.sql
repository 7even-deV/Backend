DROP TABLE IF EXISTS Roles;
CREATE TABLE Roles (
    idRole VARCHAR NOT NULL UNIQUE AUTO_INCREMENT,
    roleName VARCHAR(50),
    PRIMARY KEY (idRole)
);

DROP TABLE IF EXISTS Persons;
CREATE TABLE Persons (
    idPerson VARCHAR NOT NULL UNIQUE AUTO_INCREMENT,
    username VARCHAR(50),
    password VARCHAR(50),
    name VARCHAR(50),
    surname VARCHAR(100),
    age INTEGER,
    roles Roles,
    PRIMARY KEY (idPerson)
);
