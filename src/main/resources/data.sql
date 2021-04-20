-- Les données qui seront initialisées automatiquement quand on lance l'application
-- cf. application.properties

INSERT INTO Utilisateur(DTYPE,id,username,password,email,nom, prenom,adresse,ville, numtel, numsecu,datenaissance) VALUES
    ('Medecin',1,'wasslamenace','WassLaMenace','wass.aityoucef@gmail.com','Aityoucef','Wassim','24 rue de la Tolosane','Castres','0782350075','101026912312312',TO_DATE('1986-01-11', 'YYYY-MM-DD')),
    ('Medecin',2,'RemiBastide','remibastide','remibastide@gmail.com','Bastide', 'Remi','6 rue de la Paix','Albi','0606060607','193011348648755',TO_DATE('1972-06-18', 'YYYY-MM-DD')),
    ('Patient',3,'narcolin','jesuiscolin','colin.gay25@gmail.com','Gay','Colin','11 place de la République','Castres','0783734322','198077578943488',TO_DATE('2001-02-25', 'YYYY-MM-DD')),
    ('Patient',4,'flotov','velodrome13pastis','thauvin.florian@bouillabaisse.com','Thauvin','Florian','51 rue du Parc','Marseille','0613131313','187068186734057',TO_DATE('1983-11-04', 'YYYY-MM-DD'));


/*INSERT INTO Personne(DTYPE, nom, prenom, adresse, ville, numtel) VALUES 
    ('Medecin', 'Aityoucef', 'Wassim','24 rue de la Tolosane','81100 Castres','0782350075'),
    ('Medecin', 'Bastide', 'Remi','2 rue de la Paix','81000 Albi','0606060606'),
    ('Patient', 'Gay', 'Colin','11 place de la République','81100 Castres','0783734322'),
    ('Patient', 'Thauvin', 'Florian','51 rue du Pastis','13000 Marseille','0613131313'),
    ('Patient', 'Bernardoni', 'Paul','17 avenue De Gaulle','75000 Paris','0610203040'),
    ('Patient', 'Vidal', 'Antoine','2 rue Beaujeu','81100 Castres','0622334455');

INSERT INTO Patient(num_secu, date_naissance) VALUES
    ('101026912312312','25/02/2001'),
    ('193011348648755','18/08/1998'),
    ('198077578943488','20/06/2000'),
    ('187068186734057','01/09/2004');

INSERT INTO Medecin(num_rpps, specialite,diplome) VALUES
    ('15448','Psychologie',TRUE),
    ('45645','Psychothérapie', TRUE);

sINSERT INTO Bracelet(numSerie, freqcard, coordGPS) VALUES
    ('DBZ831235','120','39° 17′ N, 76° 36′ O'),
    ('SNK943534','120','41° 22′ N, 70° 11′ O'),
    ('JJK545307','120','30° 31′ N, 79° 33′ O'),
    ('MHA815615','120','37° 26′ N, 69° 19′ O');*/
