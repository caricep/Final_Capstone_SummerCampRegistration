BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP TABLE IF EXISTS camper_family;
DROP TABLE IF EXISTS dorm;
DROP TABLE IF EXISTS camper;
DROP TABLE IF EXISTS family_contact;
DROP TABLE IF EXISTS history;
DROP TABLE IF EXISTS update_status;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');


CREATE TABLE family_contact (
        
        family_contact_id serial primary key,
        family_contact_name varchar(60) not null,
        email_address varchar(60) not null,
        phone_number varchar(11) not null
);  

CREATE TABLE camper (

        camper_id serial primary key,
        camper_first_name varchar(30) not null,
        camper_last_name varchar(30) not null,
        date_of_birth date not null,
        street_address varchar(50) not null,
        street_address_line_two varchar(20),
        city varchar(40) not null,
        state_code varchar(2) not null,
        zip_code varchar(15) not null,
        payment_status boolean not null,
        family_contact_id int not null,
        additional_camper_notes varchar(1000),
        
        CONSTRAINT fk_family_contact_id FOREIGN KEY (family_contact_id) REFERENCES family_contact(family_contact_id)     
 );

CREATE TABLE camper_family (
        
        camper_id int not null,
        family_contact_id int not null,
        
        PRIMARY KEY (camper_id, family_contact_id),
        
        CONSTRAINT fk_camper_family_camper_id FOREIGN KEY (camper_id) REFERENCES camper(camper_id),
        CONSTRAINT fk_camper_family_family_contact_id FOREIGN KEY (family_contact_id) REFERENCES family_contact(family_contact_id)
);
 
CREATE TABLE dorm (
 
        dorm_id serial primary key,
        dorm_name varchar(30) not null
 );

INSERT INTO dorm (dorm_id, dorm_name) VALUES (DEFAULT, 'Aquaotters');
INSERT INTO dorm (dorm_id, dorm_name) VALUES (DEFAULT, 'Astronotters');    

CREATE TABLE update_status (

        update_status_id int primary key,
        update_status_desc varchar(10)
); 

INSERT INTO update_status (update_status_id, update_status_desc) VALUES (1, 'Pending');
INSERT INTO update_status (update_status_id, update_status_desc) VALUES (2, 'Approved');
INSERT INTO update_status (update_status_id, update_status_desc) VALUES (3, 'Rejected');


CREATE TABLE history (
        history_id serial primary key,
        change_type varchar(255),
        change_specifics varchar(255),
        change_date_and_time varchar(80),
        user_name varchar(255),
        update_status_id int not null,
        
        CONSTRAINT fk_update_status_id FOREIGN KEY (update_status_id) REFERENCES update_status(update_status_id)
);


COMMIT TRANSACTION;
