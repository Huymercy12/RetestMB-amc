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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.testData.FakerData as FakerData
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.web.AbstractWeb as AbstractWeb
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import static org.junit.Assert.assertTrue
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.interactions.Actions as Actions
import org.testng.Assert as Assert

import com.database.*
import java.sql.*

WebDriver driver = DriverFactory.getWebDriver()

Actions actions = new Actions(driver)

WebUI.callTestCase(findTestCase('Test Cases/Web/Customers/CreateNewCustomerInformation'), [('businessRegistrationCode') : businessRegistrationCode
        , ('company') : company, ('email') : email, ('address') : address, ('representative') : representative, ('phoneNumber') : phoneNumber
        , ('foundingDate') : foundingDate, ('type') : type, ('status') : status, ('message') : message, ('infor') : infor
        , ('action') : action], FailureHandling.STOP_ON_FAILURE)

AbstractWeb.clickButton('Object Repository/Web/Customers/InformationCustomerPage/tabInformationRent')

//verify table header
AbstractWeb.verifyArrayText('Object Repository/Web/Customers/headerTable', tableIndex, tableBuildingCode, tableFloor, tableUnitCode, 
    tableStatus, tableAction)

WebUI.verifyElementVisible(findTestObject('Object Repository/Web/imageEmpty'), FailureHandling.STOP_ON_FAILURE)

AbstractWeb.verifyText('Object Repository/Web/Buildings/messageNoRecord', messageNoRecord)

AbstractWeb.clickButton('Object Repository/Web/Customers/InformationRent/buttonCreate')

AbstractWeb.verifyVisible('Object Repository/Web/Customers/InformationRent/buttonClose')

AbstractWeb.verifyVisible('Object Repository/Web/Customers/InformationRent/buttonSave')

//validate label
AbstractWeb.verifyArrayText('Object Repository/Web/Customers/InformationRent/label', labelBuildingCode, labelFloor, labelUnitCode, 
    labelStatus)

//validate placeholder
AbstractWeb.verifyText('Object Repository/Web/Customers/InformationRent/placeholer', [('text') : placeholerBuildingCode], 
    placeholerBuildingCode)

AbstractWeb.verifyText('Object Repository/Web/Customers/InformationRent/placeholer', [('text') : placeholerFloor], placeholerFloor)

AbstractWeb.verifyText('Object Repository/Web/Customers/InformationRent/placeholer', [('text') : placeholerUnitCode], placeholerUnitCode)

//validate small warning
WebUI.click(findTestObject('Object Repository/Web/Customers/InformationRent/textBoxBuildingCode'))

WebUI.click(findTestObject('Object Repository/Web/Customers/InformationRent/textBoxFloor'))

WebUI.click(findTestObject('Object Repository/Web/Customers/InformationRent/textBoxUnit'))

WebUI.click(findTestObject('Object Repository/Web/Customers/InformationRent/selectLeasingStatus'))

AbstractWeb.verifyArrayText('Object Repository/Web/Customers/Contact/smallWarning', warningBuildingCode, warningFloor, warningUnitCode)