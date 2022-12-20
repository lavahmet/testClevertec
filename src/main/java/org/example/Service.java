package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Service {
    private final List<Double> currentPriceList = new ArrayList<>();
    private final List<Integer> quantityList = new ArrayList<>();
    private final List<String> itemList = new ArrayList<>();
    private final Item item;
    boolean isPromotion = false;
    private double total;
    private double vat;

    public Service() {
        this.item = new Item();
    }

    public String checkCardFromTxt(String card) {
        String result = "";
        String[] ct = readFromFile(card).split(" ");
        for (String str : ct) {
            result = checkForCardsAndSales(str);
        }
        return result;
    }

    public String checkCard(String card) {
        return checkForCardsAndSales(card);
    }

    public String CountSales() {
        String result;
        if (isPromotion) {
            result = String.format("%s %36s %s\n", "Total", "$", total);
            total -= total * 0.1;
            result += "VAT 10%:" + String.format("%35s %s\n", "$", new DecimalFormat(".##").format(vat - total));
            result += String.format("\n%s %36s %s\n", "TOTAL", "$", total);
        }
        else result = String.format("%s %36s %s\n", "TOTAL","$", total);
        return result;
    }

    public String checkForCardsAndSales(String cards) {
        String result = "";
        for (int i = 0; i < item.getDiscountCards().size(); i++) {
            if (item.getDiscountCards().get(i).toLowerCase().equals(cards) && isPromotion) {
                result = String.format("%s %36s %s\n", "Total","$", total);
                total -= total * 0.05;
                result += "VAT 15%:" + String.format("%34s %s\n", "$", new DecimalFormat(".##").format(vat - total));
                result += String.format("\n%s %36s %s\n", "TOTAL","$", total);
                break;
            } else if (item.getDiscountCards().get(i).toLowerCase().equals(cards)) {
                result = String.format("%s %36s %s\n", "Total","$", total);
                total -= total * 0.05;
                result += "VAT 5%:" + String.format("%35s %s\n", "$", new DecimalFormat(".##").format(vat - total));
                result += String.format("\n%s %36s %s\n", "TOTAL","$", total);
                break;
            } else result = CountSales();
        }
        return result;
    }


    public String calculate() {
        StringBuilder result = new StringBuilder();
        for (int j = 0; j < itemList.size(); j++) {
            vat += quantityList.get(j) * currentPriceList.get(j);
            result.append(String.format(" %-5s", quantityList.get(j))).append(String.format("%-22s", itemList.get(j))).append("$ ").append(String.format("%-11s", new DecimalFormat(".##").format(currentPriceList.get(j))));
            if (itemList.get(j).contains("SALE") & quantityList.get(j) >= 5) {
                currentPriceList.set(j, currentPriceList.get(j) - currentPriceList.get(j) * 0.1);
                isPromotion = true;
            }
            result.append("$ ").append(String.format("%s\n", new DecimalFormat(".##").format(quantityList.get(j) * currentPriceList.get(j))));
            total += quantityList.get(j) * currentPriceList.get(j);
        }
        return result.toString();
    }

    public int[] splitAndParse(String idAndQuantity) {
        String[] idAndQTY;
        if (idAndQuantity.endsWith(".txt")) {
            idAndQTY = readFromFile(idAndQuantity).split("[ -]");
        } else idAndQTY = idAndQuantity.split("[ -]");
        int[] items = new int[idAndQTY.length];
        for (int i = 0; i < idAndQTY.length; i++) {
            items[i] = Integer.parseInt(idAndQTY[i]);
        }
        return items;
    }

    public void filLists(String idAndQuantity) {
        int[] items = splitAndParse(idAndQuantity);
        item.fill();
        for (int i = 0; i < items.length; i++) {
            if (i % 2 == 0) {
                if (items[i] > item.getItemList().size()) {
                    throw new NotFoundException("Товара с id: " + items[i] + " или файла не существует");
                }
                itemList.add(item.getItemList().get(items[i] - 1));
                currentPriceList.add(item.getPriceList().get(items[i] - 1));
            } else quantityList.add(items[i]);
        }
    }

    public String readFromFile(String path) {
        String[] tokens = new String[0];
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                tokens = scanner.nextLine().split(" ");
            }
        } catch (IOException e) {
            throw new NotFoundException("Файл: " + path + " не существует");
        }
        StringBuilder s = new StringBuilder();
        for (String token : tokens) {
            if (token.matches("\\d+-\\d+")) {
                s.append(token).append(" ");
            } else if (token.matches("(card|Card)-\\d+")) {
                s.append(token).append(" ");
            }
        }
        return s.toString();
    }

    public void writeToFile(String str) {
        try {
            FileWriter fileWriter = new FileWriter(OffsetDateTime.now() + ".txt");
            fileWriter.write(str);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}