package com.api

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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.sun.org.apache.bcel.internal.generic.RETURN
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import internal.GlobalVariable

public class Authenticate {

	public static ResponseObject setToken(Map<String,Object> maps = [:]) {
		ResponseObject response= WS.sendRequestAndVerify(findTestObject('Api/IAM/Authenticate',maps))
		if (response.statusCode == 200) {
			GlobalVariable.refreshToken= WS.getElementPropertyValue(response,'data.refreshToken',FailureHandling.CONTINUE_ON_FAILURE)
			GlobalVariable.accessToken= WS.getElementPropertyValue(response,'data.accessToken',FailureHandling.CONTINUE_ON_FAILURE)
		}
		return response
	}
}
