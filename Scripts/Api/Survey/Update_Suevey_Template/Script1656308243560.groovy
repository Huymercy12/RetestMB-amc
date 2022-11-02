import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
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

Map map = ['idSurvey' : idSurvey,'name' : name, 'otherOpinion' : otherOpinion,'id' : id,'label' : label, 'name_q' : name_q, 'content' : content , 'id_q' : id_q, 'label_q' : label_q,'type' : type,'rangeAnswer' : rangeAnswer]


String rs = Statemnet.getTotalRecorded('EVO_SURVEY.SURVEY_TEMPLATE')

int totalRecord1 = Integer.parseInt(rs)

ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Survey/Update Survey Template', map), FailureHandling.STOP_ON_FAILURE)

if (Code == '200') {
	// request vs response
	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.id').toString(), idSurvey, false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.name').toString(), name, false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.otherOpinion').toString(), otherOpinion, false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.questionGroups[0].name').toString(), name_q, false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.questionGroups[0].label').toString(), label, false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.questionGroups[0].questions[0].content').toString(), content, false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyMatch(WS.getElementPropertyValue(response, 'data.questionGroups[0].questions[0].label').toString(), label_q, false, FailureHandling.CONTINUE_ON_FAILURE)
	// Check respon with Database
	ResultSet rs1 = Statemnet.getSurveyTemplateByID(idSurvey)
	while (rs1.next()){
		WS.verifyMatch(WS.getElementPropertyValue(response, 'data.id').toString(), rs1.getString('ID'), false, FailureHandling.CONTINUE_ON_FAILURE)
		WS.verifyMatch(WS.getElementPropertyValue(response, 'data.name').toString(), rs1.getString('NAME'), false, FailureHandling.CONTINUE_ON_FAILURE)
		WS.verifyMatch(WS.getElementPropertyValue(response, 'data.otherOpinion').toString(), rs1.getString('OTHER_OPINION'), false, FailureHandling.CONTINUE_ON_FAILURE)
	}
			String rs2 = Statemnet.getTotalRecorded('EVO_SURVEY.SURVEY_TEMPLATE')
	
			int totalRecord2 = Integer.parseInt(rs2)
	
			WS.verifyEqual(totalRecord1 , totalRecord2, FailureHandling.STOP_ON_FAILURE)
	}else if(Code == '400') {
	WS.verifyMatch(WS.getElementPropertyValue(response, 'message').toString(), message, false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyMatch(WS.getElementPropertyValue(response, 'error').toString(), error, false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyMatch(WS.getElementPropertyValue(response, 'errors[0].field').toString(), field, false, FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyMatch(WS.getElementPropertyValue(response, 'errors[0].message').toString(), messageError, false, FailureHandling.CONTINUE_ON_FAILURE)
	}
	else {
		WS.verifyMatch(WS.getElementPropertyValue(response, 'message').toString(), messagee, false, FailureHandling.CONTINUE_ON_FAILURE)}
	
ConnectionDatabase.closeDatabaseConnection()

