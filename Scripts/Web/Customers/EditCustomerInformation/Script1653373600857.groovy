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
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.testData.DataExcelUtils as DataExcelUtils
import com.testData.FakerData as FakerData
import com.testData.VariableCollections as VariableCollections
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

import com.api.Authenticate as Authenticate
import com.database.*
import java.sql.*

public enum Type{
	INTERNAL_CUSTOMER('Khách hàng nội bộ'),
	EXTERNAL_CUSTOMER('Khách hàng thuê ngoài')
	private String value
	Type (String value) {
		this.value=value
	}
	
	public static Type getTypeByValue(String value) {
        for (Type type : Type.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        return null;
    }
}

WebDriver driver = DriverFactory.getWebDriver()

Actions action = new Actions(driver)

List<WebElement> cell = WebUI.findWebElements(findTestObject('Object Repository/Web/Customers/tableFistLine'), GlobalVariable.TIME_OUT)

action.moveToElement(cell.get(1)).perform()

String company = AbstractWeb.getText('Object Repository/Web/DashboardPage/tooltipContent')

action.moveToElement(cell.get(2)).perform()

String legalRepresentative = AbstractWeb.getText('Object Repository/Web/DashboardPage/tooltipContent')

action.moveToElement(cell.get(3)).perform()

String businessCode = AbstractWeb.getText('Object Repository/Web/DashboardPage/tooltipContent')

action.moveToElement(cell.get(4)).perform()

String email = AbstractWeb.getText('Object Repository/Web/DashboardPage/tooltipContent')

String phoneNumber = cell.get(5).getText()

String status = cell.get(6).getText()

AbstractWeb.clickButton('Object Repository/Web/Customers/buttonEdit')

//validate label
//AbstractWeb.verifyText('Object Repository/Web/Customers/titleTable', titleTable)

AbstractWeb.verifyArrayText('Object Repository/Web/Customers/InformationCustomerPage/label', labelCompany, labelRepresentative, 
    labelEmail, labelPhoneNumber, address, foundingDate, labelStatus)

// verify table vs editPage
WebUI.verifyEqual(AbstractWeb.getAttribute('Object Repository/Web/Customers/InformationCustomerPage/textBoxCompany', 'value'), 
    company, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyEqual(AbstractWeb.getAttribute('Object Repository/Web/Customers/InformationCustomerPage/textBoxRepresentative', 
        'value'), legalRepresentative, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyEqual(AbstractWeb.getAttribute('Object Repository/Web/Customers/InformationCustomerPage/textBoxEmail', 'value'), 
    email, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyEqual(AbstractWeb.getAttribute('Object Repository/Web/Customers/InformationCustomerPage/textBoxPhoneNumber', 
        'value'), phoneNumber, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyEqual(AbstractWeb.getAttribute('Object Repository/Web/Customers/InformationCustomerPage/selectStatus', 'title'), 
    status, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyEqual(AbstractWeb.getText('Object Repository/Web/Customers/InformationCustomerPage/informationDetailCustomer', 
        [('text') : 'Mã ĐKKD: ']), businessCode, FailureHandling.STOP_ON_FAILURE)

//verify editPage vs database
ResultSet rs = Statemnet.getORGANIZATION_INFOR(company)
while(rs.next()) {
	WebUI.verifyEqual(AbstractWeb.getText('Object Repository/Web/Customers/InformationCustomerPage/informationDetailCustomer', 
        [('text') : 'Mã khách hàng: ']), rs.getString('CODE'), FailureHandling.STOP_ON_FAILURE)
	
	WebUI.verifyEqual(Type.getTypeByValue(AbstractWeb.getText('Object Repository/Web/Customers/InformationCustomerPage/informationDetailCustomer',
		[('text') : 'Phân loại: '])).toString(), rs.getString('TYPE').toString(), FailureHandling.STOP_ON_FAILURE)
	
	WebUI.verifyEqual(AbstractWeb.getAttribute('Object Repository/Web/Customers/InformationCustomerPage/textBoxAddress',
		'value'), rs.getString('INVOICE_ISSUING_ADDRESS').toString(), FailureHandling.STOP_ON_FAILURE)
	
	WebUI.verifyEqual( AbstractWeb.changeDateFormat(
		AbstractWeb.getAttribute('Object Repository/Web/Customers/InformationCustomerPage/textBoxFoundingDate', 'value'),"dd/MM/yyyy","yyyy-MM-dd HH:mm:ss.S"),
		rs.getString('INCORPORATION_DATE').toString(), FailureHandling.STOP_ON_FAILURE)
}
ConnectionDatabase.closeDatabaseConnection()

//input edit data
String companyInput = FakerData.randomStringQR(10)
String representativeInput = FakerData.randomStringQR(10)
String emailInput = FakerData.randomEmail()
String phoneNumberInput = FakerData.randomPhoneNumber()
String addressInput = FakerData.randomStringQR(100)
String foundingDateInput = FakerData.randomDate()
AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxCompany', companyInput)
AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxRepresentative',representativeInput )
AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxEmail', emailInput)
AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxPhoneNumber',phoneNumberInput)
AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxAddress', addressInput)
AbstractWeb.sendText('Object Repository/Web/Customers/InformationCustomerPage/textBoxFoundingDate', foundingDateInput + Keys.ENTER)

if(actions.toString().equalsIgnoreCase('cancel')) {
	AbstractWeb.clickButton('Object Repository/Web/Customers/InformationCustomerPage/buttonCancel')	
	ResultSet rs2 = Statemnet.getOrganizationByBusinessCode(businessCode)
	while(rs2.next()) {
		WebUI.verifyNotEqual(rs2.getString('NAME'), companyInput, FailureHandling.STOP_ON_FAILURE)
		WebUI.verifyNotEqual(rs2.getString('LEGAL_REPRESENTATIVE'), representativeInput, FailureHandling.STOP_ON_FAILURE)
		WebUI.verifyNotEqual(rs2.getString('EMAIL'), emailInput, FailureHandling.STOP_ON_FAILURE)
		WebUI.verifyNotEqual(rs2.getString('PHONE_NUMBER'), phoneNumberInput, FailureHandling.STOP_ON_FAILURE)
		WebUI.verifyNotEqual(rs2.getString('INVOICE_ISSUING_ADDRESS').toString(), addressInput, FailureHandling.STOP_ON_FAILURE)
		WebUI.verifyNotEqual(rs2.getString('INCORPORATION_DATE').toString(),  AbstractWeb.changeDateFormat(foundingDateInput,"dd/MM/yyyy","yyyy-MM-dd HH:mm:ss.S"), FailureHandling.STOP_ON_FAILURE)
	}
}else {
	AbstractWeb.clickButton('Object Repository/Web/Customers/InformationCustomerPage/buttonSave')
	AbstractWeb.verifyMessage('Object Repository/Web/message', message)
	ResultSet rs2 = Statemnet.getOrganizationByBusinessCode(businessCode)
	while(rs2.next()) {
		WebUI.verifyEqual(rs2.getString('NAME'), companyInput, FailureHandling.STOP_ON_FAILURE)
		WebUI.verifyEqual(rs2.getString('LEGAL_REPRESENTATIVE'), representativeInput, FailureHandling.STOP_ON_FAILURE)
		WebUI.verifyEqual(rs2.getString('EMAIL'), emailInput, FailureHandling.STOP_ON_FAILURE)
		WebUI.verifyEqual(rs2.getString('PHONE_NUMBER'), phoneNumberInput, FailureHandling.STOP_ON_FAILURE)
		WebUI.verifyEqual(rs2.getString('INVOICE_ISSUING_ADDRESS').toString(), addressInput, FailureHandling.STOP_ON_FAILURE)
		WebUI.verifyEqual(rs2.getString('INCORPORATION_DATE').toString(),  AbstractWeb.changeDateFormat(foundingDateInput,"dd/MM/yyyy","yyyy-MM-dd HH:mm:ss.S"), FailureHandling.STOP_ON_FAILURE)
	}
}
