package com.chengfei.xml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.chengfei.xml.internal.StringHandler;

public class XML {

    /**
     * Parse the file "JobResult_UCSDExt.xml".
     * @throws ParserConfigurationException if the DocumentBuilderFactory is not created.
     * @throws IOException if file not found.
     * @throws SAXException if not able to parse the XML file.
     * @throws XPathExpressionException if the XPath is invalid.
     */
    public static void parse()
            throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        String fileName = "JobResult_UCSDExt.xml";
        String serial;
        String vString = null;
        String unsigned = null;

        // Create a DOM document by the filename.
        Document doc;
        try {
            doc = getDocument(fileName);
        }
        catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }

        // Parse the XML file using the DOM parser.
        System.out.println("Results of XML Parsing using DOM:");
        serial = doc.getElementsByTagName("serial")
                .item(0)
                .getTextContent();
        Node dataNode = doc.getElementsByTagName("data").item(0);
        if (dataNode.getNodeType() == Node.ELEMENT_NODE) {
            vString = ((Element) dataNode)
                    .getElementsByTagName("visible-string")
                    .item(0)
                    .getTextContent();
        }
        dataNode = doc.getElementsByTagName("data").item(1);
        if (dataNode.getNodeType() == Node.ELEMENT_NODE) {
            dataNode = ((Element) dataNode)
                    .getElementsByTagName("structure").item(0);
            if (dataNode.getNodeType() == Node.ELEMENT_NODE) {
                unsigned = ((Element) dataNode)
                        .getElementsByTagName("unsigned")
                        .item(0)
                        .getTextContent();
            }
        }
        System.out.printf("serial: %s\n", serial);
        System.out.printf("visible-string: %s\n", vString);
        System.out.printf("unsigned: %s\n", unsigned);

        // Parse the XML file using the SAX parser.
        System.out.println("Results of XML Parsing using SAX:");
        StringHandler stringHandler = new StringHandler();
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser saxParser = spf.newSAXParser();
        saxParser.parse(fileName, stringHandler);
        serial = stringHandler.getSerial();
        vString = stringHandler.getVString();
        unsigned = stringHandler.getUnsigned();
        System.out.printf("serial: %s\n", serial);
        System.out.printf("visible-string: %s\n", vString);
        System.out.printf("unsigned: %s\n", unsigned);

        // Parse the XML file using the XPath.
        System.out.println("Results of XML Parsing using XPath:");
        // Parse the document.
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        serial = xPath.evaluate("/jobresult/serial", doc);
        vString = xPath.evaluate("/jobresult/data[1]/visible-string", doc);
        unsigned = xPath.evaluate("/jobresult/data[2]/structure/unsigned", doc);
        System.out.printf("serial: %s\n", serial);
        System.out.printf("visible-string: %s\n", vString);
        System.out.printf("unsigned: %s\n", unsigned);
    }

    /**
     * Create a DOM Document object by the filename.
     * @param fileName XML file name.
     * @return Document XML DOM document.
     * @throws ParserConfigurationException if DocumentBuilderFactory is not created.
     * @throws IOException if file not found.
     * @throws SAXException if not able to parse XML file.
     */
    private static Document getDocument(String fileName)
            throws ParserConfigurationException, IOException, SAXException {
        File file = new File(fileName);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
        return documentBuilder.parse(file);
    }
}
