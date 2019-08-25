/* Nom du schéma : incocar  mdp : mdpSQL007/*- ¨*/

CREATE TABLE locality (
  name varchar(45)  PRIMARY KEY NOT NULL unique,
  zip_code varchar(8) NOT NULL unique,
  country varchar(45) NOT NULL DEFAULT 'Belgique'
);


CREATE TABLE manufacturer (
  name varchar(30) PRIMARY KEY NOT NULL
);


CREATE TABLE model (
  name varchar(40) PRIMARY KEY NOT NULL,
  manufacturer varchar(30)  NOT NULL,
  FOREIGN KEY (manufacturer) 
    REFERENCES manufacturer (name)
);


CREATE TABLE supplier (
  tva_number varchar(20) PRIMARY KEY NOT NULL UNIQUE,
  name varchar(45)  NOT NULL,
  street varchar(60)  DEFAULT NULL,
  street_number varchar(8)  DEFAULT NULL,
  phone_number varchar(20)  DEFAULT NULL,
  locality varchar(45) DEFAULT NULL,
  FOREIGN KEY (locality) 
    REFERENCES locality (name)
);

CREATE TABLE client (
  client_id int(11) PRIMARY KEY AUTO_INCREMENT,
  last_name varchar(45)  NOT NULL,
  first_name varchar(45)  NOT NULL,
  street varchar(60)  DEFAULT NULL,
  street_number varchar(8)  DEFAULT NULL,
  phone_number varchar(20)  DEFAULT NULL,
  tva_number varchar(20)  DEFAULT NULL,

  locality varchar(45) DEFAULT NULL,
  FOREIGN KEY (locality) 
    REFERENCES locality (name)
);




CREATE TABLE vehicle (
  chassis_number varchar(25) PRIMARY KEY,
  color varchar(40) DEFAULT NULL,
  first_registration_date date DEFAULT NULL,
  engine_cylinder int(11),
  engine_energy varchar(20) NOT NULL,
  engine_power int(11),
  buy_price double check (buy_price >= 0),
  buy_date date NOT NULL,
  mileage int(11),
  notes varchar(255)  DEFAULT NULL,
  is_on_sale tinyint(1) NOT NULL,
  has_immat_certificate tinyint(1) NOT NULL,
  has_technical_control tinyint(1) NOT NULL,
  has_conformity_certificate tinyint(1) NOT NULL,
  selling_on_facebook tinyint(1) NOT NULL,
  selling_on_second_hand tinyint(1) NOT NULL,
  selling_on_autoscout tinyint(1) NOT NULL,


  model varchar(30) NOT NULL,
  FOREIGN KEY (model)
    REFERENCES model (name),

  tva_supplier varchar(20),
  FOREIGN KEY (tva_supplier)
    REFERENCES supplier (tva_number)


);

CREATE TABLE bill (
  bill_id int(11) PRIMARY KEY AUTO_INCREMENT,
  is_sale tinyint(1) NOT NULL,
  bill_date date NOT NULL,
  price double NOT NULL check (price > 0),

  vehicle_chassis varchar(25),
  FOREIGN KEY (vehicle_chassis)
   REFERENCES vehicle(chassis_number) ON DELETE CASCADE,

  client int(11),
  FOREIGN KEY (client)
   REFERENCES client(client_id)
);

CREATE TABLE payment (
  payment_id int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  type varchar(20)  NOT NULL,
  amount double NOT NULL,
  check_number varchar(20)  DEFAULT NULL,
  bill int(11) NOT NULL,
  FOREIGN KEY (bill)
    REFERENCES bill (bill_id) ON DELETE CASCADE
);



