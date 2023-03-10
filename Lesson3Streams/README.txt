Lesson3Streams Console Application



Usage: 

Enter "JobResult_124432.txt", and the program will find the number of times the pattern 00000000nnnnnnnn occurs (n could be any hex number) using stream and parallelStream. 
Then the program will compare the time taken when using both methods.
If the stream takes less time, the program will double the length of the input text and compare again until the parallelStream takes less time.



Alert:
 
If the text file name is entered incorrectly or missing, this console application won't work.



Details:

-1- Start by reading the contents of the attached JobResult_124432.txt file into a String in your Java program.
-2- Display the size of the String. Then measure the difference when counting the number of times the pattern 00000000nnnnnnnn occurs (where n can be any hex number, remember RegEx!), using Java Streams, first with a (non-parallel) stream, then with a parallelStream. Call System.currentTimeMillis before and after the call and print the difference. Your output should look like this:
	Try 1:
	String Size: ....
	Millisecs using stream: ....
	Millisecs using parallelStream: ....
-3- Which was faster? stream or parallelString? Display your results. If stream was faster, try another time, showing your output like this:
	Results: stream was nnnn faster than parallelStream
	Doubling String size and trying again
-4- Double the size of String by creating a new String that two copies of the existing string (i.e. String + String), then repeat #2 and #3 above.
-5- Continue #2, #3, and #4 until you reach a point where parallelStream is faster than stream, at which point your output should look like this:
	Results: paralleStream was nnnn faster than stream.
	All done!



Example Run:

$ java Lesson3Streams  JobResult_124432.txt