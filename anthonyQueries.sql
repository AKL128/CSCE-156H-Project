
-- 1.
SELECT p.personCode, p.brokerData, p.firstName, p.lastName, a.street, a.city, s.stateName, c.countryName FROM Person p
JOIN Address a ON p.addressId = a.addressId
JOIN State s ON a.stateId = s.stateId
JOIN Country c ON a.countryId = c.countryId;

-- 2.
SELECT p.firstName, p.lastName, e.emailName FROM Person p
LEFT JOIN Email e ON p.personId = e.personId
WHERE p.personId = 1;


-- 3.
UPDATE Email SET personId = 1 WHERE emailId = 19;

-- 4
UPDATE Email SET emailName = "changedEmail@what.com" WHERE emailId = 1;

-- 5
DELETE FROM Email WHERE personId = 1;
DELETE FROM PortfolioAsset WHERE portfolioId = 1;
DELETE FROM Portfolio WHERE ownerId = 1;
DELETE FROM Person WHERE personId = 1;

-- 6
INSERT INTO Person (personCode, brokerData, firstName, lastName, addressId) values
	('code123', 'E,sec700', 'Anthony', 'Luu', 1);

-- 7
SELECT  a.* FROM Portfolio p
LEFT JOIN PortfolioAsset pa ON p.portfolioId = pa.portfolioId
LEFT JOIN Asset a ON pa.assetId = a.assetId
WHERE p.portCode = 'PT001';

-- 8
SELECT a.* FROM Person p
LEFT JOIN Portfolio po ON p.personId = po.ownerId
LEFT JOIN PortfolioAsset pa ON po.portfolioId = pa.portfolioId
LEFT JOIN Asset a ON pa.assetId = a.assetId
WHERE p.personId = 3;

-- 9
INSERT INTO Asset (assetCode, assetType, assetLabel, apr, balance, quarterlyDividend, baseRateOfReturn, betaMeasure, stockSymbol, sharePrice, baseOmegaMeasure, totalValue) values
	('assetCode123', 'S', 'THE STONK', null, 120000.35, 63.02, 0.244, 50, 'STONK', 10000.57, null, null);
    
-- 10
INSERT INTO Portfolio (portCode, ownerId, managerId, beneficiaryId) values
	('P999', 2, 3, 4);
    
-- 11
INSERT INTO PortfolioAsset (portfolioId, assetId) values
	((SELECT portfolioId FROM Portfolio WHERE portCode = 'PZ002'),(SELECT assetId FROM Asset WHERE assetCode = 'zUcSIYPE'));

-- 12
SELECT p.firstName, p.lastName, count(po.portfolioId) FROM Person p
LEFT JOIN Portfolio po ON p.personId = po.ownerId
WHERE p.firstName = 'Fred' AND p.lastName = 'Biffin';

-- 13
SELECT p.firstName, p.lastName, count(po.portfolioId) FROM Person p
LEFT JOIN Portfolio po ON p.personId = po.managerId
WHERE p.firstName = 'Simona' AND p.lastName = 'Brant';

-- 14
SELECT SUM(a.sharePrice * )
