import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.annotation.SetUp as SetUp
import com.kms.katalon.core.annotation.SetupTestCase as SetupTestCase
import com.kms.katalon.core.annotation.TearDown as TearDown
import com.kms.katalon.core.annotation.TearDownTestCase as TearDownTestCase
import com.api.*

// Please change skipped to be false to activate this method.
/**
 * Clean test suites environment.
 */
// Please change skipped to be false to activate this method.
// Put your code here.
/**
 * Run before each test case starts.
 */
// Please change skipped to be false to activate this method.
/**
 * Run after each test case ends.
 */
// Please change skipped to be false to activate this method.
// Put your code here.

@SetUp(skipped = true)
def setUp() {
}

@TearDown(skipped = true)
def tearDown() {
}

@SetupTestCase(skipped = false)
def setupTestCase() {
    Authenticate.setToken(GlobalVariable.ACCOUNT_CENTER)
}

@TearDownTestCase(skipped = true)
def tearDownTestCase() {
}

/**
 * References:
 * Groovy tutorial page: http://docs.groovy-lang.org/next/html/documentation/
 */