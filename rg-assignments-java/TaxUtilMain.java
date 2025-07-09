package Core_Java;

class TaxUtil {

    public double calculateTax(double amount, double rate) {
        return amount * rate;
    }
}

public class TaxUtilMain {
    public static void main(String[] args) {
        TaxUtil taxUtil = new TaxUtil();
        double amount = 100.0;
        double rate = 0.15;
        double tax = taxUtil.calculateTax(amount, rate);
        System.out.println("Tax: " + tax);  // Tax: 15.0
    }
}