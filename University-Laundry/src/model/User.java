package model;

public class User {
    private String name;
    private String lastName;
    private String number;
    private String email;

    // Constructors, getters, and setters
    public User(String name, String lastName, String number, String email) {
        this.name = name;
        this.lastName = lastName;
        this.number = number;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}




