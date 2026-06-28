package model;

public class Passenger {

    private int id;
    private String name;
    private int age;
    private String gender;
    private String berthPreference;

    public Passenger(int id, String name, int age, String gender, String berthPreference) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.berthPreference = berthPreference;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Passenger ID: " + id +
                " | Name: " + name +
                " | Age: " + age +
                " | Gender: " + gender +
                " | Pref: " + berthPreference;
    }
}
