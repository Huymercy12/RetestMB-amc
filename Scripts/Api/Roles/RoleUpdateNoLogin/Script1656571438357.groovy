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
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import java.sql.Timestamp
import com.api.*
import com.database.*
import com.testData.*
import java.sql.*
import org.json.*

println "--------------"+ Test_ID +"--------------"

Authenticate.setToken(GlobalVariable.ACCOUNT_CENTER)

Timestamp timestamp = new Timestamp(System.currentTimeMillis());

String codeRandomCreate = "M"+timestamp.getTime().toString()

println "--------------Create role to update--------------"

Map mapCreate = [
	('name'):nameCreate,
	('code'):codeRandomCreate,
	('description'):descriptionCreate,
	('isRoot'):isRootCreate,
	('resourceCode'): resourceCodeCreate,
	('scopes'): scopesCreate,
	('roleLevel'): roleLevelCreate
]

ResponseObject responseCreate = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Roles/CreateRole', mapCreate),
	FailureHandling.STOP_ON_FAILURE)

WS.verifyMatch(responseCreate.statusCode.toString(),"200", false, FailureHandling.STOP_ON_FAILURE)

println "--------------Update role --------------"

String codeRandomUpdate = "MUD"+timestamp.getTime().toString()

Map mapUpdate = [
	('idRole'): WS.getElementPropertyValue(responseCreate, 'data.id').toString(),
	('name'):  nameUpdate,
	('code'): (codeUpdate == "OK") ? codeRandomCreate : codeRandomUpdate,
	('description'): descriptionUpdate,
	('isRoot'):  isRootUpdate,
	('resourceCode'): resourceCodeUpdate,
	('scopes'): scopesUpdate,
	('roleLevel'): roleLevelUpdate
]

ResponseObject responseUpdate = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Roles/UpdateRoleNoLogin',mapUpdate), FailureHandling.STOP_ON_FAILURE)

WS.verifyMatch(responseUpdate.statusCode.toString(), "401", false, FailureHandling.STOP_ON_FAILURE)

WS.verifyMatch(WS.getElementPropertyValue(responseUpdate, 'error').toString(),
	"UNAUTHORISED", false, FailureHandling.CONTINUE_ON_FAILURE)