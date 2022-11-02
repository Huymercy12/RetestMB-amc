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

import groovy.json.JsonSlurper

import java.sql.*
import org.json.*

String buildingId

Map map = DataExcelUtils.getDataByIndex(1, 'Data Files/Api/Building/Create Building')

WS.callTestCase(findTestCase('Test Cases/Api/Building/Building_01-14'), map, FailureHandling.CONTINUE_ON_FAILURE)

ResultSet rs1 = Statemnet.getBuilding('trungtam')

while (rs1.next()) {
    buildingId = rs1.getString('ID')

    Map maps = [('buildingId') : buildingId, ('nameFloor') : nameFloor, ('totalArea') : totalArea, ('floorNumber') : floorNumber
        , ('statusFloor') : statusFloor]
	
    ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Building/Create Floor', maps), 
        FailureHandling.STOP_ON_FAILURE)
	JsonSlurper slurper = new JsonSlurper()
	Map map2 = slurper.parseText(response.getResponseText())
    println(map2.toString())

    WS.verifyEqual(response.statusCode.toString(), statusCode, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'success').toString(), success, FailureHandling.CONTINUE_ON_FAILURE)
}

ConnectionDatabase.closeDatabaseConnection()

ResultSet rs2 = Statemnet.getFloor(buildingId)

while (rs2.next()) {
    if (statusCode == '200') {
        WS.verifyEqual(rs2.getString('BUILDING_ID'), buildingId, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs2.getString('NAME'), nameFloor, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs2.getString('FLOOR_NUMBER'), floorNumber, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs2.getString('STATUS'), statusFloor, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs2.getString('CREATED_BY'), username, FailureHandling.CONTINUE_ON_FAILURE)
    }
}

ConnectionDatabase.closeDatabaseConnection()