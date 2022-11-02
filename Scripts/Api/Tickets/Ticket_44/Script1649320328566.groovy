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

WS.callTestCase(findTestCase('Test Cases/Api/Tickets/Ticket_42-43'), [('username') : username, ('password') : password, ('buildingId') : buildingId
	, ('content') : content, ('floorId') : floorId, ('illustrationsFiles') : illustrationsFiles, ('status') : status
	, ('note') : note, ('statusCode') : '200', ('success') : 'true', ('code') : '200', ('serviceType') : serviceType
	, ('statusAfter') : statusAfter, ('expectedFinishAt') : expectedFinishAt])

Authenticate.setToken(GlobalVariable.ACCOUNT_MANAGER_MB001)

ResultSet rs = Statemnet.getTicket(username)

while (rs.next()) {
	String ticketId = rs.getString('ID')

	Map maps = [('ticketId') : ticketId, ('feedback') : feedback, ('inspectionFiles') : inspectionFiles]

	ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Tickets/Confirm Ticket Completion', maps), FailureHandling.STOP_ON_FAILURE)

	WS.verifyMatch(response.statusCode.toString(), statusCode, false, FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyMatch(WS.getElementPropertyValue(response, 'success').toString(), success, false, FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyMatch(WS.getElementPropertyValue(response, 'code').toString(), code, false, FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.buildingId').toString(), buildingId, false, FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.content').toString(), content, false, FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.floorId').toString(), floorId, false, FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.status').toString(), statusEnd, false, FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.serviceType').toString(), serviceType, false, FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.feedback').toString(), feedback, false, FailureHandling.CONTINUE_ON_FAILURE)
}

ConnectionDatabase.closeDatabaseConnection()

if (statusCode == '200') {
	ResultSet rs1 = Statemnet.getTicket(username)

	while (rs1.next()) {
		WS.verifyMatch(rs1.getString('BUILDING_ID'), buildingId, false, FailureHandling.CONTINUE_ON_FAILURE)

		WS.verifyMatch(rs1.getString('FLOOR_ID'), floorId, false, FailureHandling.CONTINUE_ON_FAILURE)

		WS.verifyMatch(rs1.getString('CONTENT'), content, false, FailureHandling.CONTINUE_ON_FAILURE)

		WS.verifyMatch(rs1.getString('SERVICE_TYPE'), serviceType, false, FailureHandling.CONTINUE_ON_FAILURE)

		WS.verifyMatch(rs1.getString('STATUS'), statusEnd, false, FailureHandling.CONTINUE_ON_FAILURE)

		WS.verifyMatch(rs1.getString('FEEDBACK'), feedback, false, FailureHandling.CONTINUE_ON_FAILURE)
	}

	ConnectionDatabase.closeDatabaseConnection()
}

