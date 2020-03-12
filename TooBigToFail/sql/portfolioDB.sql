start transaction;
use aluu;

drop table if exists State;
drop table if exists Country;
drop table if exists Email;
drop table if exists Address;
drop table if exists Person;
drop table if exists PortfolioAsset;
drop table if exists Asset;
drop table if exists Portfolio;

create table if not exists Email (
	emailId int not null primary key auto_increment,
    emailName varchar(255) not null
    )engine=InnoDB,collate=latin1_general_cs;

create table if not exists Country (
  countryId int not null primary key auto_increment,
  countryName varchar(255) not null
)engine=InnoDB,collate=latin1_general_cs;

create table if not exists State (
  stateId int not null primary key auto_increment,
  stateName varchar(255) not null
)engine=InnoDB,collate=latin1_general_cs;

create table if not exists Address (
  addressId int not null primary key auto_increment,
  street varchar(255),
  city varchar(255),
  stateId int,
  zipCode varchar(255),
  countryId int
)engine=InnoDB,collate=latin1_general_cs;

create table if not exists Person (
  personId int not null primary key auto_increment,
  personCode varchar(255) not null,
  brokerData varchar(255),
  firstName varchar(255),
  lastName varchar(255),
  addressId int not null,
  emailId varchar(255)
)engine=InnoDB,collate=latin1_general_cs;


create table if not exists Asset(
  assetId int not null primary key auto_increment,
  assetCode varchar(255) not null,
  assetType char not null,
  assetLabel varchar(255) not null,
  apr float,
  balance float,
  quarterlyDividend float,
  baseRateOfReturn float,
  betaMeasure float,
  stockSymbol varchar(255),
  sharePrice float,
  baseOmegaMeasure float,
  totalValue float
)engine=InnoDB,collate=latin1_general_cs;

create table if not exists Portfolio (
  portfolioId int not null primary key auto_increment,
  portCode varchar(255) not null,
  ownerId int,
  managerId int,
  beneficiaryId int,
  foreign key (ownerId) references Person(personId),
  foreign key (managerId) references Person(personId),
  foreign key (beneficiaryId) references Person(personId)
)engine=InnoDB,collate=latin1_general_cs;

create table if not exists PortfolioAsset (
  portAssetId int not null primary key auto_increment,
  portfolioId int,
  assetId int,
  assetAmount float,
  foreign key (portfolioId) references Portfolio(portfolioId),
  foreign key (assetId) references Asset(assetId)
)engine=InnoDB,collate=latin1_general_cs;

--- Persons
insert into Person (personId, personCode, brokerData, firstName, lastName, addressId, emailId) values 
(1, 'VB76HV', 'E,sec725', 'Amara', 'Deeson', 101, 'adeeson0@4shared.com'),
(2, 'CWQ9R0', 'J,sec368', 'Fred', 'Biffin', 102, 'fbiffin1@shutterfly.com'),
(3, 'BASMCU', null, 'Sergei', 'Rignoldes', 103, 'srignoldes2@hhs.gov'),
(4, 'B034B9', null, 'Tamiko', 'Ropartz', 104, 'tropartz3@ox.ac.uk'),
(5, 'K26CR3', 'E,sec564', 'Opaline', 'Lacaze', 105, 'olacaze4@blogtalkradio.com'),
(6, 'NA2V3T', null, 'Dag', 'McAllen', 106, 'dmcallen5@dagondesign.com'),
(7, 'VIO4C2', 'J,sec742', 'Glad', 'Applegate', 107, 'gapplegate6@si.edu'),
(8, 'R3M84Y', null, 'Simona', 'Brant', 108, 'sbrant7@mayoclinic.com'),
(9, 'GBRFGX', null, 'Barton', 'Mansour', 109, 'bmansour8@e-recht24.de'),
(10, '8OMYLX', 'J,sec116', 'Lucky', 'Lowbridge', 110, 'llowbridge9@trellian.com'),
(11, 'MVLJFF', 'E,sec581', 'Alano', 'Grevel', 111, 'agrevela@earthlink.net'),
(12, 'Y0QRRR', null, 'Emera', 'Schirach', 112, 'eschirachb@abc.net.au'),
(13, 'SI6KNE', 'J,sec732', 'Minna', 'Seabrooke', 113, 'mseabrookec@live.com'),
(14, 'JSTWZL', 'J,sec824', 'Marco', 'Gasson', 114, 'mgassond@cmu.edu'),
(15, 'AI4H73', 'J,sec324', 'Ikey', 'Shelborne', 115, 'ishelbornee@digg.com'),
(16, '8H1DJB', 'J,sec020', 'Brand', 'Calcut', 116, 'bcalcutf@shutterfly.com');

