# Сервис по поиску числа

## Рекомендации к программному обеспечению

Для корректной работы и тестирования сервиса необходимо наличие следующего установленного
и настроенного программного обеспечения:

* Docker;
* Gradle;
* OpenJDK 17+;
* Postman;
* Система контроля версий Git.

---


## Инструкция по запуску приложения

1. Первоначально необходимо склонировать репозиторий сервиса с помощью команды:
```bash
git clone https://github.com/euchekavelo/number-search-service.git
```
2. Перейти в корневую директорию проекта и собрать его при помощи команды:
```bash
  ./gradlew build
```   
3. Затем перейти в корневую директорию проекта и запустить docker-контейнер командой:
```bash
docker-compose up -d
```
4. Перейти по адресу http://localhost:8080/swagger-ui/index.html , чтобы ознакомиться с описанием спецификации API 
и (по желанию) отправить запрос в контейнер сервера приложения, используя кнопку "Try it out" в представленном методе контроллера;
5. (Опциональный шаг) Открыть ПО Postman и, согласно представленной спецификации API, сформировать запрос для отправки 
в контейнер сервера приложения.
---