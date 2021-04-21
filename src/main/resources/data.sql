-- Les données qui seront initialisées automatiquement quand on lance l'application
-- cf. application.properties

INSERT INTO Utilisateur(DTYPE,id,username,password,email,nom, prenom,adresse,ville, numtel, numsecu,datenaissance,nommedecin, numrpps,specialite) VALUES
    ('Medecin',1,'wasslamenace','WassLaMenace','wass.aityoucef@gmail.com','Aityoucef','Wassim','24 rue de la Tolosane','Castres','0782350075',null,null,null,123456789,'Pédopsychiatre'),
    ('Medecin',2,'RemiBastide','remibastide','remibastide@gmail.com','Bastide', 'Remi','6 rue de la Paix','Albi','0606060607',null, null, null, 987654321, 'Psychiatre'),
    ('Patient',3,'narcolin','jesuiscolin','colin.gay25@gmail.com','Gay','Colin','11 place de la République','Castres','0783734322','198077578943488',TO_DATE('2001-02-25', 'YYYY-MM-DD'),'Bastide',null,null),
    ('Patient',4,'flotov','velodrome13pastis','thauvin.florian@bouillabaisse.com','Thauvin','Florian','51 rue du Parc','Marseille','0613131313','187068186734057',TO_DATE('1983-11-04', 'YYYY-MM-DD'),'Lepic',null,null);