insert into locality(name,zip_code,country) values ("Aiseau-Presles",6250,"Belgique");
insert into locality(name,zip_code,country) values ("Amay",4540,"Belgique");
insert into locality(name,zip_code,country) values ("Amel",4770,"Belgique");
insert into locality(name,zip_code,country) values ("Anderlues",6150,"Belgique");
insert into locality(name,zip_code,country) values ("Anhée",5537,"Belgique");
insert into locality(name,zip_code,country) values ("Ans",4430,"Belgique");
insert into locality(name,zip_code,country) values ("Anthisnes",4160,"Belgique");
insert into locality(name,zip_code,country) values ("Assesse",5330,"Belgique");
insert into locality(name,zip_code,country) values ("Attert",6717,"Belgique");
insert into locality(name,zip_code,country) values ("Aubange",6791,"Belgique");
insert into locality(name,zip_code,country) values ("Aubel",4880,"Belgique");
insert into locality(name,zip_code,country) values ("Awans",4340,"Belgique");
insert into locality(name,zip_code,country) values ("Aywaille",4920,"Belgique");
insert into locality(name,zip_code,country) values ("Eghezée",5310,"Belgique");
insert into locality(name,zip_code,country) values ("Ellezelles",7890,"Belgique");
insert into locality(name,zip_code,country) values ("Engis",4480,"Belgique");
insert into locality(name,zip_code,country) values ("Erquelinnes",6560,"Belgique");
insert into locality(name,zip_code,country) values ("Esneux",4130,"Belgique");
insert into locality(name,zip_code,country) values ("Estaimpuis",7730,"Belgique");
insert into locality(name,zip_code,country) values ("Estinnes",7120,"Belgique");
insert into locality(name,zip_code,country) values ("Incourt",1315,"Belgique");
insert into locality(name,zip_code,country) values ("Ittre",1460,"Belgique");
insert into locality(name,zip_code,country) values ("Ohey",5350,"Belgique");
insert into locality(name,zip_code,country) values ("Olne",4877,"Belgique");
insert into locality(name,zip_code,country) values ("Onhaye",5520,"Belgique");
insert into locality(name,zip_code,country) values ("Oreye",4360,"Belgique");
insert into locality(name,zip_code,country) values ("Orp-Jauche",1350,"Belgique");
insert into locality(name,zip_code,country) values ("Ouffet",4590,"Belgique");
insert into locality(name,zip_code,country) values ("Oupeye",4684,"Belgique");
insert into locality(name,zip_code,country) values ("Yvoir",5530,"Belgique");
insert into locality(name,zip_code,country) values ("Ecaussinnes",7190,"Belgique");
insert into locality(name,zip_code,country) values ("Erezée",6997,"Belgique");
insert into locality(name,zip_code,country) values ("Etalle",6740,"Belgique");
insert into locality(name,zip_code,country) values ("Baelen",4837,"Belgique");
insert into locality(name,zip_code,country) values ("Bassenge",4690,"Belgique");
insert into locality(name,zip_code,country) values ("Beauvechain",1320,"Belgique");
insert into locality(name,zip_code,country) values ("Beloeil",7972,"Belgique");
insert into locality(name,zip_code,country) values ("Berloz",4257,"Belgique");
insert into locality(name,zip_code,country) values ("Bernissart",7320,"Belgique");
insert into locality(name,zip_code,country) values ("Bertogne",6687,"Belgique");
insert into locality(name,zip_code,country) values ("Bertrix",6880,"Belgique");
insert into locality(name,zip_code,country) values ("Beyne-Heusay",4610,"Belgique");
insert into locality(name,zip_code,country) values ("Bièvre",5555,"Belgique");
insert into locality(name,zip_code,country) values ("Blégny",4670,"Belgique");
insert into locality(name,zip_code,country) values ("Boussu",7300,"Belgique");
insert into locality(name,zip_code,country) values ("Braine-l'Alleud",1420,"Belgique");
insert into locality(name,zip_code,country) values ("Braine-le-Château",1440,"Belgique");
insert into locality(name,zip_code,country) values ("Braives",4260,"Belgique");
insert into locality(name,zip_code,country) values ("Brugelette",7940,"Belgique");
insert into locality(name,zip_code,country) values ("Brunehaut",7620,"Belgique");
insert into locality(name,zip_code,country) values ("Burdinne",4210,"Belgique");
insert into locality(name,zip_code,country) values ("Burg-Reuland",4791,"Belgique");
insert into locality(name,zip_code,country) values ("Büllingen",4760,"Belgique");
insert into locality(name,zip_code,country) values ("Bütgenbach",4750,"Belgique");
insert into locality(name,zip_code,country) values ("Celles (lez-Tournai)",7760,"Belgique");
insert into locality(name,zip_code,country) values ("Cerfontaine",5630,"Belgique");
insert into locality(name,zip_code,country) values ("Chapelle-lez-Herlaimont",7160,"Belgique");
insert into locality(name,zip_code,country) values ("Chastre",1450,"Belgique");
insert into locality(name,zip_code,country) values ("Chaudfontaine",4053,"Belgique");
insert into locality(name,zip_code,country) values ("Chaumont-Gistoux",1325,"Belgique");
insert into locality(name,zip_code,country) values ("Clavier",4560,"Belgique");
insert into locality(name,zip_code,country) values ("Colfontaine",7340,"Belgique");
insert into locality(name,zip_code,country) values ("Comblain-au-Pont",4170,"Belgique");
insert into locality(name,zip_code,country) values ("Courcelles",6180,"Belgique");
insert into locality(name,zip_code,country) values ("Court-Saint-Etienne",1490,"Belgique");
insert into locality(name,zip_code,country) values ("Crisnée",4367,"Belgique");
insert into locality(name,zip_code,country) values ("Dalhem",4607,"Belgique");
insert into locality(name,zip_code,country) values ("Daverdisse",6929,"Belgique");
insert into locality(name,zip_code,country) values ("Dison",4820,"Belgique");
insert into locality(name,zip_code,country) values ("Doische",5680,"Belgique");
insert into locality(name,zip_code,country) values ("Donceel",4357,"Belgique");
insert into locality(name,zip_code,country) values ("Dour",7370,"Belgique");
insert into locality(name,zip_code,country) values ("Faimes",4317,"Belgique");
insert into locality(name,zip_code,country) values ("Farciennes",6240,"Belgique");
insert into locality(name,zip_code,country) values ("Fauvillers",6637,"Belgique");
insert into locality(name,zip_code,country) values ("Fernelmont",5380,"Belgique");
insert into locality(name,zip_code,country) values ("Ferrières",4190,"Belgique");
insert into locality(name,zip_code,country) values ("Fexhe-le-Haut-Clocher",4347,"Belgique");
insert into locality(name,zip_code,country) values ("Flobecq",7880,"Belgique");
insert into locality(name,zip_code,country) values ("Floreffe",5150,"Belgique");
insert into locality(name,zip_code,country) values ("Florennes",5620,"Belgique");
insert into locality(name,zip_code,country) values ("Flémalle",4400,"Belgique");
insert into locality(name,zip_code,country) values ("Fléron",4620,"Belgique");
insert into locality(name,zip_code,country) values ("Frameries",7080,"Belgique");
insert into locality(name,zip_code,country) values ("Frasnes-lez-Anvaing",7911,"Belgique");
insert into locality(name,zip_code,country) values ("Froidchapelle",6440,"Belgique");
insert into locality(name,zip_code,country) values ("Gedinne",5575,"Belgique");
insert into locality(name,zip_code,country) values ("Geer",4250,"Belgique");
insert into locality(name,zip_code,country) values ("Gerpinnes",6280,"Belgique");
insert into locality(name,zip_code,country) values ("Gesves",5340,"Belgique");
insert into locality(name,zip_code,country) values ("Gouvy",6671,"Belgique");
insert into locality(name,zip_code,country) values ("Grez-Doiceau",1390,"Belgique");
insert into locality(name,zip_code,country) values ("Grâce-Hollogne",4460,"Belgique");
insert into locality(name,zip_code,country) values ("Habay",6720,"Belgique");
insert into locality(name,zip_code,country) values ("Ham-sur-Heure-Nalinnes",6120,"Belgique");
insert into locality(name,zip_code,country) values ("Hamoir",4180,"Belgique");
insert into locality(name,zip_code,country) values ("Hamois",5363,"Belgique");
insert into locality(name,zip_code,country) values ("Hastière",5540,"Belgique");
insert into locality(name,zip_code,country) values ("Havelange",5370,"Belgique");
insert into locality(name,zip_code,country) values ("Hensies",7350,"Belgique");
insert into locality(name,zip_code,country) values ("Herbeumont",6887,"Belgique");
insert into locality(name,zip_code,country) values ("Honnelles",7387,"Belgique");
insert into locality(name,zip_code,country) values ("Hotton",6990,"Belgique");
insert into locality(name,zip_code,country) values ("Houyet",5560,"Belgique");
insert into locality(name,zip_code,country) values ("Hélécine",1357,"Belgique");
insert into locality(name,zip_code,country) values ("Héron",4218,"Belgique");
insert into locality(name,zip_code,country) values ("Jalhay",4845,"Belgique");
insert into locality(name,zip_code,country) values ("Jemeppe-sur-Sambre",5190,"Belgique");
insert into locality(name,zip_code,country) values ("Juprelle",4450,"Belgique");
insert into locality(name,zip_code,country) values ("Jurbise",7050,"Belgique");
insert into locality(name,zip_code,country) values ("Kelmis",4720,"Belgique");
insert into locality(name,zip_code,country) values ("La Bruyère",5080,"Belgique");
insert into locality(name,zip_code,country) values ("La Hulpe",1310,"Belgique");
insert into locality(name,zip_code,country) values ("Lasne",1380,"Belgique");
insert into locality(name,zip_code,country) values ("Lens (Hainaut)",7870,"Belgique");
insert into locality(name,zip_code,country) values ("Les Bons Villers",6210,"Belgique");
insert into locality(name,zip_code,country) values ("Libin",6890,"Belgique");
insert into locality(name,zip_code,country) values ("Libramont-Chevigny",6800,"Belgique");
insert into locality(name,zip_code,country) values ("Lierneux",4990,"Belgique");
insert into locality(name,zip_code,country) values ("Lincent",4287,"Belgique");
insert into locality(name,zip_code,country) values ("Lobbes",6540,"Belgique");
insert into locality(name,zip_code,country) values ("Lontzen",4710,"Belgique");
insert into locality(name,zip_code,country) values ("Léglise",6860,"Belgique");
insert into locality(name,zip_code,country) values ("Manage",7170,"Belgique");
insert into locality(name,zip_code,country) values ("Manhay",6960,"Belgique");
insert into locality(name,zip_code,country) values ("Marchin",4570,"Belgique");
insert into locality(name,zip_code,country) values ("Martelange",6630,"Belgique");
insert into locality(name,zip_code,country) values ("Meix-devant-Virton",6769,"Belgique");
insert into locality(name,zip_code,country) values ("Merbes-le-Château",6567,"Belgique");
insert into locality(name,zip_code,country) values ("Messancy",6780,"Belgique");
insert into locality(name,zip_code,country) values ("Mettet",5640,"Belgique");
insert into locality(name,zip_code,country) values ("Modave",4577,"Belgique");
insert into locality(name,zip_code,country) values ("Momignies",6590,"Belgique");
insert into locality(name,zip_code,country) values ("Mont-Saint-Guibert",1435,"Belgique");
insert into locality(name,zip_code,country) values ("Mont-de-l'Enclus",7750,"Belgique");
insert into locality(name,zip_code,country) values ("Montigny-le-Tilleul",6110,"Belgique");
insert into locality(name,zip_code,country) values ("Morlanwelz",7140,"Belgique");
insert into locality(name,zip_code,country) values ("Musson",6750,"Belgique");
insert into locality(name,zip_code,country) values ("Nandrin",4550,"Belgique");
insert into locality(name,zip_code,country) values ("Nassogne",6950,"Belgique");
insert into locality(name,zip_code,country) values ("Neupré",4120,"Belgique");
insert into locality(name,zip_code,country) values ("Paliseul",6850,"Belgique");
insert into locality(name,zip_code,country) values ("Pecq",7740,"Belgique");
insert into locality(name,zip_code,country) values ("Pepinster",4860,"Belgique");
insert into locality(name,zip_code,country) values ("Perwez",1360,"Belgique");
insert into locality(name,zip_code,country) values ("Plombières",4850,"Belgique");
insert into locality(name,zip_code,country) values ("Pont-à-Celles",6230,"Belgique");
insert into locality(name,zip_code,country) values ("Profondeville",5170,"Belgique");
insert into locality(name,zip_code,country) values ("Quaregnon",7390,"Belgique");
insert into locality(name,zip_code,country) values ("Quiévrain",7380,"Belgique");
insert into locality(name,zip_code,country) values ("Quévy",7041,"Belgique");
insert into locality(name,zip_code,country) values ("Raeren",4730,"Belgique");
insert into locality(name,zip_code,country) values ("Ramillies",1367,"Belgique");
insert into locality(name,zip_code,country) values ("REBECQ",1430,"Belgique");
insert into locality(name,zip_code,country) values ("Remicourt",4350,"Belgique");
insert into locality(name,zip_code,country) values ("Rendeux",6987,"Belgique");
insert into locality(name,zip_code,country) values ("Rixensart",1330,"Belgique");
insert into locality(name,zip_code,country) values ("Rouvroy",6767,"Belgique");
insert into locality(name,zip_code,country) values ("Rumes",7618,"Belgique");
insert into locality(name,zip_code,country) values ("Saint-Georges-sur-Meuse",4470,"Belgique");
insert into locality(name,zip_code,country) values ("Saint-Léger (Lux.)",6747,"Belgique");
insert into locality(name,zip_code,country) values ("Saint-Nicolas",4420,"Belgique");
insert into locality(name,zip_code,country) values ("Sainte-Ode",6680,"Belgique");
insert into locality(name,zip_code,country) values ("Sambreville",5060,"Belgique");
insert into locality(name,zip_code,country) values ("Seneffe",7180,"Belgique");
insert into locality(name,zip_code,country) values ("Silly",7830,"Belgique");
insert into locality(name,zip_code,country) values ("Sivry-Rance",6470,"Belgique");
insert into locality(name,zip_code,country) values ("Sombreffe",5140,"Belgique");
insert into locality(name,zip_code,country) values ("Somme-Leuze",5377,"Belgique");
insert into locality(name,zip_code,country) values ("Soumagne",4630,"Belgique");
insert into locality(name,zip_code,country) values ("Spa",4900,"Belgique");
insert into locality(name,zip_code,country) values ("Sprimont",4140,"Belgique");
insert into locality(name,zip_code,country) values ("Stoumont",4987,"Belgique");
insert into locality(name,zip_code,country) values ("Tellin",6927,"Belgique");
insert into locality(name,zip_code,country) values ("Tenneville",6970,"Belgique");
insert into locality(name,zip_code,country) values ("Theux",4910,"Belgique");
insert into locality(name,zip_code,country) values ("Thimister-Clermont",4890,"Belgique");
insert into locality(name,zip_code,country) values ("Tinlot",4557,"Belgique");
insert into locality(name,zip_code,country) values ("Tintigny",6730,"Belgique");
insert into locality(name,zip_code,country) values ("Trois-Ponts",4980,"Belgique");
insert into locality(name,zip_code,country) values ("Trooz",4870,"Belgique");
insert into locality(name,zip_code,country) values ("Tubize",1480,"Belgique");
insert into locality(name,zip_code,country) values ("Vaux-sur-Sûre",6640,"Belgique");
insert into locality(name,zip_code,country) values ("Verlaine",4537,"Belgique");
insert into locality(name,zip_code,country) values ("Vielsalm",6690,"Belgique");
insert into locality(name,zip_code,country) values ("Villers-la-Ville",1495,"Belgique");
insert into locality(name,zip_code,country) values ("Villers-le-Bouillet",4530,"Belgique");
insert into locality(name,zip_code,country) values ("Viroinval (Nismes)",5670,"Belgique");
insert into locality(name,zip_code,country) values ("Vresse-sur-Semois",5550,"Belgique");
insert into locality(name,zip_code,country) values ("Waimes",4950,"Belgique");
insert into locality(name,zip_code,country) values ("Walhain",1457,"Belgique");
insert into locality(name,zip_code,country) values ("Wanze",4520,"Belgique");
insert into locality(name,zip_code,country) values ("Wasseiges",4219,"Belgique");
insert into locality(name,zip_code,country) values ("Waterloo",1410,"Belgique");
insert into locality(name,zip_code,country) values ("Welkenraedt",4840,"Belgique");
insert into locality(name,zip_code,country) values ("Wellin",6920,"Belgique");
insert into locality(name,zip_code,country) values ("Andenne",5300,"Belgique");
insert into locality(name,zip_code,country) values ("Antoing",7640,"Belgique");
insert into locality(name,zip_code,country) values ("Arlon",6700,"Belgique");
insert into locality(name,zip_code,country) values ("Ath",7800,"Belgique");
insert into locality(name,zip_code,country) values ("Enghien",7850,"Belgique");
insert into locality(name,zip_code,country) values ("Eupen",4700,"Belgique");
insert into locality(name,zip_code,country) values ("Ottignies-Louvain-la-Neuve",1340,"Belgique");
insert into locality(name,zip_code,country) values ("Bastogne",6600,"Belgique");
insert into locality(name,zip_code,country) values ("Beaumont",6500,"Belgique");
insert into locality(name,zip_code,country) values ("Beauraing",5570,"Belgique");
insert into locality(name,zip_code,country) values ("Binche",7130,"Belgique");
insert into locality(name,zip_code,country) values ("Bouillon",6830,"Belgique");
insert into locality(name,zip_code,country) values ("Braine-le-Comte",7090,"Belgique");
insert into locality(name,zip_code,country) values ("Charleroi",6000,"Belgique");
insert into locality(name,zip_code,country) values ("Chimay",6460,"Belgique");
insert into locality(name,zip_code,country) values ("Chiny ",6810,"Belgique");
insert into locality(name,zip_code,country) values ("Chièvres",7950,"Belgique");
insert into locality(name,zip_code,country) values ("Châtelet",6200,"Belgique");
insert into locality(name,zip_code,country) values ("Ciney",5590,"Belgique");
insert into locality(name,zip_code,country) values ("Comines-Warneton",7780,"Belgique");
insert into locality(name,zip_code,country) values ("Couvin",5660,"Belgique");
insert into locality(name,zip_code,country) values ("Dinant",5500,"Belgique");
insert into locality(name,zip_code,country) values ("Durbuy",6940,"Belgique");
insert into locality(name,zip_code,country) values ("Fleurus",6220,"Belgique");
insert into locality(name,zip_code,country) values ("Florenville",6820,"Belgique");
insert into locality(name,zip_code,country) values ("Fontaine-l'Evêque",6140,"Belgique");
insert into locality(name,zip_code,country) values ("Fosses-la-Ville",5070,"Belgique");
insert into locality(name,zip_code,country) values ("Gembloux",5030,"Belgique");
insert into locality(name,zip_code,country) values ("Genappe",1470,"Belgique");
insert into locality(name,zip_code,country) values ("Hannut",4280,"Belgique");
insert into locality(name,zip_code,country) values ("Herstal",4040,"Belgique");
insert into locality(name,zip_code,country) values ("Herve",4650,"Belgique");
insert into locality(name,zip_code,country) values ("Houffalize",6660,"Belgique");
insert into locality(name,zip_code,country) values ("Huy",4500,"Belgique");
insert into locality(name,zip_code,country) values ("Jodoigne",1370,"Belgique");
insert into locality(name,zip_code,country) values ("La Louvière",7100,"Belgique");
insert into locality(name,zip_code,country) values ("La Roche-en-Ardenne",6980,"Belgique");
insert into locality(name,zip_code,country) values ("Le Roeulx",7070,"Belgique");
insert into locality(name,zip_code,country) values ("Lessines",7860,"Belgique");
insert into locality(name,zip_code,country) values ("Leuze-en-Hainaut",7900,"Belgique");
insert into locality(name,zip_code,country) values ("Limbourg",4830,"Belgique");
insert into locality(name,zip_code,country) values ("Liège",4000,"Belgique");
insert into locality(name,zip_code,country) values ("Malmedy",4960,"Belgique");
insert into locality(name,zip_code,country) values ("Marche-en-Famenne",6900,"Belgique");
insert into locality(name,zip_code,country) values ("Mons",7000,"Belgique");
insert into locality(name,zip_code,country) values ("Mouscron",7700,"Belgique");
insert into locality(name,zip_code,country) values ("Namur",5000,"Belgique");
insert into locality(name,zip_code,country) values ("Neufchâteau",6840,"Belgique");
insert into locality(name,zip_code,country) values ("Nivelles",1400,"Belgique");
insert into locality(name,zip_code,country) values ("Philippeville",5600,"Belgique");
insert into locality(name,zip_code,country) values ("Péruwelz",7600,"Belgique");
insert into locality(name,zip_code,country) values ("Rochefort",5580,"Belgique");
insert into locality(name,zip_code,country) values ("Saint-Ghislain",7333,"Belgique");
insert into locality(name,zip_code,country) values ("Saint-Hubert",6870,"Belgique");
insert into locality(name,zip_code,country) values ("Sankt Vith",4780,"Belgique");
insert into locality(name,zip_code,country) values ("Seraing",4100,"Belgique");
insert into locality(name,zip_code,country) values ("Soignies",7060,"Belgique");
insert into locality(name,zip_code,country) values ("Stavelot",4970,"Belgique");
insert into locality(name,zip_code,country) values ("Thuin",6530,"Belgique");
insert into locality(name,zip_code,country) values ("Tournai",7500,"Belgique");
insert into locality(name,zip_code,country) values ("Verviers",4800,"Belgique");
insert into locality(name,zip_code,country) values ("Virton",6760,"Belgique");
insert into locality(name,zip_code,country) values ("Visé",4600,"Belgique");
insert into locality(name,zip_code,country) values ("Walcourt",5650,"Belgique");
insert into locality(name,zip_code,country) values ("Waremme",4300,"Belgique");
insert into locality(name,zip_code,country) values ("Wavre",1300,"Belgique");


