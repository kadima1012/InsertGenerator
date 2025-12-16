CREATE TABLE Marque(
   Id_Marque INT AUTO_INCREMENT,
   Nom_marque VARCHAR(50),
   PRIMARY KEY(Id_Marque)
);

CREATE TABLE Voiture(
   Id_Voiture INT AUTO_INCREMENT,
   Num_serie INT,
   Nom_model VARCHAR(50),
   Id_Marque INT NOT NULL,
   PRIMARY KEY(Id_Voiture),
   FOREIGN KEY(Id_Marque) REFERENCES Marque(Id_Marque)
);
