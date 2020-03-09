start transaction;

drop table if exists Person;
drop table if exists Address;
drop table if exists State;
drop table if exists Country;
drop table if exists Asset;

create table Person (
  personId int not null primary key auto_increment,
  brokerData varchar(255) not null,
  firstName varchar(255),
  lastName varchar(255),
  addressId int not null,
  emailId int not null
)engine=InnoDB,collate=latin1_general_cs;

create table Address (
  addressId int not null primary key auto_increment,
  street varchar(255),
  city varchar(255),
  stateId varchar(255),
  zipCode varchar(255),
  countryId varchar(255)
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
  portCode int not null primary key auto_increment,
  ownerId int,
  managerId int,
  beneficiaryId int,
  foreign key (ownerId) references Person(personId),
  foreign key (managerId) references Person(personId),
  foreign key (beneficiaryId) references Person(personId)
)engine=InnoDB,collate=latin1_general_cs;
