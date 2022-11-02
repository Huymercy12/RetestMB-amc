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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver		
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys
import com.kms.katalon.core.util.KeywordUtil

List<WebElement> rowsFirst = WebUI.findWebElements(findTestObject('Object Repository/Web/Complaints/FeedbackTemplate/rowTable'), GlobalVariable.TIME_OUT)

WebDriver driver = DriverFactory.getWebDriver()

JavascriptExecutor executor = (JavascriptExecutor)driver;

for (WebElement row : rowsFirst) {
	
	List<WebElement> colums = row.findElements(By.tagName('td'))
	
	boolean codeRoleBoolean
	
	codeRoleBoolean 	= colums[3].getText().equals(AbstractWeb.getConvertSubStringTextToLength(keyRole,50))
	
	if(codeRoleBoolean){
		
		List<WebElement> listButton = colums[10].findElements(By.tagName('mb-button-action'))
		
		for(WebElement element:listButton) {
			
			if(element.getAttribute('classname') == "first") {
					
					executor.executeScript("arguments[0].click()", element)
					
					KeywordUtil.logInfo( "-------------Verify PU Update-------------")
					
					valueName = WebUI.getAttribute(findTestObject("Object Repository/Web/Roles/CreateRole/inputNameRole"), "value", FailureHandling.STOP_ON_FAILURE)
					
					WebUI.verifyMatch(valueName, nameRole, false)
					
					valueKeyRole = WebUI.getAttribute(findTestObject("Object Repository/Web/Roles/CreateRole/intputKeyRole"), "value", FailureHandling.STOP_ON_FAILURE)
					
					WebUI.verifyMatch(valueKeyRole, keyRole, false)
					
					if(role == "Yes"){
						
						WebUI.verifyElementPresent(findTestObject("Object Repository/Web/Roles/CreateRole/btnSwitchRoot"),
							GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)
						
						WebUI.verifyElementNotClickable(findTestObject('Object Repository/Web/Roles/CreateRole/inputSelect'), FailureHandling.STOP_ON_FAILURE)
						
					} else {
						WebUI.verifyElementPresent(findTestObject("Object Repository/Web/Roles/CreateRole/btnSwitch"),
							GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)
						
						WebUI.verifyElementClickable(findTestObject('Object Repository/Web/Roles/CreateRole/inputSelect'), FailureHandling.STOP_ON_FAILURE)
					}
					
					valueDecribeRole = WebUI.getAttribute(findTestObject("Object Repository/Web/Roles/CreateRole/textareaDecribe"), "value", FailureHandling.STOP_ON_FAILURE)
					
					WebUI.verifyMatch(valueDecribeRole, textareaDecribe, false)
					
					KeywordUtil.logInfo( "-------------Update record-------------")
					
					WebUI.clearText(findTestObject("Object Repository/Web/Roles/CreateRole/inputNameRole"), FailureHandling.STOP_ON_FAILURE)
					
					WebUI.sendKeys(findTestObject("Object Repository/Web/Roles/CreateRole/inputNameRole"), nameRoleUpdate, FailureHandling.STOP_ON_FAILURE)
					
					if(roleUpdate == "No" && role == "Yes"){
						
						WebUI.click(findTestObject("Object Repository/Web/Roles/CreateRole/btnSwitchRoot"), FailureHandling.STOP_ON_FAILURE)
						
						WebUI.click(findTestObject("Object Repository/Web/Roles/CreateRole/inputSelect"), FailureHandling.STOP_ON_FAILURE)
	
						WebUI.click(findTestObject("Object Repository/Web/Roles/CreateRole/selectTextInput",[('text'):inputSelectUpdate]), FailureHandling.STOP_ON_FAILURE)
	
						if(inputSelectUpdate == ""){
							
							WebUI.click(findTestObject('Object Repository/Web/Roles/CreateRole/labelNameRole'), FailureHandling.STOP_ON_FAILURE)
							WebUI.verifyElementPresent(findTestObject("Object Repository/Web/Roles/CreateRole/validateError",[('error'):'Cấp của vai trò  không được để trống']),
								GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)
						}
						
					}
					
					if(roleUpdate == "Yes" && role == "No") {
						
						WebUI.click(findTestObject("Object Repository/Web/Roles/CreateRole/btnSwitch"), FailureHandling.STOP_ON_FAILURE)
					}
					
					WebUI.clearText(findTestObject("Object Repository/Web/Roles/CreateRole/textareaDecribe"), FailureHandling.STOP_ON_FAILURE)
					
					WebUI.sendKeys(findTestObject("Object Repository/Web/Roles/CreateRole/textareaDecribe"), textareaDecribeUpdate, FailureHandling.STOP_ON_FAILURE)
					
					if(btncloseUpdate == "No") {
						
						WebUI.click(findTestObject("Object Repository/Web/Roles/CreateRole/btnCancel"), FailureHandling.STOP_ON_FAILURE)
						
					} else {
						
						WebUI.click(findTestObject("Object Repository/Web/Roles/CreateRole/btnSave"), FailureHandling.STOP_ON_FAILURE)
						
						AbstractWeb.verifyMessage('Object Repository/Web/message', messageSuccess)
					}
					
					break
			}
		}
		
	}
}