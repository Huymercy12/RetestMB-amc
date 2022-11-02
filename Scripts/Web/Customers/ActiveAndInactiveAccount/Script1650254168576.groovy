import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.web.AbstractWeb as AbstractWeb
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import static org.junit.Assert.assertTrue
import org.openqa.selenium.By
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.interactions.Actions as Actions
import com.database.*
import java.sql.*

WebDriver driver = DriverFactory.getWebDriver()

Actions action = new Actions(driver)

if (lockStatus.toString().equalsIgnoreCase('can lock')) {
	AbstractWeb.waitAndSearch('Object Repository/Web/Customers/placeholderSearch', Statemnet.getOrganizationNameHasLeasingStatusAVAILABLE())


	WebElement lockButton = WebUI.findWebElement(findTestObject('Object Repository/Web/Customers/listButtonLockInTable'),
			GlobalVariable.TIME_OUT)

	lockButton.click()

	//verify displays
	AbstractWeb.verifyText('Object Repository/Web/Customers/titlePopup', titlePopupLock)

	AbstractWeb.verifyText('Object Repository/Web/Customers/contentPopup', contentPopupLock)

	AbstractWeb.verifyVisible('Object Repository/Web/Customers/buttonCancelPopup')

	AbstractWeb.verifyVisible('Object Repository/Web/Customers/buttonAcceptPopup')

	//verify button cancel
	WebUI.click(findTestObject('Object Repository/Web/Customers/buttonCancelPopup'), FailureHandling.STOP_ON_FAILURE)

	action.moveToElement(driver.findElement(By.xpath('//tbody/tr/td[4]'))).perform()

	String businessCode = AbstractWeb.getText('Object Repository/Web/DashboardPage/tooltipContent')

	ResultSet rs = Statemnet.getOrganizationContact(businessCode)

	while (rs.next()) {
		WebUI.verifyMatch(rs.getString('STATUS'), 'ACTIVE', false, FailureHandling.STOP_ON_FAILURE)
	}

	ConnectionDatabase.closeDatabaseConnection()

	// Inactive
	lockButton.click()

	AbstractWeb.clickButton('Object Repository/Web/Customers/buttonAcceptPopup')

	sleep(3000)

	ResultSet rs2 = Statemnet.getOrganizationContact(businessCode)

	while (rs2.next()) {
		WebUI.verifyMatch(rs2.getString('STATUS'), 'INACTIVE', false, FailureHandling.STOP_ON_FAILURE)
	}

	ConnectionDatabase.closeDatabaseConnection()

	//verify unlock
	WebElement unlockButton = WebUI.findWebElement(findTestObject('Object Repository/Web/Customers/listButtonUnlockInTable'),
			GlobalVariable.TIME_OUT)

	unlockButton.click()

	//verify displays
	AbstractWeb.verifyText('Object Repository/Web/Customers/titlePopup', titlePopupUnlock)

	AbstractWeb.verifyText('Object Repository/Web/Customers/contentPopup', contentPopupUnlock)

	AbstractWeb.verifyVisible('Object Repository/Web/Customers/buttonCancelPopup')

	AbstractWeb.verifyVisible('Object Repository/Web/Customers/buttonAcceptPopup')

	//verify button cancel
	WebUI.click(findTestObject('Object Repository/Web/Customers/buttonCancelPopup'), FailureHandling.STOP_ON_FAILURE)

	ResultSet rs3 = Statemnet.getOrganizationContact(businessCode)

	while (rs3.next()) {
		WebUI.verifyMatch(rs3.getString('STATUS'), 'INACTIVE', false, FailureHandling.STOP_ON_FAILURE)
	}

	ConnectionDatabase.closeDatabaseConnection()

	//Active
	unlockButton.click()

	AbstractWeb.clickButton('Object Repository/Web/Customers/buttonAcceptPopup')

	ResultSet rs4 = Statemnet.getOrganizationContact(businessCode)

	while (rs4.next()) {
		WebUI.verifyMatch(rs4.getString('STATUS'), 'ACTIVE', false, FailureHandling.STOP_ON_FAILURE)
	}

	ConnectionDatabase.closeDatabaseConnection() //cant lock
} else {
	AbstractWeb.waitAndSearch('Object Repository/Web/Customers/placeholderSearch', Statemnet.getOrganizationBusinessCodeHasLeasingStatusLeasedOrDeposit())

	ConnectionDatabase.closeDatabaseConnection()

	WebElement lockButton = WebUI.findWebElement(findTestObject('Object Repository/Web/Customers/listButtonLockInTable'),
			GlobalVariable.TIME_OUT)

	lockButton.click()

	AbstractWeb.clickButton('Object Repository/Web/Customers/buttonAcceptPopup')

	AbstractWeb.verifyMessage('Object Repository/Web/message', message)
}