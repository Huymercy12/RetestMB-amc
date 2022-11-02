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
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.web.AbstractWeb as AbstractWeb
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.interactions.Actions as Actions
import org.testng.Assert as Assert

import com.database.*
import java.sql.*
import com.testData.FakerData as FakerData

KeywordUtil.logInfo(infor)

WebUI.click(findTestObject('Object Repository/Web/Customers/buttonCreate'), FailureHandling.STOP_ON_FAILURE)

//input business Registration Code
if (businessRegistrationCode.toString().equalsIgnoreCase('trim space')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxBusinessRegistrationCode', ('  ' + 
        FakerData.randomString()) + '  ')
} else if (businessRegistrationCode.toString().equalsIgnoreCase('random50')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxBusinessRegistrationCode', FakerData.randomStringQR(
            50))
} else if (businessRegistrationCode.toString().equalsIgnoreCase('random51')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxBusinessRegistrationCode', FakerData.randomStringQR(
            51))

    Assert.assertEquals(AbstractWeb.getAttribute('Object Repository/Web/Customers/InformationCustomerPage/textBoxBusinessRegistrationCode', 
            'value').length(), 50)
} else if (businessRegistrationCode.toString().equalsIgnoreCase('random')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxBusinessRegistrationCode', FakerData.randomStringQR(
            10))
} else {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxBusinessRegistrationCode', businessRegistrationCode)
}

//select Type
AbstractWeb.moveToElementAndClick('Object Repository/Web/Customers/InformationCustomerPage/selectType')

AbstractWeb.moveToElementAndClick('Object Repository/Web/Customers/InformationCustomerPage/selectItemOption', [('option') : type])

// input company name
if (company.toString().equalsIgnoreCase('trim space')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxCompany', ('  ' + FakerData.randomUsername()) + 
        '  ')
} else if (company.toString().equalsIgnoreCase('random100')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxCompany', FakerData.randomStringQR(
            100))
} else if (company.toString().equalsIgnoreCase('random101')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxCompany', FakerData.randomStringQR(
            101))

    Assert.assertEquals(AbstractWeb.getAttribute('Object Repository/Web/Customers/InformationCustomerPage/textBoxCompany', 
            'value').length(), 100)
} else if (company.toString().equalsIgnoreCase('random')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxCompany', FakerData.randomStringQR(
            10))
} else {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxCompany', company)
}

//input representative
if (representative.toString().equalsIgnoreCase('trim space')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxRepresentative', ('  ' + FakerData.randomUsername()) + 
        '  ')
} else if (representative.toString().equalsIgnoreCase('random100')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxRepresentative', FakerData.randomStringQR(
            100))
} else if (representative.toString().equalsIgnoreCase('random101')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxRepresentative', FakerData.randomStringQR(
            101))

    Assert.assertEquals(AbstractWeb.getAttribute('Object Repository/Web/Customers/InformationCustomerPage/textBoxRepresentative', 
            'value').length(), 100)
} else if (representative.toString().equalsIgnoreCase('random')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxRepresentative', FakerData.randomStringQR(
            10))
} else {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxRepresentative', representative)
}

//input email
if (email.toString().equalsIgnoreCase('trim space')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxEmail', ('  ' + FakerData.randomEmail()) + 
        '  ')
} else if (email.toString().equalsIgnoreCase('random50')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxEmail', FakerData.randomStringQR(
            31) + FakerData.randomEmail())
} else if (email.toString().equalsIgnoreCase('random51')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxEmail', FakerData.randomStringQR(
            32) + FakerData.randomEmail())

    Assert.assertEquals(AbstractWeb.getAttribute('Object Repository/Web/Customers/InformationCustomerPage/textBoxEmail', 
            'value').length(), 50)
} else if (email.toString().equalsIgnoreCase('random')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxEmail', FakerData.randomEmail())
} else {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxEmail', email)
}

//input phone number
if (phoneNumber.toString().equalsIgnoreCase('trim space')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxPhoneNumber', ('  ' + FakerData.randomPhoneNumber()) + 
        '  ')
} else if (phoneNumber.toString().equalsIgnoreCase('random')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxPhoneNumber', FakerData.randomPhoneNumber())
} else {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxPhoneNumber', phoneNumber)
}

//input address
if (address.toString().equalsIgnoreCase('trim space')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxAddress', ('  ' + FakerData.randomString()) + 
        '  ')
} else if (address.toString().equalsIgnoreCase('random200')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxAddress', FakerData.randomStringQR(
            200))
} else if (address.toString().equalsIgnoreCase('random201')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxAddress', FakerData.randomStringQR(
            201))

    Assert.assertEquals(AbstractWeb.getAttribute('Object Repository/Web/Customers/InformationCustomerPage/textBoxAddress', 
            'value').length(), 200)
} else if (address.toString().equalsIgnoreCase('random')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxAddress', FakerData.randomStringQR(
            100))
} else {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxAddress', address)
}

