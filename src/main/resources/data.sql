INSERT INTO Organization (id, version, name, full_name, kpp, address, inn)
VALUES (1, 0, 'Сбербанк', 'Сбербанк', '100000', 'ул.Цюрупы, 16', '1111111');

INSERT INTO Organization (id, version, name, full_name, kpp, address, inn)
VALUES (2, 0, 'Чебурашка', 'Компания чебурашек', '120000', 'ул.Цюрупы, 17', '1211111');

INSERT INTO Office (id, version, name, address, organization_id)
VALUES (1, 0, 'Сбер офис 1', 'ул.Цюрупы, 18', 1);

INSERT INTO Office (id, version, name, address, organization_id)
VALUES (2, 0, 'Сбер офис 2', 'ул.Цюрупы, 19', 1);

INSERT INTO Office (id, version, name, address, organization_id)
VALUES (3, 0, 'Чебурашка офис 1', 'ул.Цюрупы, 20', 2);

INSERT INTO Office (id, version, name, address, organization_id)
VALUES (4, 0, 'Чебурашка офис 2', 'ул.Цюрупы, 21', 2);

INSERT INTO Country (id, version, name, code)
VALUES (1, 0, 'Россия', '000');

INSERT INTO Country (id, version, name, code)
VALUES (2, 0, 'Казахстан', '001');

INSERT INTO Document_Type (id, version, name, code)
VALUES (1, 0, 'Паспорт', '00');

INSERT INTO Document_Type (id, version, name, code)
VALUES (2, 0, 'Удостоверение', '01');

INSERT INTO Position_ (id, version, name)
VALUES (1, 0, 'Сотрудник');

INSERT INTO Position_ (id, version, name)
VALUES (2, 0, 'Секретарь');

INSERT INTO User_ (id, version, office_id, first_name)
VALUES (1, 0, 1, 'Ivan');

INSERT INTO User_ (id, version, office_id, first_name)
VALUES (2, 0, 2, 'Artem');

INSERT INTO User_Position (user_id, position_id)
VALUES (1, 1);

INSERT INTO User_Position (user_id, position_id)
VALUES (2, 2);

INSERT INTO Document (user_id, version)
VALUES (1, 0);

INSERT INTO Document (user_id, version)
VALUES (2, 0);


