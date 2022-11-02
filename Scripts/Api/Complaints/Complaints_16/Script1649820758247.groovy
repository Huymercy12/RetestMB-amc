import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import java.sql.*
import com.api.Authenticate as Authenticate
import com.api.*
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject

Authenticate.setToken([('username') : username, ('password') : password])
maps = [('pageIndex') : pageIndex,
			('pageSize') : pageSize,
			('keyword') : keyword]

ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Complaints/Search complaint', maps), FailureHandling.STOP_ON_FAILURE)

WS.verifyMatch(response.statusCode.toString(), statusCode, false, FailureHandling.STOP_ON_FAILURE)
WS.verifyMatch(WS.getElementPropertyValue(response, 'page.pageIndex').toString(), pageIndex, false, FailureHandling.CONTINUE_ON_FAILURE)
WS.verifyMatch(WS.getElementPropertyValue(response, 'page.pageSize').toString(), pageSize, false, FailureHandling.CONTINUE_ON_FAILURE)
try {
	WS.verifyEqual(WS.getElementPropertyValue(response, 'data[0].fullName').toString().contains(keyword), true)
}catch(Exception ex){
	println('Kết quả rỗng')
}