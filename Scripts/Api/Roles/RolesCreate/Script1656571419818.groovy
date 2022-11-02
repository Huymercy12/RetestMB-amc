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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
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
import java.sql.Timestamp
import com.kms.katalon.core.testobject.RequestObject
import java.util.Arrays
import java.util.List
import java.util.regex.Pattern as Pattern

KeywordUtil.logInfo( "-------------"+Test_ID+"-------------")

if(Test_ID != 'Test_1')
{
	Authenticate.setToken(GlobalVariable.ACCOUNT_CENTER)
}

Timestamp timestamp = new Timestamp(System.currentTimeMillis());

String codeCreate = "M"+timestamp.getTime().toString()

Map mapCreate = [
	('name'):name,
	('code'): (code != "<remove>" && code != "<duplicate>" && code != "") ? codeCreate : code,
	('description'):description,
	('isRoot'):isRoot,
	('resourceCode'): resourceCode,
	('scopes'): scopes,
	('roleLevel'): roleLevel
]

RequestObject requestObject = findTestObject('Object Repository/Api/Roles/CreateRole', mapCreate)

String body = requestObject.getHttpBody()

String itemRemove = '"[a-zA-Z]*": "<remove>"[,]*'

String itemDuplicate = '"[a-zA-Z]*": "<duplicate>"[,]*'

if (Pattern.compile(itemRemove).matcher(body).find()) {
	
	body = body.replaceAll(Pattern.compile(itemRemove), '')
	
} 

if(Pattern.compile(itemDuplicate).matcher(body).find()) {
	
	body = body.replaceAll(Pattern.compile(itemDuplicate), '"code": "SYSTEMMANAGEMENT",')
	
}

requestObject.setHttpBody(body)

def response = WS.sendRequestAndVerify(requestObject)

WS.verifyMatch(response.statusCode.toString(), statusCode, false, FailureHandling.STOP_ON_FAILURE)

if (statusCode == '200') {
	
	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.name').toString(), name, false, FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.code').toString(), codeCreate, false, FailureHandling.CONTINUE_ON_FAILURE)
	
	if(description != "<remove>" && description != "") {
		
		WS.verifyMatch(WS.getElementPropertyValue(response, 'data.description').toString(), description, false, FailureHandling.CONTINUE_ON_FAILURE)

	}
	
	if(isRoot == "" || isRoot == "<remove>") {
		
		WS.verifyMatch(WS.getElementPropertyValue(response, 'data.isRoot').toString(), "false", false, FailureHandling.CONTINUE_ON_FAILURE)
	
	}else {
		
		WS.verifyMatch(WS.getElementPropertyValue(response, 'data.isRoot').toString(), isRoot, false, FailureHandling.CONTINUE_ON_FAILURE)
	
	}
	
	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.status').toString(), "ACTIVE", false, FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.roleLevel').toString(), roleLevel, false, FailureHandling.CONTINUE_ON_FAILURE)
	
	ResultSet rs = Statemnet.getRolesInfor(codeCreate)
	
	while (rs.next()) {
		
		WS.verifyMatch(rs.getString('NAME'), name, false, FailureHandling.CONTINUE_ON_FAILURE)
		
		WS.verifyMatch(rs.getString('CODE'), codeCreate, false, FailureHandling.CONTINUE_ON_FAILURE)
		
		if(description != "<remove>" && description != "") {
			
			WS.verifyMatch(rs.getString('DESCRIPTION'), description, false, FailureHandling.CONTINUE_ON_FAILURE)
		}
		
		WS.verifyMatch(rs.getString('STATUS'), "ACTIVE", false, FailureHandling.CONTINUE_ON_FAILURE)
		
		WS.verifyMatch(rs.getString('ROLE_LEVEL'), roleLevel, false, FailureHandling.CONTINUE_ON_FAILURE)
		
		if(permissions != "<remove>" || (resourceCode != "" && scopes != "")){
			
			WS.verifyMatch(rs.getString('RESOURCE_CODE'), resourceCode, false, FailureHandling.CONTINUE_ON_FAILURE)
		
			WS.verifyMatch(rs.getString('SCOPE'), scopes, false, FailureHandling.CONTINUE_ON_FAILURE)
			
		}
		
	}
	
} else if (statusCode == '400') {
	
	if(code == "<duplicate>") {
		
		WS.verifyMatch(WS.getElementPropertyValue(response, 'message').toString(),
			"Mã code không được trùng lặp", false, FailureHandling.CONTINUE_ON_FAILURE)
		
		WS.verifyMatch(WS.getElementPropertyValue(response, 'error').toString(),
			"INVALID_INPUT", false, FailureHandling.CONTINUE_ON_FAILURE)
		
	} else if(roleLevel != "CENTER" && isRoot == "true"){
		
		WS.verifyMatch(WS.getElementPropertyValue(response, 'message').toString(),
			"Vai trò quản trị viên phải ở cấp trung tâm", false, FailureHandling.CONTINUE_ON_FAILURE)
		
		WS.verifyMatch(WS.getElementPropertyValue(response, 'error').toString(),
			"ADMIN_MUST_CENTER_LEVEL", false, FailureHandling.CONTINUE_ON_FAILURE)
		
	} else {
		
		WS.verifyMatch(WS.getElementPropertyValue(response, 'message').toString(),
			"Dữ liệu nhập không hợp lệ", false, FailureHandling.CONTINUE_ON_FAILURE)
		
		WS.verifyMatch(WS.getElementPropertyValue(response, 'error').toString(),
			"INVALID_INPUT", false, FailureHandling.CONTINUE_ON_FAILURE)
	}
	
}