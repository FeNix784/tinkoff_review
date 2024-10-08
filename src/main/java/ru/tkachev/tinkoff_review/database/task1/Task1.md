# Database - Задание №1

### PgBouncer, зачем нужен, режимы работы<br/><br/>

PgBouncer - это пулер соединений, который используется для минимизации издержек 
при открытии соединений и эффективном их использовании. Фактически новые клиенты 
базы данных будут использовать уже открытые предыдущими клиентами соединения.

OLTP системы подразумевают, что клиенты должны быстро подключаться, 
отправлять короткие запросы серверу БД, получать от него ответ и закрывать соединения.
На установление и закрытие соединения тратятся ресурсы. PostgreSQL не управляет 
клиентскими соединениями, если не считать установленных лимитов на их подключение. 
Кроме того, на каждое простаивающее соединение тратится часть ресурсов сервера, 
которые уже можно было бы высвободить или отдать другому пользователю. 
Пулы соединений - это прослойка между клиентским приложением и базой данных. 
Они слушают входящие соединения, принимают их, выполняют аутентификацию пользователей
и перенаправляют соединения на базу данных в соответствии с предоставленными кредами.

У PgBouncer'а есть 2 основных режима работы:

* session - сессионный, соединение возвращается в пул после того как его закроет клиент;
* transaction - транзакционный, соединение возвращается сразу после завершения транзакции.

С сессионным режимом работы все просто: когда клиент подключается, ему назначается 
одно серверное подключение на всё время; когда клиент отключается, это подключение 
к серверу возвращается в пул.

Транзакционный режим работы устроен хитрее: подключение к серверу назначается клиенту
только на время транзакции; когда пулер замечает, что транзакция завершена, 
это подключение возвращается в пул. Транзакционный режим работы имеет свои особенности:
подготовленные выражения (prepared statements) не поддерживаются; 
нельзя задавать состояния сеансов и сбрасывать их.

На каждом сервере базы данных разворачивается по одному экземпляру пулера на каждый 
режим. Каждый слушает соединения на своем порту:

* 5432 - session;
* 6432 - transaction.

Раз соединения в пуле можно постоянно переиспользовать, то, очевидно, размер пула 
должен быть в несколько раз меньше максимального количества соединений к базе 
(параметр max_connections). 

Устоявшиеся заблуждения:

1. Необходимо поднять лимит соединений к базе и поставить пулы на максимум, 
тогда клиенты не будут томиться в очереди и сразу смогут поработать с базой. 
Однако как показывает практика, просадки в производительности сервера базы данных 
являются следствием неоптимальных запросов и/или банальной нехватки ресурсов.

2. Приложению нужно постоянно держать сотню простаивающих соединений при паре 
активных пользователей на случай, если в один момент времени придет большое 
количество уникальных пользователей. Однако сервер базы данных вероятно будет не готов
к такому всплеску активности, поскольку ресурсы ему выделены с расчетом на нормальную
нагрузку, которую дают пара активных пользователей. При росте нагрузки все ресурсы 
сервера будут утилизированы на 100%, OOM Killer будет постоянно отстреливать 
процессы базы данных, а база не будет вылазить из режима восстановления.

В действительности наращивать размер пула имеет смысл до определенного уровня, 
дальше прироста в производительности получить не удастся. Намного быстрее 
достигнется предел в производительность процессоров, скорость чтения/записи данных с 
диска, чем исчерпается пул соединений.
