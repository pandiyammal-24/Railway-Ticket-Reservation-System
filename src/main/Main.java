package main;

import model.*;
import payment.*;
import service.BookingManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BookingManager manager = BookingManager.getInstance();

        // Two trains
        Train train1 = new Train(1, "Chennai Express", 5, 3, 2);
        Train train2 = new Train(2, "Madurai Express", 4, 2, 2);

        while (true) {
            System.out.println("\n1.Book 2.Cancel 3.Show Tickets 4.Availability 5.Booking History 6.Exit");
            int choice = sc.nextInt();

            if (choice == 1) {

                int id = manager.generatePassengerId();
                System.out.println("Generated Passenger ID: " + id);
                sc.nextLine();   // clear buffer

                System.out.print("Name: ");
                String name = sc.nextLine();

                System.out.print("Age: ");
                int age = sc.nextInt(); sc.nextLine();

                System.out.print("Gender: ");
                String gender = sc.nextLine();

                System.out.print("Berth Pref: ");
                String berth = sc.nextLine();

                Passenger p = new Passenger(id, name, age, gender, berth);

                // -------- Train selection ----------
                System.out.println("Select Train:");
                System.out.println("1. Chennai Express");
                System.out.println("2. Madurai Express");
                int trainChoice = sc.nextInt();

                Train selectedTrain = (trainChoice == 1) ? train1 : train2;

                if (!manager.hasAvailability(selectedTrain)) {
                    System.out.println("No seats available.");
                    continue;
                }

                // -------- Payment selection ----------
                System.out.println("Select Payment Mode:");
                System.out.println("1.UPI  2.Cash  3.Card");
                int payChoice = sc.nextInt();

                Payment payment;
                if (payChoice == 1) payment = new UpiPayment();
                else if (payChoice == 2) payment = new CashPayment();
                else payment = new CardPayment();

                // -------- Booking ----------
                manager.book(p, selectedTrain, payment);
            }
            else if (choice == 2) {

                System.out.print("Enter Ticket ID to Cancel: ");
                int id = sc.nextInt();   // ✔ existing ID, NOT generated

                System.out.println("Select Train(1/2):");
                int trainChoice = sc.nextInt();

                Train selectedTrain = (trainChoice == 1) ? train1 : train2;

                System.out.println("Select Refund Mode:");
                System.out.println("1.UPI  2.Cash  3.Card");
                int payChoice = sc.nextInt();

                Payment payment;
                if (payChoice == 1) payment = new UpiPayment();
                else if (payChoice == 2) payment = new CashPayment();
                else payment = new CardPayment();

                manager.cancel(id, selectedTrain, payment);
            }

            else if (choice == 3) {
                manager.showTickets();
            }
            else if (choice == 4) {

                System.out.println("Select Train:");
                System.out.println("1. Chennai Express");
                System.out.println("2. Madurai Express");
                int trainChoice = sc.nextInt();

                Train selectedTrain = (trainChoice == 1) ? train1 : train2;

                manager.showAvailability(selectedTrain);
            }
            else if(choice==5){
                manager.showBookingHistory();
            }
            else if (choice == 6) {
                System.out.println("System reset successful. Exiting...");
                break;
            }
            else {
                System.out.println("Invalid choice. Try again.");
            }

        }
    }
}
