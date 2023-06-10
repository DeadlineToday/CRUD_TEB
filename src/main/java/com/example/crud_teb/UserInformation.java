package com.example.crud_teb;

public class UserInformation {

    private int id;
    private String name, surname, pesel;
    private double salary;

    public UserInformation(int id, String name, String surname, String pesel, double salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
