package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalleeController {

    @RequestMapping(value = "/callee", method = RequestMethod.GET)
    public String receive() {
        return "success";
    }
}
