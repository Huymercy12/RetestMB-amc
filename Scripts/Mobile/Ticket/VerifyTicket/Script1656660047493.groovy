import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.sql.ResultSet

import com.database.ConnectionDatabase
import com.database.Statemnet
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.mobile.AbstractMobile
import internal.GlobalVariable
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidElement
import org.openqa.selenium.Keys

AbstractMobile.loginWithAccount(GlobalVariable.MOBILE_APP, ['username' : username, 'password' : password])

//Tap on Yêu cầu
Mobile.tap(findTestObject('Object Repository/Mobile/textView', ['text' : 'Yêu cầu']), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)

//Tap on Chờ xử lý
Mobile.tap(findTestObject('Object Repository/Mobile/Ticket/ticketOpen'), GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)

AppiumDriver<?> driver = MobileDriverFactory.getDriver()
List<AndroidElement> elements = driver.findElementsByXPath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")

for (int i = 0; i < elements.size(); i++) {
	
	elements[i].click()
	
	String buildingId
	String floorId
	String organizationId
	
	ResultSet rs = Statemnet.getTicketByUserAndStatus(username, 'OPEN', i, 1)
	while(rs.next()) {
		
		buildingId = rs.getString('BUILDING_ID')
		floorId = rs.getString('FLOOR_ID')
		organizationId = rs.getString('ORGANIZATION_ID')
		
		//Verify status, content, note if ticket
		Mobile.verifyEqual(rs.getString('STATUS'), 'OPEN', FailureHandling.STOP_ON_FAILURE)
		
		Mobile.verifyEqual(Mobile.getText(findTestObject('Object Repository/Mobile/Ticket/content', ['text' : 'Nội dung:']), GlobalVariable.TIME_OUT), rs.getString('CONTENT'), FailureHandling.CONTINUE_ON_FAILURE)
		
		if (rs.getString('NOTE') == null) {
			Mobile.verifyElementNotExist(findTestObject('Object Repository/Mobile/Ticket/title', ['text' : 'Ghi chú:']), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)
		} else {
			Mobile.verifyEqual(Mobile.getText(findTestObject('Object Repository/Mobile/Ticket/content', ['text' : 'Ghi chú:']), GlobalVariable.TIME_OUT), rs.getString('NOTE'), FailureHandling.CONTINUE_ON_FAILURE)
		}
	}
	ConnectionDatabase.closeDatabaseConnection()
	
	//Verify buiding code
	ResultSet rs1 = Statemnet.getBuildingById(buildingId)
	while (rs1.next()) {
		Mobile.verifyEqual(Mobile.getText(findTestObject('Object Repository/Mobile/Ticket/content', ['text' : 'Toà nhà:']), GlobalVariable.TIME_OUT), rs1.getString('CODE'), FailureHandling.CONTINUE_ON_FAILURE)
	}
	ConnectionDatabase.closeDatabaseConnection()
	
	//verify name floor
	ResultSet rs2 = Statemnet.getFloorById(floorId)
	while (rs2.next()) {
		Mobile.verifyEqual(Mobile.getText(findTestObject('Object Repository/Mobile/Ticket/content', ['text' : 'Tầng:']), GlobalVariable.TIME_OUT), rs2.getString('NAME'), FailureHandling.CONTINUE_ON_FAILURE)
	}
	ConnectionDatabase.closeDatabaseConnection()
	
	//Verify name organization
	ResultSet rs3 = Statemnet.getORGANIZATION_INFOR_BY_ID(organizationId)
	while (rs3.next()) {
		Mobile.verifyEqual(Mobile.getText(findTestObject('Object Repository/Mobile/Ticket/content', ['text' : 'Công ty:']), GlobalVariable.TIME_OUT), rs3.getString('NAME'), FailureHandling.CONTINUE_ON_FAILURE)
	}
	ConnectionDatabase.closeDatabaseConnection()
	
	//Verify issued user
	ResultSet rs4 = Statemnet.getQUERY_USER_INFOR(username)
	while (rs4.next()) {
		Mobile.verifyEqual(Mobile.getText(findTestObject('Object Repository/Mobile/Ticket/content', ['text' : 'Người yêu cầu:']), GlobalVariable.TIME_OUT), rs4.getString('FULL_NAME'), FailureHandling.CONTINUE_ON_FAILURE)
	}
	ConnectionDatabase.closeDatabaseConnection()
	
	//Close ticket
	Mobile.tap(findTestObject('Object Repository/Mobile/Ticket/closeTicket'), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)
}