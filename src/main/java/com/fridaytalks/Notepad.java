package com.fridaytalks;


import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.windows.WindowsDriver;

public class Notepad {

	private static WindowsDriver driver;

	@BeforeClass
	public static void setup() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("app", "C:\\Windows\\notepad.exe");
		capabilities.setCapability("paltformName", "Windows");
		capabilities.setCapability("deviceName", "WindowsPC");

		try {

			driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
		}catch(Exception e){
			e.printStackTrace();
		} 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test

	public static void checkHelpAboutNowTest() throws InterruptedException {

		driver.findElementByName("Help").click();
		Thread.sleep(3000);
		driver.findElementByName("About Notepad").click();
		Thread.sleep(3000);
		driver.findElementByName("OK").click();

	}

	@Test
	public static void  writeDataAndSave() throws InterruptedException {

		driver.findElementByName("Text Editor").sendKeys("Welcome to Firday talks");
		Thread.sleep(3000);
		driver.findElementByName("Text Editor").clear();

	}
	

	@Test
	public static void  EnterTimeAndDate() throws InterruptedException {

		driver.findElementByName("Edit").click();
		Thread.sleep(3000);
		driver.findElementByAccessibilityId("26").click();
		Thread.sleep(3000);
		String Value=driver.findElementByName("Text Editor").getAttribute("Value.Value");
		System.out.println(Value);
		Assert.assertTrue(Value.contains("28-05-2021"));
		driver.findElementByName("Text Editor").clear();

	}

	@AfterSuite

	public static void close() {
		driver.quit();
	}



}

