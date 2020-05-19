import java.io.PrintWriter;

// class definition for a singly-linked list
// updated 06-May-2018 cjk for CSC 161
// using this example as a base and then modifying
// modifyed by Francisco J Segarra Jr

public class LinkedList 
{
   private Node head;
   private int count;  	// for size
	
	// constructor that creates an empty linked list
   public LinkedList()
   {
      head = null;
      count = 0;
   }
	
	// constructor that initializes the linked list
	// to a node
   public LinkedList(Node n)
   {
      head = n;
      count = 1;
   }
	
   public boolean isEmpty()
   {
   	//return (head == null);
      return (count == 0);
   }
	
   public void insertName(String fn, String ln, double s, String g)
   {
      Node n = new Node(fn, ln, s, g);
      insertName(n);
   }
	
	// add a node at a designated location in a linked list
   public void insertName(Node n)
   {
   	// Case 1:
      if (head == null)  // the list is empty
      {
         head = n;
         count++;
      }
      else 
      
      // Case 2:
         if (n.getLastName().compareTo(head.getLastName()) < 0 || (n.getLastName().equals(head.getLastName()) && (n.getFirstName().compareTo(head.getFirstName()) <0)))
         // add at beginning of list
         {
         // set the next node of n to 
         //point to what head points to
            n.setNext(head);
            head = n;
            count++;
         }
         
         else
         {
         // Use a pair of node pointers to traverse
         // the list.  One pointer follows behind
         // the other.
            Node p = head;
            Node q = null;
         
         // walk down the list until we get
         // to the correct location
            boolean endOfList = false;
         
         // The while loop uses a short-circuit evaluation.
         // Ask if you don't know what that means.
            while ((p!=null)
              && ((n.getLastName().compareTo(p.getLastName()) > 0) || (n.getLastName().equals(p.getLastName()) && (n.getFirstName().compareTo(p.getFirstName())> 0)))
              &&(!endOfList))
            {
            // determine if we have reached the
            // end of the list.
               q = p;
               p = p.getNext();
            
               if (p == null)
               {
                  endOfList = true;
               
               }
            
            } // end while
         
         // put the new node in the
         // list after p
            q.setNext(n);
            n.setNext(p);
            count++;
         		
         } // end else
   	
   }  // end method to insert at a location
	
	
   public void insertScore(String fn, String ln, double s, String g)
   {
      Node n = new Node(fn, ln, s, g);
      insertScore(n);
   }
	
	// add a node at a designated location in a linked list
   public void insertScore(Node n)
   {
   	// Case 1:
      if (head == null)  // the list is empty
      {
         head = n;
         count++;
      }
      else 
      
      // Case 2:
         if (n.getScore() > (head.getScore()))
         // add at beginning of list
         {
         // set the next node of n to 
         //point to what head points to
            n.setNext(head);
            head = n;
            count++;
         }
         
         else
         {
         // Use a pair of node pointers to traverse
         // the list.  One pointer follows behind
         // the other.
            Node p = head;
            Node q = null;
         
         // walk down the list until we get
         // to the correct location
            boolean endOfList = false;
         
         // The while loop uses a short-circuit evaluation.
         // Ask if you don't know what that means.
            while ((p!=null) && (n.getScore() < p.getScore()) && (!endOfList))
            {
            // determine if we have reached the
            // end of the list.
               q = p;
               p = p.getNext();
            
               if (p == null)
               {
                  endOfList = true;
               
               }
            
            } // end while
         
         // put the new node in the
         // list after p
            q.setNext(n);
            n.setNext(p);
            count++;
         		
         } // end else
   	
   }  // end method to insert at a location
	
	// method to return the number of nodes in the list
   int size()
   {
      return count;
   }
	
	// method to display the contents of 
	// a linked list
   public void display(PrintWriter outFile)
   {
      outFile.println();
      outFile.println();
   	
      Node p = head;
      while (p != null)
      {
         outFile.print(p.toString());
         p = p.getNext();
      } // end while
   
   } // end display method
	
}  // end class LinkedList
