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
import com.kms.katalon.core.util.KeywordUtil
import java.util.regex.Pattern as Pattern
import com.kms.katalon.core.testobject.RequestObject

KeywordUtil.logInfo("-------------"+Test_ID+"-------------")

Authenticate.setToken(GlobalVariable.ACCOUNT_CENTER)

Timestamp timestamp = new Timestamp(System.currentTimeMillis())

String codeRandomCreate = "M"+timestamp.getTime().toString()

KeywordUtil.logInfo("--------------Create role to update--------------")

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

WS.verifyMatch(responseCreate.statusCode.toString(),statusCreate, false, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo("--------------Update role--------------")

String codeRandomUpdate = "UPD"+codeRandomCreate

String idRoleIncorrect = "Incorrect" + WS.getElementPropertyValue(responseCreate, 'data.id').toString()

Map mapUpdate = [
	('idRole'): (idRole == "OK") ? WS.getElementPropertyValue(responseCreate, 'data.id').toString() : idRoleIncorrect,
	('name'):nameUpdate,
	('code'): (codeUpdate != "<remove>" && codeUpdate != "<duplicate>" && codeUpdate != "") ? codeRandomCreate : codeUpdate,
	('description'):descriptionUpdate,
	('isRoot'):isRootUpdate,
	('resourceCode'): resourceCodeUpdate,
	('scopes'): scopesUpdate,
	('roleLevel'): roleLevelUpdate
]

RequestObject requestObject

if(Test_ID == 'Test_1'){
	
	requestObject = findTestObject('Object Repository/Api/Roles/UpdateRoleNoLogin', mapUpdate)
	
}else {
	
	requestObject = findTestObject('Object Repository/Api/Roles/UpdateRole', mapUpdate)
	
}

String body = requestObject.getHttpBody()

String itemRemove = '"[a-zA-Z]*": "<remove>"[,]*'

String itemDuplicate = '"[a-zA-Z]*": "<duplicate>"[,]*'

if (Pattern.compile(itemRemove).matcher(body).find()) {
	
	body = body.replaceAll(Pattern.compile(itemRemove), '')
	
}

if(Pattern.compile(itemDuplicate).matcher(body).find()) {
	
	body = body.replaceAll(Pattern.compile(itemDuplicate), '"code": "SYSTEMMANAGEMENT",')
	
}

println body

requestObject.setHttpBody(body)

def responseUpdate = WS.sendRequestAndVerify(requestObject)
	
WS.verifyMatch(responseUpdate.statusCode.toString(), statusUpdate, false, FailureHandling.STOP_ON_FAILURE)

if (statusUpdate == '200') {
	
	WS.verifyMatch(WS.getElementPropertyValue(responseUpdate, 'data.name').toString(), nameUpdate, false, FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.verifyMatch(WS.getElementPropertyValue(responseUpdate, 'data.code').toString(), codeRandomCreate, false, FailureHandling.CONTINUE_ON_FAILURE)
	
	if(descriptionUpdate != '<remove>') {
		
		WS.verifyMatch(WS.getElementPropertyValue(responseUpdate, 'data.description').toString(), descriptionUpdate, false, FailureHandling.CONTINUE_ON_FAILURE)

	}
	
	WS.verifyMatch(WS.getElementPropertyValue(responseUpdate, 'data.status').toString(), "ACTIVE", false, FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.verifyMatch(WS.getElementPropertyValue(responseUpdate, 'data.roleLevel').toString(), roleLevelUpdate, false, FailureHandling.CONTINUE_ON_FAILURE)
	
	if(isRootUpdate == "" || isRootUpdate == "<remove>"){
		
		WS.verifyMatch(WS.getElementPropertyValue(responseUpdate, 'data.isRoot').toString(), "false", false, FailureHandling.CONTINUE_ON_FAILURE)
	
	} else {
		
		WS.verifyMatch(WS.getElementPropertyValue(responseUpdate, 'data.isRoot').toString(), isRootUpdate, false, FailureHandling.CONTINUE_ON_FAILURE)
	
	}
	
	ResultSet rs = Statemnet.getRolesInfor(codeRandomCreate)
	
	while (rs.next()) {
		
		WS.verifyMatch(rs.getString('NAME'), nameUpdate, false, FailureHandling.CONTINUE_ON_FAILURE)
		
		WS.verifyMatch(rs.getString('CODE'), codeRandomCreate, false, FailureHandling.CONTINUE_ON_FAILURE)
		
		if(descriptionUpdate != '' && descriptionUpdate != '<remove>'){
			
			WS.verifyMatch(rs.getString('DESCRIPTION'), descriptionUpdate, false, FailureHandling.CONTINUE_ON_FAILURE)
			
		}
		
		WS.verifyMatch(rs.getString('STATUS'), "ACTIVE", false, FailureHandling.CONTINUE_ON_FAILURE)
		
		WS.verifyMatch(rs.getString('ROLE_LEVEL'), roleLevelUpdate, false, FailureHandling.CONTINUE_ON_FAILURE)
		
		if(permissionsUpdate != DATAEMPTY){
			
			WS.verifyMatch(rs.getString('RESOURCE_CODE'), resourceCodeUpdate , false, FailureHandling.CONTINUE_ON_FAILURE)
		
			WS.verifyMatch(rs.getString('SCOPE'), scopesUpdate, false, FailureHandling.CONTINUE_ON_FAILURE)
			
		}
	}
	
} else if (statusUpdate == '400') {
	
	if(isRootUpdate == "true" && roleLevelUpdate != "CENTER") {
		
		WS.verifyMatch(WS.getElementPropertyValue(responseUpdate, 'message').toString(),
			"Vai trò quản trị viên phải ở cấp trung tâm", false, FailureHandling.CONTINUE_ON_FAILURE)
		
		WS.verifyMatch(WS.getElementPropertyValue(responseUpdate, 'error').toString(),
			"ADMIN_MUST_CENTER_LEVEL", false, FailureHandling.CONTINUE_ON_FAILURE)
		
	}else {
		
		WS.verifyMatch(WS.getElementPropertyValue(responseUpdate, 'message').toString(),
			"Dữ liệu nhập không hợp lệ", false, FailureHandling.CONTINUE_ON_FAILURE)
		
		WS.verifyMatch(WS.getElementPropertyValue(responseUpdate, 'error').toString(),
			"INVALID_INPUT", false, FailureHandling.CONTINUE_ON_FAILURE)
		
	}
	
} else if (statusUpdate == '404') {
	
	WS.verifyMatch(WS.getElementPropertyValue(responseUpdate, 'message').toString(),
		"Role not found: {0}", false, FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.verifyMatch(WS.getElementPropertyValue(responseUpdate, 'error').toString(),
		"ROLE_NOT_FOUND", false, FailureHandling.CONTINUE_ON_FAILURE)
	
}