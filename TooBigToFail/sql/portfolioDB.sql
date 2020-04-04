use aluu;

start transaction;

drop table if exists PortfolioAsset;
drop table if exists Asset;
drop table if exists Portfolio;
drop table if exists Email;
drop table if exists Person;
drop table if exists Address;
drop table if exists State;
drop table if exists Country;

create table if not exists State (
  stateId int not null primary key auto_increment,
  stateName varchar(255) not null unique key
)engine=InnoDB,collate=latin1_general_cs;

create table if not exists Country (
  countryId int not null primary key auto_increment,
  countryName varchar(255) not null unique key
)engine=InnoDB,collate=latin1_general_cs;

create table if not exists Address (
  addressId int not null primary key auto_increment,
  street varchar(255) unique key,
  city varchar(255),
  stateId int not null,
  zipCode varchar(255),
  countryId int not null,
  foreign key (stateId) references State(stateId),
  foreign key (countryId) references Country(countryId)
)engine=InnoDB,collate=latin1_general_cs;

create table if not exists Person (
  personId int not null primary key auto_increment,
  personCode varchar(255) not null unique key,
  brokerData varchar(255),
  firstName varchar(255),
  lastName varchar(255),
  addressId int not null,
  foreign key (addressId) references Address(addressId)
)engine=InnoDB,collate=latin1_general_cs;

create table if not exists Email (
	emailId int not null primary key auto_increment,
    emailName varchar(255) not null unique key,
    personId int,
    foreign key (personId) references Person(personId)
    )engine=InnoDB,collate=latin1_general_cs;

create table if not exists Asset(
  assetId int not null primary key auto_increment,
  assetCode varchar(255) not null unique key,
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
  portCode varchar(255) not null unique key,
  ownerId int not null,
  managerId int,
  beneficiaryId int,
  foreign key (ownerId) references Person(personId),
  foreign key (managerId) references Person(personId),
  foreign key (beneficiaryId) references Person(personId)
)engine=InnoDB,collate=latin1_general_cs;

create table if not exists PortfolioAsset (
  portAssetId int not null primary key auto_increment,
  portfolioId int not null,
  assetId int,
  assetAmount float,
  foreign key (portfolioId) references Portfolio(portfolioId),
  foreign key (assetId) references Asset(assetId),
  constraint `UniqueAsset` unique(portfolioId, assetId)
)engine=InnoDB,collate=latin1_general_cs;

-- Countries
insert into Country (countryName) values ('United States');
insert into Country (countryName) values ('Japan');

-- States
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
    ('Washington'),
    ('Illinois'),
    ('Massachusetts'),
    ('Toyama Prefecture'),
    ('Colorado');

-- Addresses
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
    ('9 Colorado Alley', 'Phoenix', (SELECT stateId FROM State WHERE stateName = "Nebraska"), '85015', (SELECT countryId FROM Country WHERE countryName = "United States")),
    ('4789 Lake Street', 'Chicago', (SELECT stateId FROM State WHERE stateName = "Illinois"), '60123', (SELECT countryId FROM Country WHERE countryName = "United States")),
    ('478 A Street', 'Boston', (SELECT stateId FROM State WHERE stateName = "Massachusetts"), '47891', (SELECT countryId FROM Country WHERE countryName = "United States")),
    ('123 Tsune Kuni', 'Hamamatsu City', (SELECT stateId FROM State WHERE stateName = "Toyama Prefecture"), null, (SELECT countryId FROM Country WHERE countryName = "Japan")),
    ('35 Main Street', 'Springfield', (SELECT stateId FROM State WHERE stateName = "Illinois"), '62702', (SELECT countryId FROM Country WHERE countryName = "United States")),
    ('1243 West O Street', 'Omaha', (SELECT stateId FROM State WHERE stateName = "Nebraska"), '68415', (SELECT countryId FROM Country WHERE countryName = "United States")),
    ('7814 Jordan Street', 'Denver', (SELECT stateId FROM State WHERE stateName = "Colorado"), '65203', (SELECT countryId FROM Country WHERE countryName = "United States"));

-- Specific Person for queries
INSERT INTO Person(personId, personCode, brokerData, firstName, lastName, addressId) values
	(1, 'VB76HV', 'E,sec725', 'Amara', 'Deeson', (SELECT addressId FROM Address WHERE street = '24624 Glacier Hill Alley')),
    (2, 'CWQ9R0', 'J,sec368', 'Fred', 'Biffin', (SELECT addressId FROM Address WHERE street = '04 Talmadge Crossing')),
    (3, 'BASMCU', null, 'Sergei', 'Rignoldes', (SELECT addressId FROM Address WHERE street = '0 Autumn Leaf Plaza')),
	(500, '8H1DJB', 'J,sec020', 'Brand', 'Calcut', (SELECT addressId FROM Address WHERE street = '9 Colorado Alley'));

