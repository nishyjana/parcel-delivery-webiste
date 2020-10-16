package com.praveen.demo.banger.Controller;

import lombok.AllArgsConstructor;

import javax.validation.constraints.*;

@AllArgsConstructor
public class SignUpRequest {

    private int customerId;

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String password;



    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
