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
	
	@FindBy(xpath = "/html/body/main/div[2]/div")
	private WebElement alertMsg;
	
	@FindBy(id = "crud-select")
	private WebElement crudDropdownMenu;
	
	@FindBy(xpath = "/html/body/main/div[2]/section/form/div[1]/input")
	private WebElement createInputTitle;
	
	@FindBy(xpath = "/html/body/main/div[2]/section/form/div[2]/button")
	private WebElement addToDoBtn;
	
	@FindBy(xpath = "/html/body/main/div[2]/section/form/div/input")
	private WebElement retrieveOneWithIdInput;
	
	@FindBy(xpath = "/html/body/main/div[2]/section/form/button")
	private WebElement retrieveOneToDoBtn;
	
	@FindBy(xpath = "/html/body/main/div[2]/li")
	private WebElement toDo;
	
	@FindBy(xpath = "/html/body/main/div[2]/section/form/div/div[1]/input")
	private WebElement updateIdInput;
	
	@FindBy(xpath = "/html/body/main/div[2]/section/form/div/div[2]/input")
	private WebElement updateTitleInput;
	
	@FindBy(id = "todo-completed")
	private WebElement updateCompletedCheckbox;
	
	@FindBy(xpath = "/html/body/main/div[2]/section/form/div/div[4]/button")
	private WebElement updateToDoBtn;
	
	@FindBy(xpath = "/html/body/main/div[2]/section/form/div/input")
	private WebElement deleteIdInput;
	
	@FindBy(xpath = "/html/body/main/div[2]/section/form/button")
	private WebElement deleteToDoBtn;
	
	@FindBy(xpath = "/html/body/main/div[2]/ul/li[1]")
	private WebElement retrieveAllFirstElement;
	
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
	
	public WebElement getAlertMsg() {
		return alertMsg;
	}
	
	public WebElement getCrudDropdownMenu() {
		return crudDropdownMenu;
	}
	
	public void insertTitle(String title) {
		createInputTitle.sendKeys(title);
	}
	
	public void clickAddBtn() {
		addToDoBtn.click();
	}
	
	public void insertIdRetrieveOne(String id) {
		retrieveOneWithIdInput.sendKeys(id);
	}
	
	public void clickRetrieveOneBtn() {
		retrieveOneToDoBtn.click();
	}

	public void insertIdUpdate(String id) {
		updateIdInput.sendKeys(id);
	}
	
	public void insertTitleInputUpdate(String title) {
		updateTitleInput.sendKeys(title);
	}
	
	public void clickCheckboxUpdate(Boolean completed) {
		if (completed == true) {
			updateCompletedCheckbox.click();			
		}
	}
	
	public void clickUpdateBtn() {
		updateToDoBtn.click();
	}
	
	public void insertIdInputDelete(String id) {
		deleteIdInput.sendKeys(id);
	}
	
	public void clickDeleteBtn() {
		deleteToDoBtn.click();
	}
	
	public WebElement getFirstElRetrieveAll() {
		return retrieveAllFirstElement;
	}
	
	public WebElement getToDo() {
		return toDo;
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
	
	public String getLogoSrc() {
		return logo.getAttribute("src");
	}
}
