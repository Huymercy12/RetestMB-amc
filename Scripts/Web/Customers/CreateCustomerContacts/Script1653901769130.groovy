import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.testData.FakerData as FakerData
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.web.AbstractWeb as AbstractWeb
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import static org.junit.Assert.assertTrue
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.interactions.Actions as Actions
import org.testng.Assert as Assert

import com.database.*
import java.sql.*

KeywordUtil.logInfo(inforContact)

WebDriver driver = DriverFactory.getWebDriver()

Actions actions = new Actions(driver)

WebUI.callTestCase(findTestCase('Test Cases/Web/Customers/CreateNewCustomerInformation'), [('businessRegistrationCode') : businessRegistrationCode
        , ('company') : company, ('email') : email, ('address') : address, ('representative') : representative, ('phoneNumber') : phoneNumber
        , ('foundingDate') : foundingDate, ('type') : type, ('status') : status, ('message') : message, ('infor') : infor
        , ('action') : action], FailureHandling.STOP_ON_FAILURE)

AbstractWeb.clickButton('Object Repository/Web/Customers/InformationCustomerPage/tabContact')

AbstractWeb.clickButton('Object Repository/Web/Customers/Contact/buttonCreate')

//input fullName
if (fullNameContact.toString().equalsIgnoreCase('trim space')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/Contact/textBoxFullName', ('  ' + FakerData.randomFullName()) + 
        '  ')
} else if (fullNameContact.toString().equalsIgnoreCase('random100')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/Contact/textBoxFullName', FakerData.randomStringQR(100))
} else if (fullNameContact.toString().equalsIgnoreCase('random101')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/Contact/textBoxFullName', FakerData.randomStringQR(101))

    Assert.assertEquals(AbstractWeb.getAttribute('Object Repository/Web/Customers/Contact/textBoxFullName', 'value').length(), 
        100)
} else if (fullNameContact.toString().equalsIgnoreCase('random')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/Contact/textBoxFullName', FakerData.randomFullName())
} else {
    AbstractWeb.sendText('Object Repository/Web/Customers/Contact/textBoxFullName', fullNameContact)
}

//input phone number
if (phoneNumberContact.toString().equalsIgnoreCase('trim space')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/Contact/textBoxPhoneNumber', ('  ' + FakerData.randomPhoneNumber()) + 
        '  ')
} else if (phoneNumberContact.toString().equalsIgnoreCase('random')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/Contact/textBoxPhoneNumber', FakerData.randomPhoneNumber())
} else {
    AbstractWeb.sendText('Object Repository/Web/Customers/Contact/textBoxPhoneNumber', phoneNumberContact)
}

//input email
if (emailContact.toString().equalsIgnoreCase('trim space')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/Contact/textBoxEmail', ('  ' + FakerData.randomEmail()) + '  ')
} else if (emailContact.toString().equalsIgnoreCase('random50')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/Contact/textBoxEmail', FakerData.randomStringQR(31) + FakerData.randomEmail())
} else if (emailContact.toString().equalsIgnoreCase('random51')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/Contact/textBoxEmail', FakerData.randomStringQR(32) + FakerData.randomEmail())

    Assert.assertEquals(AbstractWeb.getAttribute('Object Repository/Web/Customers/Contact/textBoxEmail', 'value').length(), 
        50)
} else if (emailContact.toString().equalsIgnoreCase('random')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/Contact/textBoxEmail', FakerData.randomEmail())
} else {
    AbstractWeb.sendText('Object Repository/Web/Customers/Contact/textBoxEmail', emailContact)
}

// cancel or create
if (actionContact.toString().equalsIgnoreCase('Cancel')) {
    AbstractWeb.clickButton('Object Repository/Web/Customers/Contact/buttonCancel')

    WebUI.verifyElementVisible(findTestObject('Object Repository/Web/imageEmpty'), FailureHandling.STOP_ON_FAILURE)

    AbstractWeb.verifyText('Object Repository/Web/Buildings/messageNoRecord', messageNoRecord)
} else if (actionContact.toString().equalsIgnoreCase('Save')) {
    String fullNameContactInput = WebUI.getAttribute(findTestObject('Object Repository/Web/Customers/Contact/textBoxFullName'), 
        'value', FailureHandling.STOP_ON_FAILURE).trim()

    String phoneNumberContactInput = WebUI.getAttribute(findTestObject('Object Repository/Web/Customers/Contact/textBoxPhoneNumber'), 
        'value', FailureHandling.STOP_ON_FAILURE).trim()

    String emailContactInput = WebUI.getAttribute(findTestObject('Object Repository/Web/Customers/Contact/textBoxEmail'), 
        'value', FailureHandling.STOP_ON_FAILURE).trim()

    AbstractWeb.clickButton('Object Repository/Web/Customers/Contact/buttonSave')

    AbstractWeb.verifyMessage('Object Repository/Web/message', message)

    ResultSet rs = Statemnet.getNewOrganizationContact()

    while (rs.next()) {
        WebUI.verifyMatch(rs.getString('FULL_NAME'), fullNameContactInput, false, FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyMatch(rs.getString('EMAIL'), emailContactInput, false, FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyMatch(rs.getString('PHONE_NUMBER'), phoneNumberContactInput, false, FailureHandling.STOP_ON_FAILURE)
    }
} else {
    WebUI.verifyElementNotClickable(findTestObject('Object Repository/Web/Customers/Contact/buttonSave'), FailureHandling.STOP_ON_FAILURE)
}