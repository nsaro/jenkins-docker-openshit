package com.example.jenkinsdemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController {

    @GetMapping("/")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }
    @GetMapping("/time")
    public ResponseEntity<String> getTime() {
        return new ResponseEntity<>(new Date().toString(), HttpStatus.OK);
    }
}
