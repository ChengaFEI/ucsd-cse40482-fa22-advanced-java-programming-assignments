package com.chengfei.iostream;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.chengfei.iostream.internal.*;

public class IOStream {
    public static void writeRead(String[] args) {
        // Get option from command line.
        String option = args[0];
        switch (option) {
            case "--help" ->
                // Display help info for this console application.
                    displayInfo();
            case "--text" ->
                // Writes/reads as text file and displays results on console.
                    displayText();
            case "--binary" ->
                // Writes/reads as binary file and displays results on console.
                    displayBinary();
            case "--object" ->
                // Writes/reads as object file and displays results on console.
                    displayObject();
            default ->
                // If no built-in option is triggered, alert an input error message.
                    System.out.println("Unknown command!");
        }
    }

    /*
    Display help info on console.
    Called by entering the parameter "--help".
     */
    public static void displayInfo() {
        System.out.println(
                """
                Usage: java Lesson1IOStream [arg]
                
                There should only be 1 argument passed and the argument following source file Lesson1IOStream is passed as argument to main class.
                where options include:
                
                    --help
                        Display help information for this console application, mainly including possible options to run the program.
                    --text
                        Write/read data as text file and display results on console.
                    --binary
                        Write/read data as binary file and display results on console.
                    --object
                        Write/read data as object file and display results on console.
                """);
    }

    /*
    Write/read data as text file and display results on console.
    Called by entering the parameter "--text".
     */
    public static void displayText() {
        // Initialize hardcode texts and file name.
        String message = "Hello World!";
        int number = 123456;
        boolean correct = true;
        String fileName = "text.txt";

        // Print hardcode texts.
        System.out.printf(
                """
                Hardcode texts are:
                %s
                %d
                %b
                """, message, number, correct
        );
        System.out.println("-".repeat(50));

        // Write text to local file.
        System.out.println("Writing to local file...");
        System.out.printf("(file name: %s)\n", fileName);
        try {
            PrintWriter pw = new PrintWriter(fileName, StandardCharsets.UTF_8);
            pw.println(message);
            pw.println(number);
            pw.println(correct);
            pw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Succeed!");
        System.out.println("-".repeat(50));

        // Read text from local file.
        System.out.println("Reading from local file...");
        String filePath = System.getProperty("user.dir") + File.separator + fileName;
        Path path = Paths.get(filePath);
        System.out.printf("(file address: %s)\n", filePath);
        try {
            String s = Files.readString(path);
            System.out.println("Texts in the file are: ");
            System.out.println(s);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    Write/read data as binary file and display results on console.
    Called by entering parameter "--binary".
     */
    public static void displayBinary() {
        // Hard code data and file name.
        String message = "Hello New World!";
        int number = 654321;
        boolean incorrect = false;
        String fileName = "binary.out";

        // Print hardcode texts.
        System.out.printf(
                """
                Hardcode texts are:
                %s
                %d
                %b
                """, message, number, incorrect
        );
        System.out.println("-".repeat(50));

        // Write data to local file.
        System.out.println("Writing to local file...");
        System.out.printf("(file name: %s)\n", fileName);
        File file = new File(fileName);
        try (
                FileOutputStream fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                DataOutputStream dos = new DataOutputStream(bos))
        {
            dos.writeBytes(message);
            dos.writeBytes(String.valueOf(number));
            dos.writeBytes(String.valueOf(incorrect));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Succeed!");
        System.out.println("-".repeat(50));

        // Read data from local file and display results on console.
        System.out.println("Reading from local file...");
        System.out.printf("(file name: %s)\n", fileName);
        try (
                FileInputStream fis = new FileInputStream(fileName);
                BufferedInputStream bis = new BufferedInputStream(fis);
                DataInputStream dis = new DataInputStream(bis))
        {
            String s = new String(dis.readAllBytes());
            System.out.println("Texts in the file are: ");
            System.out.println(s);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    Write/read data as object file and display results on console.
    Called by entering parameter "--object".
     */
    public static void displayObject() {
        // Hardcode objects and file name.
        Employee[] staff;
        Employee john = new Employee("John", 2000.0, 2000, 10, 1);
        Manager may = new Manager("May", 200000.0, 1990, 2, 10);
        may.setSecretary(john);
        Manager ash = new Manager("Amy", 15000000.0, 1980, 1, 1);
        ash.setSecretary(john);
        staff = new Employee[3];
        staff[0] = john;
        staff[1] = may;
        staff[2] = ash;
        String fileName = "object.dat";

        // Write objects to local file.
        try (
                ObjectOutputStream oos =
                        new ObjectOutputStream(
                                new FileOutputStream(fileName)
                        ))
        {
            oos.writeObject(staff);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Read objects from local file.
        try (
                ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream(fileName)
                ))
        {
            Employee[] newStaff = (Employee[]) ois.readObject();
            for (Employee employee : newStaff) {
                System.out.println(employee);
            }
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