/*insert Manufacturer*/
insert into manufacturer (name) value ("ABARTH");
insert into manufacturer (name) value ("ALFA ROMEO");
insert into manufacturer (name) value ("ASTON MARTIN");
insert into manufacturer (name) value ("AUDI");
insert into manufacturer (name) value ("BENTLEY");
insert into manufacturer (name) value ("BMW");
insert into manufacturer (name) value ("CITROEN");
insert into manufacturer (name) value ("DACIA");
insert into manufacturer (name) value ("DS");
insert into manufacturer (name) value ("FERRARI");
insert into manufacturer (name) value ("FIAT");
insert into manufacturer (name) value ("FORD");
insert into manufacturer (name) value ("HONDA");
insert into manufacturer (name) value ("HYUNDAI");
insert into manufacturer (name) value ("INFINITI");
insert into manufacturer (name) value ("JAGUAR");
insert into manufacturer (name) value ("JEEP");
insert into manufacturer (name) value ("KIA");
insert into manufacturer (name) value ("LADA");
insert into manufacturer (name) value ("LAMBORGHINI");
insert into manufacturer (name) value ("LAND ROVER");
insert into manufacturer (name) value ("LEXUS");
insert into manufacturer (name) value ("LOTUS");
insert into manufacturer (name) value ("MASERATI");
insert into manufacturer (name) value ("MAZDA");
insert into manufacturer (name) value ("MCLAREN");
insert into manufacturer (name) value ("MERCEDES-BENZ");
insert into manufacturer (name) value ("MINI");
insert into manufacturer (name) value ("MITSUBISHI");
insert into manufacturer (name) value ("NISSAN");
insert into manufacturer (name) value ("OPEL");
insert into manufacturer (name) value ("PEUGEOT");
insert into manufacturer (name) value ("PORSCHE");
insert into manufacturer (name) value ("RENAULT");
insert into manufacturer (name) value ("ROLLS ROYCE");
insert into manufacturer (name) value ("SEAT");
insert into manufacturer (name) value ("SKODA");
insert into manufacturer (name) value ("SMART");
insert into manufacturer (name) value ("SSANGYONG");
insert into manufacturer (name) value ("SUBARU");
insert into manufacturer (name) value ("SUZUKI");
insert into manufacturer (name) value ("TELSA");
insert into manufacturer (name) value ("TOYOTA");
insert into manufacturer (name) value ("VOLKSWAGEN");
insert into manufacturer (name) value ("VOLVO");

