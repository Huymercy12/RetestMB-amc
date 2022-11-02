import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.database.ConnectionDatabase
import com.database.Statemnet
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.web.AbstractWeb
import internal.GlobalVariable
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import java.sql.*

WebDriver driver = DriverFactory.getWebDriver()
KeywordUtil.logInfo(description)
AbstractWeb.waitAndSearch('Object Repository/Web/Buildings/placeholderSearch', buildingCode)

String statusBuilding

ResultSet rs = Statemnet.getInforBuildingSearch(buildingCode)
while (rs.next()) {
	statusBuilding = rs.getString('STATUS')
}
ConnectionDatabase.closeDatabaseConnection()

if (statusBuilding.equals('ACTIVE')) {
	WebElement buttonLock = WebUI.findWebElement(findTestObject('Object Repository/Web/Buildings/listButtonLockInTable'), GlobalVariable.TIME_OUT)
	String buildingId
	String userId
	
	ResultSet rs1 = Statemnet.getInforBuildingSearch(buildingCode)
	while (rs1.next()) {
		buildingId = rs1.getString('ID')
	}
	ConnectionDatabase.closeDatabaseConnection()
	
	buttonLock.click()
	
	//Verify display
	AbstractWeb.verifyText('Object Repository/Web/Buildings/titlePopup', titlePopupLock)
	AbstractWeb.verifyText('Object Repository/Web/Buildings/contentPopup', AbstractWeb.contentPopupLock(buildingCode))
	AbstractWeb.verifyVisible('Object Repository/Web/Buildings/buttonCancelPopup')
	AbstractWeb.verifyVisible('Object Repository/Web/Buildings/buttonAcceptPopup')
	
	//Verify button cancel
	WebUI.click(findTestObject('Object Repository/Web/Buildings/buttonCancelPopup'), FailureHandling.STOP_ON_FAILURE)
	ResultSet rs2 = Statemnet.getInforBuildingSearch(buildingCode)
	while (rs2.next()) {
		WebUI.verifyMatch(rs2.getString('STATUS'), 'ACTIVE', false, FailureHandling.STOP_ON_FAILURE)
	}
	ConnectionDatabase.closeDatabaseConnection()
	
	//Check member in building
	ResultSet rs3 = Statemnet.getUserLocation(buildingId)
	while (rs3.next()) {
		userId = rs3.getString('USER_ID')
	}
	ConnectionDatabase.closeDatabaseConnection()
	
	buttonLock.click()
	
	//Inactive building
	AbstractWeb.clickButton('Object Repository/Web/Buildings/buttonAcceptPopup')
	if (userId != null) {
		AbstractWeb.verifyMessage('Object Repository/Web/message', message)
	} else {
		ResultSet rs4 = Statemnet.getInforBuildingSearch(buildingCode)
		while (rs4.next()) {
			WebUI.verifyMatch(rs4.getString('STATUS'), 'INACTIVE', false, FailureHandling.STOP_ON_FAILURE)
		}
		ConnectionDatabase.closeDatabaseConnection()
	}
} else {
	WebElement buttonUnlock = WebUI.findWebElement(findTestObject('Object Repository/Web/Buildings/listButtonUnlockInTable'), GlobalVariable.TIME_OUT)
	
	buttonUnlock.click()
	//Verify display
	AbstractWeb.verifyText('Object Repository/Web/Buildings/titlePopup', titlePopupUnlock)
	AbstractWeb.verifyText('Object Repository/Web/Buildings/contentPopup', AbstractWeb.contentPopupUnlock(buildingCode))
	AbstractWeb.verifyVisible('Object Repository/Web/Buildings/buttonCancelPopup')
	AbstractWeb.verifyVisible('Object Repository/Web/Buildings/buttonAcceptPopup')
	
	//Verify button cancel
	WebUI.click(findTestObject('Object Repository/Web/Buildings/buttonCancelPopup'), FailureHandling.STOP_ON_FAILURE)
	ResultSet rs5 = Statemnet.getInforBuildingSearch(buildingCode)
	while (rs5.next()) {
		WebUI.verifyMatch(rs5.getString('STATUS'), 'INACTIVE', false, FailureHandling.STOP_ON_FAILURE)
	}
	ConnectionDatabase.closeDatabaseConnection()
	
	//Active building
	buttonUnlock.click()
	AbstractWeb.clickButton('Object Repository/Web/Buildings/buttonAcceptPopup')
	ResultSet rs6 = Statemnet.getInforBuildingSearch(buildingCode)
	while (rs6.next()) {
		WebUI.verifyMatch(rs6.getString('STATUS'), 'ACTIVE', false, FailureHandling.STOP_ON_FAILURE)
	}
	ConnectionDatabase.closeDatabaseConnection()
}