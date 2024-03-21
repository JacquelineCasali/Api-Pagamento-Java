create table cliente(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
nome  varchar(255) not null,
email varchar(255) not null unique,
telefone  varchar(20) not null


)Engine=InnoDB;;

