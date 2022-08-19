DROP TABLE IF EXISTS RolesTest;
CREATE TABLE RolesTest (
    idRole VARCHAR NOT NULL UNIQUE AUTO_INCREMENT,
    roleName VARCHAR(50),
    PRIMARY KEY (idRole)
);

DROP TABLE IF EXISTS PersonsTest;
CREATE TABLE PersonsTest (
    idPerson VARCHAR NOT NULL UNIQUE AUTO_INCREMENT,
    username VARCHAR(50),
    password VARCHAR(50),
    name VARCHAR(50),
    surname VARCHAR(100),
    age INTEGER,
    roles RolesTest,
    PRIMARY KEY (idPerson)
);
