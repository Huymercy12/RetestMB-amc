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
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import com.kms.katalon.entity.global.GlobalVariableEntity as GlobalVariableEntity
import static org.junit.Assert.assertEquals
import static org.junit.Assert.*
import com.api.*
import com.database.*
import com.testData.*
import groovy.json.JsonSlurper as JsonSlurper
import java.sql.*
import javax.xml.ws.Response as Response
import org.json.*
import org.jsoup.select.Evaluator.ContainsText as ContainsText

Map map = [('name') : name, ('keyword') : keyword, ('sortBy') : sortBy, ('pageIndex') : pageIndex, ('pageSize') : pageSize]

ResponseObject response = WS.sendRequestAndVerify(findTestObject('Object Repository/Api/Survey/Search Survey Template', 
        map), FailureHandling.STOP_ON_FAILURE)
if (Code == '200') {
    // Get all response in 1 List 
    ResultSet rs = Statemnet.getTotalSurveyNameByParam(keyword, pageSize, Sort)

    ArrayList<String> listDBName = new ArrayList<String>()

    while (rs.next()) {
        String name = rs.getString('name').toString()

        String ID = rs.getString('ID').toString()

        String OPINION = rs.getString('OTHER_OPINION').toString()

        listDBName.add(name)

        listDBName.add(ID)

        listDBName.add(OPINION)
    }
    
    // Get all DataBase in 1 List
    ArrayList<String> listResponseName = WS.getElementPropertyValue(response, 'data.name')

    ArrayList<String> listResponseID = WS.getElementPropertyValue(response, 'data.id')

    ArrayList<String> listResponseOPINION = WS.getElementPropertyValue(response, 'data.otherOpinion')

    listResponseName.addAll(listResponseID)

    listResponseName.addAll(listResponseOPINION)

    Collections.sort(listDBName)

    Collections.sort(listResponseName)

    println(listDBName)

    println(listResponseName)

    boolean isEqual = listDBName.equals(listResponseName)

    // Verify Response with DataBase
    assertTrue(isEqual)
}else if(Code == '400') {
} else {
	WS.verifyMatch(WS.getElementPropertyValue(response, 'message').toString(), messagee, false, FailureHandling.CONTINUE_ON_FAILURE)}


ConnectionDatabase.closeDatabaseConnection()