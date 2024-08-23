package org.example;
import org.openqa.selenium.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import java.util.Scanner;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.io.FileHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

//import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        // Create a Scanner object to read input from the console
        //Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter their name
        //System.out.print("Please enter your name: ");
        //String userName = scanner.nextLine();

        // Display a greeting message
        //System.out.println("Hello, " + userName + "! Opening the browser...");

        JFrame frame = new JFrame("Selenium Test");
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Create a panel
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        // Display the frame
        frame.setVisible(true);
    }

    private static final String USER_AGENT = "Mozilla/5.0";

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Create a label for the user name
        JLabel userLabel = new JLabel("User Name:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        //Create label for Password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);
        //Create label for for dropdown
        JLabel selectlbl = new JLabel("Select Gender");
        selectlbl.setBounds(10,60,80,50);
        panel.add(selectlbl);

        // Create a text field for the user to enter their name
        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);
        // Create a text fiedl for the user to enter their password
        JTextField PasswordFass= new JPasswordField(20);
        PasswordFass.setBounds(100, 50, 165, 25);
        panel.add(PasswordFass);

        //Create the select box to select the Gender
        JComboBox SelectBox = new JComboBox();
        SelectBox.setBounds(100,80,165,25);
        panel.add(SelectBox);
        SelectBox.insertItemAt("",0);
        SelectBox.insertItemAt("Male",1);
        SelectBox.insertItemAt("Female",2);



        // Create a button to submit the name
        JButton loginButton = new JButton("Submit");
        loginButton.setBounds(100, 200, 80, 25);
        panel.add(loginButton);

        // Add action listener to the button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userText.getText();
                String Password = PasswordFass.getText();

                //JOptionPane.showMessageDialog(panel, "Hello, " + userName + "! Opening the browser...");

                System.out.printf("Sending Request!");
                System.setProperty("webdriver.chrome.driver",
                        "C:\\Users\\DELL\\Downloads\\launchBrowser-1.1\\launchBrowser\\src\\main\\resources\\chromedriver.exe");
                //String driverPath = Main.class.getResource("/chromedriver-win64/chromedriver.exe").getPath();
                //System.setProperty("webdriver.chrome.driver", driverPath);
                // Initialize a new WebDriver instance

                WebDriver driver = new ChromeDriver();

                //
//                    sendPostRequestAsync(url, macAddress).thenAccept(response -> {
//                        if (response != null) {
//                            System.out.println("Response from the server: " + response);
//                        } else {
//                            System.out.println("Failed to get a response.");
//                        }
//                    });
//                    try {
//                        // Send the POST request and wait for the response
//                        String response = sendPostRequestAsync(url, macAddress).get(); // Blocking call
//
//                        if (response != null) {
//                            //JOptionPane.showMessageDialog(panel, "Response from the server: " + response);
//
////                            JSONObject jsonObject = new JSONObject(jsonString);
////                            String status = jsonObject.getString("status");
//                            if(response.equals("success")) {
//                                //JOptionPane.showMessageDialog(panel, "Success");
//                            } else {
//                                //JOptionPane.showMessageDialog(panel, "Fail");
//                            }
//
////                            Person person = gson.fromJson(response, Person.class);
////                            JOptionPane.showMessageDialog(panel, person.getStatus());
//
//                            // Proceed with the rest of your code after a successful response
//                            System.out.println("Proceeding with the rest of the code...");
//                        } else {
//                            System.out.println("Failed to get a response.");
//                        }
//                    } catch (InterruptedException | ExecutionException e1) {
//                        e1.printStackTrace();
//                    }
//
//                    //JOptionPane.showMessageDialog(panel, "POST request has been sent asynchronously with MAC address: " + macAddress);
//                } else {
//                    JOptionPane.showMessageDialog(panel, "Failed to retrieve MAC address.");
//                }


                //JOptionPane.showMessageDialog(panel, "Request has been sent asynchronously!");

                // to see how IntelliJ IDEA suggests fixing it.
//                JOptionPane.showMessageDialog(panel, "Hello and welcome!");
                //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text



                // Open a webpage
                driver.get("https://example.com/account/login");
                driver.manage().window().maximize();
                WebElement userName1 = driver.findElement(By.cssSelector("#form1>div:nth-child(3)>input"));
                userName1.sendKeys(userName);
                WebElement PasswordFass = driver.findElement(By.name("login_password"));
                PasswordFass.sendKeys(Password);

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
                File imageFile = new File("C:\\Users\\DELL\\Downloads\\launchBrowser-1.1\\launchBrowser\\src\\main\\resources\\captured-image.png");
                try {
                    FileHandler.copy(screenshot, imageFile);
                } catch (IOException ignored) {

                }
                // Initialize Tesseract instance
                ITesseract tesseract = new Tesseract();

                 //Set the path to the Tesseract executable
                tesseract.setDatapath("C:\\Users\\DELL\\Downloads\\launchBrowser-1.1\\launchBrowser\\src\\main\\resources\\Tess4J\\tessdata"); // Update with your Tesseract path
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

                } catch (TesseractException e2) {
                    throw new RuntimeException(e2);
                }

                // Print the title of the page
                System.out.println("Page title is: " + driver.getTitle());

                // Close the browser
                //driver.quit();

                for (int i = 1; i <= 5; i++) {
                    //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
                    // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
                    System.out.println("i = " + i);
                }

            }
        });
    }

//    public static CompletableFuture<String> sendPostRequestAsync(String url, String macAddress) {
//        return CompletableFuture.supplyAsync(() -> {
//            try {
//                URL obj = new URL(url);
//                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//                // Set request method to GET
//                con.setRequestMethod("POST");
//
//                // Add request headers
//                con.setRequestProperty("User-Agent", USER_AGENT);
//                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//
//                // Prepare the POST parameters
//                String urlParameters = "macAddress=" + macAddress;
//
//                // Send POST request
//                con.setDoOutput(true);
//                try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
//                    wr.writeBytes(urlParameters);
//                    wr.flush();
//                }
//
//                int responseCode = con.getResponseCode();
//                System.out.println("Response Code: " + responseCode);
//
//                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                String inputLine;
//                StringBuilder response = new StringBuilder();
//
//                while ((inputLine = in.readLine()) != null) {
//                    response.append(inputLine);
//                }
//                in.close();
//
//                // Return the response
//                return response.toString();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                return null;
//            }
//        });
//    }


//    private static String getMacAddress() {
//        try {
//            NetworkInterface networkInterface = NetworkInterface.getNetworkInterfaces()
//                    .asIterator().next();
//            byte[] macBytes = networkInterface.getHardwareAddress();
//            StringBuilder macAddress = new StringBuilder();
//
//            for (int i = 0; i < macBytes.length; i++) {
//                macAddress.append(String.format("%02X%s", macBytes[i], (i < macBytes.length - 1) ? "-" : ""));
//            }
//
//            return macAddress.toString();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


}
