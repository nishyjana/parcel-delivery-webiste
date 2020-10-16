package com.praveen.demo.banger.Repo;

import com.praveen.demo.banger.Entity.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Bookingrep extends JpaRepository<Bookings, Integer> {





    List<Bookings> findByStatus(String accpted);


    List<Bookings> findAllByName(String name);
}
