package service;

import model.*;
import payment.Payment;

import java.util.*;

public class BookingManager {

    private static BookingManager instance;
    private static final double FARE = 500;

    private Map<Integer, Ticket> tickets = new HashMap<>();
    private List<Ticket> bookingHistory = new ArrayList<>();
    private int ticketCounter = 1;

    private int passengerCounter = 100;   // starting ID (you can choose any)

    public int generatePassengerId() {
        return passengerCounter++;
    }

    private BookingManager() {}

    public static BookingManager getInstance() {
        if (instance == null)
            instance = new BookingManager();
        return instance;
    }

    public void book(Passenger p, Train train, Payment payment) {

        if (!payment.pay(FARE)) {
            System.out.println("Payment failed.");
            return;
        }

        if (train.getConfirmedCount() < train.getConfirmedLimit()) {
            Integer seatObj = train.allocateConfirmedSeat();
            if (seatObj == null) {
                System.out.println("No confirmed seats available");
                return;
            }
            int seatNumber = seatObj;
            // take first free seat
            train.incrementConfirmed();
            Ticket t = new Ticket(ticketCounter++, p, train, TicketStatus.CONFIRMED, seatNumber);
            tickets.put(t.getTicketId(), t);
            bookingHistory.add(t);
            System.out.println("Ticket Confirmed");
            System.out.println("Current TicketCounter = " + t.getTicketId());
        }
        else if (train.getRacCount() < train.getRacLimit()) {
            train.incrementRac();
            Ticket t = new Ticket(ticketCounter++, p, train, TicketStatus.RAC, train.getRacCount());
            train.addRacTicket(t.getTicketId());
            tickets.put(t.getTicketId(), t);
            bookingHistory.add(t);
            System.out.println("Ticket in RAC");
        }
        else if (train.getWaitingCount() < train.getWaitingLimit()) {
            train.incrementWaiting();
            Ticket t = new Ticket(ticketCounter++, p, train, TicketStatus.WAITING, train.getWaitingCount());
            train.addWaitingTicket(t.getTicketId());
            tickets.put(t.getTicketId(), t);
            bookingHistory.add(t);
            System.out.println("Added to Waiting List");
        }
        else {
            System.out.println("No seats available.");
        }
    }

    public void cancel(int ticketId, Train train, Payment payment) {

        Ticket t = tickets.get(ticketId);

        if (t == null) {
            System.out.println("No ticket found.");
            return;
        }

        payment.refund(FARE);
        TicketStatus status = t.getStatus();

        // -------- CANCEL CONFIRMED --------
        if (status == TicketStatus.CONFIRMED) {

            train.decrementConfirmed();
            train.releaseConfirmedSeat(t.getSeatNumber());
            // Promote RAC → Confirmed
            if (train.hasRacTicket()) {

                int racTicketId = train.pollRacTicket();
                Ticket racTicket = tickets.get(racTicketId);

                Integer seatObj = train.allocateConfirmedSeat();

                if (seatObj != null) {
                    racTicket.setStatus(TicketStatus.CONFIRMED);
                    racTicket.setSeatNumber(seatObj);
                }
                train.decrementRac();
                train.incrementConfirmed();

                System.out.println("RAC ticket " + racTicketId + " moved to CONFIRMED");

                // Promote Waiting → RAC
                if (train.hasWaitingTicket()) {

                    int waitTicketId = train.pollWaitingTicket();
                    Ticket waitTicket = tickets.get(waitTicketId);

                    waitTicket.setStatus(TicketStatus.RAC);

                    train.decrementWaiting();
                    train.incrementRac();

                    train.addRacTicket(waitTicketId);

                    System.out.println("Waiting ticket " + waitTicketId + " moved to RAC");
                }
            }
        }

        // -------- CANCEL RAC --------
        else if (status == TicketStatus.RAC){

            train.decrementRac();
            train.removeRacTicket(ticketId);

            if (train.hasWaitingTicket()) {

                int waitTicketId = train.pollWaitingTicket();
                Ticket waitTicket = tickets.get(waitTicketId);

                waitTicket.setStatus(TicketStatus.RAC);

                train.decrementWaiting();
                train.incrementRac();

                train.addRacTicket(waitTicketId);

                System.out.println("Waiting ticket " + waitTicketId + " moved to RAC");
            }
        }

        // -------- CANCEL WAITING --------
        else {
            train.decrementWaiting();
            train.removeWaitingTicket(ticketId);
        }

        tickets.remove(ticketId);
        t.setStatus(TicketStatus.CANCELLED);
        System.out.println("Ticket Cancelled Successfully");
    }

    public void showTickets() {
        for (Ticket t : tickets.values())
            System.out.println(t);
    }
    public void showAvailability(Train train) {

        int confirmedAvail = train.getConfirmedLimit() - train.getConfirmedCount();
        int racAvail = train.getRacLimit() - train.getRacCount();
        int waitingAvail = train.getWaitingLimit() - train.getWaitingCount();

        System.out.println("\n---- Seat Availability for " + train.getName() + " ----");
        System.out.println("Confirmed Available: " + confirmedAvail);
        System.out.println("RAC Available: " + racAvail);
        System.out.println("Waiting Available: " + waitingAvail);
        System.out.println("--------------------------------------------");
    }
    public void showBookingHistory() {

        System.out.println("\n------ Booking History ------");

        if (bookingHistory.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (Ticket ticket : bookingHistory) {
            System.out.println(ticket);
        }
    }
    public boolean hasAvailability(Train train) {
        return train.getConfirmedCount() < train.getConfirmedLimit()
                || train.getRacCount() < train.getRacLimit()
                || train.getWaitingCount() < train.getWaitingLimit();
    }
}