/*insert model*/

insert into model (manufacturer, name) value ("ABARTH", "124 Spider");
insert into model (manufacturer, name) value ("ABARTH", "124 Spider Turismo");
insert into model (manufacturer, name) value ("ABARTH", "124 GT");
insert into model (manufacturer, name) value ("ABARTH", "595");
insert into model (manufacturer, name) value ("ABARTH", "595 Pista");
insert into model (manufacturer, name) value ("ABARTH", "595 Turismo");
insert into model (manufacturer, name) value ("ABARTH", "595 Competizione");

insert into model (manufacturer, name) value ("ASTON MARTIN", "DB11");
insert into model (manufacturer, name) value ("ASTON MARTIN", "DBS Superleggera");
insert into model (manufacturer, name) value ("ASTON MARTIN", "Rapide AMR");
insert into model (manufacturer, name) value ("ASTON MARTIN", "Rapide E");
insert into model (manufacturer, name) value ("ASTON MARTIN", "Aston Martin Valkyrie");





insert into model (manufacturer, name) value ("ALFA ROMEO", "STELVIO");
insert into model (manufacturer, name) value ("ALFA ROMEO", "GIULIA");
insert into model (manufacturer, name) value ("ALFA ROMEO", "GIULIETTA");
insert into model (manufacturer, name) value ("ALFA ROMEO", "4C");

insert into model (manufacturer, name) value ("AUDI", "A1");
insert into model (manufacturer, name) value ("AUDI", "A3");
insert into model (manufacturer, name) value ("AUDI", "A4");
insert into model (manufacturer, name) value ("AUDI", "A5");
insert into model (manufacturer, name) value ("AUDI", "A6");
insert into model (manufacturer, name) value ("AUDI", "A7");
insert into model (manufacturer, name) value ("AUDI", "A8");
insert into model (manufacturer, name) value ("AUDI", "Q2");
insert into model (manufacturer, name) value ("AUDI", "Q3");
insert into model (manufacturer, name) value ("AUDI", "Q5");
insert into model (manufacturer, name) value ("AUDI", "Q7");
insert into model (manufacturer, name) value ("AUDI", "Q8");
insert into model (manufacturer, name) value ("AUDI", "TT");
insert into model (manufacturer, name) value ("AUDI", "R8");

