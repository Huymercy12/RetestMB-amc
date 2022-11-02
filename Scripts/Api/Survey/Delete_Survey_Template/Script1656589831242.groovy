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
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import org.openqa.selenium.Keys as Keys
import com.api.*
import com.database.*
import com.testData.*
import groovy.json.JsonSlurper as JsonSlurper
import java.sql.*
import javax.xml.ws.Response as Response
import org.json.*
String rs = Statemnet.getTotalRecorded('EVO_SURVEY.SURVEY_TEMPLATE')

int totalRecord1 = Integer.parseInt(rs)

ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Survey/Delete Survey Template', 
        [('id') : id]), FailureHandling.STOP_ON_FAILURE)

if (id.toString().trim().isEmpty()) {
	
WS.verifyMatch(response.statusCode.toString(), '500', false, FailureHandling.STOP_ON_FAILURE)
WS.verifyMatch(WS.getElementPropertyValue(response, 'error'), error, false, FailureHandling.STOP_ON_FAILURE)

} else {
	WS.verifyMatch(response.statusCode.toString(), '200', false, FailureHandling.STOP_ON_FAILURE)
	String rs2 = Statemnet.getTotalRecorded('EVO_SURVEY.SURVEY_TEMPLATE')
	
			int totalRecord2 = Integer.parseInt(rs2)
	
			WS.verifyEqual(totalRecord1 -1 , totalRecord2, FailureHandling.STOP_ON_FAILURE)
	
}
