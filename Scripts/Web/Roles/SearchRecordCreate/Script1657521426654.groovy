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

List<WebElement> rowsFirst = WebUI.findWebElements(findTestObject('Object Repository/Web/Complaints/FeedbackTemplate/rowTable'), GlobalVariable.TIME_OUT)

for (WebElement row : rowsFirst) {
	
	List<WebElement> colums = row.findElements(By.tagName('td'))
	
	boolean isRootBoolean, roleLevelBoolean, codeRoleBoolean, describleBoolean, nameRoleBoolean
	
	codeRoleBoolean 	= colums[3].getText().equals(AbstractWeb.getConvertSubStringTextToLength(keyRole,50))
	
	if(codeRoleBoolean){
		
		nameRoleBoolean 	= colums[1].getText().equals(AbstractWeb.getConvertSubStringTextToLength(nameRole,100))
		
		if(colums[1].getText().length() > 100)	assertTrue(false)
			
		else assertTrue(true)
		
		if(role == "Yes") {
			
			isRootBoolean 		= colums[2].getText().equals("true")
			
			roleLevelBoolean 	= colums[4].getText().equals("Trung tâm")
			
		} else {
			
			isRootBoolean 		= colums[2].getText().equals("false")
			
			roleLevelBoolean 	= colums[4].getText().equals(inputSelect)
		}
		
		if(colums[3].getText().length() > 50)	assertTrue(false)
			
		else assertTrue(true)
		
		describleBoolean 	= colums[5].getText().equals(AbstractWeb.getConvertSubStringTextToLength(textareaDecribe,20))
		
		if(nameRoleBoolean && isRootBoolean && roleLevelBoolean && describleBoolean)
		{
			println "Search record success"
			
			break
		}
		
	}
}