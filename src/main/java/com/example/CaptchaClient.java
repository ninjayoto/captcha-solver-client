package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaptchaClient {

    public static void main(String[] args) {
        List<Input> list = new ArrayList<>();
        list.add(new Input("Albert", "Einstein", "Sample subject"));
        list.add(new Input("Marie", "Curie", "Sample subject"));
        list.add(new Input("Isaac", "Newton", "Sample subject"));
        list.add(new Input("Charles", "Darwin", "Sample subject"));
        list.add(new Input("Nikola", "Tesla", "Sample subject"));
        for (Input input :
                list) {
            try {
                testBySeleniumMySite(input.fname, input.lname, input.subject);
                Thread.sleep(5 * 1000);
            } catch (Exception ex) {
                System.err.println("Ignoring " + ex);
            }
        }
    }

    public static void testBySeleniumMySite(String name, String lastName, String subject) {
        System.setProperty("webdriver.chrome.driver", "D:/SOFTWARES/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        //TODO this is used to change the location of chrome browser opened by selenium
        driver.manage().window().setPosition(new Point(900, 20));
        //TODO 1) this is the url which you want to automate and having recaptcha on it
        String url = "https://demo.technoscripts.com/captcha/index.html";
        driver.get(url);
        WebElement nameElement = driver.findElement(By.id("fname"));
        nameElement.clear();
        nameElement.sendKeys(name);
        WebElement lastNameElement = driver.findElement(By.id("lname"));
        lastNameElement.clear();
        lastNameElement.sendKeys(lastName);
        WebElement subjectElement = driver.findElement(By.id("subject"));
        subjectElement.clear();
        subjectElement.sendKeys(subject);

        WebElement captchaKeyDivElement = driver.findElement(By.className("g-recaptcha"));
        //TODO 3) get site key from source code of target website
        String siteKey = captchaKeyDivElement.getAttribute("data-sitekey");
        System.out.println("Site Key : " + siteKey);
        String captchaSolution = getCaptchaSolution(siteKey, url);
        //fill that response in text area
        WebElement textArea = driver.findElementByCssSelector("#g-recaptcha-response");
        String js = "arguments[0].style.height='auto'; arguments[0].style.display='block';";
        ((JavascriptExecutor) driver).executeScript(js, textArea);
        textArea.clear();
        textArea.sendKeys(captchaSolution);
        //submit the page
        nameElement.submit();
    }

    private static String getCaptchaSolution(String siteKey, String url) {
        try {
            //TODO 2) captcha solver server address. You can use your local IP address where captcha-server is running.
            String captchaServerUrl = "http://captcha-server-by-niranjan.com:9990";
            RestTemplate template = new RestTemplate();
            Map<String, String> map = new HashMap<>();
            map.put("host", url);
            map.put("siteKey", siteKey);
            ResponseEntity<CaptchaResponse> response = template.postForEntity(captchaServerUrl + "/solve", map, CaptchaResponse.class);
            if(response.getStatusCode().is2xxSuccessful()) {
                CaptchaResponse captchaResponse = response.getBody();
                int id = captchaResponse.getRequestId();
                while(true) {
                    ResponseEntity<CaptchaResponse> solutionResponse = template.getForEntity(captchaServerUrl + "/solution/" + id, CaptchaResponse.class);
                    if(solutionResponse.getStatusCode().is2xxSuccessful()) {
                        String solution = solutionResponse.getBody().getSolution();
                        if(solution != null && !solution.isEmpty()) {
                            return solution;
                        }
                    }
                    Thread.sleep(5 * 1000);
                }
            } else {
                System.err.println("Error " + response);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
        return null;
    }

}
