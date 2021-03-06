create schema renttrack; // we can drop schema renttrack;
create database renttrack;

create table users (                                                                id SERIAL primary key,                                                          username varchar(10) not null unique,                                           full_name varchar(70),                                                          role varchar(30),                                                               inactive char(1)                                                                );

;; old name
create table owners(                                                                id serial primary key,                                                          name varchar(70) not null,                                                      address varchar(70),                                                            city  varchar(70),                                                              state varchar(2),                                                               zip   varchar(12),                                                              notes varchar(500),                                                             email varchar(70),                                                              index(name)                                                                     );

  phone_types ('Home', 'Work', 'Cell','Emergency');

 
 create table owner_phones(                                                          id serial primary key,                                                          owner_id integer,                                                               phone_num varchar(30) not null,                                                 type varchar(10) not null,                                                      foreign key(owner_id) references owners(id)                                     );

 create table property_types(                                                      id serial primary key,                                                          name varchar(30) not null                                                       );

	insert into property_types (name) values('House'),('Apartment'),('Condo'),('Mobile'),('Rooming House');

create table building_types(                                                      id serial  primary key,                                                         name varchar(30) not null                                                       );

insert into building_types (name) values('Single-Family'),('Multi-Family');
;;
;; previously prop_status 
;;
 create table rental_status(                                                      id serial primary key,                                                          alias varchar(5),                                                              name varchar(30)                                                                )
 insert into rental_status (alias,name) values('O','Owner Occupied'),('R','Registered'),('V','Vacant'),('C','Commercial'),('D','Drive By');


create table zonings(                                                            id serial primary key,                                                          alias varchar(15),                                                              name varchar(70)                                                                );
;;
;; old data from zonning_2007
;;
 insert into zonings (alias,name) values                                          ('BP','Business Park'),                                                         ('CA','Arterial Commercial'),                                                   ('CD','Downtown Commercial'),                                                   ('CG','General Commercial'),                                                    ('CL','Limited Commercial'),                                                    ('IN','Institutional'),                                                          ('IG','General Industrial'),                                                    ('MD','Medical'),                                                               ('MH','Manufactured Home'),                                                     ('PUD','Planned Unit Development'),                                             ('QY','Quarry'),                                                                ('RC','Residential Core'),                                                     ('RE','Residential Estate'),                                                    ('RH','Residential High Density'),                                              ('RM','Residential Multi-Family'),                                              ('RS','Residential Single-Family');
  //
 // some of the fields should go to buildings and units
 // such as building_type_id, bathrooms
 // old name registr
//  old name property_status for status_id 

create table rentals(                                                              id SERIAL primary key,                                                          status_id integer,                                                              agent_id    integer,	                                                           registered_date   DATE,                                                         last_cycle_date   DATE,                                                         permit_issued     DATE,                                                         permit_expires    DATE,                                                         permit_length     smallint,                                                     grandfathered     char(1),                                                      annexed           char(1),                                                      cdbg_funding      char(1),                                                      zoning_id         integer,	                                                     n_hood            smallint,                                                     affordable        char(1),                                                      inactive          char(1),                                                      foreign key(status_id) references rental_status(id),                            foreign key(agent_id) references owners(id),                                    foreign key(zoning_id) references zonings(id)                                 );
			
create table rental_structures(                                                    id serial primary key,                                                          rental_id integer,                                                              identifier varchar(30),                                                         building_type_id integer,                                                       prop_type_id integer,                                                           foundation varchar(20),                                                         story_cnt smallint,                                                             heat_source varchar(30),                                                        egress_height int,                                                              egress_width int,                                                               egress_sill_height  int,                                                        egress_area  double precision,                                                  year_built   smallint,                                                          egress_decree_years varchar(32),                                                egress_area2 double precision,                                                  foreign key(rental_id) references rentals(id),                                  foreign key(building_type_id) references building_types(id),                    foreign key(prop_type_id) references property_types(id)       		                );

