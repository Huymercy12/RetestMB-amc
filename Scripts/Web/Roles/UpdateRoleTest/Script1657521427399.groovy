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
import java.sql.Timestamp

println "------------Create record role want to update------------"

WebUI.scrollToElement(findTestObject('Object Repository/Web/Roles/RoleManagement/buttonCreate'), GlobalVariable.TIME_OUT)

WebUI.click(findTestObject('Object Repository/Web/Roles/RoleManagement/buttonCreate'), FailureHandling.STOP_ON_FAILURE)

Timestamp timestamp = new Timestamp(System.currentTimeMillis());

String codeCreate = "M"+timestamp.getTime().toString()

WebUI.callTestCase(findTestCase("Test Cases/Web/Roles/CreateRecordRole"), 
		[('nameRole'):nameRoleCreate,
		('keyRole'):codeCreate,
		('inputSelect'):inputSelectCreate,
		('role'):roleCreate,
		('textareaDecribe'):textareaDecribeCreate,
		('btnclose'):btncloseCreate], FailureHandling.STOP_ON_FAILURE)

println "------------Search record role created------------"

AbstractWeb.waitAndSearch('Object Repository/Web/Roles/inputSearch', nameRoleCreate)

WebDriver driver = DriverFactory.getWebDriver()

WebUI.callTestCase(findTestCase("Test Cases/Web/Roles/SearchRecordToUpdate"), 
				[('nameRole'):nameRoleCreate,
				('keyRole'):codeCreate,
				('inputSelect'):inputSelectCreate,
				('role'):roleCreate,
				('textareaDecribe'):textareaDecribeCreate,
				('nameRoleUpdate'):nameRoleUpdate,
				('inputSelectUpdate'):inputSelectUpdate,
				('roleUpdate'):roleUpdate,
				('textareaDecribeUpdate'):textareaDecribeUpdate,
				('btncloseUpdate'):btncloseUpdate], FailureHandling.STOP_ON_FAILURE)

while(true)
{
	try {
		
	  boolean btnNextDisable= driver.findElement(By.xpath("//img[@class='pagination-next disable']")).isDisplayed()
	  break
	  
	}
	catch(Exception e) {
		
		WebUI.scrollToElement(findTestObject("Object Repository/Web/Roles/RoleManagement/btnNext"), GlobalVariable.TIME_OUT)
		
		WebUI.click(findTestObject("Object Repository/Web/Roles/RoleManagement/btnNext"), FailureHandling.STOP_ON_FAILURE)
		
		WebUI.callTestCase(findTestCase("Test Cases/Web/Roles/SearchRecordToUpdate"), 
						[('nameRole'):nameRoleCreate,
						('keyRole'):codeCreate,
						('inputSelect'):inputSelectCreate,
						('role'):roleCreate,
						('textareaDecribe'):textareaDecribeCreate,
						('nameRoleUpdate'):nameRoleUpdate,
						('inputSelectUpdate'):inputSelectUpdate,
						('roleUpdate'):roleUpdate,
						('textareaDecribeUpdate'):textareaDecribeUpdate,
						('btncloseUpdate'):btncloseUpdate], FailureHandling.STOP_ON_FAILURE)
	 }
}

