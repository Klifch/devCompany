DROP TABLE IF EXISTS developer CASCADE;
CREATE TABLE developer (
                           id SERIAL PRIMARY KEY,
                           first_name VARCHAR(255) NOT NULL,
                           last_name VARCHAR(255) NOT NULL,
                           age INT,
                           salary DOUBLE PRECISION,
                           hire_date DATE,
                           position VARCHAR(50)
);

DROP TABLE IF EXISTS project CASCADE;
CREATE TABLE project (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         budget DOUBLE PRECISION
);

DROP TABLE IF EXISTS office CASCADE;
CREATE TABLE office (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        address VARCHAR(255),
                        capacity INT
);

DROP TABLE IF EXISTS developer_project CASCADE ;
CREATE TABLE developer_project (
                                   developer_id INT,
                                   project_id INT,
                                   FOREIGN KEY (developer_id) REFERENCES Developer(id),
                                   FOREIGN KEY (project_id) REFERENCES Project(id),
                                   PRIMARY KEY (developer_id, project_id)
);

DROP TABLE IF EXISTS office_project CASCADE ;
CREATE TABLE office_project (
                                office_id INT,
                                project_id INT,
                                FOREIGN KEY (office_id) REFERENCES Office(id),
                                FOREIGN KEY (project_id) REFERENCES Project(id),
                                PRIMARY KEY (office_id, project_id)
);
