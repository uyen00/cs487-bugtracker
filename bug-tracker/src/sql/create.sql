DROP TABLE Comment;
DROP TABLE BugQA;
DROP TABLE BugDeveloper;
DROP TABLE BugManager;
DROP TABLE Bug;
DROP TABLE ProductQA;
DROP TABLE ProductDevelopers;
DROP TABLE Product;
DROP TABLE State;
DROP TABLE Resolution;
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

CREATE TABLE Product (
    product_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY
    	(START WITH 1, INCREMENT BY 1),
    name VARCHAR(64),
    version VARCHAR(32),
    manager_id INTEGER NOT NULL,
    PRIMARY KEY(product_id)
);

CREATE TABLE ProductQA (
	product_id INTEGER NOT NULL,
	account_id INTEGER NOT NULL,
	PRIMARY KEY (product_id, account_id)
);

CREATE TABLE ProductDevelopers (
	product_id INTEGER NOT NULL,
	account_id INTEGER NOT NULL,
	PRIMARY KEY (product_id, account_id)
);

CREATE TABLE State (
	state VARCHAR(64) PRIMARY KEY NOT NULL
);

CREATE TABLE Resolution (
	resolution VARCHAR(64) PRIMARY KEY NOT NULL
);

CREATE TABLE Bug (
    bug_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY
    	(START WITH 1, INCREMENT BY 1),
   	state VARCHAR(64) NOT NULL,
    product_id INTEGER NOT NULL,
    resolution VARCHAR(64) NOT NULL,
    open_date TIMESTAMP NOT NULL,
    steps VARCHAR(300) NOT NULL,
    shortdesc VARCHAR(300) NOT NULL,
    PRIMARY KEY(bug_id)
);


ALTER TABLE Bug ADD CONSTRAINT FK_Bug_Product
    FOREIGN KEY (product_id) REFERENCES Product(product_id);

ALTER TABLE Bug ADD CONSTRAINT FK_Bug_Resolution
    FOREIGN KEY (resolution) REFERENCES Resolution(resolution);

CREATE TABLE BugQA (
	bug_id INTEGER NOT NULL,
	account_id INTEGER NOT NULL,
	PRIMARY KEY (bug_id, account_id)
);

CREATE TABLE BugDeveloper (
	bug_id INTEGER NOT NULL,
	account_id INTEGER NOT NULL,
	PRIMARY KEY (bug_id, account_id)
);

CREATE TABLE BugManager (
	bug_id INTEGER NOT NULL,
	account_id INTEGER NOT NULL,
	PRIMARY KEY (bug_id, account_id)
);
    
CREATE TABLE Comment (
    comment_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY
    	(START WITH 1, INCREMENT BY 1),
    bug_id INTEGER NOT NULL,
    comment VARCHAR(30000) NOT NULL,
    commenter_id INTEGER NOT NULL,
    PRIMARY KEY(comment_id)
);

ALTER TABLE Comment ADD CONSTRAINT FK_Comment_Bug
    FOREIGN KEY (bug_id) REFERENCES Bug(bug_id);

ALTER TABLE Comment ADD CONSTRAINT FK_Comment_Account
    FOREIGN KEY (commenter_id) REFERENCES Account(account_id);
    
INSERT INTO Entitlement (entitlement_type) VALUES ('MANAGER');
INSERT INTO Entitlement (entitlement_type) VALUES ('DEVELOPER');
INSERT INTO Entitlement (entitlement_type) VALUES ('QA');
INSERT INTO Entitlement (entitlement_type) VALUES ('ADMIN');

INSERT INTO Account (screen_name, password) VALUES ('test', 'password');
INSERT INTO Account (screen_name, password) VALUES ('Jon', 'password');
INSERT INTO Account (screen_name, password) VALUES ('Jacob', 'password');
INSERT INTO Account (screen_name, password) VALUES ('Juan', 'password');

INSERT INTO Resolution (resolution) VALUES ('IN PROGRESS');
INSERT INTO Resolution (resolution) VALUES ('FIXED');
INSERT INTO Resolution (resolution) VALUES ('DEFER');
INSERT INTO Resolution (resolution) VALUES ('DUP');
INSERT INTO Resolution (resolution) VALUES ('NOT REPRODUCABLE');
INSERT INTO Resolution (resolution) VALUES ('NO PLAN TO FIX');
INSERT INTO Resolution (resolution) VALUES ('NOT A BUG');
INSERT INTO Resolution (resolution) VALUES ('USER BRAIN DAMAGE');
INSERT INTO Resolution (resolution) VALUES ('NEED MORE INFORMATION');

INSERT INTO State (state) VALUES ('OPEN');
INSERT INTO State (state) VALUES ('PENDING');
INSERT INTO State (state) VALUES ('VERIFIED');
INSERT INTO State (state) VALUES ('CLOSED');

INSERT INTO Product (name, version, manager_id) VALUES ('Some Project', '1.0', 2);
INSERT INTO Product (name, version, manager_id) VALUES ('Another Project', '2.0-beta', 2);
    
INSERT INTO Bug (product_id, resolution, state, open_date, steps, shortdesc) 
	VALUES (1, 'IN PROGRESS', 'OPEN', CURRENT_TIMESTAMP, 'Do this', 'Stupid bug');

INSERT INTO ProductQA (product_id, account_id) VALUES (1, 4);
INSERT INTO ProductQA (product_id, account_id) VALUES (2, 4);

INSERT INTO ProductDevelopers (product_id, account_id) VALUES (1, 3);
INSERT INTO ProductDevelopers (product_id, account_id) VALUES (2, 3);

INSERT INTO BugQA (bug_id, account_id) VALUES (1, 4);

INSERT INTO BugDeveloper (bug_id, account_id) VALUES (1, 3);
	
INSERT INTO Comment (bug_id, comment, commenter_id) VALUES (1, 'This bug stinks', 1);
INSERT INTO Comment (bug_id, comment, commenter_id) VALUES (1, 'Your opinion', 2);
INSERT INTO Comment (bug_id, comment, commenter_id) VALUES (1, 'No its a fact', 1);

INSERT INTO AccountEntitlement
	SELECT a.account_id, e.entitlement_id FROM Account a, Entitlement e
	WHERE a.screen_name = 'test' AND e.entitlement_type = 'ADMIN';

INSERT INTO AccountEntitlement
	SELECT a.account_id, e.entitlement_id FROM Account a, Entitlement e
	WHERE a.screen_name = 'Jon' AND e.entitlement_type = 'MANAGER';

INSERT INTO AccountEntitlement
	SELECT a.account_id, e.entitlement_id FROM Account a, Entitlement e
	WHERE a.screen_name = 'Jacob' AND e.entitlement_type = 'DEVELOPER';

INSERT INTO AccountEntitlement
	SELECT a.account_id, e.entitlement_id FROM Account a, Entitlement e
	WHERE a.screen_name = 'Juan' AND e.entitlement_type = 'QA';