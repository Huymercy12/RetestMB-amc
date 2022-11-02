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
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.interactions.Actions as Actions
import org.testng.Assert as Assert
import com.database.*
import java.sql.*

WebDriver driver = DriverFactory.getWebDriver()

Actions actions = new Actions(driver)

// verify table title
AbstractWeb.verifyText('Object Repository/Web/titleTable', tableTitle)

//verify table header
AbstractWeb.verifyArrayText('Object Repository/Web/Customers/headerTable', index, buildingCode, floorName, qRName,createDate,createBy,updateDate,status,action)

//list row of table
List<WebElement> rows = WebUI.findWebElements(findTestObject('Object Repository/Web/Complaints/FeedbackTemplate/rowTable'), GlobalVariable.TIME_OUT)

AbstractWeb.getInforSizeOfPagination()

WebUI.verifyEqual(rows.size().toString(),GlobalVariable.INFOR_SIZE_OF_PAGINATION.getAt('endIndex'), FailureHandling.STOP_ON_FAILURE)

for (WebElement row : rows) {
	List<WebElement> colums = row.findElements(By.tagName('td'))

	ResultSet rs = Statemnet.getComplaintTemplateByBuildingCode(colums[1].getText(),Integer.parseInt(colums[0].getText()) - 1, 1)

	while (rs.next()) {
		WebUI.verifyEqual(colums[1].getText(), rs.getString('CODE'), FailureHandling.STOP_ON_FAILURE)
		
		WebUI.verifyEqual(colums[2].getText(), rs.getString('NAME'), FailureHandling.STOP_ON_FAILURE)
		
		actions.moveToElement(colums[3]).perform()

		WebUI.verifyEqual(AbstractWeb.getText('Object Repository/Web/DashboardPage/tooltipContent'), rs.getString('TITLE'),
				 FailureHandling.STOP_ON_FAILURE)

		WebUI.verifyEqual(colums[4].getText(), AbstractWeb.changeDateFormat(rs.getString('CREATED_AT'),'yyyy-MM-dd HH:mm:ss.S', 'dd/MM/yyyy'), FailureHandling.STOP_ON_FAILURE)
		
		WebUI.verifyEqual(colums[5].getText(), rs.getString('FULL_NAME'), FailureHandling.STOP_ON_FAILURE)
		
		WebUI.verifyEqual(colums[6].getText(), AbstractWeb.changeDateFormat(rs.getString('LAST_MODIFIED_AT'),'yyyy-MM-dd HH:mm:ss.S', 'dd/MM/yyyy'), FailureHandling.STOP_ON_FAILURE)
		
		if (colums[7].getText() == 'Hoạt động') {
			WebUI.verifyMatch('ACTIVE', rs.getString('STATUS'), false, FailureHandling.CONTINUE_ON_FAILURE)
		}

		if (colums[7].getText() == 'Không hoạt động') {
			WebUI.verifyMatch('INACTIVE', rs.getString('STATUS'), false, FailureHandling.CONTINUE_ON_FAILURE)
		}

		List<WebElement> listButton = colums.get(7).findElements(By.tagName('mb-button-action'))

		for (WebElement element : listButton) {
			Assert.assertTrue(element.isDisplayed())
		}
	}

	ConnectionDatabase.closeDatabaseConnection()
}