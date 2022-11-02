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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.web.AbstractWeb as AbstractWeb
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import static org.junit.Assert.assertTrue
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import com.database.*
import java.sql.*
import java.sql.*

AbstractWeb.verifyText('Object Repository/Web/Roles/titleTable', tableTitle)

AbstractWeb.waitAndVerifyElement('Object Repository/Web/Roles/RoleManagement/buttonCreate')

WebUI.verifyMatch(WebUI.getAttribute(findTestObject('Object Repository/Web/Roles/RoleManagement/placeholderSearch'), 'placeholder'), search, false, FailureHandling.CONTINUE_ON_FAILURE)

String size = AbstractWeb.getText('Object Repository/Web/DashboardPage/sizeOfPagination')

WebUI.verifyMatch(size.substring(8), Statemnet.getNumberRole(), false, FailureHandling.CONTINUE_ON_FAILURE)

AbstractWeb.verifyArrayText('Object Repository/Web/Buildings/Unit/headerTable', numericalOrder, columnNameRole, isAdmin, code, levelRole, describle, timeCreate, creator, timeUpdate, status, action)

int count = 0

List<WebElement> rows = WebUI.findWebElements(findTestObject('Object Repository/Web/Roles/listRowTable'), GlobalVariable.TIME_OUT)

if (rows.size() <= Integer.parseInt(WebUI.getText(findTestObject('Object Repository/Web/DashboardPage/maxRow')))) {
	
	assertTrue(true)

} else {
	
	assertTrue(false)

}

ResultSet rs

for(WebElement tr : rows) {
	
	List<WebElement> listTd = tr.findElements(By.tagName('td'))
	
	rs = Statemnet.getInforRoleLatest(count, 1)
	
	while (rs.next()) {
		
		WebUI.verifyMatch(listTd.get(0).getText(), (count+1).toString(), false, FailureHandling.CONTINUE_ON_FAILURE)
		
		WebUI.verifyMatch(listTd.get(1).getText(), rs.getString('NAME'), false, FailureHandling.CONTINUE_ON_FAILURE)
		
		if(rs.getString('IS_ROOT') == "1") {
			
			WebUI.verifyMatch(listTd.get(2).getText(), "true", false)
			
		} else {
			
			WebUI.verifyMatch(listTd.get(2).getText(), "false", false)
		
		}
		
		assertTrue(rs.getString('CODE').contains(AbstractWeb.getConvertSubStringTextToLength(listTd.get(3).getText(),3)))
		
		if(rs.getString('DESCRIPTION') != null  && listTd.get(5).getText() != ""){
			
			assertTrue(rs.getString('DESCRIPTION').contains(AbstractWeb.getConvertSubString(listTd.get(5).getText())))
		}
		
		if(rs.getString('STATUS') == 'ACTIVE'){
			
			WebUI.verifyMatch(listTd.get(9).findElement(By.tagName('span')).getText(), 'Hoạt động', false)
			
		} else {
			
			WebUI.verifyMatch(listTd.get(9).findElement(By.tagName('span')).getText(), 'Không hoạt động', false)
			
		}
		
		List<WebElement> listButton=listTd.get(10).findElements(By.tagName('mb-button-action'))
		
		for(WebElement element:listButton) {
			
			assertTrue(element.isDisplayed())
			
		}
	}
	
	count ++
	
}

ConnectionDatabase.closeDatabaseConnection()

WebUI.verifyElementPresent(findTestObject("Object Repository/Web/Roles/pagination"), GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)