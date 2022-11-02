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

ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Survey/Get SurveyTemplate by Id', 
        [('id') : id]), FailureHandling.STOP_ON_FAILURE)

//if (response.statusCode.toString() == '401') {
//    WS.callTestCase(findTestCase('Test Cases/Api/Survey/login'), [('username') : 'admin', ('password') : '123456aA@', ('statusCode') : '200'
//            , ('success') : 'true'], FailureHandling.STOP_ON_FAILURE)
//}
//
//response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Survey/Get SurveyTemplate by Id', [('id') : id]), 
//    FailureHandling.STOP_ON_FAILURE)

WS.verifyMatch(response.statusCode.toString(), '200', false, FailureHandling.STOP_ON_FAILURE)

if (id.toString().trim().isEmpty()) {
	
	String rs = Statemnet.getTotalRecorded('EVO_SURVEY.SURVEY_TEMPLATE')
	WS.verifyMatch(WS.getElementPropertyValue(response, 'page.total').toString(), rs, false, FailureHandling.STOP_ON_FAILURE)
	
	
} else {
	ResultSet rs = Statemnet.getSurveyTemplateByID(id)
	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.id').toString(), id, false, FailureHandling.STOP_ON_FAILURE)
	WS.verifyMatch(WS.getElementPropertyValue(response, 'success').toString(),success, false, FailureHandling.STOP_ON_FAILURE)
	
}