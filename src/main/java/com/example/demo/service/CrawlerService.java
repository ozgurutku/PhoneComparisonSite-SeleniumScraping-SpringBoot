package com.example.demo.service;

import com.example.demo.model.Phone;
import com.example.demo.model.PreviewPhone;

import java.util.List;

public interface CrawlerService {
    List<PreviewPhone> crawlerSearchPhone(String userInput);
    Phone crawlerPhoneDetailsPage(String url);
}