-- Persons
insert into Person (personCode, brokerData, firstName, lastName, addressId) values
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
    ('PAUT87', 'E,sec856', 'Hunter', 'David', (SELECT addressId FROM Address WHERE street = '4789 Lake Street')),
    ('WEO123', 'J, sec444', 'Wang', 'Wei', (SELECT addressId FROM Address WHERE street = '478 A Street')),
    ('0FI323', 'E, sec385', 'Light', 'Yagami', (SELECT addressId FROM Address WHERE street = '123 Tsune Kuni')),
    ('KD93N', 'J, sec928', 'Joe', 'Johnson', (SELECT addressId FROM Address WHERE street = '35 Main Street')),
    ('1234A', 'J, sec183', 'John', 'Alden', (SELECT addressId FROM Address WHERE street = '1243 West O Street')),
    ('WSY62', 'J, sec123', 'Adison', 'Tom', (SELECT addressId FROM Address WHERE street = '7814 Jordan Street'));

--- Emails
insert into Email (emailName, personId) values
	('adeeson0@4shared.com', (SELECT personId FROM Person WHERE lastName = 'Deeson' AND firstName = 'Amara')),
    ('NOTadeeson0@4shared.com', (SELECT personId FROM Person WHERE lastName = 'Deeson' AND firstName = 'Amara')),
    ('ANOTHERadeeson0@4shared.com', (SELECT personId FROM Person WHERE lastName = 'Deeson' AND firstName = 'Amara')),
    ('fbiffin1@shutterfly.com', (SELECT personId FROM Person WHERE lastName = 'Biffin' AND firstName = 'Fred')),
    ('srignoldes2@hhs.gov', (SELECT personId FROM Person WHERE lastName = 'Rignoldes' AND firstName = 'Sergei')),
    ('tropartz3@ox.ac.uk', (SELECT personId FROM Person WHERE lastName = 'Ropartz' AND firstName = 'Tamiko')),
    ('olacaze4@blogtalkradio.com', (SELECT personId FROM Person WHERE lastName = 'Lacaze' AND firstName = 'Opaline')),
    ('dmcallen5@dagondesign.com', (SELECT personId FROM Person WHERE lastName = 'McAllen' AND firstName = 'Dag')),
    ('gapplegate6@si.edu', (SELECT personId FROM Person WHERE lastName = 'Applegate' AND firstName = 'Glad')),
    ('sbrant7@mayoclinic.com', (SELECT personId FROM Person WHERE lastName = 'Brant' AND firstName = 'Simona')),
    ('bmansour8@e-recht24.de', (SELECT personId FROM Person WHERE lastName = 'Mansour' AND firstName = 'Barton')),
    ('llowbridge9@trellian.com', (SELECT personId FROM Person WHERE lastName = 'Lowbridge' AND firstName = 'Lucky')),
    ('agrevela@earthlink.net', (SELECT personId FROM Person WHERE lastName = 'Grevel' AND firstName = 'Alano')),
    ('eschirachb@abc.net.au', (SELECT personId FROM Person WHERE lastName = 'Schirach' AND firstName = 'Emera')),
    ('mseabrookec@live.com', (SELECT personId FROM Person WHERE lastName = 'Seabrooke' AND firstName = 'Minna')),
    ('mgassond@cmu.edu', (SELECT personId FROM Person WHERE lastName = 'Gasson' AND firstName = 'Marco')),
    ('ishelbornee@digg.com', (SELECT personId FROM Person WHERE lastName = 'Shelborne' AND firstName = 'Ikey')),
    ('davidhunter@yahoo.com', (SELECT personId FROM Person WHERE lastName = 'Hunter' AND firstName = 'David')),
    ('dv123@who.com', (SELECT personId FROM Person WHERE lastName = 'David' AND firstName = 'Hunter')),
    ('wweiz2@gmail.com', (SELECT personId FROM Person WHERE lastName = 'Wei' AND firstName = 'Wang')),
    ('jjohnson@somewhere.com', (SELECT personId FROM Person WHERE lastName = 'Johnson' AND firstName = 'Joe')),
    ('averagejoe@someplace.com', (SELECT personId FROM Person WHERE lastName = 'Johnson' AND firstName = 'Joe')),
    ('aj@gmail.com', (SELECT personId FROM Person WHERE lastName = 'Alden' AND firstName = 'John')),
    ('tadison@unl.edu', (SELECT personId FROM Person WHERE lastName = 'Tom' AND firstName = 'Adison'));

