create database if not exists leavepulse;

use leavepulse;

create table if not exists roles (
	role_id int primary key not null auto_increment,
    role_name varchar(50) unique not null
);

select * from roles;
insert into roles (role_name) 
values ("Developer"),("Tester");

create table if not exists employees (
	employee_id int primary key auto_increment,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(50) unique not null,
    phone_no int unique not null,
    password varchar(50) not null,
    address varchar(255) not null,
    hire_date date not null
);

insert into employees (first_name, last_name, email, phone_no, password, address, hire_date)
values ("Dhanush", "Selvam", "dhanush@kowmart.com", 9025214260, "Aa!12345", "No.5/10, Raghavan Colony, West Mambalam, Chennai - 600033", 2023-08-01),
("Surya", "Umapathy", "surya@gmail.com", "9378374838", "Aa!12345", "No.20, Gandhi Street, Anna Nagar - 600040", 2023-08-02);

alter table employees modify column phone_no bigint;

insert into employees (first_name, last_name, email, phone_no, password, address, hire_date)
values ("Dhanush", "Selvam", "dhanush@kowmart.com", 9025214260, "Aa!12345", "No.5/10, Raghavan Colony, West Mambalam, Chennai - 600033", 2023-08-01),
("Surya", "Umapathy", "surya@gmail.com", "9378374838", "Aa!12345", "No.20, Gandhi Street, Anna Nagar - 600040", 2023-08-02);

create table if not exists employees (
	employee_id int primary key auto_increment,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(50) unique not null,
    phone_no int unique not null,
    password varchar(50) not null,
    address varchar(255) not null,
    hire_date date not null
);

insert into employees (first_name, last_name, email, phone_no, password, address, hire_date)
values ("Dhanush", "Selvam", "dhanush@kowmart.com", 9025214260, "Aa!12345", "No.5/10, Raghavan Colony, West Mambalam, Chennai - 600033", 2023-08-01),
("Surya", "Umapathy", "surya@gmail.com", "9378374838", "Aa!12345", "No.20, Gandhi Street, Anna Nagar - 600040", 2023-08-02);

alter table employees modify column phone_no bigint;

insert into employees (first_name, last_name, email, phone_no, password, address, hire_date)
values ("Dhanush", "Selvam", "dhanush@kowmart.com", 9025214260, "Aa!12345", "No.5/10, Raghavan Colony, West Mambalam, Chennai - 600033", '2023-08-01'),
("Surya", "Umapathy", "surya@gmail.com", "9378374838", "Aa!12345", "No.20, Gandhi Street, Anna Nagar - 600040", '2023-08-02');

select * from employees;

create table if not exists leave_type (
	leave_id int primary key not null auto_increment,
    leave_type varchar(50) unique not null
);

insert into leave_type (leave_type) 
values ("Sick Leave"),("Personal Leave");

select * from leave_type;

insert into roles (role_name) 
values ("Manager"),("Chief HR Officer");

drop table roles;
drop table employees;

create table if not exists roles (
	role_id int primary key not null auto_increment,
    role_name varchar(50) unique not null
);

insert into roles (role_name) values ("Chief HR Officer"),("Manager"),("Developer");

create table if not exists employees (
	employee_id int primary key not null auto_increment,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(50) unique not null,
    phone_no int unique not null,
    password varchar(50) not null,
    address varchar(255) not null,
    hire_date date not null,
    created_at datetime not null,
	modified_at datetime not null
);

alter table employees modify column phone_no bigint;

insert into employees (first_name, last_name, email, phone_no, password, address, hire_date, created_at, modified_at)
values ("Dhanush", "Selvam", "dhanush@kowmart.com", 9025214260, "Aa!12345", "No.5/10, Raghavan Colony, West Mambalam, Chennai - 600033", '2023-08-01', '2023-08-01 10:55:32', '2023-08-01 10:55:32'),
("Surya", "Umapathy", "surya@gmail.com", "9378374838", "Aa!12345", "No.20, Gandhi Street, Anna Nagar - 600040", '2023-08-02', '2023-08-02 10:20:43', '2023-08-02 10:20:43'),
("Vijay", "Kumar", "vijay@gmail.com", "9873967231", "Aa!12345", "No.43, Krishnan Street, Kandanchavadi - 600040", '2023-08-03', '2023-08-03 14:08:10', '2023-08-03 14:08:10');

create table if not exists employee_role (
	emp_role_id int primary key not null auto_increment,
    employee_id int not null, foreign key (employee_id) references employees(employee_id),
    manager_id int not null, foreign key (employee_id) references employees(employee_id)
);

alter table employee_role add column employee_role int not null;

alter table employee_role add foreign key (employee_role) REFERENCES roles(role_id);

insert into employee_role (employee_id, employee_role, manager_id) values (1,1,1), (2,2,1), (3,3,2);

select * from employee_role;

CREATE TABLE IF NOT EXISTS requests (
    request_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    leave_id INT NOT NULL,
    applied_on DATE NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    reason VARCHAR(255),
    manager_id INT NOT NULL,
    status ENUM("accept","reject","pending") DEFAULT "pending",
    created_at DATETIME NOT NULL,
    modified_at DATETIME NOT NULL,
    created_by INT NOT NULL,
    modified_by INT NOT NULL,
    FOREIGN KEY (leave_id) REFERENCES leave_type(leave_id),
    FOREIGN KEY (manager_id) REFERENCES employees(employee_id),
    FOREIGN KEY (created_by) REFERENCES employees(employee_id),
    FOREIGN KEY (modified_by) REFERENCES employees(employee_id)
);

alter table requests drop column applied_on;

insert into requests (leave_id, start_date, end_date, reason, manager_id, created_at, modified_at, created_by, modified_by)
values (1, '2023-08-10', '2023-08-12', "Fever", 2, '2023-08-03 16:34:40', '2023-08-03 16:34:40', 3, 3),
(2, '2023-08-05', '2023-08-08', null, 1, '2023-08-03 16:40:20', '2023-08-03 16:40:20', 2, 2);
    
select * from requests;   

alter table requests add column comments varchar(255); 