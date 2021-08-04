package com.qa.tdlproject.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ToDoListPage {
	
	public final String URL = "http://192.168.0.21:5500/Front-end/html/toDoList.html";
	
	@FindBy(id = "logo")
	private WebElement logo;
	
	@FindBy(xpath = "/html/body/header/nav/div/a")
	private WebElement linkLogo;
	
	@FindBy(xpath = "/html/body/header/nav/div/div/div/a[1]")
	private WebElement homeLink;
	
	@FindBy(id = "title-page")
	private WebElement title;
	
	@FindBy(id = "crud-select")
	private WebElement crudDropdownMenu;
	
	@FindBy(xpath = "/html/body/footer/p")
	private WebElement footer;
	
	@FindBy(xpath = "/html/body/footer/ul/li[1]/a")
	private WebElement fbLink;
	
	@FindBy(xpath = "/html/body/footer/ul/li[2]/a")
	private WebElement instaLink;
	
	@FindBy(xpath = "/html/body/footer/ul/li[3]/a")
	private WebElement twitterLink;
	
	public void clickHome() {
		homeLink.click();
	}
	
	public void clickLogo() {
		linkLogo.click();
	}
	
	public void clickCrudDropdownMenu() {
		crudDropdownMenu.click();
	}
	
	public void clickFbLink() {
		fbLink.click();
	}
	
	public void clickInstagramLink() {
		instaLink.click();
	}
	
	public void clickTwitterLink() {
		twitterLink.click();
	}

	public String getTitle() {
		return title.getText();
	}
	
	public String getFooter() {
		return footer.getText();
	}
}
