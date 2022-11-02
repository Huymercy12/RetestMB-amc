<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>LDAP</name>
   <tag></tag>
   <elementGuidId>e94a5ac6-576c-4afb-9eee-ec1d3529c86c</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <authorizationRequest>
      <authorizationInfo>
         <entry>
            <key>bearerToken</key>
         </entry>
      </authorizationInfo>
      <authorizationType>Bearer</authorizationType>
   </authorizationRequest>
   <connectionTimeout>0</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n    \&quot;buildingIds\&quot;: [\n        \&quot;${buildingIds}\&quot;\n    ],\n    \&quot;employeeCode\&quot;: \&quot;${employeeCode}\&quot;,\n    \&quot;roleIds\&quot;: [\n        \&quot;${roleIds}\&quot;\n    ],\n    \&quot;dayOfBirth\&quot;: \&quot;${dayOfBirth}\&quot;,\n    \&quot;description\&quot;: \&quot;${description}\&quot;,\n    \&quot;gender\&quot;: \&quot;${gender}\&quot;,\n    \&quot;departmentName\&quot;: \&quot;departmentName\&quot;,\n    \&quot;username\&quot;: \&quot;${username}\&quot;,\n    \&quot;fullName\&quot;: \&quot;${fullName}\&quot;,\n    \&quot;email\&quot;: \&quot;${email}\&quot;,\n    \&quot;phoneNumber\&quot;: \&quot;${phoneNumber}\&quot;,\n    \&quot;status\&quot;: \&quot;${status}\&quot;,\n    \&quot;title\&quot;: \&quot;title\&quot;,\n    \&quot;userLevel\&quot;: \&quot;${userLevel}\&quot;\n}&quot;,
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
      <webElementGuid>1043edd1-0d37-40e8-8057-f3170361a54d</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value> Bearer ${GlobalVariable.accessToken}</value>
      <webElementGuid>15b97676-0824-49b3-a3cc-d75886ca0a3e</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>8.4.1</katalonVersion>
   <maxResponseSize>0</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${GlobalVariable.URL_IAM}/api/users</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>0</socketTimeout>
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
