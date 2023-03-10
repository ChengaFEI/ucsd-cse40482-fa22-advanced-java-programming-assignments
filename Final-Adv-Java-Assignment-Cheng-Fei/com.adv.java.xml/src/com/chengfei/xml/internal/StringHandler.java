package com.chengfei.xml.internal;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Handler for SAX parsing.
 */
public class StringHandler extends DefaultHandler {
    private static final String SERIAL = "serial";
    private static final String VSTRING = "visible-string";
    private static final String UNSIGNED = "unsigned";

    private String serial;
    private String vString;
    private String unsigned;
    private StringBuilder elementText;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (elementText == null) {
            elementText = new StringBuilder();
        }
        else {
            elementText.append(ch, start, length);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        serial = null;
        vString = null;
        unsigned = null;
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) throws SAXException {
        switch (qName) {
            case SERIAL, UNSIGNED, VSTRING -> elementText = new StringBuilder();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case SERIAL -> serial = elementText.toString();
            case VSTRING -> vString = elementText.toString();
            case UNSIGNED -> unsigned = elementText.toString();
        }
    }

    public String getSerial() { return serial; }
    public String getVString() { return vString; }
    public String getUnsigned() { return unsigned; }
}