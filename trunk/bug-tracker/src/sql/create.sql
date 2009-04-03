DROP TABLE AccountEntitlement;
DROP TABLE Account;
DROP TABLE Entitlement;

CREATE TABLE Account (
    account_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY
    	(START WITH 1, INCREMENT BY 1),
    screen_name VARCHAR(32) UNIQUE NOT NULL,
    password VARCHAR(32) NOT NULL, 
    PRIMARY KEY(account_id)
);

CREATE TABLE Entitlement(
	entitlement_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY
    	(START WITH 1, INCREMENT BY 1),
    entitlement_type VARCHAR(32) UNIQUE NOT NULL,
    PRIMARY KEY (entitlement_id)
);

CREATE TABLE AccountEntitlement(
	account_id INTEGER NOT NULL,
	entitlement_id INTEGER NOT NULL,
	PRIMARY KEY (account_id, entitlement_id)
);

ALTER TABLE AccountEntitlement ADD CONSTRAINT FK_AccountEntitlement_Account
    FOREIGN KEY (account_id) REFERENCES Account(account_id);
	
ALTER TABLE AccountEntitlement ADD CONSTRAINT FK_AccountEntitlement_Entitlement
    FOREIGN KEY (account_id) REFERENCES Account(account_id);
    
INSERT INTO Entitlement (entitlement_type) VALUES ('MANAGER');
INSERT INTO Entitlement (entitlement_type) VALUES ('DEVELOPER');
INSERT INTO Entitlement (entitlement_type) VALUES ('QA');
INSERT INTO Entitlement (entitlement_type) VALUES ('ADMIN');

INSERT INTO Account (screen_name, password) VALUES ('test', 'password');

INSERT INTO AccountEntitlement
	SELECT a.account_id, e.entitlement_id FROM Account a, Entitlement e
	WHERE a.screen_name = 'test' AND e.entitlement_type = 'ADMIN';