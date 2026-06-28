package payment;

public class CashPayment implements Payment {

    public boolean pay(double amount) {
        System.out.println("Cash received: ₹" + amount);
        return true;
    }

    public void refund(double amount) {
        System.out.println("Refund in cash: ₹" + amount);
    }
}
