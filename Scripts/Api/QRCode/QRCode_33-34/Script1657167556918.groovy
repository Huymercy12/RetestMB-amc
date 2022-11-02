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
import java.beans.Statement as Statement
import java.sql.*
import org.json.*

Authenticate.setToken([('username') : username, ('password') : password])

String idQRCreated

String status

//ResultSet rs = Statemnet.getQR()
ResultSet rs = Statemnet.getQR('admin' // Tại vì có 1 số trường hợp cụ thể mắc lỗi "không thể thao tác mẫu này"
    )

while (rs.next()) {
    idQRCreated = rs.getString('ID')

    println(idQRCreated)

    status = rs.getString('STATUS')

    println(status)
}

Map maps = [('idQRCode') : idQRCreated, ('reason') : reason]

if (status.equals('ACTIVE')) {
    ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/QRCode/Inactive QR Code', maps), 
        FailureHandling.STOP_ON_FAILURE)

    println(response.getResponseText())

    WS.verifyEqual(response.statusCode.toString(), statusCode, FailureHandling.CONTINUE_ON_FAILURE)

    ResultSet rs1 = Statemnet.getQRById(idQRCreated)

    println(rs1.toString())

    while (rs1.next()) {
        WS.verifyNotEqual(status, rs1.getString('STATUS').toString(), FailureHandling.CONTINUE_ON_FAILURE)
    }
    
    ConnectionDatabase.closeDatabaseConnection()
} else if (status.equals('INACTIVE')) {
    ResponseObject response1 = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/QRCode/Active QR Code', maps), 
        FailureHandling.STOP_ON_FAILURE)

    println(response1.getResponseText())

    WS.verifyEqual(response1.statusCode.toString(), statusCode, FailureHandling.CONTINUE_ON_FAILURE)

    ResultSet rs2 = Statemnet.getQRById(idQRCreated)

    while (rs2.next()) {
        WS.verifyNotEqual(status, rs2.getString('STATUS').toString(), FailureHandling.CONTINUE_ON_FAILURE)
    }
    
    ConnectionDatabase.closeDatabaseConnection()
}