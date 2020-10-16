package com.praveen.demo.banger.Controller;

import com.praveen.demo.banger.Entity.*;
import com.praveen.demo.banger.JwtUtil;
import com.praveen.demo.banger.Repo.*;
import com.praveen.demo.banger.Service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET, RequestMethod.DELETE})
public class AppController {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService userDetailService;


    @Autowired
    Bookingrep bookingRep;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    CarRepo carRepo;




    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername()
                            , authenticationRequest.getPassword()));

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(customerRepo.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(customerRepo.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        Customer user = new Customer( signUpRequest.getCustomerId(),signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());







        Customer result = customerRepo.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/customer/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }





    @GetMapping(path="/cars")
    public List<car> getAllCars(){
        return carRepo.findAll();
    }


    @PostMapping(path="/cars")
    public car addCar(@RequestBody car cars){
        carRepo.save(cars);
        return cars;
    }
    @GetMapping(path="/book")
    public List<Bookings> getAllBookings(){

        return bookingRep.findAll();
    }
    @GetMapping(path="/username")
    public String getUser(){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        String username = userDetails.getUsername();
        return username;
    }


    @PostMapping(path="/bookings" )
    public Bookings addBooking(@RequestBody Bookings booking){
        bookingRep.save(booking);
        return booking;
    }

    @RequestMapping(value = "/customer/{username}", method = RequestMethod.GET)
    @CrossOrigin
    public Customer findByUsername(@PathVariable("username") String username) {
        return customerRepo.findByUsername(username);
    }




    @RequestMapping(value = "/booking/accepted", method = RequestMethod.GET)
    @CrossOrigin
    public List<Bookings> findByBookingStatus() {
        return bookingRep.findByStatus("ACCEPTED");
    }


    @RequestMapping(value = "/booked/pending", method = RequestMethod.GET)
    @CrossOrigin
    public List<Bookings> findByBookedStatus() {
        return bookingRep.findByStatus("pending");
    }



    @GetMapping(path="/customers")
    public List<Customer> getAllCustomers(){

        return customerRepo.findAll();
    }


    @PostMapping(path="/customers")
    public Customer addCustomer(@RequestBody Customer customer){
        customerRepo.save(customer);
        return customer;
    }


    @RequestMapping(value = "/booking/{name}", method = RequestMethod.GET)

    @CrossOrigin
    public List<Bookings> findByBookingName(@PathVariable("name") String name) {
        return bookingRep.findAllByName(name);
    }



    @PutMapping("/cars/{id}")
    @ResponseStatus( HttpStatus.OK)
    public car updateCar(@RequestBody car cars, @PathVariable("id") int id) {

        return carRepo.findById(id)
                .map(carss -> {
                    carss.setCarModel(cars.getCarModel());
                    carss.setCarNumber(cars.getCarNumber());

                    carss.setPrice(cars.getPrice());
                    return carRepo.save(carss);
                })
                .orElseGet(() -> {
                    cars.setCarID(id);
                    return carRepo.save(cars);
                });
    }
    @PutMapping("/userStatus/{customerID}")
    @CrossOrigin
    @ResponseStatus( HttpStatus.OK)
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable("customerID") int customerID) {

        return customerRepo.findById(customerID)
                .map(customers-> {
                    customers.setUsername(customer.getUsername());
                    customers.setEmail(customer.getEmail());
                    customers.setPassword(customer.getPassword());
                    customers.setUser_status(customer.getUser_status());





                    return customerRepo.save(customers);
                })
                .orElseGet(() -> {
                    customer.setCustomerID(customerID);
                    return customerRepo.save(customer);
                });
    }
    @PutMapping("/bookings/{bookingID}")
    @CrossOrigin
    @ResponseStatus( HttpStatus.OK)
    public Bookings updateBooking(@RequestBody Bookings booking, @PathVariable("bookingID") int bookingID) {

        return bookingRep.findById(bookingID)
                .map(bookings-> {


                    bookings.setPickup_time(booking.getPickup_time());
                    bookings.setReturn_time(booking.getReturn_time());
                    bookings.setStatus(booking.getStatus());
                    bookings.setBooked_car(booking.getBooked_car());










                    return bookingRep.save(bookings);
                })
                .orElseGet(() -> {
                    booking.setBookingID(bookingID);
                    return bookingRep.save(booking);
                });
    }


    @DeleteMapping("/cars/{carID}")
    @ResponseStatus( HttpStatus.OK)
    public void deleteCar(@PathVariable("carID") int carID)

    {
        carRepo.deleteById(carID);
    }




}