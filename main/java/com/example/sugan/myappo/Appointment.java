package com.example.sugan.myappo;

/**
 * Created by sugan on 20/07/16.
 */
public class Appointment {
    int _id;
    String name;
    String lastName;
    String gender;
    String street;
    String city;
    String zipCode;
    String country;
    String phone;
    String email;
    String date;
    String time;

    public Appointment(int _id, String name, String lastName, String gender, String street, String city, String zipCode, String country, String phone, String email, String date, String time) {
        this._id = _id;
        this.name = name;
        this.lastName = lastName;
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

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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
}
