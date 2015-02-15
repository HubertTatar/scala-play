---------
--USERS--
---------

CREATE TABLE "HR"."USERS" 
   (  
   "ID" NUMBER(10,0), 
  "NAME" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
  "PASSWORD" VARCHAR2(40 BYTE) NOT NULL ENABLE, 
  "ACTIVE" VARCHAR2(1 BYTE) DEFAULT 'N', 
  "BLOCKED" VARCHAR2(1 BYTE) DEFAULT 'N', 
  "VERSION" NUMBER(10) DEFAULT 0 NOT NULL 
  );

  CREATE UNIQUE INDEX USERS_PK ON USERS(ID);
  CREATE UNIQUE INDEX USERS_IDX_1 ON USERS(NAME);
  
  ALTER TABLE USERS ADD CONSTRAINT "USERS_PK" PRIMARY KEY ("ID") USING INDEX;

CREATE SEQUENCE user_id_seq
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  CACHE 10;

CREATE OR REPLACE TRIGGER users_trg1
BEFORE INSERT ON users
FOR EACH ROW
BEGIN
SELECT user_id_seq.nextval INTO :new.id FROM dual;
END;
	
CREATE UNIQUE INDEX users_idx_1
  ON users (name);
  
ALTER INDEX users_idx_1
  REBUILD COMPUTE STATISTICS;


---------
--ROLES--
---------

CREATE TABLE "HR"."ROLES" (
	"ID" NUMBER(10),
	"NAME" VARCHAR2(20) NOT NULL
); 

CREATE UNIQUE INDEX ROLES_IDX_1 ON ROLES (name);
CREATE UNIQUE INDEX ROLES_PK ON ROLES (ID);
ALTER TABLE ROLES ADD CONSTRAINT ROLES_PK PRIMARY KEY (ID) USING INDEX ;


--------------
--USER_ROLES--
--------------


CREATE TABLE "USER_ROLES" (
  "USER_ID"   NUMBER(10),
  "ROLE_ID"   NUMBER(10),
  "ADD_DATE"  DATE,
  "REM_DATE"  DATE,
  "CTRL_ID"   NUMBER(10)
);

CREATE UNIQUE INDEX USER_ROLES_IDX_1 ON USER_ROLES (USER_ID);
CREATE UNIQUE INDEX USER_ROLES_IDX_2 ON USER_ROLES (ROLE_ID);

ALTER TABLE USER_ROLES ADD CONSTRAINT USER_ROLES_FK1 FOREIGN KEY (USER_ID) REFERENCES USERS(ID); 
ALTER TABLE USER_ROLES ADD CONSTRAINT USER_ROLES_FK2 FOREIGN KEY (CTRL_ID) REFERENCES USERS(ID);
ALTER TABLE USER_ROLES ADD CONSTRAINT USER_ROLES_FK3 FOREIGN KEY (ROLE_ID) REFERENCES ROLES(ID);


insert into user_roles(user_id, role_id, add_date, rem_date,ctrl_id) 
values(1,1,to_date('2015-01-01','yyyy-mm-dd'),to_date('2015-01-01','yyyy-mm-dd'),1);

insert into user_roles(user_id, role_id, add_date, rem_date,ctrl_id) 
values(1,2,to_date('2015-01-01','yyyy-mm-dd'),to_date('2015-01-01','yyyy-mm-dd'),1);

insert into user_roles(user_id, role_id, add_date, rem_date,ctrl_id) 
values(1,3,to_date('2015-01-01','yyyy-mm-dd'),to_date('2015-01-01','yyyy-mm-dd'),1);

insert into user_roles(user_id, role_id, add_date, rem_date,ctrl_id) 
values(1,4,to_date('2015-01-01','yyyy-mm-dd'),to_date('2015-01-01','yyyy-mm-dd'),1);


insert into roles(id,name) values(1, 'ADMIN');
insert into roles(id,name) values(2, 'MANAGER');
insert into roles(id,name) values(3, 'VERIFICATION');
insert into roles(id,name) values(4, 'ACTEPT');