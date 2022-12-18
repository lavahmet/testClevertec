package org.example;

public class PrintCheckRunner {
    private final String idAndQuantity;
    private String card;
    private final Service check;

    public PrintCheckRunner(String idAndQuantity, String card) {
        this.idAndQuantity = idAndQuantity;
        this.card = card;
        this.check = new Service();
        printReceipt();
    }

    public PrintCheckRunner(String idAndQuantity) {
        this.idAndQuantity = idAndQuantity;
        this.check = new Service();
        printReceipt();
    }


    private void printReceipt() {
        String receipt = "                    CASH RECEIPT                   \n\n" +
                String.format("%-5s %s %15s %12s\n", "QTY", "DESCRIPTION", "PRICE", "TOTAL") +
                String.format("%-5s %s %15s %12s\n", "----", "-----------", "-----", "-----");
        check.filLists(idAndQuantity);
        receipt += check.calculate()+ "\n-----------------------------------------------------\n" + checkForSales() ;
        System.out.print(receipt);
        check.writeToFile(receipt);
    }

    private String checkForSales() {
        String result;
        if (card != null) {
            if (card.endsWith(".txt")) {
                result = check.checkCardFromTxt(card);
            } else {
                result = check.checkCard(card);
            }
        } else
            result = check.CountSales();
        return result;
    }
}
