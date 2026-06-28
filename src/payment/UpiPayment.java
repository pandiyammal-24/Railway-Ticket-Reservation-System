package payment;

public class UpiPayment implements Payment {

    public boolean pay(double amount) {
        System.out.println("UPI payment successful: ₹" + amount);
        return true;
    }

    public void refund(double amount) {
        System.out.println("Refund via UPI: ₹" + amount);
    }
}
