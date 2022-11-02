package com.testData

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

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
import org.apache.commons.lang.RandomStringUtils
import java.util.Random
import java.util.concurrent.ThreadLocalRandom

import internal.GlobalVariable
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit

import com.github.javafaker.DateAndTime;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
public class FakerData {

	def static FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-GB"), new RandomService())

	def static Faker faker=new Faker(new Locale("vi"))

	def static String randomEmail() {
		return fakeValuesService.bothify("?????###@mymail.com");
	}

	def static String randomPhoneNumber() {
		return faker.phoneNumber().phoneNumber()
	}

	def static String randomFullName() {
		return faker.name().fullName()
	}

	def static String randomUsername() {
		return fakeValuesService.bothify("????##")
	}

	def static String randomUsername2() {
		return fakeValuesService.bothify("????.###")
	}

	def static String randomString() {
		return fakeValuesService.bothify("???????")
	}

	def static String parseDate(String date) {
		return date+" 00:00:00.0"
	}

	def static String randomBuildingCode() {
		return fakeValuesService.bothify("ND######")
	}

	def static String randomBuildingName() {
		return fakeValuesService.bothify("Tòa nhà ?????######")
	}

	def static String randomNumber() {
		return faker.number().digits(2)
	}

	def static String randomArea() {
		return fakeValuesService.bothify("9#######")
	}

	def static String getLocalDate() {
		return String.valueOf(LocalDate.now())
	}


	def static long getUnixTimestamp() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd")
		Date date = format.parse(String.valueOf(LocalDateTime.now()))
		long timestamp = date.getTime()
		long nextDay = timestamp + 100000000
		return nextDay
	}

	def static String randomStringQR(int number) {
		return RandomStringUtils.randomAlphabetic(number)
	}

	def static String randomDataInput(int sizeText) {
		StringBuilder data = new StringBuilder()
		for (int i = 0; i < sizeText; i++) {
			data.append(fakeValuesService.bothify("?"))
		}
		return data
	}

	def static String randomDataWithSpace(int sizeText) {
		StringBuilder data = new StringBuilder()
		data.append("   ")
		for (int i = 0; i < sizeText; i++) {
			data.append(fakeValuesService.bothify("?"))
		}
		data.append("   ")
		return data
	}

	def static int randomFloor(int number) {
		Random r = new Random()
		return r.nextInt(number) + 1
	}

	def static String randomDate(String formatDate) {
		LocalDate from = LocalDate.of(2000, 1, 1);
		LocalDate to = LocalDate.of(2022, 1, 1);
		long days = from.until(to, ChronoUnit.DAYS);
		long randomDays = ThreadLocalRandom.current().nextLong(days + 1);
		LocalDate randomDate = from.plusDays(randomDays);
		return randomDate.format(DateTimeFormatter.ofPattern(formatDate)).toString()
	}

	def static String randomPassword() {
		return fakeValuesService.bothify("#####??A@")
	}

	def static String randomPhoneNumberNoSpace() {
		return fakeValuesService.bothify("0#########")
	}

	def static String randomGender() {
		Random r = new Random()
		List<String> listGender = Arrays.asList('Nam', 'Nữ', 'Khác')
		int randomIndex = r.nextInt(listGender.size())
		String randomGender = listGender.get(randomIndex)
		return randomGender
	}

	def static String randomCode(int size) {
		StringBuilder code = new StringBuilder()
		for (int i = 0; i < size; i++) {
			code.append(fakeValuesService.bothify("#"))
		}
		return code
	}
}
