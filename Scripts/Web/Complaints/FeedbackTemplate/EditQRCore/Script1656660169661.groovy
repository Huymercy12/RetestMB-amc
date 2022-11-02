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
import com.testData.DataExcelUtils as DataExcelUtils
import com.testData.FakerData as FakerData

KeywordUtil.logInfo(info)

Map map = DataExcelUtils.getDataByIndex(2, 'Data Files/Web/ComplaintTemplate/CreateNewQRCode')

WebUI.callTestCase(findTestCase('Test Cases/Web/Complaints/FeedbackTemplate/CreateNewQRCode'), map, FailureHandling.STOP_ON_FAILURE)

AbstractWeb.waitVerifyElementAndClick('Object Repository/Web/DashboardPage/linkFeebbackTemplate')

WebUI.click(findTestObject('Object Repository/Web/Complaints/FeedbackTemplate/buttonEdit'), FailureHandling.STOP_ON_FAILURE)

String buildingCodeDisplayed = splitBulingInfo(AbstractWeb.getText('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/selectBuilding'))[
0]

String floorDisplayed = AbstractWeb.getText('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/selectFloor')

String templateNameDisplayed = AbstractWeb.getAttribute('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxTitleQRCode', 
    'value')

String statusDisplayed = AbstractWeb.getText('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/selectStatus')

String noteDisplayed = AbstractWeb.getAttribute('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxNote', 
    'value')

ResultSet rs = Statemnet.getComplaintTemplateByTitle(templateNameDisplayed)

// verify Ui vs database
while (rs.next()) {
    WebUI.verifyEqual(buildingCodeDisplayed, rs.getString('CODE'), FailureHandling.STOP_ON_FAILURE)

    WebUI.verifyEqual(floorDisplayed, rs.getString('NAME'), FailureHandling.STOP_ON_FAILURE)

    WebUI.verifyEqual(templateNameDisplayed, rs.getString('TITLE'), FailureHandling.STOP_ON_FAILURE)

    WebUI.verifyEqual(noteDisplayed, rs.getString('NOTE'), FailureHandling.STOP_ON_FAILURE)

    if (statusDisplayed.equalsIgnoreCase('Hoạt động')) {
        WebUI.verifyEqual('ACTIVE', rs.getString('STATUS'), FailureHandling.STOP_ON_FAILURE)
    } else {
        WebUI.verifyEqual('INACTIVE', rs.getString('STATUS'), FailureHandling.STOP_ON_FAILURE)
    }
}

//select floor
WebUI.click(findTestObject('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/selectFloor'), FailureHandling.STOP_ON_FAILURE)

List<WebElement> listFloor = WebUI.findWebElements(findTestObject('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/optionSelect2'), 
    GlobalVariable.TIME_OUT)

Random rand = new Random()

def randomElement1 = listFloor.get(rand.nextInt(listFloor.size()))

randomElement1.click()

//input titleTemplate
if (titleTemplate.toString().equalsIgnoreCase('random')) {
    WebUI.setText(findTestObject('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxTitleQRCode'), 
        FakerData.randomDataInput(100), FailureHandling.STOP_ON_FAILURE)
} else if (titleTemplate.toString().equalsIgnoreCase('random200')) {
    WebUI.setText(findTestObject('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxTitleQRCode'), 
        FakerData.randomDataInput(200), FailureHandling.STOP_ON_FAILURE)
} else if (titleTemplate.toString().equalsIgnoreCase('random201')) {
    WebUI.setText(findTestObject('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxTitleQRCode'), 
        FakerData.randomDataInput(201), FailureHandling.STOP_ON_FAILURE)

    WebUI.verifyEqual(AbstractWeb.getAttribute('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxTitleQRCode', 
            'value').length(), 200, FailureHandling.STOP_ON_FAILURE)
} else if (titleTemplate.toString().equalsIgnoreCase('trim space')) {
    WebUI.setText(findTestObject('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxTitleQRCode'), 
        ('   ' + FakerData.randomDataInput(100)) + '   ', FailureHandling.STOP_ON_FAILURE)
} else {
    WebUI.setText(findTestObject('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxTitleQRCode'), 
        (titleTemplate + 'a') + Keys.BACK_SPACE, FailureHandling.STOP_ON_FAILURE)
}

