package com.ssw.controller;

import com.ssw.service.RemoteService;
import com.ssw.service.RemoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @Autowired
    RemoteService remoteService;

    @GetMapping("/person")
    public String person(){
        return remoteService.getName();
    }

}
