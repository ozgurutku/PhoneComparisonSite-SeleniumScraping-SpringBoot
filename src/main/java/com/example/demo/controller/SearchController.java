package com.example.demo.controller;

import com.example.demo.service.CrawlerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@Controller
@RequestMapping("/tk")
@SessionAttributes("id")
public class SearchController {

    @Inject
    private CrawlerService crawlerService;

    public String id;

    @GetMapping("/search")
    public String search(Model model){
        model.addAttribute("phoneDetails",crawlerService.crawlerPhoneDetailsPage(id));
        return "urunler.htm";
    }

    @GetMapping("/redirect")
    public String search1(@RequestParam(value = "id") String id, Model model){
        this.id=id;
        return "redirect:/tk/search";
    }

    @GetMapping("/deneme")
    public String deneme(@RequestParam String userInput, Model model){
        model.addAttribute("previewPhone",crawlerService.crawlerSearchPhone(userInput));
        return "kategori.htm";
    }

    //Todo: Post ??
}
