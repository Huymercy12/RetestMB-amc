<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Create Survey</name>
   <tag></tag>
   <elementGuidId>5fc7c2e6-73d2-4c71-9e30-451e56ac9954</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;buildingIds\&quot;: [\n    \&quot;${buildingIds}\&quot;\n  ],\n  \&quot;endAt\&quot;: ${endAt},\n  \&quot;floorIds\&quot;: [\n    \&quot;${floorIds}\&quot;\n  ],\n  \&quot;introduction\&quot;: \&quot;${introduction}\&quot;,\n  \&quot;name\&quot;: \&quot;${name}\&quot;,\n  \&quot;note\&quot;: \&quot;${note}\&quot;,\n  \&quot;notificationContent\&quot;: \&quot;${notificationContent}\&quot;,\n  \&quot;organizationIds\&quot;: [\n    \&quot;${organizationIds}\&quot;\n  ],\n  \&quot;startAt\&quot;: ${startAt},\n  \&quot;status\&quot;: \&quot;${status}\&quot;,\n  \&quot;surveyTemplate\&quot;: {\n    \&quot;name\&quot;: \&quot;${name}\&quot;,\n    \&quot;otherOpinion\&quot;: \&quot;${otherOpinion}\&quot;\n  },\n  \&quot;surveyTemplateId\&quot;: \&quot;${surveyTemplateId}\&quot;\n}&quot;,
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
   <restUrl>${GlobalVariable.URL_SURVEY}/api/surveys</restUrl>
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
