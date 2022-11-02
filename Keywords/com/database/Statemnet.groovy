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
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.database.ConnectionDatabase
import java.sql.*
import internal.GlobalVariable

public class Statemnet {

	def static ResultSet getQUERY_USER(String username) {
		return ConnectionDatabase.executeQuery("SELECT ur.role_id,u.*, ul.BUILDING_ID FROM QA_EVO_IAM.USER_ROLE ur, QA_EVO_IAM.USERS u , QA_EVO_IAM.USER_LOCATION ul WHERE (u.id = ur.user_id and u.id = ul.user_id and u.username='"+username+"')")
	}

	def static ResultSet getQUERY_USER_INFOR(String username) {
		return ConnectionDatabase.executeQuery("SELECT * FROM QA_EVO_IAM.users WHERE username='"+username+"' AND DELETED = 0")
	}

	def static ResultSet getTicket(String username) {
		return ConnectionDatabase.executeQuery("SELECT * FROM EVO_TICKET.TICKET WHERE CREATED_AT = (SELECT MAX(CREATED_AT) FROM EVO_TICKET.TICKET WHERE CREATED_BY = '"+username+"')")
	}

	def static ResultSet getBuilding(String username) {
		return ConnectionDatabase.executeQuery("SELECT * FROM QA_EVO_BUILDING.BUILDING WHERE CREATED_AT = (SELECT MAX(CREATED_AT) FROM QA_EVO_BUILDING.BUILDING WHERE CREATED_BY = '"+username+"')")
	}

	def static ResultSet getBuildingById(String buildingId) {
		return ConnectionDatabase.executeQuery("SELECT * FROM QA_EVO_BUILDING.BUILDING WHERE ID= '"+buildingId+"' AND DELETED = 0")
	}

	def static ResultSet getUnit(String username) {
		return ConnectionDatabase.executeQuery("SELECT * FROM EVO_BUILDING.UNIT WHERE CREATED_AT = (SELECT MAX(CREATED_AT) FROM EVO_BUILDING.UNIT WHERE CREATED_BY = '"+username+"')")
	}

	def static ResultSet getOtherOpinion(String id) {
		return ConnectionDatabase.executeQuery("SELECT OTHER_OPINION FROM EVO_SURVEY.SURVEY_TEMPLATE st WHERE ID = '"+id+"'")
	}

	def static ResultSet getIDSurveyTeamplate() {
		return ConnectionDatabase.executeQuery("SELECT * FROM EVO_SURVEY.SURVEY_TEMPLATE st WHERE CREATED_AT = (select MAX(CREATED_AT) from EVO_SURVEY.SURVEY_TEMPLATE where CREATED_BY = 'admin')")
	}

	def static ResultSet countSurveyTeamplate() {
		return ConnectionDatabase.executeQuery("SELECT count(id) FROM EVO_SURVEY.SURVEY_TEMPLATE")
	}

	def static ResultSet getIDSurvey() {
		return ConnectionDatabase.executeQuery("SELECT ID FROM EVO_SURVEY.SURVEY s WHERE CREATED_At = (SELECT MAX(CREATED_At) FROM EVO_SURVEY.SURVEY s2 where CREATED_BY = 'admin')")
	}

	def static ResultSet getStatusSurvey(String id) {
		return ConnectionDatabase.executeQuery("SELECT STATUS FROM EVO_SURVEY.SURVEY s WHERE ID = '"+id+"'")
	}

	def static ResultSet getIDNotification() {
		return ConnectionDatabase.executeQuery("SELECT ID FROM EVO_NOTIFICATION.EVENT e WHERE (CREATED_AT = (SELECT Max(CREATED_AT) FROM EVO_NOTIFICATION.EVENT WHERE CREATED_BY = 'admin'))")
	}

	def static ResultSet getORGANIZATION_INFOR(String code) {
		return ConnectionDatabase.executeQuery("Select * from QA_EVO_IAM.organization Where CODE='"+code+"'")
	}

