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
String fullNameRandom
String phoneNumberRandom
String dayOfBirthRandom

if (email == 'random') {
	emailRandom = FakerData.randomEmail()
} else {
	emailRandom = email
}

if (fullName == 'random') {
	fullNameRandom = FakerData.randomFullName()
} else {
	fullNameRandom = fullName
}

if (phoneNumber == 'random') {
	phoneNumberRandom = FakerData.randomPhoneNumber()
} else {
	phoneNumberRandom = phoneNumber
}


if (dayOfBirth == 'random') {
	dayOfBirthRandom = FakerData.randomDate('yyyy-MM-dd')
} else {
	dayOfBirthRandom = dayOfBirth
}

Authenticate.setToken([('username'):username,('password'):password])

Map maps = [('email') : emailRandom, ('fullName') : fullNameRandom,('phoneNumber') : phoneNumberRandom, ('dayOfBirth') : dayOfBirthRandom, ('gender') : gender]

ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/IAM/Update my profile', maps), FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyEqual(response.statusCode.toString(), statusCode, FailureHandling.CONTINUE_ON_FAILURE)

// request vs response
	
WS.verifyEqual(WS.getElementPropertyValue(response, 'data.email').toString(), emailRandom, FailureHandling.CONTINUE_ON_FAILURE)
	
WS.verifyEqual(WS.getElementPropertyValue(response, 'data.fullName').toString(), fullNameRandom, FailureHandling.CONTINUE_ON_FAILURE)
	
WS.verifyEqual(WS.getElementPropertyValue(response, 'data.phoneNumber').toString(), phoneNumberRandom, FailureHandling.CONTINUE_ON_FAILURE)
	
WS.verifyEqual(WS.getElementPropertyValue(response, 'data.dayOfBirth').toString(), dayOfBirthRandom, FailureHandling.CONTINUE_ON_FAILURE)
	
WS.verifyEqual(WS.getElementPropertyValue(response, 'data.gender').toString(), gender, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyEqual(WS.getElementPropertyValue(response, 'success').toString(), success, FailureHandling.CONTINUE_ON_FAILURE)

ResultSet rs = Statemnet.getQUERY_USER_INFOR(username)

while (rs.next()) {
	WS.verifyEqual(rs.getString('FULL_NAME'), fullNameRandom, FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyEqual(rs.getString('EMAIL'), emailRandom, FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyEqual(rs.getString('PHONE_NUMBER'), phoneNumberRandom, FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyEqual(rs.getString('DAY_OF_BIRTH'), FakerData.parseDate(dayOfBirthRandom), FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyEqual(rs.getString('GENDER'),gender, FailureHandling.CONTINUE_ON_FAILURE)
}
	
ConnectionDatabase.closeDatabaseConnection()