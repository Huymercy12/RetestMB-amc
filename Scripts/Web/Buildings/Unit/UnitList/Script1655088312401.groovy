import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.database.Statemnet
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.web.AbstractWeb
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions

WebDriver driver = DriverFactory.getWebDriver()

AbstractWeb.waitAndVerifyElement('Object Repository/Web/Buildings/Unit/buttonCreate')

WebUI.verifyEqual(WebUI.getAttribute(findTestObject('Object Repository/Web/Buildings/Unit/inputSearch'), 'placeholder'), search, FailureHandling.STOP_ON_FAILURE)

AbstractWeb.scrollToEndPage()

AbstractWeb.getInforSizeOfPagination()

WebUI.verifyEqual(GlobalVariable.INFOR_SIZE_OF_PAGINATION.getAt('totalRecord'), Statemnet.getTotalUnit(), FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Test Cases/Web/Buildings/Unit/ValidateTable'), null, FailureHandling.STOP_ON_FAILURE)