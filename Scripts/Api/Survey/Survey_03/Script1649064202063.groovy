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

Authenticate.setToken([('username') : username, ('password') : password])

Map maps = [('accessToken') : GlobalVariable.accessToken, ('buildingIds') : buildingIds, ('endAt') : endAt, ('floorIds') : floorIds
    , ('introduction') : introduction, ('name') : name, ('note') : note, ('notificationContent') : notificationContent, ('organizationIds') : organizationIds
    , ('startAt') : startAt, ('status') : status, ('name_survey') : name_survey, ('otherOpinion') : otherOpinion, ('surveyTemplateId') : surveyTemplateId]

ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Survey/Create Survey', maps), FailureHandling.STOP_ON_FAILURE)

WS.verifyMatch(response.statusCode.toString(), statusCode, false, FailureHandling.CONTINUE_ON_FAILURE)

ResultSet rs = Statemnet.getIDSurvey()

while (rs.next()) {
    WS.verifyMatch(WS.getElementPropertyValue(response, 'data.id').toString(), rs.getString('ID').toString(), false, FailureHandling.CONTINUE_ON_FAILURE)
}

ConnectionDatabase.closeDatabaseConnection()

idSurvey = WS.getElementPropertyValue(response, 'data.id').toString()

Map map1 = [('idSurvey') : idSurvey]

ResponseObject response1 = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Survey/Send Survey', map1), FailureHandling.STOP_ON_FAILURE)

WS.verifyMatch(response1.statusCode.toString(), statusCodeSent, false, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyMatch(WS.getElementPropertyValue(response1, 'data.status').toString(), statusSurvey, false, FailureHandling.CONTINUE_ON_FAILURE)

ResultSet rs1 = Statemnet.getStatusSurvey(idSurvey)

while (rs1.next()) {
    WS.verifyMatch(WS.getElementPropertyValue(response1, 'data.status').toString(), rs1.getString('STATUS').toString(), 
        false, FailureHandling.CONTINUE_ON_FAILURE)
}

ConnectionDatabase.closeDatabaseConnection()