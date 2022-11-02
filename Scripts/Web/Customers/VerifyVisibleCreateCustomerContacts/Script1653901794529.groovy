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
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.interactions.Actions as Actions

import com.database.*
import java.sql.*

WebDriver driver = DriverFactory.getWebDriver()

Actions actions = new Actions(driver)

WebUI.callTestCase(findTestCase('Test Cases/Web/Customers/CreateNewCustomerInformation'), [('businessRegistrationCode') : businessRegistrationCode
        , ('company') : company, ('email') : email, ('address') : address, ('representative') : representative, ('phoneNumber') : phoneNumber
        , ('foundingDate') : foundingDate, ('type') : type, ('status') : status, ('message') : message, ('infor') : infor
        , ('action') : action], FailureHandling.STOP_ON_FAILURE)

AbstractWeb.clickButton('Object Repository/Web/Customers/InformationCustomerPage/tabContact')

//verify table header
AbstractWeb.verifyArrayText('Object Repository/Web/Customers/headerTable', tableIndex, tableFullName, tablePhoneNumber, 
    tableEmail, tableAction)

WebUI.verifyElementVisible(findTestObject('Object Repository/Web/imageEmpty'), FailureHandling.STOP_ON_FAILURE)

AbstractWeb.verifyText('Object Repository/Web/Buildings/messageNoRecord', messageNoRecord)

AbstractWeb.clickButton('Object Repository/Web/Customers/Contact/buttonCreate')

//validate label
AbstractWeb.verifyArrayText('Object Repository/Web/Customers/Contact/label', labelFullName, labelPhoneNumber, labelEmail)

//validate placeholder
WebUI.verifyMatch(WebUI.getAttribute(findTestObject('Object Repository/Web/Customers/Contact/textBoxPhoneNumber'), 'placeholder', 
        FailureHandling.STOP_ON_FAILURE), textBoxPhoneNumber, false, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyMatch(WebUI.getAttribute(findTestObject('Object Repository/Web/Customers/Contact/textBoxFullName'), 'placeholder', 
        FailureHandling.STOP_ON_FAILURE), textBoxFullName, false, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyMatch(WebUI.getAttribute(findTestObject('Object Repository/Web/Customers/Contact/textBoxEmail'), 'placeholder', 
        FailureHandling.STOP_ON_FAILURE), textBoxEmail, false, FailureHandling.STOP_ON_FAILURE)

//validate small warning
WebUI.click(findTestObject('Object Repository/Web/Customers/Contact/textBoxEmail'))

WebUI.click(findTestObject('Object Repository/Web/Customers/Contact/textBoxFullName'))

WebUI.click(findTestObject('Object Repository/Web/Customers/Contact/textBoxPhoneNumber'))

WebUI.click(findTestObject('Object Repository/Web/Customers/Contact/textBoxEmail'))

AbstractWeb.verifyArrayText('Object Repository/Web/Customers/Contact/smallWarning', fullNameWarning, phoneNumberWarning, 
    emailWarning)

//invalidate
AbstractWeb.sendText('Object Repository/Web/Customers/Contact/textBoxPhoneNumber', 'abc')

AbstractWeb.sendText('Object Repository/Web/Customers/Contact/textBoxEmail', 'abc')

WebUI.verifyEqual(AbstractWeb.getText('Object Repository/Web/Customers/Contact/smallWarning2', [('text') : 'Email không đúng định dạng']), 
    'Email không đúng định dạng', FailureHandling.STOP_ON_FAILURE)

WebUI.verifyEqual(AbstractWeb.getText('Object Repository/Web/Customers/Contact/smallWarning2', [('text') : 'Số điện thoại không đúng định dạng']), 
    'Số điện thoại không đúng định dạng', FailureHandling.STOP_ON_FAILURE)