//select status
AbstractWeb.moveToElementAndClick('Object Repository/Web/Customers/InformationCustomerPage/selectStatus')

AbstractWeb.moveToElementAndClick('Object Repository/Web/Customers/InformationCustomerPage/selectItemOption', [('option') : status])

if (foundingDate.toString().equalsIgnoreCase('random')) {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxFoundingDate', FakerData.randomDate() + Keys.ENTER)
} else {
    AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxFoundingDate', foundingDate + Keys.ENTER)
}

// cancel or create
if (action.toString().equalsIgnoreCase('Hủy')) {
    AbstractWeb.clickButton('Object Repository/Web/Customers/InformationCustomerPage/buttonCancel')

    AbstractWeb.verifyText('Object Repository/Web/Customers/titleTable', 'Danh sách khách hàng')
} else if (action.toString().equalsIgnoreCase('Lưu')) {
    String businessRegistrationCodeInput = WebUI.getAttribute(findTestObject('Object Repository/Web/Customers/InformationCustomerPage/textBoxBusinessRegistrationCode'), 
        'value', FailureHandling.STOP_ON_FAILURE).trim()

    String companyInput = WebUI.getAttribute(findTestObject('Object Repository/Web/Customers/InformationCustomerPage/textBoxCompany'), 
        'value', FailureHandling.STOP_ON_FAILURE).trim()

    String emailInput = WebUI.getAttribute(findTestObject('Object Repository/Web/Customers/InformationCustomerPage/textBoxEmail'), 
        'value', FailureHandling.STOP_ON_FAILURE).trim()

    String addressInput = WebUI.getAttribute(findTestObject('Object Repository/Web/Customers/InformationCustomerPage/textBoxAddress'), 
        'value', FailureHandling.STOP_ON_FAILURE).trim()

    if (addressInput.equalsIgnoreCase('')) {
        addressInput = 'null'
    }
    
    String representativeInput = WebUI.getAttribute(findTestObject('Object Repository/Web/Customers/InformationCustomerPage/textBoxRepresentative'), 
        'value', FailureHandling.STOP_ON_FAILURE).trim()

    String phoneNumberInput = WebUI.getAttribute(findTestObject('Object Repository/Web/Customers/InformationCustomerPage/textBoxPhoneNumber'), 
        'value', FailureHandling.STOP_ON_FAILURE).trim()

    String foundingDateInput = WebUI.getAttribute(findTestObject('Object Repository/Web/Customers/InformationCustomerPage/textBoxFoundingDate'), 
        'value', FailureHandling.STOP_ON_FAILURE).trim()

    if (foundingDateInput.equalsIgnoreCase('')) {
        foundingDateInput = 'null'
    } else {
        foundingDateInput = AbstractWeb.changeDateFormat(foundingDateInput,"dd/MM/yyyy","yyyy-MM-dd HH:mm:ss.S")
    }
    
    String statusInput

    if (status.toString().equalsIgnoreCase('Hoạt động')) {
        statusInput = 'ACTIVE'
    } else {
        statusInput = 'INACTIVE'
    }
    
    String typeInput

    if (type.toString().equalsIgnoreCase('Khách hàng nội bộ')) {
        typeInput = 'INTERNAL_CUSTOMER'
    } else {
        typeInput = 'EXTERNAL_CUSTOMER'
    }
    
    AbstractWeb.clickButton('Object Repository/Web/Customers/InformationCustomerPage/buttonSave')

    AbstractWeb.verifyMessage('Object Repository/Web/message', message)

    ResultSet rs = Statemnet.getOrganizationContact(businessRegistrationCodeInput)

    while (rs.next()) {
        WebUI.verifyMatch(rs.getString('BUSINESS_CODE'), businessRegistrationCodeInput, false, FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyMatch(rs.getString('NAME'), companyInput, false, FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyMatch(rs.getString('EMAIL'), emailInput, false, FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyMatch(rs.getString('INVOICE_ISSUING_ADDRESS').toString(), addressInput, false, FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyMatch(rs.getString('LEGAL_REPRESENTATIVE'), representativeInput, false, FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyMatch(rs.getString('PHONE_NUMBER'), phoneNumberInput, false, FailureHandling.STOP_ON_FAILURE)
		
        WebUI.verifyMatch(rs.getString('INCORPORATION_DATE').toString(), foundingDateInput, false, FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyMatch(rs.getString('STATUS'), statusInput, false, FailureHandling.STOP_ON_FAILURE)

        WebUI.verifyMatch(rs.getString('TYPE'), typeInput, false, FailureHandling.STOP_ON_FAILURE)
    }
	ConnectionDatabase.closeDatabaseConnection()
} else {
    WebUI.verifyElementNotClickable(findTestObject('Object Repository/Web/Customers/InformationCustomerPage/buttonSave'), 
        FailureHandling.STOP_ON_FAILURE)
}