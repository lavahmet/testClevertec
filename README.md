## Используемый стек
[Java 17](https://www.oracle.com/java/technologies/downloads/#java17)

[Gradle](https://docs.gradle.org/7.5/userguide/userguide.html)
## Инструкцию по запуску
Приложение запускается java PrintCheckRunner(набор_параметров), где набор
параметров в формате itemId-quantity (itemId - идентификатор товара, quantity -
его количество), card-number (number - код скидочной карты. Если она есть).

```java
//Пример
PrintCheckRunner printCheckRunner = new PrintCheckRunner("3-1"); // 3 - это идентификатор товара, 1 - количество товара
PrintCheckRunner printCheckRunner = new PrintCheckRunner("3-1", "card-123"); // card-123 - это скидочная карта  
```
