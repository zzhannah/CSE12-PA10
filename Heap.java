/******************************************************************************
							Han Zhang
							CSE 12, Winter 2020
							March 21st, 2020
							cs12wi20kj
				Assignment Ten
File Name:	Heap.java
Description:	Functions for Heap data structure. A Heap structure is a 
		specialized binary tree array-based data structure with 
		properties that the tree is complete and parent nodes have 
		higher priority than the child nodes. For this implementation,
		items are removed from the heap in alphabetic order.
******************************************************************************/
//=============================================================================
// class Heap
//
// Description:	Implement the heap data structure.
// 		Each heap starts with root with index 0.
// 		Able to remove, insert and write.
//
// Data Fields:
// 	debug		- to show debug message
// 	heap_count	- count of the heap
// 	heapcounter	- counter for heap_count
// 	heapArray	- to store heap as an array
// 	index		- index of the item stored
// 	occupancy	- occupancy of the heap
// 	root		- root of the heap
// 	storage		- size of heap
// 	TWO		- for calculate use
//
// Public Functions:
// 	debugOn		- turn debug on
// 	debugOff	- turn debug off
// 	isEmpty		- check if heap is empty
// 	insert		- insert the element user give
// 	remove		- remove the root
// 	toString	- give back readable data
//
//=============================================================================
public class Heap extends Base {
	//data fields
	private int index;		//index of the item stored
	private int occupancy; 		//occupancy of the heap
	private int storage;		//to store size of heap
	private long heap_count;	//count of the heap
	private long heapcounter = 0;	//counter for heap_count
	private TNode heapArray[];	//to store heap as an array
	private TNode root;		//root of the heap
	//debug flag
	private static boolean debug;	//if user need debug message
	//defined data
	private static final int  TWO = 2;	//for calculate use
	//debug messages
	private static final String ALLOCATE = " - Allocating]\n";
	private static final String AND = " and ";
	private static final String COMPARE = " - Comparing ";
	private static final String FULL = "Heap is full, could not insert!!";
	private static final String EMPTY = "Heap is empty!";
	private static final String INSERT = " - Inserting ";
	private static final String REMOVE = " - Removing ";
	private static final String HEAP = "[Heap ";
	/*=====================================================================
	 * Heap Constructor:
	 *
	 * Initialize all instance variables. Takes a size, which will be the
	 * size of the array used to store the data.
	 *
	 * @param	size of the heap should be
	 ====================================================================*/
	public Heap (int size) {
		//initialize values
		heap_count = ++heapcounter;
		storage = size;
		occupancy = 0;
		//create array and set root to null
		heapArray = new TNode[storage];
		root = null;
		//debug msg
		if (debug == true) {
			System.out.print(HEAP + heap_count + ALLOCATE);
		}
	}
        /*=====================================================================
	 * debugOn:
	 *
         * This method will turn debug on.
         ====================================================================*/
	public static void debugOn () {
		//turn debug on
		if (debug == false) {
			debug = true;
		}
	}
        /*=====================================================================
	 * debugOff:
	 *
         * This method will turn debug off.
         ====================================================================*/
	public static void debugOff () {
		//turn debug off
		if (debug == true) {
			debug = false;
		}
	}
	
	/*=====================================================================
	 * isEmpty:
	 *
	 * Check if the tree is empty by checking the occupancy
	 *
	 * @return 	return truth if is empty, false otherwise
	 ====================================================================*/
	public boolean isEmpty () {
		//get root to see if empty
		if (occupancy == 0) {
			return true;
		}
		return false;
	}
	/*=====================================================================
	 * insert:
	 *
	 * Inserts a UCSDStudent object. Each element is inserted in the array
	 * at the final leaf, and then reheap-up is performed to find its
	 * correct location
	 *
	 * @param	the data user wants to input
	 * @return	true if insert successfully, false otherwise
	 ====================================================================*/
	public boolean insert (Base element) {
		int parentindex;	//keep track of index
		TNode temp;		//keep track parent node
		TNode working;		//keep track current node
		//if storage is full
		if (occupancy == storage) {
			System.out.println(FULL);
			return false;
			//if no root, element becomes new root
		} else if (occupancy == 0) {
			root = new TNode(element);
			heapArray[0] = root;
			//debug msg
			if (debug == true) {
				System.out.print(HEAP+heap_count+INSERT+
						element.getName()+"]\n");
			}
		//else insert at the end
		} else {
			//create new node
			working = new TNode(element);
			working.index = occupancy-1;
			//save in the array
			heapArray[working.index] = working;
			//connect with parent
			parentindex = (working.index-1)/TWO;
			working.parent = heapArray[parentindex];
			//connect temp with its child
			//if index % 2 is 0, right child
			if ((working.index%TWO) == 0) {
				heapArray[parentindex].right = working;
				// otherwise, left child
			} else {
				heapArray[parentindex].left = working;
			} 
			//debug msg
			if (debug == true) {
				System.out.print(HEAP+heap_count+INSERT+
						element.getName()+"]\n");
			}
			//call TNodeInsert to edit heap
			working.reheapUp(heapArray[parentindex], working);
		}
		return true;
	}
	
