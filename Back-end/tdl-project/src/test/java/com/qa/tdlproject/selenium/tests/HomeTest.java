package com.qa.tdlproject.selenium.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.tdlproject.selenium.pages.HomePage;
import com.qa.tdlproject.selenium.pages.ToDoListPage;

@SpringBootTest
class HomeTest {

	private WebDriver driver;
	
	@BeforeEach
	public void setup() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test 
	void testOpeningPage() {
		//Set up
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		
		//Go to Homepage URL
		this.driver.get(homePage.URL);
		
		//Assert that the page is in the Homepage
		assertThat(homePage.getTitle()).isEqualTo("Hey there!");
	}
	
	@Test
	void testToDoListLink() {
		//Set up
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		ToDoListPage toDoListPage = PageFactory.initElements(driver, ToDoListPage.class);
		
		//Go to Homepage URL
		this.driver.get(homePage.URL);
		
		//Click to do list navbar
		homePage.clickToDoList();
		
		//verify that we are in the to do list page
		assertTrue(toDoListPage.getTitle().contains("To Do List"));
	}
	
	@Test
	void testLogoImg() {
		//Set up
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		
		//Go to Homepage URL
		this.driver.get(homePage.URL);
		
		//img URL
		String imgURL = "http://192.168.0.21:5500/Front-end/resources/logo.png";
		
		//Check the image is present
		assertThat(homePage.getLogoSrc()).isEqualTo(imgURL);
	}
	
	@Test
	void testLogoLink() {
		//Set up
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		
		//Go to to do page URL
		this.driver.get(homePage.URL);
		
		//Click logo link
		homePage.clickLogo();
		
		//verify that we are in the home page
		assertTrue(homePage.getTitle().contains("Hey there!"));
	}
	
	
	@Test
	void testLearnMoreBtn() {
		//Set up
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		ToDoListPage toDoListPage = PageFactory.initElements(driver, ToDoListPage.class);
		
		//Go to Homepage URL
		this.driver.get(homePage.URL);
		
		//Click on the learn more button
		homePage.clickLearnMoreBtn();
		
		//Assert that we are redirected to the to do list page
		assertTrue(toDoListPage.getTitle().contains("To Do List"));
	}
	
	@Test
	void testFooter() {
		//Set up
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);

		//Go to Homepage URL
		this.driver.get(homePage.URL);
		
		//Assert that the footer contain the expected string
		assertTrue(homePage.getFooter().contains("?? Marco Castellana 2021"));
	}
	
	@AfterEach
	public void tearDown() {
		this.driver.close();
	}
}
