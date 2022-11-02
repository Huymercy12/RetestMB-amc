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

Authenticate.setToken(GlobalVariable.ACCOUNT_SERVICES_MONITOR)

Map maps = [('buildingId') : buildingId, ('content') : content, ('floorId') : floorId
	, ('illustrationsFiles') : illustrationsFiles, ('issuedUserId') : issuedUserId, ('note') : note, ('organizationId') : organizationId
	, ('serviceType') : serviceType]

ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Tickets/Ticket Issued By Employee', maps), FailureHandling.STOP_ON_FAILURE)

WS.verifyMatch(WS.getElementPropertyValue(response, 'success').toString(), success, false, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(WS.getElementPropertyValue(response, 'code').toString(), code, false, FailureHandling.CONTINUE_ON_FAILURE)

if (statusCode == '200') {
	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.buildingId').toString(), buildingId, false, FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.content').toString(), content, false, FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.floorId').toString(), floorId, false, FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.illustrationsFiles[0].fileId').toString(), illustrationsFiles,
			false, FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.issuedUserId').toString(), issuedUserId, false, FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.note').toString(), note, false, FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.organizationId').toString(), organizationId, false, FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.serviceType').toString(), serviceType, false, FailureHandling.CONTINUE_ON_FAILURE)

	ResultSet rs = Statemnet.getTicket(username)

	while (rs.next()) {
		WS.verifyMatch(rs.getString('BUILDING_ID'), buildingId, false, FailureHandling.CONTINUE_ON_FAILURE)

		WS.verifyMatch(rs.getString('CONTENT'), content, false, FailureHandling.CONTINUE_ON_FAILURE)

		WS.verifyMatch(rs.getString('FLOOR_ID'), floorId, false, FailureHandling.CONTINUE_ON_FAILURE)

		WS.verifyMatch(rs.getString('ORGANIZATION_ID'), organizationId, false, FailureHandling.CONTINUE_ON_FAILURE)
	}

	ConnectionDatabase.closeDatabaseConnection()
} else {
	WS.verifyMatch(WS.getElementPropertyValue(response, 'message').toString(), message, false, FailureHandling.CONTINUE_ON_FAILURE)
}