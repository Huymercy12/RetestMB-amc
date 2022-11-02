package com.database

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
import java.sql.*
import internal.GlobalVariable

public class ConnectionDatabase {

	private static Connection CONNECTION = null

	public static Connection getConnectDB(){
		Map maps= GlobalVariable.DATABASE
		try {
			if(CONNECTION != null && !CONNECTION.isClosed()){
				CONNECTION.close()
			}
			CONNECTION = DriverManager.getConnection(maps.getAt('DATABASE_PROTOCOL')+maps.getAt('DATABASE_HOST')+':'+maps.getAt('DATABASE_PORT')+'/'+maps.getAt('DATABASE_SID'),maps.getAt('DATABASE_USERNAME'),maps.getAt('DATABASE_PASSWORD'))
			System.out.println("connect successfully!");
		} catch (Exception e) {
			System.out.println("connect failure!");
			e.printStackTrace()
		}
		return CONNECTION
	}

	public static ResultSet executeQuery(String queryString) {
		getConnectDB()
		Statement stm = CONNECTION.createStatement()
		return stm.executeQuery(queryString)
	}


	public static void closeDatabaseConnection() {
		if(CONNECTION != null && !CONNECTION.isClosed()){
			CONNECTION.close()
		}
		CONNECTION = null
	}
}
