package com.praveen.demo.banger.Repo;


import com.praveen.demo.banger.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin
public interface CustomerRepo extends JpaRepository
        <Customer, Integer> {
    Customer findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);





  //  Customer findByCustomersID(String customerID);
}

