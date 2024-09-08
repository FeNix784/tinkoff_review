# Kubernetes - Задание №2

### Пробы, какие бывают виды, от чего зависит статус<br/><br/>

Все пробы имеют следующие параметры:

* `initialDelaySeconds`: количество секунд ожидания перед запуском проверки работоспособности или готовности;
* `periodSeconds`: как часто проверять пробу;
* `timeoutSeconds`: количество секунд до отметки тайм-аута пробы;
* `successThreshold`: минимальное количество последовательных успешных проверок, чтобы проба была помечена как пройденная;
* `failureThreshold`: количество повторных попыток до пометки пробы как неудачной, для проб работоспособности это приведет к перезапуску модуля, для проб готовности это приведет к тому, что контейнер будет помечен как неготовый.


### Liveness Probe

Liveness-проба помогает контролировать состояние приложения: показывает, работает 
ли под с запущенным приложением или нет. Проверки выполняются периодически. 
В случае, если под не пройдет проверку Liveness-пробы, Kubernetes автоматически 
перезапустит под с приложением. Если же под с приложением запущен, 
проверка Liveness-пробы игнорируется.

Liveness-пробы не должны быть ресурсоемкими. Если вызывать их каждую секунду, 
кластер Kubernetes будет расходовать дополнительные ресурсы.

### Readiness Probe

Readiness-проба помогает проверить, готов ли под с запущенным приложением принимать 
сетевой трафик. Важная особенность Readiness-пробы в том, что если проверка будет 
неуспешной, то под с приложением будет исключен из балансировки. Это значит, 
что новые запросы перестанут на него поступать, но сам под продолжит работу и не 
будет принудительно завершен. Такую особенность можно использовать, 
чтобы «освободить» приложение от потока поступающего сетевого трафика. 
Как только приложение возобновит прием трафика, под вернется обратно в балансировку.

Readiness-проба полезна больше всего, когда приложение работает со сбоями и 
временно не может принимать входящий трафик. Она сообщает Kubernetes, что 
приложение находится на этапе запуска, прежде чем начнет получать и обрабатывать 
сетевой трафик.

Также Readiness-пробы широко используются при первоначальном запуске: 
при старте масштабных приложений им может потребоваться некоторое время на запуск. 
Readiness-проба гарантирует, что Kubernetes не будет отправлять трафик до тех пор, 
пока приложение полностью не запустится. 

### Startup Probe

Startup-проба проверяет, что приложение на запуске прошло инициализацию и готово 
принимать запросы. Когда под с приложением запущен, Kubernetes считает, 
что приложение в нем работает и может принимать запросы.

Однако некоторые приложения могут долго запускаться. Если Kubernetes попытается 
отправить запрос до запуска, они не обработаются. Предотвратить такие ситуации 
помогают Startup-пробы, которые сообщают кластеру Kubernetes о готовности 
приложения к работе. Только после этого Kubernetes будет считать, что они работают.