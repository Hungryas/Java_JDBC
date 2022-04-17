-- Вывести имена всех когда-либо обслуживаемых пассажиров авиакомпаний
select p.name
from passenger p, pass_in_trip pit 
where p.id = pit.passenger;