	def static ResultSet getORGANIZATION_INFOR_BY_ID(String id) {
		return ConnectionDatabase.executeQuery("Select * from QA_EVO_IAM.organization Where id='"+id+"' AND DELETED = 0")
	}

	def static ResultSet getBuildingsByCurrentUser(String username) {
		return ConnectionDatabase.executeQuery("SELECT ID, NAME, CODE FROM QA_EVO_BUILDING.BUILDING WHERE ID = (SELECT BUILDING_ID FROM QA_EVO_IAM.USER_LOCATION WHERE USER_ID = (SELECT ID FROM QA_EVO_IAM.USERS WHERE USERNAME = '"+username+"'))")
	}

	def static ResultSet getOrganizationByRoleOfCurrentUserLogin(String username) {
		return ConnectionDatabase.executeQuery("SELECT * FROM QA_EVO_IAM.ORGANIZATION o, QA_EVO_IAM.USERS u WHERE o.ID = u.ORGANIZATION_ID AND u.USERNAME = '"+username+"'")
	}

	def static ResultSet getQR(String username) {
		return ConnectionDatabase.executeQuery("SELECT * FROM QA_EVO_TICKET.COMPLAINT_TEMPLATE ct WHERE CREATED_AT = (SELECT MAX(CREATEd_AT) FROM QA_EVO_TICKET.COMPLAINT_TEMPLATE ct2 WHERE CREATED_BY = '"+username+"')")
	}

	def static ResultSet getQRById(String id) {
		return ConnectionDatabase.executeQuery("SELECT * FROM QA_EVO_TICKET.COMPLAINT_TEMPLATE ct WHERE ID = '"+id+"'")
	}

	def static ResultSet getStatusQR(String id) {
		return ConnectionDatabase.executeQuery("SELECT STATUS FROM EVO_TICKET.COMPLAINT_TEMPLATE ct WHERE ID = '"+id+"'")
	}

	def static ResultSet getFloor(String buildingId) {
		return ConnectionDatabase.executeQuery("SELECT * FROM EVO_BUILDING.FLOOR WHERE BUILDING_ID = '"+buildingId+"'")
	}

	def static ResultSet getComplaintInfor(String fullName) {
		return ConnectionDatabase.executeQuery("Select * from QA_EVO_TICKET.complaint WHERE CREATED_AT = (select MAX(CREATED_AT) from QA_EVO_TICKET.complaint where full_name = '"+fullName+"')")
	}

	def static ResultSet getComplaintType(String id) {
		return ConnectionDatabase.executeQuery("SELECT COMPLAINT_TYPE FROM QA_EVO_TICKET.COMPLAINT c WHERE ID = '"+id+"'")
	}

	def static ResultSet getListComplaint(String fullName) {
		return ConnectionDatabase.executeQuery("SELECT * FROM QA_EVO_TICKET.COMPLAINT c WHERE CREATED_AT = (select MAX(CREATED_AT) from QA_EVO_TICKET.complaint where FULL_NAME = '"+fullName+"' AND COMPLAINT_TYPE = 'SPAM')")
	}

	def static ResultSet getNewOrganizationContact(String username) {
		return ConnectionDatabase.executeQuery("SELECT * FROM QA_EVO_IAM.organization_contact WHERE CREATED_AT = (SELECT MAX(CREATED_AT) FROM QA_EVO_IAM.organization_contact WHERE CREATED_BY = '"+username+"')")
	}

	def static String getTotalRecorded(String tableDatabase) {
		String rowNumber
		ResultSet rs=ConnectionDatabase.executeQuery("SELECT COUNT(*) AS rowNumber FROM "+tableDatabase+"  WHERE DELETED = 0")
		while (rs.next()) {
			rowNumber=rs.getString("rowNumber")
		}
		ConnectionDatabase.closeDatabaseConnection()
		return rowNumber
	}

	def static ResultSet getInforCustomerLatest(int beginIndex,int endIndex) {
		return ConnectionDatabase.executeQuery("select * from EVO_IAM.organization where deleted=0 ORDER BY created_at DESC OFFSET "+beginIndex+" ROWS FETCH NEXT "+endIndex+" ROWS ONLY")
	}