-- Specific email for queries
INSERT INTO Email (emailId, emailName, personId) values
	(500, 'bcalcutf@shutterfly.com', (SELECT personId FROM Person WHERE lastName = 'Calcut' AND firstName = 'Brand'));
-- Emails without a person
insert into Email (emailName) values
	('randomEmail@4shared.com');

-- Deposit Accounts
insert into Asset (assetCode, assetType, assetLabel, apr) values
  ('YMj2jjQt', 'D', 'Photobug', 83.89),
	('FSacHJRA', 'D', 'Minyx', 36.5),
	('TTAVkJ1z', 'D', 'Kimia', 80.46),
	('WSWGFGOt', 'D', 'Tazz', 2.14),
	('Z2XGqhbU', 'D', 'Photobug', 22.17),
	('OYWhU0b8', 'D', 'Agivu', 13.34),
	('NMgkglJq', 'D', 'Gigabox', 75.03),
	('tAceWN9L', 'D', 'Yotz', 32.12),
	('zUcSIYPE', 'D', 'Thoughtbridge', 70.95),
	('giff6C5r', 'D', 'Dablist', 70.77),
    ('VTST', 'D', 'Geigco', 1.78),
    ('CD4523', 'D', '10-year CD', 8.76),
    ('SEARCH8', 'D', 'Yahoo', 1.34);

-- Stocks
insert into Asset (assetCode, assetType, assetLabel, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice) values
  ('89uKEpsi', 'S', 'Buzzster', 1738472.35, 53.02, 0.14452, 43, 'ACV', 99737.57),
	('4WDyHL0t', 'S', 'Realbridge', 4698672.52, 13.78, 0.85331, 21, 'PRA', 1963.33),
	('OM3XnDC7', 'S', 'Trupe', 9816301.0, 30.42, 0.09815, 87, 'RESN', 56916.89),
	('s0rhIsHa', 'S', 'Topdrive', 6828861.31, 27.51, 0.87092, 77.77, 'KND', 834.36),
	('fWHZRzrb', 'S', 'Aimbo', 353829.81, 8.21, 0.1455, 12.21, 'SSFN', 21662.82),
	('0KZ7GLay', 'S', 'Twimbo', 9852915.0, 82.89, 0.43662, 44.4, 'TIER', 36996.0),
	('CvGUIwRO', 'S', 'Meemm', 2656051.28, 36.34, 0.41667, 69, 'EW', 42952.67),
	('Q2pTejug', 'S', 'Skyvu', 5381220.0, 38.14, 0.55755, 65.57, 'HABT', 74776.52),
	('un1qqk3u', 'S', 'Talane', 3163798.35, 6.21, 0.86828, 1, 'Y', 54341.31),
	('4agAKJEq', 'S', 'Rhycero', 4224872.0, 64.13, 0.26453, 99.9, 'WTW', 33628.92);

-- Private Investments
insert into Asset (assetCode, assetType, assetLabel, quarterlyDividend, baseRateOfReturn, baseOmegaMeasure, totalValue) values
  ('ZEnSzfbI', 'P', 'Roombo', 3519178.68, 15.08, 0.86325, 6514528.34),
	('kzMLkD2z', 'P', 'Kwimbee', 3125087.79, 38.38, 0.0325, 729509.87),
	('N9dpHjke', 'P', 'Avamm', 8901536.0, 12.37, 0.76147, 666338.36),
	('N4ujtpKT', 'P', 'Fadeo', 5659081.7, 82.82, 0.71471, 549900.29),
	('EbTjfJsg', 'P', 'JumpXS', 325017.3, 94.36, 0.70894, 5780667.82),
	('hm7FbEF2', 'P', 'Livetube', 8482339.0, 11.79, 0.36347, 8560968.0),
	('zd68EsoK', 'P', 'Vidoo', 3567412.0, 63.69, 0.06971, 2774868.86),
	('D6j50RPV', 'P', 'Zoozzy', 9500256.0, 21.91, 0.62879, 8101357.0),
	('bC51jaAV', 'P', 'Tagchat', 5180527.3, 11.15, 0.35742, 7391383.09),
	('LS30qQV5', 'P', 'Wordify', 8041050.18, 34.4, 0.03922, 9126317.0);

