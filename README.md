#Sql vs NoSql

Wykorzystane technologie
- Spring Boot
- Vaadin
- PostgreSQL
- MongoDB

Program odczytuje 1000 rekordów z serwisu 1000 rekordów w postaci CSV.
Następnie przeprowadzany jest pięciokrotny zapis i odczyt do baz danych.
Dane pomiarowe zapisane zostaję w bazie danych.
Po uruchomieniu programu i wybraniu w przeglądarce adresu http://localhost:8080
zostaną wyświetlone wyniki przeprowadzonego testu.

Aby skorzystać z programu, należy zarejestrować się w serwisie Mockaroo,
następnie w pliku Start.java w linii
```
String response = restTemplate.getForObject("url_to_mockarro_api",

```
wstawić poprawny link z serwisu Mockaroo.

