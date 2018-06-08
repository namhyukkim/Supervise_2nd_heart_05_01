package com.example.user.supervise_2nd_heart.user;

public class User {

    String userID;
    String userPassword;
    String userCustomer;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserCustomer() {
        return userCustomer;
    }

    public void setUserCustomer(String userCustomer) {
        this.userCustomer = userCustomer;
    }

    public User(String userID, String userPassword, String userCustomer) {
        this.userID = userID;
        this.userPassword = userPassword;
        this.userCustomer = userCustomer;
    }
}
