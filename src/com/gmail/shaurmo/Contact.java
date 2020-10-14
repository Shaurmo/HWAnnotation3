package com.gmail.shaurmo;

public class Contact {
    @Save
    private String name;

    @Save
    private int number;

    @Save
    private String email;

    private String info;

    public Contact() {
    }

    public Contact(String name, int number, String email, String info) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", email='" + email + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
