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
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testdata.reader.ExcelFactory
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class DataExcelUtils {
	def static Map<Object,Object> getDataByIndex(int rowIndex, String testDataId) {
		TestData data=TestDataFactory.findTestData(testDataId)
		List<Object> columnNames=data.getColumnNames().toList()
		Map<Object,Object> maps =new HashMap<Object,Object>()
		for(Object name:columnNames) {
			maps.put(name, data.getObjectValue(name, rowIndex))
		}
		return maps
	}

	def static Map<Object,Object> getDataByTestCaseId(String TC_Id, String testDataId) {
		TestData data=TestDataFactory.findTestData(testDataId)
		List<Object> columnNames=data.getColumnNames().toList()
		Map<Object,Object> maps =new HashMap<Object,Object>()
		List<List<Object>> allData=data.getAllData()
		for(List<Object> list:allData) {
			if (list.get(columnNames.indexOf('TC_ID')).toString().equalsIgnoreCase(TC_Id)) {
				for (Object name:columnNames) {
					maps.put(name, list.get(columnNames.indexOf(name)))
				}
			}
		}
		return maps
	}
}
