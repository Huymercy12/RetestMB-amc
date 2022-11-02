import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.sql.*
import com.api.*
import com.database.*
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.testData.*
import groovy.json.JsonSlurper as JsonSlurper

KeywordUtil.logInfo(TC_ID)

Authenticate.setToken([('username') : 'admin', ('password') : '123456aA@'])

String codeRandom

String nameRandom

String commissioningDateRandom

String buildingId

if (code == 'random') {
    codeRandom = FakerData.randomBuildingCode()
} else {
    codeRandom = code
}

if (nameBuilding == 'random') {
    nameRandom = FakerData.randomBuildingName()
} else {
    nameRandom = nameBuilding
}

if (commissioningDate == 'random') {
    commissioningDateRandom = FakerData.randomDate('yyyy-MM-dd')
} else {
    commissioningDateRandom = commissioningDate
}

Map map = DataExcelUtils.getDataByIndex(1, 'Data Files/Api/Building/Create Building')

WS.callTestCase(findTestCase('Test Cases/Api/Building/Building_01-14'), map, FailureHandling.CONTINUE_ON_FAILURE)

ResultSet rs = Statemnet.getBuilding('admin')

while (rs.next()) {
    buildingId = rs.getString('ID')
}

ConnectionDatabase.closeDatabaseConnection()

Map maps = [('buildingId') : buildingId]

ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Building/Delete Building', maps), 
    FailureHandling.STOP_ON_FAILURE)

JsonSlurper slurper = new JsonSlurper()

Map map2 = slurper.parseText(response.getResponseText())

KeywordUtil.logInfo(map2.toString())

WS.verifyEqual(response.statusCode.toString(), statusCode, FailureHandling.CONTINUE_ON_FAILURE)

WS.verifyEqual(WS.getElementPropertyValue(response, 'success').toString(), success, FailureHandling.CONTINUE_ON_FAILURE)

if (statusCode == '200') {
    ResultSet rs1 = Statemnet.getBuilding('admin')

    while (rs1.next()) {
        WS.verifyEqual(rs1.getString('DELETED'), Integer.parseInt('1').toString(), FailureHandling.CONTINUE_ON_FAILURE)
    }
    
    ConnectionDatabase.closeDatabaseConnection()
}