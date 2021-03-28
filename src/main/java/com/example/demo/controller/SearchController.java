package com.example.demo.controller;

import com.example.demo.service.CrawlerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@Controller
@RequestMapping("/tk")
public class SearchController {

    @Inject
    private CrawlerService crawlerService;

    @ModelAttribute("userInput")
    public String addAttribute(){
        return new String();
    }

    @GetMapping("/search")
    public String search(@ModelAttribute String userInput, Model model){
        model.addAttribute("previewPhone",crawlerService.crawlerSearchPhone(userInput));
        return "a.html";
    }

    //Todo: Post ??
}
