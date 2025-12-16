#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------

#------------------------------------------------------------
# Table: SALES
#------------------------------------------------------------

CREATE TABLE SALES(
        ID_Sales    INT AUTO_INCREMENT NOT NULL ,
        Name        VARCHAR(50) NOT NULL ,
        Firstname   VARCHAR(50) NOT NULL ,
        Address     VARCHAR(50) NOT NULL ,
        NumberPhone VARCHAR(50) NOT NULL ,
        CONSTRAINT SALES_PK PRIMARY KEY (ID_Sales)
) ENGINE=InnoDB;

#------------------------------------------------------------
# Table: CARS
#------------------------------------------------------------

CREATE TABLE CARS(
        ID_Cars         INT AUTO_INCREMENT NOT NULL ,
        Brand           VARCHAR(50) NOT NULL ,
        Model           VARCHAR(50) NOT NULL ,
        Production_Year DATE NOT NULL ,
        Serial_Number   VARCHAR(50) NOT NULL ,
        Price           FLOAT NOT NULL ,
        CONSTRAINT CARS_PK PRIMARY KEY (ID_Cars)
) ENGINE=InnoDB;

#------------------------------------------------------------
# Table: SHOPPER
#------------------------------------------------------------

CREATE TABLE SHOPPER(
        ID_Shoppers INT AUTO_INCREMENT NOT NULL ,
        Name        VARCHAR(50) NOT NULL ,
        Firstname   VARCHAR(50) NOT NULL ,
        Address     VARCHAR(50) NOT NULL ,
        NumberPhone VARCHAR(50) NOT NULL ,
        CONSTRAINT SHOPPER_PK PRIMARY KEY (ID_Shoppers)
) ENGINE=InnoDB;

#------------------------------------------------------------
# Table: TRANSACTIONS
#------------------------------------------------------------

CREATE TABLE TRANSACTIONS(
        ID_Trans    INT AUTO_INCREMENT NOT NULL ,
        Date_Trans  DATE NOT NULL ,
        Total_Trans FLOAT NOT NULL ,
        ID_Cars     INT NOT NULL ,
        ID_Sales    INT NOT NULL ,
        ID_Shoppers INT NOT NULL ,
        CONSTRAINT TRANSACTIONS_PK PRIMARY KEY (ID_Trans),
        CONSTRAINT TRANSACTIONS_CARS_FK FOREIGN KEY (ID_Cars) REFERENCES CARS(ID_Cars),
        CONSTRAINT TRANSACTIONS_SALES0_FK FOREIGN KEY (ID_Sales) REFERENCES SALES(ID_Sales),
        CONSTRAINT TRANSACTIONS_SHOPPER1_FK FOREIGN KEY (ID_Shoppers) REFERENCES SHOPPER(ID_Shoppers)
) ENGINE=InnoDB;
