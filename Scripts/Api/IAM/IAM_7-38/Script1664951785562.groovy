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
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import static org.junit.Assert.assertTrue
import org.openqa.selenium.Keys as Keys
import com.api.*
import com.database.*
import com.testData.*
import com.web.AbstractWeb as AbstractWeb
import ch.qos.logback.core.joran.conditional.ElseAction as ElseAction
import java.sql.*
import java.time.DayOfWeek as DayOfWeek
import org.json.*

KeywordUtil.logInfo(TC_ID)

String emailRandom

String phoneNumberRandom

String fullNameRandom

String usernameRandom

String dayOfBirthRandom

String buildingIdsRandom

String roleIdsRandom

String employeeCodeRandom

if (email == 'random') {
    emailRandom = FakerData.randomEmail()
} else {
    emailRandom = email
}

if (phoneNumber == 'random') {
    phoneNumberRandom = FakerData.randomPhoneNumberNoSpace()
} else {
    phoneNumberRandom = phoneNumber
}

if (fullName == 'random') {
    fullNameRandom = FakerData.randomFullName()
} else {
    fullNameRandom = fullName
}

if (username == 'random') {
    usernameRandom = FakerData.randomUsername()
} else {
    usernameRandom = username
}

if (dayOfBirth == '') {
    dayOfBirthRandom = dayOfBirth
} else {
    dayOfBirthRandom = AbstractWeb.changeDateFormat(dayOfBirth, 'dd/MM/yyyy', 'yyyy-MM-dd')
}

if (buildingIds == 'random') {
    buildingIdsRandom = FakerData.randomBuildingCode()
} else {
    buildingIdsRandom = buildingIds
}

if (roleIds == 'random') {
    roleIdsRandom = FakerData.randomUsername()
} else {
    roleIdsRandom = roleIds
}

if (employeeCode == 'random') {
    employeeCodeRandom = FakerData.randomNumber()
} else {
    employeeCodeRandom = employeeCode
}

Map maps = [('buildingIds') : buildingIdsRandom, ('dayOfBirth') : dayOfBirthRandom, ('email') : emailRandom, ('employeeCode') : employeeCodeRandom
    , ('fullName') : fullNameRandom, ('gender') : gender, ('phoneNumber') : phoneNumberRandom, ('roleIds') : roleIdsRandom
    , ('status') : status, ('userLevel') : userLevel, ('username') : usernameRandom, ('description') : description, ('departmentname') : departmentname
    , ('tiltle') : tiltle]

println(maps)

ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/IAM/LDAP', maps), FailureHandling.STOP_ON_FAILURE)

println(response.getResponseText())

WS.verifyEqual(response.statusCode.toString(), statusCode, FailureHandling.STOP_ON_FAILURE)

if (statusCode == '200') {
    // request vs response
    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.username').toString(), usernameRandom, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.fullName').toString(), fullNameRandom, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.email').toString(), emailRandom, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.phoneNumber').toString(), phoneNumberRandom, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.dayOfBirth').toString(), dayOfBirthRandom, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.gender').toString(), gender, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.userLevel').toString(), userLevel, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.status').toString(), status, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.employeeCode').toString(), employeeCodeRandom, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.description').toString(), description, FailureHandling.CONTINUE_ON_FAILURE)

	WS.verifyEqual(WS.getElementPropertyValue(response, 'data.authenticationType').toString(), authenticationType, FailureHandling.CONTINUE_ON_FAILURE)
    ResultSet rs = Statemnet.getQUERY_USERLDAP(usernameRandom)

    while (rs.next()) {
        WS.verifyEqual(rs.getString('USERNAME'), usernameRandom, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('FULL_NAME'), fullNameRandom, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('EMAIL'), emailRandom, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('PHONE_NUMBER'), phoneNumberRandom, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('DAY_OF_BIRTH'), FakerData.parseDate(dayOfBirthRandom), FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('GENDER'), gender, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('DESCRIPTION'), description, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('STATUS'), status, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('BUILDING_ID'), buildingIds, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('ROLE_ID'), roleIds, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('AUTHENTICATION_TYPE'), authenticationType, FailureHandling.CONTINUE_ON_FAILURE)
    }
    
    ConnectionDatabase.closeDatabaseConnection()
} else if (statusCode == '400') {
    WS.verifyEqual(WS.getElementPropertyValue(response, 'message').toString(), message, FailureHandling.CONTINUE_ON_FAILURE)
}