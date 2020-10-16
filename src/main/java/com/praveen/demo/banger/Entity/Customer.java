package com.praveen.demo.banger.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Data

@Entity
@Getter
@Setter
@ToString
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "customer_ID")
    private int customerID;


    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "Password")
    private String Password;
    @Column(name = "user_status")
    private String user_status = "ACTIVE";

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public Customer() {

    }

    public Customer(int customerID, String username, String email, String password) {
        this.customerID=customerID;
        this.username = username;
        this.email = email;
        this.Password = password;
    }





}
