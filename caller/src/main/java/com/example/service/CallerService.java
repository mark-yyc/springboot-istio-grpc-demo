package com.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url="${callee-service-url}",name="callee")
public interface CallerService {
    @RequestMapping(method= RequestMethod.GET,value="/callee")
    String callMessage();
}
