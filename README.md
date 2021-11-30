## 3.2 SQL ##
1. Установить плагин IDEA (для Docker Toolbox и Windows).
2. Запустить контейнер с mysql командой: docker-compose up
3. Запустить приложение командой: java -jar artifacts/app-deadline.jar
4. Запустить автотесты.
5. При повторном запуске приложения, перед началом работы в console запустить: 

   ``` 
   delete from auth_codes;
   delete from cards;
   delete from users; 
