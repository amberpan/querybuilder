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

create table JOIN_SCHEMA(
id INT AUTO_INCREMENT PRIMARY KEY,
source_table VARCHAR(50) NOT NULL,
source_column VARCHAR(50) NOT NULL,
target_table VARCHAR(50) NOT NULL,
target_column VARCHAR(50) NOT NULL,
join_type VARCHAR(50) NOT NULL,
join_precedence INT NULL
);