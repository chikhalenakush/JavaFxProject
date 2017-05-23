package simple;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.io.Files;
import com.sun.glass.events.KeyEvent;

import jodd.swingspy.SwingSpy;

public class SimulateMouse {
	
//	Cookie ck;
	public void startmagic(String FilepathforIEDriver, String sn, Boolean BG, Boolean Stick, String loginname,
			String loginPassword, Boolean AutoSync, Logger logger, int counterforresetcheck)
			throws AWTException, InterruptedException, SecurityException, IOException {
		
		// TODO Auto-generated method stub
		try{
		logger.info("Inside Class Simulate mouse and Method Startmagic");

		Boolean IsEnglishOrDeutsch = false;
		String Lang = null;
		int ReplacmentForN;
		
		WebDriver driver;
		Robot robot = new Robot();
		
		logger.info("Inside Method Startmagic Before Desired Cap");
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability("ignoreZoomSetting", true);
		capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
		
		logger.info("(Inside Method Startmagic) DesiredCap filled ");

		try {
			File file = new File(FilepathforIEDriver);

			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());

			 driver = new InternetExplorerDriver(capabilities);

			driver.manage().window().maximize();
			
			
			
			//Cookie name1 = new Cookie("dtCookie", loginCookies.get("dtCookie"));
			//Cookie ck = new Cookie("locale",  loginCookies.get("locale"), "dtCookie",loginCookies.get("dtCookie"),
			//		"JSESSIONID",loginCookies.get("JSESSIONID")/*,"_WL_AUTHCOOKIE_JSESSIONID",loginCookies.get("_WL_AUTHCOOKIE_JSESSIONID"), null*/);
			//driver.manage().addCookie(name);
			driver.get("https://carelink.minimed.eu/patient/entry.jsp?bhcp=1");
			//driver.get("https://carelink.minimed.eu/patient/main/deviceUpload.do");
			

	}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,
					"Please Check if the correct IEDriver is selected OR IE settings are not correctly");
			logger.info("Inside catch of Class Simulate mouse and Method Startmagic");
			return;
		}
		
		//if(Controller.counterforresetcheck ==0){
			logger.info("(Inside Method Startmagic If condition Controller.counterforresetcheck ");
			//driver.get("https://carelink.minimed.eu/patient/main/deviceUpload.do");
			
			Boolean isPresent = driver.findElements(By.id("j_username")).size() > 0;
			
			if (isPresent) {
				driver.findElement(By.id("j_username")).sendKeys(loginname);
				driver.findElement(By.id("j_password")).sendKeys(loginPassword);
				Thread.sleep(2000);

				driver.findElement(By.id("j_password")).sendKeys(Keys.ENTER);
			} else {
				Thread.sleep(4000);
				if (driver.findElements(By.id("j_username")).size() > 0) {
					driver.findElement(By.id("j_username")).sendKeys(loginname);
					driver.findElement(By.id("j_password")).sendKeys(loginPassword);
					Thread.sleep(2000);
					// driver.findElement(By.id("loginButton")).click();
					driver.findElement(By.id("j_password")).sendKeys(Keys.ENTER);
				} else {
					Thread.sleep(6000);
					if (driver.findElements(By.id("j_username")).size() > 0) {
						driver.findElement(By.id("j_username")).sendKeys(loginname);
						driver.findElement(By.id("j_password")).sendKeys(loginPassword);
						Thread.sleep(2000);

						driver.findElement(By.id("j_password")).sendKeys(Keys.ENTER);
					} else {
						JOptionPane.showMessageDialog(null, "Website has not fully loaded close and try running again");
						return;
					}
				}
			}
			

		
			Thread.sleep(4000);

			Boolean isUploadPresent = driver.findElements(By.id("upload")).size() > 0;
			if (isUploadPresent) {
				driver.findElement(By.id("upload")).sendKeys(Keys.ENTER);
			} else {
				Thread.sleep(6000);
				if (driver.findElements(By.id("upload")).size() > 0) {
					driver.findElement(By.id("upload")).sendKeys(Keys.ENTER);
				} else {
					Thread.sleep(8000);

					if (driver.findElements(By.id("upload")).size() > 0) {
						driver.findElement(By.id("upload")).sendKeys(Keys.ENTER);
					} else {
						JOptionPane.showMessageDialog(null,
								"upload section has not fully loaded close IE and try running Java program again");
								return;
					}
				}
			}
			Thread.sleep(10000);

			

			Thread.sleep(2000);
			try {

				Lang = driver.findElement(By.tagName("html")).getAttribute("lang");

				LanguageClass Language = new LanguageClass();

				ReplacmentForN = Language.getReplacment(Lang);
				
				

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Site not fully loaded");
				logger.info("There is issue with getting language of webpage");
				return;
			}
			AppletButtons AB = new AppletButtons();

			if (driver.findElements(By.tagName("object")).size()>0/*driver.findElements(By.id("content")).size() > 0*/) {
				AB.RunningAppletButtons(sn, BG, Stick, robot, logger, ReplacmentForN);
				//Runningappletbuttons(sn, BG, Stick, robot, logger, ReplacmentForN);
				//String wdt = driver.findElement(By.tagName("object")).getAttribute("width");
				
			} else {
				Thread.sleep(10000);
				if (driver.findElements(By.tagName("object")).size()>0) {
					AB.RunningAppletButtons(sn, BG, Stick, robot, logger, ReplacmentForN);
					//Runningappletbuttons(sn, BG, Stick, robot, logger, ReplacmentForN);

				} else {
					Thread.sleep(12000);
					if (driver.findElements(By.tagName("object")).size()>0) {
						AB.RunningAppletButtons(sn, BG, Stick, robot, logger, ReplacmentForN);
						//Runningappletbuttons(sn, BG, Stick, robot, logger, ReplacmentForN);

					} else {
						JOptionPane.showMessageDialog(null,
								"Applet section has not fully loaded close IE and try running again Java program again");

					}
				}

			}

			logger.info("Exit Class Simulate mouse and Method Startmagic");
	
			
	}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,
					"Please Check if the correct IEDriver is selected OR IE settings are not correctly");
			logger.info("Inside catch of Class Simulate mouse and Method Startmagic");
			return;
		}
		finally {
			// JOptionPane.showMessageDialog(null, "Please Check if the correct
			// IEDriver File Location is selected OR IE settings are correctly
			// selected");
			Thread.currentThread().interrupt();

		}

	}
