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
import com.testData.FakerData
import com.web.AbstractWeb
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

String floor = FakerData.randomFloor(200)
WebUI.callTestCase(findTestCase('Test Cases/Web/Buildings/Building/CreateBuilding'), ['buildingCode' : buildingCode, 'buildingName' : buildingName, 'floor' : floor, 'area' : area, 'address' : address, 'statusBuilding' : statusBuilding, 'note' : note, 'message' : message])

//Verify title
AbstractWeb.verifyText('Object Repository/Web/Buildings/titlePage', titlePage)
AbstractWeb.verifyText('Object Repository/Web/Buildings/CreateFloor/titleInfoBuilding', titleInfoBuilding)

//Verify table header
AbstractWeb.verifyArrayText('Object Repository/Web/Buildings/headerTable', numberFloor, floorName, totalArea, rentedArea, availableArea, action)

AbstractWeb.clickButton('Object Repository/Web/Buildings/buttonCreate')

//Verify popup
AbstractWeb.verifyText('Object Repository/Web/Buildings/titlePopup', titlePopupCreateFloor)

//Verify message
WebUI.click(findTestObject('Object Repository/Web/Buildings/CreateFloor/textboxNumberFloor'), FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/Web/Buildings/CreateFloor/textboxFloorName'), FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/Web/Buildings/CreateFloor/textboxTotalArea'), FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/Web/Buildings/CreateFloor/titleTextbox'), FailureHandling.STOP_ON_FAILURE)

AbstractWeb.verifyText('Object Repository/Web/Buildings/CreateFloor/msgNumberFloorNotBlank', msgNumberFloorNotBlank)
AbstractWeb.verifyText('Object Repository/Web/Buildings/CreateFloor/msgFloorNameNotBlank', msgFloorNameNotBlank)
AbstractWeb.verifyText('Object Repository/Web/Buildings/CreateFloor/msgTotalAreaNotBlank', msgTotalAreaNotBlank)

WebUI.verifyEqual(AbstractWeb.getInforOfBuilding(AbstractWeb.getText('Object Repository/Web/Buildings/CreateFloor/titleNumberFloor'), 'total'), floor, FailureHandling.STOP_ON_FAILURE)
AbstractWeb.sendText('Object Repository/Web/Buildings/CreateFloor/textboxNumberFloor', numberFloorInput)
WebUI.verifyEqual(AbstractWeb.getAttribute('Object Repository/Web/Buildings/CreateFloor/textboxNumberFloor', 'value'), numberFloorInput, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyEqual(AbstractWeb.getInforOfBuilding(AbstractWeb.getText('Object Repository/Web/Buildings/CreateFloor/titleNumberFloor'), 'input'), numberFloorInput, FailureHandling.STOP_ON_FAILURE)
AbstractWeb.verifyText('Object Repository/Web/Buildings/CreateFloor/msgMaxNumberFloor', msgMaxNumberFloor) 

WebUI.verifyEqual(AbstractWeb.getInforOfBuilding(AbstractWeb.getText('Object Repository/Web/Buildings/CreateFloor/titleTotalArea'), 'total'), area, FailureHandling.STOP_ON_FAILURE)
AbstractWeb.sendText('Object Repository/Web/Buildings/CreateFloor/textboxTotalArea', totalAreaInput)
WebUI.verifyEqual(AbstractWeb.getAttribute('Object Repository/Web/Buildings/CreateFloor/textboxTotalArea', 'value'), totalAreaInput, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyEqual(AbstractWeb.getInforOfBuilding(AbstractWeb.getText('Object Repository/Web/Buildings/CreateFloor/titleTotalArea'), 'input'), totalAreaInput, FailureHandling.STOP_ON_FAILURE)
AbstractWeb.verifyText('Object Repository/Web/Buildings/CreateFloor/msgMaxFloorArea', msgMaxFloorArea)

//Verify button
AbstractWeb.waitAndVerifyElement('Object Repository/Web/Buildings/CreateFloor/btnCancelCreateFloor')
WebUI.verifyElementNotClickable(findTestObject('Object Repository/Web/Buildings/CreateFloor/buttonCreateFloor'), FailureHandling.STOP_ON_FAILURE)
AbstractWeb.clickButton('Object Repository/Web/Buildings/CreateFloor/btnCancelCreateFloor')