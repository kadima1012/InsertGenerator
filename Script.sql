#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: SALES
#------------------------------------------------------------

CREATE TABLE SALES(
        ID_Sales    Int  Auto_increment  NOT NULL ,
        Name        Varchar (50) NOT NULL ,
        Firstname   Varchar (50) NOT NULL ,
        Address     Varchar (50) NOT NULL ,
        NumberPhone Varchar (50) NOT NULL
	,CONSTRAINT SALES_PK PRIMARY KEY (ID_Sales)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: CARS
#------------------------------------------------------------

CREATE TABLE CARS(
        ID_Cars         Int  Auto_increment  NOT NULL ,
        Brand           Varchar (50) NOT NULL ,
        Model           Varchar (50) NOT NULL ,
        Production_Year Date NOT NULL ,
        Serial_Number   Varchar (50) NOT NULL ,
        Price           Float NOT NULL
	,CONSTRAINT CARS_PK PRIMARY KEY (ID_Cars)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: SHOPPER
#------------------------------------------------------------

CREATE TABLE SHOPPER(
        ID_Shoppers Int  Auto_increment  NOT NULL ,
        Name        Varchar (50) NOT NULL ,
        Firstname   Varchar (50) NOT NULL ,
        Address     Varchar (50) NOT NULL ,
        NumberPhone Varchar (50) NOT NULL
	,CONSTRAINT SHOPPER_PK PRIMARY KEY (ID_Shoppers)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: TRANSACTIONS
#------------------------------------------------------------

CREATE TABLE TRANSACTIONS(
        ID_Trans    Int  Auto_increment  NOT NULL ,
        Date_Trans  Date NOT NULL ,
        Total_Trans Float NOT NULL ,
        ID_Cars     Int NOT NULL ,
        ID_Sales    Int NOT NULL ,
        ID_Shoppers Int NOT NULL
	,CONSTRAINT TRANSACTIONS_PK PRIMARY KEY (ID_Trans)

	,CONSTRAINT TRANSACTIONS_CARS_FK FOREIGN KEY (ID_Cars) REFERENCES CARS(ID_Cars)
	,CONSTRAINT TRANSACTIONS_SALES0_FK FOREIGN KEY (ID_Sales) REFERENCES SALES(ID_Sales)
	,CONSTRAINT TRANSACTIONS_SHOPPER1_FK FOREIGN KEY (ID_Shoppers) REFERENCES SHOPPER(ID_Shoppers)
)ENGINE=InnoDB;

