DROP TABLE IF EXISTS categories CASCADE;

CREATE TABLE categories (
  id  INTEGER UNIQUE,
  description VARCHAR(255) NOT NULL,
   mcccode INTEGER);

DROP TABLE IF EXISTS subcategories;

 CREATE TABLE subcategories (
  id  INTEGER,
  description VARCHAR(255) NOT NULL,
  categorieid  INTEGER,
  merchantcategorie INTEGER NOT NULL,
  FOREIGN KEY (categorieid) REFERENCES categories(id));

DROP TABLE IF EXISTS panverification;

CREATE TABLE panverification(
Id INTEGER  PRIMARY KEY,
  panverificationid VARCHAR(255)
);

DROP TABLE IF EXISTS bankverification;

CREATE TABLE bankverification(
Id INTEGER  PRIMARY KEY,
bankverificationid VARCHAR(255)
);

DROP TABLE IF EXISTS gstinverification;

CREATE TABLE gstinverification(
Id INTEGER PRIMARY KEY,
gstinverificationid VARCHAR(255)
);

DROP TABLE IF EXISTS aadharverification;

CREATE TABLE aadharverification(
Id INTEGER PRIMARY KEY,
aadharverificationid VARCHAR(255)
);



