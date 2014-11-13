drop database if exists librodb;
create database librodb;
 
use librodb;
 
create table users (
username	varchar(20) not null primary key,
userpass	char(32) not null,
name	        varchar(70) not null,
email	        varchar(255) not null
);
 
create table user_roles (
username	varchar(20) not null,
rolename        varchar(20) not null,
foreign key(username) references users(username) on delete cascade,
primary key (username, rolename)
);

create table autores (
idautor       int not null auto_increment primary key,
nombre          varchar(200) not null
);
 
create table libros (
libroid         int not null auto_increment primary key,
titulo          varchar(500) not null,
lengua	        varchar(100) not null,
edicion	        varchar(500) not null,
Fecha_Edicion   varchar(10)    not null,
Fecha_Impresion varchar(10)    not null,
Editorial       varchar(100) not null,
last_modified	timestamp default current_timestamp ON UPDATE CURRENT_TIMESTAMP
);

create table libro_autores(
idautor int not null,
libroid  int not null,
foreign key (idautor)  references autores(idautor) on delete cascade,
foreign key (libroid)  references libros(libroid)  on delete cascade,
primary key (idautor,libroid)
);

create table opinion(
libroid         int not null,
idcomentario    int not null auto_increment unique,
username        varchar(70) not null,
last_modified	timestamp default current_timestamp ON UPDATE CURRENT_TIMESTAMP,
texto           varchar(500) not null,
foreign key (libroid) references libros(libroid) on delete cascade,
foreign key (username) references users(username),
primary key (libroid,username)
);