	/*=====================================================================
	 * remove:
	 *
	 * removes the most important UCSDStudent object (stored at the root).
	 * The root element is replaced by the element at the final leaf, and 
	 * then reheap-down is performed to find its correct location.
	 *
	 * @return	null if not found
	 * 		stduent number if found
	 ====================================================================*/
	public Base remove () {
		Base temp;		//to save root's data
		int isleft;		//to track if left node
		int parentindex;	//to track parent index
		//if heap empty, return false
		if (occupancy == 0) {
			System.out.println(EMPTY);
			return null;
			//else remove root
		} else {
			//save root's data
			temp = root.data;
			//change root data to final leaf data
			root.data = heapArray[occupancy-1].data;
			//delete final leaf
			heapArray[occupancy-1].data = null;
			parentindex = (occupancy-TWO)/TWO;
			isleft = (occupancy-1)%TWO;
			//disconnect from its parent
			if (isleft == 1) {
				heapArray[parentindex].left = null;
			} else {
				heapArray[parentindex].right = null;
			}
			//decrease occupancy
			occupancy--;
			//debug msg
			if (debug == true) {
				System.out.print(HEAP+heap_count+REMOVE+
						temp.getName()+"]\n");
			}
			//call reheapDown to edit heap
			root.reheapDown(root, root.left, root.right);
			return temp;
		}
	}
        /*=====================================================================
	 * toString:
	 *
         * Creates a string representation of this tree. This method first
         * adds the general information of this tree, then calls the
         * recursive TNode function to add all nodes to the return string 
         *
         * @return  String representation of this tree 
         ====================================================================*/
	public String toString () {
		//print occupancy
		String string = "Heap " + heap_count + ":\noccupancy is ";
		string += occupancy + " elements.";
		//print all TNodes
		if(root != null)
			string += root.writeAllTNodes();
		return string;
	}
//=============================================================================
// class TNode
//
// Description:	Implement the TNode of heap data structure.
// 		Each TNode contains data, left, right, parent and index.
//
// Data Fields:
// 	data		- data stored in the current TNode
// 	index		- current index in heapArray
//	left		- pointer to the left child
//	parent		- pointer to the its parent
//	right		- pointer to the right child
//
// Public Functions:
// 	reheapDown	- reheap the heap from the root
// 	reheapUp	- reheap the heap from current TNode
// 	toString	- transfer string to readable data
//	writeAllTNodes	- collect all TNodes' data
//
//=============================================================================
	private class TNode {
		public Base data;	//to save data from user
		public TNode left, right, parent; //pointer to move in heap
		public int index;	//index in heapArray
		/*=============================================================
		 * TNode constructor:
		 *
		 * initialize all the elements of TNode
		 * takes in an element as its data
		 *
		 * @param	the data it should store
		 ============================================================*/
		public TNode (Base element) {
			//initialize TNode
			data = element;
			left = null;
			right = null;
			parent = null;
			index = 0;
			//increase occupancy
			occupancy++;
		}
		
