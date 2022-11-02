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

String emailRandom

String employeeRandom

String descriptionRandom

String departmentNameRandom

String fullNameRandom

String phoneNumberRandom

String dayOfBirthRandom

String titleRandom


if (email == 'random') {
    emailRandom = FakerData.randomEmail()
} else {
    emailRandom = email
}

if (employeeCode == 'random') {
    employeeCodeRandom = FakerData.randomString()
} else {
    employeeCodeRandom = employeeCode
}

if (description == 'random') {
    descriptionRandom = FakerData.randomString()
} else {
    descriptionRandom = description
}

if (departmentName == 'random') {
    departmentNameRandom = FakerData.randomString()
} else {
    departmentNameRandom = departmentName
}

if (fullName == 'random') {
    fullNameRandom = FakerData.randomFullName()
} else {
    fullNameRandom = fullName
}

if (phoneNumber == 'random') {
    phoneNumberRandom = FakerData.randomPhoneNumber()
} else {
    phoneNumberRandom = phoneNumber
}

if (dayOfBirth == 'random') {
    dayOfBirthRandom = FakerData.getLocalDate()
} else {
    dayOfBirthRandom = dayOfBirth
}

if (title == 'random') {
    titleRandom = FakerData.randomString()
} else {
    titleRandom = title
}


// test api 
// những biến và giá trị chuyền vào api ('organizationId') : organizationId/
KeywordUtil.logInfo(TC_ID)
Map maps = [('id') : id, ('employeeCode') : employeeCodeRandom, ('roleIds') : roleIds, ('dayOfBirth') : dayOfBirthRandom
    , ('description') : descriptionRandom, ('gender') : gender, ('departmentName') : departmentNameRandom, ('fullName') : fullNameRandom
    , ('email') : emailRandom, ('phoneNumber') : phoneNumberRandom, ('status') : status,
    , ('title') : titleRandom, ('userLevel') : userLevel, ('username'): username, ('buildingIds') : buildingIds] 

ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/IAM/Update user', maps), FailureHandling.STOP_ON_FAILURE)
KeywordUtil.logInfo(response.responseText)

WS.verifyEqual(response.statusCode.toString(), statusCode, FailureHandling.STOP_ON_FAILURE)

if (statusCode == '200') { // kiểm tra giá trị respon và so sánh với datafile
    // request vs response
	// WS.verifyEqual(WS.getElementPropertyValue(response, 'data.avatarFileId').toString(), avatarFileId, FailureHandling.CONTINUE_ON_FAILURE) 
	
    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.employeeCode').toString(), employeeCodeRandom, FailureHandling.CONTINUE_ON_FAILURE)
	
	WS.verifyEqual(WS.getElementPropertyValue(response, 'data.userLevel').toString(), userLevel, FailureHandling.CONTINUE_ON_FAILURE)
	
    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.roles[0].id').toString(), roleIds, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.dayOfBirth').toString(), dayOfBirthRandom, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.description').toString(), descriptionRandom, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.gender').toString(), gender, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.departmentName').toString(), departmentNameRandom, FailureHandling.CONTINUE_ON_FAILURE)
        
    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.fullName').toString(), fullNameRandom, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.email').toString(), emailRandom, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.phoneNumber').toString(), phoneNumberRandom, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.status').toString(), status, FailureHandling.CONTINUE_ON_FAILURE)

   // WS.verifyEqual(WS.getElementPropertyValue(response, 'data.organizationId').toString(), organizationId, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'data.title').toString(), titleRandom, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'success').toString(), success, FailureHandling.CONTINUE_ON_FAILURE)
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
    // test database
    ResultSet rs = Statemnet.getQUERY_USER(username)  

    while (rs.next()) {
       // WS.verifyEqual(rs.getString('AVATAR_FILE_ID'), avatarFileId, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('EMPLOYEE_CODE'), employeeCodeRandom, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('ROLE_ID'), roleIds, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('DAY_OF_BIRTH'), FakerData.parseDate(dayOfBirthRandom), FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('DESCRIPTION'), descriptionRandom, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('GENDER'), gender, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('DEPARTMENT_NAME'), departmentNameRandom, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('FULL_NAME'), fullNameRandom, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('EMAIL'), emailRandom, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('PHONE_NUMBER'), phoneNumberRandom, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('STATUS'), status, FailureHandling.CONTINUE_ON_FAILURE)

       // WS.verifyEqual(rs.getString('ORGANIZATION_ID'), organizationId, FailureHandling.CONTINUE_ON_FAILURE)

        WS.verifyEqual(rs.getString('TITLE'), titleRandom, FailureHandling.CONTINUE_ON_FAILURE)
    }
    
    ConnectionDatabase.closeDatabaseConnection()
} else if (statusCode == '400') {
    WS.verifyEqual(WS.getElementPropertyValue(response, 'message').toString(), message, FailureHandling.CONTINUE_ON_FAILURE)

    WS.verifyEqual(WS.getElementPropertyValue(response, 'error').toString(), error, FailureHandling.CONTINUE_ON_FAILURE)
}