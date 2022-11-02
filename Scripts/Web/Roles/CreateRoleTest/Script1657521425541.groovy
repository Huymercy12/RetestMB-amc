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
import com.web.AbstractWeb as AbstractWeb
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import static org.junit.Assert.assertTrue
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import com.database.*
import java.sql.*
import java.sql.Timestamp
import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.logInfo( "-------------"+Test_ID+"-------------")

String recordTableRoleBefore = Statemnet.getTotalRecorded("EVO_IAM.ROLE")

int sumRecordTableRoleBefore = Integer.parseInt(recordTableRoleBefore)

int sumRecordDisplay = Integer.parseInt(WebUI.getText(findTestObject("Object Repository/Web/Roles/RoleManagement/numberRecorDisplay")))

KeywordUtil.logInfo( "-------------Verify button create-------------")
WebUI.callTestCase(findTestCase("Test Cases/Web/Roles/VerifyButtonCreate"), null, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo( "-------------Create role-------------")
WebUI.sendKeys(findTestObject("Object Repository/Web/Roles/CreateRole/inputNameRole"), nameRole, FailureHandling.STOP_ON_FAILURE)

if(nameRole == ""){
	
	WebUI.click(findTestObject('Object Repository/Web/Roles/CreateRole/labelNameRole'), FailureHandling.STOP_ON_FAILURE)
	WebUI.verifyElementPresent(findTestObject("Object Repository/Web/Roles/CreateRole/validateError",[('error'):'Tên vai trò  không được để trống']),
		GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)
}

Timestamp timestamp = new Timestamp(System.currentTimeMillis());

String codeCreate = "M"+timestamp.getTime().toString()

if(keyRole == ""){
	
	WebUI.sendKeys(findTestObject("Object Repository/Web/Roles/CreateRole/intputKeyRole"), keyRole, FailureHandling.STOP_ON_FAILURE)
	WebUI.click(findTestObject('Object Repository/Web/Roles/CreateRole/labelNameRole'), FailureHandling.STOP_ON_FAILURE)
	WebUI.verifyElementPresent(findTestObject("Object Repository/Web/Roles/CreateRole/validateError",[('error'):'Mã vai trò  không được để trống']),
		GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)
	
} else if(keyRole == "duplicate") {
	
	WebUI.sendKeys(findTestObject("Object Repository/Web/Roles/CreateRole/intputKeyRole"), codeCreate, FailureHandling.STOP_ON_FAILURE)
	
} else {
	
	WebUI.sendKeys(findTestObject("Object Repository/Web/Roles/CreateRole/intputKeyRole"), codeCreate, FailureHandling.STOP_ON_FAILURE)
}

WebUI.click(findTestObject("Object Repository/Web/Roles/CreateRole/inputSelect"), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject("Object Repository/Web/Roles/CreateRole/selectTextInput",[('text'):inputSelect]), FailureHandling.STOP_ON_FAILURE)

if(inputSelect == ""){
	
	WebUI.click(findTestObject('Object Repository/Web/Roles/CreateRole/labelNameRole'), FailureHandling.STOP_ON_FAILURE)
	WebUI.verifyElementPresent(findTestObject("Object Repository/Web/Roles/CreateRole/validateError",[('error'):'Cấp của vai trò  không được để trống']),
		GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)
}

if(role == "Yes"){
	
	WebUI.click(findTestObject("Object Repository/Web/Roles/CreateRole/btnSwitch"), FailureHandling.STOP_ON_FAILURE)
}

WebUI.sendKeys(findTestObject("Object Repository/Web/Roles/CreateRole/textareaDecribe"), textareaDecribe, FailureHandling.STOP_ON_FAILURE)

if(btnclose == "No"){
	
	WebUI.click(findTestObject("Object Repository/Web/Roles/CreateRole/btnSave"), FailureHandling.STOP_ON_FAILURE)
	
	String sumRecordTableRoleAfter = Statemnet.getTotalRecorded("EVO_IAM.ROLE")

	WebUI.verifyMatch(String.valueOf(sumRecordTableRoleBefore+1), sumRecordTableRoleAfter, false, FailureHandling.STOP_ON_FAILURE)

	AbstractWeb.verifyMessage('Object Repository/Web/message', message)
	
}else {

	KeywordUtil.logInfo("------------Create role------------")
	
	WebUI.click(findTestObject("Object Repository/Web/Roles/CreateRole/btnCancel"), FailureHandling.STOP_ON_FAILURE)
	
}

KeywordUtil.logInfo("------------Search role created------------")

WebDriver driver = DriverFactory.getWebDriver()

if(keyRole == "duplicate") {
	
	AbstractWeb.verifyMessage('Object Repository/Web/message', messageError)
	
}

if(nameRole != "" && keyRole != "" && keyRole != "duplicate" && inputSelect != ""  && btnclose == "No"){
	
	AbstractWeb.waitAndSearch('Object Repository/Web/Roles/inputSearch', codeCreate)

	WebUI.callTestCase(findTestCase("Test Cases/Web/Roles/SearchRecordCreate"),
		[('nameRole'):nameRole,
		('keyRole'): codeCreate,
		('inputSelect'):inputSelect,
		('role'):role,
		('textareaDecribe'):textareaDecribe],
		FailureHandling.STOP_ON_FAILURE)
	
	while(true){
		
		try {
			
		  boolean btnNextDisable= driver.findElement(By.xpath("//img[@class='pagination-next disable']")).isDisplayed()
		  break
		  
		}
		catch(Exception e) {
			
			WebUI.scrollToElement(findTestObject("Object Repository/Web/Roles/RoleManagement/btnNext"), GlobalVariable.TIME_OUT)
			
			WebUI.click(findTestObject("Object Repository/Web/Roles/RoleManagement/btnNext"), FailureHandling.STOP_ON_FAILURE)
			
			WebUI.callTestCase(findTestCase("Test Cases/Web/Roles/SearchRecordCreate"),
				[('nameRole'):nameRole,
				('keyRole'):codeCreate,
				('inputSelect'):inputSelect,
				('role'):role,
				('textareaDecribe'):textareaDecribe],
				FailureHandling.STOP_ON_FAILURE)
			
	 	}
	}
}