	def static String getFullNameUser(String username) {
		String fullName
		ResultSet rs=ConnectionDatabase.executeQuery("SELECT * FROM EVO_IAM.USERS WHERE USERNAME = '"+username+"' AND DELETED = 0")
		while (rs.next()) {
			fullName=rs.getString('FULL_NAME')
		}
		ConnectionDatabase.closeDatabaseConnection()
		return fullName
	}

	def static String getNumBuilding() {
		String rowNumber
		ResultSet rs=ConnectionDatabase.executeQuery("SELECT COUNT(*) AS rowNumber FROM EVO_BUILDING.BUILDING WHERE DELETED = 0")
		while (rs.next()) {
			rowNumber=rs.getString("rowNumber")
		}
		ConnectionDatabase.closeDatabaseConnection()
		return rowNumber
	}

	def static ResultSet getInforBuildingLatest(int beginIndex,int endIndex) {
		return ConnectionDatabase.executeQuery("SELECT * FROM EVO_BUILDING.BUILDING WHERE DELETED = 0 ORDER BY CREATED_AT DESC OFFSET "+beginIndex+" ROWS FETCH NEXT "+endIndex+" ROWS ONLY")
	}

	def static ResultSet getInforBuildingSearch(String buildingCode) {
		return ConnectionDatabase.executeQuery("SELECT * FROM EVO_BUILDING.BUILDING WHERE CODE = '"+buildingCode+"'AND DELETED = 0")
	}

	def static ResultSet getOrganizationContact(String businessCode) {
		return ConnectionDatabase.executeQuery("SELECT * FROM EVO_IAM.ORGANIZATION WHERE BUSINESS_CODE = '"+businessCode+"' AND DELETED = 0")
	}

	def static ResultSet getUserLocation(String buildingId) {
		return ConnectionDatabase.executeQuery("SELECT * FROM EVO_IAM.USER_LOCATION  WHERE BUILDING_ID = '"+buildingId+"'")
	}

	def static String getOrganizationBusinessCodeHasLeasingStatusLeasedOrDeposit() {
		ResultSet rs0=ConnectionDatabase.executeQuery("SELECT evo_iam.organization.business_code FROM EVO_IAM.organization,EVO_IAM.organization_location WHERE (EVO_IAM.organization.id=EVO_IAM.organization_location.ORGANIZATION_ID AND EVO_IAM.organization.DELETED =0 AND (evo_iam.organization_location.leasing_status='LEASED' OR evo_iam.organization_location.leasing_status='DEPOSIT'))")
		String businessCode
		while(rs0.next()) {
			businessCode=rs0.getString('BUSINESS_CODE')
			break
		}
		ConnectionDatabase.closeDatabaseConnection()
		return businessCode
	}

	def static String getOrganizationNameHasLeasingStatusAVAILABLE() {
		ResultSet rs0=ConnectionDatabase.executeQuery("SELECT evo_iam.organization.name FROM EVO_IAM.organization,EVO_IAM.organization_location WHERE (EVO_IAM.organization.id=EVO_IAM.organization_location.ORGANIZATION_ID AND EVO_IAM.organization.DELETED =0 AND (evo_iam.organization_location.leasing_status='RETURNED') AND evo_iam.organization.status='ACTIVE')")
		String businessCode
		while(rs0.next()) {
			businessCode=rs0.getString('NAME')
			break
		}
		ConnectionDatabase.closeDatabaseConnection()
		return businessCode
	}

	def static ResultSet getNewOrganizationContact() {
		return ConnectionDatabase.executeQuery("SELECT * FROM (SELECT *  FROM EVO_IAM.organization_contact  ORDER by created_at DESC ) WHERE rownum <= 1")
	}

	def static ResultSet getFloorById(String floorId) {
		return ConnectionDatabase.executeQuery("select * FROM floor where id='"+floorId+"' AND DELETED = 0")
	}

