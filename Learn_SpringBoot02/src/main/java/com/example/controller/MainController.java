package com.example.controller;

import com.example.entity.Vtuber;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @RequestMapping("/index")
    @ResponseBody
    public String index () {
        return "Fxxk SpringBoot";
    }

    @RequestMapping("/vtuber")
    @ResponseBody
    public Vtuber vtuber () {
        Vtuber vtuber = new Vtuber("Hiiro", "Cat", 14);
        return vtuber;
    }

}
