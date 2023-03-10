database.query.Lesson7Database Console Application



Usage: 

Read in the attached file (Lessons.sql/Lesson7_dropadd.sql) and use JDBC to populate the database.
After populating the database, query the Lessons table metadata to read the column names.
Use these column names as the headers for the output. Then query the contents of the Lessons table, and display each lesson number and title on a separate line.



Alert:
 
1. If the option or the parameter is entered incorrectly or missing, this console application won't work.
2. 
2.1. If running the program for the first time, input the file "Lesson7.sql" (without deletion of the older table).
2.2. If running the program more than one time, input the file "Lesson7_dropadd.sql" (delete the older version table before creating a newer one).



Example Run:

Run the program in IntelliJ succeeds.
* Before running this program, you should start the derby database server using shell/server-start.sh
Settings:
Working directory: src
CLI arguments: 
	Lessons.sql         // run for the first time.
        Lessons_dropadd.sql // otherwise.
Required external libraries: (include them in the IntelliJ)
	derby.jar
	derbyclient.jar
	derbynet.jar



The following method fails to run in the terminal and report "java.lang.ClassNotFoundException: org.apache.derby.jdbc.ClientDriver". (Still don't know why)
// $ java database.query.Lesson7Database Lessons.sql
// Database Connection info: ...
// Connecting to the database: Done.
// Populating the database: Done.
// Query of Lessons table results:
// ....



Details:

Alternative run:
Open a new terminal on the folder shell
Then enter
$ sh server-start.sh    // Start the derby server in the background process
$ sh db-connect.sh      // Connect to the derby database COREJAVA (located in the folder db)
                        // If the database COREJAVA is not present, initialize the database
// Then you can define tables / manipulate data in the database COREJAVA.
$ sh server-shutdown.sh // Shut down the derby server in the background process.