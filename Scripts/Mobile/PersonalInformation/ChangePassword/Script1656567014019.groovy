import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.database.Statemnet
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
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
import internal.GlobalVariable
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidElement
import org.openqa.selenium.Keys
import org.testng.Assert

KeywordUtil.logInfo(description)

AbstractMobile.loginWithAccount(['username' : username, 'password' : password])

Mobile.tap(findTestObject('Object Repository/Mobile/textView', ['text' : 'Cá nhân']), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Mobile/PersonalScreen/linkChangePassword'), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)

Mobile.verifyElementText(findTestObject('Object Repository/Mobile/textView', ['text' : 'Đổi mật khẩu']), 'Đổi mật khẩu', FailureHandling.CONTINUE_ON_FAILURE)
Mobile.verifyElementText(findTestObject('Object Repository/Mobile/textView', ['text' : 'Mật khẩu cũ:']), 'Mật khẩu cũ:', FailureHandling.CONTINUE_ON_FAILURE)
Mobile.verifyElementText(findTestObject('Object Repository/Mobile/textView', ['text' : 'Mật khẩu mới:']), 'Mật khẩu mới:', FailureHandling.CONTINUE_ON_FAILURE)
Mobile.verifyElementText(findTestObject('Object Repository/Mobile/textView', ['text' : 'Xác nhận mật khẩu mới:']), 'Xác nhận mật khẩu mới:', FailureHandling.CONTINUE_ON_FAILURE)

Mobile.verifyEqual(Mobile.getAttribute(findTestObject('Object Repository/Mobile/PersonalScreen/ChangePassword/btnUpdatePassword'), 'enabled', GlobalVariable.TIME_OUT), 'false', FailureHandling.CONTINUE_ON_FAILURE)

String newPasswordRandom
String confirmPassword

if (newPassword.equals('random')) {
	newPasswordRandom = FakerData.randomPassword()
} else {
	newPasswordRandom = newPassword
}

if (confirmNewPassword.equals('confirm')) {
	confirmPassword = newPasswordRandom
} else if (confirmNewPassword.equals('random')){
	confirmPassword = FakerData.randomPassword()
} else {
	confirmPassword = confirmNewPassword
}
AppiumDriver<?>	driver = MobileDriverFactory.getDriver()
List<AndroidElement> elements = driver.findElementsByClassName("android.widget.EditText")

elements[0].sendKeys(oldPassword)
elements[0].click()

elements[1].sendKeys(newPasswordRandom)
elements[1].click()

elements[2].sendKeys(confirmPassword)
elements[2].click()

Mobile.tap(findTestObject('Object Repository/Mobile/textView', ['text' : 'Đổi mật khẩu']), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)

String isBtnEnable = Mobile.getAttribute(findTestObject('Object Repository/Mobile/PersonalScreen/ChangePassword/btnUpdatePassword'), 'enabled', GlobalVariable.TIME_OUT)

if (isBtnEnable == 'true') {
	Mobile.tap(findTestObject('Object Repository/Mobile/PersonalScreen/ChangePassword/btnUpdatePassword'), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)
	
	Mobile.verifyElementText(findTestObject('Object Repository/Mobile/textView', ['text' : message]), message, FailureHandling.CONTINUE_ON_FAILURE)
	if (message.equals('Đổi mật khẩu thành công')) {
		Mobile.tap(findTestObject('Object Repository/Mobile/PersonalScreen/ChangePassword/btnAccept'), GlobalVariable.TIME_OUT, FailureHandling.CONTINUE_ON_FAILURE)
		
		AbstractMobile.loginWithAccount(['username' : username, 'password' : newPasswordRandom])
		
		Mobile.tap(findTestObject('Object Repository/Mobile/textView', ['text' : 'Cá nhân']), GlobalVariable.TIME_OUT)
		String nameUser = Statemnet.getFullNameUser(username)
		Mobile.verifyElementText(findTestObject('Object Repository/Mobile/textView', ['text' : nameUser]), nameUser, FailureHandling.CONTINUE_ON_FAILURE)
		
		Mobile.callTestCase(findTestCase('Test Cases/Api/IAM/IAM_181'), ['username' : username, 'oldPassword' : newPasswordRandom, 'newPassword' : password, 'statusCode' : '200', 'success' : 'true'], FailureHandling.CONTINUE_ON_FAILURE)
	} else {
		Mobile.pressBack()
	}
} else {
	if (message.equals('')) {
		Mobile.pressBack()
	} else {
		Mobile.verifyElementText(findTestObject('Object Repository/Mobile/textView', ['text' : message]), message, FailureHandling.CONTINUE_ON_FAILURE)
		Mobile.pressBack()
	}
	
}