	def static ResultSet getUnitByCode(String code) {
		return ConnectionDatabase.executeQuery("select * FROM unit where code='"+code+"'")
	}

	def static ResultSet getOrganizationByBusinessCode(String BusinessCode) {
		return ConnectionDatabase.executeQuery("Select * from EVO_IAM.organization Where business_code='"+BusinessCode+"'")
	}

	def static ResultSet getInforUnitLastest(int beginIndex,int endIndex) {
		return ConnectionDatabase.executeQuery("SELECT * FROM EVO_BUILDING.UNIT WHERE DELETED = 0 ORDER BY CREATED_AT DESC OFFSET '"+beginIndex+"' ROWS FETCH NEXT '"+endIndex+"' ROWS ONLY")
	}

	def static ResultSet getOrganizationLocationByUnitId(String unitId) {
		return ConnectionDatabase.executeQuery("SELECT * FROM EVO_IAM.ORGANIZATION_LOCATION WHERE UNIT_ID = '"+unitId+"'")
	}

	def static ResultSet getComplaintTemplateByBuildingCode(String buildingCode, int beginIndex,int endIndex) {
		return ConnectionDatabase.executeQuery("SELECT DISTINCT ct.*, f.NAME, b.CODE, u.full_name FROM EVO_TICKET.COMPLAINT_TEMPLATE ct, EVO_BUILDING.FLOOR f, EVO_BUILDING.BUILDING b,EVO_IAM.USERS u WHERE ct.FLOOR_ID = f.ID AND ct.BUILDING_ID = (SELECT b.ID FROM EVO_BUILDING.BUILDING b WHERE b.CODE = '"+buildingCode+"') AND ct.building_id=b.id and ct.CREATED_BY=u.username ORDER BY ct.CREATED_AT DESC OFFSET '"+beginIndex+"' ROWS FETCH NEXT '"+endIndex+"' ROWS ONLY")
	}

	def static ResultSet getComplaintTemplateByTitle(String title) {
		return ConnectionDatabase.executeQuery("SELECT DISTINCT ct.*, f.NAME, b.CODE, u.full_name FROM EVO_TICKET.COMPLAINT_TEMPLATE ct, EVO_BUILDING.FLOOR f, EVO_BUILDING.BUILDING b,EVO_IAM.USERS u WHERE ct.FLOOR_ID = f.ID AND ct.title='"+ title +"' AND ct.building_id=b.id and ct.CREATED_BY=u.username ORDER BY ct.CREATED_AT DESC")
	}

	def static String getTotalUnit() {
		String rowNumber
		ResultSet rs = ConnectionDatabase.executeQuery("SELECT COUNT(*) AS rowNumber FROM EVO_BUILDING.UNIT WHERE DELETED = 0 AND AREA <> 0 AND AREA > 0")
		while (rs.next()) {
			rowNumber=rs.getString("rowNumber")
		}
		ConnectionDatabase.closeDatabaseConnection()
		return rowNumber
	}

	def static ResultSet getRolesInfor(String code) {
		return ConnectionDatabase.executeQuery("SELECT * FROM EVO_IAM.ROLE LEFT JOIN EVO_IAM.ROLE_PERMISSION ON EVO_IAM.ROLE_PERMISSION.ROLE_ID=EVO_IAM.ROLE.ID WHERE CODE ='"+ code +"' AND EVO_IAM.ROLE_PERMISSION.DELETED = 0")
	}

	def static ResultSet getSurveyTemplateByID(String id) {
		return ConnectionDatabase.executeQuery("Select * from EVO_SURVEY.SURVEY_TEMPLATE Where id='"+id+"'")
	}

	def static ResultSet getFirstSurveyTemplate(String ColumnDatabase) {
		return ConnectionDatabase.executeQuery("SELECT * FROM (SELECT *  FROM EVO_SURVEY.SURVEY_TEMPLATE  ORDER by "+ColumnDatabase+"  ASC ) WHERE rownum <= 1")
	}

