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
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.testData.DataExcelUtils
import com.testData.FakerData
import com.testData.VariableCollections
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

String buildingId

Map map = DataExcelUtils.getDataByIndex(1, 'Data Files/Web/Building/CreateBuilding')
map.putAll(['floorName' : floorName, 'totalArea' : totalArea])

WebUI.callTestCase(findTestCase('Test Cases/Web/Buildings/Building/CreateFloor'), map, FailureHandling.STOP_ON_FAILURE)

ResultSet rs = Statemnet.getInforBuildingSearch(VariableCollections.map.get('buildingCode'))

while (rs.next()) {
	buildingId = rs.getString('ID')
}
ConnectionDatabase.closeDatabaseConnection()

List<WebElement> rows = WebUI.findWebElements(findTestObject('Object Repository/Web/Buildings/rowTable'), GlobalVariable.TIME_OUT)
for (WebElement row : rows) {
	List<WebElement> columns = row.findElements(By.tagName('td'))
	
	ResultSet rs1 = Statemnet.getFloor(buildingId)
	while (rs1.next()) {
		WebUI.verifyMatch(columns[1].getText(), rs1.getString('NAME'), false, FailureHandling.STOP_ON_FAILURE)
		
		String[] totalArea = columns[2].getText().split('\\(')
		String[] availableArea = columns[4].getText().split('\\(')
		float rentedArea = Float.parseFloat(totalArea[0]) - Float.parseFloat(availableArea[0])
		
		boolean b1 = columns[2].getText().contains(rs1.getString('TOTAL_AREA'))
		boolean b2 = columns[3].getText().contains(String.valueOf(rentedArea))
		boolean b3 = columns[4].getText().contains(rs1.getString('AVAILABLE_AREA'))
		
		if (b1 == true && b2 == true && b3 == true) {
			Assert.assertTrue(true)
		} else {
			Assert.assertTrue(false)
		}
	}
	ConnectionDatabase.closeDatabaseConnection()
	
	List<WebElement> listButton = columns[5].findElements(By.tagName('mb-button-action'))
	
	actions.moveToElement(listButton[0]).perform()
	if (AbstractWeb.getText('Object Repository/Web/DashboardPage/tooltipContent') == 'Chi tiết') {
		actions.click().perform()
		AbstractWeb.verifyText('Object Repository/Web/Buildings/titlePopup', titleEditFloor)
		String floorNameRandom = FakerData.randomString()
		AbstractWeb.sendText('Object Repository/Web/Buildings/CreateFloor/textboxFloorName', floorNameRandom)
		
		//Input total area valid
		AbstractWeb.sendText('Object Repository/Web/Buildings/CreateFloor/textboxTotalArea', areaFloor)
		AbstractWeb.clickButton('Object Repository/Web/Buildings/CreateFloor/buttonCreateFloor')
		AbstractWeb.verifyMessage('Object Repository/Web/message', msgEditSuccess)
		
	}
}

List<WebElement> rowsAferEdit = WebUI.findWebElements(findTestObject('Object Repository/Web/Buildings/rowTable'), GlobalVariable.TIME_OUT)
for (WebElement row : rowsAferEdit) {
	List<WebElement> columns = row.findElements(By.tagName('td'))
	
	ResultSet rs2 = Statemnet.getFloor(buildingId)
	while (rs2.next()) {
		WebUI.verifyMatch(buildingId, rs2.getString('BUILDING_ID'), false, FailureHandling.STOP_ON_FAILURE)
		WebUI.verifyMatch(columns[0].getText(), rs2.getString('FLOOR_NUMBER'), false, FailureHandling.STOP_ON_FAILURE)
		WebUI.verifyMatch(columns[1].getText(), rs2.getString('NAME'), false, FailureHandling.STOP_ON_FAILURE)
		
		String[] totalArea = columns[2].getText().split('\\(')
		String[] availableArea = columns[4].getText().split('\\(')
		float rentedArea = Float.parseFloat(totalArea[0]) - Float.parseFloat(availableArea[0])
		
		boolean b1 = columns[2].getText().contains(rs2.getString('TOTAL_AREA'))
		boolean b2 = columns[3].getText().contains(String.valueOf(rentedArea))
		boolean b3 = columns[4].getText().contains(rs2.getString('AVAILABLE_AREA'))
		
		if (b1 == true && b2 == true && b3 == true) {
			Assert.assertTrue(true)
		} else {
			Assert.assertTrue(false)
		}
	}
	ConnectionDatabase.closeDatabaseConnection()
}