--- Addresses
insert into Address (addressId, street, city, stateId, zipCode, countryId) values 
	(101, '24624 Glacier Hill Alley', 'Springfield', 201, '22156', 301),
    (102, '04 Talmadge Crossing', 'Hampton', 201, '23663', 301),
    (103, '0 Autumn Leaf Plaza', 'Los Angeles', 202, '90045', 301),
    (104, '796 Grover Crossing', 'Richmond', 201, '23208', 301),
    (105, '18169 Knutson Point', 'Arlington', 203, '76096', 301),
    (106, '00 Esker Road', 'Athens', 204, '30605', 301),
    (107, '61 Weeping Birch Parkway', 'Youngstown', 205, '44511', 301),
    (108, '57 Holy Cross Circle', 'Des Moines', 206, '50369', 301),
    (109, '63417 Sutteridge Junction', 'Bakersfield', 202, '93305', 301),
	(110, '6574 Forest Trail', 'Phoenix', 207, '85015', 301),
    (111, '44 Graceland Terrace', 'Lincoln', 208, '68524', 301),
    (112, '3 Columbus Center', 'Jamaica', 209, '11499', 301),
    (113, '64202 Mcbride Road', 'Little Rock', 210, '72204', 301),
    (114, '09 Cascade Plaza', 'Visalia', 202, '93291', 301),
    (115, '5 Oakridge Pass', 'Yakima', 211, '98907', 301),
    (116, '9 Colorado Alley', 'Phoenix', 207, '85015', 301);

--- States
insert into State (stateId, stateName) values 
	(201, 'Virginia'),
    (202, 'California'),
    (203, 'Texas'),
    (204, 'Georgia'),
    (205, 'Ohio'),
    (206, 'Iowa'),
    (207, 'Arizona'),
    (208, 'Nebraska'),
    (209, 'New York'),
    (210, 'Arkansas'),
    (211, 'Washington');

--- Countries
insert into Country (countryId, countryName) values (301, 'United States');

