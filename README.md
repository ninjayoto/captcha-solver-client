# Captcha solver client application

## Selenium client application
  This is sample code to demonstrate how to write a selenium automation code and get captcha response from server app.
There are four TODOs added in code.

* #### //TODO this is used to change the location of chrome browser opened by selenium
      driver.manage().window().setPosition(new Point(900, 20));
You can remove this line, as it is used only to change the location of chrome browser opened by chrome driver.

* #### //TODO 1) this is the url which you want to automate and having recaptcha on it
      String url = "https://demo.technoscripts.com/captcha/index.html";
Replace this url with your own website url. Also update the subsequent lines based on your website input elements. For e.g. I'm finding fname, lname and subject input boxes from my html page and inserting given inputs in it.

* #### //TODO 2) get site key from source code of target website
      WebElement captchaKeyDivElement = driver.findElement(By.className("g-recaptcha"));
      String siteKey = captchaKeyDivElement.getAttribute("data-sitekey");
These two lines are used to find site key of the website. Google uses this sitekey to identify the website and provide recaptcha.
There are two ways to find site key

  1. Find a link that begins with www.google.com/recaptcha/api2/anchor and find parameter k=6L....  **OR**
  1. Find element with class name `g-recaptcha` and find its `data-sitekey` parameter.

* #### //TODO 3) captcha solver server address. You can use your local IP address where captcha-server is running.
      String captchaServerUrl = "http://captcha-server-by-niranjan.com:9990";
Change this url with your **Captcha solver server app** url. If you're running **Captcha solver server app** on same system where **captcha solver client** is running then replace this url with http://localhost:9990 or http://127.0.0.1:9990.

If you're running **Captcha solver server app** on other system in same network then you can add ip address of that system (Make sure its 9990 port is not blocked by fiirewall).

