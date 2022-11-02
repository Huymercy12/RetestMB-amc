<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Update user</name>
   <tag></tag>
   <elementGuidId>e372cd43-145f-46bb-840b-c19b88f30867</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n \&quot;username\&quot;:\&quot;${username}\&quot;,\n \&quot;buildingIds\&quot;:[\&quot;${buildingIds}\&quot;],\n \&quot;employeeCode\&quot;:\&quot;${employeeCode}\&quot;,\n \&quot;roleIds\&quot;:[\&quot;${roleIds}\&quot;],\n \&quot;dayOfBirth\&quot;:\&quot;${dayOfBirth}\&quot;,\n \&quot;description\&quot;:\&quot;${description}\&quot;,\n \&quot;gender\&quot;:\&quot;${gender}\&quot;,\n \&quot;departmentName\&quot;:\&quot;${departmentName}\&quot;,\n \&quot;fullName\&quot;:\&quot;${fullName}\&quot;,\n \&quot;email\&quot;:\&quot;${email}\&quot;,\n \&quot;phoneNumber\&quot;:\&quot;${phoneNumber}\&quot;,\n \&quot;status\&quot;:\&quot;${status}\&quot;,\n \&quot;title\&quot;:\&quot;${title}\&quot;,\n \&quot;userLevel\&quot;:\&quot;${userLevel}\&quot;\n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
      <webElementGuid>7d343b01-7242-47f6-a230-33e47e2d829a</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer ${GlobalVariable.accessToken}</value>
      <webElementGuid>3918b8af-71e5-4e24-9cb7-c863d00b6abf</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>8.2.5</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${GlobalVariable.URL_IAM}/api/users/${id}/update</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
