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
import com.web.AbstractWeb
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

AbstractWeb.clickButton('Object Repository/Web/Buildings/buttonCreate')

//Verify title
AbstractWeb.verifyText('Object Repository/Web/Buildings/titlePage', titlePage)
AbstractWeb.verifyText('Object Repository/Web/Buildings/CreateBuilding/titleInfoBuilding', titleInfoBuilding)
AbstractWeb.verifyArrayText('Object Repository/Web/Buildings/CreateBuilding/titleTextbox', titleBuildingCode, titleBuildingName, titleFloor, titleArea, titleAddress, titleStatusBuilding, titleNote)

//Verify button
AbstractWeb.waitAndVerifyElement('Object Repository/Web/Buildings/CreateBuilding/buttonCancel')
WebUI.verifyElementNotClickable(findTestObject('Object Repository/Web/Buildings/CreateBuilding/buttonCreate'), FailureHandling.STOP_ON_FAILURE)

//Verify placeholder
WebUI.verifyMatch(WebUI.getAttribute(findTestObject('Object Repository/Web/Buildings/CreateBuilding/textboxBuildingCode'), 'placeholder'), placeholderBuildingCode, false, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyMatch(WebUI.getAttribute(findTestObject('Object Repository/Web/Buildings/CreateBuilding/textboxBuildingName'), 'placeholder'), placeholderBuildingName, false, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyMatch(WebUI.getAttribute(findTestObject('Object Repository/Web/Buildings/CreateBuilding/textboxFloor'), 'placeholder'), placeholderFloor, false, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyMatch(WebUI.getAttribute(findTestObject('Object Repository/Web/Buildings/CreateBuilding/textboxArea'), 'placeholder'), placeholderArea, false, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyMatch(WebUI.getAttribute(findTestObject('Object Repository/Web/Buildings/CreateBuilding/textboxAddress'), 'placeholder'), placeholderAddress, false, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyMatch(WebUI.getAttribute(findTestObject('Object Repository/Web/Buildings/CreateBuilding/textboxNote'), 'placeholder'), placeholderNote, false, FailureHandling.STOP_ON_FAILURE)

//Verify message
WebUI.click(findTestObject('Object Repository/Web/Buildings/CreateBuilding/textboxBuildingCode'))
WebUI.click(findTestObject('Object Repository/Web/Buildings/CreateBuilding/textboxBuildingName'))
WebUI.click(findTestObject('Object Repository/Web/Buildings/CreateBuilding/textboxFloor'))
WebUI.click(findTestObject('Object Repository/Web/Buildings/CreateBuilding/textboxArea'))
WebUI.click(findTestObject('Object Repository/Web/Buildings/CreateBuilding/textboxAddress'))
WebUI.click(findTestObject('Object Repository/Web/Buildings/CreateBuilding/textboxNote'))

AbstractWeb.verifyText('Object Repository/Web/Buildings/CreateBuilding/msgBuildingCodeNotBlank', msgBuildingCodeNotBlank)
AbstractWeb.verifyText('Object Repository/Web/Buildings/CreateBuilding/msgBuildingNameNotBlank', msgBuildingNameNotBlank)
AbstractWeb.verifyText('Object Repository/Web/Buildings/CreateBuilding/msgFloorNotBlank', msgFloorNotBlank)
AbstractWeb.verifyText('Object Repository/Web/Buildings/CreateBuilding/msgAreaNotBlank', msgAreaNotBlank)
AbstractWeb.verifyText('Object Repository/Web/Buildings/CreateBuilding/msgAddressNotBlank', msgAddressNotBlank)

AbstractWeb.sendText('Object Repository/Web/Buildings/CreateBuilding/textboxBuildingCode', buildingCode)
AbstractWeb.verifyText('Object Repository/Web/Buildings/CreateBuilding/msgBuildingCodeInvalid', msgBuildingCodeInvalid)

AbstractWeb.sendText('Object Repository/Web/Buildings/CreateBuilding/textboxArea', area)
AbstractWeb.verifyText('Object Repository/Web/Buildings/CreateBuilding/msgMaxArea', msgMaxArea)