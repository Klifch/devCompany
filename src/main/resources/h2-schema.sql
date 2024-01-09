-- Drop foreign key constraints first
SET REFERENTIAL_INTEGRITY FALSE;

-- Drop tables
DROP TABLE IF EXISTS Developer_Project;
DROP TABLE IF EXISTS Office_Project;
DROP TABLE IF EXISTS Developer;
DROP TABLE IF EXISTS Project;
DROP TABLE IF EXISTS Office;

-- Enable foreign key constraints again
SET REFERENTIAL_INTEGRITY TRUE;


CREATE TABLE Developer (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           first_name VARCHAR(255) NOT NULL,
                           last_name VARCHAR(255) NOT NULL,
                           age INT,
                           salary DOUBLE,
                           hire_date DATE,
                           position VARCHAR(50)
);

CREATE TABLE Project (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(255) NOT NULL,
                         budget DOUBLE
);

CREATE TABLE Office (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(255) NOT NULL,
                        address VARCHAR(255)
);

CREATE TABLE Developer_Project (
                                   developer_id INT,
                                   project_id INT,
                                   FOREIGN KEY (developer_id) REFERENCES Developer(id),
                                   FOREIGN KEY (project_id) REFERENCES Project(id),
                                   PRIMARY KEY (developer_id, project_id)
);

CREATE TABLE Office_Project (
                                office_id INT,
                                project_id INT,
                                FOREIGN KEY (office_id) REFERENCES Office(id),
                                FOREIGN KEY (project_id) REFERENCES Project(id),
                                PRIMARY KEY (office_id, project_id)
);

