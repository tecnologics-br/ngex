create table ngex_class (
   id serial not null primary key,
   class_name varchar(100),
   package_name varchar(300),
   entity_name varchar(100),
   description varchar(250) 
)

create table ngex_attribute (
   id serial not null primary key,
   class_id integer not null,
   attribute_name varchar(100),
   field_name varchar(100),
   description varchar(250),
   attribute_type varchar(50),
   field_type varchar(50),
   mandatory boolean,
   field_size integer,
   field_precision integer,
   constraint class_fk foreign key (class_id) references ngex_class(id)
)