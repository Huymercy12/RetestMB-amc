<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Create User Customer</name>
   <tag></tag>
   <elementGuidId>4462ddac-e539-49d7-b9d1-ffda6df0ce23</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;buildingIds\&quot;: [\n    \&quot;${buildingIds}\&quot;\n  ],\n  \&quot;dayOfBirth\&quot;: \&quot;${dayOfBirth}\&quot;,\n  \&quot;description\&quot;: \&quot;${description}\&quot;,\n  \&quot;email\&quot;: \&quot;${email}\&quot;,\n  \&quot;fullName\&quot;: \&quot;${fullName}\&quot;,\n  \&quot;gender\&quot;: \&quot;${gender}\&quot;,\n  \&quot;organizationId\&quot;: \&quot;${organizationId}\&quot;,\n  \&quot;password\&quot;: \&quot;${password}\&quot;,\n  \&quot;phoneNumber\&quot;: \&quot;${phoneNumber}\&quot;,\n  \&quot;repeatPassword\&quot;: \&quot;${repeatPassword}\&quot;,\n  \&quot;roleIds\&quot;: [\n    \&quot;${roleIds}\&quot;\n  ],\n  \&quot;status\&quot;: \&quot;${status}\&quot;,\n  \&quot;username\&quot;: \&quot;${username}\&quot;\n}&quot;,
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
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer ${GlobalVariable.accessToken}</value>
   </httpHeaderProperties>
   <katalonVersion>8.2.5</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${GlobalVariable.URL_IAM}/api/users/customer</restUrl>
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
