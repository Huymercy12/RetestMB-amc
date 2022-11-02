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
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.web.AbstractWeb
import internal.GlobalVariable

//Click button create unit
AbstractWeb.clickButton('Object Repository/Web/Buildings/Unit/buttonCreate')

//Verify title
AbstractWeb.verifyText('Object Repository/Web/Buildings/Unit/CreateUnit/titleInfoUnit', titleInfoUnit)
AbstractWeb.verifyArrayText('Object Repository/Web/Buildings/Unit/CreateUnit/titleTextbox', titleBuilding, titleBuildingFloor, titleArea, titlePrice, titleUnitType, titleStatus, titleNote)

//Verify button
AbstractWeb.waitAndVerifyElement('Object Repository/Web/Buildings/Unit/CreateUnit/buttonCancel')
WebUI.verifyElementNotClickable(findTestObject('Object Repository/Web/Buildings/Unit/CreateUnit/buttonSave'), FailureHandling.STOP_ON_FAILURE)

//Verify placeholder
WebUI.verifyMatch(WebUI.getAttribute(findTestObject('Object Repository/Web/Buildings/Unit/CreateUnit/textboxArea'), 'placeholder'), placeholderArea, false)
WebUI.verifyMatch(WebUI.getAttribute(findTestObject('Object Repository/Web/Buildings/Unit/CreateUnit/textboxNote'), 'placeholder'), placeholderNote, false)

//Verify message
WebUI.click(findTestObject('Object Repository/Web/Buildings/Unit/CreateUnit/selectBuildingId'))
WebUI.click(findTestObject('Object Repository/Web/Buildings/Unit/CreateUnit/titleInfoUnit'))
WebUI.click(findTestObject('Object Repository/Web/Buildings/Unit/CreateUnit/textboxArea'))
WebUI.click(findTestObject('Object Repository/Web/Buildings/Unit/CreateUnit/titleInfoUnit'))

AbstractWeb.verifyText('Object Repository/Web/Buildings/Unit/CreateUnit/msgBuildingIsRequired', msgBuildingIsRequired)
AbstractWeb.verifyText('Object Repository/Web/Buildings/Unit/CreateUnit/msgAreaNotBlank', msgAreaNotBlank)

WebUI.verifyElementVisible(findTestObject('Object Repository/Web/Buildings/Unit/CreateUnit/statusUnit'), FailureHandling.STOP_ON_FAILURE)