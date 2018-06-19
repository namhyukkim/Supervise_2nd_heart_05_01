package com.example.user.supervise_2nd_heart.user;

public class User {


    String userCustomer;
    String userMk;
    String temp;
    String ampere;
    String voltage;
    String watt;
    String atmospheric;

    public User(String userCustomer, String userMk, String temp, String ampere, String voltage, String watt, String atmospheric) {
        this.userCustomer = userCustomer;
        this.userMk = userMk;
        this.temp = temp;
        this.ampere = ampere;
        this.voltage = voltage;
        this.watt = watt;
        this.atmospheric = atmospheric;
    }

    public String getUserCustomer() {
        return userCustomer;
    }

    public void setUserCustomer(String userCustomer) {
        this.userCustomer = userCustomer;
    }

    public String getUserMk() {
        return userMk;
    }

    public void setUserMk(String userMk) {
        this.userMk = userMk;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getAmpere() {
        return ampere;
    }

    public void setAmpere(String ampere) {
        this.ampere = ampere;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getWatt() {
        return watt;
    }

    public void setWatt(String watt) {
        this.watt = watt;
    }

    public String getAtmospheric() {
        return atmospheric;
    }

    public void setAtmospheric(String atmospheric) {
        this.atmospheric = atmospheric;
    }
}
