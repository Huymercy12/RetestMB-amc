package com.web

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import javax.security.auth.login.FailedLoginException

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.sun.org.apache.bcel.internal.generic.RETURN
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import internal.GlobalVariable

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.Keys
import org.openqa.selenium.OutputType
import org.openqa.selenium.interactions.Actions
import org.testng.Assert

import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.File
import java.io.IOException
import java.text.Normalizer
import java.text.SimpleDateFormat
import java.util.regex.Pattern
import javax.imageio.ImageIO

import com.google.zxing.BinaryBitmap
import com.google.zxing.MultiFormatReader
import com.google.zxing.NotFoundException
import com.google.zxing.Result
import com.google.zxing.client.j2se.BufferedImageLuminanceSource
import com.google.zxing.common.HybridBinarizer
import org.bouncycastle.util.encoders.Base64

public class AbstractWeb {

	def static clickButton(String objectId) {
		WebUI.click(findTestObject(objectId), FailureHandling.STOP_ON_FAILURE)
	}

	def static verifyVisible(String objectId) {
		WebUI.verifyElementVisible(findTestObject(objectId), FailureHandling.CONTINUE_ON_FAILURE)
	}

	def static sendText(String objectId,String text) {
		WebUI.clearText(findTestObject(objectId))
		WebUI.sendKeys(findTestObject(objectId), text, FailureHandling.STOP_ON_FAILURE)
	}

	def static String getText(String objectId,Map<String,Object> map=[:]) {
		WebUI.waitForElementVisible(findTestObject(objectId,map), GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)
		return WebUI.getText(findTestObject(objectId,map),FailureHandling.STOP_ON_FAILURE)
	}

	def static verifyText(String objectId,Map<String,Object> map=[:],String text) {
		WebUI.verifyMatch(getText(objectId,map).trim(),text.trim(), false, FailureHandling.CONTINUE_ON_FAILURE)
	}

	def static verifyMessage(String objectId,String message) {
		WebUI.verifyMatch(getText(objectId,[('message'):message]),message, false, FailureHandling.CONTINUE_ON_FAILURE)
	}

	def static waitAndVerifyElement(String objectId) {
		WebUI.waitForElementVisible(findTestObject(objectId), GlobalVariable.TIME_OUT, FailureHandling.STOP_ON_FAILURE)
		verifyVisible(objectId)
	}

	def static waitVerifyElementAndSendKey(String objectId,String text) {
		waitAndVerifyElement(objectId)
		WebUI.clearText(findTestObject(objectId), FailureHandling.STOP_ON_FAILURE)
		WebUI.sendKeys(findTestObject(objectId), text, FailureHandling.STOP_ON_FAILURE)
	}

	def static waitVerifyElementAndClick(String objectId) {
		waitAndVerifyElement(objectId)
		WebUI.click(findTestObject(objectId), FailureHandling.STOP_ON_FAILURE)
	}

	def static waitAndSearch(String objectId,String text) {
		waitVerifyElementAndSendKey(objectId, text)
		WebUI.sendKeys(findTestObject(objectId), Keys.chord(Keys.ENTER))
	}

