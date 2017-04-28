ALTER TABLE SBI_META_TABLE_BC
	DROP CONSTRAINT FK_SBI_META_TABLE_BC_2;
ALTER TABLE SBI_META_TABLE_BC
	ADD CONSTRAINT FK_SBI_META_TABLE_BC_2 FOREIGN KEY (BC_ID) REFERENCES SBI_META_BC (BC_ID) ON DELETE CASCADE;

ALTER TABLE SBI_META_BC
	DROP CONSTRAINT FK_SBI_META_BC_1;
ALTER TABLE SBI_META_BC
	ADD CONSTRAINT FK_SBI_META_BC_1 FOREIGN KEY (MODEL_ID) REFERENCES SBI_META_MODELS (ID) ON DELETE CASCADE;
	
ALTER TABLE SBI_META_BC_ATTRIBUTE
	DROP CONSTRAINT FK_SBI_META_BC_ATTRIBUTE_1;
ALTER TABLE SBI_META_BC_ATTRIBUTE
	ADD CONSTRAINT FK_SBI_META_BC_ATTRIBUTE_1 FOREIGN KEY (BC_ID) REFERENCES SBI_META_BC (BC_ID) ON DELETE CASCADE;	
	
ALTER TABLE SBI_META_BC_ATTRIBUTE
	DROP CONSTRAINT FK_SBI_META_BC_ATTRIBUTE_2;
ALTER TABLE SBI_META_BC_ATTRIBUTE
	ADD CONSTRAINT FK_SBI_META_BC_ATTRIBUTE_2 FOREIGN KEY (COLUMN_ID) REFERENCES SBI_META_TABLE_COLUMN (COLUMN_ID) ON DELETE CASCADE;	
	
ALTER TABLE SBI_META_DS_BC
	DROP CONSTRAINT FK_SBI_META_DS_BC_2;
ALTER TABLE SBI_META_DS_BC
	ADD CONSTRAINT FK_SBI_META_DS_BC_2 FOREIGN KEY (BC_ID) REFERENCES SBI_META_BC (BC_ID) ON DELETE CASCADE;
	
	
update SBI_ENGINES set MAIN_URL='/knowagewhatifengine/restful-services/olap/startolap' where LABEL = 'knowageolapengine';
update SBI_ENGINES set MAIN_URL='/knowagewhatifengine/restful-services/olap/startwhatif' where LABEL = 'knowagewhatifengine';

-- 06.09.2016 Chiara Capobianco: add unique name in sbi_kpi_kpi
ALTER TABLE  SBI_KPI_KPI ADD UNIQUE (NAME, VERSION, ORGANIZATION);

--14.09.2016 Alessandro Piovani: add url, remote columns to SBI_CATALOG_FUNCTIONS and make SCRIPT field nullable

ALTER TABLE SBI_CATALOG_FUNCTION ADD REMOTE SMALLINT DEFAULT 0;
ALTER TABLE SBI_CATALOG_FUNCTION ADD URL VARCHAR2(100);
ALTER TABLE SBI_CATALOG_FUNCTION CHANGE COLUMN `SCRIPT` `SCRIPT` TEXT NULL;

ALTER TABLE SBI_OUTPUT_PARAMETER ADD COLUMN IS_USER_DEFINED SMALLINT DEFAULT 0;

-- 13.12.2016 Ana Tomic added table sbi_accessibility preferences
CREATE TABLE SBI_ACCESSIBILITY_PREFERENCES (
	 ID INTEGER NOT NULL,
	 USER_ID INTEGER NOT NULL,
	 ENABLE_UIO NUMBER(38,0) NOT NULL,
	 ENABLE_RROBOBRAILLE NUMBER(38,0) DEFAULT NULL,
	 ENABLE_GRAPH_SONIFICATION NUMBER(38,0) DEFAULT NULL,
	 ENABLE_VOICE NUMBER(38,0) DEFAULT NULL,
	 PREFERENCES CLOB NULL,
     USER_IN VARCHAR2(100) NOT NULL,
     USER_UP VARCHAR2(100),
     USER_DE VARCHAR2(100),
	 TIME_IN TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	 TIME_UP TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	 TIME_DE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     SBI_VERSION_IN VARCHAR2(10),
     SBI_VERSION_UP VARCHAR2(10),
     SBI_VERSION_DE VARCHAR2(10),
     ORGANIZATION VARCHAR2(20),
	 PRIMARY KEY (ID)
);

CREATE INDEX IDX_SBI_ACCESSIB_PREF_SBI_USER 		ON SBI_ACCESSIBILITY_PREFERENCES (USER_ID);
ALTER TABLE SBI_ACCESSIBILITY_PREFERENCES ADD CONSTRAINT FK_SBI_ACCESSIB_PREF_SBI_USER FOREIGN KEY (USER_ID) REFERENCES SBI_USER (ID) ON DELETE CASCADE;

ALTER TABLE SBI_SNAPSHOTS ADD SCHEDULATION VARCHAR2(100);
ALTER TABLE SBI_SNAPSHOTS ADD SCHEDULER VARCHAR2(100);
ALTER TABLE SBI_SNAPSHOTS ADD SCHEDULATION_START INTEGER;

ALTER TABLE SBI_IMAGES DROP CONSTRAINT NAME_UNIQUE;
ALTER TABLE SBI_IMAGES ADD CONSTRAINT NAME_UNIQUE UNIQUE (NAME, ORGANIZATION);

ALTER TABLE SBI_KPI_TARGET ADD CONSTRAINT XAK1SBI_KPI_TARGET UNIQUE (NAME, ORGANIZATION);

-- 02.03.2017 Dragan Pirkovic 
-- changed path for chart document execution

UPDATE SBI_ENGINES SET MAIN_URL = '/knowagecockpitengine/api/1.0/chart/pages/execute' WHERE LABEL = 'knowagechartengine';
ALTER TABLE SBI_CROSS_NAVIGATION ADD COLUMN TYPE INTEGER NOT NULL;