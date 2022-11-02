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


Map maps = [('username') : username, ('password') : password]

if(error=='LOGIN_FAIL_BLOCK_ACCOUNT') {
	WS.callTestCase(findTestCase('Api/IAM/IAM_103'), [('username'):username,('statusCode'):'200',('success'):'true'], FailureHandling.STOP_ON_FAILURE)
	ResultSet rs=Statemnet.getQUERY_USER_INFOR(username)
	while (rs.next()) {
		WS.verifyMatch(rs.getString('STATUS'), 'INACTIVE', false, FailureHandling.CONTINUE_ON_FAILURE)
	}
}

ResponseObject response = Authenticate.setToken(maps)

WS.verifyMatch(response.statusCode.toString(), statusCode, false, FailureHandling.STOP_ON_FAILURE)

if (statusCode == '200') {
	assertTrue(WS.getElementPropertyValue(response, 'data.accessToken').toString().contains(accessToken))
	assertTrue(WS.getElementPropertyValue(response, 'data.refreshToken').toString().contains(refreshToken))
	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.expiresIn').toString(), expiresIn, false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.tokenType').toString(), tokenType, false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyMatch(WS.getElementPropertyValue(response, 'success').toString(), success, false, FailureHandling.CONTINUE_ON_FAILURE)
	ResultSet rs2=Statemnet.getQUERY_USER_INFOR(username)
	while (rs2.next()) {
		WS.verifyMatch(rs2.getString('USERNAME'), username, false, FailureHandling.CONTINUE_ON_FAILURE)
	}
	
} else if (statusCode=='400') {
	WS.verifyMatch(WS.getElementPropertyValue(response, 'error').toString(), error, false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyMatch(WS.getElementPropertyValue(response, 'success').toString(), success, false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyMatch(WS.getElementPropertyValue(response, 'message').toString(), message, false, FailureHandling.CONTINUE_ON_FAILURE)
}
