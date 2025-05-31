package com.crispyecho.springkarate.controller;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RestController
@RequestMapping("/hello")
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @GetMapping
        public String hello() {
            return "Hello World!";
    }
    @GetMapping("/{type}")
    public String helloBranch(@PathVariable String type){
        log.info("Received request for /hello/{}", type);
        if ("formal".equalsIgnoreCase(type)) {
            return "Hello World!";
        } else {
            return "Hey there!";
        }
    }

    @GetMapping("/status/{code}")
    public ResponseEntity<String> customStatus(@PathVariable int code) {
        if (code == 200) {
            return ResponseEntity.ok("Everything is fine");
        } else if (code == 404) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @PostMapping("/echo")
    public ResponseEntity<String> echo(@RequestBody Map<String, String> payload) {
        String msg = payload.getOrDefault("message", "No message");
        return ResponseEntity.ok("Echo: " + msg);
    }  

}
