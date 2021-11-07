package com.example.Covid_Alert.controllers;

import com.example.Covid_Alert.models.Location;
import com.example.Covid_Alert.repositories.LocationRepository;
import com.example.Covid_Alert.util.OnCreateLocationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;

@RestController
public class GetLocationController {
    @Autowired
    LocationRepository locationRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostMapping("/getLocation")
    public String saveLocation(@Validated @ModelAttribute("location")
                                       Location location, BindingResult result) {
        locationRepository.save(location);
        // create/save a location object
        Date date = new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        location.setLocation_date(ts);
        eventPublisher.publishEvent(new OnCreateLocationEvent("/",location));
        return "index";
    }
}
