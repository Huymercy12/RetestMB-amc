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
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

Map map = ['id' : id]
String status

ResultSet rs = Statemnet.getORGANIZATION_INFOR_BY_ID(id)
while(rs.next()) {
	status = rs.getString('STATUS')
}
ConnectionDatabase.closeDatabaseConnection()

if (status.equals('ACTIVE')) {
	ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/IAM/Inactive Organization', map), FailureHandling.STOP_ON_FAILURE)
	WS.verifyMatch(response.statusCode.toString(), statusCode, false, FailureHandling.STOP_ON_FAILURE)
	WS.verifyMatch(WS.getElementPropertyValue(response, 'success').toString(), success, false, FailureHandling.CONTINUE_ON_FAILURE)
	
	ResultSet rs1 = Statemnet.getORGANIZATION_INFOR_BY_ID(id)
	while(rs1.next()) {
		WS.verifyEqual(rs1.getString('STATUS'), 'INACTIVE', FailureHandling.CONTINUE_ON_FAILURE)
	}
	ConnectionDatabase.closeDatabaseConnection()
} else {
	ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/IAM/Active Organization', map), FailureHandling.STOP_ON_FAILURE)
	WS.verifyMatch(response.statusCode.toString(), statusCode, false, FailureHandling.STOP_ON_FAILURE)
	WS.verifyMatch(WS.getElementPropertyValue(response, 'success').toString(), success, false, FailureHandling.CONTINUE_ON_FAILURE)
	
	ResultSet rs2 = Statemnet.getORGANIZATION_INFOR_BY_ID(id)
	while(rs2.next()) {
		WS.verifyEqual(rs2.getString('STATUS'), 'ACTIVE', FailureHandling.CONTINUE_ON_FAILURE)
	}
	ConnectionDatabase.closeDatabaseConnection()
}
