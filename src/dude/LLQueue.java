/*
 * Name: Xavier Cho
 * Class: CSCE A311 - Data Structures & Algorithms
 * Instructor: Dr. Martin Cenek
 * Date: November 22, 2015
 * Homework #: 5
   Compiler: java 1.80_60
   IDE: Netbeans 8.02
 * 
 * 
*/

public class LLQueue {
  
private HeadTailList theList;

void display() {
int counter = 0;
        
while(!theList.isEmpty()){
System.out.println( theList);
counter++;
if (counter ==1){
	break;
  }
 }
}
 
 public class HeadTailList{//makes the head and the tail of the queue
 private Link head; // makes link head to connect later on when constructing the queue
 private Link tail;
   
 public HeadTailList(){//makes sure the head and the tail are null//initializes head and tail
  head = null;
  tail = null;  
  }
    
public boolean isEmpty(){// checks to see if the head is empty via null 
 return head == null;
 }
    
public void insertHead(String data){//inserts the head of the data 
Link newLink = new Link(data);//creates the new link 
if(isEmpty()){// checks to see if the list is empty 
 tail = newLink; //makes the new link into the tail 
 newLink.next = head;// makes the next as the head 
 head = newLink;// creates the head as the new link 
 }
}
    
public void insertTail(String data){
 Link newLink = new Link(data);
 if(isEmpty())
 head = newLink;//checks to see if it is empty // and inserts the data into the head 
  else
  tail.next = newLink;// otherwise makes the tail.next tot he new link 
  tail = newLink;
}
     
public String peek(){
 return head.Webpage;// checks the first webpage
}

public String deleteHead(){// deletes the head makes a temp them makes the next node as head 
 String temp = head.Webpage;
        
if(head.next == null)
 tail = null;
 head = head.next;
 return temp; 
}

 public String toString(){
  String str = "";
  Link present = head;
  while (present != null){// whiles the head/present/data is not null 
   str += present.toString();// turns it to string 
   present = present.next;// current is assisgned to the next value/current.next
 }
  return str;// returns the string
 }
}
 
public LLQueue(){
 theList = new HeadTailList();// makes a new list of headtaillist
 }

public boolean isEmpty(){//calls function to check if empty 
 return theList.isEmpty();
 }
    
public String peek(){
 return theList.peek();// calls function to see the first node of the list 
 }

public void enqueue(String thisPage){
 theList.insertTail(thisPage);   //calls the function to insert the data in the back of the list 
 }
 
public String dequeue(){
 return theList.deleteHead();//calls function to delete head and point to next
}

 public String toString(){
  return theList.toString();//puts the list into string 
  }
}
  