/*
	private void Runningappletbuttons(String sn, Boolean BG, Boolean Stick, Robot robot, Logger logger,
			int replacmentForN) throws InterruptedException, AWTException {
		logger.info("Inside Runningappletbuttons method for Mimic of Alt plus buttons");
		// First selecting Minimed

		Thread.sleep(500);

		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_TAB);

		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_TAB);
		

		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(2000);
		
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(replacmentForN);
		
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(replacmentForN);

		
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_TAB);

		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_TAB);

		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);

	
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(replacmentForN);
		
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(replacmentForN);
		// Third selecting ALT + N
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(replacmentForN);
		
		robot.keyRelease(KeyEvent.VK_ALT);		
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(replacmentForN);

		// Entering Value for Device
		Thread.sleep(2000);
		type(sn);
		// Fourth selecting ALT + N
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(replacmentForN);
		
		robot.keyRelease(KeyEvent.VK_ALT);		
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(replacmentForN);

		if (Stick) {
			// System.out.println("Stick is selected");
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			// Thread.sleep(2000);
		}
		if (BG) {

			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);

			Thread.sleep(2000);
			
			
		}

		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(replacmentForN);
		robot.keyRelease(KeyEvent.VK_ALT);
		
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(replacmentForN);
		if (BG) {
			robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_F);
		robot.keyRelease(KeyEvent.VK_F);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		}
		
		logger.info("Exit Runningappletbuttons method for Mimic of Alt plus buttons");
	}*/

	/*
	private static void leftClick() throws AWTException {
		Robot robot = new Robot();
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.delay(200);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.delay(200);
	}

	private void type(int i) throws AWTException {
		Robot robot = new Robot();
		robot.delay(40);
		robot.keyPress(i);
		robot.keyRelease(i);
	}

	private static void type(String s) throws AWTException {
		Robot robot = new Robot();
		byte[] bytes = s.getBytes();
		for (byte b : bytes) {
			int code = b;
			// keycode only handles [A-Z] (which is ASCII decimal [65-90])
			if (code > 96 && code < 123)
				code = code - 32;
			robot.delay(40);
			robot.keyPress(code);
			robot.keyRelease(code);
		}
	}*/

}
