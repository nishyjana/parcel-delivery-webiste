package com.praveen.demo.banger.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "cars")
public class car {
    @Id
    @GeneratedValue
    private int carID;

    private String carModel;




    private String price;
    private String carNumber;
    private Boolean availabilty;
    private String imgURl;

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Boolean getAvailabilty() {
        return availabilty;
    }

    public void setAvailabilty(Boolean availabilty) {
        this.availabilty = availabilty;
    }

    public String getImgURl() {
        return imgURl;
    }

    public void setImgURl(String imgURl) {
        this.imgURl = imgURl;
    }
}