insert into model (manufacturer, name) value ("BMW", "SÉRIE 1 SPORTSHATCH");
insert into model (manufacturer, name) value ("BMW", "SÉRIE 1 HATCH");
insert into model (manufacturer, name) value ("BMW", "SÉRIE 2 COUPÉ");
insert into model (manufacturer, name) value ("BMW", "SÉRIE 2 CABRIO");
insert into model (manufacturer, name) value ("BMW", "SÉRIE 2 ACTIVE TOURER");
insert into model (manufacturer, name) value ("BMW", "225XE ACTIVE TOURER PLUG-IN HYBRID");
insert into model (manufacturer, name) value ("BMW", "SÉRIE 2 GRAN TOURER");
insert into model (manufacturer, name) value ("BMW", "SÉRIE 3 BERLINE");
insert into model (manufacturer, name) value ("BMW", "SÉRIE 3 TOURING");
insert into model (manufacturer, name) value ("BMW", "SÉRIE 3 GRAN TURISMO");
insert into model (manufacturer, name) value ("BMW", "SÉRIE 4 COUPÉ");
insert into model (manufacturer, name) value ("BMW", "SÉRIE 4 GRAN COUPÉ");
insert into model (manufacturer, name) value ("BMW", "SÉRIE 4 CABRIO");
insert into model (manufacturer, name) value ("BMW", "SÉRIE 5 BERLINE");
insert into model (manufacturer, name) value ("BMW", "SÉRIE 5 TOURING");
insert into model (manufacturer, name) value ("BMW", "SÉRIE 6 GRAN TURISMO");
insert into model (manufacturer, name) value ("BMW", "SÉRIE 7 BERLINE");
insert into model (manufacturer, name) value ("BMW", "745E PLUG-IN-HYBRID");
insert into model (manufacturer, name) value ("BMW", "M760LI XDRIVE BERLINE");
insert into model (manufacturer, name) value ("BMW", "SÉRIE 8 COUPÉ");
insert into model (manufacturer, name) value ("BMW", "SÉRIE 8 CABRIO");
insert into model (manufacturer, name) value ("BMW", "X1");
insert into model (manufacturer, name) value ("BMW", "X2");
insert into model (manufacturer, name) value ("BMW", "X3");
insert into model (manufacturer, name) value ("BMW", "X3 XDRIVE30E");
insert into model (manufacturer, name) value ("BMW", "X4");
insert into model (manufacturer, name) value ("BMW", "X5");
insert into model (manufacturer, name) value ("BMW", "X6");
insert into model (manufacturer, name) value ("BMW", "X7");
insert into model (manufacturer, name) value ("BMW", "Z4 ROADSTER");
insert into model (manufacturer, name) value ("BMW", "I3");
insert into model (manufacturer, name) value ("BMW", "I8 COUPÉ");
insert into model (manufacturer, name) value ("BMW", "I8 ROADSTER");
insert into model (manufacturer, name) value ("BMW", "SÉRIE 5 PLUG-IN HYBRIDE");
insert into model (manufacturer, name) value ("BMW", "M2 COMPETITION");
insert into model (manufacturer, name) value ("BMW", "M4 COUPÉ");
insert into model (manufacturer, name) value ("BMW", "M4 CABRIO");
insert into model (manufacturer, name) value ("BMW", "M5");
insert into model (manufacturer, name) value ("BMW", "X3 M");
insert into model (manufacturer, name) value ("BMW", "X4 M");

insert into model (manufacturer, name) value ("CITROEN", "C-ZÉRO");
insert into model (manufacturer, name) value ("CITROEN", "C1");
insert into model (manufacturer, name) value ("CITROEN", "NEW CITROËN E-MEHARI");
insert into model (manufacturer, name) value ("CITROEN", "C3");
insert into model (manufacturer, name) value ("CITROEN", "C3 AIRCROSS");
insert into model (manufacturer, name) value ("CITROEN", "C-ELYSÉE");
insert into model (manufacturer, name) value ("CITROEN", "BERLINE C4 CACTUS");
insert into model (manufacturer, name) value ("CITROEN", "C4 SPACETOURER");
insert into model (manufacturer, name) value ("CITROEN", "GRAND C4 SPACETOURER");
insert into model (manufacturer, name) value ("CITROEN", "NEW SUV C5 AIRCROSS");
insert into model (manufacturer, name) value ("CITROEN", "NEW BERLINGO");
insert into model (manufacturer, name) value ("CITROEN", "E-BERLINGO");
insert into model (manufacturer, name) value ("CITROEN", "SPACETOURER");
insert into model (manufacturer, name) value ("CITROEN", "SPACETOURER BUSINESS LOUNGE");

insert into model (manufacturer, name) value ("FIAT", "500");
insert into model (manufacturer, name) value ("FIAT", "500C");
insert into model (manufacturer, name) value ("FIAT", "500X CROSS");
insert into model (manufacturer, name) value ("FIAT", "500X URBAN");
insert into model (manufacturer, name) value ("FIAT", "500L");
insert into model (manufacturer, name) value ("FIAT", "500L CROSS");
insert into model (manufacturer, name) value ("FIAT", "500L WAGON");
insert into model (manufacturer, name) value ("FIAT", "TIPO BERLINE");
insert into model (manufacturer, name) value ("FIAT", "TIPO 5 PORTES");
insert into model (manufacturer, name) value ("FIAT", "TIPO STATION WAGON");
insert into model (manufacturer, name) value ("FIAT", "PANDA");
insert into model (manufacturer, name) value ("FIAT", "PANDA CITY CROSS");
insert into model (manufacturer, name) value ("FIAT", "PANDA 4X4");
insert into model (manufacturer, name) value ("FIAT", "PANDA CROSS 4X4");
insert into model (manufacturer, name) value ("FIAT", "PUNTO");
insert into model (manufacturer, name) value ("FIAT", "DOBLO");
insert into model (manufacturer, name) value ("FIAT", "QUBO");
insert into model (manufacturer, name) value ("FIAT", "FULLBACK");
insert into model (manufacturer, name) value ("FIAT", "FULLBACK CROSS");


insert into model (manufacturer, name) value ("FORD", "KA plus");
insert into model (manufacturer, name) value ("FORD", "FIESTA");
insert into model (manufacturer, name) value ("FORD", "TOURNEO COURIER");
insert into model (manufacturer, name) value ("FORD", "ECOSPORT");
insert into model (manufacturer, name) value ("FORD", "PUMA");
insert into model (manufacturer, name) value ("FORD", "FOCUS");
insert into model (manufacturer, name) value ("FORD", "C-MAX");
insert into model (manufacturer, name) value ("FORD", "TOURNEO CONNECT");
insert into model (manufacturer, name) value ("FORD", "KUGA");
insert into model (manufacturer, name) value ("FORD", "MONDEO");
insert into model (manufacturer, name) value ("FORD", "MUSTANG");
insert into model (manufacturer, name) value ("FORD", "S-MAX");
insert into model (manufacturer, name) value ("FORD", "GALAXY");
insert into model (manufacturer, name) value ("FORD", "EDGE");
insert into model (manufacturer, name) value ("FORD", "TOURNEO CUSTOM");
insert into model (manufacturer, name) value ("FORD", "RANGER");
insert into model (manufacturer, name) value ("FORD", "RANGER RAPTOR");
insert into model (manufacturer, name) value ("FORD", "FORD GT");


insert into model (manufacturer, name) value ("HONDA", "JAZZ");
insert into model (manufacturer, name) value ("HONDA", "CIVIC");
insert into model (manufacturer, name) value ("HONDA", "CIVIC Type R");
insert into model (manufacturer, name) value ("HONDA", "HR-V");
insert into model (manufacturer, name) value ("HONDA", "CR-V Hybrid");
insert into model (manufacturer, name) value ("HONDA", "CR-V");
insert into model (manufacturer, name) value ("HONDA", "Urban EV");
insert into model (manufacturer, name) value ("HONDA", "NSX");

