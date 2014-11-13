source librodb.sql
insert into users values('alicia', MD5('alicia'), 'Alicia', 'alicia@acme.com');
insert into user_roles values ('alicia', 'registered');

insert into users values('blas', MD5('blas'), 'Blas', 'blas@acme.com');
insert into user_roles values ('blas', 'registered');

insert into users values('yifei', MD5('yifei'), 'yoki', 'yokihappy@hotmail.com');
insert into user_roles values ('yifei', 'administrador');

insert into autores (nombre) values('J.K Rowling');
insert into autores (nombre) values('R.R.Tolkien');
insert into autores (nombre) values('Suzanne Collins');
insert into autores (nombre) values('C. S. Lewis');

select sleep(1);insert into libros (titulo,lengua,edicion,Fecha_Edicion,Fecha_Impresion,Editorial) values ('Harry Potter I','EN','Primera','1998-2-10','1999-3-18','ABC');
select sleep(1);insert into libros (titulo,lengua,edicion,Fecha_Edicion,Fecha_Impresion,Editorial) values ('The Lord Of The Rings','EN','Primera','1954-8-28','1960-9-1','NSA');
select sleep(1);insert into libros (titulo,lengua,edicion,Fecha_Edicion,Fecha_Impresion,Editorial) values ('The Hunger Game','EN','Primera','2004-9-4','2008-10-19','VIC');
select sleep(1);insert into libros (titulo,lengua,edicion,Fecha_Edicion,Fecha_Impresion,Editorial) values ('Harry Potter I','EN','Primera','1998-2-10','1999-3-18','ABC');
select sleep(1);insert into libros (titulo,lengua,edicion,Fecha_Edicion,Fecha_Impresion,Editorial) values ('Harry Potter II','EN','Primera','1999-5-8','2000-4-2','ABC');
select sleep(1);insert into libros (titulo,lengua,edicion,Fecha_Edicion,Fecha_Impresion,Editorial) values ('Harry Potter III','EN','Primera','2000-9-15','2002-6-7','ABC');
select sleep(1);insert into libros (titulo,lengua,edicion,Fecha_Edicion,Fecha_Impresion,Editorial) values ('Harry Potter VI','EN','Primera','2001-10-7','2004-3-5','ABC');
select sleep(1);insert into libros (titulo,lengua,edicion,Fecha_Edicion,Fecha_Impresion,Editorial) values ('Harry Potter V','EN','Primera','2004-8-10','2006-6-12','ABC');
select sleep(1);insert into libros (titulo,lengua,edicion,Fecha_Edicion,Fecha_Impresion,Editorial) values ('Harry Potter VI','EN','Primera','2007-3-17','2008-6-7','ABC');
select sleep(1);insert into libros (titulo,lengua,edicion,Fecha_Edicion,Fecha_Impresion,Editorial) values ('Harry Potter VII','EN','Primera','2009-4-7','2010-4-19','ABC');
select sleep(1);insert into libros (titulo,lengua,edicion,Fecha_Edicion,Fecha_Impresion,Editorial) values ('The Hunger Game II','EN','Primera','2005-5-13','2009-12-16','VIC');


insert into libro_autores(idautor,libroid) values(1,1);
insert into libro_autores(idautor,libroid) values(2,2);
insert into libro_autores(idautor,libroid) values(3,3);
insert into libro_autores(idautor,libroid) values(1,4);
insert into libro_autores(idautor,libroid) values(1,5);
insert into libro_autores(idautor,libroid) values(1,6);
insert into libro_autores(idautor,libroid) values(1,7);
insert into libro_autores(idautor,libroid) values(1,8);
insert into libro_autores(idautor,libroid) values(1,9);
insert into libro_autores(idautor,libroid) values(1,10);
insert into libro_autores(idautor,libroid) values(2,11);

select sleep(1);insert into opinion(libroid,username,texto) values ('1','alicia','Un libro muy interesante');
select sleep(1);insert into opinion(libroid,username,texto) values ('1','blas','Un libro entretenido');
select sleep(1);insert into opinion(libroid,username,texto) values ('3','alicia','Un libro demasiado largo');
select sleep(1);insert into opinion(libroid,username,texto) values ('4','blas','Un libro perfecto para los jovenes');
select sleep(1);insert into opinion(libroid,username,texto) values ('7','alicia','Un libro con mucho detalle');
