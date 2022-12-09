CREATE DATABASE IF NOT EXISTS sample;

CREATE TABLE facilitator(
    id int NOT NULL AUTO_INCREMENT,
    name char(16) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE class_room (
    id int NOT NULL AUTO_INCREMENT,
    name char(16) NOT NULL,
    facilitator_id int NOT NULL,
    PRIMARY KEY (id),
    INDEX faci_ind (facilitator_id),
    FOREIGN KEY (facilitator_id)
        REFERENCES facilitator (id)
        ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE student (
    id int NOT NULL AUTO_INCREMENT,
    name char(16) NOT NULL,
    login_id char(20) NOT NULL,
    class_room_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (class_room_id)
        REFERENCES class_room (id)
        ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO facilitator(name) VALUES('Reginald Birsong');
INSERT INTO facilitator(name) VALUES('Hugh Chung');
INSERT INTO facilitator(name) VALUES('Graham Quinnett');
INSERT INTO facilitator(name) VALUES('Godfrey Murrills');
INSERT INTO facilitator(name) VALUES('Bernard McQueen');

INSERT INTO class_room(name, facilitator_id) VALUES('spcial', 1);
INSERT INTO class_room(name, facilitator_id) VALUES('nomal', 1);
INSERT INTO class_room(name, facilitator_id) VALUES('sience', 2);

INSERT INTO student(name, login_id, class_room_id) VALUES('Pauly Franklin', 'student_1', 1);
INSERT INTO student(name, login_id, class_room_id) VALUES('Eric Frampton', 'student_2', 1);
INSERT INTO student(name, login_id, class_room_id) VALUES('Adolf Appleby', 'student_3', 1);
INSERT INTO student(name, login_id, class_room_id) VALUES('Ambrose Dave', 'student_4', 2);
INSERT INTO student(name, login_id, class_room_id) VALUES('Enos Derick', 'student_5', 2);
INSERT INTO student(name, login_id, class_room_id) VALUES('Roly Sibley', 'student_6', 3);
INSERT INTO student(name, login_id, class_room_id) VALUES('Derick Arden', 'student_7', 3);
