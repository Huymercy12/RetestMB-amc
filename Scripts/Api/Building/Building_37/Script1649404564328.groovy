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
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
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

String buildingId

String floorId

WS.callTestCase(findTestCase('Test Cases/Api/Building/Building_36'), [('username') : username, ('code') : code, ('nameBuilding') : nameBuilding
        , ('totalFloor') : totalFloor, ('address') : address, ('note') : note, ('statusBuilding') : statusBuilding, ('area') : area
        , ('statusCode') : '200', ('success') : 'true', ('nameFloor') : nameFloor, ('totalArea') : totalArea, ('floorNumber') : floorNumber
        , ('statusFloor') : statusFloor])

ResultSet rs = Statemnet.getBuilding(username)

while (rs.next()) {
    buildingId = rs.getString('ID')
}

ConnectionDatabase.closeDatabaseConnection()

ResultSet rs1 = Statemnet.getFloor(buildingId)

while (rs1.next()) {
    floorId = rs1.getString('ID')
}

ConnectionDatabase.closeDatabaseConnection()

Map maps = [('buildingId') : buildingId, ('floorId') : floorId]

RequestObject request = new RequestObject(findTestObject('Object Repository/Api/Building/Delete Floor In Building').getObjectId())

KeywordUtil.logInfo(request.toString())

ResponseObject response = WS.sendRequestAndVerify(request, maps, FailureHandling.CONTINUE_ON_FAILURE)

//ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Building/Delete Floor In Building', maps), FailureHandling.STOP_ON_FAILURE)
KeywordUtil.logInfo(response.getResponseText())

WS.verifyEqual(response.statusCode.toString(), statusCode, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyEqual(WS.getElementPropertyValue(response, 'success').toString(), success, FailureHandling.CONTINUE_ON_FAILURE)

if (statusCode == '200') {
    ResultSet rs2 = Statemnet.getFloor(buildingId)

    while (rs2.next()) {
        WS.verifyEqual(rs2.getString('DELETED'), Integer.parseInt('1').toString(), FailureHandling.CONTINUE_ON_FAILURE)
    }
    
    ConnectionDatabase.closeDatabaseConnection()
}