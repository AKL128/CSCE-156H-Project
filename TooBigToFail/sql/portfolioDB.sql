start transaction;
use bberg;

drop table if exists PortfolioAsset;
drop table if exists Asset;
drop table if exists Portfolio;
drop table if exists PersonEmail;
drop table if exists Person;
drop table if exists Address;
drop table if exists State;
drop table if exists Country;
drop table if exists Email;

create table if not exists Email (
	emailId int not null primary key auto_increment,
    personId int not null,
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
  countryId int,
  foreign key (stateId) references State(stateId),
  foreign key (countryId) references Country(countryId)
)engine=InnoDB,collate=latin1_general_cs;

create table if not exists Person (
  personId int not null primary key auto_increment,
  personCode varchar(255) not null,
  brokerData varchar(255),
  firstName varchar(255),
  lastName varchar(255),
  addressId int not null,
  foreign key (addressId) references Address(addressId)
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

--- Countries
insert into Country (countryName) values ('United States');

--- States
insert into State (stateName) values 
	('Virginia'),
    ('California'),
    ('Texas'),
    ('Georgia'),
    ('Ohio'),
    ('Iowa'),
    ('Arizona'),
    ('Nebraska'),
    ('New York'),
    ('Arkansas'),
    ('Washington');

--- Addresses
insert into Address (street, city, stateId, zipCode, countryId) values 
	('24624 Glacier Hill Alley', 'Springfield', (SELECT stateId FROM State WHERE stateName = "Virginia"), '22156', (SELECT countryId FROM Country WHERE countryName = "United States")),
    ('04 Talmadge Crossing', 'Hampton', (SELECT stateId FROM State WHERE stateName = "Virginia"), '23663', (SELECT countryId FROM Country WHERE countryName = "United States")),
    ('0 Autumn Leaf Plaza', 'Los Angeles', (SELECT stateId FROM State WHERE stateName = "California"), '90045', (SELECT countryId FROM Country WHERE countryName = "United States")),
    ('796 Grover Crossing', 'Richmond', (SELECT stateId FROM State WHERE stateName = "Virginia"), '23208', (SELECT countryId FROM Country WHERE countryName = "United States")),
    ('18169 Knutson Point', 'Arlington', (SELECT stateId FROM State WHERE stateName = "Texas"), '76096', (SELECT countryId FROM Country WHERE countryName = "United States")),
    ('00 Esker Road', 'Athens', (SELECT stateId FROM State WHERE stateName = "Georgia"), '30605', (SELECT countryId FROM Country WHERE countryName = "United States")),
    ('61 Weeping Birch Parkway', 'Youngstown', (SELECT stateId FROM State WHERE stateName = "Ohio"), '44511', (SELECT countryId FROM Country WHERE countryName = "United States")),
    ('57 Holy Cross Circle', 'Des Moines', (SELECT stateId FROM State WHERE stateName = "Iowa"), '50369', (SELECT countryId FROM Country WHERE countryName = "United States")),
    ('63417 Sutteridge Junction', 'Bakersfield', (SELECT stateId FROM State WHERE stateName = "Arizona"), '93305', (SELECT countryId FROM Country WHERE countryName = "United States")),
	('6574 Forest Trail', 'Phoenix', (SELECT stateId FROM State WHERE stateName = "New York"), '85015', (SELECT countryId FROM Country WHERE countryName = "United States")),
    ('44 Graceland Terrace', 'Lincoln', (SELECT stateId FROM State WHERE stateName = "Nebraska"), '68524', (SELECT countryId FROM Country WHERE countryName = "United States")),
    ('3 Columbus Center', 'Jamaica', (SELECT stateId FROM State WHERE stateName = "Washington"), '11499', (SELECT countryId FROM Country WHERE countryName = "United States")),
    ('64202 Mcbride Road', 'Little Rock', (SELECT stateId FROM State WHERE stateName = "Arkansas"), '72204', (SELECT countryId FROM Country WHERE countryName = "United States")),
    ('09 Cascade Plaza', 'Visalia', (SELECT stateId FROM State WHERE stateName = "Iowa"), '93291', (SELECT countryId FROM Country WHERE countryName = "United States")),
    ('5 Oakridge Pass', 'Yakima', (SELECT stateId FROM State WHERE stateName = "Washington"), '98907', (SELECT countryId FROM Country WHERE countryName = "United States")),
    ('9 Colorado Alley', 'Phoenix', (SELECT stateId FROM State WHERE stateName = "Nebraska"), '85015', (SELECT countryId FROM Country WHERE countryName = "United States"));
    
--- Emails
insert into Email (personId, emailName) values 
	(1, 'adeeson0@4shared.com'),
    (2, 'fbiffin1@shutterfly.com'),
    (3, 'srignoldes2@hhs.gov'),
    (4, 'tropartz3@ox.ac.uk'),
    (5, 'olacaze4@blogtalkradio.com'),
    (6, 'dmcallen5@dagondesign.com'),
    (7, 'gapplegate6@si.edu'),
    (8, 'sbrant7@mayoclinic.com'),
    (9, 'bmansour8@e-recht24.de'),
    (10, 'llowbridge9@trellian.com'),
    (11, 'agrevela@earthlink.net'),
    (12, 'eschirachb@abc.net.au'),
    (13, 'mseabrookec@live.com'),
    (14, 'mgassond@cmu.edu'),
    (15, 'ishelbornee@digg.com'),
    (16, 'bcalcutf@shutterfly.com');
    
--- Persons
insert into Person (personCode, brokerData, firstName, lastName, addressId) values 
	('VB76HV', 'E,sec725', 'Amara', 'Deeson', (SELECT addressId FROM Address WHERE street = '24624 Glacier Hill Alley')),
	('CWQ9R0', 'J,sec368', 'Fred', 'Biffin', (SELECT addressId FROM Address WHERE street = '04 Talmadge Crossing')),
	('BASMCU', null, 'Sergei', 'Rignoldes', (SELECT addressId FROM Address WHERE street = '0 Autumn Leaf Plaza')),
	('B034B9', null, 'Tamiko', 'Ropartz', (SELECT addressId FROM Address WHERE street = '796 Grover Crossing')),
	('K26CR3', 'E,sec564', 'Opaline', 'Lacaze', (SELECT addressId FROM Address WHERE street = '18169 Knutson Point')),
	('NA2V3T', null, 'Dag', 'McAllen', (SELECT addressId FROM Address WHERE street = '00 Esker Road')),
	('VIO4C2', 'J,sec742', 'Glad', 'Applegate', (SELECT addressId FROM Address WHERE street = '61 Weeping Birch Parkway')),
	('R3M84Y', null, 'Simona', 'Brant', (SELECT addressId FROM Address WHERE street = '57 Holy Cross Circle')),
	('GBRFGX', null, 'Barton', 'Mansour', (SELECT addressId FROM Address WHERE street = '63417 Sutteridge Junction')),
	('8OMYLX', 'J,sec116', 'Lucky', 'Lowbridge', (SELECT addressId FROM Address WHERE street = '6574 Forest Trail')),
	('MVLJFF', 'E,sec581', 'Alano', 'Grevel', (SELECT addressId FROM Address WHERE street = '44 Graceland Terrace')),
	('Y0QRRR', null, 'Emera', 'Schirach', (SELECT addressId FROM Address WHERE street = '3 Columbus Center')),
	('SI6KNE', 'J,sec732', 'Minna', 'Seabrooke', (SELECT addressId FROM Address WHERE street = '64202 Mcbride Road')),
	('JSTWZL', 'J,sec824', 'Marco', 'Gasson', (SELECT addressId FROM Address WHERE street = '09 Cascade Plaza')),
	('AI4H73', 'J,sec324', 'Ikey', 'Shelborne', (SELECT addressId FROM Address WHERE street = '5 Oakridge Pass')),
	('8H1DJB', 'J,sec020', 'Brand', 'Calcut', (SELECT addressId FROM Address WHERE street = '9 Colorado Alley'));


--- Assets
insert into Asset (assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values 
	('YMj2jjQt', 'D', 'Photobug', 83.89, null, null, null, null, null, null, null, null),
	('FSacHJRA', 'D', 'Minyx', 36.5, null, null, null, null, null, null, null, null),
	('TTAVkJ1z', 'D', 'Kimia', 80.46, null, null, null, null, null, null, null, null),
	('WSWGFGOt', 'D', 'Tazz', 2.14, null, null, null, null, null, null, null, null),
	('Z2XGqhbU', 'D', 'Photobug', 22.17, null, null, null, null, null, null, null, null),
	('OYWhU0b8', 'D', 'Agivu', 13.34, null, null, null, null, null, null, null, null),
	('NMgkglJq', 'D', 'Gigabox', 75.03, null, null, null, null, null, null, null, null),
	('tAceWN9L', 'D', 'Yotz', 32.12, null, null, null, null, null, null, null, null),
	('zUcSIYPE', 'D', 'Thoughtbridge', 70.95, null, null, null, null, null, null, null, null),
	('giff6C5r', 'D', 'Dablist', 70.77, null, null, null, null, null, null, null, null),
	('89uKEpsi', 'S', 'Buzzster', null, 1738472.35, 53.02, 0.14452, 43, 'ACV', 99737.57, null, null),
	('4WDyHL0t', 'S', 'Realbridge', null, 4698672.52, 13.78, 0.85331, 21, 'PRA', 1963.33, null, null),
	('OM3XnDC7', 'S', 'Trupe', null, 9816301.0, 30.42, 0.09815, 87, 'RESN', 56916.89, null, null),
	('s0rhIsHa', 'S', 'Topdrive', null, 6828861.31, 27.51, 0.87092, 77.77, 'KND', 834.36, null, null),
	('fWHZRzrb', 'S', 'Aimbo', null, 353829.81, 8.21, 0.1455, 12.21, 'SSFN', 21662.82, null, null),
	('0KZ7GLay', 'S', 'Twimbo', null, 9852915.0, 82.89, 0.43662, 44.4, 'TIER', 36996.0, null, null),
	('CvGUIwRO', 'S', 'Meemm', null, 2656051.28, 36.34, 0.41667, 69, 'EW', 42952.67, null, null),
	('Q2pTejug', 'S', 'Skyvu', null, 5381220.0, 38.14, 0.55755, 65.57, 'HABT', 74776.52, null, null),
	('un1qqk3u', 'S', 'Talane', null, 3163798.35, 6.21, 0.86828, 1, 'Y', 54341.31, null, null),
	('4agAKJEq', 'S', 'Rhycero', null, 4224872.0, 64.13, 0.26453, 99.9, 'WTW', 33628.92, null, null),
	('ZEnSzfbI', 'P', 'Roombo', null, 3519178.68, 15.08, null, null, null, null, 0.86325, 6514528.34),
	('kzMLkD2z', 'P', 'Kwimbee', null, 3125087.79, 38.38, null, null, null, null, 0.0325, 729509.87),
	('N9dpHjke', 'P', 'Avamm', null, 8901536.0, 12.37, null, null, null, null, 0.76147, 666338.36),
	('N4ujtpKT', 'P', 'Fadeo', null, 5659081.7, 82.82, null, null, null, null, 0.71471, 549900.29),
	('EbTjfJsg', 'P', 'JumpXS', null, 325017.3, 94.36, null, null, null, null, 0.70894, 5780667.82),
	('hm7FbEF2', 'P', 'Livetube', null, 8482339.0, 11.79, null, null, null, null, 0.36347, 8560968.0),
	('zd68EsoK', 'P', 'Vidoo', null, 3567412.0, 63.69, null, null, null, null, 0.06971, 2774868.86),
	('D6j50RPV', 'P', 'Zoozzy', null, 9500256.0, 21.91, null, null, null, null, 0.62879, 8101357.0),
	('bC51jaAV', 'P', 'Tagchat', null, 5180527.3, 11.15, null, null, null, null, 0.35742, 7391383.09),
	('LS30qQV5', 'P', 'Wordify', null, 8041050.18, 34.4, null, null, null, null, 0.03922, 9126317.0);


--- Portfolios
insert into Portfolio (portCode, ownerId, managerId, beneficiaryId) values 
	('PD111', (SELECT personId FROM Person WHERE lastName = 'Deeson' AND firstName = 'Amara'), (SELECT personId FROM Person WHERE lastName = 'Biffin' AND firstName = 'Fred'), null),
    ('PT001', (SELECT personId FROM Person WHERE lastName = 'Rignoldes' AND firstName = 'Sergei'), (SELECT personId FROM Person WHERE lastName = 'Ropartz' AND firstName = 'Tamiko'), null),
    ('PF001', (SELECT personId FROM Person WHERE lastName = 'Lacaze' AND firstName = 'Opaline'), (SELECT personId FROM Person WHERE lastName = 'McAllen' AND firstName = 'Dag'), null),
    ('PT002', (SELECT personId FROM Person WHERE lastName = 'Applegate' AND firstName = 'Glad'), (SELECT personId FROM Person WHERE lastName = 'Brant' AND firstName = 'Simona'), null),
    ('PF002', (SELECT personId FROM Person WHERE lastName = 'Mansour' AND firstName = 'Barton'), (SELECT personId FROM Person WHERE lastName = 'Lowbridge' AND firstName = 'Lucky'), null),
    ('PD001', (SELECT personId FROM Person WHERE lastName = 'Grevel' AND firstName = 'Alano'), (SELECT personId FROM Person WHERE lastName = 'Schirach' AND firstName = 'Emera'), null),
    ('PZ001', (SELECT personId FROM Person WHERE lastName = 'Seabrooke' AND firstName = 'Minna'), (SELECT personId FROM Person WHERE lastName = 'Gasson' AND firstName = 'Marco'), null),
    ('PZ002', (SELECT personId FROM Person WHERE lastName = 'Shelborne' AND firstName = 'Ikey'), (SELECT personId FROM Person WHERE lastName = 'Calcut' AND firstName = 'Brand'), null);

--- Portfolio Assets
insert into PortfolioAsset (portfolioId, assetId, assetAmount) values 
	((SELECT portfolioId FROM Portfolio WHERE portCode = 'PD111'), (SELECT assetId FROM Asset WHERE assetCode = 'YMj2jjQt'), 10120.23),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PD111'), (SELECT assetId FROM Asset WHERE assetCode = '89uKEpsi'), 400),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PD111'), (SELECT assetId FROM Asset WHERE assetCode = 'ZEnSzfbI'), 50),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PD111'), (SELECT assetId FROM Asset WHERE assetCode = 'FSacHJRA'), 89),
    (2, 2, 54321.01),
    (2, 12, 98),
    (2, 13, 64),
    (2, 22, 55),
    (2, 21, 10),
    (3, 3, 12455.68),
    (3, 4, 97979.10),
    (3, 1, 80900.50),
    (3, 14, 30),
    (3, 23, 77),
	(4, 21, 33),
    (4, 27, 43),
    (4, 30, 68),
    (4, 18, 30),
    (4, 10, 10100203.10),
    (5, 16, 20),
    (5, 17, 44),
    (5, 18, 50),
    (5, 11, 30),
    (5, 10, 10.12),
    (6, 8, 5.00),
    (6, 9, 43431243.10),
    (6, 2, 43021.12),
    (6, 3, 981765.00),
    (6, 17, 40),
    (7, 15, 20),
    (7, 24, 43),
	(7, 21, 63),
    (8, 30, 23),
    (8, 26, 43),
	(8, 23, 74),
	(8, 10, 123.32);