;;
;; units need to be split into multiple units with identifier or address
;; we added identifier (subunit address) and remove units column
;; units column removed
;;
 create table rental_units(                                                        id serial primary key,                                                          old_id integer,                                                                 structure_id integer,                                                           identifier varchar(30),		                                                     bedrooms smallint,                                                              occup_load smallint,                                                            sleep_room  char(1),                                                            bathrooms  smallint,                                                            half_bath  smallint,                                                            uninspected char(1),                                                            accessory_dwelling char(1),                                                     notes   varchar(500),                                                           address_id int,                                                                 attic_access char(1),                                                           efficiency char(1),                                                             foreign key(address_id) references addresses(id),                               foreign key(structure_id) references rental_structures(id)		                  );

 // room_types
 // type: kitchen, living room, bedroom
 create table unit_rooms(                                                          id serial primary key,                                                          unit_id integer,                                                                identifier varchar(30),		                                                     type varchar(30),                                                               measures varchar(30),                                                           foreign key(unit_id) references rental_units(id)                               ); 
 
;; 
;; in the following rental_id is redundant
;; address should be linked to a unit
create table addresses(                                                           id serial primary key,                                                          street_address varchar(70) not null,                                            city  varchar(30),                                                              rental_id         int,                                                          ma_street_id int,                                                               ma_subunit_id        int,                                                       invalid           char(1),                                                      longitude  double precision,                                                    latitude double precision,                                                      state varchar(2),                                                               zip   varchar(10),                                                              foreign key(rental_id) references rentals(id)                                   );

 create index on addresses (street_address);
 
create table addresses(                                                           id serial primary key,                                                          street_address varchar(70) not null,                                            street_num varchar(10),                                                         street_dir char(1),                                                             street_name varchar(50),                                                        street_type varchar(10),                                                        post_dir char(1),                                                               subunit_type varchar(10),                                                       subunit_identifier varchar(20),                                                 city  varchar(30),                                                              rental_id         int,                                                          ma_street_id int,                                                               ma_subunit_id        int,                                                       invalid    char(1),                                                             longitude numeric(12,7),                                                        latitude numeric(12,7),                                                         ma_location_id int,                                                             foreign key(rental_id) references rentals(id)                                   );

 create index on addresses (street_address);

;;
;; we need these for search only
;;
 create table street_types(                                                        id varchar(10) not null unique primary key,                                     name varchar(30) not null unique
  );