insert into model (manufacturer, name) value ("HYUNDAI", "i20");
insert into model (manufacturer, name) value ("HYUNDAI", "i30 Fastback N");
insert into model (manufacturer, name) value ("HYUNDAI", "KONA");
insert into model (manufacturer, name) value ("HYUNDAI", "KONA Hybrid");
insert into model (manufacturer, name) value ("HYUNDAI", "KONA EV");
insert into model (manufacturer, name) value ("HYUNDAI", "TUCSON");
insert into model (manufacturer, name) value ("HYUNDAI", "Santa Fe");
insert into model (manufacturer, name) value ("HYUNDAI", "NEXO");
insert into model (manufacturer, name) value ("HYUNDAI", "i10");
insert into model (manufacturer, name) value ("HYUNDAI", "i20 Coupé");
insert into model (manufacturer, name) value ("HYUNDAI", "ix20");
insert into model (manufacturer, name) value ("HYUNDAI", "i30");
insert into model (manufacturer, name) value ("HYUNDAI", "i30 Wagon");
insert into model (manufacturer, name) value ("HYUNDAI", "IONIQ hybride");
insert into model (manufacturer, name) value ("HYUNDAI", "IONIQ plug-in hybride");
insert into model (manufacturer, name) value ("HYUNDAI", "IONIQ electrique");
insert into model (manufacturer, name) value ("HYUNDAI", "i40 Sedan");
insert into model (manufacturer, name) value ("HYUNDAI", "i40 Wagon");
insert into model (manufacturer, name) value ("HYUNDAI", "H-1 People");


insert into model (manufacturer, name) value ("JAGUAR", "E‑PACE");
insert into model (manufacturer, name) value ("JAGUAR", "F‑PACE");
insert into model (manufacturer, name) value ("JAGUAR", "I‑PACE");
insert into model (manufacturer, name) value ("JAGUAR", "F‑TYPE");
insert into model (manufacturer, name) value ("JAGUAR", "XE");
insert into model (manufacturer, name) value ("JAGUAR", "XF");
insert into model (manufacturer, name) value ("JAGUAR", "XJ");

insert into model (manufacturer, name) value ("JEEP", "Renegade");
insert into model (manufacturer, name) value ("JEEP", "Compass");
insert into model (manufacturer, name) value ("JEEP", "Cherokee");
insert into model (manufacturer, name) value ("JEEP", "Grand Cherokee");
insert into model (manufacturer, name) value ("JEEP", "Wrangler JL");

insert into model (manufacturer, name) value ("KIA", "Picanto");
insert into model (manufacturer, name) value ("KIA", "Rio ");
insert into model (manufacturer, name) value ("KIA", "Venga");
insert into model (manufacturer, name) value ("KIA", "Stonic  ");
insert into model (manufacturer, name) value ("KIA", "Ceed ");
insert into model (manufacturer, name) value ("KIA", "Ceed GT  ");
insert into model (manufacturer, name) value ("KIA", "Ceed SW   ");
insert into model (manufacturer, name) value ("KIA", "ProCeed  ");
insert into model (manufacturer, name) value ("KIA", "Kia Niro PHEV");
insert into model (manufacturer, name) value ("KIA", "Kia e-Niro");
insert into model (manufacturer, name) value ("KIA", "Kia Sportage");
insert into model (manufacturer, name) value ("KIA", "Sorento");
insert into model (manufacturer, name) value ("KIA", "Optima");
insert into model (manufacturer, name) value ("KIA", "Optima SW");
insert into model (manufacturer, name) value ("KIA", "Optima PHEV");
insert into model (manufacturer, name) value ("KIA", "Optima SW PHEV");
insert into model (manufacturer, name) value ("KIA", "Stinger");



insert into model (manufacturer, name) value ("OPEL", "ADAM");
insert into model (manufacturer, name) value ("OPEL", "ADAM ROCKS");
insert into model (manufacturer, name) value ("OPEL", "ASTRA 5-PORTES");
insert into model (manufacturer, name) value ("OPEL", "Astra Sports Tourer");
insert into model (manufacturer, name) value ("OPEL", "Cascada");
insert into model (manufacturer, name) value ("OPEL", "CORSA 3-PORTES");
insert into model (manufacturer, name) value ("OPEL", "CORSA 5-PORTES");
insert into model (manufacturer, name) value ("OPEL", "Combo Life");
insert into model (manufacturer, name) value ("OPEL", "Combo Cargo");
insert into model (manufacturer, name) value ("OPEL", "Crossland X");
insert into model (manufacturer, name) value ("OPEL", "Grandland X");
insert into model (manufacturer, name) value ("OPEL", "INSIGNIA GRAND SPORT");
insert into model (manufacturer, name) value ("OPEL", "INSIGNIA SPORTS TOURER");
insert into model (manufacturer, name) value ("OPEL", "INSIGNIA COUNTRY TOURER");
insert into model (manufacturer, name) value ("OPEL", "INSIGNIA GSi");
insert into model (manufacturer, name) value ("OPEL", "KARL");
insert into model (manufacturer, name) value ("OPEL", "KARL ROCKS");
insert into model (manufacturer, name) value ("OPEL", "Mokka X");
insert into model (manufacturer, name) value ("OPEL", "Movano");
insert into model (manufacturer, name) value ("OPEL", "Vivaro Fourgon");
insert into model (manufacturer, name) value ("OPEL", "Vivaro Double Cabine");
insert into model (manufacturer, name) value ("OPEL", "Vivaro Combi");
insert into model (manufacturer, name) value ("OPEL", "Vivaro Plancher Cabine");
insert into model (manufacturer, name) value ("OPEL", "Zafira Life");

insert into model (manufacturer, name) value ("PEUGEOT", "108 3 portes");
insert into model (manufacturer, name) value ("PEUGEOT", "208");
insert into model (manufacturer, name) value ("PEUGEOT", "308");
insert into model (manufacturer, name) value ("PEUGEOT", "308 GTi");
insert into model (manufacturer, name) value ("PEUGEOT", "308 SW");
insert into model (manufacturer, name) value ("PEUGEOT", "508");
insert into model (manufacturer, name) value ("PEUGEOT", "508 SW");
insert into model (manufacturer, name) value ("PEUGEOT", "SUV 2008");
insert into model (manufacturer, name) value ("PEUGEOT", "SUV 3008");
insert into model (manufacturer, name) value ("PEUGEOT", "SUV 5008");
insert into model (manufacturer, name) value ("PEUGEOT", "Rifter");
insert into model (manufacturer, name) value ("PEUGEOT", "Partner Tepee Electric");
insert into model (manufacturer, name) value ("PEUGEOT", "Traveller");
insert into model (manufacturer, name) value ("PEUGEOT", "Expert Combi");
insert into model (manufacturer, name) value ("PEUGEOT", "Boxer Combi");
insert into model (manufacturer, name) value ("PEUGEOT", "Partner");
insert into model (manufacturer, name) value ("PEUGEOT", "Expert");
insert into model (manufacturer, name) value ("PEUGEOT", "Boxer");



insert into supplier (tva_number, name, street, street_number,phone_number, locality) values ("BE0416618958","Garage Roméo Ceschiat SA","Avenue Président Roosevelt","93-95","071772006","Sambreville");
insert into supplier (tva_number, name, street, street_number,phone_number, locality) values ("BE0501810296","Tagliarini SPRL","Route d'eghezee","195","071788516","Jemeppe-sur-sambre");
insert into supplier (tva_number, name, street, street_number,phone_number, locality) values ("BE0424929185","SPRL Garage Gilbert et fils","Zoning Industriel 1","B-5190","071785923","Jemeppe-sur-Sambre");
insert into supplier (tva_number, name, street, street_number,phone_number, locality) values ("BE0408314372","Le centre auto","Avenue du Milénaire","2","071256080","Charleroi");
insert into supplier (tva_number, name, street, street_number,phone_number, locality) values ("BE0439338239","La scala SPRL","Rue de wangenies","137","003271817019","Fleurus");
insert into supplier (tva_number, name, street, street_number,phone_number, locality) values ("BE0806892322","Auto 66","Rue de l'hopital","66","+32472742411","Châtelet");
insert into supplier (tva_number, name, street, street_number,phone_number, locality) values ("BE0521883655","SamCar SPRL","Route de saussin","34a","0494192256","Jemeppe-sur-Sambre");
insert into supplier (tva_number, name, street, street_number,phone_number, locality) values ("BE0536197390","Mab Automobiles","Rue Vital Francoisse","130","+32498560814","Charleroi");
insert into supplier (tva_number, name, street, street_number,phone_number, locality) values ("BE0454089860","Garage du stain SPRL","rue bois du loup","11","071887673","Sombreffe");
insert into supplier (tva_number, name, street, street_number,phone_number, locality) values ("BE0449488793","Garage hublet et fils SPRL","Rue albert 1er","138","+3271380704","Farciennes");
insert into supplier (tva_number, name, street, street_number,phone_number, locality) values ("BE0426628566","Detournay SA","Drève des Alliés","89","+3271590231","Thuin");


