# Client-Server-Math-Application
# Variables
JAVAC = javac
JFLAGS = -g

# Targets and Dependencies
all: MATHServer MATHClient TCPServer TCPClient

MATHServer: MATHServer.java
	$(JAVAC) $(JFLAGS) MATHServer.java

MATHClient: MATHClient.java
	$(JAVAC) $(JFLAGS) MATHClient.java

TCPServer: TCPServer.java
	$(JAVAC) $(JFLAGS) TCPServer.java

TCPClient: TCPClient.java
	$(JAVAC) $(JFLAGS) TCPClient.java

# Clean target
clean:
	rm -f *.class
