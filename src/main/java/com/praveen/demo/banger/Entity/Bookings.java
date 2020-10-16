package com.praveen.demo.banger.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "book")
public class Bookings {
    @Id
    @GeneratedValue
    private int bookingID;

    @Column(name = "pickup_time")
    private Date pickup_time;

    @Column(name = "return_time")
    private Date return_time;

    @Column(name = "booked_car")
    private String booked_car;
    @Column(name = "name")
    private String name ;

    @Column(name = "status")
    private String status = "pending";

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public Date getPickup_time() {
        return pickup_time;
    }

    public void setPickup_time(Date pickup_time) {
        this.pickup_time = pickup_time;
    }

    public Date getReturn_time() {
        return return_time;
    }

    public void setReturn_time(Date return_time) {
        this.return_time = return_time;
    }

    public String getBooked_car() {
        return booked_car;
    }

    public void setBooked_car(String booked_car) {
        this.booked_car = booked_car;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