/* En vente */
insert into vehicle (chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, selling_on_second_hand, selling_on_autoscout, model, tva_supplier)
  values ("TESTERGEZ654ZMCCC", "Noire", "2013-01-20", 1200, "Diesel", 80, 3000, "2019-04-10",30000, null ,1, 1,0,1,0,0,1,"X4", "BE0806892322");

insert into vehicle (chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, selling_on_second_hand, selling_on_autoscout, model, tva_supplier)
  values ("TESTERGEZ654ZMBBB", "Grise", "2005-01-16", 1800, "Diesel", 100, 5000, "2019-04-13",29000, null ,1, 1,1,1,1,0,1,"PUMA", "BE0536197390");

insert into vehicle (chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, selling_on_second_hand, selling_on_autoscout, model, tva_supplier)
  values ("TESTERGEZ654ZMAAA", "Grise", "2008-10-19", 1500, "Diesel", 120, 7000, "2019-06-01",7000, null ,1, 1,1,1,1,1,1,"C3", "BE0521883655");

insert into vehicle (chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, selling_on_second_hand, selling_on_autoscout, model, tva_supplier)
  values ("TESTERGEZ654ZG000", "Rouge", "2015-12-06", 1700, "Essence", 70, 7500, "2019-07-15",5000, null ,1, 1,0,1,0,0,1,"JAZZ", "BE0426628566");

insert into vehicle (chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, selling_on_second_hand, selling_on_autoscout, model, tva_supplier)
  values ("TESTERGEZ654ZM888", "Verte", "2016-01-02", 1800, "Diesel", 100, 8000, "2019-07-22",17000, null ,1, 1,0,1,0,0,1,"KA plus", "BE0501810296");

insert into vehicle (chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, selling_on_second_hand, selling_on_autoscout, model, tva_supplier)
  values ("TESTERGEZ654ZM777", "Grise", "2015-11-01", 1700, "Essence", 120, 6500, "2019-04-28",2000, null ,1, 0,0,1,0,0,1,"i20 Coupé", "BE0426628566");

insert into vehicle (chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, selling_on_second_hand, selling_on_autoscout, model, tva_supplier)
  values ("TESTERGEZ654ZM666", "Grise", "2005-01-24", 1800, "Diesel", 100, 2500, "2019-05-30",45000, null ,1, 0,0,1,0,0,1,"EDGE", "BE0408314372");

insert into vehicle (chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, selling_on_second_hand, selling_on_autoscout, model, tva_supplier)
  values ("TESTERGEZ654ZM555", "Bleue", "2008-01-25", 1900, "Essence", 180, 3900, "2019-06-19",4000, null ,1, 0,0,1,0,0,1,"PUMA", "BE0439338239");

insert into vehicle (chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, selling_on_second_hand, selling_on_autoscout, model, tva_supplier)
  values ("TESTERGEZ654ZM444", "Jaune", "2006-01-17", 1800, "Diesel", 90, 8900, "2019-07-25",60000, null ,1, 0,0,1,0,0,1,"4C", "BE0536197390");

insert into vehicle (chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, selling_on_second_hand, selling_on_autoscout, model, tva_supplier)
  values ("TESTERGEZ654ZM333", "Noire", "2015-01-16", 1800, "Diesel", 100, 3500, "2019-08-01",200000, null ,1, 1,0,1,0,0,1,"PUMA", "BE0416618958");

insert into vehicle (chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, selling_on_second_hand, selling_on_autoscout, model, tva_supplier)
  values ("TESTERGEZ654ZM222", "Rouge", "2005-01-28", 1800, "Essence", 60, 10000, "2019-08-05",100000, null ,1, 1,0,1,0,0,1,"i40 Wagon", "BE0416618958");

insert into vehicle (chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, selling_on_second_hand, selling_on_autoscout, model, tva_supplier)
  values ("TEYTERGEP654ZG464", "Grise", "2009-08-04", 1700, "Electrique", 50, 3500, "2019-07-31",10000, null ,1, 1,0,0,0,0,1,"KONA", "BE0521883655");

insert into vehicle (chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, selling_on_second_hand, selling_on_autoscout, model, tva_supplier)
  values ("TESTERGEZ654ZM111", "Grise", "2008-01-09", 1500, "Essence", 90, 4800, "2019-06-27",30000, null ,1, 1,0,1,0,0,1,"CIVIC", "BE0501810296");



insert into vehicle (chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, selling_on_second_hand, selling_on_autoscout, model, tva_supplier)
  values ("TESTERTEZ654ZE4LL", "Rouge", "2009-10-10", 1200, "Diesel", 60, 1000, "2019-07-20",180000, null ,1, 0,1,1,1,1,1,"PANDA", "BE0416618958");
insert into vehicle (chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, selling_on_second_hand, selling_on_autoscout, model, tva_supplier)
  values ("TESTWVWZZZ50ZLK02", "Noire", "2010-11-15", 1400, "Essence", 80, 5000, "2019-06-28",80000, "Retro",1, 1,1,1,1,1,1,"A1", "BE0501810296");
insert into vehicle (chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, selling_on_second_hand, selling_on_autoscout, model, tva_supplier)
  values ("TESTAGBUO564DG975", "Verte", "2015-01-20", 1500, "Electrique", 100, 8000, "2019-05-15",50000, "Griffe capot",1, 1,1,1,1,1,1,"GIULIETTA", "BE0454089860");
insert into vehicle (chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, selling_on_second_hand, selling_on_autoscout, model, tva_supplier)
  values ("TESTGUIDY654SUI89", "Noire", "2013-05-18", 1400, "Essence", 80, 7000, "2019-07-07",60000, null ,1, 1,1,1,1,1,1,"X5", "BE0416618958");





insert into client (last_name,first_name, street, street_number,phone_number, tva_number, locality)
values("Montoya-Molina", "François-Xavier", "Sq des marronniers","9", "+32497767778", null, "Charleroi");
insert into client (last_name,first_name, street, street_number,phone_number, tva_number, locality)
values("Veervotte", "Djé", "Rue des chêne","29A", "+32470595210", null, "Sambreville");
insert into client (last_name,first_name, street, street_number,phone_number, tva_number, locality)
values("Piraux", "Marie-Catherine", "Rue Puissant","327", "+32476806925", null, "Gerpinnes");
insert into client (last_name,first_name, street, street_number,phone_number, tva_number, locality)
values("Dejejet", "Philippe", "Rue de Fleurus","9", "+32497767778", null, "Charleroi");
insert into client (last_name,first_name, street, street_number,phone_number, tva_number, locality)
values("Montoya", "Renaud", "Rue des papillons","19", "+32497767778", null, "Sombreffe");
insert into client (last_name,first_name, street, street_number,phone_number, tva_number, locality)
values("Dupont", "Christophe", "Rue de fer","159", "0497827781", "BE0501810100", "Namur");
insert into client (last_name,first_name, street, street_number,phone_number, tva_number, locality)
values("Dumay", "Sophie", "Rue de la paix","8", "077677787", null, "Marche-en-Famenne");
insert into client (last_name,first_name, street, street_number,phone_number, tva_number, locality)
values("Ali", "Mohamed", "Chausée de Mons","10", "+32497700778", null, "Spa");
insert into client (last_name,first_name, street, street_number,phone_number, tva_number, locality)
values("Zaho", "Xiu", "Rue albert 1er","87", "+32457721770", "BE0520883415", "La Louvière");
insert into client (last_name,first_name, street, street_number,phone_number, tva_number, locality)
values("Bolterys", "Florian", "Rue de la Joncquière","6", "+32497760102", "BE0477221140", "Wavre");
insert into client (last_name,first_name, street, street_number,phone_number, tva_number, locality)
values("Micheleti", "Mireille", "Rue Puissant","327", "071818230", null, "Charleroi");




