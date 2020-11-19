DROP
    SCHEMA IF EXISTS tradeshift;
CREATE DATABASE tradeshift;
SET
    default_storage_engine = INNODB;

use tradeshift;
CREATE TABLE node (
                      id int(10) unsigned NOT NULL AUTO_INCREMENT,
                      description varchar(255) NOT NULL,
                      parent int(10) unsigned DEFAULT NULL,
                      PRIMARY KEY (id),
                      FOREIGN KEY (parent) REFERENCES node (id)
                          ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO node(description,parent)
VALUES('Electronics',NULL);

INSERT INTO node(description,parent)
VALUES('Laptops & PC',1);

INSERT INTO node(description,parent)
VALUES('Laptops',2);
INSERT INTO node(description,parent)
VALUES('PC',2);

INSERT INTO node(description,parent)
VALUES('Cameras & photo',1);
INSERT INTO node(description,parent)
VALUES('Camera',5);

INSERT INTO node(description,parent)
VALUES('Phones & Accessories',1);
INSERT INTO node(description,parent)
VALUES('Smartphones',7);

INSERT INTO node(description,parent)
VALUES('Android',8);
INSERT INTO node(description,parent)
VALUES('iOS',8);
INSERT INTO node(description,parent)
VALUES('Other Smartphones',8);

INSERT INTO node(description,parent)
VALUES('Batteries',7);
INSERT INTO node(description,parent)
VALUES('Headsets',7);
INSERT INTO node(description,parent)
VALUES('Screen Protectors',7);
