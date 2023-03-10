Lesson6Networking Console Application



Usage: 

$ java Lesson6NetworkingServer --port NUMBER
When you start the Lesson6NetworkingServer, give it a port number to listen on. 

$ java Lesson6NetworkingClient --server ADDRESS --port PORT 
After starting up the Lesson6NetworkingServer, in another console window, 
start up the Lesson6NetworkingClient, specifying the server address and port number. 
Then connect to the server, and display the info returned by the server to the console.

When a client connects to the server, send back the following:

HTTP/1.0 200 OK

Content-Type: text/html

<html>
	<head>
		<title>Java Networking</title>
	</head>
	<body>
		<h1>Java Networking</h1>
	</body>
</html>



Alert:
 
1. If the option or the parameter is entered incorrectly or missing, this console application won't work.
2. The Lesson6NetworkingServer must be set up before calling a Lesson6Networking Client; otherwise, the Lesson6Networking Client will report a "connection refused" error.



Details:
The server and the client are recommended to run on separate terminals, or the server can be accessed through your web browser by entering "localhost"/"localhost:<your port>".


Example Run:

First step:
$ java Lesson6NetworkingServer --port NUMBER 
Second step:
$ java Lesson6NetworkingClient --server ADDRESS --port PORT 