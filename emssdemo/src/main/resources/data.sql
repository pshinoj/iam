insert into
   role (name)
values
   (
      'ROLE_ADMIN'
   );
insert into
   role (name)
values
   (
      'ROLE_USER'
   );
insert into
   role (name)
values
   (
      'ROLE_EMPLOYEE'
   );
insert into
   role (name)
values
   (
      'ROLE_MANAGER'
   );

insert into user_authorities (username, authority)
values ('admin', 'ROLE_ADMIN');
insert into user_authorities (username, authority)
values ('admin', 'ROLE_EMPLOYEE');
insert into user_authorities (username, authority)
values ('james', 'ROLE_USER');
insert into user_authorities (username, authority)
values ('dennis', 'ROLE_USER');
insert into user_authorities (username, authority)
values ('bjarne', 'ROLE_USER');
insert into user_authorities (username, authority)
values ('james', 'ROLE_EMPLOYEE');
insert into user_authorities (username, authority)
values ('dennis', 'ROLE_EMPLOYEE');
insert into user_authorities (username, authority)
values ('bjarne', 'ROLE_EMPLOYEE');

insert into security_users (username, password, enabled)
values ('admin', '$2a$04$sEnTVTtCal21bhXZg1YI9.BiZFDmZPUFRU6H9IhfZwAf5k.jd5/uW', true);
insert into security_users (username, password, enabled)
values ('james', '$2a$04$sEnTVTtCal21bhXZg1YI9.BiZFDmZPUFRU6H9IhfZwAf5k.jd5/uW', true);
insert into security_users (username, password, enabled)
values ('dennis', '$2a$04$sEnTVTtCal21bhXZg1YI9.BiZFDmZPUFRU6H9IhfZwAf5k.jd5/uW', true);
insert into security_users (username, password, enabled)
values ('bjarne', '$2a$04$sEnTVTtCal21bhXZg1YI9.BiZFDmZPUFRU6H9IhfZwAf5k.jd5/uW', true);

insert into
   employee (first_name, last_name, type, security_users_id)
values
   (
      'admin', 'admin', 'Admin', 1
   );
insert into
   employee (first_name, last_name, type, security_users_id)
values
   (
      'James', 'Gosling', 'Employee', 2
   );
insert into
   employee (first_name, last_name, type, security_users_id)
values
   (
      'Dennis', 'Ritchie', 'Employee', 3
   );
insert into
   employee (first_name, last_name, type, security_users_id)
values
   (
      'Bjarne', 'Stroustrup', 'Employee', 4
   );
