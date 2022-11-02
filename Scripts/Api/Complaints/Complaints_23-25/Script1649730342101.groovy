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
import com.eviware.soapui.config.TestOnDemandUploadRequestDocument.TestOnDemandUploadRequest.Request
import com.testData.*
import java.sql.*
import org.json.*

KeywordUtil.logInfo(TC_ID)

Authenticate.setToken([('username') : 'admin', ('password') : '123456aA@'])

Map maps

String complaintIdsClassify1

WS.callTestCase(findTestCase('Test Cases/Api/Complaints/Complaints_1-13'), [('complaintTemplateId') : complaintTemplateId
        , ('content') : content, ('fullName') : fullName, ('phoneNumber') : phoneNumber, ('invalidReason') : invalidReason
        , ('statusCode') : statusCodeClassify, ('message') : message, ('success') : success, ('error') : error])

if (statusCodeClassify == '200') {
    ResultSet rs = Statemnet.getComplaintInfor('demo abc')

    while (rs.next()) {
        complaintIdsClassify1 = rs.getString('ID')

        println('Trong while' + complaintIdsClassify1)
    }
}

ConnectionDatabase.closeDatabaseConnection()

println('Ngoài while' + complaintIdsClassify1)

maps = [('complaintIdsClassify1') : complaintIdsClassify1, ('invalidReason') : invalidReason]

ResponseObject response1 = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Complaints/Classify complaint', 
        maps), FailureHandling.STOP_ON_FAILURE)

println(response1.getResponseText())

if (complaintIdsClassify == 'data') {
    WS.verifyEqual(response1.statusCode.toString(), statusCodeClassify,  FailureHandling.STOP_ON_FAILURE)

    ResultSet rs1 = Statemnet.getComplaintType(complaintIdsClassify1)

    while (rs1.next()) {
        WS.verifyEqual(rs1.getString('COMPLAINT_TYPE'), typeComplaint,  FailureHandling.STOP_ON_FAILURE)
    }
    
    ConnectionDatabase.closeDatabaseConnection()
} else {
    WS.verifyEqual(response1.statusCode.toString(), statusCodeClassify,  FailureHandling.STOP_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response1, 'message').toString(), message,  FailureHandling.CONTINUE_ON_FAILURE)
}