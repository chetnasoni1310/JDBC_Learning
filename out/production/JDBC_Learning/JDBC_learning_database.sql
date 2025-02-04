use students;
create table employee
(
  id int primary key,
  name varchar(20) ,
  title varchar(200),
  salary double 
);

insert into employee (id, name, title, salary) values 
  (1, 'Chetna', 'GoogleSWE', 1.00),
  (2, 'Harshita', 'SWE', 2.00);

select * from employee ;

create table image_table (
image_id int Primary Key auto_increment ,
image_data LONGBLOB ,
upload_date TIMESTAMP default current_timestamp 
);

show tables;

describe image_table;

select * from image_table ;
