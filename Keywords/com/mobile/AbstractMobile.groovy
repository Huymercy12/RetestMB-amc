package com.mobile

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import org.openqa.selenium.By
import org.openqa.selenium.remote.DesiredCapabilities

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.appium.driver.AppiumDriverManager
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.driver.MobileDriverType
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable
import io.appium.java_client.AppiumDriver
import io.appium.java_client.TouchAction
import io.appium.java_client.android.AndroidElement
import groovy.json.JsonOutput
public class AbstractMobile {

	def static loginWithAccount(Map accountInfo) {
		AppiumDriver<?> driver = MobileDriverFactory.getDriver()
		List<AndroidElement> elements = driver.findElementsByXPath("//android.widget.TextView[@text='Cá nhân']")

		if (elements.size() > 0) {
			Mobile.tap(findTestObject('Object Repository/Mobile/textView', ['text' : 'Cá nhân']), GlobalVariable.TIME_OUT)
			Mobile.tap(findTestObject('Object Repository/Mobile/PersonalScreen/btnLogout'), GlobalVariable.TIME_OUT)
		}

		Mobile.setText(findTestObject('Object Repository/Mobile/editText', ['text' :'Nhập tên đăng nhập']), accountInfo.get('username'), GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)
		Mobile.setText(findTestObject('Object Repository/Mobile/editText', ['text' : 'Nhập mật khẩu']), accountInfo.get('password'), GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)

		Mobile.verifyEqual(Mobile.getAttribute(findTestObject('Object Repository/Mobile/Login-Logout/btnLogin'), 'enabled', GlobalVariable.TIME_OUT), 'true', FailureHandling.STOP_ON_FAILURE)
		Mobile.tap(findTestObject('Object Repository/Mobile/Login-Logout/btnLogin'), GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)
	}

	def static loginWithAccount(String appPackage, Map accountInfo) {
		Mobile.startApplication(appPackage, false)
		loginWithAccount(accountInfo)
	}

	def static String convertPassword(String password) {
		String hidenPass = ""
		for (int i = 0; i < password.length(); i++) {
			hidenPass = hidenPass + "•"
		}
		return hidenPass
	}

	def static loginWithAccount(String udid, Map accountInfo, Map desiredCapabilities) {
		DesiredCapabilities capabilities = new DesiredCapabilities(desiredCapabilities)
		AppiumDriverManager.createMobileDriver(MobileDriverType.ANDROID_DRIVER, udid, capabilities)
		loginWithAccount(accountInfo)
	}

	def static swipeToBottom() {
		int startX = (int) (0.5*Mobile.getDeviceWidth())
		int startY = (int) (0.7*Mobile.getDeviceHeight())
		int endY = (int) (0.5*Mobile.getDeviceHeight())
		Mobile.swipe(startX,startY , startX, endY)
	}

	def static swipeToTop() {
		int startX = (int) (0.5*Mobile.getDeviceWidth())
		int startY = (int) (0.2*Mobile.getDeviceHeight())
		int endY = (int) (0.8*Mobile.getDeviceHeight())
		Mobile.swipe(startX,startY , startX, endY)
	}

	def static scrollToElement(String locater, String listLocater) {
		AppiumDriver driver = MobileDriverFactory.getDriver()
		while(true) {
			List <AndroidElement> list = driver.findElementsByXPath(listLocater)
			String text= list.get(list.size()-5).getText()
			try {
				AndroidElement abc = driver.findElements(By.xpath(locater))
				if(abc.isDisplayed()) {
					break
				}
			}catch(Exception e) {
				swipeToBottom()
				List <AndroidElement> list2 = driver.findElementsByXPath(listLocater)
				String text2= list2.get(list2.size()-5).getText()
				if(text.equalsIgnoreCase(text2)) {
					Mobile.verifyEqual(false,true, FailureHandling.STOP_ON_FAILURE)
					break
				}
			}
		}
	}

	def static Map infoDate(String date) {
		String[] str = date.split('/')
		Map<String, String> mapDate = new HashMap<>()
		mapDate.put('day', str[0])
		mapDate.put('month', str[1])
		mapDate.put('year', str[2])
		return mapDate
	}

	def static String convertMonth(String month) {
		String convertMonth = 'Tháng '
		if (Integer.parseInt(month) < 10) {
			String[] str = month.split('0')
			convertMonth = convertMonth + str[1]
		} else {
			convertMonth = convertMonth + month
		}
		return convertMonth
	}

	def static String convertDate(String day, String month, String year) {
		String convertMonth = convertMonth(month)
		String convertDate = day + ' ' + convertMonth + ', ' + year
		return convertDate
	}
}
