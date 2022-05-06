-- Вывести все рейсы, совершенные из Москвы
select *
from trip t 
where t.town_from = 'Moscow'
order by t.id;