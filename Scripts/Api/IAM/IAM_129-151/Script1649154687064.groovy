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

String businessCodeRandom
String codeRandom
String nameRandom
String emailRandom
String id

if (invoiceIssuingEmails == 'random') {
    emailRandom = FakerData.randomEmail()
} else {
    emailRandom = invoiceIssuingEmails
}

if (name == 'random') {
    nameRandom = FakerData.randomFullName()
} else {
    nameRandom = name
}

if (code == 'random') {
	codeRandom = FakerData.randomBuildingCode()
} else {
	codeRandom = code
}

if (businessCode == 'random') {
    businessCodeRandom = FakerData.randomCode(12)
} else if (businessCode == 'random2') {
    businessCodeRandom = FakerData.randomString()
} else {
    businessCodeRandom = businessCode
}

Map maps = [('institutionType') : institutionType, ('businessCode') : businessCodeRandom, ('code') : codeRandom, ('name') : nameRandom
    , ('type') : type, ('invoiceIssuingEmails') : emailRandom, ('invoiceIssuingAddress') : invoiceIssuingAddress, ('status') : status
    , ('identificationNumber') : identificationNumber, ('partnerType') : partnerType]

ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/IAM/Create Customers', maps), FailureHandling.STOP_ON_FAILURE)

WS.verifyEqual(response.statusCode.toString(), statusCode, FailureHandling.STOP_ON_FAILURE)

if (statusCode == '200') {
    // request vs response
    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.institutionType').toString(), institutionType, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.businessCode').toString(), businessCodeRandom, 
        FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.code').toString(), codeRandom, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.name').toString(), nameRandom, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.invoiceIssuingEmails[0]').toString(), emailRandom, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.invoiceIssuingAddress').toString(), invoiceIssuingAddress, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.status').toString(), status, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.identificationNumber').toString(), identificationNumber, FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.verifyEqual(WS.getElementPropertyValue(response, 'data.partnerType').toString(), partnerType, FailureHandling.CONTINUE_ON_FAILURE)

    ResultSet rs = Statemnet.getORGANIZATION_INFOR(codeRandom)

    while (rs.next()) {
		
		id = rs.getString('ID')
		
        WS.verifyEqual(rs.getString('INSTITUTION_TYPE'), institutionType, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('BUSINESS_CODE'), businessCodeRandom, FailureHandling.CONTINUE_ON_FAILURE)
		
		WS.verifyEqual(rs.getString('CODE'), codeRandom, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('NAME'), nameRandom, FailureHandling.CONTINUE_ON_FAILURE)
		
		WS.verifyEqual(rs.getString('INVOICE_ISSUING_EMAILS'), emailRandom, FailureHandling.CONTINUE_ON_FAILURE)

		WS.verifyEqual(rs.getString('INVOICE_ISSUING_ADDRESS'), invoiceIssuingAddress, FailureHandling.CONTINUE_ON_FAILURE)
		
		WS.verifyEqual(rs.getString('STATUS'), status, FailureHandling.CONTINUE_ON_FAILURE)
		
		WS.verifyEqual(rs.getString('IDENTIFICATION_NUMBER'), identificationNumber, FailureHandling.CONTINUE_ON_FAILURE)
		
		WS.verifyEqual(rs.getString('PARTNER_TYPE'), partnerType, FailureHandling.CONTINUE_ON_FAILURE)	
    }
    ConnectionDatabase.closeDatabaseConnection()
	
	VariableCollections.map.put('id', id)
	VariableCollections.map.put('code', codeRandom)
	VariableCollections.map.put('businessCode', businessCodeRandom)

} else if (statusCode == '400') {
    WS.verifyMatch(WS.getElementPropertyValue(response, 'message').toString(), message, false, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyMatch(WS.getElementPropertyValue(response, 'error').toString(), error, false, FailureHandling.CONTINUE_ON_FAILURE)
}