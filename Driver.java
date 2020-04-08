/******************************************************************************
                                                        Han Zhang
                                                        CSE 12, Winter 2020
                                                        March 21st, 2020
                                                        cs12wi20kj
                                Assignment Ten
File Name:      Driver.java
Description:    Contains the member functions for the UCSDStudent class as part 
	        	of the Driver.java file used to test Heap. Is able to use 
	        	isGreaterThan function for heap and contains main for heap.
	        	Work together with Heap.java.
******************************************************************************/
import java.io.*;
//=============================================================================
//Class UCSDStduent
//
//Description:		Extends from Base
//			initialize incoming values and provide isGreaterThan and 
//			equals functions for Heap
//
//Data Fields:		
//	name		- holds the name inserted from user
//	stduentnum	- holds the student num inserted from user
//
//Public Functions:	
//	equals		- compare the name of the elements
//	getName		- other classes can get name from here
//	isGreaterThan	- compare name and return truth value
//	toString	- print out strings
//
//=============================================================================
class UCSDStudent extends Base {
	private String name;		//to store student name
	private long studentnum;	//to store stduent number
	/*=====================================================================
	 * UCSDstudent constructor:
	 *
	 * take in name and student number from user and initialize all values
	 *
	 * @param	student name gives from user
	 * @param	stduent number gives from user
	 ====================================================================*/
	public UCSDStudent(String name, long studentnum) {
		//initialize
		this.name = new String(name);
		this.studentnum = studentnum;
	}
	/*=====================================================================
	 * equals:
	 *
	 * takes in another UCSDStudent object and compare name with current 
	 * one if is equal
	 *
	 * @param	another object need to be compared
	 * @return	if name is same, return true
	 * 		otherwise return false
	 ====================================================================*/
	public boolean equals(Object other) {
		//if the same, return true
		if (this == other) {
			return true;
		}
		//if not a instance of UCSDStudent
		//return false
		if (!(other instanceof UCSDStudent)) {
			return false;
		}
		//get other element and cast to UCSDStudent
		UCSDStudent otherstu = (UCSDStudent)other;
		//compare name of elements
		return name.equals(otherstu.getName());
	}
	/*=====================================================================
	 * isGreaterThan:
	 *
	 * Takes in another UCSDStudent object and compare name with current
	 * one if is bigger
	 *
	 * @param	another object need to be compared
	 * @return	if name is greater, return true
	 * 		otherwise returnfalse
	 ====================================================================*/
	public boolean isGreaterThan(Base base) {
		//define if this name is greater
		return (name.compareTo (base.getName())>0) ? true:false;
	}
	/*=====================================================================
	 * getName:
	 *
	 * Allow user to get name of current student
	 *
	 * @return	this student's name
	 ====================================================================*/
	public String getName() {
		//return student name
		return this.name;
	}
	/*=====================================================================
	 * toString:
	 *
	 * translate readable data to user
	 *
	 * @return	string of student name and student number
	 ====================================================================*/
	public String toString () {
		//return readable data to user
		return "name:  " + name + "  studentnum:  " + studentnum;
	}
}
//=============================================================================
// class Driver
//
// Description:	Contains the main of this driver. Able to call UCSDStudent and 
// 		Heap methods to reach user's goal.
//
// Data Fields:
// 	buffer		- user's string input for student name
// 	command		- user's character input for choose command
// 	number 		- user's number input for student number
// 	run		- keep track of eof
// 	size		- user's number input for heap size
// 	NULL		- set null to 0
//
// Public Functions:
// 	main		- ask user for input and call methods to reach goal
//
//=============================================================================
public class Driver {
	private static final short NULL = 0;
	/*=====================================================================
	 * main:
	 *
	 * Ask user for input and give result for user. 
	 * i for insert, user can enter name and number for the student.
	 * r for remove, delete the first element in the heap.
	 * w for write, shows user the elements in the heap.
	 *
	 * @param	stores arguments passed by command line while starting
	 * 		a program
	 ====================================================================*/
	public static void main (String [] args) {
		//initialize debug states
		Heap.debugOff();
		//check command line options
		for (int index = 0; index < args.length; ++index) {
			if (args[index].equals("-x"))
				Heap.debugOn();
		}
		//The real start of the code
		Heap heap;		//create a heap object
		String buffer = null;	//keep track student name
		char command = NULL;	//keep track command word
		long number = 0;	//keep track student number
		boolean run = true;	//keep track eof	
		int size = 0;		//keep track heap size
		//loop asking for size
		try{
		while (size == 0) {	
			//ask input for size
			System.out.print("Please enter the number of " + 
					"objects to be able to store: ");
			//get size from user
			size = (int)MyLib.decin ();
			//get rid of return
			MyLib.clrbuf (command);	
			//if size is 0, ask for another input
			if (size == 0) {
				System.out.println("Invalid Heap size."+ 
					"Please enter a size greater than 0.");
			}
		}
		}
		catch (EOFException eof) {
			run = false;
		}
		//create heap by pass in the size
		heap = new Heap(size);
		//keep track eof
		if (run == true) {
			//print initial heap
			System.out.println ("Initial Heap:\n" + heap);
		}
		while (run == true) {
			command = NULL; // reset command each time in loop
			System.out.print ("Please enter a command:  " + 
					"((i)nsert, (r)emove, (w)rite):  ");
			try {
				command = MyLib.getchar ();
				MyLib.clrbuf (command); // get rid of return
			switch (command) {
			//if enter i, call insert
			case 'i':
				System.out.print
				("Please enter UCSD student name to insert:  ");
				buffer = MyLib.getline ();// formatted input
				System.out.print
				("Please enter UCSD student number:  ");
				number = MyLib.decin ();
				MyLib.clrbuf (command); // get rid of return
					
				// create student and place in heap
					
				heap.insert(new UCSDStudent (buffer, number));
					
				break;
			//if enter r, call remove
			case 'r': { 
				  UCSDStudent removed; //to save returning data
				  removed = (UCSDStudent)heap.remove();
				  //if success, return student info
				  if (removed != null) {
				  System.out.println("Student removed!"); 
				  System.out.println(removed);
				  } 
			}
			break;
			//if enter w , write out the heap
			case 'w':
			System.out.println("The Heap contains:\n" + heap);
			}
			}
			catch (EOFException eof) {
				break;
			}
		}
		//new line
		System.out.print("\n");
		//keep track eof
		if (run == true) {
			//print final heap
			System.out.println("\nFinal Heap:\n" + heap);
		}
	}
}