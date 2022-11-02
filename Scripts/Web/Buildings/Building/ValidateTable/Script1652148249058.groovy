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
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.testng.Assert
import com.database.*
import java.sql.*

WebDriver driver = DriverFactory.getWebDriver()
Actions actions = new Actions(driver)

//Verify table title
AbstractWeb.verifyText('Object Repository/Web/Buildings/titleTable', titleTable)

//Verify table header
AbstractWeb.verifyArrayText('Object Repository/Web/Buildings/headerTable', index, buildingCode, buildingName, buildingArea, floor, address, status, action)

//List row of table
List<WebElement> rows = WebUI.findWebElements(findTestObject('Object Repository/Web/Buildings/rowTable'), GlobalVariable.TIME_OUT)

AbstractWeb.getInforSizeOfPagination()
Assert.assertEquals(rows.size().toString(), GlobalVariable.INFOR_SIZE_OF_PAGINATION.getAt('endIndex'), 'Số bản ghi của bảng và phân trang khác nhau')

for(WebElement row : rows) {
	List<WebElement> colums = row.findElements(By.tagName('td'))
	ResultSet rs = Statemnet.getInforBuildingLatest(Integer.parseInt(colums.get(0).getText())-1, 1)
	while(rs.next()) {
		actions.moveToElement(colums.get(1)).perform()
		WebUI.verifyMatch(AbstractWeb.getText('Object Repository/Web/DashboardPage/tooltipContent'), rs.getString('CODE'), false, FailureHandling.STOP_ON_FAILURE)
		
		actions.moveToElement(colums.get(2)).perform()
		WebUI.verifyMatch(AbstractWeb.getText('Object Repository/Web/DashboardPage/tooltipContent'), rs.getString('NAME'), false, FailureHandling.STOP_ON_FAILURE)
		
		WebUI.verifyMatch(colums.get(3).getText(),rs.getString('TOTAL_AREA'), false, FailureHandling.STOP_ON_FAILURE)
		
		WebUI.verifyMatch(colums.get(4).getText(),rs.getString('TOTAL_FLOOR'), false, FailureHandling.STOP_ON_FAILURE)
		
		actions.moveToElement(colums.get(5)).perform()
		WebUI.verifyMatch(AbstractWeb.getText('Object Repository/Web/DashboardPage/tooltipContent'), rs.getString('ADDRESS'), false, FailureHandling.CONTINUE_ON_FAILURE)
		
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