#
#												Han Zhang
#												CSE 12, Winter 2020
#												March 21st, 2020
#												cs12wi20kj
#
# This is the Makefile to use for Final Assignment 
# To use, at the prompt, type:
#
#       make Driver             # This will make executable driver
# or
#       make clean              # This will safely remove old stuff
#make executable driver
Driver:	Driver.class
#compile java files
Driver.class:	Base.java Driver.java MyLib.java Heap.java
	javac -g Driver.java
	echo 'java Driver $$*' > Driver
	chmod ug+rx Driver
#valgrind not supported for java
valgrind_Driver: 
	echo "not supported"
#remove old stuff
clean:
	rm -f *.class Driver core
#remove old stuff and compile again
new:
	make clean
	make
public:
	make new
	
	# remove .o files to prepare for java executable creation
	rm -f *.o