<option value="APT">Apartment
<option value="BSMT">Basement
<option value="BLDG">Building
<option value="FL">Floor
<option value="LOT">Lot
<option value="LOWR">Lower
<option value="RM">Room
<option value="SPC">Space
<option value="STE">Suite
<option value="TRLR">Trailer
<option value="UNIT">Unit
<option value="UPPR">Upper	

 create table subunit_types(                                                       id varchar(10) not null unique primary key,                                     name varchar(30) not null unique
  );
	insert into subunit_types values('Apt','Apartment'),('Bsmt','Basement'),('Bldg','Building'),('Fl','Floor'),('Lot','Lot'),('Lowr','Lower'),('Rm','Room'),('Spc','Space'),('Ste','Suite'),('Trlr','Trailer'),('Unit','Unit'),('Uppr','Upper');

	
	insert into street_types values('Ave','Avenue'),('Bnd','Bend'),('Blvd','Boulevard'),('BOW','Bow'),('Ctr','Center'),('Cir','Circle'),('Ct','Court'),('Crst','Crest'),('Dr','Drive'),('Expy','Expressway'),('Knl','Knoll',('Ln','Lane'),('Pike','Pike'),('Pkwy','Parkway'),('Pl','Place'),('Rd','Road'),('Rdg','Ridge'),('Run','Run'),('Sq','Square'),('St','Street'),('Ter','Terrace'),('Trl','Trail'),('Tpke','Turnpike'),('Turn','Turn'),('Vly','Valley'),('Way','Way'); 



 create table rental_owners(                                                        id serial primary key,                                                          rental_id integer,                                                              owner_id integer,                                                               foreign key(rental_id) references rentals(id),                                  foreign key(owner_id) references owners(id)                                     );

 create table inspection_types(                                                    id serial primary key,                                                          alias varchar(4),                                                               name varchar(30) not null                                                       );
	 
	 insert into inspection_types (alias, name) values('CYCL','Cycle'),('REIN','Remaining Violation'),('COMP','Complaint'),('FIRE','Fire'),('TV','Tenant Violation'),('EE','Exterior Extension Reminder'),('HOME','Home Inspection'),('PRMT','Permit Upgrade'),('SHTR','Shelter Plus Care');

	 
 create table pull_reasons(                                                         id serial primary key,                                                          alias varchar(8),                                                               reason varchar(30) not null                                                   );
		

		insert into pull_reasons (alias,reason) values('RI','Re-Inspects'),             ('E2','EE 2 mth Notice'),                                                       ('LG','In Legal'),                                                              ('1S','1-st Notice to Schedule'),                                               ('2S','2-nd Notice to Schedule'),                                               ('1R','1-st Notice to register'),                                               ('2R','2-nd Notice to register'),                                               ('1L','Cycle'),                                                                 ('2L','60 day notice'),                                                         ('EE','Exterior Extension'),                                                    ('SL','Start Legal'),                                                           ('TV','Tenant Violation'),                                                      ('CO','Complaint'),                                                             ('DB','Drive By'),                                                              ('1N','1-st NTRNSC'),                                                           ('2N','2-nd NTRNSC'),                                                           ('O','Other'),                                                                  ('BS','Billing Statement'),                                                     ('OO','Over Occupancy'),                                                        ('F','Fire'),                                                                   ('RV','Remaining Violation'),                                                   ('AP','Appeal'),                                                                ('MC','Municipal Code Violation'),                                              ('HO','Home'),                                                                  ('DL','Delinquent Bill'),                                                       ('RC','Recertification'),                                                       ('BD','BHQA Deadline'),                                                         ('PR','Plan Review'),                                                           ('IP','In Planning'),                                                           ('FU','Followup');
		
  create table pull_history(                                                        id serial primary key,                                                          rental_id int,                                                                  date date,                                                                      pull_reason_id int,                                                             pull_by_id int,                                                                 completed char(1),                                                              completed_date date,                                                            foreign key(rental_id) references rentals(id),                                  foreign key(pull_reason_id) references pull_reasons(id),                        foreign key(pull_by_id) references users(id)                                    );		

 foundation_types ('Slab', 'Crawl Space', 'Basement','Cellar','Other');
 heat_source_types ('Gas','Electric','Other');
 time_status_types ('In Progress','Completed');
 
 create table inspections(                                                          id serial,                                                                      rental_id int,                                                                  inspection_date date,                                                           inspection_type_id int,                                                         compliance_date date,                                                                                                                                           violations smallint,                                                            inspected_by int,                                                               inspect_file varchar(160),                                                      comments     varchar(500),                                                      foundation   varchar(20),                                                                                                                                       attic_access char(1),                                                           accessory varchar(30),                                                          story_cnt    smallint,                                                          heat_source  varchar(15),                                                       smoke_detectors smallint,                                                                                                                                       life_safety smallint,                                                           time_spent decimal(6,2),                                                        time_status varchar(20),                                                        cancelled char(1),                                                              approved char(1),                                                               approved_by int,                                                                approved_date date,                                                             primary key(id),                                                                foreign key(rental_id) references rentals(id),                                  foreign key(inspected_by) references users(id)                                  );

 //
 // track the inspection files number and uploads
 // did not add foreign key for rental_id or inspection_id to avoid
 // hibernate slow loading foreign objects
 //
 create table inspection_file_logs(                                                id serial,                                                                      rental_id int,                                                                  inspection_id int,                                                              inspect_file varchar(160),                                                      date date,                                                                      user_id int,                                                                    primary key(id),                                                                foreign key(user_id) references users(id)                                      );

 insert into inspection_file_logs (id,rental_id,inspection_id,inspect_file,date,user_id) (select id,rental_id,id,inspect_file,inspection_date,inspected_by from inspections where inspect_file is not null);

 create table inspection_templates(                                                 id serial,                                                                      rental_id int,                                                                  building_cnt int not null default 1,                                            date date,                                                                      user_id int,                                                                    primary key(id),                                                                foreign key(rental_id) references rentals(id)                                   );

 create table template_components(                                                     id serial,                                                                      template_id int not null,                                                       building_num int default 0,                                                     unit_num int default 0,                                                         floor_num int default 0,                                                        component varchar(80) not null,                                                 primary key(id),                                                                foreign key(template_id) references inspection_templates(id)                   );

 	create table inspection_cans(                                                      id serial,                                                                      inspection_id int,                                                              template_component_id int,                                                      type varchar(10),                                                               title varchar(128),                                                             title2 varchar(128),                                                            item1 varchar(1024),                                                            item2 varchar(1024),                                                            item3 varchar(1024),                                                            item4 varchar(1024),                                                            item5 varchar(512),                                                             item6 varchar(512),                                                             item7 varchar(512),                                                             item8 varchar(512),                                                             primary key(id),                                                                foreign key(inspection_id) references inspections(id),                          foreign key(template_component_id) references template_components(id)           );
 
 pay_status ('Paid','Unpaid'); 

create table rental_bills(                                                         id serial primary key,                                                          rental_id int,                                                                  issue_date date,                                                                due_date  date,                                                                 single_building_rate  decimal(6,2) default 0,                                   multi_building_rate decimal(6,2) default 0,                                     condo_building_rate decimal(6,2) default 0,                                     rooming_building_rate decimal(6,2) default 0,                                   unit_rate decimal(6,2) default 0,                                               bath_rate decimal(6,2) default 0,                                               reinsp_rate decimal(6,2) default 0,                                             noshow_rate decimal(6,2) default 0,                                             bhqa_fine   decimal(6,2) default 0,                                             single_building_cnt  smallint default 0,                                        multi_building_cnt   smallint default 0,                                        condo_building_cnt   smallint default 0,                                        rooming_building_cnt smallint default 0,                                        unit_cnt smallint default 0,                                                    bath_cnt smallint default 0,                                                    noshow_cnt smallint default 0,                                                  reinsp_cnt smallint default 0,                                                  reinsp_dates varchar(80),                                                       noshow_dates varchar(80),                                                       status varchar(15),                                                             appeal char(1),                                                                 appeal_fee decimal(6,2) default 0,                                              credit decimal(6,2) default 0,                                                  summary_rate decimal(6,2) default 0,                                            idl_rate decimal(6,2) default 0,                                                summary_flag char(1),                                                           idl_flag char(1),                                                               summary_cnt smallint default 0,                                                 idl_cnt smallint default 0,                                                     foreign key(rental_id) references rentals(id)                                  );
	 
 paid_by_types ('Cash','Check','Money Order','Credit Card');

// id = receipt_no
create table receipts(                                                              id serial primary key,                                                          receipt_no int,                                                                 bill_id int,                                                                    received_sum decimal(7,2),                                                      received_date date,                                                             received_from varchar(70),                                                      paid_by varchar(30),                                                           check_no  varchar(50),                                                          foreign key(bill_id) references rental_bills(id)                                );

//
// old rental_images
// added inspection_id
// date = image_date
// file_name = image_file

create table attachements(                                                         id serial primary key,                                                          rental_id int,                                                                  inspection_id int,                                                              date date,                                                                      file_name varchar(70),                                                          old_file_name varchar(150),                                                     notes varchar(500),                                                             foreign key(rental_id) references rentals(id),                                  foreign key(inspection_id) references inspections(id)                          );

create table attachement_seq(                                                         id serial primary key                                                         );

;; attachement_seq_id_seq is the sequence
;; next value is: select nextval('attachement_seq_id_seq');
;; insert into attachement_seq (id) values(nextval('attachement_seq_id_seq'));
;;
;;
;; date = note_date
;; user_id = userid
;;
create table rental_notes(                                                        id serial primary key,                                                          rental_id int,                                                                  date date,                                                                      notes varchar(500),                                                             user_id int,                                                                    foreign key(rental_id) references rentals(id),                                  foreign key(user_id) references users(id)                                       );

;; for Insert:Start 
create type action_type as enum('Save','Update');

;;
;; old name: rental_updates
;;
;; DoDo (changed)
create table rental_logs(                                                         id serial primary key,                                                          date date,                                                                      action_taken  varchar(10),                                                      user_id int,                                                                    rental_id int,                                                                  status_id integer,                                                              agent_id    integer,	                                                          registered_date   DATE,                                                         permit_issued     DATE,                                                         permit_expires    DATE,                                                         permit_length     smallint,                                                     grandfathered     char(1),                                                      annexed           char(1),                                                      cdbg_funding      char(1),                                                      zoning_id         integer,	                                                    n_hood            smallint,                                                     affordable        char(1),                                                      inactive          char(1),                                                      foreign key(rental_id) references rentals(id),                                  foreign key(user_id) references users(id)                                       );

;;
;; ToDo (Eric Request to have audit to owners records
;; no foreign key as we may delete the record but we keep the log
create table owner_logs(                                                          id serial primary key,                                                          date date,                                                                      action_taken  varchar(10),                                                      user_id int,                                                                    owner_id int,                                                                   name varchar(70) not null,                                                      address varchar(70),                                                            city  varchar(70),                                                              state varchar(2),                                                               zip   varchar(12),                                                              notes varchar(500),                                                             email varchar(70),                                                              foreign key(user_id) references users(id)                                       );

;; id = vid
;; rental_id = id

create table variances(                                                           id serial primary key,                                                          rental_id int,                                                                  date date,                                                                      variance  varchar(4000),                                                        user_id int,                                                                    foreign key(rental_id) references rentals(id),                                  foreign key(user_id) references users(id)                                       );



email_types as enum('General','Expire');
;;
;; not sure if this is needed
;; will keep for now
create table email_logs(                                                           id serial primary key,                                                          date date,                                                                      type varchar(15),                                                               user_id int,                                                                    email_from varchar(80),                                                         foreign key(user_id) references users(id)                                     );

;;
;; date = send_date
;; user_id = userid
;; send_status not needed, the status is either success or failure
;; added error_text will point to failure otherwise success
;;

create table email_detail_logs(                                                     id serial primary key,                                                          email_log_id int,                                                               log_to varchar(70),                                                             log_cc varchar(70),                                                             log_bcc varchar(300),                                                           owners_id varchar(30),                                                          agent_id varchar(30),                                                           rentals_id varchar(50),                                                         error_text varchar(500),                                                        foreign key(email_log_id) references email_logs(id));

;;
;; status not needed, using error_text as a flag
;;
create table legalit_email_logs(                                                    id serial primary key,                                                          rental_id int,                                                                  date date,                                                                      e_from varchar(80),                                                             e_to varchar(80),                                                               e_cc varchar(80),                                                               e_subject varchar(80),                                                          e_msg varchar(1000),                                                            error_text varchar(500),                                                        foreign key(rental_id) references rentals(id)                                   );

  create table cans(                                                                id serial primary key,                                                          title varchar(128),                                                             file_ref  varchar(128),                                                         type varchar(10),                                                               item1 varchar(1024),                                                            item2 varchar(1024),                                                            item3 varchar(1024),                                                            item4 varchar(1024),                                                            item5 varchar(512),                                                             item6 varchar(512),                                                             item7 varchar(512),                                                             item8 varchar(512)                                                              );

	create table inspection_cans(                                                      id serial primary key,                                                          inspection_id int,                                                              type varchar(10),                                                               title varchar(128),                                                             title2 varchar(128),                                                            item1 varchar(1024),                                                            item2 varchar(1024),                                                            item3 varchar(1024),                                                            item4 varchar(1024),                                                            item5 varchar(256),                                                             item6 varchar(256),                                                             item7 varchar(256),                                                             item8 varchar(256),                                                             foreign key(inspection_id) references inspections(id)                           );

	//
	// id = legal_id from legaltrack app
	//
	create table rental_legals(                                                        id int unique,             	                                                   rental_id int,                                                                  date date,                                                                      start_by int,                                                                   foreign key(rental_id) references rentals(id),                                  foreign key(start_by) references users(id)                                      );

		create table egresses(                                                          id serial primary key,      	                                                  start_year int,                                                                 end_year int,                                                                   height int,                                                                     width int,                                                                      sill_height  int,                                                               area  double precision,                                                         area2 double precission,                                                        type varchar(10)                                                                );

		create table standard_fees(                                                       id serial primary key,      	                                                  date date,                                                                      user_id int,                                                                    single_unit_building_rate decimal(6,2),                                         multi_unit_building_rate  decimal(6,2),                                         condo_unit_building_rate decimal(6,2),                                          rooming_building_rate  decimal(6,2),                                            rooming_bath_rate      decimal(6,2),                                            unit_rate             decimal(6,2),                                             reinspection_rate  decimal(6,2),                                                no_show_rate       decimal(6,2),                                                summary_rate       decimal(6,2),                                                idl_rate           decimal(6,2),                                                appeal_fee        decimal(6,2),                                                 foreign key(user_id) references users(id)                                       );

		
;;
;; update sequences to reflect current data
;; alter sequence "name" restart number
;; restart values
;;
 alter sequence owners_id_seq restart 12115; 
 alter sequence owner_phones_id_seq  restart 19928; 
 alter sequence rentals_id_seq restart 10898;
 alter sequence addresses_id_seq restart 9818;
 alter sequence attachements_id_seq restart 18801; 
 alter sequence bills_id_seq restart 26041; 
 alter sequence building_types_id_seq restart 3; 
 alter sequence email_detail_logs_id_seq restart 13331;
 alter sequence property_types_id_seq restart 6;
 alter sequence pull_history_id_seq restart 134746;
 alter sequence pull_reasons_id_seq  restart 31;
 alter sequence receipts_id_seq     restart 21381;
 alter sequence rental_logs_id_seq restart 5303;
 alter sequence rental_notes_id_seq restart 17505; 
 alter sequence rental_owners_id_seq restart 10245; 
 alter sequence rental_status_id_seq restart 6;

 alter sequence rental_structures_id_seq restart 8652;
 alter sequence rental_units_id_seq restart 8652;
 
 alter sequence users_id_seq restart 534;
 alter sequence variances_id_seq restart 2055;
 alter sequence zonings_id_seq restart 41;

 alter sequence rental_structures_id_seq restart 25150;
 alter sequence attachement_seq_id_seq restart 18801;
 alter sequence inspections_id_seq restart 52000;
 alter sequence inspection_file_logs_id_seq restart 52100;
 alter sequence unit_rooms_id_seq restart 100000;
;; //
;; // safer way to reset the sequence
;; //
 select setval(pg_get_serial_sequence('rental_units', 'id'), MAX(id)) FROM rental_units; 

alter table inspections alter column time_status type varchar(15);

 ALTER TABLE products ALTER COLUMN price TYPE numeric(10,2);

 alter table rental_structures add foundation varchar(20);
 alter table rental_structures add story_cnt smallint;
 alter table rental_structures add heat_source varchar(30);

 alter table rental_units add attic_access char(1);

 
;; // to drop a column
 alter table cans drop column file_ref;
 
;; //
;; // need to update rental_structures for the new added columns
;; //
  	update rental_structures set foundation = (select i.foundation from inspections i where i.rental_id=rental_structures.rental_id order by id desc limit 1);
		update rental_structures set story_cnt = (select i.story_cnt from inspections i where i.rental_id=rental_structures.rental_id order by id desc limit 1);
		update rental_structures set heat_source = (select i.heat_source from inspections i where i.rental_id=rental_structures.rental_id order by id desc limit 1);
 
 // update rental_units for attic_access
 //
 update rental_units set attic_access = (select i.attic_access from inspections i,rental_structures s where i.rental_id=s.rental_id and s.id = rental_units.structure_id limit 1) 

 //
 // in production we need to drop the following columns from inspections table
 // foundation, attic_access, story_cnt, heat_source 
 //
 alter table inspections add cancelled char(1);
 alter table inspections add approved char(1);
 alter table inspections add approved_by int;
 alter table inspections add approved_date date;
 
 alter table inspections add foreign key(approved_by) references users(id);
 
 alter table addresses add longitude double precision;
 alter table addresses add latitude double precision; 

 added 5/17/2018
 ===============
 alter table rental_structures add egress_height int;
 alter table rental_structures add egress_width int;
 alter table rental_structures add egress_sill_height int;
 alter table rental_structures add egress_area double precision;
 alter table rental_structures add egress_area2 double precision;
 alter table rental_structures add year_built smallint;
 alter table rental_structures add egress_decree_years varchar(32);	 
 alter table rental_units add efficiency char(1);
 alter table addresses add ma_location_id int;
 
 add unit_rooms table see above.

;;	 
;; for testing pusposes we are adding the following values
;;
 
 insert into egresses values(2,1992,1994,     26,    21,         45,  6.5, 'Single',null),( 1,1991,1992,25,20,44,  6.4,'Single',null),(3,2008,2011,24,20,44,5,'Multi',5.7);

 ;;
 ;; 5/29/2019
 ;;
 		insert into rental.standard_fees values(1,1,current_date,70,70,70,70,25,25,70,50,25,25,20);

		alter table rental.rental_bills add other_fee_title varchar(80);
		alter table rental.rental_bills add other_fee decimal(6,2);
		alter table rental.rental_bills add other_fee2_title varchar(80);
		alter table rental.rental_bills add other_fee2 decimal(6,2);

;;
;; 6/7/2019
;;
 alter table rental.cans alter column item5 type varchar(1024);
 alter table rental.cans alter column item6 type varchar(1024);				
 alter table rental.cans alter column item7 type varchar(1024);
 alter table rental.cans alter column item8 type varchar(1024);	
 
 
 alter table rental.inspection_cans alter column item5 type varchar(1024);
 alter table rental.inspection_cans alter column item6 type varchar(1024);
 alter table rental.inspection_cans alter column item7 type varchar(1024);
 alter table rental.inspection_cans alter column item8 type varchar(1024);		

 ;;
 ;; change all rental records that has 'No Agent' as agent to null
 ;; agent_id=0, need to be changed to null
 ;;
update rentals set agent_id=null where agent_id=0;
alter table rental_logs alter column action_taken type varchar(30);
;;
;; added the following two fields to addresses table
;;
 alter table rental.addresses add column state varchar(2);	
 alter table rental.addresses add column zip varchar(10);	
 update adddresses set state='IN';
;;
;; 7/22/2019
;;
;; we need to set default values for null values for numeric fields

alter table rental_bills alter column single_building_rate  set default 0;
alter table rental_bills alter column	 multi_building_rate  set default 0;
alter table rental_bills alter column	 condo_building_rate  set default 0;
alter table rental_bills alter column	 rooming_building_rate  set default 0;
alter table rental_bills alter column	 unit_rate  set default 0;
	 
alter table rental_bills alter column	 bath_rate  set default 0;
alter table rental_bills alter column	 reinsp_rate  set default 0;
alter table rental_bills alter column	 noshow_rate  set default 0;
alter table rental_bills alter column	 bhqa_fine  set default 0;
alter table rental_bills alter column	 single_building_cnt  set default 0;
	 
alter table rental_bills alter column	 multi_building_cnt  set default 0;
alter table rental_bills alter column	 condo_building_cnt  set default 0;
alter table rental_bills alter column	 rooming_building_cnt  set default 0;

alter table rental_bills alter column	 unit_cnt  set default 0;
alter table rental_bills alter column	 bath_cnt  set default 0;
alter table rental_bills alter column	 noshow_cnt  set default 0;
	 
alter table rental_bills alter column	 reinsp_cnt  set default 0;
alter table rental_bills alter column	 appeal_fee  set default 0;
alter table rental_bills alter column	 credit  set default 0;
alter table rental_bills alter column	 summary_rate  set default 0;
	 
alter table rental_bills alter column	 idl_rate  set default 0;
alter table rental_bills alter column	 summary_cnt  set default 0;
alter table rental_bills alter column	 idl_cnt  set default 0;
	 
alter table rental_bills alter column	 other_fee  set default 0;
alter table rental_bills alter column	 other_fee2  set default 0;
;;
update rental_bills set single_building_rate=0 where single_building_rate is null;
update rental_bills set multi_building_rate=0 where multi_building_rate is null;
update rental_bills set condo_building_rate=0 where condo_building_rate is null;
update rental_bills set rooming_building_rate=0 where rooming_building_rate is null;
update rental_bills set unit_rate=0 where unit_rate is null;
update rental_bills set bath_rate=0 where bath_rate is null;
update rental_bills set reinsp_rate=0 where reinsp_rate is null;
update rental_bills set noshow_rate=0  where noshow_rate is null;
update rental_bills set bhqa_fine=0 where bhqa_fine is null;
update rental_bills set single_building_cnt=0 where single_building_cnt is null;
update rental_bills set multi_building_cnt=0 where multi_building_cnt is null;
update rental_bills set condo_building_cnt=0 where condo_building_cnt is null;
update rental_bills set rooming_building_cnt=0 where rooming_building_cnt is null;
update rental_bills set unit_cnt=0 where unit_cnt is null;
update rental_bills set bath_cnt=0 where bath_cnt is null;
update rental_bills set noshow_cnt=0 where noshow_cnt is null;
update rental_bills set reinsp_cnt=0 where reinsp_cnt is null;
update rental_bills set appeal_fee=0 where appeal_fee is null;
update rental_bills set credit=0 where credit is null;
update rental_bills set summary_rate=0 where summary_rate is null;
update rental_bills set idl_rate=0 where  idl_rate  is null;
update rental_bills set summary_cnt=0 where summary_cnt is null;
update rental_bills set idl_cnt=0 where idl_cnt is null;
update rental_bills set  other_fee=0 where other_fee is null;
update rental_bills set  other_fee2=0 where other_fee2 is null;

;;
;; a procedure to calculate the amount due on the bill
;;
CREATE OR REPLACE FUNCTION get_bill_balance(bid int)
RETURNS numeric AS $$
DECLARE
   single_rate numeric(6,2):=0;
	 multi_rate numeric(6,2):=0;
	 condo_rate numeric(6,2):=0;
	 rooming_rate numeric(6,2):=0;
	 unit_rate numeric(6,2):=0;
	 bath_rate numeric(6,2):=0;
	 reinsp_rate numeric(6,2):=0;
	 noshow_rate numeric(6,2):=0;
	 bhqa_fine numeric(6,2):=0;
	 single_cnt int:=0;
	 multi_cnt int:=0;
	 condo_cnt int:=0;
	 rooming_cnt int:=0;
	 unit_cnt int:=0;
	 bath_cnt int:=0;
	 noshow_cnt int:=0;
	 reinsp_cnt int:=0;
	 appeal character(1);
	 appeal_fee numeric(6,2):=0;
	 credit numeric(6,2):=0;
	 summary_rate numeric(6,2):=0;
	 summary_cnt int:=0;
	 idl_cnt int:=0;
	 idl_rate numeric(6,2):=0;			
	 other_fee numeric(6,2):=0;
	 other_fee2 numeric(6,2):=0;
	 summary_flag character(1):=null;
	 idl_flag character(1):=null;
	 balance numeric:=0;
BEGIN
	 SELECT
	 b.single_building_rate,
	 b.multi_building_rate,
	 b.condo_building_rate,
	 b.rooming_building_rate,
	 b.unit_rate,
	 
	 b.bath_rate,
	 b.reinsp_rate,
	 b.noshow_rate,
	 b.bhqa_fine,
	 b.single_building_cnt,
	 
	 b.multi_building_cnt,
	 b.condo_building_cnt,
	 b.unit_cnt,
	 b.bath_cnt,
	 b.noshow_cnt,
	 
	 b.reinsp_cnt,
	 b.appeal,
	 b.appeal_fee,
	 b.credit,
	 b.summary_rate,
	 
	 b.idl_rate,
	 b.summary_flag,
	 b.idl_flag,
	 b.summary_cnt,
	 b.idl_cnt,
	 
	 b.other_fee,
	 b.other_fee2
	 into
	 single_rate,
	 multi_rate,
	 condo_rate,
	 rooming_rate,
	 unit_rate,
	 
	 bath_rate,
	 reinsp_rate,
	 noshow_rate,
	 bhqa_fine,
	 single_cnt,
	 
	 multi_cnt,
	 condo_cnt,
	 unit_cnt,
	 bath_cnt,
	 noshow_cnt,
	 
	 reinsp_cnt,
	 appeal,
	 appeal_fee,
	 credit,
	 summary_rate,
	 
	 idl_rate,
	 summary_flag,
	 idl_flag,
	 summary_cnt,
	 idl_cnt,
	 
	 other_fee,
	 other_fee2
	 FROM rental.rental_bills AS b
	 WHERE b.id=bid;

	 IF appeal is not null
	 THEN
			balance = appeal_fee;
	 ELSE
		   balance = single_rate*single_cnt+multi_rate*multi_cnt+
			 condo_rate*condo_cnt+rooming_rate*rooming_cnt+unit_rate*unit_cnt+
			 bath_cnt*bath_rate+reinsp_cnt*reinsp_rate+noshow_rate*noshow_cnt+
			 idl_rate*idl_cnt+bhqa_fine+other_fee+other_fee2;
	 END IF;
	 
	 IF summary_flag is not null THEN
	   IF summary_cnt > 0 THEN
	     balance = balance + summary_cnt*summary_rate;
		 ELSE
		   balance = balance + unit_cnt*summary_rate;
		 END IF;
		END IF;
	 if credit > 0 THEN
	  if balance > credit THEN
		 balance = balance - credit;
		ELSE
		 balance = 0;
	  END IF;
	 END IF;
	 RETURN balance;
END;
$$ language PLpgSQL;


;;
;; for testing
;;
CREATE OR REPLACE FUNCTION get_bill_balance(IN bid int) RETURNS numeric AS $$ DECLARE single_rate numeric(6,2):=0;multi_rate numeric(6,2):=0;balance numeric:=0; BEGIN SELECT single_building_rate, multi_building_rate into single_rate,multi_rate    FROM rental_bills WHERE id=bid;balance:=single_rate+multi_rate; RETURN balance; END;$$ language PLpgSQL;


DO
$$
DECLARE
  rec record;
BEGIN
  FOR rec IN
  SELECT table_schema,table_name,column_name
  FROM information_schema.columns
  WHERE table_name = 'rental_bills' and table_schema='rental'
  LOOP
    EXECUTE format('ALTER TABLE %I.%I DROP %I;',
                   rec.table_schema, rec.table_name, rec.column_name);
  END LOOP;
END;
$$
LANGUAGE plpgsql
