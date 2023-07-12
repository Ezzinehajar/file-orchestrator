--liquibase formatted sql

--changeset hezzine:add_issuer-syntactic-data context:dev

INSERT INTO Issuer (isin, name, date_added)
VALUES ('AN8068571086', 'Schlumberger NV', '2021-01-01'),
       ('CA4457371090', 'Hunter Technology Corp', '2021-02-01'),
       ('AT0000625504', 'OBERBANK AG-VORZ', '2021-01-05'),
       ('ARBCOM4600U8', 'Sol Gel Technologies Ltd', '2021-04-01');


