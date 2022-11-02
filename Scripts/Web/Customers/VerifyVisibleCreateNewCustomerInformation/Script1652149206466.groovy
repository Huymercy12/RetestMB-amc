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

WebUI.click(findTestObject('Object Repository/Web/Customers/buttonCreate'), FailureHandling.STOP_ON_FAILURE)

//validate label
AbstractWeb.verifyText('Object Repository/Web/Customers/titleTable', titleTable)

AbstractWeb.verifyArrayText('Object Repository/Web/Customers/InformationCustomerPage/label', labelBusinessRegistrationCode, 
    labelType, labelCompany, labelRepresentative, labelEmail, labelPhoneNumber, address, foundingDate, labelStatus)

//validate placeholder
WebUI.verifyMatch(WebUI.getAttribute(findTestObject('Object Repository/Web/Customers/InformationCustomerPage/textBoxBusinessRegistrationCode'), 
        'placeholder', FailureHandling.STOP_ON_FAILURE), businessRegistrationCode, false, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyMatch(WebUI.getAttribute(findTestObject('Object Repository/Web/Customers/InformationCustomerPage/textBoxCompany'), 
        'placeholder', FailureHandling.STOP_ON_FAILURE), company, false, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyMatch(WebUI.getAttribute(findTestObject('Object Repository/Web/Customers/InformationCustomerPage/textBoxEmail'), 
        'placeholder', FailureHandling.STOP_ON_FAILURE), email, false, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyMatch(WebUI.getAttribute(findTestObject('Object Repository/Web/Customers/InformationCustomerPage/textBoxAddress'), 
        'placeholder', FailureHandling.STOP_ON_FAILURE), address, false, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyMatch(WebUI.getAttribute(findTestObject('Object Repository/Web/Customers/InformationCustomerPage/textBoxRepresentative'), 
        'placeholder', FailureHandling.STOP_ON_FAILURE), representative, false, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyMatch(WebUI.getAttribute(findTestObject('Object Repository/Web/Customers/InformationCustomerPage/textBoxPhoneNumber'), 
        'placeholder', FailureHandling.STOP_ON_FAILURE), phoneNumber, false, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyMatch(WebUI.getAttribute(findTestObject('Object Repository/Web/Customers/InformationCustomerPage/textBoxFoundingDate'), 
        'placeholder', FailureHandling.STOP_ON_FAILURE), fomatDate, false, FailureHandling.STOP_ON_FAILURE)

//validate small warning
WebUI.click(findTestObject('Object Repository/Web/Customers/InformationCustomerPage/textBoxBusinessRegistrationCode'))

WebUI.click(findTestObject('Object Repository/Web/Customers/InformationCustomerPage/textBoxCompany'))

WebUI.click(findTestObject('Object Repository/Web/Customers/InformationCustomerPage/textBoxEmail'))

WebUI.click(findTestObject('Object Repository/Web/Customers/InformationCustomerPage/textBoxRepresentative'))

WebUI.click(findTestObject('Object Repository/Web/Customers/InformationCustomerPage/textBoxPhoneNumber'))

WebUI.click(findTestObject('Object Repository/Web/Customers/InformationCustomerPage/textBoxAddress'))

AbstractWeb.verifyArrayText('Object Repository/Web/Customers/InformationCustomerPage/smallWarning', businessRegistrationCodeWarning, 
    companyWarning, representativeWarning, emailWarning, phoneNumberWarning)

//invalidate
AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxEmail', 'abc')

AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxPhoneNumber', 'abc')

//WebUI.click(findTestObject('Object Repository/Web/Customers/InformationCustomerPage/textBoxAddress'))

WebUI.verifyEqual(AbstractWeb.getText('Object Repository/Web/Customers/InformationCustomerPage/smallWarning2', [('text') : 'Email không đúng định dạng']), 
    'Email không đúng định dạng', FailureHandling.STOP_ON_FAILURE)

WebUI.verifyEqual(AbstractWeb.getText('Object Repository/Web/Customers/InformationCustomerPage/smallWarning2', [('text') : 'Số điện thoại không đúng định dạng']), 
    'Số điện thoại không đúng định dạng', FailureHandling.STOP_ON_FAILURE)