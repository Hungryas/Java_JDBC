-- Вывести компании, которые организуют перелеты с Владивостока
select distinct c.name
from company c , trip t 
where t.town_from = 'Vladivostok' and c.id = t.company;