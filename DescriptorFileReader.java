/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.SynapseUnitTestClient;

import java.io.*;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

/**
 * Class responsible for reading data from the test descriptor file
 */
public class DescriptorFileReader {

    private static Logger log = Logger.getLogger(DescriptorFileReader.class.getName());
    TestDataHolder dataHolder = new TestDataHolder();

    public String inputXmlPayload2 = null;
    public String artifactId2 = null;
    public String fileName2 = null;
    public String properties2 = null;
    public String expectedPropVal2 = null;
    public String expectedPayload2 = null;

    public TestDataHolder readDescriptorFile(String descriptorFilePath) {

        BasicConfigurator.configure();

        try {
            String fileString = FileUtils.readFileToString(new File(descriptorFilePath));
            OMElement xmlFile = AXIOMUtil.stringToOM(fileString);

            QName qName1 = new QName("", "set-inputXmlPayload", "");
            OMElement inputXmlPayload1 = xmlFile.getFirstChildWithName(qName1);
            inputXmlPayload2 = inputXmlPayload1.getText();

            QName qName2 = new QName("", "artifactIdString", "");
            OMElement artifactId1 = xmlFile.getFirstChildWithName(qName2);
            artifactId2 = artifactId1.getText();

            QName qName3 = new QName("", "fileName", "");
            OMElement fileName1 = xmlFile.getFirstChildWithName(qName3);
            fileName2 = fileName1.getText();

            QName qName4 = new QName("", "properties", "");
            OMElement properties1 = xmlFile.getFirstChildWithName(qName4);
            properties2 = properties1.getText();

            QName qName5 = new QName("", "expectedPropVal", "");
            OMElement expectedPropVal1 = xmlFile.getFirstChildWithName(qName5);
            expectedPropVal2 = expectedPropVal1.getText();

            QName qName6 = new QName("", "expectedPayload", "");
            OMElement expectedPayload1 = xmlFile.getFirstChildWithName(qName6);
            expectedPayload2 = expectedPayload1.getText();

            dataHolder.setInputXmlPayload(inputXmlPayload2);
            dataHolder.setArtifactId(artifactId2);
            dataHolder.setFileName(fileName2);
            dataHolder.setProperties(properties2);
            dataHolder.setExpectedPropVal(expectedPropVal2);
            dataHolder.setExpectedPayload(expectedPayload2);

            return dataHolder;

        } catch (FileNotFoundException e) {
            log.error("File not found");
        } catch (XMLStreamException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        }
        return null;
    }
}