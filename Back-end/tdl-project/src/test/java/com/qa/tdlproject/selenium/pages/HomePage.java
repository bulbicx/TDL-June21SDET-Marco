package com.qa.tdlproject.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
	
	public final String URL = "http://192.168.0.21:5500/Front-end/html/index.html";
	
	@FindBy(id = "logo")
	private WebElement logo;
	
	@FindBy(xpath = "/html/body/header/nav/div/a")
	private WebElement linkLogo;
	
	@FindBy(xpath = "/html/body/header/nav/div/div/div/a[2]")
	private WebElement toDoListLink;
	
	@FindBy(id = "title-page")
	private WebElement title;
	
	@FindBy(xpath = "/html/body/main/p[2]/a")
	private WebElement learnMoreBtn;
	
	@FindBy(xpath = "/html/body/footer/p")
	private WebElement footer;
	
	@FindBy(xpath = "/html/body/footer/ul/li[1]/a")
	private WebElement fbLink;
	
	@FindBy(xpath = "/html/body/footer/ul/li[2]/a")
	private WebElement instaLink;
	
	@FindBy(xpath = "/html/body/footer/ul/li[3]/a")
	private WebElement twitterLink;
	
	public void clickToDoList() {
		toDoListLink.click();
	}
	
	public void clickLogo() {
		linkLogo.click();
	}
	
	public void clickLearnMoreBtn() {
		learnMoreBtn.click();
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
