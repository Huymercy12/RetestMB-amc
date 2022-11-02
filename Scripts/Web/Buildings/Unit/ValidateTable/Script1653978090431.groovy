import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.sql.ResultSet

import com.database.ConnectionDatabase
import com.database.Statemnet
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.keyword.builtin.VerifyMatchKeyword
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
import internal.GlobalVariable
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.testng.Assert

WebDriver driver = DriverFactory.getWebDriver()
Actions actions = new Actions(driver)

//Verify table title
AbstractWeb.verifyText('Object Repository/Web/Buildings/Unit/titleTable', tableTitle)

//Verify table header
AbstractWeb.verifyArrayText('Object Repository/Web/Buildings/Unit/headerTable', index, buildingCode, floor, unitCode, unitType, area, company, representative, status, action)

//List row of table
List<WebElement> rows = WebUI.findWebElements(findTestObject('Object Repository/Web/Buildings/Unit/rowTable'), GlobalVariable.TIME_OUT)

AbstractWeb.getInforSizeOfPagination()
Assert.assertEquals(rows.size().toString(), GlobalVariable.INFOR_SIZE_OF_PAGINATION.getAt('endIndex'), 'Số bản ghi của bảng và phân trang khác nhau')

for (WebElement row : rows) {
	String buildingId
	String floorId
	String unitId
	String unitCode
	String organizationId
	List<WebElement> columns = row.findElements(By.tagName('td'))
	ResultSet rs = Statemnet.getInforUnitLastest(Integer.parseInt(columns.get(0).getText()) - 1, 1)
	while(rs.next()) {
		buildingId = rs.getString('BUILDING_ID')
		floorId = rs.getString('FLOOR_ID')
		unitId = rs.getString('ID')
		
		if (columns.get(3).getText().equals('')) {
			unitCode = 'null'
		} else {
			unitCode = columns.get(3).getText()
		}
		WebUI.verifyMatch(unitCode, rs.getString('CODE').toString(), false, FailureHandling.STOP_ON_FAILURE)
		
		if (columns.get(4).getText() == 'Văn phòng') {
			WebUI.verifyMatch('OFFICE', rs.getString('TYPE'), false, FailureHandling.CONTINUE_ON_FAILURE)
		}
		if (columns.get(4).getText() == 'Thương mại') {
			WebUI.verifyMatch('COMMERCE', rs.getString('TYPE'), false, FailureHandling.CONTINUE_ON_FAILURE)
		}
		if (columns.get(4).getText() == '-') {
			WebUI.verifyMatch('null', rs.getString('TYPE').toString(), false, FailureHandling.CONTINUE_ON_FAILURE)
		}
		if (columns.get(4).getText() == 'Khác') {
			WebUI.verifyMatch('OTHER', rs.getString('TYPE'), false, FailureHandling.CONTINUE_ON_FAILURE)
		}
		
		WebUI.verifyMatch(columns.get(5).getText(), rs.getString('AREA'), false, FailureHandling.STOP_ON_FAILURE)
		
		if (columns.get(8).findElement(By.tagName('span')).getText() == 'Đã trả') {
			WebUI.verifyMatch('RETURNED', rs.getString('LEASING_STATUS'), false, FailureHandling.CONTINUE_ON_FAILURE)
		}
		if (columns.get(8).findElement(By.tagName('span')).getText() == 'Còn trống') {
			WebUI.verifyMatch('null', rs.getString('LEASING_STATUS').toString(), false, FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.verifyMatch(columns.get(6).getText(), '', false, FailureHandling.STOP_ON_FAILURE)
			WebUI.verifyMatch(columns.get(7).getText(), '', false, FailureHandling.STOP_ON_FAILURE)
		}
		if (columns.get(8).findElement(By.tagName('span')).getText() == 'Đã cho thuê') {
			WebUI.verifyMatch('LEASED', rs.getString('LEASING_STATUS'), false, FailureHandling.CONTINUE_ON_FAILURE)
		}
		if (columns.get(8).findElement(By.tagName('span')).getText() == 'Khả dụng') {
			WebUI.verifyMatch('AVAILABLE', rs.getString('LEASING_STATUS'), false, FailureHandling.CONTINUE_ON_FAILURE)
			WebUI.verifyMatch(columns.get(6).getText(), '', false, FailureHandling.STOP_ON_FAILURE)
			WebUI.verifyMatch(columns.get(7).getText(), '', false, FailureHandling.STOP_ON_FAILURE)
		}
		if (columns.get(8).findElement(By.tagName('span')).getText() == 'Đã đặt cọc') {
			WebUI.verifyMatch('DEPOSIT', rs.getString('LEASING_STATUS'), false, FailureHandling.CONTINUE_ON_FAILURE)
		}
		
		List<WebElement> listButton = columns.get(9).findElements(By.tagName('mb-button-action'))
			for (WebElement element : listButton) {
				Assert.assertTrue(element.isDisplayed())
		}
	}
	ConnectionDatabase.closeDatabaseConnection()
	
	ResultSet rs1 = Statemnet.getBuildingById(buildingId)
	while(rs1.next()) {
		actions.moveToElement(columns.get(1)).perform()
		WebUI.verifyMatch(AbstractWeb.getText('Object Repository/Web/DashboardPage/tooltipContent'), rs1.getString('CODE'), false, FailureHandling.STOP_ON_FAILURE)
	}
	ConnectionDatabase.closeDatabaseConnection()
	
	ResultSet rs2 = Statemnet.getFloorById(floorId)
	while(rs2.next()) {
		WebUI.verifyMatch(columns.get(2).getText(), rs2.getString('NAME'), false, FailureHandling.STOP_ON_FAILURE)
	}
	ConnectionDatabase.closeDatabaseConnection()
	
	ResultSet rs3 = Statemnet.getOrganizationLocationByUnitId(unitId)
	while(rs3.next()) {
		organizationId = rs3.getString('ORGANIZATION_ID')
	}
	ConnectionDatabase.closeDatabaseConnection()
	
	if (columns.get(6).getText().equals('') && columns.get(7).getText().equals('')) {
		Assert.assertTrue(true)
	} else {
		ResultSet rs4 = Statemnet.getORGANIZATION_INFOR_BY_ID(organizationId)
		while(rs4.next()) {
			actions.moveToElement(columns.get(6)).perform()
			WebUI.verifyMatch(AbstractWeb.getText('Object Repository/Web/DashboardPage/tooltipContent'), rs4.getString('NAME'), false, FailureHandling.STOP_ON_FAILURE)
			
			actions.moveToElement(columns.get(7)).perform()
			WebUI.verifyMatch(AbstractWeb.getText('Object Repository/Web/DashboardPage/tooltipContent'), rs4.getString('LEGAL_REPRESENTATIVE'), false, FailureHandling.STOP_ON_FAILURE)
		}
		ConnectionDatabase.closeDatabaseConnection()
	}
}
