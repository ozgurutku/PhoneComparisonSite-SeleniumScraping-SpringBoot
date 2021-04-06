package com.example.demo.controller;

import com.example.demo.service.CrawlerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@Controller
@RequestMapping(value = "/tk")
public class HomeController {

    @Inject
    private CrawlerService crawlerService;

    @ModelAttribute("userInput")
    public String addAttribute(){
        return new String();
    }

    @GetMapping()
    public String homePage(Model model){
        //karışık gelmesi için boş string yolluyoruz
        model.addAttribute("previewPhone",crawlerService.crawlerSearchPhone(""));
        return "index.htm";
    }

}
