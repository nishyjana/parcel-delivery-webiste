package com.praveen.demo.banger.Repo;

import com.praveen.demo.banger.Entity.car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin
public interface CarRepo extends JpaRepository
        <car, Integer> {
}
