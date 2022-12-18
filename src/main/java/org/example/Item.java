package org.example;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private final List<String> itemList = new ArrayList<>();
    private final List<Double> priceList = new ArrayList<>();
    private final List<String> discountCards = new ArrayList<>();

    public void fill() {
        itemList.add("Asado Roll SALE");
        itemList.add("Buko Pie");
        itemList.add("Cheese Bread SALE");
        itemList.add("Egg Pie SALE");
        itemList.add("Emapanada");
        itemList.add("Ensayamada SALE");
        itemList.add("Hopia");
        itemList.add("Inipit");
        itemList.add("Mamon SALE");
        itemList.add("Monay");

        priceList.add(50.00);
        priceList.add(100.00);
        priceList.add(20.00);
        priceList.add(200.00);
        priceList.add(100.00);
        priceList.add(10.00);
        priceList.add(10.00);
        priceList.add(5.00);
        priceList.add(5.00);
        priceList.add(5.00);

        discountCards.add("card-123");
        discountCards.add("card-4356");
        discountCards.add("card-32454");
        discountCards.add("card-0978");
    }

    public List<String> getItemList() {
        return itemList;
    }

    public List<Double> getPriceList() {
        return priceList;
    }

    public List<String> getDiscountCards() {
        return discountCards;
    }
}
