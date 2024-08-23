package org.example;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.io.FileHandler;
//import org.testng.*;
//import org.testng.Assert;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.io.FileHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.io.File;
import java.util.Set;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.io.IOException;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class LaunchBrowser {
    private static Object imgUrl;

    public static void main(String[] args) throws IOException {
        // Create a Scanner object to read input from the console
        //Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter their name
        //System.out.print("Please enter your name: ");
        //String userName = scanner.nextLine();

        // Display a greeting message
        //System.out.println("Hello, " + userName + "! Opening the browser...");

        JFrame frame = new JFrame("Selenium Test");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Create a panel
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        private static final String USER_AGENT = "Mozilla/5.0";


        // Display the frame
        frame.setVisible(true);
    }
    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Create a label for the user name
        JLabel userLabel = new JLabel("User Name:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        // Create a text field for the user to enter their name
        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        // Create a button to submit the name
        JButton loginButton = new JButton("Submit");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        // Add action listener to the button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userText.getText();
                //JOptionPane.showMessageDialog(panel, "Hello, " + userName + "! Opening the browser...");

                System.out.printf("Sending Request!");

                String url = "https://schoolflow360.com/captcha.php"; //https://jsonplaceholder.typicode.com/posts"; // Example API endpoint
                String macAddress = getMacAddress();

                if (macAddress != null) {
//                    sendPostRequestAsync(url, macAddress).thenAccept(response -> {
//                        if (response != null) {
//                            System.out.println("Response from the server: " + response);
//                        } else {
//                            System.out.println("Failed to get a response.");
//                        }
//                    });
                    try {
                        // Send the POST request and wait for the response
                        String response = sendPostRequestAsync(url, macAddress).get(); // Blocking call

                        if (response != null) {
                            //JOptionPane.showMessageDialog(panel, "Response from the server: " + response);

//                            JSONObject jsonObject = new JSONObject(jsonString);
//                            String status = jsonObject.getString("status");
                            if(response.equals("success")) {
                                //JOptionPane.showMessageDialog(panel, "Success");
                            } else {
                                //JOptionPane.showMessageDialog(panel, "Fail");
                            }

//                            Person person = gson.fromJson(response, Person.class);
//                            JOptionPane.showMessageDialog(panel, person.getStatus());

                            // Proceed with the rest of your code after a successful response
                            System.out.println("Proceeding with the rest of the code...");
                        } else {
                            System.out.println("Failed to get a response.");
                        }
                    } catch (InterruptedException | ExecutionException e1) {
                        e1.printStackTrace();
                    }

                    //JOptionPane.showMessageDialog(panel, "POST request has been sent asynchronously with MAC address: " + macAddress);
                } else {
                    JOptionPane.showMessageDialog(panel, "Failed to retrieve MAC address.");
                }


                //JOptionPane.showMessageDialog(panel, "Request has been sent asynchronously!");
        
        // to see how IntelliJ IDEA suggests fixing it.
//                JOptionPane.showMessageDialog(panel, "Hello and welcome!");
                //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text

        
        System.setProperty("webdriver.chrome.driver", "D:\\Zeeshan\\InteliJIdea_Projects\\launchBrowser\\src\\main\\resources\\chrome.exe");
        //String driverPath = LaunchBrowser.class.getResource("/chromedriver-win64/chromedriver.exe").getPath();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("D:/Zeeshan/InteliJIdea_Projects/launchBrowser/target/launchBrowser-1.0-SNAPSHOT.exe");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        //options.setBinary("D:/Zeeshan/InteliJIdea_Projects/launchBrowser.exe");
        WebDriver driver = new ChromeDriver();
        try {

            driver.get("https://blsitalypakistan.com/account/login");
            driver.manage().window().maximize();
            driver.findElement(By.cssSelector("#form1>div:nth-child(3)>input")).sendKeys("test@gmail.com");
            driver.findElement(By.name("login_password")).sendKeys("Test@123");
            WebElement imageElement = driver.findElement(By.xpath("//*[@id=\"Imageid\"]"));
            // Open the image in a new tab
            String imageUrl = imageElement.getAttribute("src");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.open('" + imageUrl + "', '_blank');");
            // Switch to the new tab
            //String originalTab = driver.getWindowHandle();
            String mainWindowHandle = driver.getWindowHandle();
            Set<String> allWindowHandles = driver.getWindowHandles();
            for (String windowHandle : allWindowHandles) {
                if (!windowHandle.equals(mainWindowHandle)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }
            // Capture the screenshot of the element
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File imageFile = new File("D:\\Zeeshan\\InteliJIdea_Projects\\launchBrowser\\src\\main\\resources\\captured-image.png");
            FileHandler.copy(screenshot, imageFile);
            // Initialize Tesseract instance
            ITesseract tesseract = new Tesseract();

            // Set the path to the Tesseract executable
            tesseract.setDatapath("D:\\Zeeshan\\InteliJIdea_Projects\\launchBrowser\\src\\main\\resources\\Tess4J\\tessdata"); // Update with your Tesseract path
            try {
                String result = tesseract.doOCR(imageFile);
                System.out.println("Extracted Text: " + result);
                driver.close(); // Closes the current window
                driver.switchTo().window(mainWindowHandle);
                WebElement cap = driver.findElement(By.name("captcha_code"));
                Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                wait.until(ExpectedConditions.elementToBeClickable(cap));
                cap.sendKeys(result);

                driver.findElement(By.name("submitLogin")).click();
                //SoftAssert.assertEquals("Incorrect code !!!", expected, "error message");

            } catch (TesseractException e) {
                throw new RuntimeException(e);
            }


        } finally {

        }

    }
}