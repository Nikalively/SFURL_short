Зачетная итоговая работа по дисциплине "Программирование на языке Java".
В качестве задания разрабатывался консольный сервис сокращения ссылок с возможностью управления лимитом переходов и времени жизни ссылки, автоматически переноправлять пользователя на оригинальный URL. 
Функциональность:
1.Возможность авторизации пользователя по UUID. Если пользователь не ввел UUID, создается новый пользователь, которому возвращается его UUID и сразу создается ссылка.
2.Короткая ссылка ссылка генерируется с использованием первых 6 символов UUID, что обеспечивает уникальность.
Разные пользователи получают уникальные ссылки. В сервисе пользователь указывает длинный URL, далее устанавливаются лимиты переходов и времени жизни ссылки, происходит генерация короткой ссылки. Также возможно удаление ссылки пользователем.
3. При вводе короткой ссылки пользователь автоматически перенаправляется на исходный URL адрес. Если же ссылка недоступна (превышен лимит переходов по короткой ссылке, истек срок действия короткой ссылки), сервис выводит уведомление об этом пользователю.
4. Основные параметры, такие как время жизни ссылки, лимит переходов вынесены в отдельный файл application.properties. Основные параметры могут быть изменены в конфигурационном файле без необходимости изменения кода. 
5. Понятный интуитивный интерфейс, все подсказки, приветствие и прощание выводятся на экран.

В качестве реализации проекта использовалась H2 база данных. Ссылки автоматически удаляются при достижении максимального времени жизни короткой ссылки или превышения лимита перехода по ней.
