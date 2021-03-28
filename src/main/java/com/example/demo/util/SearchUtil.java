package com.example.demo.util;

import com.example.demo.DemoApplication;

import java.util.Scanner;

public class SearchUtil {

    public static String editInput(String userInput){

        String aramaUrl = "https://www.cimri.com/cep-telefonlari?q=";

        //Todo: otomatik olarak normalize ediyor galiba bu işlemlere gerek kalmayabilir
        userInput = userInput.trim();

        userInput = userInput.replaceAll("\\s+", "+");

        aramaUrl = aramaUrl + userInput;

        return aramaUrl;
        //demoApplication.crawlerSearchPhone(aramaUrl);
    }
        //DemoApplication demoApplication = new DemoApplication();
        //demoApplication.crawlerPhoneDetailsPage("https://www.cimri.com/cep-telefonlari/en-ucuz-xiaomi-redmi-note-8-64gb-4gb-ram-6-3-inc-48mp-akilli-cep-telefonu-mavi-fiyatlari,330907466");
}

