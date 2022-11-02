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

Map map = DataExcelUtils.getDataByIndex(1, 'Data Files/Api/QRCode/Create QR Code')

WS.callTestCase(findTestCase('Test Cases/Api/QRCode/QRCode_1-16'), map, FailureHandling.STOP_ON_FAILURE)

Authenticate.setToken([('username') : username, ('password') : password])

String titleRandom

String noteRandom

String idQR

String idQRCreated

if (title == 'random') {
    titleRandom = FakerData.randomFullName()
} else if (title == 'random200') {
    titleRandom = FakerData.randomStringQR(200)
} else {
    titleRandom = title
}

if (note == 'random1000') {
    noteRandom = FakerData.randomStringQR(1000)
} else {
    noteRandom = note
}

ResultSet rs = Statemnet.getQR('admin')

while (rs.next()) {
    idQRCreated = rs.getString('ID')
}

Map maps = [('buildingId') : buildingId, ('floorId') : floorId, ('note') : noteRandom, ('status') : status, ('title') : titleRandom
    , ('reason') : reason, ('idQRCreated') : idQRCreated]

ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/QRCode/Update QR Code', maps), FailureHandling.STOP_ON_FAILURE)

println(response.getResponseText())

WS.verifyEqual(response.statusCode.toString(), statusCode, FailureHandling.CONTINUE_ON_FAILURE)

if (statusCode == '200') {
    idQR = WS.getElementPropertyValue(response, 'data.id').toString()

    ResultSet rs1 = Statemnet.getQRById(idQR)

    while (rs1.next()) {
        WS.verifyEqual(WS.getElementPropertyValue(response, 'data.title').toString(), rs1.getString('TITLE').toString(), 
            FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(WS.getElementPropertyValue(response, 'data.status').toString(), rs1.getString('STATUS').toString(), 
            FailureHandling.CONTINUE_ON_FAILURE)
    }
    
    ConnectionDatabase.closeDatabaseConnection()
} else if ((message == 'Bạn không có quyền truy cập') || (message == 'Tên mã QR đã tồn tại')) {
    WS.verifyEqual(response.statusCode.toString(), statusCode, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'message').toString(), message, FailureHandling.CONTINUE_ON_FAILURE)
} else {
    WS.verifyEqual(response.statusCode.toString(), statusCode, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'errors.message').toString(), message, FailureHandling.CONTINUE_ON_FAILURE)
}