	// Chuyển tiếng việt thành tiếng anh, chữ hoa thành chữ thường
	def static String convertString(String value){
		String temp = Normalizer.normalize(value, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("").replace('đ', 'd').replace('Đ', 'D').toLowerCase().trim()
	}

	def static selectMaxNumberRowDisplayed(String objectId, String number) {
		scrollToEndPage()
		moveToElementAndClick('Object Repository/Web/DashboardPage/dropdownShow')
		moveToElementAndClick('Object Repository/Web/DashboardPage/numberRow', [('number'):number])
	}

	def static String getAttribute(String objectId, String attribute) {
		WebElement element = WebUI.findWebElement(findTestObject(objectId), GlobalVariable.TIME_OUT)
		return element.getAttribute(attribute)
	}

	def static moveToElementAndClick(String objectId,Map<String,Object> maps = [:]) {
		WebDriver driver=DriverFactory.getWebDriver()
		WebUI.mouseOver(findTestObject(objectId,maps), FailureHandling.STOP_ON_FAILURE)
		Actions actions= new Actions(driver)
		actions.click().perform()
	}

	def static getInforSizeOfPagination() {
		String rawInfor=WebUI.getText(findTestObject('Object Repository/Web/DashboardPage/sizeOfPagination'), FailureHandling.STOP_ON_FAILURE)
		String[] str
		str=rawInfor.split('-')
		GlobalVariable.INFOR_SIZE_OF_PAGINATION.putAt('beginIndex', str[0])
		String[] str2=str[1].split(' of ')
		GlobalVariable.INFOR_SIZE_OF_PAGINATION.putAt('endIndex', str2[0])
		GlobalVariable.INFOR_SIZE_OF_PAGINATION.putAt('totalRecord', str2[1])
	}

	def static scrollToEndPage() {
		WebDriver driver=DriverFactory.getWebDriver()
		WebUI.click(findTestObject('Object Repository/Web/DashboardPage/textFullName'), FailureHandling.STOP_ON_FAILURE)
		Actions action = new Actions(driver)
		action.keyDown(Keys.CONTROL).sendKeys(Keys.END).keyUp(Keys.CONTROL).perform()
	}

	def static verifyArrayText(String onjectId,String ...text) {
		List<WebElement> listElement= WebUI.findWebElements(findTestObject(onjectId),GlobalVariable.TIME_OUT)
		for(int i=0;i<listElement.size();i++) {
			Assert.assertTrue(listElement.get(i).isDisplayed(),text[i]+"not displayed")
			Assert.assertEquals(listElement.get(i).getText(), text[i])
		}
	}

	/**
	 * @param dateString : 19/05/1890
	 * @param fromFormat : dd/MM/yyyy
	 * @param toFormat	 : yyyy-MM-dd HH:mm:ss.S
	 * @return
	 */
	def static String changeDateFormat(String dateString,String fromFormat ,String toFormat) {
		SimpleDateFormat dt = new SimpleDateFormat(fromFormat)
		Date date = dt.parse(dateString)
		SimpleDateFormat dt1 = new SimpleDateFormat(toFormat)
		return dt1.format(date).toString()
	}

	def static String contentPopupLock(String buildingCode) {
		return 'Bạn có chắc chắn khóa tòa nhà '+buildingCode+' không?'
	}

	def static String contentPopupUnlock(String buildingCodeUnlock) {
		return 'Xác nhận mở khóa tòa nhà ' +buildingCodeUnlock
	}

	def static String getInforOfBuilding(String text, String infor) {
		String getInfor
		String[] str = text.split('\\*')
		String[] str1 = str[1].split("/")
		if (infor.equals('total')) {
			String[] str2 =  str1[1].split('\\)')
			getInfor = str2[0].trim()
		} else if (infor.equals('input')) {
			String[] str2 =  str1[0].split('\\(')
			getInfor = str2[1].trim()
		}
		return getInfor
	}

	public static String readQRCode(String base64Image) {
		String encodedContent = null
		byte[] imageBytes = Base64.decode(base64Image)
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes)
		BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream)
		encodedContent = readQRCode(bufferedImage)
		return encodedContent
	}

	public static String readQRCode(File qrCodeFile) {
		String encodedContent = null
		BufferedImage bufferedImage = ImageIO.read(qrCodeFile)
		encodedContent = readQRCode(bufferedImage)
		return encodedContent
	}

	public static String readQRCode(BufferedImage bufferedImage) {
		String encodedContent = null
		BufferedImageLuminanceSource bufferedImageLuminanceSource = new BufferedImageLuminanceSource(bufferedImage)
		HybridBinarizer hybridBinarizer = new HybridBinarizer(bufferedImageLuminanceSource)
		BinaryBitmap binaryBitmap = new BinaryBitmap(hybridBinarizer)
		MultiFormatReader multiFormatReader = new MultiFormatReader()
		Result result = multiFormatReader.decode(binaryBitmap)
		encodedContent = result.getText()
		return encodedContent
	}

	public static String readQRCode(String objectId,OutputType target) {
		WebElement anElement = WebUI.findWebElement(findTestObject(objectId),GlobalVariable.TIME_OUT)
		return readQRCode(anElement.getScreenshotAs(target))
	}

	def static getConvertSubString(String text) {
		if(text.length() > 3){
			if(text.substring(text.length()-3).equalsIgnoreCase('...')) {
				return text.substring(0,text.length()-3)
			}
		}
		return text
	}

	def static getConvertSubStringTextToLength(String text,int lenght) {
		if(text.length() > lenght){
			if(text.substring(text.length()-lenght).equalsIgnoreCase('...')) {
				return text.substring(0,text.length()-lenght)
			}
		}
		return text
	}
}

