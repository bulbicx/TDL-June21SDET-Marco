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
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.tdlproject.selenium.pages.HomePage;
import com.qa.tdlproject.selenium.pages.ToDoListPage;

@SpringBootTest
public class ToDoListTest {

	private WebDriver driver;
	
	@BeforeEach
	public void setup() {
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test 
	public void testOpeningPage() {
		//Set up
		ToDoListPage page = PageFactory.initElements(driver, ToDoListPage.class);
		
		//Go to page URL
		this.driver.get(page.URL);
		
		//Assert that the page is in the Homepage
		assertThat(page.getTitle()).isEqualTo("To Do List");
	}
	
	@Test
	public void testHomeNavbarLink() {
		//Set up
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		ToDoListPage toDoListPage = PageFactory.initElements(driver, ToDoListPage.class);
		
		//Go to to do page URL
		this.driver.get(toDoListPage.URL);
		
		//Click to do list navbar
		toDoListPage.clickHome();
		
		//verify that we are in the home page
		assertTrue(homePage.getTitle().contains("Hey there!"));
	}
	
	@Test
	public void testLogoImg() {
		//Set up
		ToDoListPage page = PageFactory.initElements(driver, ToDoListPage.class);
		
		//Go to to do page URL
		this.driver.get(page.URL);
		
		//img URL
		String imgURL = "http://192.168.0.21:5500/Front-end/resources/logo.png";
		
		//Check the image is present
		assertThat(page.getLogoSrc()).isEqualTo(imgURL);
	}
	
	@Test
	public void testLogoLink() {
		//Set up
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		ToDoListPage toDoListPage = PageFactory.initElements(driver, ToDoListPage.class);
		
		//Go to to do page URL
		this.driver.get(toDoListPage.URL);
		
		//Click logo link
		toDoListPage.clickLogo();
		
		//verify that we are in the home page
		assertTrue(homePage.getTitle().contains("Hey there!"));
	}
	
	@Test
	public void testCreateToDo() {
		//Set up
		ToDoListPage page = PageFactory.initElements(driver, ToDoListPage.class);
		
		//Go to to do page URL
		this.driver.get(page.URL);
		
		//Click the create option from the dropdown menu
		Select selectElement = new Select(page.getCrudDropdownMenu());
		selectElement.selectByValue("create");
		
		//Insert a title onto the input field
		String title = "Buy apples";
		page.insertTitle(title);
		
		//Press the add button
		page.clickAddBtn();
		
		//Assert that the create went successfully by checking the alert message
		assertThat(page.getAlertMsg().getText()).isEqualTo("To do [" + title + "] created successfully!");
	}
	
	@Test
	public void testGetOneToDo() {
		//Set up
		ToDoListPage page = PageFactory.initElements(driver, ToDoListPage.class);
		
		//Go to to do page URL
		this.driver.get(page.URL);
		
		//Click the readOne option from the dropdown menu
		Select selectElement = new Select(page.getCrudDropdownMenu());
		selectElement.selectByValue("readOne");
		
		//Insert the id into the respective field
		page.insertIdRetrieveOne("1");
		
		//press the retrieve button
		page.clickRetrieveOneBtn();
		
		//Verify that the retrieve has worked by checking the to do is displayed
		String toDo = page.getToDo().getText();
		assertThat(toDo).isNotEqualTo("");
	}
	
	@Test
	public void testUpdateToDo() {
		//Set up
		ToDoListPage page = PageFactory.initElements(driver, ToDoListPage.class);
		
		//Go to to do page URL
		this.driver.get(page.URL);

		//Click the update option from the dropdown menu
		Select selectElement = new Select(page.getCrudDropdownMenu());
		selectElement.selectByValue("update");
		
		//Insert id, title and completed
		page.insertIdUpdate("1");
		page.insertTitleInputUpdate("Buy apples");
		page.clickCheckboxUpdate(true);
		
		//press the update button
		page.clickUpdateBtn();
		
		//Verify that the update alert displays 
		assertThat(page.getAlertMsg().getText()).contains("updated successfully");
	}
	
	@Test
	public void testDeleteToDo() {
		//Set up
		ToDoListPage page = PageFactory.initElements(driver, ToDoListPage.class);
		
		//Go to to do page URL
		this.driver.get(page.URL);

		//Click the delete option from the dropdown menu
		Select selectElement = new Select(page.getCrudDropdownMenu());
		selectElement.selectByValue("delete");
		
		//Insert id into the respective id field
		page.insertIdInputDelete("1");
		
		//Click the delete button
		page.clickDeleteBtn();
		
		//Verify that the delete message is displayed
		assertThat(page.getAlertMsg().getText()).contains("deleted successfully");
	}
	
	@Test
	public void testGetAllToDo() {
		//Set up
		ToDoListPage page = PageFactory.initElements(driver, ToDoListPage.class);
		
		//Go to to do page URL
		this.driver.get(page.URL);

		//Click the readAll option from the dropdown menu
		Select selectElement = new Select(page.getCrudDropdownMenu());
		selectElement.selectByValue("readAll");
		
		//Verify that the retrieve all displays a list
		assertThat(page.getFirstElRetrieveAll().getText()).isNotEqualTo("");
	}
	
	@Test
	public void testFooter() {
		//Set up
		ToDoListPage page = PageFactory.initElements(driver, ToDoListPage.class);

		//Go to to do page URL
		this.driver.get(page.URL);
		
		//Assert that the footer contain the expected string
		assertTrue(page.getFooter().contains("© Marco Castellana 2021"));
	}
	
	@AfterEach
	public void tearDown() {
		this.driver.close();
	}

}
