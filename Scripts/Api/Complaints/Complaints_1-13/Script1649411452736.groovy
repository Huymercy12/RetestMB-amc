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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
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

Map maps=[('complaintTemplateId'):complaintTemplateId,('content'):content,('fullName'):fullName,('phoneNumber'):phoneNumber]

ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Complaints/Create complaint', maps), FailureHandling.STOP_ON_FAILURE)
println(response.getResponseText())
WS.verifyEqual(response.statusCode.toString(), statusCode,  FailureHandling.STOP_ON_FAILURE)

if (statusCode == '200') {
	// request vs response

	WS.verifyEqual(WS.getElementPropertyValue(response, 'data.complaintTemplateId').toString(), complaintTemplateId, 
		FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyEqual(WS.getElementPropertyValue(response, 'data.content').toString(), content,  FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyEqual(WS.getElementPropertyValue(response, 'data.fullName').toString(), fullName,  FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyEqual(WS.getElementPropertyValue(response, 'data.phoneNumber').toString(), phoneNumber,  FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyEqual(WS.getElementPropertyValue(response, 'success').toString(), success,  FailureHandling.CONTINUE_ON_FAILURE)

	ResultSet rs = Statemnet.getComplaintInfor(fullName)

	while (rs.next()) {
		WS.verifyEqual(rs.getString('COMPLAINT_TEMPLATE_ID'),complaintTemplateId,  FailureHandling.CONTINUE_ON_FAILURE)

		WS.verifyEqual(rs.getString('FULL_NAME'), fullName,  FailureHandling.CONTINUE_ON_FAILURE)

		WS.verifyEqual(rs.getString('CONTENT'), content,  FailureHandling.CONTINUE_ON_FAILURE)

		WS.verifyEqual(rs.getString('PHONE_NUMBER'), phoneNumber,  FailureHandling.CONTINUE_ON_FAILURE)
		
	}
	ConnectionDatabase.closeDatabaseConnection()

} else if (statusCode == '400') {
	WS.verifyEqual(WS.getElementPropertyValue(response, 'message').toString(), message,  FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyEqual(WS.getElementPropertyValue(response, 'error').toString(), error,  FailureHandling.CONTINUE_ON_FAILURE)
}