import com.kms.katalon.core.model.FailureHandling as FailureHandling
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.entity.global.GlobalVariableEntity as GlobalVariableEntity
import internal.GlobalVariable as GlobalVariable
import static org.junit.Assert.assertTrue
import org.openqa.selenium.Keys as Keys
import com.api.*
import com.database.*
import com.testData.*
import java.sql.*
import org.json.*

KeywordUtil.logInfo(TC_ID)

String buildingIdFromDb
String floorIdFromDb

if ((buildingId == 'data') && (floorId == 'data')) {
    WS.callTestCase(findTestCase('Test Cases/Api/Building/Building_36'), [('username') : username, ('code') : code, ('nameBuilding') : nameBuilding
            , ('totalFloor') : totalFloor, ('address') : address, ('note') : note, ('statusBuilding') : statusBuilding, ('area') : area
            , ('statusCode') : '200', ('success') : 'true', ('nameFloor') : nameFloor, ('totalArea') : totalArea, ('floorNumber') : floorNumber
            , ('statusFloor') : statusFloor])
	
    ResultSet rs3 = Statemnet.getBuilding(username)
    while (rs3.next()) {
        buildingIdFromDb = rs3.getString('ID')
    }
    ConnectionDatabase.closeDatabaseConnection()
	
    ResultSet rs4 = Statemnet.getFloor(buildingIdFromDb)
    while (rs4.next()) {
        floorIdFromDb = rs4.getString('ID')
    }
    ConnectionDatabase.closeDatabaseConnection()
	
	Map maps = [('buildingId') : buildingIdFromDb, ('floorId') : floorIdFromDb, ('price') : price, ('areaUnit') : areaUnit, ('leasingStatus') : leasingStatus
		, ('noteUnit') : noteUnit, ('type') : type]

    ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Building/Create Unit', maps), FailureHandling.STOP_ON_FAILURE)
	
    WS.verifyMatch(response.statusCode.toString(), statusCode, false, FailureHandling.CONTINUE_ON_FAILURE)
	
	VariableCollections.map.put("response", response)

    WS.verifyMatch(WS.getElementPropertyValue(response, 'success').toString(), success, false, FailureHandling.CONTINUE_ON_FAILURE)
	
	if (statusCode == '200') {
		// request vs response
		WS.verifyMatch(WS.getElementPropertyValue(response, 'data.buildingId').toString(), buildingIdFromDb, false, FailureHandling.CONTINUE_ON_FAILURE)
	
		WS.verifyMatch(WS.getElementPropertyValue(response, 'data.floorId').toString(), floorIdFromDb, false, FailureHandling.CONTINUE_ON_FAILURE)
	
		WS.verifyMatch(WS.getElementPropertyValue(response, 'data.price').toString(), price, false, FailureHandling.CONTINUE_ON_FAILURE)
	
		WS.verifyMatch(WS.getElementPropertyValue(response, 'data.leasingStatus').toString(), leasingStatus, false, FailureHandling.CONTINUE_ON_FAILURE)
	
		WS.verifyMatch(WS.getElementPropertyValue(response, 'data.type').toString(), type, false, FailureHandling.CONTINUE_ON_FAILURE)
	
		WS.verifyMatch(WS.getElementPropertyValue(response, 'data.note').toString(), noteUnit, false, FailureHandling.CONTINUE_ON_FAILURE)
	
		WS.verifyMatch(WS.getElementPropertyValue(response, 'data.area').toString(), Double.parseDouble(areaUnit).toString(),
			false, FailureHandling.CONTINUE_ON_FAILURE)
	
		ResultSet rs5 = Statemnet.getUnit(username)
	
		while (rs5.next()) {
			WS.verifyMatch(rs5.getString('BUILDING_ID'), buildingIdFromDb, false, FailureHandling.CONTINUE_ON_FAILURE)
	
			WS.verifyMatch(rs5.getString('FLOOR_ID'), floorIdFromDb, false, FailureHandling.CONTINUE_ON_FAILURE)
	
			WS.verifyMatch(rs5.getString('LEASING_STATUS'), leasingStatus, false, FailureHandling.CONTINUE_ON_FAILURE)
	
			WS.verifyMatch(rs5.getString('NOTE'), noteUnit, false, FailureHandling.CONTINUE_ON_FAILURE)
	
			WS.verifyMatch(rs5.getString('TYPE'), type, false, FailureHandling.CONTINUE_ON_FAILURE)
		}
		ConnectionDatabase.closeDatabaseConnection()
	}
} else {
	buildingIdFromDb = buildingId
	floorIdFromDb = floorId
	
	Map maps = [('buildingId') : buildingId, ('floorId') : floorId, ('price') : price, ('areaUnit') : areaUnit, ('leasingStatus') : leasingStatus
		, ('noteUnit') : noteUnit, ('type') : type]

	ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Building/Create Unit', maps), FailureHandling.STOP_ON_FAILURE)
	
	WS.verifyMatch(response.statusCode.toString(), statusCode, false, FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.verifyMatch(WS.getElementPropertyValue(response, 'success').toString(), success, false, FailureHandling.CONTINUE_ON_FAILURE)
	
	if (statusCode == '400') {
		WS.verifyMatch(WS.getElementPropertyValue(response, 'message').toString(), message, false, FailureHandling.CONTINUE_ON_FAILURE)
	}  
}