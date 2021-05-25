package com.example.demo.util;

public class SearchUtil {

    public static String editInput(String userInput){

        String aramaUrl = "https://www.cimri.com/cep-telefonlari?q=";

        //Todo: otomatik olarak normalize ediyor galiba bu işlemlere gerek kalmayabilir
        userInput = userInput.trim();

        userInput = userInput.replaceAll("\\s+", "+");

        aramaUrl = aramaUrl + userInput;

        return aramaUrl;
    }
}

