-- Вывести количество рейсов, совершенных на TU-134
select count(*)
from trip t 
where t.plane = 'TU-134';