-- noinspection SqlNoDataSourceInspectionForFile

-- Developer
INSERT INTO Developer (first_name, last_name, age, salary, hire_date, position) VALUES
                                                                                    ('Ben', 'Brown', 20, 2500, '2030-03-02', 'SENIOR'),
                                                                                    ('Oleksii', 'Demydenko', 22, 5500, '2027-10-09', 'JUNIOR'),
                                                                                    ('Anna', 'Smith', 22, 2700, '2023-10-12', 'LEAD'),
                                                                                    ('Leo', 'Potters', 19, 2500, '2024-07-10', 'JUNIOR');

-- Project
INSERT INTO Project (name, budget) VALUES
                                       ('Spring2', 100500),
                                       ('javaCrab', 100000),
                                       ('java40', 13330),
                                       ('python4', 1000);

-- Office
INSERT INTO Office (name, address) VALUES
                                                 ('BrOffice', 'Long ave. 14'),
                                                 ('Groen', 'Nationalestraat 5');

-- Developer_Project relationships
INSERT INTO Developer_Project (developer_id, project_id) VALUES
                                                             (2, 2),
                                                             (2, 4),
                                                             (4, 2),
                                                             (4, 4),
                                                             (4, 3),
                                                             (3, 1);

-- Office_Project relationships
INSERT INTO Office_Project (office_id, project_id) VALUES
                                                       (1, 1),
                                                       (1, 3),
                                                       (2, 4),
                                                       (2, 2);