DROP TABLE IF EXISTS camper_family;
DROP TABLE IF EXISTS dorm;
DROP TABLE IF EXISTS camper;
DROP TABLE IF EXISTS family_contact;


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
        dorm_name varchar(30) not null,
        camper_id int not null,       
        
        CONSTRAINT fk_camper_id FOREIGN KEY (camper_id) REFERENCES camper(camper_id)
 );       
        