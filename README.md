## TODOs
1. Add query join support

##### Sample Request
````
{
     "requestMetadata": {
         "description": "Simple query to test app"
     },
     "filterCriteria": [
         {
             "filterName": "EntityID",
             "operator": "EQUALS",
             "values": [
                 "1"
             ]
         },
         {
             "filterName": "TradeDate",
             "operator": "LT",
             "values": [
                 "2020-08-25"
             ]
         },
         {
             "filterName": "Price",
             "operator": "BETWEEN",
             "values": [
                 "120",
                 "150"
             ]
         },
         {
             "filterName": "Quantity",
             "operator": "LTE",
             "values": [
                 "500"
             ]
         },
         {
             "filterName": "Side",
             "operator": "NOT_IN",
             "values": [
                 "3",
                 "4"
                 
             ]
         }
     ],
     "responseSchema": []
 }
````
 
 ##### Sample response
 ````select * from [OPM_R_10].[dbo].report_index where (ENTITY_ID = '1' and TRADE_DATE < '2020-08-25' and SIDE not in ('3', '4') and PRICE between 120 and 150 and TRADE_QUANTITY <= 500)````
 
 ##### Column schema
 ![Column Schema](src/main/resources/docs/column_schema.PNG?raw=true "Column Schema")