-- Portfolios
insert into Portfolio (portCode, ownerId, managerId, beneficiaryId) values
	('PD111', (SELECT personId FROM Person WHERE lastName = 'Deeson' AND firstName = 'Amara'), (SELECT personId FROM Person WHERE lastName = 'Biffin' AND firstName = 'Fred'), (SELECT personId FROM Person WHERE lastName = 'McAllen' AND firstName = 'Dag')),
    ('PT001', (SELECT personId FROM Person WHERE lastName = 'Rignoldes' AND firstName = 'Sergei'), (SELECT personId FROM Person WHERE lastName = 'Ropartz' AND firstName = 'Tamiko'), (SELECT personId FROM Person WHERE lastName = 'Deeson' AND firstName = 'Amara')),
    ('PF001', (SELECT personId FROM Person WHERE lastName = 'Lacaze' AND firstName = 'Opaline'), (SELECT personId FROM Person WHERE lastName = 'McAllen' AND firstName = 'Dag'), (SELECT personId FROM Person WHERE lastName = 'Gasson' AND firstName = 'Marco')),
    ('PT002', (SELECT personId FROM Person WHERE lastName = 'Applegate' AND firstName = 'Glad'), (SELECT personId FROM Person WHERE lastName = 'Brant' AND firstName = 'Simona'), (SELECT personId FROM Person WHERE lastName = 'Calcut' AND firstName = 'Brand')),
    ('PF002', (SELECT personId FROM Person WHERE lastName = 'Mansour' AND firstName = 'Barton'), (SELECT personId FROM Person WHERE lastName = 'Brant' AND firstName = 'Simona'), (SELECT personId FROM Person WHERE lastName = 'Grevel' AND firstName = 'Alano')),
    ('PD001', (SELECT personId FROM Person WHERE lastName = 'Grevel' AND firstName = 'Alano'), (SELECT personId FROM Person WHERE lastName = 'Schirach' AND firstName = 'Emera'), (SELECT personId FROM Person WHERE lastName = 'Brant' AND firstName = 'Simona')),
    ('PZ001', (SELECT personId FROM Person WHERE lastName = 'Calcut' AND firstName = 'Brand'), (SELECT personId FROM Person WHERE lastName = 'Gasson' AND firstName = 'Marco'), (SELECT personId FROM Person WHERE lastName = 'Calcut' AND firstName = 'Brand')),
    ('PZ002', (SELECT personId FROM Person WHERE lastName = 'Shelborne' AND firstName = 'Ikey'), (SELECT personId FROM Person WHERE lastName = 'Calcut' AND firstName = 'Brand'), (SELECT personId FROM Person WHERE lastName = 'David' AND firstName = 'Hunter')),
    ('PZ003', (SELECT personId FROM Person WHERE lastName = 'Shelborne' AND firstName = 'Ikey'), (SELECT personId FROM Person WHERE lastName = 'Brant' AND firstName = 'Simona'), (SELECT personId FROM Person WHERE lastName = 'Johnson' AND firstName = 'Joe')),
    ('PF473', (SELECT personId FROM Person WHERE lastName = 'Wei' AND firstName = 'Wang'), (SELECT personId FROM Person WHERE lastName = 'David' AND firstName = 'Hunter'), (SELECT personId FROM Person WHERE lastName = 'Lacaze' AND firstName = 'Opaline')),
    ('CPF003', (SELECT personId FROM Person WHERE lastName = 'Yagami' AND firstName = 'Light'), (SELECT personId FROM Person WHERE lastName = 'Johnson' AND firstName = 'Joe'), (SELECT personId FROM Person WHERE lastName = 'Tom' AND firstName = 'Adison')),
    ('PT114', (SELECT personId FROM Person WHERE lastName = 'Alden' AND firstName = 'John'), (SELECT personId FROM Person WHERE lastName = 'Tom' AND firstName = 'Adison'), (SELECT personId FROM Person WHERE lastName = 'Yagami' AND firstName = 'Light'));

