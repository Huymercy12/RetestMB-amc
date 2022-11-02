<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Create Building</name>
   <tag></tag>
   <elementGuidId>403cd363-1e69-4631-a90c-e8b24d31488c</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n    \&quot;code\&quot;: \&quot;${code}\&quot;,\n    \&quot;name\&quot;: \&quot;${name}\&quot;,\n    \&quot;buildingClassId\&quot;: \&quot;${buildingClassId}\&quot;,\n    \&quot;customerTypes\&quot;: [\n        \&quot;${customerTypes}\&quot;\n    ],\n    \&quot;commissioningDate\&quot;: \&quot;${date}\&quot;,\n    \&quot;totalArea\&quot;: \&quot;${totalArea}\&quot;,\n    \&quot;totalBasement\&quot;: ${totalBasement},\n    \&quot;totalFloor\&quot;: ${totalFloor},\n  \t\&quot;address\&quot; : \&quot;${address}\&quot;,\n    \&quot;regionCode\&quot;: \&quot;${regionCode}\&quot;,\n    \&quot;provinceCode\&quot;: \&quot;${provinceCode}\&quot;,\n    \&quot;districtCode\&quot;: \&quot;${districtCode}\&quot;,\n    \&quot;note\&quot;: \&quot;${note}\&quot;,\n    \&quot;status\&quot;: \&quot;${status}\&quot;\n}&quot;,
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
      <webElementGuid>372b9693-49d6-4056-9e54-d1f754fc0045</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer ${GlobalVariable.accessToken}</value>
      <webElementGuid>de790f40-24ad-47ec-b3eb-0b0d14e200c7</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>8.2.5</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${GlobalVariable.URL_BUILDING}/api/buildings</restUrl>
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
