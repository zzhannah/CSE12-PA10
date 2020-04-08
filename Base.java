/******************************************************************************
							Han Zhang
							CSE 12, Winter 2020
							March 21st, 2020
							cs12wi20kj
				Assignment TEN
 File Name:	Base.java
 Description:	The file contains abstract Base and its two methods, getName
 	        	and isGreaterThan. Used in Heap.java and Driver.java.
 Public Functions:
 	getName		- get name from a UCSDStduent, used in Driver.java
	isGreaterThan	- compare two student name and define if is greater, 
			used in Driver.java
******************************************************************************/
public abstract class Base {
	public String getName () {
		return null;
	}
	public boolean isGreaterThan (Base base) {     // isGreaterThan function
		return true;
	}
}
