# 🚆 Railway Ticket Reservation System

A console-based **Railway Ticket Reservation System** developed in **Java** using Object-Oriented Programming (OOP), SOLID principles, Java Collections Framework, and Design Patterns. The application simulates a real-world railway reservation system by supporting booking, cancellation, dynamic seat allocation, RAC, waiting list management, and multiple payment methods.

---

## 📌 Features

* Book railway tickets
* Cancel booked tickets
* Dynamic seat allocation
* Automatic RAC → Confirmed promotion
* Automatic Waiting → RAC promotion
* Multiple train support
* View active tickets
* View booking history
* Check seat availability
* Multiple payment options (UPI, Card, Cash)

---

## 🛠 Technologies Used

* Java 17
* IntelliJ IDEA
* Java Collections Framework
* Git & GitHub

---

## 💡 OOP Concepts Implemented

* Class & Object
* Encapsulation
* Abstraction
* Inheritance
* Polymorphism

---

## 📐 SOLID Principles

* **S** – Single Responsibility Principle
* **O** – Open/Closed Principle
* **L** – Liskov Substitution Principle
* **I** – Interface Segregation Principle
* **D** – Dependency Inversion Principle

---

## 🎯 Design Patterns Used

### Singleton Pattern

* `BookingManager` is implemented as a Singleton to ensure only one booking manager instance exists throughout the application.

### Strategy Pattern

* Payment processing is implemented using the Strategy Pattern.
* Different payment methods:

  * UPI Payment
  * Card Payment
  * Cash Payment

---

## 📚 Java Collections Used

| Collection         | Purpose                                               |
| ------------------ | ----------------------------------------------------- |
| HashMap            | Store and manage active tickets                       |
| Queue (LinkedList) | Manage Confirmed, RAC, and Waiting list in FIFO order |
| ArrayList          | Maintain booking history                              |

---

## 📂 Project Structure

```
src
│
├── main
│   └── Main.java
│
├── model
│   ├── Passenger.java
│   ├── Ticket.java
│   ├── TicketStatus.java
│   └── Train.java
│
├── payment
│   ├── Payment.java
│   ├── UpiPayment.java
│   ├── CardPayment.java
│   └── CashPayment.java
│
└── service
    └── BookingManager.java
```

---

## ⚙️ Reservation Workflow

1. Passenger enters details.
2. Select a train.
3. System checks seat availability.
4. Passenger selects a payment method.
5. Ticket is booked as:

   * Confirmed
   * RAC
   * Waiting
6. During cancellation:

   * RAC ticket is promoted to Confirmed.
   * Waiting ticket is promoted to RAC automatically.

---

## ▶️ How to Run

1. Clone the repository.

```
git clone https://github.com/your-username/Railway-Ticket-Reservation-System.git
```

2. Open the project in IntelliJ IDEA.

3. Run:

```
Main.java
```

---

## 📷 Sample Menu

```
1. Book Ticket
2. Cancel Ticket
3. Show Tickets
4. Seat Availability
5. Booking History
6. Exit
```

---

## 🚀 Future Enhancements

* Database integration (MySQL)
* Spring Boot REST API
* User Authentication
* Admin Dashboard
* Online Payment Gateway
* Seat Preference Allocation
* File-based data persistence

---

## 👩‍💻 Author

**Pandiyammal A**

If you found this project useful, feel free to ⭐ the repository.
