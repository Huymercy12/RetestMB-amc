import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.sql.ResultSet
import org.openqa.selenium.By
import com.database.ConnectionDatabase
import com.database.Statemnet
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.mobile.AbstractMobile
import com.web.AbstractWeb
import internal.GlobalVariable
import io.appium.java_client.AppiumDriver
import io.appium.java_client.TouchAction
import io.appium.java_client.android.AndroidElement

AbstractMobile.loginWithAccount(['username' : username, 'password' : password])

Mobile.tap(findTestObject('Object Repository/Mobile/textView', ['text' : 'Cá nhân']), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)
Mobile.tap(findTestObject('Object Repository/Mobile/PersonalScreen/linkInfoUser'), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.delay(2)
Mobile.verifyElementText(findTestObject('Object Repository/Mobile/PersonalScreen/InfoUser/titleInfoUser'), 'Thông tin cá nhân', FailureHandling.CONTINUE_ON_FAILURE)
Mobile.verifyElementText(findTestObject('Object Repository/Mobile/textView', ['text' : username]), username, FailureHandling.CONTINUE_ON_FAILURE)

String fullName
String phoneNumber
String email
String dateOfBirth
String gender

ResultSet rs = Statemnet.getQUERY_USER_INFOR(username)
while (rs.next()) {
	fullName = rs.getString('FULL_NAME')
	phoneNumber = rs.getString('PHONE_NUMBER')
	email = rs.getString('EMAIL')
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

KeywordUtil.logInfo(dateOfBirth)
String dateOfBirthDisplay = AbstractWeb.changeDateFormat(dateOfBirth, 'yyyy-MM-dd HH:mm:ss.S', 'dd/MM/yyyy')

Mobile.verifyElementText(findTestObject('Object Repository/Mobile/textView', ['text' : 'Họ tên:']), 'Họ tên:', FailureHandling.CONTINUE_ON_FAILURE)
Mobile.verifyElementText(findTestObject('Object Repository/Mobile/editText', ['text' : fullName]), fullName, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.verifyElementText(findTestObject('Object Repository/Mobile/textView', ['text' : 'Số điện thoại:']), 'Số điện thoại:', FailureHandling.CONTINUE_ON_FAILURE)
Mobile.verifyElementText(findTestObject('Object Repository/Mobile/editText', ['text' : phoneNumber]), phoneNumber, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.verifyElementText(findTestObject('Object Repository/Mobile/textView', ['text' : 'Email:']), 'Email:', FailureHandling.CONTINUE_ON_FAILURE)
Mobile.verifyElementText(findTestObject('Object Repository/Mobile/editText', ['text' : email]), email, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.verifyElementText(findTestObject('Object Repository/Mobile/textView', ['text' : 'Ngày sinh:']), 'Ngày sinh:', FailureHandling.CONTINUE_ON_FAILURE)
Mobile.verifyElementText(findTestObject('Object Repository/Mobile/editText', ['text' : dateOfBirthDisplay]), dateOfBirthDisplay, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.verifyElementText(findTestObject('Object Repository/Mobile/textView', ['text' : 'Giới tính:']), 'Giới tính:', FailureHandling.CONTINUE_ON_FAILURE)
Mobile.verifyElementText(findTestObject('Object Repository/Mobile/editText', ['text' : gender]), gender, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.verifyEqual(Mobile.getAttribute(findTestObject('Object Repository/Mobile/PersonalScreen/InfoUser/btnUpdateInfo'), 'enabled', GlobalVariable.TIME_OUT), 'true', FailureHandling.CONTINUE_ON_FAILURE)
Mobile.pressBack()