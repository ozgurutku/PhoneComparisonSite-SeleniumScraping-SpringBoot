package com.example.demo.controller;

import com.example.demo.service.CrawlerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@Controller
@RequestMapping("/tk")
public class DetailsController {

    @Inject
    private CrawlerService crawlerService;

    @ModelAttribute("phoneLink")
    public String addAttribute(){
        return new String();
    }

    @GetMapping("/details")
    public String details(@ModelAttribute String phoneLink, Model model){
        model.addAttribute("phoneDetails",crawlerService.crawlerPhoneDetailsPage(phoneLink));
        return "a.html";
    }

    //Todo : Post ??
}
