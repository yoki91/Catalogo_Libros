drop user 'libro'@'localhost';
create user 'libro'@'localhost' identified by 'libro';
grant all privileges on librodb.* to 'libro'@'localhost';
flush privileges;
