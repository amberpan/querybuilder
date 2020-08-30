create table DATA_DICTIONARY(
id INT AUTO_INCREMENT PRIMARY KEY,
column_alias VARCHAR(50) NOT NULL,
column_name VARCHAR(50) NOT NULL,
data_type VARCHAR(50) NOT NULL,
schema_name VARCHAR(50) NOT NULL,
table_name VARCHAR(50) NOT NULL,
database_name VARCHAR(50) NOT NULL,
filter_precedence INT NULL
);