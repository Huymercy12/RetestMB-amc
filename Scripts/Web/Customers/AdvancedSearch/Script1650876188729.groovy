import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.web.AbstractWeb as AbstractWeb
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import static org.junit.Assert.assertTrue
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement

import com.database.*
import com.google.common.hash.BloomFilter
import com.testData.DataExcelUtils
import java.sql.*
import org.openqa.selenium.interactions.Actions
import org.testng.Assert

WebDriver driver=DriverFactory.getWebDriver()

AbstractWeb.waitVerifyElementAndClick('Object Repository/Web/Customers/buttonAdvancedSearch')

AbstractWeb.verifyText('Object Repository/Web/Customers/titlePopup', titleAdvancedSearch)

AbstractWeb.verifyText('Object Repository/Web/Customers/contentPopupAdvancedSearch', contentAdvancedSearch)

AbstractWeb.waitVerifyElementAndClick('Object Repository/Web/Customers/buttonResetAdvancedSearch')

if(status.toString().equalsIgnoreCase('Trạng thái')) {
	AbstractWeb.waitVerifyElementAndClick('Object Repository/Web/Customers/buttonAcceptPopup')
	WebUI.callTestCase(findTestCase('Test Cases/Web/Customers/SearchCustomers'), 
		DataExcelUtils.getDataByIndex(2, 'Data Files/Web/Customers/SearchCustomers'), 
		FailureHandling.STOP_ON_FAILURE)
}

if(status.toString().equalsIgnoreCase('Hoạt động')) {
	AbstractWeb.moveToElementAndClick('Object Repository/Web/Customers/selectPlaceholder')
	AbstractWeb.moveToElementAndClick('Object Repository/Web/Customers/statusActive')
	AbstractWeb.waitVerifyElementAndClick('Object Repository/Web/Customers/buttonAcceptPopup')
	WebUI.callTestCase(findTestCase('Test Cases/Web/Customers/SearchCustomers'),
		 DataExcelUtils.getDataByIndex(2, 'Data Files/Web/Customers/SearchCustomers'), 
		 FailureHandling.STOP_ON_FAILURE)
	List<WebElement> element=WebUI.findWebElements(findTestObject('Object Repository/Web/Customers/tableStatusInActive'),GlobalVariable.TIME_OUT)
	if (element.size()>0) {
		Assert.assertTrue(false)
	}
}

if(status.toString().equalsIgnoreCase('Không hoạt động')) {
	AbstractWeb.moveToElementAndClick('Object Repository/Web/Customers/selectPlaceholder')
	AbstractWeb.moveToElementAndClick('Object Repository/Web/Customers/statusInActive')
	AbstractWeb.waitVerifyElementAndClick('Object Repository/Web/Customers/buttonAcceptPopup')
	WebUI.callTestCase(findTestCase('Test Cases/Web/Customers/SearchCustomers'), 
		DataExcelUtils.getDataByIndex(2, 'Data Files/Web/Customers/SearchCustomers'), 
		FailureHandling.STOP_ON_FAILURE)
	List<WebElement> element=WebUI.findWebElements(findTestObject('Object Repository/Web/Customers/tableStatusActive'),GlobalVariable.TIME_OUT)
	if (element.size()>0) {
		Assert.assertTrue(false)
	}
}

