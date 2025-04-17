SET search_path TO projet;


-- Supprime toutes les données
DELETE FROM garde;
DELETE FROM contrat;
DELETE FROM parent;
DELETE FROM compte;

-- Insère les données

-- Compte

INSERT INTO compte (idcompte, pseudo, motdepasse, email, flagadmin ) VALUES 
( 1, 'geek', 'geek', 'geek@jfox.fr', TRUE ),
( 2, 'chef', 'chef', 'chef@jfox.fr', FALSE ),
( 3, 'job', 'job', 'job@jfox.fr', FALSE ),
( 4, 'wil', 'wil', 'wil@jfox.fr', FALSE ),
( 5, 'evans', 'evans', 'evans@jfox.fr', FALSE );

ALTER TABLE compte ALTER COLUMN idcompte RESTART WITH 6;
 
-- Parent
INSERT INTO parent (IdParent, IdCompte, Nom, Prenom, Adresse, Email, Telephone) VALUES
(1, 2, 'Dupont', 'Marie', '123 Rue des Fleurs', 'marie.dupont@example.com', '0123456789'),
(2, 3, 'Leroy', 'Paul', '321 Chemin des Écoles', 'paul.leroy@example.com', '0162487530');

ALTER TABLE parent ALTER COLUMN idparent RESTART WITH 3;

--contrat
INSERT INTO contrat (IdContrat, IdParent, NomEnfant, PrenomEnfant, DateNaissance, DateDebut, DateFin, TarifHoraire, IndemniteTauxHoraire, IndemniteMontantMinimum, IndemniteRepas) VALUES
(1, 1, 'Lucas', 'Dupont', '2018-05-15', '2023-09-01', '2024-06-30', 15.00, 1.50, 5.00, 3.00),
(2, 2, 'Emma', 'Martin', '2019-03-22', '2023-09-01', '2024-06-30', 14.50, 1.40, 4.50, 2.50),
(3, 1, 'Noah', 'Durand', '2017-12-05', '2023-09-01', '2024-06-30', 16.00, 1.60, 6.00, 4.00);

ALTER TABLE contrat ALTER COLUMN idcontrat RESTART WITH 4;

--garde
INSERT INTO garde (IdGarde, IdContrat, date, HeureArrivee, HeureDepart, NombreRepasPris) VALUES
(1, 1, '2023-10-01', '08:00', '17:00', 3),
(2, 2, '2023-10-02', '09:00', '16:00', 2),
(3, 3, '2023-10-03', '08:30', '17:30', 0);

ALTER TABLE garde ALTER COLUMN idgarde RESTART WITH 4;

--Tarif
INSERT INTO tarifs (idtarif, tauxHoraire, indemnitesEntretienTauxHoraire, indemnitesEntretienMinJour, indemnitesRepas) VALUES 
(1, 3.57, 0.352, 2.65, 5.00);  -- Tarifs standards

ALTER TABLE tarifs ALTER COLUMN idtarif RESTART WITH 2;