--- Assets
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values 
	(401, 'YMj2jjQt', 'D', 'Photobug', 83.89, null, null, null, null, null, null, null, null),
	(402, 'FSacHJRA', 'D', 'Minyx', 36.5, null, null, null, null, null, null, null, null),
	(403, 'TTAVkJ1z', 'D', 'Kimia', 80.46, null, null, null, null, null, null, null, null),
	(404, 'WSWGFGOt', 'D', 'Tazz', 2.14, null, null, null, null, null, null, null, null),
	(405, 'Z2XGqhbU', 'D', 'Photobug', 22.17, null, null, null, null, null, null, null, null),
	(406, 'OYWhU0b8', 'D', 'Agivu', 13.34, null, null, null, null, null, null, null, null),
	(407, 'NMgkglJq', 'D', 'Gigabox', 75.03, null, null, null, null, null, null, null, null),
	(408, 'tAceWN9L', 'D', 'Yotz', 32.12, null, null, null, null, null, null, null, null),
	(409, 'zUcSIYPE', 'D', 'Thoughtbridge', 70.95, null, null, null, null, null, null, null, null),
	(410, 'giff6C5r', 'D', 'Dablist', 70.77, null, null, null, null, null, null, null, null),
	(411, '89uKEpsi', 'S', 'Buzzster', null, 1738472.35, 53.02, 0.14452, 43, 'ACV', 99737.57, null, null),
	(412, '4WDyHL0t', 'S', 'Realbridge', null, 4698672.52, 13.78, 0.85331, 21, 'PRA', 1963.33, null, null),
	(413, 'OM3XnDC7', 'S', 'Trupe', null, 9816301.0, 30.42, 0.09815, 87, 'RESN', 56916.89, null, null),
	(414, 's0rhIsHa', 'S', 'Topdrive', null, 6828861.31, 27.51, 0.87092, 77.77, 'KND', 834.36, null, null),
	(415, 'fWHZRzrb', 'S', 'Aimbo', null, 353829.81, 8.21, 0.1455, 12.21, 'SSFN', 21662.82, null, null),
	(416, '0KZ7GLay', 'S', 'Twimbo', null, 9852915.0, 82.89, 0.43662, 44.4, 'TIER', 36996.0, null, null),
	(417, 'CvGUIwRO', 'S', 'Meemm', null, 2656051.28, 36.34, 0.41667, 69, 'EW', 42952.67, null, null),
	(418, 'Q2pTejug', 'S', 'Skyvu', null, 5381220.0, 38.14, 0.55755, 65.57, 'HABT', 74776.52, null, null),
	(419, 'un1qqk3u', 'S', 'Talane', null, 3163798.35, 6.21, 0.86828, 1, 'Y', 54341.31, null, null),
	(420, '4agAKJEq', 'S', 'Rhycero', null, 4224872.0, 64.13, 0.26453, 99.9, 'WTW', 33628.92, null, null),
	(421, 'ZEnSzfbI', 'P', 'Roombo', null, 3519178.68, 15.08, null, null, null, null, 0.86325, 6514528.34),
	(422, 'kzMLkD2z', 'P', 'Kwimbee', null, 3125087.79, 38.38, null, null, null, null, 0.0325, 729509.87),
	(423, 'N9dpHjke', 'P', 'Avamm', null, 8901536.0, 12.37, null, null, null, null, 0.76147, 666338.36),
	(424, 'N4ujtpKT', 'P', 'Fadeo', null, 5659081.7, 82.82, null, null, null, null, 0.71471, 549900.29),
	(425, 'EbTjfJsg', 'P', 'JumpXS', null, 325017.3, 94.36, null, null, null, null, 0.70894, 5780667.82),
	(426, 'hm7FbEF2', 'P', 'Livetube', null, 8482339.0, 11.79, null, null, null, null, 0.36347, 8560968.0),
	(427, 'zd68EsoK', 'P', 'Vidoo', null, 3567412.0, 63.69, null, null, null, null, 0.06971, 2774868.86),
	(428, 'D6j50RPV', 'P', 'Zoozzy', null, 9500256.0, 21.91, null, null, null, null, 0.62879, 8101357.0),
	(429, 'bC51jaAV', 'P', 'Tagchat', null, 5180527.3, 11.15, null, null, null, null, 0.35742, 7391383.09),
	(430, 'LS30qQV5', 'P', 'Wordify', null, 8041050.18, 34.4, null, null, null, null, 0.03922, 9126317.0);


--- Portfolios
insert into Portfolio (portfolioId, portCode, ownerId, managerId, beneficiaryId) values 
	(501, 'PD111', 1, 2, null),
    (502, 'PT001', 3, 4, null),
    (503, 'PF001', 5, 1, null),
    (504, 'PT002', 6, 7, null),
    (505, 'PF002', 8, 9, null),
    (506, 'PD001', 10, 11, null),
    (507, 'PZ001', 12, 14, null),
    (508, 'Pz002', 15, 16, null);

--- Portfolio Assets
insert into PortfolioAsset (portfolioId, assetId, assetAmount) values 
	(501, 401, 10120.23),
    (501, 401, 400),
    (501, 411, 50),
    (501, 421, 89),
    (502, 402, 54321.01),
    (502, 412, 98),
    (502, 413, 64),
    (502, 422, 55),
    (502, 421, 10),
    (503, 403, 12455.68),
    (503, 404, 97979.10),
    (503, 401, 80900.50),
    (503, 414, 30),
    (503, 423, 77),
	(504, 421, 33),
    (504, 427, 43),
    (504, 430, 68),
    (504, 418, 30),
    (504, 410, 10100203.10),
    (505, 416, 20),
    (505, 417, 44),
    (505, 418, 50),
    (505, 411, 30),
    (505, 410, 10.12),
    (506, 408, 5.00),
    (506, 409, 43431243.10),
    (506, 402, 43021.12),
    (506, 403, 981765.00),
    (507, 417, 40),
    (507, 415, 20),
    (507, 424, 43),
	(507, 421, 63),
    (508, 430, 23),
    (508, 426, 43),
	(508, 423, 74),
	(508, 410, 123.32);

