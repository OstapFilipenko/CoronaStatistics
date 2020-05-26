Create VIEW max_infec_country AS
    SELECT TOP (10) WITH Records.Country, Records.Province, Records.Infections FROM Records
        ORDER by Records.Infections asc

Create VIEW min_infec_country AS
    SELECT TOP (10) WITH Records.Country, Records.Province, Records.Infections FROM Records
        ORDER by Records.Infections desc

CREATE View lowest_Increace AS
    SELECT TOP (10) WITH Records.Country, Records.Province, Records.Infections FROM Records
        WHERE MIN((SELECT Infections FROM Records WHERE DateV IN (SELECT max(DateV) FROM Records)) > (SELECT Infections FROM Records WHERE DateV IN (SELECT max(DateV-1) FROM Records)) )