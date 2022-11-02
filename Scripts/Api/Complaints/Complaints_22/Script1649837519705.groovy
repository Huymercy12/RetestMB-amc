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

if(complaintIdsClassify == 'data1') {
WS.callTestCase(findTestCase('Test Cases/Api/Complaints/Complaints_1-13'), [('complaintFileIds') : complaintFileIds, ('complaintTemplateId') : complaintTemplateId
	, ('content') : content, ('fullName') : fullName, ('phoneNumber') : phoneNumber,('statusCode'):'200',('success'):'true'])

ResultSet rs = Statemnet.getListComplaint(fullName)
String complaintIdsClassify1
while (rs.next()) {
	  complaintIdsClassify1 = rs.getString('ID')
}
ConnectionDatabase.closeDatabaseConnection()
println complaintIdsClassify1

Map maps = [('complaintIdsClassify') : complaintIdsClassify1,
			('feedback') : feedback]
ResponseObject response1 = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Complaints/Update complaint', maps), FailureHandling.STOP_ON_FAILURE)

WS.verifyMatch(response1.statusCode.toString(), statusCode , false, FailureHandling.STOP_ON_FAILURE)
WS.verifyMatch(WS.getElementPropertyValue(response1, 'message').toString(), message, false, FailureHandling.CONTINUE_ON_FAILURE)

}