Lesson5XML Console Application



Usage: 

A Java console application does the following:

- Accepts "--num-threads NUMBER-OF-THREADS" where you can specify how many threads to create. 
- Accepts "---" if you want to use locking. By default, you do not use any locking
- Accepts "--AtomicLong" if you want to use AtomicLong. By default, you do not use any locking.

The program should start by creating the number of threads specified on the command line. The threads should count the number of characters that are in the .class and .java files for this project, putting their totals into the following variable:
public static long count = 0;
At the end of the program, display the total count.



Alert:
 
If the option or the parameter is entered incorrectly or missing, this console application won't work.
After each command, you should press Ctrl+C/Ctrl+D to interrupt the process manually.



Details:



Example Run:

$ java Lesson5Concurrent --num-threads 8 
$ java Lesson5Concurrent --num-threads 8 --ReentrantLock
$ java Lesson5Concurrent --num-threads 8 --AtomicLong
$ java Lesson5Concurrent --num-threads 16 
$ java Lesson5Concurrent --num-threads 16 --ReentrantLock
$ java Lesson5Concurrent --num-threads 16 --AtomicLong