alter table medicos add column activo tinyint;
update medicos set activo = 1;
