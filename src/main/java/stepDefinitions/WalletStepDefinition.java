package stepDefinitions;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.RandomStringGeneration;

public class WalletStepDefinition {

	WebDriver driver;

	@FindBy(css = "span[class='brgm-button brgm-signup']")
	WebElement login;

	@FindBy(xpath = "//*[@id='join-login']/form/div[1]/input")
	WebElement email;

	@FindBy(xpath = "//*[@id='join-login']/form/div[2]/input")
	WebElement passwordElm;

	@FindBy(xpath = "//*[@id='join-login']/form/div[4]/button[2]")
	WebElement loginBtn;

	@FindBy(xpath = "//*[@id='web-app']/header/div/nav[1]/div[5]/span")
	WebElement loggedInName;

	@FindBy(xpath = "//*[@id='reviews-section']/div[1]/div[3]/review-star/div/*[name()='svg']")
	List<WebElement> stars;

	@FindBy(xpath = "//div[@class='dropdown second']/span")
	WebElement profileSelectDropDown;

	@FindBy(xpath = "//div[@class='dropdown second opened']/ul/li[starts-with(text(),'Health')]")
	WebElement healthPolicy;

	@FindBy(css = "textarea[class='textarea wrev-user-input validate']")
	WebElement reviewTextArea;

	@FindBy(css = "div[class='sbn-action semi-bold-font btn fixed-w-c tall']")
	WebElement submit;

	@FindBy(css = "div[class='rvc-header']")
	WebElement reviewConfirmation;

	@FindBy(xpath = "//div[contains(@class,'review')]/p[2]")
	WebElement actualReview;

	String reviewText = RandomStringGeneration.getStringForReview(200);

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		driver = new ChromeDriver();
		PageFactory.initElements(driver, this);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Given("^user is already logged in with \"([^\"]*)\" and \"([^\"]*)\" to WalletHub$")
	public void user_is_already_logged_in_with_and_to_WalletHub(String username, String password)
			throws InterruptedException {
		driver.get("http://wallethub.com/profile/test_insurance_company/");
		login.click();
		email.sendKeys(username);
		passwordElm.sendKeys(password);
		loginBtn.submit();
		driver.get("http://wallethub.com/profile/test_insurance_company/");
		try {
			Assert.assertTrue(loggedInName.isDisplayed());
		} catch (Exception exp) {
			System.out.println(exp + " found. There is issue with Wallet Login!");
		}

	}

	@When("^user hovers on fourth star$")
	public void user_hovers_on_forth_star() {
		Actions actions = new Actions(driver);
		actions.moveToElement(stars.get(3)).perform();
	}

	@Then("^starts should get lit up$")
	public void starts_should_get_lit_up() {
		try {
			// since we are hovering on 4 stars
			int starsToHover = 4;
			String pathTillSVG = "//*[@id='reviews-section']/div[1]/div[3]/review-star/div/*[name()='svg']";
			for (int i = 1; i <= starsToHover; i++) {
				List<WebElement> focusedStar = driver
						.findElements(By.xpath(pathTillSVG + "[" + i + "]/*[name()='g']/*[name()='path']"));
				// there are two path nodes to svg/g, when start is focused
				Assert.assertEquals(focusedStar.size(), 2);
				Assert.assertEquals(focusedStar.get(1).getAttribute("stroke-width"), "1.4988");
			}
		} catch (NoSuchElementException noSuchElmExp) {
			System.out.println("Exception occured while hovering the stars: " + noSuchElmExp);
		}
	}

	@When("^user clicks on fifth star$")
	public void user_clicks_on_fifth_star() {
		stars.get(4).click();
	}

	@And("^select policy as Health$")
	public void select_policy_as_Health() {
		profileSelectDropDown.click();
		healthPolicy.click();
	}

	@And("^submit review$")
	public void submit_review() {

		reviewTextArea.sendKeys(reviewText);
		submit.click();
	}

	@Then("^user should see a confirmation screen$")
	public void user_should_see_a_confirmation_screen() {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(reviewConfirmation));
		System.out.println(driver.getCurrentUrl());
		Assert.assertTrue(driver.getCurrentUrl().contains("/confirm-review"));
		String actualString = reviewConfirmation.getText();
		String expectedString = "Your review has been posted.";
		Assert.assertTrue(actualString.contains(expectedString));
	}

	@And("^user should see review in \"([^\"]*)\" profile$")
	public void user_should_see_review_in_his_profile(String username) {
		String uname = username.split("@")[0];

		String reviewURL = "https://wallethub.com/profile/" + uname + "/reviews/";
		System.out.println(reviewURL);
		driver.get(reviewURL);
		driver.findElement(By.linkText("Reviews")).click();
		System.out.println(actualReview.getText());
		System.out.println(reviewText);
		Assert.assertEquals(actualReview.getText(), reviewText);

	}

	@After
	public void cleanUp() {
		// driver.quit();
	}
}
