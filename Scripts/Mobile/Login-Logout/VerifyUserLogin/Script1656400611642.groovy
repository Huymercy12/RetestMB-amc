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
import internal.GlobalVariable
import org.openqa.selenium.Keys

KeywordUtil.logInfo(description)

if (result.equals('error internet')) {
	
	Mobile.toggleWifi('false')
	Mobile.verifyElementText(findTestObject('Object Repository/Mobile/Login-Logout/msgErrorInternet'), 'Lỗi mạng', FailureHandling.STOP_ON_FAILURE)
	
	AbstractMobile.loginWithAccount(['username' : username, 'password' : password])
	
	Mobile.verifyElementText(findTestObject('Object Repository/Mobile/Login-Logout/titleMessage'), 'Lỗi đăng nhập', FailureHandling.STOP_ON_FAILURE)
	Mobile.verifyElementText(findTestObject('Object Repository/Mobile/Login-Logout/contentMessage'), 'Sai thông tin đăng nhập. Vui lòng kiểm tra lại.', FailureHandling.STOP_ON_FAILURE)

	Mobile.toggleWifi('true')
} else {
	
	AbstractMobile.loginWithAccount(['username' : username, 'password' : password])
	
	if (result.equals('success')){
		
		Mobile.tap(findTestObject('Object Repository/Mobile/textView', ['text' : 'Cá nhân']), GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)
		//Verify username
		String nameUser = Statemnet.getFullNameUser(username)
		Mobile.verifyElementText(findTestObject('Object Repository/Mobile/textView', ['text' : nameUser]), nameUser, FailureHandling.STOP_ON_FAILURE)
	} else {
		
		Mobile.verifyElementText(findTestObject('Object Repository/Mobile/Login-Logout/titleMessage'), 'Lỗi đăng nhập', FailureHandling.STOP_ON_FAILURE)
		Mobile.verifyElementText(findTestObject('Object Repository/Mobile/Login-Logout/contentMessage'), 'Sai thông tin đăng nhập. Vui lòng kiểm tra lại.', FailureHandling.STOP_ON_FAILURE)
		
		Mobile.clearText(findTestObject('Object Repository/Mobile/editText', ['text' : username]), GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)
		Mobile.clearText(findTestObject('Object Repository/Mobile/editText', ['text' : AbstractMobile.convertPassword(password)]), GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)
	}
}