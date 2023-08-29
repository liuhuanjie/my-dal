package com.ctrip.demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Mysql8BugTestController {

    @GetMapping("/timeout/npe")
    public String timeoutNpe(@RequestParam(name = "type") String type) {

    }
}
