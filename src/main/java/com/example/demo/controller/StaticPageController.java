package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticPageController {

    @GetMapping("/iletisim")
    public String iletisimPage(Model model){
        return "iletisim.htm";
    }

    @GetMapping("/hakkimizda")
    public String hakkimizdaPage(Model model){
        return "hakkimizda.htm";
    }
}