// select status
WebUI.click(findTestObject('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/selectStatus'), FailureHandling.STOP_ON_FAILURE)

List<WebElement> listStatus = WebUI.findWebElements(findTestObject('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/optionSelect2'), 
    GlobalVariable.TIME_OUT)

def randomElement2 = listStatus.get(rand.nextInt(listStatus.size()))

randomElement2.click()

//input note
if (note.toString().equalsIgnoreCase('random')) {
    WebUI.setText(findTestObject('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxNote'), FakerData.randomDataInput(
            100), FailureHandling.STOP_ON_FAILURE)
} else if (note.toString().equalsIgnoreCase('random1000')) {
    WebUI.setText(findTestObject('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxNote'), FakerData.randomDataInput(
            1000), FailureHandling.STOP_ON_FAILURE)
} else if (note.toString().equalsIgnoreCase('random1001')) {
    WebUI.setText(findTestObject('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxNote'), FakerData.randomDataInput(
            1001), FailureHandling.STOP_ON_FAILURE)

    WebUI.verifyEqual(AbstractWeb.getAttribute('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxNote', 
            'value').length(), 1000, FailureHandling.STOP_ON_FAILURE)
} else if (note.toString().equalsIgnoreCase('trim space')) {
    WebUI.setText(findTestObject('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxNote'), ('   ' + 
        FakerData.randomDataInput(100)) + '   ', FailureHandling.STOP_ON_FAILURE)
} else {
    WebUI.setText(findTestObject('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxNote'), note, 
        FailureHandling.STOP_ON_FAILURE)
}

if (action.toString().equalsIgnoreCase('cancel')) {
    AbstractWeb.clickButton('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/buttonCancel')

    AbstractWeb.verifyText('Object Repository/Web/titleTable', 'Danh sách Mã QR')
} else if (action.toString().equalsIgnoreCase('update')) {
    AbstractWeb.clickButton('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/buttonUpdate')

    WebUI.sendKeys(findTestObject('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textareaPUReason'), 
        FakerData.randomDataInput(50), FailureHandling.STOP_ON_FAILURE)

    AbstractWeb.clickButton('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/buttonPUUpdate')

    if (message.toString().equalsIgnoreCase('Lỗi')) {
        AbstractWeb.verifyMessage('Object Repository/Web/message', message)
    } else {
        AbstractWeb.verifyMessage('Object Repository/Web/message', message)

        String floorInput = AbstractWeb.getText('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/selectFloor')

        String templateNameInput = AbstractWeb.getAttribute('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxTitleQRCode', 
            'value')

        String statusInput = AbstractWeb.getText('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/selectStatus')

        String noteInput = AbstractWeb.getAttribute('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/textBoxNote', 
            'value').trim()

        if (noteInput.equalsIgnoreCase('')) {
            noteInput = noteDisplayed
        }
        
        ResultSet rs2 = Statemnet.getComplaintTemplateByTitle(templateNameInput)

        while (rs2.next()) {
            WebUI.verifyEqual(floorInput, rs2.getString('NAME'), FailureHandling.STOP_ON_FAILURE)

            WebUI.verifyEqual(templateNameInput, rs2.getString('TITLE'), FailureHandling.STOP_ON_FAILURE)

            if (statusInput.equalsIgnoreCase('Hoạt động')) {
                WebUI.verifyEqual('ACTIVE', rs2.getString('STATUS'), FailureHandling.STOP_ON_FAILURE)
            } else {
                WebUI.verifyEqual('INACTIVE', rs2.getString('STATUS'), FailureHandling.STOP_ON_FAILURE)
            }
            
            WebUI.verifyEqual(noteInput, rs2.getString('NOTE').toString(), FailureHandling.STOP_ON_FAILURE)
        }
    }
} else {
    WebUI.verifyElementNotClickable(findTestObject('Object Repository/Web/Complaints/FeedbackTemplate/GeneralInformation/buttonUpdate'), 
        FailureHandling.STOP_ON_FAILURE)
}

ConnectionDatabase.closeDatabaseConnection()

String[] splitBulingInfo(String bulidingText) {
    return bulidingText.split('\\s', 3)
}