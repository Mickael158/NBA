CREATE TABLE joueur(
    idJoueur SERIAL PRIMARY KEY ,
    nom VARCHAR,
    prenom VARCHAR
);

CREATE TABLE equipe(
  idEquipe SERIAL PRIMARY KEY ,
  nom VARCHAR
);

CREATE TABLE post(
  idPost SERIAL PRIMARY KEY ,
  nom VARCHAR,
  accr VARCHAR
);

CREATE TABLE composition(
    idComposition SERIAL PRIMARY KEY ,
    idJoueur INT,
    idEquipe INT,
    numero VARCHAR,
    idPost INT,
    FOREIGN KEY (idJoueur) REFERENCES joueur(idJoueur),
    FOREIGN KEY (idEquipe) REFERENCES equipe(idEquipe)
);

CREATE TABLE match(
  idMatch SERIAL PRIMARY KEY ,
  idEquipe1 INT,
  idEquipe2 INT,
  dates timestamp,
  FOREIGN KEY (idEquipe1) REFERENCES equipe(idEquipe),
  FOREIGN KEY (idEquipe2) REFERENCES equipe(idEquipe)
);

CREATE TABLE action(
  idAction VARCHAR PRIMARY KEY ,
  nom VARCHAR
);

CREATE TABLE statistiquematch(
  idStatistiqueMatch SERIAL PRIMARY KEY,
  idMatch INT,
  idJoueur INT,
  idAction VARCHAR,
  dates timestamp,
  FOREIGN KEY (idMatch) REFERENCES  match(idMatch),
  FOREIGN KEY (idJoueur) REFERENCES joueur(idJoueur),
  FOREIGN KEY (idAction) REFERENCES action(idAction)
);
SELECT  EXTRACT(EPOCH FROM (sm2.dates - sm1.dates)) / 3600 AS difference_hours FROM statistiquematch sm1
    JOIN
      statistiquematch sm2 ON sm1.idMatch = sm2.idMatch
    WHERE
      sm1.idAction = '1' AND sm2.idAction = '2'; 



INSERT INTO joueur (nom, prenom) VALUES
('Jordan', 'Michael'),
('Bryant', 'Kobe'),
('James', 'LeBron'),
('Curry', 'Stephen');

INSERT INTO equipe (nom) VALUES
('Chicago Bulls'),
('Los Angeles Lakers'),
('Cleveland Cavaliers'),
('Golden State Warriors');

INSERT INTO post (nom, accr) VALUES
('Arrière', 'AR'),
('Ailier', 'AL'),
('Pivot', 'PI'),
('Meneur', 'ME');

INSERT INTO composition (idJoueur, idEquipe, numero, idPost) VALUES
(1, 1, '23', 1),
(2, 2, '24', 1),
(3, 3, '6', 2),
(4, 4, '30', 2);

INSERT INTO match (idEquipe1, idEquipe2, dates) VALUES
(1, 2, '2023-01-01 18:00:00'),
(3, 4, '2023-02-15 20:30:00'),
(2, 3, '2023-03-10 19:45:00');

INSERT INTO action (idAction, nom) VALUES
('1PT', 'Lancer franc'),
('2PT', 'Panier à deux points'),
('3PT', 'Panier à trois points');

INSERT INTO statistiquematch (idMatch, idJoueur, idAction, dates) VALUES
(1, 1, '2PT', '2023-01-01 18:15:00'),
(1, 2, '3PT', '2023-01-01 18:20:00'),
(2, 3, '1PT', '2023-02-15 21:00:00'),
(2, 4, '2PT', '2023-02-15 21:10:00'),
(3, 2, '3PT', '2023-03-10 20:00:00'),
(3, 3, '2PT', '2023-03-10 20:10:00');

SELECT AVG(nb_actions_1pt) AS moyenne_actions_1pt
FROM (
    SELECT idMatch, COUNT(*) AS nb_actions_1pt
    FROM statistiquematch
    WHERE idAction = '1PT'
    GROUP BY idMatch
) AS sous_requete;
