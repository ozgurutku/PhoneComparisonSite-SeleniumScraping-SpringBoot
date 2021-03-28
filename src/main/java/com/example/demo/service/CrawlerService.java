package com.example.demo.service;

import com.example.demo.model.Phone;
import com.example.demo.model.PreviewPhone;
import com.example.demo.model.util.OtherSites;
import com.example.demo.util.SearchUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CrawlerService {

    // Telefon aramasını yapan metod
    public List<PreviewPhone> crawlerSearchPhone(String userInput){

        WebDriver driver = new FirefoxDriver();

        driver.manage().window().maximize();

        String searchUrl = SearchUtil.editInput(userInput);
        driver.get(searchUrl);

        // findElement için : /html/body/div[1]/div/div[4]/div/div[1]/div[2]/div[2]/div[3]           //*[@id="cimri-product"]
        //Todo: gerek varmı ? !!!incele
        List<WebElement> divAllPhone = driver.findElement(By.cssSelector("#main_container > div > div.s1cegxbo-0.envLfj > div.s1a29zcm-8.ilklTg > div.s1a29zcm-9.fpThqT > div.s1cegxbo-1.cACjAF")).findElements(By.cssSelector("#cimri-product"));

        // findElement için : //*[@id="cimri-product"]
        //List<WebElement> mainVersions = divAllPhone.findElements(By.cssSelector("#cimri-product"));

        List<PreviewPhone> previewPhones = new ArrayList<>();
        for(WebElement div: divAllPhone) {
            for(WebElement article: div.findElements(By.tagName("article"))) {
                List<WebElement> divPhoneImageLink = article.findElements(By.cssSelector("#cimri-product > article > div.image-wrapper"));
                String phoneImageLink = "https:"+divPhoneImageLink.get(0).findElement(By.tagName("img")).getAttribute("data-src");
                List<WebElement> h3 = article.findElements(By.tagName("h3"));
                List<WebElement> a = article.findElements(By.tagName("a"));
                String phoneLinks = a.get(0).getAttribute("href");
                if (!h3.isEmpty()) {
                    String phoneName = h3.get(0).getText();
                    PreviewPhone previewPhone = new PreviewPhone();
                    previewPhone.setName(phoneName);
                    previewPhone.setPhoneUrl(phoneLinks);
                    previewPhone.setImageUrl(phoneImageLink);
                    previewPhones.add(previewPhone);
                }
            }
        }
        driver.quit();
        return previewPhones;
    }

    // telefon özelliklerini ceken metod
    public Phone crawlerPhoneDetailsPage(String url){

        WebDriver driver = new FirefoxDriver();

        driver.manage().window().maximize();

        // Örnegin: https://www.cimri.com/cep-telefonlari/en-ucuz-xiaomi-redmi-note-8-64gb-4gb-ram-6-3-inc-48mp-akilli-cep-telefonu-mavi-fiyatlari,330907466
        driver.get(url);

        //Telefonun detaylı özellikleri

        // findElements icin : /html/body/div[1]/div[1]/div[4]/div/div[1]/div[3]/div[2]/div/div
        // css selector icin : #main_container > div > div.s98wa6g-0.feTYBN > div.s98wa6g-3.iCxKVJ > div:nth-child(2) > div > div
        WebElement divPhoneProperty = driver.findElement(By.cssSelector("#main_container > div > div.s98wa6g-0.feTYBN > div.s98wa6g-3.iCxKVJ > div:nth-child(2) > div > div"));

        List<WebElement> ulPhoneProperty = divPhoneProperty.findElements(By.tagName("ul"));

        Phone phone = new Phone();
        Map<String, String> features = new HashMap<>();

        for(WebElement ul: ulPhoneProperty) {
            //Başlıklar
            List<WebElement> phoneTitle = ul.findElements(By.tagName("li"));
            System.out.println("BAŞLIK: " + phoneTitle.get(0).getText());
            System.out.println("*************************");
            for(WebElement li: ul.findElements(By.tagName("li"))) {
                //Başlıkların altındaki özellikler
                List<WebElement> phonePropety = li.findElements(By.tagName("span"));
                if (!phonePropety.isEmpty()) {
                    //WebElement kullanmaya gerek yok yukarıdaki gibi kullanabilirsin veya Stringe eşitle
                    WebElement phoneFeatureName = phonePropety.get(0);
                    WebElement phoneFeatureValue = phonePropety.get(1);
                    System.out.println("öz1   : " + phoneFeatureName.getText());
                    System.out.println("öz2    : " + phoneFeatureValue.getText());
                    System.out.println("------------------------------");
                    features.put(phoneFeatureName.getText(),phoneFeatureValue.getText());
                    phone.getFeatures().put(phoneTitle.get(0).getText(),features);
                }
            }
        }

        //Telefonun Diger web sitelerdeki fiyatlarla karşılaştırılması için linkler,resim vs

        //find element için : /html/body/div[1]/div[1]/div[4]/div/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/div/table
        WebElement divPhoneComparasion = driver.findElement(By.cssSelector("#main_container > div > div.s98wa6g-0.feTYBN > div.s1a29zcm-8.ilklTg > div.s1a29zcm-9.fpThqT > div.s98wa6g-9.cqirRS > div.s98wa6g-15.erkGpU > div:nth-child(1) > div > table"));
        WebElement tbodyPhoneComparasion = divPhoneComparasion.findElement(By.tagName("tbody"));
        System.out.println("/////////////////////////////////////////////////////////////");
        for(WebElement tr: tbodyPhoneComparasion.findElements(By.tagName("tr"))) {
            List<WebElement> td = tr.findElements(By.tagName("td"));
            // size ile reklama karşı önlem alıyoruz
            if (!td.isEmpty() && !(td.size()<4)) {
                OtherSites otherSites = new OtherSites();
                WebElement a = td.get(0).findElement(By.tagName("a"));
                WebElement img = a.findElement(By.tagName("img"));

                //WebElement kullanmaya gerek yok yukarıdaki gibi kullanabilirsin veya Stringe eşitle
                WebElement phoneDescription = td.get(1);
                WebElement cargo = td.get(2);
                WebElement phonePrice = td.get(3);
                System.out.println("Site linki    : " +a.getAttribute("href"));
                System.out.println("Resimin linki    : " +img.getAttribute("src"));
                System.out.println("Açıklama    : " + phoneDescription.getText());
                System.out.println("Kargo    : " + cargo.getText());
                System.out.println("Fiyat    : " + phonePrice.getText());
                System.out.println("------------------------------");
                otherSites.setSiteLink(a.getAttribute("href"));
                otherSites.setImageLink(img.getAttribute("src"));
                otherSites.setDescription(phoneDescription.getText());
                otherSites.setCargo(cargo.getText());
                otherSites.setPrice(phonePrice.getText());
                phone.getOtherSites().add(otherSites);
            }
        }

        //Telefonun ismi ve Telefonun acıklaması

        // findElement için : /html/body/div[1]/div[1]/div[4]/div/div[1]/div[2]/div[2]/div[1]
        WebElement divPhoneName = driver.findElement(By.cssSelector("#main_container > div > div.s98wa6g-0.feTYBN > div.s1a29zcm-8.ilklTg > div.s1a29zcm-9.fpThqT > div.s1wytv2f-0.nGzWR"));
        String phoneName = divPhoneName.findElement(By.tagName("h1")).getText();
        phone.setName(phoneName);
        System.out.println("Telefonun ismi : "+phoneName);
        List<WebElement> phoneDescription = divPhoneName.findElement(By.tagName("ul")).findElements(By.tagName("li"));
        //Todo: neden sonuncusu gelmiyor anlamadım
        String description ="";
        for(int i=0; i<phoneDescription.size() ;i++){
            System.out.println("acıklama: "+phoneDescription.get(i).getText());
            description = description + phoneDescription.get(i).getText();
        }
        phone.setDescription(description);

        //Telefonun resimi için link

        //findElement için	/html/body/div[1]/div[1]/div[4]/div/div[1]/div[2]/div[1]/div[2]
        WebElement divPhoneImage = driver.findElement(By.cssSelector("#main_container > div > div.s98wa6g-0.feTYBN > div.s1a29zcm-8.ilklTg > div.s1a29zcm-9.iZWLxS > div.s98wa6g-1.fyONWW"));
        String phoneImage = divPhoneImage.findElement(By.tagName("ul")).findElement(By.tagName("li")).findElement(By.tagName("img")).getAttribute("src");
        System.out.println("resim linki : "+phoneImage);
        phone.setPicture(phoneImage);
        driver.quit();
        return phone;
    }

}
