start transaction;

drop table if exists Person;
drop table if exists Address;
drop table if exists State;
drop table if exists Country;
drop table if exists Stock;
drop table if exists DepositAccount;
drop table if exists PrivateInvestment;

create table Person (
  personId varchar(255) not null primary key auto_increment,
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
  stateId varchar(255) not null primary key auto_increment,
  stateName varchar(255) not null
)engine=InnoDB,collate=latin1_general_cs;

create table Country (
  countryId varchar(255) not null primary key auto_increment,
  countryName varchar(255) not null
)engine=InnoDB,collate=latin1_general_cs;

create table Stock (
  assetId varchar(255) not null primary key auto_increment,
  ownerId varchar(255),
  quarterlyDividend float,
  baseRateOfReturn float,
  betaMeasure float,
  stockSymbol varchar(255),
  sharePrice float,
  foreign key (ownerId) references Person(personId)
)engine=InnoDB,collate=latin1_general_cs;

create table DepositAccount (
  assetId varchar(255) not null primary key auto_increment,
  ownerId varchar(255),
  apr float,
  balance float,
  foreign key (ownerId) references Person(personId)
)engine=InnoDB,collate=latin1_general_cs;

create table PrivateInvestment (
  assetId varchar(255) not null primary key auto_increment,
  ownerId varchar(255),
  quarterlyDividend float,
  baseRateOfReturn float,
  baseOmegaMeasure float,
  totalValue float,
  foreign key (ownerId) references Person(personId)
)engine=InnoDB,collate=latin1_general_cs;

create table Portfolio (
  portCode varchar(255) not null primary key auto_increment,
  ownerId varchar(255),
  managerId varchar(255),
  beneficiaryId varchar(255)
  foreign key (ownerId) references Person(personId),
  foreign key (managerId) references Person(personId),
  foreign key (beneficiaryId) references Person(personId)
)engine=InnoDB,collate=latin1_general_cs;
