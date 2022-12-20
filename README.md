## Используемый стек
[Java 17](https://www.oracle.com/java/technologies/downloads/#java17)

[Gradle](https://docs.gradle.org/7.5/userguide/userguide.html)
## Инструкцию по запуску
Приложение запускается java PrintCheckRunner(набор_параметров), где набор
параметров в формате itemId-quantity (itemId - идентификатор товара, quantity -
его количество), card-number (number - код скидочной карты. Если она есть).

```java
//Пример

//Ввод
PrintCheckRunner printCheckRunner = new PrintCheckRunner("3-1 4-3 2-4 2-3"); // 3 - это идентификатор товара, 1 - количество товара

//Вывод
                    CASH RECEIPT                   

QTY   DESCRIPTION           PRICE        TOTAL
----  -----------           -----        -----
 1    Cheese Bread SALE     $ 20.0       $ 20.0
 3    Egg Pie SALE          $ 200.0      $ 600.0
 4    Buko Pie              $ 100.0      $ 400.0
 3    Buko Pie              $ 100.0      $ 300.0

-----------------------------------------------------
TOTAL                                    $ 1320.0

new PrintCheckRunner("3-1 4-5"); // Если количество акционного товара >= 5 то тогда скидка 10%

                    CASH RECEIPT                   

QTY   DESCRIPTION           PRICE        TOTAL
----  -----------           -----        -----
 1    Cheese Bread SALE     $ 20.0       $ 20.0
 6    Egg Pie SALE          $ 200.0      $ 1080.0

-----------------------------------------------------
Total                                    $ 1100.0
VAT 10%:                                  $ 230.0

TOTAL                                    $ 990.0


//Ввод

PrintCheckRunner printCheckRunner = new PrintCheckRunner("3-1 4-3 2-4 2-3", "card-123"); // card-123 - это скидочная карта, которая дает 5% скидку

//Вывод
                    CASH RECEIPT                   

QTY   DESCRIPTION           PRICE        TOTAL
----  -----------           -----        -----
 1    Cheese Bread SALE     $ 20.0       $ 20.0
 3    Egg Pie SALE          $ 200.0      $ 600.0
 4    Buko Pie              $ 100.0      $ 400.0
 3    Buko Pie              $ 100.0      $ 300.0

-----------------------------------------------------
Total                                    $ 1320.0
VAT 5%:                                  $ 66.0

TOTAL                                    $ 1254.0

```
