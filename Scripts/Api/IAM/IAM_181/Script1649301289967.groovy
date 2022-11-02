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

//Change password
Authenticate.setToken([('username'):username,('password'):oldPassword])
String newPassword = FakerData.randomPassword()
ResponseObject response =WS.sendRequestAndVerify(findTestObject('Object Repository/Api/IAM/Change password', [('oldPassword'):oldPassword,('newPassword'):newPassword,('refreshToken'):GlobalVariable.refreshToken]), FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyEqual(response.statusCode.toString(), statusCode, FailureHandling.CONTINUE_ON_FAILURE)
WS.verifyEqual(WS.getElementPropertyValue(response, 'success').toString(), success, FailureHandling.CONTINUE_ON_FAILURE)

//Log in with new password
ResponseObject response2=WS.sendRequestAndVerify(findTestObject('Object Repository/Api/IAM/Authenticate',[('username'):username,('password'):newPassword]), FailureHandling.CONTINUE_ON_FAILURE)
WS.verifyEqual(response2.statusCode.toString(), statusCode, FailureHandling.CONTINUE_ON_FAILURE)

//Reset the old password
Authenticate.setToken([('username'):username,('password'):newPassword])
ResponseObject response3 =WS.sendRequestAndVerify(findTestObject('Object Repository/Api/IAM/Change password', [('oldPassword'):newPassword,('newPassword'):oldPassword,('refreshToken'):GlobalVariable.refreshToken]), FailureHandling.CONTINUE_ON_FAILURE)
WS.verifyEqual(response.statusCode.toString(), statusCode, FailureHandling.CONTINUE_ON_FAILURE)