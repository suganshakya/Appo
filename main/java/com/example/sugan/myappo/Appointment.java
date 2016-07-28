package com.example.sugan.myappo;

/**
 * Created by sugan on 20/07/16.
 */
public class Appointment {
    int id;
    String name;
    String surname;
    String gender;
    String street;
    String city;
    String zipCode;
    String country;
    String phone;
    String email;
    String date;
    String time;

    public Appointment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Appointment(int id, String name, String surname, String gender, String street, String city, String zipCode, String country, String phone, String email, String date, String time) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;

        this.country = country;
        this.phone = phone;
        this.email = email;
        this.date = date;
        this.time = time;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Appointment {" +
                "\n ID : " + id +
                "\n Name : " + name +
                "\n Surname : " + surname +
                "\n Gender : " + gender +
                "\n Street : " + street +
                "\n City : " + city +
                "\n ZipCode : " + zipCode +
                "\n Country : " + country +
                "\n Phone : " + phone +
                "\n Email : " + email +
                "\n Date : " + date +
                "\n Time : " + time +
                "\n}";
    }

    public String getValue(int i) {
        switch (i) {
            case 0:
                return String.valueOf(id);
            case 1:
                return name;
            case 2:
                return surname;
            case 3:
                return gender;
            case 4:
                return street;
            case 5:
                return city;
            case 6:
                return zipCode;
            case 7:
                return country;
            case 8:
                return phone;
            case 9:
                return email;
            case 10:
                return date;
            case 11:
                return time;
            default:
                return null;
        }
    }

    public void setValue(int i, String value) {
        switch (i){
            case 0:
                id = Integer.parseInt(value);
                break;
            case 1:
                name = value;
                break;
            case 2:
                surname = value;
                break;
            case 3:
                gender = value;
                break;
            case 4:
                street = value;
                break;
            case 5:
                city = value;
                break;
            case 6:
                zipCode = value;
                break;
            case 7:
                country = value;
                break;
            case 8:
                phone = value;
                break;
            case 9:
                email = value;
                break;
            case 10:
                date = value;
                break;
            case 11:
                time = value;
                break;
            default:
                break;
        }

    }
}
