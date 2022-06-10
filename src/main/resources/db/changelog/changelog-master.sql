--liquibase formatted sql
--changeset Daniel_Farias:1 splitStatements:true endDelimiter:;
CREATE TABLE availability (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(255) NULL,
  PRIMARY KEY (id)
);

CREATE TABLE candidate (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE exam (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  candidate_id int DEFAULT NULL,
  room_id int DEFAULT NULL,
  availability_id int DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE room (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

create table room_availability (
    room_id integer not null,
    availability_id integer not null,
    PRIMARY KEY(room_id, availability_id)
);
alter table room_availability add constraint room_availability_id_ava foreign key (availability_id) references availability (id);
alter table room_availability add constraint room_availability_id_row foreign key (room_id) references room (id);
alter table exam add constraint exam_candidate_fk foreign key (candidate_id) references candidate (id);
alter table exam add constraint exam_room_fk foreign key (room_id) references room (id);


--changeset Daniel_Farias:2 splitStatements:true endDelimiter:;
INSERT INTO availability(id, name)  VALUES (1, 'Desk 1');
INSERT INTO availability(id, name)  VALUES (2, 'Desk 2');
INSERT INTO availability(id, name)  VALUES (3, 'Desk 3');

INSERT INTO room(id, name) VALUES (1,'Room 1');
INSERT INTO room(id, name) VALUES (2,'Room 2');
INSERT INTO room(id, name) VALUES (3,'Room 3');

--changeset Daniel_Farias:3 splitStatements:true endDelimiter:;
INSERT INTO room_availability(room_id, availability_id) VALUES (1,1),(1,2),(1,3);
INSERT INTO room_availability(room_id, availability_id) VALUES (2,1);

--changeset Daniel_Farias:4 splitStatements:true endDelimiter:;
INSERT INTO candidate(id, name) VALUES (1,'Daniel Farias da Silva');
