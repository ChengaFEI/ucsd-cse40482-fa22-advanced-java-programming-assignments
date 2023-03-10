package com.chengfei.regex;

import java.io.*;
import java.util.regex.*;

public class RegEx {
    public static void regex(String[] args) {
        if (args.length < 1) {
            System.out.println("Arguments required");
        }
        String fileName = args[0];
        // Load data from local file to program.
        String input = null;
        try (FileInputStream fis = new FileInputStream(fileName)) {
            input = new String(fis.readAllBytes());
        } catch (IOException e) {
            // If file is not found or something wrong happens, raise the IOException.
            e.printStackTrace();
            System.out.println("Something goes wrong with loading file!");
        }

        // If file is found and no other errors happen, start processing input texts.
        System.out.printf(
                """
                Processed the following input file:
                %s
                Results are as follows:
                """, fileName
        );

        // Find PAN IDs.
        System.out.println("- List of PAN IDs (Total of 2)");
        String[] panIDs = new String[2];
        // PAN IDs' regex pattern.
        String regex1 = "(?<PANID>PANID)\\s*=\\s*(?<index>[0-9a-fA-F]+)";
        Pattern pattern1 = Pattern.compile(regex1);
        Matcher matcher1 = pattern1.matcher(input);
        int i = 0;
        while (matcher1.find()) {
            panIDs[i] = matcher1.group("PANID") + " = " + matcher1.group("index");
            System.out.println(panIDs[i]);
            i++;
        }

        // Find MAC addresses.
        System.out.println("- List of MAC Addresses (Total of 4)");
        String[] MACaddr = new String[4];
        // MAC address's regex.
        String regex2 = "000781fe0000[0-9a-fA-F]{4}";
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(input);
        int j = 0;
        while (matcher2.find()) {
            MACaddr[j] = matcher2.group();
            System.out.println(MACaddr[j]);
            j++;
        }

        // Find RF_RSSI_M values for each MAC address.
        System.out.println("- List of RF_RSSI_M values for each MAC address");
        String[] RFRSSIM = new String[4];
        // RF_RSSI_M's regex.
        String regex3 = "-\\d{1,2}.\\d+";
        Pattern pattern3 = Pattern.compile(regex3);
        Matcher matcher3 = pattern3.matcher(input);
        int k = 0;
        while (matcher3.find()) {
            RFRSSIM[k] = matcher3.group();
            System.out.println(MACaddr[k] + " " + RFRSSIM[k]);
            k++;
        }
    }
}
