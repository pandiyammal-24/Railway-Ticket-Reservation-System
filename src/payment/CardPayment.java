package payment;

public class CardPayment implements Payment {

    public boolean pay(double amount) {
        System.out.println("Card payment successful: ₹" + amount);
        return true;
    }

    public void refund(double amount) {
        System.out.println("Refund to card: ₹" + amount);
    }
}
