package com.example.demo.controller;

import com.example.demo.service.CrawlerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@Controller
@RequestMapping("/tk")
@SessionAttributes("id")
public class SearchController {

    @Inject
    private CrawlerServiceImpl crawlerServiceImpl;

    public String id;

    @GetMapping("/search")
    public String search(Model model){
        model.addAttribute("phoneDetails", crawlerServiceImpl.crawlerPhoneDetailsPage(id));
        return "urunler.htm";
    }

    @GetMapping("/redirect")
    public String search1(@RequestParam(value = "id") String id, Model model){
        this.id=id;
        return "redirect:/tk/search";
    }

    @GetMapping("/searchInput")
    public String searchInput(@RequestParam String userInput, Model model){
        model.addAttribute("previewPhone", crawlerServiceImpl.crawlerSearchPhone(userInput));
        return "kategori.htm";
    }

    //Todo: Post ??
}
