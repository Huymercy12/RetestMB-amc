import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.testData.*
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
import com.web.AbstractWeb
import internal.GlobalVariable
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.testng.Assert
import java.sql.*

WebDriver driver = DriverFactory.getWebDriver()
KeywordUtil.logInfo(description)
AbstractWeb.clickButton('Object Repository/Web/Buildings/buttonCreate')

String buildingCodeRandom
String buildingNameRandom
String floorRandom

if (buildingCode.equals('random')) {
	buildingCodeRandom = FakerData.randomBuildingCode()
} else if (buildingCode.equals('random51')){
	buildingCodeRandom = FakerData.randomDataInput(51)
} else {
	buildingCodeRandom = buildingCode
}

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

AbstractWeb.sendText('Object Repository/Web/Buildings/CreateBuilding/textboxBuildingCode', buildingCodeRandom)

AbstractWeb.sendText('Object Repository/Web/Buildings/CreateBuilding/textboxBuildingName', buildingNameRandom)

AbstractWeb.sendText('Object Repository/Web/Buildings/CreateBuilding/textboxFloor', floorRandom)

AbstractWeb.sendText('Object Repository/Web/Buildings/CreateBuilding/textboxArea', area)

AbstractWeb.sendText('Object Repository/Web/Buildings/CreateBuilding/textboxAddress', address)

WebUI.click(findTestObject('Object Repository/Web/Buildings/CreateBuilding/statusBuilding'), FailureHandling.STOP_ON_FAILURE)

if (statusBuilding.equals('INACTIVE')) {
	AbstractWeb.waitVerifyElementAndClick('Object Repository/Web/Buildings/CreateBuilding/statusInactive')
} else {
	AbstractWeb.waitVerifyElementAndClick('Object Repository/Web/Buildings/CreateBuilding/statusActive')
}

AbstractWeb.sendText('Object Repository/Web/Buildings/CreateBuilding/textboxNote', note)

String buidingCodeInput = AbstractWeb.getAttribute('Object Repository/Web/Buildings/CreateBuilding/textboxBuildingCode', 'value')
String buidingNameInput = AbstractWeb.getAttribute('Object Repository/Web/Buildings/CreateBuilding/textboxBuildingName', 'value').trim()
String addressInput = AbstractWeb.getAttribute('Object Repository/Web/Buildings/CreateBuilding/textboxAddress', 'value').trim()
String noteInput = AbstractWeb.getAttribute('Object Repository/Web/Buildings/CreateBuilding/textboxNote', 'value').trim()

if (note.equals('')) {
	noteInput = 'null'
}

String buttonDisable = AbstractWeb.getAttribute('Object Repository/Web/Buildings/CreateBuilding/buttonCreate', 'disabled')

if (buttonDisable == 'true') {
	WebUI.verifyElementNotClickable(findTestObject('Object Repository/Web/Buildings/CreateBuilding/buttonCreate'), FailureHandling.STOP_ON_FAILURE)
} else {
	AbstractWeb.clickButton('Object Repository/Web/Buildings/CreateBuilding/buttonCreate')
	//Verify display
	AbstractWeb.verifyText('Object Repository/Web/Buildings/CreateBuilding/titlePopup', titlePopup)
	AbstractWeb.verifyText('Object Repository/Web/Buildings/CreateBuilding/contentPopup', contentPopup)
	AbstractWeb.verifyVisible('Object Repository/Web/Buildings/CreateBuilding/buttonCancelPopup')
	AbstractWeb.verifyVisible('Object Repository/Web/Buildings/CreateBuilding/buttonAcceptPopup')
	
	//Verify create building
	AbstractWeb.clickButton('Object Repository/Web/Buildings/CreateBuilding/buttonAcceptPopup')
	AbstractWeb.verifyMessage('Object Repository/Web/message', message)
	
	if (message.equals('Tạo mới tòa nhà thành công')) {
		ResultSet rs = Statemnet.getInforBuildingSearch(buidingCodeInput)
		while (rs.next()) {
			WebUI.verifyMatch(buidingCodeInput, rs.getString('CODE'), false, FailureHandling.STOP_ON_FAILURE)
			WebUI.verifyMatch(buidingNameInput.trim(), rs.getString('NAME'), false, FailureHandling.STOP_ON_FAILURE)
			if (Integer.parseInt(floorRandom) > 200) {
				WebUI.verifyMatch('200', rs.getString('TOTAL_FLOOR'), false, FailureHandling.STOP_ON_FAILURE)
			} else {
				WebUI.verifyMatch(floorRandom, rs.getString('TOTAL_FLOOR'), false, FailureHandling.STOP_ON_FAILURE)
			}
			WebUI.verifyMatch(area, rs.getString('TOTAL_AREA'), false, FailureHandling.STOP_ON_FAILURE)
			WebUI.verifyMatch(addressInput, rs.getString('ADDRESS'), false, FailureHandling.STOP_ON_FAILURE)
			WebUI.verifyMatch(statusBuilding, rs.getString('STATUS'), false, FailureHandling.STOP_ON_FAILURE)
			WebUI.verifyMatch(noteInput, rs.getString('NOTE').toString(), false, FailureHandling.STOP_ON_FAILURE)
		}
		ConnectionDatabase.closeDatabaseConnection()
		VariableCollections.map.put('buildingCode', buidingCodeInput)
		VariableCollections.map.put('numberFloor', floorRandom)
	}
}