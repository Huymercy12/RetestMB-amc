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

String emailRandom

String invoiceIssuingAddressRandom

String legalRepresentativeRandom

String nameRandom

String phoneNumberRandom

String incorporationDateRandom

if (email == 'random') {
    emailRandom = FakerData.randomEmail()
} else {
    emailRandom = email
}

if (invoiceIssuingAddress == 'random') {
    invoiceIssuingAddressRandom = FakerData.randomString()
} else {
    invoiceIssuingAddressRandom = invoiceIssuingAddress
}

if (legalRepresentative == 'random') {
    legalRepresentativeRandom = FakerData.randomString()
} else {
    legalRepresentativeRandom = invoiceIssuingAddress
}

if (name == 'random') {
    nameRandom = FakerData.randomUsername()
} else {
    nameRandom = name
}

if (phoneNumber == 'random') {
    phoneNumberRandom = FakerData.randomPhoneNumber()
} else {
    phoneNumberRandom = phoneNumber
}

if (incorporationDate == 'random') {
    incorporationDateRandom = FakerData.getLocalDate()
} else {
    incorporationDateRandom = incorporationDate
}

Map maps = [('email') : emailRandom, ('invoiceIssuingAddress') : invoiceIssuingAddressRandom, ('legalRepresentative') : legalRepresentativeRandom
    , ('name') : nameRandom, ('phoneNumber') : phoneNumberRandom, ('incorporationDate') : incorporationDateRandom, ('status') : status
    , ('id') : id]

ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/IAM/Update Organization', maps), FailureHandling.STOP_ON_FAILURE)

WS.verifyMatch(response.statusCode.toString(), statusCode, false, FailureHandling.STOP_ON_FAILURE)

if (statusCode == '200') {
    // request vs response
    WS.verifyMatch(WS.getElementPropertyValue(response, 'data.legalRepresentative').toString(), legalRepresentativeRandom, 
        false, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyMatch(WS.getElementPropertyValue(response, 'data.name').toString(), nameRandom, false, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyMatch(WS.getElementPropertyValue(response, 'data.email').toString(), emailRandom, false, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyMatch(WS.getElementPropertyValue(response, 'data.phoneNumber').toString(), phoneNumberRandom, false, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyMatch(WS.getElementPropertyValue(response, 'data.invoiceIssuingAddress').toString(), invoiceIssuingAddressRandom, 
        false, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyMatch(WS.getElementPropertyValue(response, 'data.status').toString(), status, false, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyMatch(WS.getElementPropertyValue(response, 'data.incorporationDate').toString(), incorporationDateRandom, false, 
        FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyMatch(WS.getElementPropertyValue(response, 'success').toString(), success, false, FailureHandling.CONTINUE_ON_FAILURE)

    ResultSet rs =Statemnet.getORGANIZATION_INFOR(nameRandom)

    while (rs.next()) {
        WS.verifyMatch(rs.getString('NAME'), nameRandom, false, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyMatch(rs.getString('EMAIL'), emailRandom, false, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyMatch(rs.getString('PHONE_NUMBER'), phoneNumberRandom, false, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyMatch(rs.getString('INCORPORATION_DATE'), FakerData.parseDate(incorporationDateRandom), false, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyMatch(rs.getString('INVOICE_ISSUING_ADDRESS'), invoiceIssuingAddressRandom, false, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyMatch(rs.getString('STATUS'), status, false, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyMatch(rs.getString('LEGAL_REPRESENTATIVE'), legalRepresentativeRandom, false, FailureHandling.CONTINUE_ON_FAILURE)
    }
    
    ConnectionDatabase.closeDatabaseConnection()
} else if (statusCode == '400') {
    WS.verifyMatch(WS.getElementPropertyValue(response, 'message').toString(), message, false, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyMatch(WS.getElementPropertyValue(response, 'error').toString(), error, false, FailureHandling.CONTINUE_ON_FAILURE)
}