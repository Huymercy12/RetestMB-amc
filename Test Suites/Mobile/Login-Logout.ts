<?xml version="1.0" encoding="UTF-8"?>
<TestSuiteEntity>
   <description></description>
   <name>Login-Logout</name>
   <tag></tag>
   <isRerun>false</isRerun>
   <mailRecipient></mailRecipient>
   <numberOfRerun>0</numberOfRerun>
   <pageLoadTimeout>2</pageLoadTimeout>
   <pageLoadTimeoutDefault>true</pageLoadTimeoutDefault>
   <rerunFailedTestCasesOnly>false</rerunFailedTestCasesOnly>
   <rerunImmediately>false</rerunImmediately>
   <testSuiteGuid>b2cc763b-79d0-4ea7-b618-041f940d8e8d</testSuiteGuid>
   <testCaseLink>
      <guid>bf4f1231-b6cb-41aa-9841-846096b96013</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>false</isRun>
      <testCaseId>Test Cases/Mobile/Login-Logout/VerifyLoginScreen</testCaseId>
      <usingDataBindingAtTestSuiteLevel>true</usingDataBindingAtTestSuiteLevel>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>5ec00258-3263-469a-806e-fbe00317793a</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId></testDataLinkId>
         <type>DEFAULT</type>
         <value></value>
         <variableId>e5a183e8-dfc0-4e78-b506-74fd10f82024</variableId>
      </variableLink>
   </testCaseLink>
   <testCaseLink>
      <guid>56c67edf-c14b-41a7-9091-8a2e4dc840cf</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>false</isRun>
      <testCaseId>Test Cases/Mobile/Login-Logout/VerifyUserLogin</testCaseId>
      <testDataLink>
         <combinationType>ONE</combinationType>
         <id>3fe0dce4-d7de-4fd7-92c8-72816e648493</id>
         <iterationEntity>
            <iterationType>ALL</iterationType>
            <value></value>
         </iterationEntity>
         <testDataId>Data Files/Mobile/Login</testDataId>
      </testDataLink>
      <usingDataBindingAtTestSuiteLevel>true</usingDataBindingAtTestSuiteLevel>
      <variableLink>
         <testDataLinkId>3fe0dce4-d7de-4fd7-92c8-72816e648493</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>username</value>
         <variableId>16dfd9d3-ee71-41d9-a4c4-78bfa584c6c8</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>3fe0dce4-d7de-4fd7-92c8-72816e648493</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>password</value>
         <variableId>c8e1bc4f-da9f-44ff-871d-70593d482a20</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>3fe0dce4-d7de-4fd7-92c8-72816e648493</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>result</value>
         <variableId>bf4ba319-33fc-4461-aa97-c274011f5ae8</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>3fe0dce4-d7de-4fd7-92c8-72816e648493</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>description</value>
         <variableId>e70bddf2-9179-42d7-971f-636c34f2e686</variableId>
      </variableLink>
   </testCaseLink>
   <testCaseLink>
      <guid>854e8013-dfb7-404e-aece-e6cdfe622a26</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/Mobile/Login-Logout/AccountType</testCaseId>
      <testDataLink>
         <combinationType>ONE</combinationType>
         <id>7bde50e8-73b9-4921-836e-f8207a8a41f3</id>
         <iterationEntity>
            <iterationType>ALL</iterationType>
            <value></value>
         </iterationEntity>
         <testDataId>Data Files/Mobile/AccountType</testDataId>
      </testDataLink>
      <usingDataBindingAtTestSuiteLevel>true</usingDataBindingAtTestSuiteLevel>
      <variableLink>
         <testDataLinkId>7bde50e8-73b9-4921-836e-f8207a8a41f3</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>username</value>
         <variableId>6f108e8c-5e65-4027-a839-f67a06222390</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>7bde50e8-73b9-4921-836e-f8207a8a41f3</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>password</value>
         <variableId>193f6b10-7f56-4137-83c4-c94a0c9a8dbd</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>7bde50e8-73b9-4921-836e-f8207a8a41f3</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>description</value>
         <variableId>72ff6cf3-ba41-4c52-8e0d-3873d87c8bd8</variableId>
      </variableLink>
   </testCaseLink>
</TestSuiteEntity>
