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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.mobile.AbstractMobile
import com.testData.FakerData
import com.web.AbstractWeb
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys
import org.testng.Assert

AbstractMobile.loginWithAccount(['username' : username, 'password' : password])

Mobile.tap(findTestObject('Object Repository/Mobile/textView', ['text' : 'Cá nhân']), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)
Mobile.tap(findTestObject('Object Repository/Mobile/PersonalScreen/linkInfoUser'), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.verifyElementText(findTestObject('Object Repository/Mobile/textView', ['text' : username]), username, FailureHandling.CONTINUE_ON_FAILURE)

KeywordUtil.logInfo(description)

String fullNameFromDb
String phoneNumberFromDb
String emailFromDb
String dateOfBirth
String gender

ResultSet rs = Statemnet.getQUERY_USER_INFOR(username)
while (rs.next()) {
	fullNameFromDb = rs.getString('FULL_NAME')
	phoneNumberFromDb = rs.getString('PHONE_NUMBER')
	emailFromDb = rs.getString('EMAIL')
	dateOfBirth = rs.getString('DAY_OF_BIRTH')
	gender = rs.getString('GENDER')
}
ConnectionDatabase.closeDatabaseConnection()

if (gender.equals('OTHER')) {
	gender = 'Khác'
} else if (gender.equals('MALE')) {
	gender = 'Nam'
} else {
	gender = 'Nữ'
}

String dateOfBirthDisplay = AbstractWeb.changeDateFormat(dateOfBirth, 'yyyy-MM-dd HH:mm:ss.S', 'dd/MM/yyyy')

String randomFullname
String randomPhoneNumber
String randomEmail
String randomDateOfBirth = FakerData.randomDate()
String randomGender = FakerData.randomGender()

if (fullName.equals('random')) {
	randomFullname = FakerData.randomFullName()
} else {
	randomFullname = fullName
}

if (phoneNumber.equals('random')) {
	randomPhoneNumber = FakerData.randomPhoneNumberNoSpace()
} else {
	randomPhoneNumber = phoneNumber
}

if (email.equals('random')) {
	randomEmail = FakerData.randomEmail()
} else {
	randomEmail = email
}

Map<String, String> mapDate = AbstractMobile.infoDate(dateOfBirthDisplay)
Map<String, String> mapRandomDate = AbstractMobile.infoDate(randomDateOfBirth)

//Input full name
Mobile.setText(findTestObject('Object Repository/Mobile/editText', ['text' : fullNameFromDb]), randomFullname, GlobalVariable.TIME_OUT)

//Input phone number
Mobile.setText(findTestObject('Object Repository/Mobile/editText', ['text' : phoneNumberFromDb]), randomPhoneNumber, GlobalVariable.TIME_OUT)
Mobile.tap(findTestObject('Object Repository/Mobile/editText', ['text' : randomPhoneNumber]), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)

//Input email
Mobile.setText(findTestObject('Object Repository/Mobile/editText', ['text' : emailFromDb]), randomEmail, GlobalVariable.TIME_OUT)
Mobile.tap(findTestObject('Object Repository/Mobile/editText', ['text' : randomEmail]), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)

//Select date of birth
Mobile.tap(findTestObject('Object Repository/Mobile/textView', ['text' : 'Số điện thoại:']), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)
Mobile.tap(findTestObject('Object Repository/Mobile/PersonalScreen/InfoUser/btnCalendar', ['text' : dateOfBirthDisplay]), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)
Mobile.tap(findTestObject('Object Repository/Mobile/textView', ['text' : AbstractMobile.convertDate(mapDate.get('day'), mapDate.get('month'), mapDate.get('year'))]), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Mobile/textView', ['text' : 'Năm']), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)
Mobile.scrollToText(mapRandomDate.get('year'), FailureHandling.CONTINUE_ON_FAILURE)
Mobile.tap(findTestObject('Object Repository/Mobile/textView', ['text' : mapRandomDate.get('year')]), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Mobile/textView', ['text' : 'Tháng']), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)
Mobile.tap(findTestObject('Object Repository/Mobile/textView', ['text' : AbstractMobile.convertMonth(mapRandomDate.get('month'))]), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Mobile/textView', ['text' : AbstractMobile.convertDate(mapDate.get('day'), mapRandomDate.get('month'), mapRandomDate.get('year'))]), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)
Mobile.tap(findTestObject('Object Repository/Mobile/textView', ['text' : mapRandomDate.get('day')]), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Mobile/textView', ['text' : 'Xác nhận']), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)

//Select gender
Mobile.tap(findTestObject('Object Repository/Mobile/PersonalScreen/InfoUser/selectGender', ['text' : gender]), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)
Mobile.verifyElementText(findTestObject('Object Repository/Mobile/textView', ['text' : 'Giới tính']), 'Giới tính', FailureHandling.CONTINUE_ON_FAILURE)
Mobile.tap(findTestObject('Object Repository/Mobile/textView', ['text' : randomGender]), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)

//Verify button not enable
String btnEnable = Mobile.getAttribute(findTestObject('Object Repository/Mobile/PersonalScreen/InfoUser/btnUpdateInfo'), 'enabled', GlobalVariable.TIME_OUT)
if (btnEnable == 'false') {
	Mobile.verifyElementText(findTestObject('Object Repository/Mobile/textView', ['text' : message]), message, FailureHandling.CONTINUE_ON_FAILURE)
	Mobile.pressBack(FailureHandling.CONTINUE_ON_FAILURE)
} else {
	//Verify button enable and verify info after update
	Mobile.verifyEqual(Mobile.getAttribute(findTestObject('Object Repository/Mobile/PersonalScreen/InfoUser/btnUpdateInfo'), 'enabled', GlobalVariable.TIME_OUT), 'true', FailureHandling.CONTINUE_ON_FAILURE)
	
	Mobile.tap(findTestObject('Object Repository/Mobile/PersonalScreen/InfoUser/btnUpdateInfo'), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)
	
	Mobile.verifyElementText(findTestObject('Object Repository/Mobile/textView', ['text' : message]), message, FailureHandling.CONTINUE_ON_FAILURE)
	
	if (message.equals('Dữ liệu nhập không hợp lệ')) {
		Mobile.pressBack(FailureHandling.CONTINUE_ON_FAILURE)
	} else {
		ResultSet rs1 = Statemnet.getQUERY_USER_INFOR(username)
		while (rs1.next()) {
			Mobile.verifyEqual(randomFullname, rs1.getString('FULL_NAME'), FailureHandling.CONTINUE_ON_FAILURE)
			Mobile.verifyEqual(randomPhoneNumber, rs1.getString('PHONE_NUMBER'), FailureHandling.CONTINUE_ON_FAILURE)
			Mobile.verifyEqual(randomEmail, rs1.getString('EMAIL'), FailureHandling.CONTINUE_ON_FAILURE)
			Mobile.verifyEqual(AbstractWeb.changeDateFormat(randomDateOfBirth, 'dd/MM/yyyy', 'yyyy-MM-dd HH:mm:ss.S'), rs1.getString('DAY_OF_BIRTH'), FailureHandling.CONTINUE_ON_FAILURE)
			if (randomGender.equals('Nam')) {
				Mobile.verifyEqual('MALE', rs1.getString('GENDER'), FailureHandling.CONTINUE_ON_FAILURE)
			} else if (randomGender.equals('Nữ')) {
				Mobile.verifyEqual('FEMALE', rs1.getString('GENDER'), FailureHandling.CONTINUE_ON_FAILURE)
			} else {
				Mobile.verifyEqual('OTHER', rs1.getString('GENDER'), FailureHandling.CONTINUE_ON_FAILURE)
			}
		}
		ConnectionDatabase.closeDatabaseConnection()
	}
}