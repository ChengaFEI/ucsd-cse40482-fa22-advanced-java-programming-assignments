package com.chengfei.application;

import com.chengfei.iostream.IOStream;
import com.chengfei.regex.RegEx;
import com.chengfei.streams.Streams;
import com.chengfei.xml.XML;
import com.chengfei.concurrency.Concurrent;
import com.chengfei.networking.*;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

public class FinalApp {

    public static void main(String[] args)
            throws XPathExpressionException, ParserConfigurationException, IOException, SAXException, InterruptedException {

        System.out.println("Using com.adv.java.iostream to write and read the hard-code file:");
        String[] strings = new String[1];
        strings[0] = "--help";
        IOStream.writeRead(strings);
        strings[0] = "--text";
        IOStream.writeRead(strings);
        strings[0] = "--binary";
        IOStream.writeRead(strings);
        strings[0] = "--object";
        IOStream.writeRead(strings);
        System.out.println("-".repeat(100)+"\n");

        System.out.println("Using com.adv.java.regex to process the file.\n"
                + "The results are as follows:");
        strings[0] = "text.txt";
        RegEx.regex(strings);
        System.out.println("-".repeat(100)+"\n");

        System.out.println("Using com.adv.java.streams to process the file.\n"
                + "The results are as follows:");
        strings[0] = "JobResult_124432.txt";
        Streams.streams(strings);
        System.out.println("-".repeat(100)+"\n");

        System.out.println("Using com.adv.java.xml to parse the XML.\n"
                + "The results are as follows:");
        XML.parse();
        System.out.println("-".repeat(100)+"\n");

        System.out.println("Using com.adv.java.concurrency to parse the file.\n"
                + "The results are as follows:");
        String[] conStrings = new String[3];
        conStrings[0] = "--num-threads"; conStrings[1] = "8"; conStrings[2] = "--ReentrantLock";
        Concurrent.concurrent(conStrings);
        conStrings[0] = "--num-threads"; conStrings[1] = "16"; conStrings[2] = "--ReentrantLock";
        Concurrent.concurrent(conStrings);
        System.out.println("-".repeat(100)+"\n");

        System.out.println("All done!");
    }
}