
insert into Currency values (1,  'AUD', 2);
insert into Currency values (2,  'CAD', 2);
insert into Currency values (3,  'CNY', 2);
insert into Currency values (4,  'CZK', 2);
insert into Currency values (5,  'DKK', 2);
insert into Currency values (6,  'EUR', 2);
insert into Currency values (7,  'GBP', 2);
insert into Currency values (8,  'JPY', 0);
insert into Currency values (9,  'NOK', 2);
insert into Currency values (10, 'NZD', 2);
insert into Currency values (11, 'USD', 2);

insert into ExchangeRate (id, baseCurrency_id, termCurrency_id, rate) values (1, 1, 11, 0.8371);
insert into ExchangeRate (id, baseCurrency_id, termCurrency_id, rate) values (2, 2, 11, 0.8711);
insert into ExchangeRate (id, baseCurrency_id, termCurrency_id, rate) values (3, 11, 3, 6.1715);
insert into ExchangeRate (id, baseCurrency_id, termCurrency_id, rate) values (4, 6, 11, 1.2315);
insert into ExchangeRate (id, baseCurrency_id, termCurrency_id, rate) values (5, 7, 11, 1.5683);
insert into ExchangeRate (id, baseCurrency_id, termCurrency_id, rate) values (6, 10, 11, 0.7750);
insert into ExchangeRate (id, baseCurrency_id, termCurrency_id, rate) values (7, 11, 8, 119.95);
insert into ExchangeRate (id, baseCurrency_id, termCurrency_id, rate) values (8, 6, 4, 27.6028);
insert into ExchangeRate (id, baseCurrency_id, termCurrency_id, rate) values (9, 6, 5, 7.4405);
insert into ExchangeRate (id, baseCurrency_id, termCurrency_id, rate) values (10, 6, 9, 8.6651);