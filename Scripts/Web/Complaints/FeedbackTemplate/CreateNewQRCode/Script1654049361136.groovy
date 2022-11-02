import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.web.AbstractWeb as AbstractWeb
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.interactions.Actions as Actions
import org.testng.Assert as Assert
import com.database.*
import java.sql.*
import com.testData.FakerData as FakerData

KeywordUtil.logInfo(info)

WebUI.click(findTestObject('Object Repository/Web/Complaints/FeedbackTemplate/buttonCreate'), FailureHandling.STOP_ON_FAILURE)

//input bulding
if (selectBuiding.toString().equalsIgnoreCase('random')) {
    AbstractWeb.clickButton('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/selectBuilding')

    WebUI.sendKeys(findTestObject('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxBulding'), 
        '' + Keys.ENTER, FailureHandling.STOP_ON_FAILURE)
}

//input floor
if (selectFloor.toString().equalsIgnoreCase('random')) {
    AbstractWeb.clickButton('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/selectFloor')

    WebUI.click(findTestObject('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/optionSelect2'), 
        FailureHandling.STOP_ON_FAILURE)
}

//input template name
if (complaintTemplateName.toString().equalsIgnoreCase('random')) {
    AbstractWeb.sendText('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxTitleQRCode', FakerData.randomDataInput(
            100))
} else if (complaintTemplateName.toString().equalsIgnoreCase('random200')) {
    AbstractWeb.sendText('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxTitleQRCode', FakerData.randomDataInput(
            200))
} else if (complaintTemplateName.toString().equalsIgnoreCase('random201')) {
    AbstractWeb.sendText('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxTitleQRCode', FakerData.randomDataInput(
            201))

    WebUI.verifyEqual(AbstractWeb.getAttribute('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxTitleQRCode', 
            'value').length(), 200, FailureHandling.STOP_ON_FAILURE)
} else {
    AbstractWeb.sendText('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxTitleQRCode', complaintTemplateName)
}

//input status
AbstractWeb.clickButton('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/selectStatus')

WebUI.click(findTestObject('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/optionSelect', [('text') : status]), 
    FailureHandling.STOP_ON_FAILURE)

//input note
if (note.toString().equalsIgnoreCase('random')) {
    AbstractWeb.sendText('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxNote', FakerData.randomDataInput(
            100))
} else if (note.toString().equalsIgnoreCase('random1000')) {
    AbstractWeb.sendText('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxNote', FakerData.randomDataInput(
            1000))
} else if (note.toString().equalsIgnoreCase('random1001')) {
    AbstractWeb.sendText('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxNote', FakerData.randomDataInput(
            1001))

    WebUI.verifyEqual(AbstractWeb.getAttribute('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxNote', 
            'value').length(), 1000, FailureHandling.STOP_ON_FAILURE)
} else {
    AbstractWeb.sendText('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxNote', note)
}

if (action.toString().equalsIgnoreCase('cancel')) {
    AbstractWeb.clickButton('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/buttonCancel')

    AbstractWeb.verifyText('Object Repository/Web/titleTable', 'Danh sách Mã QR')
} else if (action.toString().equalsIgnoreCase('create')) {
    AbstractWeb.clickButton('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/buttonCreate')

    if (message.toString().equalsIgnoreCase('Lỗi')) {
        AbstractWeb.verifyMessage('Object Repository/Web/message', message)
    } else {
        AbstractWeb.verifyMessage('Object Repository/Web/message', message)

        String buildingCodeInput = splitBulingInfo(AbstractWeb.getText('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/selectBuilding'))[
        0]

        String floorInput = AbstractWeb.getText('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/selectFloor')

        String templateNameInput = AbstractWeb.getAttribute('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxTitleQRCode', 
            'value')

        String statusInput = AbstractWeb.getText('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/selectStatus')

        String noteInput = AbstractWeb.getAttribute('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxNote', 
            'value')

        if (noteInput.equalsIgnoreCase('')) {
            noteInput = 'null'
        }
        
        ResultSet rs = Statemnet.getComplaintTemplateByTitle(templateNameInput)

        while (rs.next()) {
            WebUI.verifyEqual(buildingCodeInput, rs.getString('CODE'), FailureHandling.STOP_ON_FAILURE)

            WebUI.verifyEqual(floorInput, rs.getString('NAME'), FailureHandling.STOP_ON_FAILURE)

            WebUI.verifyEqual(templateNameInput, rs.getString('TITLE'), FailureHandling.STOP_ON_FAILURE)

            if (statusInput.equalsIgnoreCase('Hoạt động')) {
                WebUI.verifyEqual('ACTIVE', rs.getString('STATUS'), FailureHandling.STOP_ON_FAILURE)
            } else {
                WebUI.verifyEqual('INACTIVE', rs.getString('STATUS'), FailureHandling.STOP_ON_FAILURE)
            }
            
            WebUI.verifyEqual(noteInput, rs.getString('NOTE').toString(), FailureHandling.STOP_ON_FAILURE)
        }
    }
} else {
    WebUI.verifyElementNotClickable(findTestObject('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/buttonCreate'), 
        FailureHandling.STOP_ON_FAILURE)
}

String[] splitBulingInfo(String bulidingText) {
    return bulidingText.split('\\s', 3)
}