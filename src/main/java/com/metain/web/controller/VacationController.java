package com.metain.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VacationController {
    @RequestMapping("/vacationlist")
    public String vacationList() {

        return "vacationlist";
    }
}