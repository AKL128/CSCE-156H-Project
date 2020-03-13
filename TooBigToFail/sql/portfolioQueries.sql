
-- 1.
SELECT p.personCode, p.brokerData, p.firstName, p.lastName, a.street, a.city, s.stateName, c.countryName, e.emailName FROM Person p
JOIN Address a ON p.addressId = a.addressId
JOIN State s ON a.stateId = s.stateId
JOIN Country c ON a.countryId = c.countryId
LEFT JOIN Email e ON p.personId = e.personId;

-- 2.
SELECT p.firstName, p.lastName, e.emailName FROM Person p
LEFT JOIN Email e ON p.personId = e.personId
WHERE p.personCode = 'CWQ9R0';

-- 3.
INSERT INTO Email (personId, emailName) VALUES ((SELECT personId FROM Person WHERE lastName = 'Calcut' AND firstName = 'Brand')
												, 'AddedEmail@gmail.com');

-- 4
UPDATE Email SET emailName = "changedEmail@what.com"
WHERE emailId = 500 AND emailName = 'bcalcutf@shutterfly.com';

-- 5
DELETE FROM Email WHERE personId = 1;
DELETE FROM PortfolioAsset WHERE portfolioId = (SELECT portfolioId FROM Portfolio WHERE ownerId = 1);
DELETE FROM Portfolio WHERE ownerId = 1;
DELETE FROM Person WHERE personCode = 'VB76HV' AND personId = 1;

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
WHERE p.firstName = 'Brand' AND p.lastName = 'Calcut';

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
SELECT p.portCode, ROUND(SUM(a.sharePrice * pa.assetAmount), 2) FROM Portfolio p
JOIN PortfolioAsset pa ON p.portfolioId = pa.portfolioId
JOIN Asset a ON pa.assetId = a.assetId
WHERE a.assetType = 'S'
GROUP BY p.portCode;

-- 15
SELECT a.assetCode, pa.assetAmount FROM Asset a
JOIN PortfolioAsset pa ON a.assetId = pa.assetId
WHERE pa.assetAmount > 100 AND a.assetType = 'P';