insert into client (last_name,first_name, street, street_number,phone_number, tva_number, locality)
values("Dumoulin", "Sevio", "Rue du coucou","659", "+32492222778", null, "Hotton");
insert into client (last_name,first_name, street, street_number,phone_number, tva_number, locality)
values("Rio", "Seb", "Rue des poissons","88", "+32470591111", null, "Gerpinnes");
insert into client (last_name,first_name, street, street_number,phone_number, tva_number, locality)
values("Piblio", "Pablo", "Rue du guet","387", "+32476806123", null, "Gerpinnes");
insert into client (last_name,first_name, street, street_number,phone_number, tva_number, locality)
values("Deniou", "Christine", "Rue de Mons","1024", "+32497760110", null, "Charleroi");
insert into client (last_name,first_name, street, street_number,phone_number, tva_number, locality)
values("Montoya", "Antoine", "Rue Fleurjoux","627", "+32433767888", null, "Charleroi");
insert into client (last_name,first_name, street, street_number,phone_number, tva_number, locality)
values("Prati", "Nicole", "Rue de l'air","189", "0497017741", "BE0521234415", "Namur");
insert into client (last_name,first_name, street, street_number,phone_number, tva_number, locality)
values("Uzumaki", "Fuji", "Rue du moine","80", "077017727", null, "Marche-en-Famenne");
insert into client (last_name,first_name, street, street_number,phone_number, tva_number, locality)
values("Krali", "Youssoupha", "Chausée de Villers","102", "+32497888778", null, "Spa");
insert into client (last_name,first_name, street, street_number,phone_number, tva_number, locality)
values("Hurlorage", "Illidan", "Rue d'azeroth","01", "+32457721766", "BE0520753615", "Wavre");
insert into client (last_name,first_name, street, street_number,phone_number, tva_number, locality)
values("Hurlenfer", "Garrosh", "Rue de la hache","61", "+32497765502", "BE8987221140", "Wavre");
insert into client (last_name,first_name, street, street_number,phone_number, tva_number, locality)
values("Portvaillant", "Jaina", "Rue du givre","329", "071818232", null, "Namur");


/* Vendue */
insert into vehicle (chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, selling_on_second_hand, selling_on_autoscout, model, tva_supplier)
  values ("TESTERGEZ654ZMDDD", "Grise", "2016-01-08", 1400, "Diesel", 80, 3700, "2019-07-05",20000, null ,0, 1,0,1,0,0,1,"PUMA", null);

insert into vehicle (chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, selling_on_second_hand, selling_on_autoscout, model, tva_supplier)
  values ("TESTERGEZ654ZMZZZ", "Rouge", "2009-10-10", 2000, "Diesel", 60, 1000, "2019-04-20",180000, null ,0, 0,1,1,1,1,1,"A5", null);

insert into vehicle (chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, selling_on_second_hand, selling_on_autoscout, model, tva_supplier)
  values ("TESTERGEZ654ZMKKK", "Rouge", "2015-07-12", 2000, "Essence", 60, 8000, "2019-06-27",180000, null ,0, 0,1,1,1,1,1,"X1", null);

insert into vehicle (chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, selling_on_second_hand, selling_on_autoscout, model, tva_supplier)
  values ("TESTGUIDY654STTTT", "Noire", "2013-05-25", 1400, "Essence", 80, 7000, "2019-08-07",60000, null ,1, 1,1,1,1,1,1,"A1", "BE0416618958");


insert into vehicle (chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, selling_on_second_hand, selling_on_autoscout, model, tva_supplier)
  values ("TESTERTEZ654Z7892", "Verte", "2010-09-12", 1200, "Diesel", 60, 6000, "2019-07-22",180000, null ,0, 0,1,1,1,1,1,"NEXO", "BE0416618958");
insert into vehicle (chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, selling_on_second_hand, selling_on_autoscout, model, tva_supplier)
  values ("TESTWVWZZZ50ZL456", "Brune", "2010-11-15", 1400, "Essence", 80, 5000, "2019-06-25",80000, "Retro",0, 1,1,1,1,1,1,"TUCSON", "BE0501810296");
insert into vehicle (chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, selling_on_second_hand, selling_on_autoscout, model, tva_supplier)
  values ("TESTAGBUO564DG456", "Jaune", "2015-01-20", 1500, "Electrique", 100, 8000, "2019-05-05",50000, "Griffe capot",0, 1,1,1,1,1,1,"MONDEO", "BE0454089860");
insert into vehicle (chassis_number, color, first_registration_date, engine_cylinder, engine_energy, engine_power, buy_price, buy_date, mileage, notes, is_on_sale, has_immat_certificate, has_technical_control, has_conformity_certificate, selling_on_facebook, selling_on_second_hand, selling_on_autoscout, model, tva_supplier)
  values ("TESTGUIDY654SU123", "Noire", "2013-05-18", 1400, "Essence", 80, 7000, "2019-07-03",60000, null ,0, 1,1,1,1,1,1,"QUBO", "BE0416618958");




/* Achat */
insert into bill(is_sale, bill_date, price, vehicle_chassis, client)
values(0, "2019-04-20", 2000, "TESTERGEZ654ZMZZZ", 1);
insert into bill(is_sale, bill_date, price, vehicle_chassis, client)
values(0, "2019-07-07", 7000, "TESTGUIDY654SUI89", 4);
insert into bill(is_sale, bill_date, price, vehicle_chassis, client)
values(0, "2019-07-05", 3700, "TESTERGEZ654ZMDDD", 7);


/* Vente */
insert into bill(is_sale, bill_date, price, vehicle_chassis, client)
values(1, sysdate(), 4000, "TESTERGEZ654ZMDDD", 5);
insert into bill(is_sale, bill_date, price, vehicle_chassis, client)
values(1, "2019-08-03", 3000, "TESTERGEZ654ZMZZZ", 6);
insert into bill(is_sale, bill_date, price, vehicle_chassis, client)
values(1, "2019-08-01", 7500, "TESTERGEZ654ZMKKK", 1);


insert into bill(is_sale, bill_date, price, vehicle_chassis, client)
values(1, sysdate(), 7000, "TESTERTEZ654Z7892", 10);
insert into bill(is_sale, bill_date, price, vehicle_chassis, client)
values(1, "2019-08-05", 6500, "TESTWVWZZZ50ZL456", 13);
insert into bill(is_sale, bill_date, price, vehicle_chassis, client)
values(1, "2019-08-09", 9800, "TESTAGBUO564DG456", 12);
insert into bill(is_sale, bill_date, price, vehicle_chassis, client)
values(1, "2019-08-10", 7500, "TESTGUIDY654SU123", 17);


/* Sans payement */
insert into bill(is_sale, bill_date, price, vehicle_chassis, client)
values(1, "2019-08-08", 7500, "TESTGUIDY654STTTT", 3);



/* Payement */
insert into payment(type, amount, check_number, bill)
values ("CASH", 1000, null, 6);
insert into payment(type, amount, check_number, bill)
values ("CASH", 4000, null, 4);
insert into payment(type, amount, check_number, bill)
values ("Bancontact", 1500, null, 5);
insert into payment(type, amount, check_number, bill)
values ("CASH", 1000, null, 5);

