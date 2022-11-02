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
import ch.qos.logback.core.joran.conditional.ElseAction as ElseAction
import java.sql.*
import org.json.*

KeywordUtil.logInfo(TC_ID)

Authenticate.setToken([('username') : username, ('password') : password])

println(username)

println(password)

String titlerandom

String noteRandom

if (title == 'random') {
    titlerandom = FakerData.randomFullName()
} else if (title == 'random200') {
    titlerandom = FakerData.randomStringQR(200)
} else {
    titlerandom = title
}

if (note == 'random1000') {
    noteRandom = FakerData.randomStringQR(1000)
} else {
    noteRandom = note
}

println(titlerandom)

Map maps = [('title') : titlerandom, ('status') : status, ('floorId') : floorId, ('buildingId') : buildingId, ('note') : noteRandom]

ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/QRCode/Create QR Code', maps), FailureHandling.STOP_ON_FAILURE)

println(response.getResponseText())

WS.verifyEqual(response.statusCode.toString(), statusCode, FailureHandling.CONTINUE_ON_FAILURE)

if (statusCode == '200') {
    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.buildingId').toString(), buildingId, FailureHandling.STOP_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.floorId', FailureHandling.STOP_ON_FAILURE).toString(), floorId, 
        FailureHandling.STOP_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.status').toString(), status, FailureHandling.STOP_ON_FAILURE)

    ResultSet rs1 = Statemnet.getQR('admin')

    while (rs1.next()) {
        WS.verifyEqual(WS.getElementPropertyValue(response, 'data.id').toString(), rs1.getString('ID').toString(), FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(WS.getElementPropertyValue(response, 'data.buildingId').toString(), rs1.getString('BUILDING_ID').toString(), 
            FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(WS.getElementPropertyValue(response, 'data.floorId').toString(), rs1.getString('FLOOR_ID').toString(), 
            FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(WS.getElementPropertyValue(response, 'data.status').toString(), rs1.getString('STATUS').toString(), 
            FailureHandling.CONTINUE_ON_FAILURE)
    }
    
    ConnectionDatabase.closeDatabaseConnection()
} else {
    if (TC_ID == '2') {
        WS.verifyEqual(response.statusCode.toString(), statusCode, FailureHandling.CONTINUE_ON_FAILURE)
    } else {
        WS.verifyEqual(response.statusCode.toString(), statusCode, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(WS.getElementPropertyValue(response, 'message').toString(), message.toString(), FailureHandling.CONTINUE_ON_FAILURE)
    }
}

GlobalVariable.accessToken = ''