	def static ResultSet getInforSurveyLatest(int beginIndex,int endIndex, String ColumnDatabase ) {
		return ConnectionDatabase.executeQuery("select * from EVO_SURVEY.SURVEY_TEMPLATE where deleted=0 ORDER BY "+ColumnDatabase+" ASC OFFSET "+beginIndex+" ROWS FETCH NEXT "+endIndex+" ROWS ONLY")
	}
	def static ResultSet getIDFirstSurveyTemplate(String ColumnDatabase) {
		return ConnectionDatabase.executeQuery("SELECT ID FROM (SELECT *  FROM EVO_SURVEY.SURVEY_TEMPLATE  ORDER by "+ColumnDatabase+"  ASC )")
	}

	def static ResultSet getTicketByUserAndStatus(String username, String status, int beginIndex, int endIndex) {
		return ConnectionDatabase.executeQuery("SELECT * FROM EVO_TICKET.TICKET WHERE CREATED_BY = '"+username+"' AND STATUS = '"+status+"' ORDER BY CREATED_AT DESC OFFSET "+beginIndex+" ROWS FETCH NEXT "+endIndex+" ROWS ONLY")
	}


	def static ResultSet getTotalSurveyByCondition(String ColumnDatabase, String Number,String expectedName) {
		return ConnectionDatabase.executeQuery("SELECT * FROM (SELECT *  FROM EVO_SURVEY.SURVEY_TEMPLATE WHERE deleted=0 AND NAME like '%"+expectedName+"%' ORDER by "+ColumnDatabase+"  ASC ) WHERE rownum <= "+Number+"")
	}

	def static ResultSet getTotalSurveyNameByParam(String expectedName , String Number, String ColumnDatabase) {
		return ConnectionDatabase.executeQuery("select * from EVO_SURVEY.SURVEY_TEMPLATE where deleted=0 AND NAME = '"+expectedName+"' AND (rownum <= "+Number+") ORDER by "+ColumnDatabase+"  ASC")
	}

	def static ResultSet getInforRoleLatest(int beginIndex,int numberRecord) {
		return ConnectionDatabase.executeQuery("SELECT EVO_IAM.ROLE.* FROM EVO_IAM.ROLE WHERE DELETED = 0 OFFSET "+beginIndex+" ROWS FETCH NEXT "+numberRecord+" ROWS ONLY")
	}

	def static String getInforIdRoleLatest(int beginIndex,int numberRecord,String columnName) {
		String idString
		ResultSet rs = ConnectionDatabase.executeQuery("SELECT EVO_IAM.ROLE.ID,EVO_IAM.ROLE.IS_ROOT FROM EVO_IAM.ROLE WHERE DELETED = 0 OFFSET "+beginIndex+" ROWS FETCH NEXT "+numberRecord+" ROWS ONLY")
		while (rs.next()) {
			idString =rs.getString(columnName)
		}
		ConnectionDatabase.closeDatabaseConnection()
		return idString
	}

	def static ResultSet getInforSearchRoleLatest(String value,int beginIndex,int numberRecord) {
		return ConnectionDatabase.executeQuery("SELECT EVO_IAM.ROLE.* FROM EVO_IAM.ROLE WHERE  EVO_IAM.ROLE.NAME LIKE '%"+value+"%' OR EVO_IAM.ROLE.CODE LIKE '%"+value+"%' OR EVO_IAM.ROLE.CODE LIKE '%"+value+"%' AND DELETED = 0 OFFSET "+beginIndex+" ROWS FETCH NEXT "+numberRecord+" ROWS ONLY")
	}

	def static String getNumberRole() {
		String rowNumber
		ResultSet rs=ConnectionDatabase.executeQuery("SELECT COUNT(*) AS rowNumber FROM EVO_IAM.ROLE WHERE DELETED = 0")
		while (rs.next()) {
			rowNumber=rs.getString("rowNumber")
		}
		ConnectionDatabase.closeDatabaseConnection()
		return rowNumber
	}
}