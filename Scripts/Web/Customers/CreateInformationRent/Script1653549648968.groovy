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

Map map = DataExcelUtils.getDataByIndex(2, 'Data Files/Web/Customers/CreateNewCustomerInformation')

WebUI.callTestCase(findTestCase('Test Cases/Web/Customers/CreateNewCustomerInformation'), map, FailureHandling.STOP_ON_FAILURE)

Map map2 = DataExcelUtils.getDataByTestCaseId('15', 'Data Files/Api/Building/Create Unit')

Authenticate.setToken(GlobalVariable.ACCOUNT_CENTER)

WS.callTestCase(findTestCase('Test Cases/Api/Building/Building_15-19'), map2, FailureHandling.STOP_ON_FAILURE)

ResponseObject response = VariableCollections.map.getAt('response')

AbstractWeb.clickButton('Object Repository/Web/Customers/InformationRent/buttonCreate')

String buildingCode

String floorname

String unitCode = WS.getElementPropertyValue(response, 'data.code', FailureHandling.STOP_ON_FAILURE)

ResultSet rs = Statemnet.getBuildingById(WS.getElementPropertyValue(response, 'data.buildingId', FailureHandling.STOP_ON_FAILURE))

while (rs.next()) {
    buildingCode = rs.getString('CODE')
}

ConnectionDatabase.closeDatabaseConnection()

ResultSet rs2 = Statemnet.getFloorById(WS.getElementPropertyValue(response, 'data.floorId', FailureHandling.STOP_ON_FAILURE))

while (rs2.next()) {
    floorname = rs2.getString('NAME')
}

ConnectionDatabase.closeDatabaseConnection()

WebUI.sendKeys(findTestObject('Object Repository/Web/Customers/InformationRent/textBoxBuildingCode'), buildingCode + Keys.ENTER, 
    FailureHandling.STOP_ON_FAILURE)

WebUI.sendKeys(findTestObject('Object Repository/Web/Customers/InformationRent/textBoxFloor'), floorname + Keys.ENTER, FailureHandling.STOP_ON_FAILURE)

WebUI.sendKeys(findTestObject('Object Repository/Web/Customers/InformationRent/textBoxUnit'), unitCode + Keys.ENTER, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Web/Customers/InformationRent/selectLeasingStatus'), FailureHandling.STOP_ON_FAILURE)

AbstractWeb.moveToElementAndClick('Object Repository/Web/Customers/InformationRent/optionSelect', [('text') : status])

if (action.toString().equalsIgnoreCase('cancel')) {
    AbstractWeb.clickButton('Object Repository/Web/Customers/InformationRent/buttonClose')

    WebUI.verifyElementVisible(findTestObject('Object Repository/Web/imageEmpty'), FailureHandling.STOP_ON_FAILURE)
} else {
    AbstractWeb.clickButton('Object Repository/Web/Customers/InformationRent/buttonSave')

    AbstractWeb.verifyMessage('Object Repository/Web/message', message)

    List<WebElement> element = WebUI.findWebElements(findTestObject('Object Repository/Web/Customers/InformationRent/cellTable'), 
        GlobalVariable.TIME_OUT)

    WebUI.verifyEqual((element[1]).getText(), buildingCode, FailureHandling.STOP_ON_FAILURE)

    WebUI.verifyEqual((element[2]).getText(), floorname, FailureHandling.STOP_ON_FAILURE)

    WebUI.verifyEqual((element[3]).getText(), unitCode, FailureHandling.STOP_ON_FAILURE)

    WebUI.verifyEqual((element[4]).getText(), status, FailureHandling.STOP_ON_FAILURE)

    ResultSet rs3 = Statemnet.getUnitByCode(unitCode)
	
	VariableCollections.map.put('unitCode', unitCode)

    if (status.toString().equalsIgnoreCase('Đã cho thuê')) {
        while (rs3.next()) {
            WebUI.verifyEqual(rs3.getString('LEASING_STATUS'), 'LEASED', FailureHandling.STOP_ON_FAILURE)
        }
    }
    
    if (status.toString().equalsIgnoreCase('Đã đặt cọc')) {
        while (rs3.next()) {
            WebUI.verifyEqual(rs3.getString('LEASING_STATUS'), 'DEPOSIT', FailureHandling.STOP_ON_FAILURE)
        }
    }
	
	ConnectionDatabase.closeDatabaseConnection()
}