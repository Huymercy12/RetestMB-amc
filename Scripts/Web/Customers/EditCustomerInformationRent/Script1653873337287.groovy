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
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.testData.DataExcelUtils as DataExcelUtils
import com.testData.FakerData as FakerData
import com.testData.VariableCollections as VariableCollections
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

import com.api.Authenticate as Authenticate
import com.database.*
import java.sql.*

WebDriver driver = DriverFactory.getWebDriver()

Map map = DataExcelUtils.getDataByIndex(3, 'Data Files/Web/Customers/CreateInformationRent')

WebUI.callTestCase(findTestCase('Test Cases/Web/Customers/CreateInformationRent'), map, FailureHandling.STOP_ON_FAILURE)

if(action.toString().equalsIgnoreCase("delete")) {
	AbstractWeb.clickButton('Object Repository/Web/Customers/InformationRent/buttonDelete')
	AbstractWeb.clickButton('Object Repository/Web/Customers/InformationRent/buttonConfirm')
	WebUI.verifyElementVisible(findTestObject('Object Repository/Web/imageEmpty'), FailureHandling.STOP_ON_FAILURE)
	ResultSet rs = Statemnet.getUnitByCode(VariableCollections.map.get('unitCode'))
	
	while (rs.next()) {
		WebUI.verifyEqual(rs.getString('LEASING_STATUS'), 'AVAILABLE', FailureHandling.STOP_ON_FAILURE)
	}
	ConnectionDatabase.closeDatabaseConnection()
}else if(action.toString().equalsIgnoreCase('paid')) {
	AbstractWeb.clickButton('Object Repository/Web/Customers/InformationRent/buttonPaid')
	AbstractWeb.clickButton('Object Repository/Web/Customers/InformationRent/buttonConfirm')
	
	List<WebElement> element = WebUI.findWebElements(findTestObject('Object Repository/Web/Customers/InformationRent/cellTable'),
		GlobalVariable.TIME_OUT)

	WebUI.verifyEqual((element[4]).getText(), status, FailureHandling.STOP_ON_FAILURE)
	
	ResultSet rs = Statemnet.getUnitByCode(VariableCollections.map.get('unitCode'))
	
	while (rs.next()) {
		WebUI.verifyEqual(rs.getString('LEASING_STATUS'), 'LEASED', FailureHandling.STOP_ON_FAILURE)
	}
	ConnectionDatabase.closeDatabaseConnection()
}else {
	AbstractWeb.clickButton('Object Repository/Web/Customers/InformationRent/buttonReturned')
	AbstractWeb.clickButton('Object Repository/Web/Customers/InformationRent/buttonConfirm')
	
	List<WebElement> element = WebUI.findWebElements(findTestObject('Object Repository/Web/Customers/InformationRent/cellTable'),
		GlobalVariable.TIME_OUT)

	WebUI.verifyEqual((element[4]).getText(), status, FailureHandling.STOP_ON_FAILURE)
	
	ResultSet rs = Statemnet.getUnitByCode(VariableCollections.map.get('unitCode'))
	
	while (rs.next()) {
		WebUI.verifyEqual(rs.getString('LEASING_STATUS'), 'RETURNED', FailureHandling.STOP_ON_FAILURE)
	}
	ConnectionDatabase.closeDatabaseConnection()
}
