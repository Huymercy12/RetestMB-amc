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

KeywordUtil.logInfo(TC_ID)
Authenticate.setToken(GlobalVariable.ACCOUNT_ADMIN)
String nameRandom

String totalFloorRandom

String addressRandom

String noteRandom

String commissioningDateRandom

String areaRandom

if (name == 'random') {
    nameRandom = FakerData.randomBuildingName()
} else {
    nameRandom = name
}

if (address == 'random') {
    addressRandom = FakerData.randomUsername()
} else {
    addressRandom = address
}

if (note == 'random') {
    noteRandom = FakerData.randomUsername()
} else {
    noteRandom = note
}

if (commissioningDate == 'random') {
    commissioningDateRandom = FakerData.randomDate('yyyy-MM-dd')
} else {
    commissioningDateRandom = commissioningDate
}

Map maps = [('id') : ID, ('buildingClassId') : buildingId, ('name') : nameRandom, ('customerTypes') : customerTypes, ('date') : commissioningDateRandom
    , ('totalArea') : totalArea, ('totalFloor') : totalFloor, ('totalBasement') : totalBasement, ('regionCode') : regionCode
    , ('provinceCode') : provinceCode, ('districtCode') : districtCode, ('address') : addressRandom, ('note') : noteRandom]

ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Building/Update Building', maps), 
    FailureHandling.STOP_ON_FAILURE)

println(response.getResponseText())

WS.verifyEqual(response.statusCode.toString(), statusCode, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyEqual(WS.getElementPropertyValue(response, 'success').toString(), success, FailureHandling.CONTINUE_ON_FAILURE)

if (statusCode == '200') {
    // request vs response
    //	WS.verifyEqual(WS.getElementPropertyValue(response, 'data.lastModifiedBy').toString(), username,  FailureHandling.CONTINUE_ON_FAILURE)
    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.name').toString(), nameRandom, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.totalFloor').toString(), totalFloor, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.address').toString(), addressRandom, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.status').toString(), statusBuilding, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.note').toString(), noteRandom, FailureHandling.CONTINUE_ON_FAILURE)

    ResultSet rs = Statemnet.getBuildingById(ID)

    while (rs.next()) {
        //		WS.verifyEqual(rs.getString('LAST_MODIFIED_BY'), username,  FailureHandling.CONTINUE_ON_FAILURE)
        WS.verifyEqual(rs.getString('ADDRESS'), addressRandom, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('NAME'), nameRandom, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('TOTAL_FLOOR'), totalFloor, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('STATUS'), statusBuilding, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('NOTE'), noteRandom, FailureHandling.CONTINUE_ON_FAILURE)
    }
    
    ConnectionDatabase.closeDatabaseConnection()
} else {
    WS.verifyEqual(WS.getElementPropertyValue(response, 'message').toString(), message, FailureHandling.CONTINUE_ON_FAILURE)
}