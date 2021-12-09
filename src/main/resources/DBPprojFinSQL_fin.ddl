DROP SEQUENCE policyIdSeq;

CREATE SEQUENCE policyIdSeq;

DROP SEQUENCE postNumSeq;

CREATE SEQUENCE postNumSeq;

DROP SEQUENCE replyNumSeq;

CREATE SEQUENCE replyNumSeq;

DROP TABLE Scrap CASCADE CONSTRAINTS PURGE;

DROP TABLE Reply CASCADE CONSTRAINTS PURGE;

DROP TABLE Post CASCADE CONSTRAINTS PURGE;

DROP TABLE Policy CASCADE CONSTRAINTS PURGE;

DROP TABLE User1 CASCADE CONSTRAINTS PURGE;

CREATE TABLE Policy
(
	policyId             INTEGER NOT NULL ,
	name                 VARCHAR2(18) NULL ,
	startAge             INTEGER NULL ,
	contents             VARCHAR2(1000) NULL ,
	category             VARCHAR2(18) NULL ,
	local                VARCHAR2(18) NULL ,
	policySummary        VARCHAR2(18) NULL ,
	qualificationForApplication VARCHAR2(18) NULL ,
	howToApply           VARCHAR2(18) NULL ,
	startDate            DATE NULL ,
	income               INTEGER NULL ,
	endAge               INTEGER NULL ,
	endDate              DATE NULL ,
	scrap                CHAR(1) NULL 
);

CREATE UNIQUE INDEX XPKPolicy ON Policy
(policyId   ASC);

ALTER TABLE Policy
	ADD CONSTRAINT  XPKPolicy PRIMARY KEY (policyId);

CREATE TABLE Post
(
	postNum              INTEGER NOT NULL ,
	title                VARCHAR2(30) NULL ,
	writeDate            DATE NULL ,
	content              VARCHAR2(500) NULL ,
	user_Id              VARCHAR2(20) NOT NULL ,
	policyId             INTEGER NOT NULL 
);

CREATE UNIQUE INDEX XPKPost ON Post
(postNum   ASC);

ALTER TABLE Post
	ADD CONSTRAINT  XPKPost PRIMARY KEY (postNum);

CREATE TABLE Reply
(
	agree                CHAR(1) NULL ,
	replyContent         VARCHAR2(100) NULL ,
	postNum              INTEGER NOT NULL ,
	replyNum             INTEGER NULL ,
	disagree             CHAR(1) NULL 
);

CREATE UNIQUE INDEX XPKReply ON Reply
(postNum   ASC);

ALTER TABLE Reply
	ADD CONSTRAINT  XPKReply PRIMARY KEY (postNum);

CREATE TABLE User1
(
	user_Id              VARCHAR2(20) NOT NULL ,
	name                 VARCHAR2(20) NULL ,
	email                VARCHAR2(30) NULL ,
	password             VARCHAR2(20) NULL ,
	birth                DATE NULL ,
	phoneNumber          CHAR(13) NULL 
);

CREATE UNIQUE INDEX XPKUser1 ON User1
(user_Id   ASC);

ALTER TABLE User1
	ADD CONSTRAINT  XPKUser1 PRIMARY KEY (user_Id);

CREATE TABLE Scrap
(
	policyId             INTEGER NOT NULL ,
	user_Id              VARCHAR2(20) NOT NULL 
);

CREATE UNIQUE INDEX XPKScrap ON Scrap
(user_Id   ASC,policyId   ASC);

ALTER TABLE Scrap
	ADD CONSTRAINT  XPKScrap PRIMARY KEY (user_Id,policyId);

ALTER TABLE Post
	ADD (CONSTRAINT R_2 FOREIGN KEY (user_Id) REFERENCES User1 (user_Id));

ALTER TABLE Post
	ADD (CONSTRAINT R_3 FOREIGN KEY (policyId) REFERENCES Policy (policyId));

ALTER TABLE Reply
	ADD (CONSTRAINT R_6 FOREIGN KEY (postNum) REFERENCES Post (postNum));

ALTER TABLE Scrap
	ADD (CONSTRAINT R_4 FOREIGN KEY (policyId) REFERENCES Policy (policyId));

ALTER TABLE Scrap
	ADD (CONSTRAINT R_5 FOREIGN KEY (user_Id) REFERENCES User1 (user_Id));


INSERT INTO User1 VALUES ('dbpro0102', '°ü¸®ÀÚ', null, 'dbpro0102', null, null);

commit;
