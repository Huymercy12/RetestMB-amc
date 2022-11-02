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

String unitId

WS.callTestCase(findTestCase('Test Cases/Api/Building/Building_15-19'), [('username') : username, ('code') : code, ('nameBuilding') : nameBuilding
        , ('totalFloor') : totalFloor, ('address') : address, ('note') : note, ('statusBuilding') : statusBuilding, ('area') : area
        , ('statusCode') : '200', ('success') : 'true', ('nameFloor') : nameFloor, ('totalArea') : totalArea, ('floorNumber') : floorNumber
        , ('statusFloor') : statusFloor, ('buildingId') : buildingId, ('floorId') : floorId, ('price') : price, ('areaUnit') : areaUnit
        , ('leasingStatus') : leasingStatus, ('noteUnit') : noteUnit, ('type') : type])

ResultSet rs = Statemnet.getUnit(username)

while (rs.next()) {
    unitId = rs.getString('ID')
}

ConnectionDatabase.closeDatabaseConnection()

Map maps = [('unitId') : unitId]

ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Building/Delete Unit', maps), 
    FailureHandling.STOP_ON_FAILURE)

WS.verifyMatch(response.statusCode.toString(), statusCode, false, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(WS.getElementPropertyValue(response, 'success').toString(), success, false, FailureHandling.CONTINUE_ON_FAILURE)

if (statusCode == '200') {
    ResultSet rs2 = Statemnet.getUnit(username)

    while (rs2.next()) {
        WS.verifyMatch(rs2.getString('DELETED'), Integer.parseInt('1').toString(), false, FailureHandling.CONTINUE_ON_FAILURE)
    }
    
    ConnectionDatabase.closeDatabaseConnection()
}