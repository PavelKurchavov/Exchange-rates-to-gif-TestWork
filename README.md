Cервис, который обращается к сервису курсов валют, и отдает gif в ответ:  
если курс по за сегодня стал выше вчерашнего,  
то отдает рандомную отсюда https://giphy.com/search/rich  
если ниже - отсюда https://giphy.com/search/broke  
Ссылки:
REST API курсов валют - https://docs.openexchangerates.org/  
REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide  
Сервис на Spring Boot 2 + Java.
Запросы приходят на HTTP endpoint, туда передается код валюты  
Для взаимодействия с внешними сервисами используется Feign  
Все параметры (валюта по отношению к которой смотрится курс и т.д) вынесены в .properties 
На сервис написаны тесты.
(для мока внешних сервисов использовался @Mockbean)   
Сборка - gradle.  

Инструкция по запуску:
1. Скачать jar - архив из папки lib.
2. Запустить.
2.1. Для Windows - double click.
2.2. Для  Linux - ввести в терминал команду java -jar service.jar
4. В браузере перейти по адресу localhost:8080/get_gif?to=code

(где code - трехзначный код валюты. Поддерживается GBP, JPY, RUB, EUR.
Базовая валюта - USD.
Параметры можно не указывать, по умолчанию стоит конвертация USD-EUR)

4.Наслаждаться гифкой.

Docker:
1.  Получить образ с DockerHub - docker pull pavelkurchavov/myservice:1
2.  Запустить образ - docker run -d -p 8080:8080 -t pavelkurchavov/myservice:1
3.  Перейти в браузере по адресу, указанному в инструкции по запуску выше.
4.  Profit!