-- Portfolio Assets
insert into PortfolioAsset (portfolioId, assetId, assetAmount) values
	((SELECT portfolioId FROM Portfolio WHERE portCode = 'PD111'), (SELECT assetId FROM Asset WHERE assetCode = 'YMj2jjQt'), 10120.23),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PD111'), (SELECT assetId FROM Asset WHERE assetCode = '89uKEpsi'), 400),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PD111'), (SELECT assetId FROM Asset WHERE assetCode = 'ZEnSzfbI'), 50),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PD111'), (SELECT assetId FROM Asset WHERE assetCode = 'FSacHJRA'), 89),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PT001'), (SELECT assetId FROM Asset WHERE assetCode = 'TTAVkJ1z'), 54321.01),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PT001'), (SELECT assetId FROM Asset WHERE assetCode = '4WDyHL0t'), 98),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PT001'), (SELECT assetId FROM Asset WHERE assetCode = 'kzMLkD2z'), 64),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PT001'), (SELECT assetId FROM Asset WHERE assetCode = 's0rhIsHa'), 55),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PT001'), (SELECT assetId FROM Asset WHERE assetCode = 'fWHZRzrb'), 10),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PF001'), (SELECT assetId FROM Asset WHERE assetCode = '4agAKJEq'), 55),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PF001'), (SELECT assetId FROM Asset WHERE assetCode = '89uKEpsi'), 97979.10),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PF001'), (SELECT assetId FROM Asset WHERE assetCode = '4WDyHL0t'), 80900.50),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PF001'), (SELECT assetId FROM Asset WHERE assetCode = '0KZ7GLay'), 30),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PF001'), (SELECT assetId FROM Asset WHERE assetCode = 'CvGUIwRO'), 77),
	((SELECT portfolioId FROM Portfolio WHERE portCode = 'PT002'), (SELECT assetId FROM Asset WHERE assetCode = 'N4ujtpKT'), 33),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PT002'), (SELECT assetId FROM Asset WHERE assetCode = 'OYWhU0b8'), 43),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PT002'), (SELECT assetId FROM Asset WHERE assetCode = 'zUcSIYPE'), 68),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PT002'), (SELECT assetId FROM Asset WHERE assetCode = '4agAKJEq'), 30),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PT002'), (SELECT assetId FROM Asset WHERE assetCode = 'LS30qQV5'), 10100203.10),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PF002'), (SELECT assetId FROM Asset WHERE assetCode = '4WDyHL0t'), 20),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PF002'), (SELECT assetId FROM Asset WHERE assetCode = 'bC51jaAV'), 44),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PF002'), (SELECT assetId FROM Asset WHERE assetCode = 'giff6C5r'), 50),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PF002'), (SELECT assetId FROM Asset WHERE assetCode = 's0rhIsHa'), 30),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PF002'), (SELECT assetId FROM Asset WHERE assetCode = 'zd68EsoK'), 10.12),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PD001'), (SELECT assetId FROM Asset WHERE assetCode = 'N4ujtpKT'), 5.00),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PD001'), (SELECT assetId FROM Asset WHERE assetCode = 'zd68EsoK'), 43431243.10),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PD001'), (SELECT assetId FROM Asset WHERE assetCode = 'CvGUIwRO'), 43021.12),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PD001'), (SELECT assetId FROM Asset WHERE assetCode = 'TTAVkJ1z'), 981765.00),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PZ001'), (SELECT assetId FROM Asset WHERE assetCode = '4WDyHL0t'), 40),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PZ001'), (SELECT assetId FROM Asset WHERE assetCode = 'TTAVkJ1z'), 20),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PZ001'), (SELECT assetId FROM Asset WHERE assetCode = 'giff6C5r'), 43),
	((SELECT portfolioId FROM Portfolio WHERE portCode = 'PZ001'), (SELECT assetId FROM Asset WHERE assetCode = '4agAKJEq'), 63),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PZ002'), (SELECT assetId FROM Asset WHERE assetCode = 'un1qqk3u'), 23),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PZ002'), (SELECT assetId FROM Asset WHERE assetCode = 'bC51jaAV'), 43),
	((SELECT portfolioId FROM Portfolio WHERE portCode = 'PZ002'), (SELECT assetId FROM Asset WHERE assetCode = 'tAceWN9L'), 74),
	((SELECT portfolioId FROM Portfolio WHERE portCode = 'PZ002'), (SELECT assetId FROM Asset WHERE assetCode = 'LS30qQV5'), 123.51),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PF473'), (SELECT assetId FROM Asset WHERE assetCode = 'VTST'), 47852),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'CPF003'), (SELECT assetId FROM Asset WHERE assetCode = 'CD4523'), 50000),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PT114'), (SELECT assetId FROM Asset WHERE assetCode = 'SEARCH8'), 19800),
    ((SELECT portfolioId FROM Portfolio WHERE portCode = 'PZ003'), null, null);
