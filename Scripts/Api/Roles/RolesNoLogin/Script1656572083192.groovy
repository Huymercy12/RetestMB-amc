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
import com.api.*
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject

Authenticate.setToken(GlobalVariable.ACCOUNT_CENTER)

Map maps=[
	('name'):name,
	('code'):code,
	('description'):description,
	('isRoot'):isRoot,
	("resourceCode"): resourceCode,
	("scopes"): scopes,
	("roleLevel"): roleLevel
]

ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Roles/CreateRoleNoLogin',maps), FailureHandling.STOP_ON_FAILURE)

WS.verifyMatch(response.statusCode.toString(), "401", false, FailureHandling.STOP_ON_FAILURE)

WS.verifyMatch(WS.getElementPropertyValue(response, 'message').toString(), 
	"You were not authorized to request POST /api/roles", false, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(WS.getElementPropertyValue(response, 'error').toString(), 
	"UNAUTHORISED", false, FailureHandling.CONTINUE_ON_FAILURE)
