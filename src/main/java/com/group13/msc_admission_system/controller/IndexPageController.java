package com.group13.msc_admission_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexPageController {
    @GetMapping("")
    public String ShowIndexPage(){
        return "index";
    }
}
