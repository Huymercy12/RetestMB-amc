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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.testData.DataExcelUtils
import com.testData.FakerData
import com.testData.VariableCollections
import com.web.AbstractWeb
import internal.GlobalVariable
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import org.testng.Assert

String buildingId
Map map = DataExcelUtils.getDataByIndex(1, 'Data Files/Web/Building/CreateBuilding')

WebUI.callTestCase(findTestCase('Test Cases/Web/Buildings/Building/CreateBuilding'), map)

ResultSet rs = Statemnet.getInforBuildingSearch(VariableCollections.map.get('buildingCode'))
while (rs.next()) {
	buildingId = rs.getString('ID')
}
ConnectionDatabase.closeDatabaseConnection()

AbstractWeb.clickButton('Object Repository/Web/Buildings/buttonCreate')
int numberFloor = FakerData.randomFloor(Integer.parseInt(VariableCollections.map.get('numberFloor')))

AbstractWeb.sendText('Object Repository/Web/Buildings/CreateFloor/textboxNumberFloor', String.valueOf(numberFloor))
AbstractWeb.sendText('Object Repository/Web/Buildings/CreateFloor/textboxFloorName', floorName)
AbstractWeb.sendText('Object Repository/Web/Buildings/CreateFloor/textboxTotalArea', totalArea)

AbstractWeb.clickButton('Object Repository/Web/Buildings/CreateFloor/buttonCreateFloor')
AbstractWeb.verifyMessage('Object Repository/Web/message', messageSuccess)

List<WebElement> rows = WebUI.findWebElements(findTestObject('Object Repository/Web/Buildings/rowTable'), GlobalVariable.TIME_OUT)
for (WebElement row : rows) {
	List<WebElement> columns = row.findElements(By.tagName('td'))
	ResultSet rs1 = Statemnet.getFloor(buildingId)
	while (rs1.next()) {
		WebUI.verifyMatch(buildingId, rs1.getString('BUILDING_ID'), false, FailureHandling.STOP_ON_FAILURE)
		WebUI.verifyMatch(columns[0].getText(), rs1.getString('FLOOR_NUMBER'), false, FailureHandling.STOP_ON_FAILURE)
		WebUI.verifyMatch(columns[1].getText(), rs1.getString('NAME'), false, FailureHandling.STOP_ON_FAILURE)
		
		String totalAreaDisplay = columns[2].getText()
		String totalAreaDB =  rs1.getString('TOTAL_AREA')
		boolean b1 = totalAreaDisplay.contains(totalAreaDB)
		
		String availableAreaDisplay = columns[4].getText()
		String availableAreaDB = rs1.getString('AVAILABLE_AREA')
		boolean b2 = availableAreaDisplay.contains(availableAreaDB)
		if (b1 == true && b2 == true) {
			Assert.assertTrue(true)
		} else {
			Assert.assertTrue(false)
		}	 
		
		List<WebElement> listButton = columns[5].findElements(By.tagName('mb-button-action'))
		for (WebElement element : listButton) {
			Assert.assertTrue(element.isDisplayed())
		}
	}
	ConnectionDatabase.closeDatabaseConnection()
}