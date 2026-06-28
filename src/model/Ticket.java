package model;

public class Ticket {

    private int ticketId;
    private Passenger passenger;
    private Train train;
    private TicketStatus status;
    private int seatNo;

    public Ticket(int id, Passenger p, Train t, TicketStatus status, int seatNo) {
        this.ticketId = id;
        this.passenger = p;
        this.train = t;
        this.status = status;
        this.seatNo = seatNo;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public int getSeatNumber() {
        return seatNo;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setSeatNumber(int seatNo) {
        this.seatNo = seatNo;
    }

    @Override
    public String toString() {
        return "Ticket ID : " + ticketId +
                "\n" + passenger +
                "\nTrain      : " + train.getName() +
                "\nStatus     : " + status +
                "\nSeat No    : " + seatNo +
                "\n---------------------------------------";
    }
}