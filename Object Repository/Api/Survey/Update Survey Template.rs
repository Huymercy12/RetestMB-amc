<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Update Survey Template</name>
   <tag></tag>
   <elementGuidId>5f155e69-1b50-4622-aedf-dd94c59148ce</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <connectionTimeout>0</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;name\&quot;: \&quot;${name}\&quot;,\n  \&quot;otherOpinion\&quot;: \&quot;${otherOpinion}\&quot;,\n  \&quot;questionGroups\&quot;: [\n    {\n      \&quot;id\&quot;: \&quot;${id}\&quot;,\n      \&quot;label\&quot;: \&quot;${label}\&quot;,\n      \&quot;name\&quot;: \&quot;${name_q}\&quot;,\n      \&quot;questions\&quot;: [\n        {\n          \&quot;content\&quot;: \&quot;${content}\&quot;,\n          \&quot;id\&quot;: \&quot;${id_q}\&quot;,\n          \&quot;label\&quot;: \&quot;${label_q}\&quot;,\n          \&quot;rangeAnswer\&quot;:${rangeAnswer},\n          \&quot;type\&quot;: \&quot;${type}\&quot;\n        }\n      ]\n    }\n  ]\n}&quot;,
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
      <webElementGuid>302ba36e-dfa3-4cfe-860d-88dbc71364a9</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer ${GlobalVariable.accessToken}</value>
      <webElementGuid>0bfac393-eacd-4393-aa05-1dedbd2fa024</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>8.2.5</katalonVersion>
   <maxResponseSize>0</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${GlobalVariable.URL_SURVEY}/api/survey-templates/${idSurvey}/update</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>0</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>7851e1c9-9aed-462c-86e4-6a9da3349dc2</id>
      <masked>false</masked>
      <name>name</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>7015a0fc-94d7-4bbc-b97f-6cb6c38d313f</id>
      <masked>false</masked>
      <name>otherOpinion</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>bfb46d15-e7c8-44e4-9a32-ccf7fcd0b012</id>
      <masked>false</masked>
      <name>id_1</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>46022800-fb30-4054-a6a9-6561c4f6f5b2</id>
      <masked>false</masked>
      <name>label</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>6ebb5b0a-14b3-4a54-b136-5d8c2a30044d</id>
      <masked>false</masked>
      <name>name_q</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>bf638098-bc08-407b-a248-e4e52bb911f1</id>
      <masked>false</masked>
      <name>content</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>be08f559-599b-4179-af0a-3ad0e0e18806</id>
      <masked>false</masked>
      <name>id_q</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>63ffe656-f106-406b-9f49-b59ff4f43a95</id>
      <masked>false</masked>
      <name>label_q</name>
   </variables>
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
