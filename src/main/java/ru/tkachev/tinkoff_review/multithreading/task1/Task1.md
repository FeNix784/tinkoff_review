# Multithreading - Задание №1

### Посмотреть разные реализации тред пулов, выработать понимание использования определенного тред пула для разных практических задач <br/><br/>

SingleThreadExecutor - пул потоков только с одним потоком, таким образом все отправленные задачи будут выполняться последовательно

CachedThreadPool - пул потоков, который создает столько потоков, сколько ему необходимо для параллельного выполнения задач, старые доступные потоки будут повторно использоваться для новых задач, но если поток не используется в течение 60 секунд, он будет завершен и удален из пула, этот тип подходит для ситуаций, когда требуется динамическое создание потоков в зависимости от количества задач

FixedThreadPool - пул потоков с фиксированным количеством потоков, если в пуле нет свободных потоков для выполнения задачи, задача помещается в очередь в ожидании завершения другой задачи, этот тип пула потоков подходит для ситуаций, когда требуется выполнение фиксированного количества задач параллельно

ScheduledThreadPool - пул потоков для планирования будущих задач, этот тип пула потоков подходит для ситуаций, когда требуется выполнение задач по расписанию или с определенной задержкой

SingleThreadScheduledPool - пул потоков, содержащий только один поток для планирования будущей задачи
