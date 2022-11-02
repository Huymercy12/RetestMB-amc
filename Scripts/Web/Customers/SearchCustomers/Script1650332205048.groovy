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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.web.AbstractWeb as AbstractWeb
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.testng.Assert

import com.database.*
import java.sql.*

KeywordUtil.logInfo(description)
KeywordUtil.logInfo(expected)

WebDriver driver = DriverFactory.getWebDriver()
Actions actions = new Actions(driver)

// verify table title
AbstractWeb.verifyText('Object Repository/Web/Customers/titleTable', tableTitle)

WebUI.verifyMatch(AbstractWeb.getAttribute('Object Repository/Web/Customers/placeholderSearch', 'placeholder'),search, false, FailureHandling.STOP_ON_FAILURE)

//Search
AbstractWeb.waitAndSearch('Object Repository/Web/Customers/placeholderSearch', textSearch)

//verify table header
AbstractWeb.verifyArrayText('Object Repository/Web/Customers/headerTable', index.toString(), company.toString(), representative.toString(), businessRegistrationCode.toString(),
		email.toString(), phoneNumber.toString(), status.toString(), action.toString())

if (expected.toString().equalsIgnoreCase('Không ra kết quả')) {
	WebUI.verifyElementVisible(findTestObject('Object Repository/Web/imageEmpty'), FailureHandling.STOP_ON_FAILURE)
	AbstractWeb.verifyText('Object Repository/Web/Buildings/messageNoRecord', messageNoRecord)
}else if(expected.toString().equalsIgnoreCase('Không thay đổi')) {
	WebUI.callTestCase(findTestCase('Test Cases/Web/Customers/ValidateTable'),null, FailureHandling.STOP_ON_FAILURE)
}
else {

	//list row of table
	List<WebElement> rows = WebUI.findWebElements(findTestObject('Object Repository/Web/Customers/rowTable'), GlobalVariable.TIME_OUT)

	AbstractWeb.getInforSizeOfPagination()
	Assert.assertEquals(rows.size().toString(), GlobalVariable.INFOR_SIZE_OF_PAGINATION.getAt('endIndex'), 'Số bản ghi của bảng và phân trang khác nhau')

	for(WebElement row:rows) {
		List<WebElement> colums = row.findElements(By.tagName('td'))

		actions.moveToElement(colums.get(3)).perform()
		ResultSet rs = Statemnet.getOrganizationContact(AbstractWeb.getText('Object Repository/Web/DashboardPage/tooltipContent'))
		while(rs.next()) {
			actions.moveToElement(colums.get(1)).perform()
			WebUI.verifyMatch(AbstractWeb.getText('Object Repository/Web/DashboardPage/tooltipContent'),rs.getString('NAME'), false, FailureHandling.STOP_ON_FAILURE)
			boolean b1 =AbstractWeb.convertString(AbstractWeb.getText('Object Repository/Web/DashboardPage/tooltipContent')).contains(AbstractWeb.convertString(textSearch))

			actions.moveToElement(colums.get(2)).perform()
			WebUI.verifyMatch(AbstractWeb.getText('Object Repository/Web/DashboardPage/tooltipContent'),rs.getString('LEGAL_REPRESENTATIVE'), false, FailureHandling.STOP_ON_FAILURE)
			boolean b2 =AbstractWeb.convertString(AbstractWeb.getText('Object Repository/Web/DashboardPage/tooltipContent')).contains(AbstractWeb.convertString(textSearch))

			actions.moveToElement(colums.get(3)).perform()
			WebUI.verifyMatch(AbstractWeb.getText('Object Repository/Web/DashboardPage/tooltipContent'),rs.getString('BUSINESS_CODE'), false, FailureHandling.STOP_ON_FAILURE)
			boolean b3 =AbstractWeb.convertString(AbstractWeb.getText('Object Repository/Web/DashboardPage/tooltipContent')).contains(AbstractWeb.convertString(textSearch))

			actions.moveToElement(colums.get(4)).perform()
			WebUI.verifyMatch(AbstractWeb.getText('Object Repository/Web/DashboardPage/tooltipContent'),rs.getString('EMAIL'), false, FailureHandling.STOP_ON_FAILURE)
			boolean b4 =AbstractWeb.convertString(AbstractWeb.getText('Object Repository/Web/DashboardPage/tooltipContent')).contains(AbstractWeb.convertString(textSearch))

			WebUI.verifyMatch(colums.get(5).getText(), rs.getString('PHONE_NUMBER'), false, FailureHandling.CONTINUE_ON_FAILURE)
			boolean b5 =colums.get(5).getText().contains(AbstractWeb.convertString(textSearch))

			//Kiểm tra xem từ khoá tìm kiếm có nằm trong kết quả ko
			if (b1==true || b2 ==true || b3==true || b4 == true || b5 == true) {
				Assert.assertTrue(true)
			} else {
				Assert.assertTrue(false)
			}

			if (colums.get(6).findElement(By.tagName('span')).getText() == 'Hoạt động') {
				WebUI.verifyMatch('ACTIVE', rs.getString('STATUS'), false, FailureHandling.CONTINUE_ON_FAILURE)
			}

			if (colums.get(6).findElement(By.tagName('span')).getText() == 'Không hoạt động') {
				WebUI.verifyMatch('INACTIVE', rs.getString('STATUS'), false, FailureHandling.CONTINUE_ON_FAILURE)
			}

			List<WebElement> listButton = colums.get(7).findElements(By.tagName('mb-button-action'))

			for (WebElement element : listButton) {
				Assert.assertTrue(element.isDisplayed())
			}
		}

		ConnectionDatabase.closeDatabaseConnection()
	}
}








