insert into DATA_DICTIONARY(column_alias,column_name,data_type,schema_name,table_name,database_name,filter_precedence) values('EntityID','ENTITY_ID','STRING','dbo','report_index','OPM_R_10', 1);
insert into DATA_DICTIONARY(column_alias,column_name,data_type,schema_name,table_name,database_name,filter_precedence) values('TradeDate','TRADE_DATE','DATE','dbo','report_index','OPM_R_10', 2);
insert into DATA_DICTIONARY(column_alias,column_name,data_type,schema_name,table_name,database_name,filter_precedence) values('Side','SIDE','STRING','dbo','report_index','OPM_R_10', 3);
insert into DATA_DICTIONARY(column_alias,column_name,data_type,schema_name,table_name,database_name,filter_precedence) values('Price','PRICE','DECIMAL','dbo','report_index','OPM_R_10', 4);
insert into DATA_DICTIONARY(column_alias,column_name,data_type,schema_name,table_name,database_name,filter_precedence) values('Quantity','TRADE_QUANTITY','INTEGER','dbo','report_index','OPM_R_10', 5);
insert into DATA_DICTIONARY(column_alias,column_name,data_type,schema_name,table_name,database_name,filter_precedence) values('TransactTime','TRANSACT_TIME','Timestamp','dbo','report_index','OPM_R_10', 6);
insert into DATA_DICTIONARY(column_alias,column_name,data_type,schema_name,table_name,database_name,filter_precedence) values('IDN_RPT_IDX','IDN_RPT_IDX','String','dbo','report_index','OPM_R_10', 6);
insert into DATA_DICTIONARY(column_alias,column_name,data_type,schema_name,table_name,database_name,filter_precedence) values('IDN_ENTY','IDN_ENTY','String','dbo','Entity','OPM_O_10', 6);
insert into DATA_DICTIONARY(column_alias,column_name,data_type,schema_name,table_name,database_name,filter_precedence) values('DT2_TRD_DT','IDN_ENTY','String','dbo','report_index','OPM_R_10', 6);
insert into DATA_DICTIONARY(column_alias,column_name,data_type,schema_name,table_name,database_name,filter_precedence) values('TRD_DT','IDN_ENTY','String','dbo','Entity','OPM_O_10', 6);




insert into JOIN_SCHEMA(source_table,source_column,target_table,target_column,join_type,join_precedence) values
('report_index','IDN_RPT_IDX','Entity','IDN_ENTY','LEFT_OUTER_JOIN', 1),
('report_index','DT2_TRD_DT','Entity','TRD_DT','LEFT_OUTER_JOIN', 2);