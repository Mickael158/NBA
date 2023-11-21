CREATE TABLE assureur(
    id SERIAL PRIMARY KEY,
    nom VARCHAR
);

CREATE TABLE vehicule (
    id SERIAL PRIMARY KEY,
    nom VARCHAR,
    marque VARCHAR,
    idassureur INT NOT NULL REFERENCES assureur
);

CREATE TABLE kilometrage (
    id SERIAL PRIMARY KEY,
    idvehicule INT NOT NULL REFERENCES vehicule,
    date DATE NOT NULL,
    debut INT,
    fin INT
);

CREATE TABLE catalogueassurance(
    id SERIAL PRIMARY KEY,
    idassureur INT NOT NULL REFERENCES assureur,
    nom VARCHAR,
    prix DOUBLE PRECISION
);

CREATE TABLE assurancevehicule(
    id SERIAL PRIMARY KEY,
    idvehicule INT NOT NULL REFERENCES vehicule,
    idcataloqueassurance INT NOT NULL REFERENCES catalogueassurance,
    date DATE NOT NULL,
    validite INT
);

CREATE TABLE fichevehicule(
    id SERIAL PRIMARY KEY,
    idvehicule INT NOT NULL REFERENCES vehicule,
    vidange INT DEFAULT 5000,
    pneu INT DEFAULT 1000
);

CREATE TABLE typeentretien(
    id SERIAL PRIMARY KEY,
    nom VARCHAR
);

CREATE TABLE entretien(
    id SERIAL PRIMARY KEY,
    idvehicule INT NOT NULL REFERENCES vehicule,
    idtypeentretien INT REFERENCES typeentretien,
    date DATE NOT NULL,
    prix DOUBLE PRECISION
);