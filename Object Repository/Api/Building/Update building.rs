<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Update Building</name>
   <tag></tag>
   <elementGuidId>80bcc1a6-2708-445d-bb25-01985cc40685</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{    \n    \&quot;name\&quot;: \&quot;${name}\&quot;,\n    \&quot;buildingClassId\&quot;: \&quot;${buildingClassId}\&quot;,\n    \&quot;customerTypes\&quot;: [\n        \&quot;${customerTypes}\&quot;\n    ],\n    \&quot;commissioningDate\&quot;: \&quot;${date}\&quot;,\n    \&quot;totalArea\&quot;: \&quot;${totalArea}\&quot;,\n    \&quot;totalBasement\&quot;: ${totalBasement},\n    \&quot;totalFloor\&quot;: ${totalFloor},\n    \&quot;regionCode\&quot;: \&quot;${regionCode}\&quot;,\n    \&quot;provinceCode\&quot;: \&quot;${provinceCode}\&quot;,\n    \&quot;districtCode\&quot;: \&quot;${districtCode}\&quot;,\n    \&quot;address\&quot; : \&quot;${address}\&quot;,\n    \&quot;note\&quot;: \&quot;${note}\&quot;\n}&quot;,
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
      <webElementGuid>4c024c9e-2ffb-40e4-bc83-e68f9362462b</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer ${GlobalVariable.accessToken}</value>
      <webElementGuid>603559e3-04e0-40cf-9600-d3516c5d8f48</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>8.2.5</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${GlobalVariable.URL_BUILDING}/api/buildings/${id}/update</restUrl>
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
