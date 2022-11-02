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
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.testng.Assert as Assert

import com.database.*
import java.sql.*

WebDriver driver = DriverFactory.getWebDriver()
Actions action = new Actions(driver)


AbstractWeb.waitAndVerifyElement('Object Repository/Web/Customers/buttonCreate')

WebUI.verifyMatch(WebUI.getAttribute(findTestObject('Object Repository/Web/Customers/placeholderSearch'), 'placeholder'), 
    search, false, FailureHandling.CONTINUE_ON_FAILURE)

AbstractWeb.waitAndVerifyElement('Object Repository/Web/Customers/buttonAdvancedSearch')

AbstractWeb.scrollToEndPage()

AbstractWeb.getInforSizeOfPagination()

WebUI.verifyMatch(GlobalVariable.INFOR_SIZE_OF_PAGINATION.getAt('totalRecord'), Statemnet.getTotalRecorded('EVO_IAM.ORGANIZATION'), 
    false, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Web/Customers/ValidateTable'),null, FailureHandling.STOP_ON_FAILURE)
