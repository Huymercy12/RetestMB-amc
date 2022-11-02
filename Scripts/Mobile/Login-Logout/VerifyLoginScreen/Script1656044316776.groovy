import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
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

import internal.GlobalVariable
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

//Verify display textbox username
Mobile.verifyElementText(findTestObject('Object Repository/Mobile/Login-Logout/titleUsername'), 'Tên đăng nhập:', FailureHandling.STOP_ON_FAILURE)
Mobile.verifyElementText(findTestObject('Object Repository/Mobile/editText', ['text' :'Nhập tên đăng nhập']), 'Nhập tên đăng nhập', FailureHandling.STOP_ON_FAILURE)

//Verify display textbox password
Mobile.verifyElementText(findTestObject('Object Repository/Mobile/Login-Logout/titlePassword'), 'Mật khẩu:', FailureHandling.STOP_ON_FAILURE)
Mobile.verifyElementText(findTestObject('Object Repository/Mobile/editText', ['text' : 'Nhập mật khẩu']), 'Nhập mật khẩu', FailureHandling.STOP_ON_FAILURE)

//Input password and verify hiden password
Mobile.setText(findTestObject('Object Repository/Mobile/editText', ['text' : 'Nhập mật khẩu']), password, GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)
Mobile.verifyElementText(findTestObject('Object Repository/Mobile/editText', ['text' : AbstractMobile.convertPassword(password)]), AbstractMobile.convertPassword(password), FailureHandling.STOP_ON_FAILURE)

//See password input
Mobile.tap(findTestObject('Object Repository/Mobile/Login-Logout/iconSeeOrHidePassword', ['text' : AbstractMobile.convertPassword(password)]), GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)
Mobile.verifyElementText(findTestObject('Object Repository/Mobile/editText', ['text' : password]), password, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Mobile/Login-Logout/iconSeeOrHidePassword', ['text' : password]), GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)
Mobile.clearText(findTestObject('Object Repository/Mobile/editText', ['text' : AbstractMobile.convertPassword(password)]), GlobalVariable.TIME_OUT)


//Verify button login disabled when not input, input username or password
Mobile.verifyElementText(findTestObject('Object Repository/Mobile/Login-Logout/textLogin'), 'Đăng nhập', FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Mobile.getAttribute(findTestObject('Object Repository/Mobile/Login-Logout/btnLogin'), 'enabled', GlobalVariable.TIME_OUT), 'false', FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('Object Repository/Mobile/editText', ['text' :'Nhập tên đăng nhập']), username, GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)
Mobile.verifyEqual(Mobile.getAttribute(findTestObject('Object Repository/Mobile/Login-Logout/btnLogin'), 'enabled', GlobalVariable.TIME_OUT), 'false', FailureHandling.STOP_ON_FAILURE)
Mobile.clearText(findTestObject('Object Repository/Mobile/editText', ['text' : username]), GlobalVariable.TIME_OUT)

Mobile.setText(findTestObject('Object Repository/Mobile/editText', ['text' : 'Nhập mật khẩu']), password, GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)
Mobile.verifyEqual(Mobile.getAttribute(findTestObject('Object Repository/Mobile/Login-Logout/btnLogin'), 'enabled', GlobalVariable.TIME_OUT), 'false', FailureHandling.STOP_ON_FAILURE)
Mobile.clearText(findTestObject('Object Repository/Mobile/editText', ['text' : AbstractMobile.convertPassword(password)]), GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)

//Verify link forget password
Mobile.verifyElementText(findTestObject('Object Repository/Mobile/Login-Logout/linkForgetPassword'), 'Quên mật khẩu?', FailureHandling.STOP_ON_FAILURE)
Mobile.tap(findTestObject('Object Repository/Mobile/Login-Logout/linkForgetPassword'), GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)
Mobile.verifyElementText(findTestObject('Object Repository/Mobile/Login-Logout/titleForgetPassword'), 'Quên mật khẩu', FailureHandling.STOP_ON_FAILURE)
Mobile.pressBack()

//Verify text not have account 
Mobile.verifyElementText(findTestObject('Object Repository/Mobile/Login-Logout/textNotHaveAccount'), 'Bạn chưa có tài khoản?', FailureHandling.STOP_ON_FAILURE)

//Verify link register
Mobile.verifyElementText(findTestObject('Object Repository/Mobile/Login-Logout/linkRegister'), 'Đăng ký', FailureHandling.STOP_ON_FAILURE)
Mobile.tap(findTestObject('Object Repository/Mobile/Login-Logout/linkRegister'), GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)
Mobile.verifyElementText(findTestObject('Object Repository/Mobile/Login-Logout/titleRegister'), 'ĐĂNG KÝ', FailureHandling.STOP_ON_FAILURE)
Mobile.pressBack()
