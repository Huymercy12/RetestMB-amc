import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.sql.ResultSet

import com.database.ConnectionDatabase
import com.database.Statemnet
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.testData.DataExcelUtils
import com.testData.FakerData
import com.testData.VariableCollections as VariableCollections
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

Map map = DataExcelUtils.getDataByIndex(1, 'Data Files/Api/IAM/Create Customer')

WS.callTestCase(findTestCase('Test Cases/Api/IAM/IAM_129-151'), map, FailureHandling.CONTINUE_ON_FAILURE)

String id = VariableCollections.map.get('id')
String code = VariableCollections.map.get('code')
String businessCode = VariableCollections.map.get('businessCode')

String nameRandom
String identificationNumberRandom
String emailsRandom

if (name == 'random') {
	nameRandom = FakerData.randomFullName()
} else {
	nameRandom = name
}

if (invoiceIssuingEmails == 'random') {
	emailsRandom = FakerData.randomEmail()
} else {
	emailsRandom = invoiceIssuingEmails
}

if (identificationNumber == 'random') {
	identificationNumberRandom = FakerData.randomCode(10)
} else {
	identificationNumberRandom = identificationNumber
}

Map maps = [('id') : id, ('name') : nameRandom, ('invoiceIssuingEmails') : emailsRandom, ('invoiceIssuingAddress') : invoiceIssuingAddress
    , ('status') : status, ('identificationNumber') : identificationNumberRandom, ('partnerType') : partnerType, ('code') : code, 'institutionType' : institutionType, 'businessCode' : businessCode]

ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/IAM/Update Customers', maps), FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyEqual(response.statusCode.toString(), statusCode, FailureHandling.CONTINUE_ON_FAILURE)

if (statusCode == '200') {
	WS.verifyEqual(WS.getElementPropertyValue(response, 'data.id').toString(), id, FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.verifyEqual(WS.getElementPropertyValue(response, 'data.name').toString(), nameRandom, FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.verifyEqual(WS.getElementPropertyValue(response, 'data.invoiceIssuingEmails[0]').toString(), emailsRandom, FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.verifyEqual(WS.getElementPropertyValue(response, 'data.invoiceIssuingAddress').toString(), invoiceIssuingAddress, FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.verifyEqual(WS.getElementPropertyValue(response, 'data.status').toString(), status, FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.verifyEqual(WS.getElementPropertyValue(response, 'data.identificationNumber').toString(), identificationNumberRandom, FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.verifyEqual(WS.getElementPropertyValue(response, 'data.partnerType').toString(), partnerType, FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.verifyEqual(WS.getElementPropertyValue(response, 'data.code').toString(), code, FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.verifyEqual(WS.getElementPropertyValue(response, 'data.institutionType').toString(), institutionType, FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.verifyEqual(WS.getElementPropertyValue(response, 'data.businessCode').toString(), businessCode, FailureHandling.CONTINUE_ON_FAILURE)
	
	ResultSet rs = Statemnet.getORGANIZATION_INFOR(code)
	while(rs.next()) {
		WS.verifyEqual(rs.getString('NAME'), nameRandom, FailureHandling.CONTINUE_ON_FAILURE)
		
		WS.verifyEqual(rs.getString('INVOICE_ISSUING_EMAILS'), emailsRandom, FailureHandling.CONTINUE_ON_FAILURE)
				
		WS.verifyEqual(rs.getString('INVOICE_ISSUING_ADDRESS'), invoiceIssuingAddress, FailureHandling.CONTINUE_ON_FAILURE)
		
		WS.verifyEqual(rs.getString('STATUS'), status, FailureHandling.CONTINUE_ON_FAILURE)
				
		WS.verifyEqual(rs.getString('IDENTIFICATION_NUMBER'), identificationNumberRandom, FailureHandling.CONTINUE_ON_FAILURE)
		
		WS.verifyEqual(rs.getString('PARTNER_TYPE'), partnerType, FailureHandling.CONTINUE_ON_FAILURE)
				
		WS.verifyEqual(rs.getString('INSTITUTION_TYPE'), institutionType, FailureHandling.CONTINUE_ON_FAILURE)
	}
	ConnectionDatabase.closeDatabaseConnection()
} else {
	WS.verifyMatch(WS.getElementPropertyValue(response, 'message').toString(), message, false, FailureHandling.CONTINUE_ON_FAILURE)
}
