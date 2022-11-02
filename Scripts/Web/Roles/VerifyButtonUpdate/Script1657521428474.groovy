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
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.support.ui.Wait
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

WebUI.waitForElementPresent(findTestObject('Object Repository/Web/Roles/titleTable'), GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)

AbstractWeb.verifyText('Object Repository/Web/Roles/titleTable', tableTitle)

int count = 0

List<WebElement> rowsFirst = WebUI.findWebElements(findTestObject('Object Repository/Web/Roles/UpdatePU/btnUpdate'), GlobalVariable.TIME_OUT)

WebDriver driver = DriverFactory.getWebDriver()

JavascriptExecutor executor = (JavascriptExecutor)driver;

for (WebElement element : rowsFirst) {
	
	ResultSet rs = Statemnet.getInforRoleLatest(count, 1)
	
	executor.executeScript("arguments[0].click()", element)
	
	WebUI.callTestCase(findTestCase("Test Cases/Web/Roles/VerifyPU"),[:],FailureHandling.STOP_ON_FAILURE)
	
	while(rs.next()) {

		valueName = WebUI.getAttribute(findTestObject("Object Repository/Web/Roles/CreateRole/inputNameRole"), "value", FailureHandling.STOP_ON_FAILURE)
		
		WebUI.verifyMatch(valueName, rs.getString('NAME'), false, FailureHandling.STOP_ON_FAILURE)
		
		valueKeyRole = WebUI.getAttribute(findTestObject("Object Repository/Web/Roles/CreateRole/intputKeyRole"), "value", FailureHandling.STOP_ON_FAILURE)
		
		WebUI.verifyMatch(valueKeyRole, rs.getString('CODE'), false)
		
		if(rs.getString('IS_ROOT') == "1"){
			
			WebUI.verifyElementPresent(findTestObject("Object Repository/Web/Roles/CreateRole/btnSwitchRoot"),
				GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)
			
			WebUI.verifyElementNotClickable(findTestObject('Object Repository/Web/Roles/CreateRole/inputSelect'), FailureHandling.STOP_ON_FAILURE)
			
		} else {
			
			WebUI.verifyElementPresent(findTestObject("Object Repository/Web/Roles/CreateRole/btnSwitch"),
				GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)
			
			WebUI.verifyElementClickable(findTestObject('Object Repository/Web/Roles/CreateRole/inputSelect'), FailureHandling.STOP_ON_FAILURE)
		}
		
		valueDecribeRole = WebUI.getAttribute(findTestObject("Object Repository/Web/Roles/CreateRole/textareaDecribe"), "value", FailureHandling.STOP_ON_FAILURE)
		
		if(valueDecribeRole != '' && rs.getString('DESCRIPTION') != null) {
			
			WebUI.verifyMatch(valueDecribeRole, rs.getString('DESCRIPTION'), false)
			
		}
		
	}
	
	WebUI.click(findTestObject("Object Repository/Web/Roles/CreateRole/btnCancel"), FailureHandling.STOP_ON_FAILURE)
	
	ConnectionDatabase.closeDatabaseConnection()
	
	count++
}
