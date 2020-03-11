start transaction;
use bberg;

SET FOREIGN_KEY_CHECKS = 0;

drop table if exists Person;
drop table if exists Address;
drop table if exists State;
drop table if exists Country;
drop table if exists Asset;
drop table if exists Portfolio;
drop table if exists PortfolioAsset;

create table Person (
  personId int not null primary key auto_increment,
  personCode varchar(255),
  brokerData varchar(255),
  firstName varchar(255),
  lastName varchar(255),
  addressId int not null,
  emailId varchar(255) not null
)engine=InnoDB,collate=latin1_general_cs;

create table Address (
  addressId int not null primary key auto_increment,
  street varchar(255),
  city varchar(255),
  stateId int,
  zipCode varchar(255),
  countryId int
)engine=InnoDB,collate=latin1_general_cs;

create table State (
  stateId int not null primary key auto_increment,
  stateName varchar(255) not null
)engine=InnoDB,collate=latin1_general_cs;

create table Country (
  countryId int not null primary key auto_increment,
  countryName varchar(255) not null
)engine=InnoDB,collate=latin1_general_cs;

create table Asset(
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

create table Portfolio (
  portfolioId int not null primary key auto_increment,
  portCode varchar(255),
  ownerId int,
  managerId int,
  beneficiaryId int,
  foreign key (ownerId) references Person(personId),
  foreign key (managerId) references Person(personId),
  foreign key (beneficiaryId) references Person(personId)
)engine=InnoDB,collate=latin1_general_cs;

create table PortfolioAsset (
  portAssetId int not null primary key auto_increment,
  portfolioId int,
  assetId int,
  assetAmount float,
  foreign key (portfolioId) references Portfolio(portfolioId),
  foreign key (assetId) references Asset(assetId)
)engine=InnoDB,collate=latin1_general_cs;

--- Persons
insert into Person (personId, personCode, brokerData, firstName, lastName, addressId, emailId) values (1, 'VB76HV', 'E,sec725', 'Amara', 'Deeson', 101, 'adeeson0@4shared.com');
insert into Person (personId, personCode, brokerData, firstName, lastName, addressId, emailId) values (2, 'CWQ9R0', 'J,sec368', 'Fred', 'Biffin', 102, 'fbiffin1@shutterfly.com');
insert into Person (personId, personCode, brokerData, firstName, lastName, addressId, emailId) values (3, 'BASMCU', null, 'Sergei', 'Rignoldes', 103, 'srignoldes2@hhs.gov');
insert into Person (personId, personCode, brokerData, firstName, lastName, addressId, emailId) values (4, 'B034B9', null, 'Tamiko', 'Ropartz', 104, 'tropartz3@ox.ac.uk');
insert into Person (personId, personCode, brokerData, firstName, lastName, addressId, emailId) values (5, 'K26CR3', 'E,sec564', 'Opaline', 'Lacaze', 105, 'olacaze4@blogtalkradio.com');
insert into Person (personId, personCode, brokerData, firstName, lastName, addressId, emailId) values (6, 'NA2V3T', null, 'Dag', 'McAllen', 106, 'dmcallen5@dagondesign.com');
insert into Person (personId, personCode, brokerData, firstName, lastName, addressId, emailId) values (7, 'VIO4C2', 'J,sec742', 'Glad', 'Applegate', 107, 'gapplegate6@si.edu');
insert into Person (personId, personCode, brokerData, firstName, lastName, addressId, emailId) values (8, 'R3M84Y', null, 'Simona', 'Brant', 108, 'sbrant7@mayoclinic.com');
insert into Person (personId, personCode, brokerData, firstName, lastName, addressId, emailId) values (9, 'GBRFGX', null, 'Barton', 'Mansour', 109, 'bmansour8@e-recht24.de');
insert into Person (personId, personCode, brokerData, firstName, lastName, addressId, emailId) values (10, '8OMYLX', 'J,sec116', 'Lucky', 'Lowbridge', 110, 'llowbridge9@trellian.com');
insert into Person (personId, personCode, brokerData, firstName, lastName, addressId, emailId) values (11, 'MVLJFF', 'E,sec581', 'Alano', 'Grevel', 111, 'agrevela@earthlink.net');
insert into Person (personId, personCode, brokerData, firstName, lastName, addressId, emailId) values (12, 'Y0QRRR', null, 'Emera', 'Schirach', 112, 'eschirachb@abc.net.au');
insert into Person (personId, personCode, brokerData, firstName, lastName, addressId, emailId) values (13, 'SI6KNE', 'J,sec732', 'Minna', 'Seabrooke', 113, 'mseabrookec@live.com');
insert into Person (personId, personCode, brokerData, firstName, lastName, addressId, emailId) values (14, 'JSTWZL', 'J,sec824', 'Marco', 'Gasson', 114, 'mgassond@cmu.edu');
insert into Person (personId, personCode, brokerData, firstName, lastName, addressId, emailId) values (15, 'AI4H73', 'J,sec324', 'Ikey', 'Shelborne', 115, 'ishelbornee@digg.com');
insert into Person (personId, personCode, brokerData, firstName, lastName, addressId, emailId) values (16, '8H1DJB', 'J,sec020', 'Brand', 'Calcut', 116, 'bcalcutf@shutterfly.com');

--- Addresses
insert into Address (addressId, street, city, stateId, zipCode, countryId) values (101, '24624 Glacier Hill Alley', 'Springfield', 201, '22156', 301);
insert into Address (addressId, street, city, stateId, zipCode, countryId) values (102, '04 Talmadge Crossing', 'Hampton', 201, '23663', 301);
insert into Address (addressId, street, city, stateId, zipCode, countryId) values (103, '0 Autumn Leaf Plaza', 'Los Angeles', 202, '90045', 301);
insert into Address (addressId, street, city, stateId, zipCode, countryId) values (104, '796 Grover Crossing', 'Richmond', 201, '23208', 301);
insert into Address (addressId, street, city, stateId, zipCode, countryId) values (105, '18169 Knutson Point', 'Arlington', 203, '76096', 301);
insert into Address (addressId, street, city, stateId, zipCode, countryId) values (106, '00 Esker Road', 'Athens', 204, '30605', 301);
insert into Address (addressId, street, city, stateId, zipCode, countryId) values (107, '61 Weeping Birch Parkway', 'Youngstown', 205, '44511', 301) ;
insert into Address (addressId, street, city, stateId, zipCode, countryId) values (108, '57 Holy Cross Circle', 'Des Moines', 206, '50369', 301);
insert into Address (addressId, street, city, stateId, zipCode, countryId) values (109, '63417 Sutteridge Junction', 'Bakersfield', 202, '93305', 301);
insert into Address (addressId, street, city, stateId, zipCode, countryId) values (110, '6574 Forest Trail', 'Phoenix', 207, '85015', 301);
insert into Address (addressId, street, city, stateId, zipCode, countryId) values (111, '44 Graceland Terrace', 'Lincoln', 208, '68524', 301);
insert into Address (addressId, street, city, stateId, zipCode, countryId) values (112, '3 Columbus Center', 'Jamaica', 209, '11499', 301);
insert into Address (addressId, street, city, stateId, zipCode, countryId) values (113, '64202 Mcbride Road', 'Little Rock', 210, '72204', 301);
insert into Address (addressId, street, city, stateId, zipCode, countryId) values (114, '09 Cascade Plaza', 'Visalia', 202, '93291', 301);
insert into Address (addressId, street, city, stateId, zipCode, countryId) values (115, '5 Oakridge Pass', 'Yakima', 211, '98907', 301);
insert into Address (addressId, street, city, stateId, zipCode, countryId) values (116, '9 Colorado Alley', 'Phoenix', 207, '85015', 301);

--- States
insert into State (stateId, stateName) values (201, 'Virginia');
insert into State (stateId, stateName) values (202, 'California');
insert into State (stateId, stateName) values (203, 'Texas');
insert into State (stateId, stateName) values (204, 'Georgia');
insert into State (stateId, stateName) values (205, 'Ohio');
insert into State (stateId, stateName) values (206, 'Iowa');
insert into State (stateId, stateName) values (207, 'Arizona');
insert into State (stateId, stateName) values (208, 'Nebraska');
insert into State (stateId, stateName) values (209, 'New York');
insert into State (stateId, stateName) values (210, 'Arkansas');
insert into State (stateId, stateName) values (211, 'Washington');

--- Countries
insert into Country (countryId, countryName) values (301, 'United States');

--- Assets
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (401, 'YMj2jjQt', 'D', 'Photobug', 83.89, null, null, null, null, null, null, null, null);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (402, 'FSacHJRA', 'D', 'Minyx', 36.5, null, null, null, null, null, null, null, null);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (403, 'TTAVkJ1z', 'D', 'Kimia', 80.46, null, null, null, null, null, null, null, null);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (404, 'WSWGFGOt', 'D', 'Tazz', 2.14, null, null, null, null, null, null, null, null);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (405, 'Z2XGqhbU', 'D', 'Photobug', 22.17, null, null, null, null, null, null, null, null);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (406, 'OYWhU0b8', 'D', 'Agivu', 13.34, null, null, null, null, null, null, null, null);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (407, 'NMgkglJq', 'D', 'Gigabox', 75.03, null, null, null, null, null, null, null, null);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (408, 'tAceWN9L', 'D', 'Yotz', 32.12, null, null, null, null, null, null, null, null);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (409, 'zUcSIYPE', 'D', 'Thoughtbridge', 70.95, null, null, null, null, null, null, null, null);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (410, 'giff6C5r', 'D', 'Dablist', 70.77, null, null, null, null, null, null, null, null);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (411, '89uKEpsi', 'S', 'Buzzster', null, 1738472.35, 53.02, 0.14452, 43, 'ACV', 99737.57, null, null);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (412, '4WDyHL0t', 'S', 'Realbridge', null, 4698672.52, 13.78, 0.85331, 21, 'PRA', 1963.33, null, null);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (413, 'OM3XnDC7', 'S', 'Trupe', null, 9816301.0, 30.42, 0.09815, 87, 'RESN', 56916.89, null, null);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (414, 's0rhIsHa', 'S', 'Topdrive', null, 6828861.31, 27.51, 0.87092, 77.77, 'KND', 834.36, null, null);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (415, 'fWHZRzrb', 'S', 'Aimbo', null, 353829.81, 8.21, 0.1455, 12.21, 'SSFN', 21662.82, null, null);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (416, '0KZ7GLay', 'S', 'Twimbo', null, 9852915.0, 82.89, 0.43662, 44.4, 'TIER', 36996.0, null, null);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (417, 'CvGUIwRO', 'S', 'Meemm', null, 2656051.28, 36.34, 0.41667, 69, 'EW', 42952.67, null, null);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (418, 'Q2pTejug', 'S', 'Skyvu', null, 5381220.0, 38.14, 0.55755, 65.57, 'HABT', 74776.52, null, null);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (419, 'un1qqk3u', 'S', 'Talane', null, 3163798.35, 6.21, 0.86828, 1, 'Y', 54341.31, null, null);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (420, '4agAKJEq', 'S', 'Rhycero', null, 4224872.0, 64.13, 0.26453, 99.9, 'WTW', 33628.92, null, null);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (421, 'ZEnSzfbI', 'P', 'Roombo', null, 3519178.68, 15.08, null, null, null, null, 0.86325, 6514528.34);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (422, 'kzMLkD2z', 'P', 'Kwimbee', null, 3125087.79, 38.38, null, null, null, null, 0.0325, 729509.87);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (423, 'N9dpHjke', 'P', 'Avamm', null, 8901536.0, 12.37, null, null, null, null, 0.76147, 666338.36);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (424, 'N4ujtpKT', 'P', 'Fadeo', null, 5659081.7, 82.82, null, null, null, null, 0.71471, 549900.29);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (425, 'EbTjfJsg', 'P', 'JumpXS', null, 325017.3, 94.36, null, null, null, null, 0.70894, 5780667.82);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (426, 'hm7FbEF2', 'P', 'Livetube', null, 8482339.0, 11.79, null, null, null, null, 0.36347, 8560968.0);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (427, 'zd68EsoK', 'P', 'Vidoo', null, 3567412.0, 63.69, null, null, null, null, 0.06971, 2774868.86);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (428, 'D6j50RPV', 'P', 'Zoozzy', null, 9500256.0, 21.91, null, null, null, null, 0.62879, 8101357.0);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (429, 'bC51jaAV', 'P', 'Tagchat', null, 5180527.3, 11.15, null, null, null, null, 0.35742, 7391383.09);
insert into Asset (assetId, assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values (430, 'LS30qQV5', 'P', 'Wordify', null, 8041050.18, 34.4, null, null, null, null, 0.03922, 9126317.0);

--- Portfolios
insert into Portfolio (portfolioId, portCode, ownerId, managerId, beneficiaryId) values (501, 'PD111', 1, 2, null); 
insert into Portfolio (portfolioId, portCode, ownerId, managerId, beneficiaryId) values (502, 'PT001', 3, 4, null); 
insert into Portfolio (portfolioId, portCode, ownerId, managerId, beneficiaryId) values (503, 'PF001', 5, 1, null); 
insert into Portfolio (portfolioId, portCode, ownerId, managerId, beneficiaryId) values (504, 'PT002', 6, 7, null); 
insert into Portfolio (portfolioId, portCode, ownerId, managerId, beneficiaryId) values (505, 'PF002', 8, 9, null); 
insert into Portfolio (portfolioId, portCode, ownerId, managerId, beneficiaryId) values (506, 'PD001', 10, 11, null); 
insert into Portfolio (portfolioId, portCode, ownerId, managerId, beneficiaryId) values (507, 'PZ001', 12, 14, null); 
insert into Portfolio (portfolioId, portCode, ownerId, managerId, beneficiaryId) values (508, 'Pz002', 15, 16, null); 

--- Portfolio Assets
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (601, 501, 401, 10120.23);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (602, 501, 401, 400);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (603, 501, 411, 50);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (604, 501, 421, 89);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (605, 502, 402, 54321.01);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (606, 502, 412, 98);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (607, 502, 413, 64);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (608, 502, 422, 55);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (609, 502, 421, 10);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (610, 503, 403, 12455.68);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (611, 503, 404, 97979.10);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (612, 503, 401, 80900.50);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (613, 503, 414, 30);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (614, 503, 423, 77);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (615, 504, 421, 33);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (616, 504, 427, 43);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (617, 504, 430, 68);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (618, 504, 418, 30);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (619, 504, 410, 10100203.10);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (620, 505, 416, 20);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (621, 505, 417, 44);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (622, 505, 418, 50);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (623, 505, 411, 30);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (624, 505, 410, 10.12);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (625, 506, 408, 5.00);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (626, 506, 409, 43431243.10);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (627, 506, 402, 43021.12);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (628, 506, 403, 981765.00);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (629, 507, 417, 40);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (630, 507, 415, 20);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (631, 507, 424, 43);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (632, 507, 421, 63);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (633, 508, 430, 23);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (634, 508, 426, 43);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (635, 508, 423, 74);
insert into PortfolioAsset (portAssetId, portfolioId, assetId, assetAmount) values (636, 508, 410, 123.32);
