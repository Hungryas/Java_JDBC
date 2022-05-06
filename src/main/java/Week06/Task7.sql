/* Вывести отсортированный по количеству перелетов (по убыванию) и имени (по
возрастанию) список пассажиров, совершивших хотя бы 1 полет
*/
select p.name, count(pit.*) as cpit
from passenger p, pass_in_trip pit
where p.id = pit.passenger
group by p.name
order by cpit desc, name