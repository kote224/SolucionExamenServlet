create table IF NOT EXISTS EMPRESA(
	dni varchar(25) PRIMARY KEY,
	nombre varchar(25),
	fecha date
);
create table IF NOT EXISTS CONSOLA(
	id varchar(25) PRIMARY KEY,
	nombre varchar(25),
	empresa varchar(25)
);
create table IF NOT EXISTS VIDEOJUEGO(
	id varchar(25) PRIMARY KEY,
	titulo varchar(25),
	edad int(25),
	lanzamiento date
);
