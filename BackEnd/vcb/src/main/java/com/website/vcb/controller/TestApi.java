package com.website.vcb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApi {
    @GetMapping("/hello")
    String hello() {
        return "hello";
    }
}