		/*=============================================================
		 * reheapDown:
		 *
		 * Called from remove. Starting at the root, this method should
		 * move the former final leaf element down the tree until it 
		 * satisfies the heap-order, swapping places with its most
		 * important child until it is less important than its parent
		 * and more important than its children.
		 *
		 * @param	the current node needs to be compared
		 * @param	current node's left child to be compared
		 * @param	current node's right child to be compared
		 ============================================================*/
		public void reheapDown (TNode Parent, TNode Left, TNode Right) {
			Base temp;		//keep track parent data
		//if have both child
		if (Left != null && Right != null) {
			//debug msg
			if (debug == true) {
				System.out.print(HEAP + heap_count + COMPARE + 
						Right.data.getName() + AND + 
						Left.data.getName() + "]\n");
			}
			//compare two child first
			//if left is smaller
			if (Right.data.isGreaterThan(Left.data)) {
				//debug msg
				if (debug == true) {
					System.out.print(HEAP + heap_count + 
					COMPARE + Left.data.getName() + AND + 
					Parent.data.getName() + "]\n");
				}
				//swap with left if parent bigger
				if (Parent.data.isGreaterThan(Left.data)) {
					temp = Parent.data;
					Parent.data = Left.data;
					Left.data = temp;
					//go down the heap and check
					Left.reheapDown(Left, Left.left, 
							Left.right);
				//if left bigger, finish
				} else {
					return;
				}
			//if right is smaller
			} else {
				//debug msg
				if (debug == true) {
					System.out.print(HEAP + heap_count + 
					COMPARE + Right.data.getName() + AND + 
					Parent.data.getName() + "]\n");
				}
				//swap with right if parent bigger
				if (Parent.data.isGreaterThan(Right.data)) {
					temp = Parent.data;
					Parent.data = Right.data;
					Right.data = temp;
					//go down the heap and check
					Right.reheapDown(Right, Right.left, 
							Right.right);
				//if right bigger, finish
				} else {
					return;
				}
			}
		//if only have left child
		} else if (Left != null && Right == null) {
			//debug msg
			if (debug == true) {
				System.out.print(HEAP + heap_count + COMPARE + 
						Left.data.getName() + AND + 
						Parent.data.getName() + "]\n");
			}
			//swap with left if parent bigger
			if (Parent.data.isGreaterThan(Left.data)) {
				temp = Parent.data;
				Parent.data = Left.data;
				Left.data = temp;
			}
			return;	
		//if only have right child
		} else if (Right != null && Left == null) {
			//debug msg
			if (debug == true) {
				System.out.print(HEAP + heap_count + COMPARE + 
						Right.data.getName() + AND + 
						Parent.data.getName() + "]\n");
			}
			//swap with right if parent bigger
			if (Parent.data.isGreaterThan(Right.data)) {
				temp = Parent.data;
				Parent.data = Right.data;
				Right.data = temp;
				//go down the heap and check
				Right.reheapDown(Right, Right.left, 
						Right.right);
			} 
			return;	
		} 
		//if no child, finish
		return;
		}
		/*=============================================================
		 * reheapUp:
		 *
		 * Called from insert. Starting at the final leaf, this method
		 * should move the inserted element up the tree until it
		 * satisfies the heap-order, swapping places with its parent
		 * until it is less important than its parent and more 
		 * important than its children.
		 *
		 * @param	current TNode's parent node
		 * @param	current node working on
		 ============================================================*/
		public void reheapUp (TNode Parent, TNode working) {
			Base temp = Parent.data;	//to save Parent's data
			//debug msg
			if (debug == true) {
				System.out.print(HEAP + heap_count + COMPARE + 
						working.data.getName() + AND + 
						Parent.data.getName() + "]\n");
			}
			//if parent data is bigger, swap data with working
			if (Parent.data.isGreaterThan(working.data)){
				Parent.data = working.data;
				working.data = temp;
			}
			//swap until null
			if (Parent.parent != null) {
				Parent.reheapUp(Parent.parent, Parent);
			}
		}
        /*=============================================================
         * Creates a string representation of this node. Information
         * to be printed includes this node's height, its balance,
         * and the data its storing.
         *
         * @return  String representation of this node 
		 ============================================================*/
		public String toString () {
			return "at index: " + index + "  " + data;
		}
        /*=============================================================
         * Writes all TNodes to the String representation field. 
         * This recursive method performs an in-order
         * traversal of the entire tree to print all nodes in
         * sorted order, as determined by the keys stored in each
         * node. To print itself, the current node will append to
         * tree's String field.
         ============================================================*/
		public String writeAllTNodes () {
			String string = "";	//for printing string
			int count = 0;		//keep track index
			int total = occupancy;	//keep track occupancy
			//print from first index to last index
			while (count < total) {
				string += "\n" + heapArray[count];
				count++;
			}	
			return string;
		}
	}
}