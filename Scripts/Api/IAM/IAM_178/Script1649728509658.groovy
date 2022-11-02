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
import com.kms.katalon.core.testobject.TestObject as TestObject
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

WS.callTestCase(findTestCase('Test Cases/Api/Building/Building_15-19'), [('username') : username, ('code') : code, ('nameBuilding') : nameBuilding
        , ('totalFloor') : totalFloor, ('address') : address, ('note') : note, ('statusBuilding') : statusBuilding, ('area') : area
        , ('nameFloor') : nameFloor, ('totalArea') : totalArea, ('floorNumber') : floorNumber, ('statusFloor') : statusFloor
        , ('buildingId') : buildingId, ('floorId') : floorId, ('price') : price, ('areaUnit') : areaUnit, ('leasingStatus') : leasingStatus
        , ('noteUnit') : noteUnit, ('type') : type, ('statusCode') : '200', ('success') : 'true'])

String buildingIdCreated
String floorIdCreated
String unitIdCreated

ResultSet rs = Statemnet.getBuilding(username)
while (rs.next()) {
    buildingIdCreated = rs.getString('ID')
}
ConnectionDatabase.closeDatabaseConnection()

ResultSet rs1 = Statemnet.getFloor(buildingIdCreated)
while (rs1.next()) {
    floorIdCreated = rs1.getString('ID')
}
ConnectionDatabase.closeDatabaseConnection()

ResultSet rs2 = Statemnet.getUnit(username)
while (rs2.next()) {
    unitIdCreated = rs2.getString('ID')
}
ConnectionDatabase.closeDatabaseConnection()

Map maps = [('organizationId') : organizationId, ('unitId') : unitIdCreated, ('leasingStatusUnit') : leasingStatusUnit]

ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/IAM/Create Leasing Unit', maps), FailureHandling.STOP_ON_FAILURE)

WS.verifyMatch(response.statusCode.toString(), statusCode, false, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(WS.getElementPropertyValue(response, 'success').toString(), success, false, FailureHandling.CONTINUE_ON_FAILURE)

if (statusCode == '200') {
    WS.verifyMatch(WS.getElementPropertyValue(response, 'data.buildingId').toString(), buildingIdCreated, false, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyMatch(WS.getElementPropertyValue(response, 'data.floorId').toString(), floorIdCreated, false, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyMatch(WS.getElementPropertyValue(response, 'data.unitId').toString(), unitIdCreated, false, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyMatch(WS.getElementPropertyValue(response, 'data.organizationId').toString(), organizationId, false, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyMatch(WS.getElementPropertyValue(response, 'data.leasingStatus').toString(), leasingStatusUnit, false, FailureHandling.CONTINUE_ON_FAILURE)

    ResultSet rs3 = Statemnet.getUnit(username)

    while (rs3.next()) {
        WS.verifyMatch(rs3.getString('BUILDING_ID'), buildingIdCreated, false, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyMatch(rs3.getString('FLOOR_ID'), floorIdCreated, false, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyMatch(rs3.getString('LEASING_STATUS'), leasingStatusUnit, false, FailureHandling.CONTINUE_ON_FAILURE)
    }
    ConnectionDatabase.closeDatabaseConnection()
}