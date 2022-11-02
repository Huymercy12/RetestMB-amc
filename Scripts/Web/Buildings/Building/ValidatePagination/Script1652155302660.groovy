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
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.web.AbstractWeb 
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import static org.junit.Assert.assertTrue
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import com.database.*
import com.google.common.hash.BloomFilter
import com.TestData.DataExcelUtils
import java.sql.*
import org.openqa.selenium.interactions.Actions
import org.testng.Assert

WebDriver driver = DriverFactory.getWebDriver()
KeywordUtil.logInfo(description)
AbstractWeb.selectMaxNumberRowDisplayed('Object Repository/Web/DashboardPage/numberRow', row)

AbstractWeb.verifyText('Object Repository/Web/DashboardPage/maxRow', row.trim())

if(Integer.parseInt(AbstractWeb.getText('Object Repository/Web/Buildings/lastIndexInTable')) <= Integer.parseInt(row.trim())) {
	Assert.assertTrue(true)
} else {
	Assert.assertTrue(false)
}

while (true) {
	if (AbstractWeb.getAttribute('Object Repository/Web/DashboardPage/paginationNext', 'class').equalsIgnoreCase('pagination-next hover')) {
		AbstractWeb.clickButton('Object Repository/Web/DashboardPage/paginationNext')
	}else {
		break
	}
}

AbstractWeb.getInforSizeOfPagination()

WebUI.verifyMatch(AbstractWeb.getText('Object Repository/Web/Buildings/firstIndexInTable'), GlobalVariable.INFOR_SIZE_OF_PAGINATION.getAt('beginIndex'), false, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyMatch(AbstractWeb.getText('Object Repository/Web/Buildings/lastIndexInTable'), GlobalVariable.INFOR_SIZE_OF_PAGINATION.getAt('endIndex'), false, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyMatch(Statemnet.getTotalRecorded('EVO_BUILDING.BUILDING'), GlobalVariable.INFOR_SIZE_OF_PAGINATION.getAt('totalRecord') , false, FailureHandling.STOP_ON_FAILURE)

while (true) {
	if (AbstractWeb.getAttribute('Object Repository/Web/DashboardPage/paginationPrevious', 'class').equalsIgnoreCase('pagination-previous hover')) {
		AbstractWeb.clickButton('Object Repository/Web/DashboardPage/paginationPrevious')
	}else {
		break
	}
}

AbstractWeb.getInforSizeOfPagination()

WebUI.verifyMatch(AbstractWeb.getText('Object Repository/Web/Buildings/firstIndexInTable'), GlobalVariable.INFOR_SIZE_OF_PAGINATION.getAt('beginIndex'), false, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyMatch(AbstractWeb.getText('Object Repository/Web/Buildings/lastIndexInTable'), GlobalVariable.INFOR_SIZE_OF_PAGINATION.getAt('endIndex'), false, FailureHandling.STOP_ON_FAILURE)

AbstractWeb.selectMaxNumberRowDisplayed('Object Repository/Web/DashboardPage/numberRow', '10 ')

Actions action = new Actions(driver)
action.sendKeys(Keys.HOME).perform()