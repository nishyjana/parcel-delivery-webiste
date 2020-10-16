package com.praveen.demo.banger.Security;

import com.praveen.demo.banger.JwtRequestFilter;
import com.praveen.demo.banger.Service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityClass extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailService myUserDetailService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable()
               .authorizeRequests().antMatchers("/booking/{name}","/booked/pending","/addUtil","/utilities","/booking/{customer}","/uplaodlist","/booked/accepted","/booking/status","/uploadFile","/downloadFile/","/uploadMultipleFiles","/downloadFile/{fileName:.+}","/downloadFile/{fileName:.+}","/customers/{username}","/bookings/{bookingID}","/customer/{username}","/userStatus/{customerID}","/authenticate","/book","/customers","/bookings","/username","/signup","/cars","/h2-console/**").permitAll().
               anyRequest().authenticated()
       .anyRequest().authenticated().and().sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
       http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        http.headers().frameOptions().disable();
    }
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
