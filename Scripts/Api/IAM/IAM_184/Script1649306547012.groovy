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

Authenticate.setToken([('username'):username,('password'):password])

ResponseObject response =WS.sendRequestAndVerify(findTestObject('Object Repository/Api/IAM/Get Organization By Role of Current User Login'), FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyEqual(response.statusCode.toString(), statusCode, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyEqual(WS.getElementPropertyValue(response, 'success').toString(), success, FailureHandling.CONTINUE_ON_FAILURE)

ResultSet rs = Statemnet.getOrganizationByRoleOfCurrentUserLogin(username)

while (rs.next()) {
	WS.verifyEqual(rs.getString('ID'), WS.getElementPropertyValue(response, 'data[0].id').toString(), FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyEqual(rs.getString('NAME').toString(), WS.getElementPropertyValue(response, 'data[0].name').toString(), FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyEqual(rs.getString('CODE'), WS.getElementPropertyValue(response, 'data[0].code').toString(), FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyEqual(rs.getString('STATUS').toString(), WS.getElementPropertyValue(response, 'data[0].status').toString(), FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyEqual(rs.getString('TYPE'), WS.getElementPropertyValue(response, 'data[0].type').toString(), FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyEqual(rs.getString('INSTITUTION_TYPE'), WS.getElementPropertyValue(response, 'data[0].institutionType').toString(), FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyEqual(rs.getString('POSITION'), WS.getElementPropertyValue(response, 'data[0].position').toString(), FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyEqual(rs.getString('BUSINESS_ADDRESS'), WS.getElementPropertyValue(response, 'data[0].businessAddress').toString(), FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyEqual(rs.getString('CONTACT_ADDRESS'), WS.getElementPropertyValue(response, 'data[0].contactAddress').toString(), FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyEqual(rs.getString('LEGAL_REPRESENTATIVE'), WS.getElementPropertyValue(response, 'data[0].legalRepresentative').toString(), FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyEqual(rs.getString('BUSINESS_CODE'), WS.getElementPropertyValue(response, 'data[0].businessCode').toString(), FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyEqual(rs.getString('INVOICE_ISSUING_ADDRESS'), WS.getElementPropertyValue(response, 'data[0].invoiceIssuingAddress').toString(), FailureHandling.CONTINUE_ON_FAILURE)
}

ConnectionDatabase.closeDatabaseConnection()