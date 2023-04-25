package com.lahutina.controller;

import com.lahutina.model.Date;
import com.lahutina.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    private final DateService dateService;

    @Autowired
    public Controller(DateService dateService) {
        this.dateService = dateService;
    }

    @GetMapping("/year-pass")
    public ResponseEntity<Double> getPercentageFromCurrentDay() {
        return new ResponseEntity<>(dateService.getDaysFromCurrentDay(), HttpStatus.OK);
    }

    @PostMapping("/year-pass")
    public ResponseEntity<Double> getPercentageDaysFromSpecificDay(@RequestBody Date date) {
        double percDaysPassed = dateService.getDaysFromSpecificDay(date);

        if (percDaysPassed != -1)
            return new ResponseEntity<>(percDaysPassed, HttpStatus.OK);
        else
            return new ResponseEntity<>(-1.0, HttpStatus.BAD_REQUEST);
    }
}
