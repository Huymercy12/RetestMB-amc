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
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

WebDriver driver = DriverFactory.getWebDriver()
Actions action = new Actions(driver)

Map map = DataExcelUtils.getDataByIndex(1, 'Data Files/Web/Building/CreateBuilding')

WebUI.callTestCase(findTestCase('Test Cases/Web/Buildings/Building/CreateBuilding'), map, FailureHandling.STOP_ON_FAILURE)

//Click on info building
AbstractWeb.waitVerifyElementAndClick('Object Repository/Web/Buildings/tabInfoBuilding')
KeywordUtil.logInfo(description)

String buildingNameRandom
String floorRandom

if (buildingName.equals('random')) {
	buildingNameRandom = FakerData.randomBuildingName()
} else {
	buildingNameRandom = buildingName
}

if (buildingName.equals('random101')) {
	buildingNameRandom = FakerData.randomDataInput(101)
}

if (buildingName.equals('randomSpace')) {
	buildingNameRandom = FakerData.randomDataWithSpace(9)
}

if (floor.equals('random')) {
	floorRandom = FakerData.randomFloor(200)
} else {
	floorRandom = floor
}

//Edit building
if (buildingNameRandom.equals('')) {
	WebUI.setText(findTestObject('Object Repository/Web/Buildings/EditBuilding/textboxBuildingName'), buildingNameRandom + 'a' + Keys.BACK_SPACE, FailureHandling.STOP_ON_FAILURE)
} else {
	WebUI.setText(findTestObject('Object Repository/Web/Buildings/EditBuilding/textboxBuildingName'), buildingNameRandom, FailureHandling.STOP_ON_FAILURE)
}

if (floorRandom.equals('')) {
	WebUI.setText(findTestObject('Object Repository/Web/Buildings/EditBuilding/textboxFloor'), floorRandom + '1' + Keys.BACK_SPACE, FailureHandling.STOP_ON_FAILURE)
} else {
	WebUI.setText(findTestObject('Object Repository/Web/Buildings/EditBuilding/textboxFloor'), floorRandom, FailureHandling.STOP_ON_FAILURE)
}

if (area.equals('')) {
	WebUI.setText(findTestObject('Object Repository/Web/Buildings/EditBuilding/textboxArea'), area + '1' + Keys.BACK_SPACE, FailureHandling.STOP_ON_FAILURE)
} else {
	WebUI.setText(findTestObject('Object Repository/Web/Buildings/EditBuilding/textboxArea'), area, FailureHandling.STOP_ON_FAILURE)
}

if (address.equals('')) {
	WebUI.setText(findTestObject('Object Repository/Web/Buildings/EditBuilding/textboxAddress'), address  + 'a' + Keys.BACK_SPACE, FailureHandling.STOP_ON_FAILURE)
} else {
	WebUI.setText(findTestObject('Object Repository/Web/Buildings/EditBuilding/textboxAddress'), address, FailureHandling.STOP_ON_FAILURE)
}

WebUI.click(findTestObject('Object Repository/Web/Buildings/EditBuilding/statusBuilding'), FailureHandling.STOP_ON_FAILURE)

if (statusBuilding.equals('INACTIVE')) {
	AbstractWeb.waitVerifyElementAndClick('Object Repository/Web/Buildings/EditBuilding/statusInactive')
} else {
	AbstractWeb.waitVerifyElementAndClick('Object Repository/Web/Buildings/EditBuilding/statusActive')
}

if(note.equals('')) {
	WebUI.setText(findTestObject('Object Repository/Web/Buildings/EditBuilding/textboxNote'), note + 'a' + Keys.BACK_SPACE, FailureHandling.STOP_ON_FAILURE)
} else {
	WebUI.setText(findTestObject('Object Repository/Web/Buildings/EditBuilding/textboxNote'), note, FailureHandling.STOP_ON_FAILURE)
}

String buidingNameInput = AbstractWeb.getAttribute('Object Repository/Web/Buildings/EditBuilding/textboxBuildingName', 'value').trim()
String floorInput = AbstractWeb.getAttribute('Object Repository/Web/Buildings/EditBuilding/textboxFloor', 'value')
String addressInput = AbstractWeb.getAttribute('Object Repository/Web/Buildings/EditBuilding/textboxAddress', 'value').trim()
String noteInput = AbstractWeb.getAttribute('Object Repository/Web/Buildings/EditBuilding/textboxNote', 'value').trim()

if (note.equals('')) {
	noteInput = 'null'
}

String buttonDisable = AbstractWeb.getAttribute('Object Repository/Web/Buildings/EditBuilding/buttonUpdate', 'disabled')

if (buttonDisable == 'true') {
	WebUI.verifyElementNotClickable(findTestObject('Object Repository/Web/Buildings/EditBuilding/buttonUpdate'), FailureHandling.STOP_ON_FAILURE)
} else {
	AbstractWeb.clickButton('Object Repository/Web/Buildings/EditBuilding/buttonUpdate')
	//Verify display
	AbstractWeb.verifyText('Object Repository/Web/Buildings/EditBuilding/titlePopup', titlePopup)
	AbstractWeb.verifyText('Object Repository/Web/Buildings/EditBuilding/contentPopup', contentPopup)
	AbstractWeb.verifyVisible('Object Repository/Web/Buildings/EditBuilding/buttonCancelPopup')
	AbstractWeb.verifyVisible('Object Repository/Web/Buildings/EditBuilding/buttonAcceptPopup')
	
	//Verify info edit
	AbstractWeb.clickButton('Object Repository/Web/Buildings/EditBuilding/buttonAcceptPopup')
	AbstractWeb.verifyMessage('Object Repository/Web/message', message)
	
	if (message.equals('Thành công')) {
		ResultSet rs = Statemnet.getInforBuildingSearch(VariableCollections.map.get('buildingCode'))
		while (rs.next()) {
			WebUI.verifyMatch(buidingNameInput.trim(), rs.getString('NAME'), false, FailureHandling.STOP_ON_FAILURE)
			if (Integer.parseInt(floorInput) > 200) {
				WebUI.verifyMatch('200', rs.getString('TOTAL_FLOOR'), false, FailureHandling.STOP_ON_FAILURE)
			} else {
				WebUI.verifyMatch(floorInput, rs.getString('TOTAL_FLOOR'), false, FailureHandling.STOP_ON_FAILURE)
			}
			WebUI.verifyMatch(area, rs.getString('TOTAL_AREA'), false, FailureHandling.STOP_ON_FAILURE)
			WebUI.verifyMatch(addressInput, rs.getString('ADDRESS'), false, FailureHandling.STOP_ON_FAILURE)
			WebUI.verifyMatch(statusBuilding, rs.getString('STATUS'), false, FailureHandling.STOP_ON_FAILURE)
			WebUI.verifyMatch(noteInput, rs.getString('NOTE').toString(), false, FailureHandling.STOP_ON_FAILURE)
		}
		ConnectionDatabase.closeDatabaseConnection()
	}
}