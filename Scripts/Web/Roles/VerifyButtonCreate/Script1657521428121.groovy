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

AbstractWeb.verifyText('Object Repository/Web/Roles/titleTable', tableTitle)

AbstractWeb.waitAndVerifyElement('Object Repository/Web/Roles/RoleManagement/buttonCreate')

WebUI.scrollToElement(findTestObject('Object Repository/Web/Roles/RoleManagement/buttonCreate'), GlobalVariable.TIME_OUT)

WebUI.click(findTestObject('Object Repository/Web/Roles/RoleManagement/buttonCreate'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject("Object Repository/Web/Roles/CreateRole/labelNameRole"), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject("Object Repository/Web/Roles/CreateRole/inputNameRole"), FailureHandling.STOP_ON_FAILURE)

attributeMaxLenghtName = WebUI.getAttribute(findTestObject("Object Repository/Web/Roles/CreateRole/inputNameRole"), 'maxlength')

WebUI.verifyMatch(attributeMaxLenghtName, "100", false)

WebUI.verifyElementVisible(findTestObject("Object Repository/Web/Roles/CreateRole/labelCodeRole"), FailureHandling.STOP_ON_FAILURE)

attributeMaxLenghtKey = WebUI.getAttribute(findTestObject("Object Repository/Web/Roles/CreateRole/intputKeyRole"), 'maxlength')

WebUI.verifyMatch(attributeMaxLenghtKey, "50", false)

WebUI.verifyElementVisible(findTestObject("Object Repository/Web/Roles/CreateRole/intputKeyRole"), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject("Object Repository/Web/Roles/CreateRole/labelIsRoot"), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject("Object Repository/Web/Roles/CreateRole/btnSwitch"), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject("Object Repository/Web/Roles/CreateRole/labelLevelRole"), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject("Object Repository/Web/Roles/CreateRole/inputSelect"), GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject("Object Repository/Web/Roles/CreateRole/labelTextareaDescribe"), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject("Object Repository/Web/Roles/CreateRole/textareaDecribe"), FailureHandling.STOP_ON_FAILURE)

attributeMaxLenghtDescribe = WebUI.getAttribute(findTestObject("Object Repository/Web/Roles/CreateRole/textareaDecribe"), 'maxlength')

WebUI.verifyMatch(attributeMaxLenghtDescribe, "1000", false)

WebUI.verifyElementVisible(findTestObject("Object Repository/Web/Roles/CreateRole/btnCancel"), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject("Object Repository/Web/Roles/CreateRole/btnClose"), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject("Object Repository/Web/Roles/CreateRole/btnSave"), FailureHandling.STOP_ON_FAILURE)
