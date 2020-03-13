--- 1
SELECT p.personId, p.personCode, p.brokerData, p.firstName, p.lastName, a.street,
a.city, a.zipCode, s.stateName, c.countryName, e.emailName FROM Person p
LEFT JOIN Address a ON p.addressId = a.addressId
LEFT JOIN State s ON a.stateId = s.stateId
LEFT JOIN Country c ON a.countryId = c.countryId
LEFT JOIN Email e ON p.personId = e.personId;

--- 2
SELECT e.emailName FROM Person p
JOIN Email e ON p.personId = e.personId
WHERE p.personCode = 'VB76HV';

--- 3
INSERT INTO Email (personId, emailName) VALUES (1, 'deezson01@altacct.com');

--- 4
UPDATE Email
SET emailName = 'adeeson69@5shared.com'
WHERE emailId = 1 AND emailName = 'adeeson0@4shared.com';

--- 5
DELETE FROM PortfolioAsset WHERE portfolioId = 1;
DELETE FROM Portfolio WHERE ownerId = 1;
DELETE FROM Person WHERE personId = 1;

--- 6
INSERT INTO Person (personCode, brokerData, firstName, lastName, addressId) VALUES ('BWB316', null, 'Brett', 'Berg', 1);

--- 7
SELECT a.assetCode FROM Portfolio p
JOIN PortfolioAsset pa ON p.portfolioId = pa.portfolioId
JOIN Asset a ON pa.assetId = a.assetId
WHERE p.portfolioId = 2;

--- 8
SELECT a.assetCode FROM Person p
JOIN Portfolio po ON p.personId = po.ownerId
JOIN PortfolioAsset pa ON po.portfolioId = pa.portfolioId
JOIN Asset a ON pa.assetId = a.assetId
WHERE p.personId = 3;

--- 9
INSERT INTO Asset (assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, 
stockSymbol, sharePrice, baseOmegaMeasure, totalValue) VALUES ('NEWTHING', 'D', 'Boop', 69.69, null, null, null, null, 
null, null, null, null);

--- 10
INSERT INTO Portfolio (portCode, ownerId, managerId, beneficiaryId) VALUES ('PB007', 17, 14, null);

--- 11
INSERT INTO PortfolioAsset (portfolioId, assetId, assetAmount) VALUES (9, 31, 900);

--- 12
SELECT COUNT(p.portCode) FROM Person a
WHERE