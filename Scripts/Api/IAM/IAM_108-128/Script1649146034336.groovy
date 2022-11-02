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

String emailRandom

String phoneNumberRandom

String usernameRandom

if (email == 'random') {
	emailRandom = FakerData.randomEmail()
} else {
	emailRandom = email
}

if (phoneNumber == 'random') {
	phoneNumberRandom = FakerData.randomPhoneNumber()
} else {
	phoneNumberRandom = phoneNumber
}

if (username == 'random') {
	usernameRandom = FakerData.randomUsername()
} else if (username == 'random2') {
	usernameRandom = FakerData.randomUsername2()
} else {
	usernameRandom = username
}

Map maps=[('email'):emailRandom,('fullName'):fullName,('gender'):gender,('password'):password,('phoneNumber'):phoneNumberRandom,('username'):usernameRandom]

ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/IAM/Register', maps), FailureHandling.STOP_ON_FAILURE)
//WS.verifyEqual(findWindowsObject, findTestCase, FailureHandling.CONTINUE_ON_FAILURE)
WS.verifyEqual(response.statusCode.toString(), statusCode, FailureHandling.STOP_ON_FAILURE)

if (statusCode == '200') {
	// request vs response
	WS.verifyEqual(WS.getElementPropertyValue(response, 'data.username').toString(), usernameRandom, FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyEqual(WS.getElementPropertyValue(response, 'data.fullName').toString(), fullName, FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyEqual(WS.getElementPropertyValue(response, 'data.email').toString(), emailRandom, FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyEqual(WS.getElementPropertyValue(response, 'data.phoneNumber').toString(), phoneNumberRandom, FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyEqual(WS.getElementPropertyValue(response, 'data.gender').toString(), gender, FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyEqual(WS.getElementPropertyValue(response, 'success').toString(), success, FailureHandling.CONTINUE_ON_FAILURE)

	ResultSet rs = Statemnet.getQUERY_USER(usernameRandom)

	while (rs.next()) {
		WS.verifyEqual(rs.getString('USERNAME'), usernameRandom, FailureHandling.CONTINUE_ON_FAILURE)

		WS.verifyEqual(rs.getString('FULL_NAME'), fullNameRandom, FailureHandling.CONTINUE_ON_FAILURE)

		WS.verifyEqual(rs.getString('EMAIL'), emailRandom, FailureHandling.CONTINUE_ON_FAILURE)

		WS.verifyEqual(rs.getString('PHONE_NUMBER'), phoneNumberRandom, FailureHandling.CONTINUE_ON_FAILURE)

		WS.verifyEqual(rs.getString('GENDER'), gender, FailureHandling.CONTINUE_ON_FAILURE)

	}
	
	ConnectionDatabase.closeDatabaseConnection()

	ResponseObject response1 = Authenticate.setToken([('username') : usernameRandom, ('password') : password])

	WS.verifyEqual(response1.statusCode.toString(), '200', FailureHandling.STOP_ON_FAILURE)
	
} else if (statusCode == '400') {
	WS.verifyEqual(WS.getElementPropertyValue(response, 'message').toString(), message, FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyEqual(WS.getElementPropertyValue(response, 'error').toString(), error, FailureHandling.CONTINUE_ON_FAILURE)
}































