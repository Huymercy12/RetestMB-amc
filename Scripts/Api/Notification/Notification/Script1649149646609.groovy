import com.kms.katalon.core.model.FailureHandling as FailureHandling
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.entity.global.GlobalVariableEntity as GlobalVariableEntity
import internal.GlobalVariable as GlobalVariable
import static org.junit.Assert.assertTrue
import org.openqa.selenium.Keys as Keys
import com.api.*
import com.database.*
import com.testData.*
import java.sql.*
import org.json.*

Authenticate.setToken([('username') : username, ('password') : password])

Map maps = [
	('buildingIds') : buildingIds, 
	('title') : title, 
	('content') : content, 
    ('expectedNotificationAt'): FakerData.getUnixTimestamp(),
	('eventTargetType') : eventTargetType, 
	('eventType') : eventType, 
	('eventLevel') : eventLevel ]
println maps
ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Notification/Create Notification', maps), FailureHandling.STOP_ON_FAILURE)
println response.getResponseText()
WS.verifyMatch(response.statusCode.toString(), statusCode, false, FailureHandling.CONTINUE_ON_FAILURE)

ResultSet rs1 = Statemnet.getIDNotification()

while(rs1.next()) {
	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.id').toString(), rs1.getString('ID').toString(), false, FailureHandling.CONTINUE_ON_FAILURE)
}

ConnectionDatabase.closeDatabaseConnection()
