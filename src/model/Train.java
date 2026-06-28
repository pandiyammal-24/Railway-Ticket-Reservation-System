package model;

import java.util.*;

public class Train {

    private int trainId;
    private String name;

    private int confirmedLimit;
    private int racLimit;
    private int waitingLimit;

    private int confirmedCount = 0;
    private int racCount = 0;
    private int waitingCount = 0;

    private Queue<Integer> freeConfirmedSeats = new LinkedList<>();
    private Queue<Integer> racQueue = new LinkedList<>();
    private Queue<Integer> waitingQueue = new LinkedList<>();

    public Train(int id, String name, int c, int r, int w) {
        this.trainId = id;
        this.name = name;
        this.confirmedLimit = c;
        this.racLimit = r;
        this.waitingLimit = w;
        for (int i = 1; i <= confirmedLimit; i++) {
            freeConfirmedSeats.add(i);
        }
    }

    public int getTrainId() { return trainId; }
    public String getName() { return name; }
    public int getConfirmedLimit() { return confirmedLimit; }
    public int getRacLimit() { return racLimit; }
    public int getWaitingLimit() { return waitingLimit; }

    public int getConfirmedCount() { return confirmedCount; }
    public int getRacCount() { return racCount; }
    public int getWaitingCount() { return waitingCount; }
    public void incrementConfirmed() {
        confirmedCount++; }
    public void decrementConfirmed() {
        confirmedCount--; }
    public void incrementRac() {
        racCount++; }
    public void decrementRac() {
        racCount--; }
    public void incrementWaiting() {
        waitingCount++; }
    public void decrementWaiting() {
        waitingCount--; }
    public Integer allocateConfirmedSeat() {
        return freeConfirmedSeats.poll();
    }
    public void releaseConfirmedSeat(int seatNo) {
        freeConfirmedSeats.offer(seatNo);
    }
    public void addRacTicket(int ticketId) {
        racQueue.offer(ticketId); }
    public Integer pollRacTicket() {
        return racQueue.poll(); }
    public boolean hasRacTicket() {
        return !racQueue.isEmpty(); }
    public void removeRacTicket(int ticketId) {
        racQueue.remove(ticketId); }
    public void addWaitingTicket(int ticketId) {
        waitingQueue.offer(ticketId); }
    public Integer pollWaitingTicket() {
        return waitingQueue.poll(); }
    public boolean hasWaitingTicket() {
        return !waitingQueue.isEmpty(); }
    public void removeWaitingTicket(int ticketId) {
        waitingQueue.remove(ticketId); }
}
