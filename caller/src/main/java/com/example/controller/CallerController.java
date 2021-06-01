package com.example.controller;

import com.example.service.CallerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallerController {
    @Autowired
    private CallerService callerService;

    @RequestMapping("/call")
    public String call(){
        return callerService.callMessage();
    }
}
