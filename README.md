## 3.2 SQL ##
1. Установить плагин IDEA (для Docker Toolbox и Windows);
2. Запустить контейнер с mysql командой: docker-compose up;
3. Добавить новую БД с помощью виджета Database -> "+" -> Data Source -> MySQL;
4. Задать параметры name "my connection", user "app", password "pass", database "app";
5. В console добавить схему из файла schema.sql; 
6. Запустить приложение командой: java -jar artifacts/app-deadline.jar;
7. Запустить автотесты;
8. При повторном запуске приложения, перед началом работы в console запустить: 

   ``` 
   delete from auth_codes;
   delete from cards;
   delete from users; 
