# Spring - Задание №3

### Разобрать вопрос с несколькими бинами одного класса в контексте<br/><br/>

Если автоматическая конфигурация Spring по каким-то причинам не подходит, например,
в данной задаче требуется создать в контексте несколько бинов одного класса,
то можно использоваться java-конфигурирование.

Для этого создается обычный java-класс, методы которого будут возвращать 
нужные бины одного класса с разными именами, и такой класс помечается аннотацией `@Configuration`. 

В данном примере создан класс `User`, который имеет единственное поле `name`.
Далее задан конфигурационный класс `UserConfig`, в котором определены два
метода `getUser1()` и `getUser2()`, возвращающие бины для первого и второго
пользователей с именами `user1` и `user2` соответственно.

Далее создан сервис `UserService`, в который через конструктор инжектятся
два заданных в конфигурации бина с именами `user1` и `user2` соответственно.
После чего в методе `initialize()`, помеченном аннотацией `@PostConstruct`,
выводятся имена пользователей (см. рисунок ниже).  
![Инжект нескольких бинов одного класса](Task3.png)
