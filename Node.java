// a class for a node in a linked list
// updated 06-May-2018 cjk for CSC 161
// using as base and then modifying original code
// modifyed by Francisco J Segarra jr

public class Node 
{
	// data members
	private Node next;
	private String firstName, lastName, grade;
	private double score;
		
	// Constructor that takes a parameter for
	// the data field
	public Node(String fn, String ln, double s, String g)
	{
		firstName = fn;
		lastName = ln;
		score = s;
		grade = g;
		next = null;
	}
	
	// set methods
	public void setNext(Node n)
	{
		next = n;
	}
	
	
	// get methods
	public String getFirstName()
	{
		return firstName;
		
	}
	
	public String getLastName()
	{
		return lastName;
		
	}
	
	public double getScore()
	{
		return score;
	}
	
	public String getGrade()
	{
		return grade;
		
	}
	
	public Node getNext()
	{
		return next;
	}
	
	public String toString()
	{
		String msg = String.format("%-15s  %-15s   %-6.2f  %-2s\n", firstName, lastName, score, grade);
		return msg;
	}

} // end class
