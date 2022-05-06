-- Реализовать схему бд
create table company (
	id serial primary key,
	name varchar(50) unique
);

create table passenger (
	id serial primary key,
	name varchar(50) unique
);

create table trip (
	id serial primary key,
	company int references company(id),
	plane varchar(10) not null,
	town_from varchar(30) not null,
	town_to varchar(30) not null,
	time_out timestamp not null,
	time_in timestamp not null
);

create table public.pass_in_trip (
	id serial primary key,
	trip int references trip(id),
	passenger int references passenger(id),
	place varchar(10) not null
);