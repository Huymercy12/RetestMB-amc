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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.By as By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.web.AbstractWeb as AbstractWeb
import com.database.Statemnet as Statemnet

List<WebElement> element = WebUI.findWebElements(findTestObject('Object Repository/Web/DashboardPage/buttonLogout'), 1)

if (element.size() > 0) {
    WebUI.click(findTestObject('Object Repository/Web/DashboardPage/buttonLogout'), FailureHandling.STOP_ON_FAILURE)
}

WebUI.navigateToUrl(GlobalVariable.URL_LOGIN)

AbstractWeb.waitVerifyElementAndSendKey('Object Repository/Web/LoginPage/inputUsername', username)

AbstractWeb.waitVerifyElementAndSendKey('Object Repository/Web/LoginPage/inputPassword', password)

AbstractWeb.waitVerifyElementAndClick('Object Repository/Web/LoginPage/buttonLogin')

AbstractWeb.verifyMessage('Object Repository/Web/message', message)

AbstractWeb.verifyText('Object Repository/Web/DashboardPage/textFullName', Statemnet.getFullNameUser(username))